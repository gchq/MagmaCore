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

package uk.gov.gchq.magmacore.hqdm.model.impl;

import uk.gov.gchq.magmacore.hqdm.model.ClassOfPhysicalProperty;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;

/**
 * An implementation of ClassOfPhysicalProperty.
 */
public class ClassOfPhysicalPropertyImpl extends HqdmObject implements ClassOfPhysicalProperty {
    /**
     * Constructs a new ClassOfPhysicalProperty.
     *
     * @param id String of the ClassOfPhysicalProperty.
     */
    public ClassOfPhysicalPropertyImpl(final IRI id) {
        super(id);
    }
}
