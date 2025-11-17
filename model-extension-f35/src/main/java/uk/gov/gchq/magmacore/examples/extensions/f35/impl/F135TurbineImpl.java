package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135Turbine;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An implementation of the F135Turbine interface.
 */
public class F135TurbineImpl extends HqdmObject implements F135Turbine {
    /**
     * Constructor.
     *
     * @param id {@link IRI}
     */
    public F135TurbineImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.F135_TURBINE_TYPE_IRI);
    }
}
