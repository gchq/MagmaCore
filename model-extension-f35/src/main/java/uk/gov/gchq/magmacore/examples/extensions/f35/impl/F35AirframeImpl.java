package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35Airframe;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An implementation of the F35Airframe interface.
 */
public class F35AirframeImpl extends HqdmObject implements F35Airframe {
    /**
     * Constructor.
     *
     * @param id {@link IRI}
     */
    public F35AirframeImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.F35_AIRFRAME_TYPE_IRI);
    }
}
