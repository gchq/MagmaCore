package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.StateOfF35TurbineComponent;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An StateOfF35TurbineComponentImpl.
 */
public class StateOfF35TurbineComponentImpl extends HqdmObject implements StateOfF35TurbineComponent {

    /**
     * Constructor.
     *
     * @param id An IRI.
     */
    public StateOfF35TurbineComponentImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.STATE_OF_F35_TURBINE_COMPONENT_TYPE_IRI);
    }
}
