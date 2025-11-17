package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.StateOfF135Turbine;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An StateOfF135TurbineImpl.
 */
public class StateOfF135TurbineImpl extends HqdmObject implements StateOfF135Turbine {

    /**
     * Constructor.
     *
     * @param id An IRI.
     */
    public StateOfF135TurbineImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.STATE_OF_F135_TURBINE_TYPE_IRI);
    }
}
