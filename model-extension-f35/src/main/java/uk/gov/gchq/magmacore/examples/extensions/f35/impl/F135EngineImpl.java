package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135Engine;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An implementation of the F35Engine interface.
 */
public class F135EngineImpl extends HqdmObject implements F135Engine {
    /**
     * Constructor.
     *
     * @param id {@link IRI}
     */
    public F135EngineImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.F135_ENGINE_TYPE_IRI);
    }
}
