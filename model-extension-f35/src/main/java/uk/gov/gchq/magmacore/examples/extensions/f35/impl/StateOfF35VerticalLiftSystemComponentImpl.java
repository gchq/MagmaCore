package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.StateOfF35VerticalLiftSystemComponent;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An StateOfF35VerticalLiftSystemComponentImpl.
 */
public class StateOfF35VerticalLiftSystemComponentImpl extends HqdmObject implements StateOfF35VerticalLiftSystemComponent {

    /**
     * Constructor.
     *
     * @param id An IRI.
     */
    public StateOfF35VerticalLiftSystemComponentImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.STATE_OF_F35_VLS_COMPONENT_TYPE_IRI);
    }
}
