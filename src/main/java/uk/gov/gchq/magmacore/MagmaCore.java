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

package uk.gov.gchq.magmacore;

import uk.gov.gchq.magmacore.demo.FusekiServiceDemo;
import uk.gov.gchq.magmacore.demo.JenaDatabaseDemo;
import uk.gov.gchq.magmacore.demo.RemoteSparqlDatabaseDemo;

/**
 * Application entry point.
 */
public final class MagmaCore {

    private MagmaCore() {}

    /**
     * Executes the selected database example.
     *
     * @param args Application arguments.
     */
    public static void main(final String[] args) {
        if (args.length == 0) {
            fuseki(true);
        } else {
          final String option = args[0];

          if (option.equals("fuseki")) {
              fuseki(false);
          } else if (option.equals("fuseki-populate")) {
              fuseki(true);
          } else if (option.equals("remote")) {
              remoteSparqlDatabaseDemo(false);
          } else if (option.equals("remote-populate")) {
            remoteSparqlDatabaseDemo(true);
          } else if (option.equals("jena")) {
            jenaDemo();
          } else {
            System.out.println("Unknown option: " + option);
          }
        }
    }

    /**
     * Executes the FusekiServiceDemo.
     *
     * @param populate true if the dataset should be populated with example data
     */
    public static void fuseki(final boolean populate) {
        new FusekiServiceDemo().run(populate);
    }

    /**
     * Executes the RemoteSparqlDatabaseDemo.
     *
     * @param populate true if the dataset should be populated with example data
     */
    public static void remoteSparqlDatabaseDemo(final boolean populate) {
        new RemoteSparqlDatabaseDemo("http://localhost:3330/tdb").run(populate);
    }

    /**
     * Executes the JenaDatabaseDemo.
     */
    public static void jenaDemo() {
      new JenaDatabaseDemo().run();
    }
}
