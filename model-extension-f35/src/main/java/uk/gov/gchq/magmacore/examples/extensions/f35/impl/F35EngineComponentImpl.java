package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35EngineComponent;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An implementation of the F35EngineComponent interface.
 */
public class F35EngineComponentImpl extends HqdmObject implements F35EngineComponent {
    /**
     * Constructor.
     *
     * @param id {@link IRI}
     */
    public F35EngineComponentImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.F35_ENGINE_COMPONENT_TYPE_IRI);
    }
}
