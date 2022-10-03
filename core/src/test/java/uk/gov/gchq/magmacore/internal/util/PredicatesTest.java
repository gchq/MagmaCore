/*
 * Copyright 2021 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package uk.gov.gchq.magmacore.internal.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.function.Predicate;

import org.junit.Test;

import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;
import uk.gov.gchq.magmacore.database.MagmaCoreJenaDatabase;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.Person;
import uk.gov.gchq.magmacore.hqdm.model.PointInTime;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IriBase;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Check that {@link Predicates} works correctly.
 */
public class PredicatesTest {

    private static final IriBase BASE = new IriBase("base", "http://test.co.uk/base#");

    private static Instant begin = LocalDateTime.now().minusDays(1L).toInstant(ZoneOffset.UTC);
    private static Instant end = LocalDateTime.now().plusDays(1L).toInstant(ZoneOffset.UTC);

    /**
     * Check that we get the default value when there is no ENTITY_NAME that can be parsed to an
     * Instant.
     */
    @Test
    public void testGetInstantWithNoValuePresent() {
        final Event event = SpatioTemporalExtentServices.createEvent("test_id");
        final Instant now = Instant.now();
        final Instant value = Predicates.getInstant(event, now);

        assertEquals(now, value);
    }

    /**
     * Check that we get the correct value when there is an ENTITY_NAME that can be parsed to an
     * Instant.
     */
    @Test
    public void testGetInstantWithValuePresent() {
        final Event event = SpatioTemporalExtentServices.createEvent("test_id");
        final Instant now = Instant.now();
        event.addValue(HQDM.ENTITY_NAME, now.toString());

        final Instant value = Predicates.getInstant(event, end);

        assertEquals(now, value);
    }

    /**
     * Check that we get the default value when there is an ENTITY_NAME that cannot be parsed to an
     * Instant.
     */
    @Test
    public void testGetInstantWithInvalidValuePresent() {
        final Event event = SpatioTemporalExtentServices.createEvent("test_id");
        event.addValue(HQDM.ENTITY_NAME, "oops");

        final Instant value = Predicates.getInstant(event, end);

        assertEquals(end, value);
    }

    /**
     * Check that a {@link PointInTime} between the BEGINNING and ENDING of an object is assessed as
     * valid.
     */
    @Test
    public void testIsValidAtPointInTimeBetweenBeginAndEnd() {
        final MagmaCoreDatabase db = new MagmaCoreJenaDatabase();

        // `now` is between two Instant values.
        final Instant now = Instant.now();
        final PointInTime pointInTime = SpatioTemporalExtentServices.createPointInTime("test_point_in_time");
        pointInTime.addStringValue(HQDM.ENTITY_NAME, now.toString());

        // Create an object with a BEGINNING and an ENDING
        final Person person = SpatioTemporalExtentServices.createPerson("test_person");
        final PointInTime begin = SpatioTemporalExtentServices.createPointInTime(new IRI(BASE, "beginning").toString());
        final PointInTime end = SpatioTemporalExtentServices.createPointInTime(new IRI(BASE, "ending").toString());

        person.addValue(RDFS.RDF_TYPE, HQDM.PERSON);
        person.addValue(HQDM.BEGINNING, new IRI(begin.getId()));
        person.addValue(HQDM.ENDING, new IRI(end.getId()));

        begin.addValue(RDFS.RDF_TYPE, HQDM.POINT_IN_TIME);
        begin.addValue(HQDM.ENTITY_NAME, begin.toString());
        end.addValue(RDFS.RDF_TYPE, HQDM.POINT_IN_TIME);
        end.addValue(HQDM.ENTITY_NAME, end.toString());

        db.begin();

        // Persist the objects and test the predicate.
        db.create(person);
        db.create(begin);
        db.create(end);

        final Predicate<Thing> predicate = Predicates.isValidAtPointInTime(db, pointInTime);

        assertTrue(predicate.test(person));

        db.commit();
    }

    /**
     * Check that a {@link PointInTime} at the BEGINNING of an object is assessed as valid.
     */
    @Test
    public void testIsValidAtPointInTimeAtBeginning() {
        final MagmaCoreDatabase db = new MagmaCoreJenaDatabase();

        // This is the BEGINNING of the object.
        final Instant when = begin;
        final PointInTime pointInTime = SpatioTemporalExtentServices.createPointInTime("test_point_in_time");
        pointInTime.addStringValue(HQDM.ENTITY_NAME, when.toString());

        // Create an object with a BEGINNING and an ENDING
        final Person person = SpatioTemporalExtentServices.createPerson("test_person");
        final PointInTime begin = SpatioTemporalExtentServices.createPointInTime(new IRI(BASE, "beginning").toString());
        final PointInTime end = SpatioTemporalExtentServices.createPointInTime(new IRI(BASE, "ending").toString());

        person.addValue(RDFS.RDF_TYPE, HQDM.PERSON);
        person.addValue(HQDM.BEGINNING, new IRI(begin.getId()));
        person.addValue(HQDM.ENDING, new IRI(end.getId()));

        begin.addValue(RDFS.RDF_TYPE, HQDM.POINT_IN_TIME);
        begin.addValue(HQDM.ENTITY_NAME, begin.toString());
        end.addValue(RDFS.RDF_TYPE, HQDM.POINT_IN_TIME);
        end.addValue(HQDM.ENTITY_NAME, end.toString());

        db.begin();

        // Persist the objects and test the predicate.
        db.create(person);
        db.create(begin);
        db.create(end);

        final Predicate<Thing> predicate = Predicates.isValidAtPointInTime(db, pointInTime);

        assertTrue(predicate.test(person));

        db.commit();
    }

