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
 * Classes demonstrating implementation and use of Magma Core databases, SPARQL queries and Fuseki
 * server.
 */
module uk.gov.gchq.magmacore.examples {
    requires uk.gov.gchq.magmacore.hqdm;
    requires uk.gov.gchq.magmacore;
    requires uk.gov.gchq.magmacore.examples.extensions;
    exports uk.gov.gchq.magmacore.examples.service;

    uses uk.gov.gchq.magmacore.hqdm.extensions.ExtensionServiceProvider;
}
