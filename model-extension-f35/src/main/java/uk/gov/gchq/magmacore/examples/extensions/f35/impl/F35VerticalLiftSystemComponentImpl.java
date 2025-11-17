package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35VerticalLiftSystemComponent;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An implementation of the F35VerticalLiftSystemComponent interface.
 */
public class F35VerticalLiftSystemComponentImpl extends HqdmObject implements F35VerticalLiftSystemComponent {
    /**
     * Constructor.
     *
     * @param id {@link IRI}
     */
    public F35VerticalLiftSystemComponentImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.F35_VERTICAL_LIFT_SYSTEM_COMPONENT_TYPE_IRI);
    }
}
