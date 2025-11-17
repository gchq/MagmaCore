package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35EjectionSeatComponent;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An implementation of the F35EjectionSeatComponent interface.
 */
public class F35EjectionSeatComponentImpl extends HqdmObject implements F35EjectionSeatComponent {
    /**
     * Constructor.
     *
     * @param id {@link IRI}
     */
    public F35EjectionSeatComponentImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.F35_EJECTION_SEAT_COMPONENT_TYPE_IRI);
    }
}
