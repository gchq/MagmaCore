package uk.gov.gchq.magmacore.examples.extensions;

import java.util.UUID;
import java.util.function.Function;

import uk.gov.gchq.magmacore.examples.extensions.ext.HqdmExtensionService;
import uk.gov.gchq.magmacore.examples.extensions.model.Child;
import uk.gov.gchq.magmacore.examples.extensions.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.model.Parent;
import uk.gov.gchq.magmacore.examples.extensions.model.ParentChildAssociation;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.Role;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IriBase;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.services.ClassServices;
import uk.gov.gchq.magmacore.service.MagmaCoreService;

/**
 * A Function to create and persist a ParentChildAssociation.
 */
public class ParentChildAssociationFunction implements Function<MagmaCoreService, MagmaCoreService> {

    // An IRI base for this example. Normally it would be elsewhere.
    private static final IriBase TEST_BASE = new IriBase("test", "http://example.com/test#");

    // The function parameters are created as fields.
    private final PossibleWorld possibleWorld;
    private final Parent parent;
    private final Child child;
    private final Event beginning;
    private final Event ending;

    // The Function result.
    private ParentChildAssociation result;

    /**
     * Constructor that initialises all of the parameters.
     *
     * @param possibleWorld {@link PossibleWorld}
     * @param parent {@link Parent}
     * @param child {@link Child}
     * @param beginning {@link Event}
     * @param ending {@link Event}
     */
    public ParentChildAssociationFunction(
            final PossibleWorld possibleWorld,
            final Parent parent, 
            final Child child,
            final Event beginning,
            final Event ending) {

        this.possibleWorld = possibleWorld;
        this.parent = parent;
        this.child = child;
        this.beginning = beginning;
        this.ending = ending;
    }

    /**
     * Function application - for use with {@link MagmaCoreService#runInWriteTransaction(Function)}.
     *
     * @param mcSvc {@link MagmaCoreService}
     * @return {@link MagmaCoreService}
     */
    @Override
    public MagmaCoreService apply(final MagmaCoreService mcSvc) {

        // The required entity is defined in an extension module so use the service
        // to create an instance.
        result = new HqdmExtensionService().createEntity(Constants.PARENT_CHILD_ASSOCIATION_TYPE_NAME, randomIri());

        // Add the parent and child to the association.
        result.setParentIri(parent.getId());
        result.setChildIri(child.getId());

        // Mark the parent and child as participant entities.
        parent.addValue(RDFS.RDF_TYPE, HQDM.PARTICIPANT);
        child.addValue(RDFS.RDF_TYPE, HQDM.PARTICIPANT);

        // Add the parent and child as participants in the association.
        parent.addValue(HQDM.PARTICIPANT_IN, result.getId());
        child.addValue(HQDM.PARTICIPANT_IN, result.getId());

        // Obtain the required parent and child roles.
        final Role parentRole = getOrCreateRole(mcSvc, Constants.PARENT_ROLE_NAME);
        final Role childRole = getOrCreateRole(mcSvc, Constants.CHILD_ROLE_NAME);
        
        // Apply the roles to the parent and child.
        parent.addValue(HQDM.MEMBER_OF_KIND, parentRole.getId());
        child.addValue(HQDM.MEMBER_OF_KIND, childRole.getId());

        // If a beginning was supplied, add it as a temporal bound on the entities.
        if (beginning != null) {
            result.addValue(HQDM.BEGINNING, beginning.getId());
            parent.addValue(HQDM.BEGINNING, beginning.getId());
            child.addValue(HQDM.BEGINNING, beginning.getId());

            // Persist the event as part of the possibleWorld.
            beginning.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId());
            mcSvc.create(beginning);
        }

        // If an ending was supplied, add it as a temporal bound on the entities.
        if (ending != null) {
            result.addValue(HQDM.ENDING, ending.getId());
            parent.addValue(HQDM.ENDING, ending.getId());
            child.addValue(HQDM.ENDING, ending.getId());

            // Persist the event as part of the possibleWorld.
            ending.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId());
            mcSvc.create(ending);
        }

        // Make sure the parent, child and association are part of the possibleWorld.
        result.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId());
        parent.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId());
        child.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId());

        // Persist everything - including the supplied parameters and roles in 
        // case they weren't already persisted.
        mcSvc.create(possibleWorld);
        mcSvc.create(result);
        mcSvc.create(parent);
        mcSvc.create(child);
        mcSvc.create(parentRole);
        mcSvc.create(childRole);

        return mcSvc;
    }

    /**
     * The Roles should exist as Reference Data entities, but for this example 
     * we create them if they don't exist. This may also be useful in production.
     *
     * @param mcSvc {@link MagmaCoreService}
     * @param roleName {@link String}
     * @return {@link Role}
     */
    private Role getOrCreateRole(final MagmaCoreService mcSvc, final String roleName) {
        try {
            return mcSvc.findByEntityName(roleName);
        } catch (final RuntimeException e) {
            final Role role = ClassServices.createRole(randomIri());
            role.addStringValue(HQDM.ENTITY_NAME, roleName);
            return role;
        }
    }

    /**
     * Create a random IRI.
     *
     * @return {@link IRI}
     */
    private static IRI randomIri() {
        return new IRI(TEST_BASE, UUID.randomUUID().toString());
    }

}
