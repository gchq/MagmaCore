/*
 * Copyright 2021 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

/**
 * Magma Core is a lightweight set of Java classes to enable HQDM data objects to be created and
 * used as RDF Linked Data through Apache Jena.
 */
module uk.gov.gchq.magmacore {
    requires org.apache.jena.arq;
    requires org.apache.jena.base;
    requires org.apache.jena.core;
    requires org.apache.jena.dboe.base;
    requires org.apache.jena.rdfconnection;
    requires org.apache.jena.tdb2;
    requires com.fasterxml.jackson.annotation;
    requires java.net.http;

    requires transitive uk.gov.gchq.magmacore.hqdm;

    exports uk.gov.gchq.magmacore.service;
    exports uk.gov.gchq.magmacore.service.dto;
    exports uk.gov.gchq.magmacore.service.transformation;
    exports uk.gov.gchq.magmacore.exception;
    exports uk.gov.gchq.magmacore.util;
}
