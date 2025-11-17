package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135TurbineComponent;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An implementation of the F35TurbineComponent interface.
 */
public class F135TurbineComponentImpl extends HqdmObject implements F135TurbineComponent {
    /**
     * Constructor.
     *
     * @param id {@link IRI}
     */
    public F135TurbineComponentImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.F135_TURBINE_COMPONENT_TYPE_IRI);
    }
}
