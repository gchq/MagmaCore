package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135VerticalLiftSystemComponent;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An implementation of the F35VerticalLiftSystemComponent interface.
 */
public class F135VerticalLiftSystemComponentImpl extends HqdmObject implements F135VerticalLiftSystemComponent {
    /**
     * Constructor.
     *
     * @param id {@link IRI}
     */
    public F135VerticalLiftSystemComponentImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.F135_VERTICAL_LIFT_SYSTEM_COMPONENT_TYPE_IRI);
    }
}
