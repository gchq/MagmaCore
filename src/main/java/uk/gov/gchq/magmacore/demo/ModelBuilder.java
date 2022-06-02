package uk.gov.gchq.magmacore.demo;

import static uk.gov.gchq.hqdm.iri.HQDM.ENTITY_NAME;
import static uk.gov.gchq.magmacore.util.DataObjectUtils.REF_BASE;
import static uk.gov.gchq.magmacore.util.DataObjectUtils.USER_BASE;
import static uk.gov.gchq.magmacore.util.DataObjectUtils.uid;

import java.util.ArrayList;
import java.util.List;

import uk.gov.gchq.hqdm.iri.HQDM;
import uk.gov.gchq.hqdm.iri.IRI;
import uk.gov.gchq.hqdm.model.Association;
import uk.gov.gchq.hqdm.model.Class;
import uk.gov.gchq.hqdm.model.ClassOfStateOfFunctionalSystem;
import uk.gov.gchq.hqdm.model.ClassOfStateOfPerson;
import uk.gov.gchq.hqdm.model.Event;
import uk.gov.gchq.hqdm.model.FunctionalSystem;
import uk.gov.gchq.hqdm.model.KindOfAssociation;
import uk.gov.gchq.hqdm.model.KindOfBiologicalSystemComponent;
import uk.gov.gchq.hqdm.model.KindOfFunctionalSystem;
import uk.gov.gchq.hqdm.model.KindOfFunctionalSystemComponent;
import uk.gov.gchq.hqdm.model.KindOfPerson;
import uk.gov.gchq.hqdm.model.KindOfSystem;
import uk.gov.gchq.hqdm.model.KindOfSystemComponent;
import uk.gov.gchq.hqdm.model.Participant;
import uk.gov.gchq.hqdm.model.Person;
import uk.gov.gchq.hqdm.model.PointInTime;
import uk.gov.gchq.hqdm.model.PossibleWorld;
import uk.gov.gchq.hqdm.model.Role;
import uk.gov.gchq.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.hqdm.model.StateOfFunctionalSystem;
import uk.gov.gchq.hqdm.model.StateOfPerson;
import uk.gov.gchq.hqdm.model.Thing;
import uk.gov.gchq.hqdm.services.ClassServices;
import uk.gov.gchq.hqdm.services.SpatioTemporalExtentServices;

/**
 * Class for building HQDM models.
 *
 * */
public class ModelBuilder {

    private List<Thing> objects;

