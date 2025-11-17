package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.StateOfF135TurbineComponent;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An StateOfF135TurbineComponentImpl.
 */
public class StateOfF135TurbineComponentImpl extends HqdmObject implements StateOfF135TurbineComponent {

    /**
     * Constructor.
     *
     * @param id An IRI.
     */
    public StateOfF135TurbineComponentImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.STATE_OF_F135_TURBINE_COMPONENT_TYPE_IRI);
    }
}
