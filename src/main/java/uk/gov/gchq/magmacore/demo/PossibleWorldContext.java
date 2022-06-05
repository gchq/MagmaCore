package uk.gov.gchq.magmacore.demo;

import static uk.gov.gchq.hqdm.iri.HQDM.ENTITY_NAME;
import static uk.gov.gchq.magmacore.util.DataObjectUtils.USER_BASE;

import java.util.ArrayList;
import java.util.List;

import uk.gov.gchq.hqdm.iri.HQDM;
import uk.gov.gchq.hqdm.iri.IRI;
import uk.gov.gchq.hqdm.model.Association;
import uk.gov.gchq.hqdm.model.Class;
import uk.gov.gchq.hqdm.model.Event;
import uk.gov.gchq.hqdm.model.FunctionalSystem;
import uk.gov.gchq.hqdm.model.Participant;
import uk.gov.gchq.hqdm.model.Person;
import uk.gov.gchq.hqdm.model.PointInTime;
import uk.gov.gchq.hqdm.model.PossibleWorld;
import uk.gov.gchq.hqdm.model.Role;
import uk.gov.gchq.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.hqdm.model.StateOfFunctionalSystem;
import uk.gov.gchq.hqdm.model.StateOfPerson;
import uk.gov.gchq.hqdm.model.Thing;
import uk.gov.gchq.hqdm.services.SpatioTemporalExtentServices;

/**
 * Class for building HQDM models.
 * Adds objects to the supplied {@link PossibleWorld}.
 *
 * */
public class PossibleWorldContext {

    private List<Thing> objects;
    private PossibleWorld possibleWorld;

    /**
     * Constructor that creates a new {@link PossibleWorld}.
     *
     * @param name The {@link PossibleWorld} name.
     * */
    public PossibleWorldContext(final String name) {
        this(SpatioTemporalExtentServices.createPossibleWorld(name));
    }

    /**
     * Constructor.
     *
     * @param p {@link PossibleWorld}
    */
    public PossibleWorldContext(final PossibleWorld p) {
        this.possibleWorld = p;
        objects = new ArrayList<>();
        objects.add(p);
    }

    /**
     * Get the model objects.
     *
     * @return a {@link List} of {@link Thing}
    */
    public List<Thing> getObjects() {
        return objects;
    }

    /**
     * Create a PointInTime.
     *
     * @param value {@link String} value of the date-time.
     * @return {@link PointInTime}
    */
    public PointInTime createPointInTime(final String value) {
        final PointInTime o = SpatioTemporalExtentServices.createPointInTime(new IRI(USER_BASE, value).getIri());
        o.addStringValue(ENTITY_NAME.getIri(), value);
        objects.add(o);
        o.addValue(HQDM.PART_OF_POSSIBLE_WORLD.getIri(), possibleWorld.getId());
        return o;
    }

    /**
     * Create a Person.
     *
     * @param name {@link String} name of the entity.
     * @return {@link Person}
    */
    public Person createPerson(final String name) {
        final Person o = SpatioTemporalExtentServices.createPerson(new IRI(USER_BASE, name).getIri());
        o.addStringValue(ENTITY_NAME.getIri(), name);
        objects.add(o);
        o.addValue(HQDM.PART_OF_POSSIBLE_WORLD.getIri(), possibleWorld.getId());
        return o;
    }

    /**
     * Create a StateOfPerson.
     *
     * @param name {@link String} name of the entity.
     * @return {@link StateOfPerson}
    */
    public StateOfPerson createStateOfPerson(final String name) {
        final StateOfPerson o = SpatioTemporalExtentServices.createStateOfPerson(new IRI(USER_BASE, name).getIri());
        o.addStringValue(ENTITY_NAME.getIri(), name);
        objects.add(o);
        o.addValue(HQDM.PART_OF_POSSIBLE_WORLD.getIri(), possibleWorld.getId());
        return o;
    }

    /**
     * Create a FunctionalSystem.
     *
     * @param name {@link String} name of the entity.
     * @return {@link FunctionalSystem}
    */
    public FunctionalSystem createFunctionalSystem(final String name) {
        final FunctionalSystem o = 
            SpatioTemporalExtentServices.createFunctionalSystem(new IRI(USER_BASE, name).getIri());
        o.addStringValue(ENTITY_NAME.getIri(), name);
        objects.add(o);
        o.addValue(HQDM.PART_OF_POSSIBLE_WORLD.getIri(), possibleWorld.getId());
        return o;
    }