    public ModelBuilder() {
        objects = new ArrayList<>();
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
     * Create a new Class.
     *
     * @param name {@link String}
     * @return a {@link Class}
     * */
    public Class createClass(final String name) {
        final Class c = ClassServices.createClass(new IRI(REF_BASE, uid()).getIri());
        c.addStringValue(ENTITY_NAME.getIri(), name);
        objects.add(c);
        return c;
    }

    /**
     * Create a new ClassOfStateOfFunctionalSystem.
     *
     * @param name {@link String}
     * @return a {@link ClassOfStateOfFunctionalSystem}
     * */
    public ClassOfStateOfFunctionalSystem createClassOfStateOfFunctionalSystem(final String name) {
        final ClassOfStateOfFunctionalSystem c = 
            ClassServices.createClassOfStateOfFunctionalSystem(new IRI(REF_BASE, uid()).getIri());
        c.addStringValue(ENTITY_NAME.getIri(), name);
        objects.add(c);
        return c;
    }

    /**
     * Create a new ClassOfStateOfPerson.
     *
     * @param name {@link String}
     * @return a {@link ClassOfStateOfPerson}
     * */
    public ClassOfStateOfPerson createClassOfStateOfPerson(final String name) {
        final ClassOfStateOfPerson c = ClassServices.createClassOfStateOfPerson(new IRI(REF_BASE, uid()).getIri());
        c.addStringValue(ENTITY_NAME.getIri(), name);
        objects.add(c);
        return c;
    }

    /**
     * Create a new KindOfAssociation.
     *
     * @param name {@link String}
     * @return a {@link KindOfAssociation}
     * */
    public KindOfAssociation createKindOfAssociation(final String name) {
        final KindOfAssociation c = ClassServices.createKindOfAssociation(new IRI(REF_BASE, uid()).getIri());
        c.addStringValue(ENTITY_NAME.getIri(), name);
        objects.add(c);
        return c;
    }

    /**
     * Create a new Role.
     *
     * @param name {@link String}
     * @return a {@link Role}
     * */
    public Role createRole(final String name) {
        final Role c = ClassServices.createRole(new IRI(REF_BASE, uid()).getIri());
        c.addStringValue(ENTITY_NAME.getIri(), name);
        objects.add(c);
        return c;
    }

    /**
     * Create a new {@link KindOfBiologicalSystemComponent}.
     *
     * @param name the name {@link String}
     * @return {@link KindOfBiologicalSystemComponent}
    */
    public KindOfBiologicalSystemComponent createKindOfBiologicalSystemComponent(final String name) {
        final KindOfBiologicalSystemComponent c = 
                ClassServices.createKindOfBiologicalSystemComponent(new IRI(REF_BASE, uid()).getIri());
        c.addStringValue(ENTITY_NAME.getIri(), name);
        objects.add(c);
        return c;
    }

    /**
     * Create a new {@link KindOfFunctionalSystem}.
     *
     * @param name the name {@link String}
     * @return {@link KindOfFunctionalSystem}
    */
    public KindOfFunctionalSystem createKindOfFunctionalSystem(final String name) {
        final KindOfFunctionalSystem c = ClassServices.createKindOfFunctionalSystem(new IRI(REF_BASE, uid()).getIri());
        c.addStringValue(ENTITY_NAME.getIri(), name);
        objects.add(c);
        return c;
    }

    /**
     * Create a new {@link KindOfFunctionalSystemComponent}.
     *
     * @param name the name {@link String}
     * @return {@link KindOfFunctionalSystemComponent}
    */
    public KindOfFunctionalSystemComponent createKindOfFunctionalSystemComponent(final String name) {
        final KindOfFunctionalSystemComponent c = 
            ClassServices.createKindOfFunctionalSystemComponent(new IRI(REF_BASE, uid()).getIri());
        c.addStringValue(ENTITY_NAME.getIri(), name);
        objects.add(c);
        return c;
    }

    /**
     * Create a new {@link KindOfPerson}.
     *
     * @param name the name {@link String}
     * @return {@link KindOfPerson}
    */
    public KindOfPerson createKindOfPerson(final String name) {
        final KindOfPerson c = ClassServices.createKindOfPerson(new IRI(REF_BASE, uid()).getIri());
        c.addStringValue(ENTITY_NAME.getIri(), name);
        objects.add(c);
        return c;
    }

    /**
     * Create a subclass relationship.
     *
     * @param superclass {@link Class}
     * @param subclass {@link Class}
    */
    public void addSubclass(final Class superclass, final Class subclass) {
        subclass.addValue(HQDM.HAS_SUPERCLASS.getIri(), superclass.getId());
    }

    /**
     * Add a {@link Class} as a member__of another {@link Class}.
     *
     * @param set the {@link Class} that the member is a member__of.
     * @param member the {@link Class} that is the member of the set.
    */
    public void addClassMember(final Class set, final Class member) {
        member.addValue(HQDM.MEMBER__OF.getIri(), set.getId());
    }

    /**
     * Add a HAS_COMPONENT_BY_CLASS relationship.
     *
     * @param system a {@link KindOfSystem}
     * @param component a {@link KindOfSystemComponent}
    */
    public void addHasComponentByClass(final KindOfSystem system, 
                                       final KindOfSystemComponent component) {
        system.addValue(HQDM.HAS_COMPONENT_BY_CLASS.getIri(), component.getId());
    }

    /**
     * Add a CONSISTS_OF_BY_CLASS relationship.
     *
     * @param kindOfAssociation {@link KindOfAssociation}
     * @param role {@link Role}
    */
    public void addConsistsOfByClass(final KindOfAssociation kindOfAssociation, 
                                       final Role role) {
        kindOfAssociation.addValue(HQDM.CONSISTS_OF_BY_CLASS.getIri(), role.getId());
    }

    /**
     * Create a new {@link PossibleWorld}.
     *
     * @param name {@link String}
     * @return {@link PossibleWorld}
    */
    public PossibleWorld createPossibleWorld(final String name) {
        final PossibleWorld o = SpatioTemporalExtentServices.createPossibleWorld(new IRI(USER_BASE, uid()).getIri());
        o.addStringValue(ENTITY_NAME.getIri(), name);
        objects.add(o);
        return o;
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
        return o;
    }

    /**
     * Add a {@link SpatioTemporalExtent} to a {@link PossibleWorld}.
     *
     * @param ste {@link SpatioTemporalExtent}
     * @param pw {@link PossibleWorld}
    */
    public void addToPossibleWorld(final SpatioTemporalExtent ste, final PossibleWorld pw) {
        ste.addValue(HQDM.PART_OF_POSSIBLE_WORLD.getIri(), pw.getId());
    }

    /**
     * Add a MEMBER_OF_KIND.
     *
     * @param ste {@link SpatioTemporalExtent}
     * @param k {@link Class} which should be a Kind of something.
    */
    public void addMemberOfKind(final SpatioTemporalExtent ste, final Class k) {
        k.addValue(HQDM.MEMBER_OF_KIND.getIri(), ste.getId());
    }

    /**
     * Add a NATURAL_ROLE.
     *
     * @param ste {@link SpatioTemporalExtent}
     * @param r {@link Role}
    */
    public void addNaturalRole(final SpatioTemporalExtent ste, final Role r) {
        r.addValue(HQDM.NATURAL_ROLE.getIri(), ste.getId());
    }

    /**
     * Add BEGINNING {@link Event}.
     *
     * @param ste {@link SpatioTemporalExtent}
     * @param e {@link Event}
    */
    public void addBeginningEvent(final SpatioTemporalExtent ste, final Event e) {
        ste.addValue(HQDM.BEGINNING.getIri(), e.getId());
    }

    /**
     * Add ENDING {@link Event}.
     *
     * @param ste {@link SpatioTemporalExtent}
     * @param e {@link Event}
    */
    public void addEndingEvent(final SpatioTemporalExtent ste, final Event e) {
        ste.addValue(HQDM.ENDING.getIri(), e.getId());
    }

    /**
     * Add MEMBER_OF.
     *
     * @param ste {@link SpatioTemporalExtent}
     * @param c {@link Class}
    */
    public void addMemberOf(final SpatioTemporalExtent ste, final Class c) {
        ste.addValue(HQDM.MEMBER_OF.getIri(), c.getId());
    }

    /**
     * Add TEMPORAL_PART_OF.
     *
     * @param whole {@link SpatioTemporalExtent}
     * @param part {@link SpatioTemporalExtent}
    */
    public void addTemporalPartOf(final SpatioTemporalExtent whole, final SpatioTemporalExtent part) {
        part.addValue(HQDM.TEMPORAL_PART_OF.getIri(), whole.getId());
    }

    /**
     * Add INTENDED_ROLE.
     *
     * @param ste {@link SpatioTemporalExtent}
     * @param r {@link Role}
    */
    public void addIntendedRole(final SpatioTemporalExtent ste, final Role r) {
        ste.addValue(HQDM.INTENDED_ROLE.getIri(), r.getId());
    }

    /**
     * Add CONSISTS_OF_PARTICIPANT.
     *
     * @param a {@link Association}
     * @param p {@link Participant}
    */
    public void addConsistsOfParticipant(final Association a, final Participant p) {
        a.addValue(HQDM.CONSISTS_OF_PARTICIPANT.getIri(), p.getId());
    }

}
