package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135VerticalLiftSystem;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An implementation of the F135VerticalLiftSystem interface.
 */
public class F135VerticalLiftSystemImpl extends HqdmObject implements F135VerticalLiftSystem {
    /**
     * Constructor.
     *
     * @param id {@link IRI}
     */
    public F135VerticalLiftSystemImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.F135_VERTICAL_LIFT_SYSTEM_TYPE_IRI);
    }
}