    /**
     * Check that a {@link PointInTime} at the ENDING of an object is assessed as valid.
     */
    @Test
    public void testIsValidAtPointInTimeAtEnd() {
        final MagmaCoreDatabase db = new MagmaCoreJenaDatabase();

        // This is the ENDING of the object.
        final Instant when = end;
        final PointInTime pointInTime = SpatioTemporalExtentServices.createPointInTime("test_point_in_time");
        pointInTime.addStringValue(HQDM.ENTITY_NAME, when.toString());

        // Create an object with a BEGINNING and an ENDING
        final Person person = SpatioTemporalExtentServices.createPerson("test_person");
        final PointInTime begin = SpatioTemporalExtentServices.createPointInTime(new IRI(BASE, "beginning").toString());
        final PointInTime end = SpatioTemporalExtentServices.createPointInTime(new IRI(BASE, "ending").toString());

        person.addValue(RDFS.RDF_TYPE, HQDM.PERSON);
        person.addValue(HQDM.BEGINNING, new IRI(begin.getId()));
        person.addValue(HQDM.ENDING, new IRI(end.getId()));

        begin.addValue(RDFS.RDF_TYPE, HQDM.POINT_IN_TIME);
        begin.addValue(HQDM.ENTITY_NAME, begin.toString());
        end.addValue(RDFS.RDF_TYPE, HQDM.POINT_IN_TIME);
        end.addValue(HQDM.ENTITY_NAME, end.toString());

        db.begin();

        // Persist the objects and test the predicate.
        db.create(person);
        db.create(begin);
        db.create(end);

        final Predicate<Thing> predicate = Predicates.isValidAtPointInTime(db, pointInTime);

        assertTrue(predicate.test(person));

        db.commit();
    }

    /**
     * Check that a {@link PointInTime} before the BEGINNING is not valid.
     */
    @Test
    public void testIsNotValidAtPointInTimeBeforeBeginning() {
        final MagmaCoreDatabase db = new MagmaCoreJenaDatabase();

        // This is the ENDING of the object.
        final Instant when = begin;
        final PointInTime pointInTime = SpatioTemporalExtentServices.createPointInTime("test_point_in_time");
        pointInTime.addStringValue(HQDM.ENTITY_NAME, when.toString());

        // Create an object with a BEGINNING and an ENDING
        final Person person = SpatioTemporalExtentServices.createPerson("test_person");
        final PointInTime begin = SpatioTemporalExtentServices.createPointInTime(new IRI(BASE, "beginning").toString());
        final PointInTime end = SpatioTemporalExtentServices.createPointInTime(new IRI(BASE, "ending").toString());

        person.addValue(RDFS.RDF_TYPE, HQDM.PERSON);
        person.addValue(HQDM.BEGINNING, new IRI(begin.getId()));
        person.addValue(HQDM.ENDING, new IRI(end.getId()));

        begin.addValue(RDFS.RDF_TYPE, HQDM.POINT_IN_TIME);
        begin.addValue(HQDM.ENTITY_NAME, Instant.now().toString());
        end.addValue(RDFS.RDF_TYPE, HQDM.POINT_IN_TIME);
        end.addValue(HQDM.ENTITY_NAME, end.toString());

        db.begin();

        // Persist the objects and test the predicate.
        db.create(person);
        db.create(begin);
        db.create(end);

        final Predicate<Thing> predicate = Predicates.isValidAtPointInTime(db, pointInTime);

        assertFalse(predicate.test(person));

        db.commit();
    }

    /**
     * Check that a {@link PointInTime} after the ENDING is not valid.
     */
    @Test
    public void testIsNotValidAtPointInTimeAfterEnding() {
        final MagmaCoreDatabase db = new MagmaCoreJenaDatabase();

        // This is the ENDING of the object.
        final Instant when = end;
        final PointInTime pointInTime = SpatioTemporalExtentServices.createPointInTime("test_point_in_time");
        pointInTime.addStringValue(HQDM.ENTITY_NAME, when.toString());

        // Create an object with a BEGINNING and an ENDING
        final Person person = SpatioTemporalExtentServices.createPerson("test_person");
        final PointInTime begin = SpatioTemporalExtentServices.createPointInTime(new IRI(BASE, "beginning").toString());
        final PointInTime end = SpatioTemporalExtentServices.createPointInTime(new IRI(BASE, "ending").toString());

        person.addValue(RDFS.RDF_TYPE, HQDM.PERSON);
        person.addValue(HQDM.BEGINNING, new IRI(begin.getId()));
        person.addValue(HQDM.ENDING, new IRI(end.getId()));

        begin.addValue(RDFS.RDF_TYPE, HQDM.POINT_IN_TIME);
        begin.addValue(HQDM.ENTITY_NAME, begin.toString());
        end.addValue(RDFS.RDF_TYPE, HQDM.POINT_IN_TIME);
        end.addValue(HQDM.ENTITY_NAME, Instant.now().toString());

        db.begin();

        // Persist the objects and test the predicate.
        db.create(person);
        db.create(begin);
        db.create(end);

        final Predicate<Thing> predicate = Predicates.isValidAtPointInTime(db, pointInTime);

        assertFalse(predicate.test(person));

        db.commit();
    }
}
