
module MagmaCore.service.main {
    requires HQDM.model.main;
    requires HQDM.rdf.main;
    requires org.apache.jena.arq;
    requires org.apache.jena.core;
    requires org.apache.jena.fuseki.main;
    requires org.apache.jena.fuseki.core;
    requires org.apache.jena.rdfconnection;
    requires org.apache.jena.tdb2;
    requires com.fasterxml.jackson.annotation;

    exports uk.gov.gchq.magmacore.service;
    exports uk.gov.gchq.magmacore.exception;
}
