package uk.gov.gchq.magmacore.examples.extensions.model;

import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;

/**
 * An HQDM Extension class to add a new entity type to MagmaCore.
 */
public class UkLimitedCompanyImpl extends HqdmObject implements UkLimitedCompany {

    public UkLimitedCompanyImpl(final IRI id) {
        super(id);
    }

}

