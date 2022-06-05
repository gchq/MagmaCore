package uk.gov.gchq.magmacore.demo;

import static uk.gov.gchq.hqdm.iri.HQDM.ENTITY_NAME;
import static uk.gov.gchq.magmacore.util.DataObjectUtils.REF_BASE;
import static uk.gov.gchq.magmacore.util.DataObjectUtils.uid;

import java.util.ArrayList;
import java.util.List;

import uk.gov.gchq.hqdm.iri.HQDM;
import uk.gov.gchq.hqdm.iri.IRI;
import uk.gov.gchq.hqdm.model.Class;
import uk.gov.gchq.hqdm.model.ClassOfStateOfFunctionalSystem;
import uk.gov.gchq.hqdm.model.ClassOfStateOfPerson;
import uk.gov.gchq.hqdm.model.KindOfAssociation;
import uk.gov.gchq.hqdm.model.KindOfBiologicalSystemComponent;
import uk.gov.gchq.hqdm.model.KindOfFunctionalSystem;
import uk.gov.gchq.hqdm.model.KindOfFunctionalSystemComponent;
import uk.gov.gchq.hqdm.model.KindOfPerson;
import uk.gov.gchq.hqdm.model.KindOfSystem;
import uk.gov.gchq.hqdm.model.KindOfSystemComponent;
import uk.gov.gchq.hqdm.model.Role;
import uk.gov.gchq.hqdm.model.Thing;
import uk.gov.gchq.hqdm.services.ClassServices;

/**
 * Class for building HQDM models.
 *
 * */
public class ExampleModelBuilder {

    private List<Thing> objects;

    /**
     * Constructor.
     *
     * */
    public ExampleModelBuilder() {
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

}