    /**
     * Create a StateOfFunctionalSystem.
     *
     * @param name {@link String} name of the entity.
     * @return {@link StateOfFunctionalSystem}
    */
    public StateOfFunctionalSystem createStateOfFunctionalSystem(final String name) {
        final StateOfFunctionalSystem o = 
            SpatioTemporalExtentServices.createStateOfFunctionalSystem(new IRI(USER_BASE, name).getIri());
        o.addStringValue(ENTITY_NAME.getIri(), name);
        objects.add(o);
        o.addValue(HQDM.PART_OF_POSSIBLE_WORLD.getIri(), possibleWorld.getId());
        return o;
    }

    /**
     * Create a Participant.
     *
     * @param name {@link String} name of the entity.
     * @return {@link Participant}
    */
    public Participant createParticipant(final String name) {
        final Participant o = 
            SpatioTemporalExtentServices.createParticipant(new IRI(USER_BASE, name).getIri());
        o.addStringValue(ENTITY_NAME.getIri(), name);
        objects.add(o);
        o.addValue(HQDM.PART_OF_POSSIBLE_WORLD.getIri(), possibleWorld.getId());
        return o;
    }

    /**
     * Create a Association.
     *
     * @param name {@link String} name of the entity.
     * @return {@link Association}
    */
    public Association createAssociation(final String name) {
        final Association o = 
            SpatioTemporalExtentServices.createAssociation(new IRI(USER_BASE, name).getIri());
        o.addStringValue(ENTITY_NAME.getIri(), name);
        objects.add(o);
        o.addValue(HQDM.PART_OF_POSSIBLE_WORLD.getIri(), possibleWorld.getId());
        return o;
    }

    /**
     * Add a MEMBER_OF_KIND.
     *
     * @param ste {@link SpatioTemporalExtent}
     * @param k {@link Class} which should be a Kind of something.
     * @return {@link PossibleWorldContext}
    */
    public PossibleWorldContext addMemberOfKind(final SpatioTemporalExtent ste, final Class k) {
        k.addValue(HQDM.MEMBER_OF_KIND.getIri(), ste.getId());
        return this;
    }

    /**
     * Add a NATURAL_ROLE.
     *
     * @param ste {@link SpatioTemporalExtent}
     * @param r {@link Role}
     * @return {@link PossibleWorldContext}
    */
    public PossibleWorldContext addNaturalRole(final SpatioTemporalExtent ste, final Role r) {
        r.addValue(HQDM.NATURAL_ROLE.getIri(), ste.getId());
        return this;
    }

    /**
     * Add BEGINNING {@link Event}.
     *
     * @param ste {@link SpatioTemporalExtent}
     * @param e {@link Event}
     * @return {@link PossibleWorldContext}
    */
    public PossibleWorldContext addBeginningEvent(final SpatioTemporalExtent ste, final Event e) {
        ste.addValue(HQDM.BEGINNING.getIri(), e.getId());
        return this;
    }

    /**
     * Add ENDING {@link Event}.
     *
     * @param ste {@link SpatioTemporalExtent}
     * @param e {@link Event}
     * @return {@link PossibleWorldContext}
    */
    public PossibleWorldContext addEndingEvent(final SpatioTemporalExtent ste, final Event e) {
        ste.addValue(HQDM.ENDING.getIri(), e.getId());
        return this;
    }

    /**
     * Add MEMBER_OF.
     *
     * @param ste {@link SpatioTemporalExtent}
     * @param c {@link Class}
     * @return {@link PossibleWorldContext}
    */
    public PossibleWorldContext addMemberOf(final SpatioTemporalExtent ste, final Class c) {
        ste.addValue(HQDM.MEMBER_OF.getIri(), c.getId());
        return this;
    }

    /**
     * Add TEMPORAL_PART_OF.
     *
     * @param whole {@link SpatioTemporalExtent}
     * @param part {@link SpatioTemporalExtent}
     * @return {@link PossibleWorldContext}
    */
    public PossibleWorldContext addTemporalPartOf(final SpatioTemporalExtent whole, final SpatioTemporalExtent part) {
        part.addValue(HQDM.TEMPORAL_PART_OF.getIri(), whole.getId());
        return this;
    }

    /**
     * Add INTENDED_ROLE.
     *
     * @param ste {@link SpatioTemporalExtent}
     * @param r {@link Role}
     * @return {@link PossibleWorldContext}
    */
    public PossibleWorldContext addIntendedRole(final SpatioTemporalExtent ste, final Role r) {
        ste.addValue(HQDM.INTENDED_ROLE.getIri(), r.getId());
        return this;
    }

    /**
     * Add CONSISTS_OF_PARTICIPANT.
     *
     * @param a {@link Association}
     * @param p {@link Participant}
     * @return {@link PossibleWorldContext}
    */
    public PossibleWorldContext addConsistsOfParticipant(final Association a, final Participant p) {
        a.addValue(HQDM.CONSISTS_OF_PARTICIPANT.getIri(), p.getId());
        return this;
    }

}
