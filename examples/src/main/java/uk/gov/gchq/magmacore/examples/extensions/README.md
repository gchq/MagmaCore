# F35 Model Extension Test

This project demonstrates how to use the **F-35 Model Extension** within the [MagmaCore HQDM](https://github.com/gchq/MagmaCore) framework to build a semantically rich model of an F-35 aircraft, its subsystems, and installed components.

## Overview

`F35ModelExtensionTest.java` is a runnable Java class that:

1. Creates an **in-memory RDF model** using Apache Jena.
2. Loads the **F-35 domain model** from the `F35ExtensionService`.
3. Programmatically constructs:
   - A `PossibleWorld` context.
   - An F-35 aircraft and its subsystems.
   - All major components (engine, turbine, vertical lift system, airframe, ejection seat).
4. Models installation events for each component.
5. Saves and exports the model in **Turtle (TTL)** format.

The code follows HQDM (High Quality Data Model) principles, where every object is created with clear temporal and spatial context.

## Key Concepts

- **Possible World**  
  A conceptual container for all entities created in this test.

- **Functional Objects**  
  Tangible systems and components, e.g. `F135Engine`, `F135Turbine`, `F35Airframe`.

- **Functional Systems**  
  Logical groupings of components, e.g. `F135EngineSystem` and the full `F35Aircraft`.

- **Installation Events**  
  HQDM events linking a physical component to a system at a specific time.

## Structure

The build process is divided into steps:

1. **Initialisation**
   - Sets a test IRI base.
   - Creates a MagmaCoreService with in-memory Jena.
   - Loads the F-35 domain ontology.

2. **Component Creation**
   - Instantiates each F-35 component using `F35ExtensionService.createEntity`.
   - Links each component to the `PossibleWorld`.

3. **System Assembly**
   - Groups components into higher-level systems (engine system, aircraft system).
   - Uses setter methods (e.g. `setEngine()`) to define relationships.

4. **Installation Modelling**
   - Creates `Installed...` entities.
   - Adds temporal properties (beginning events).
   - Links installed components to both their physical object and system component.

5. **Persistence & Export**
   - Saves all created entities to the RDF model.
   - Outputs the complete model in TTL to stdout.

## Running the Example

From the project root:

```bash
mvn compile exec:java \
    -Dexec.mainClass=uk.gov.gchq.magmacore.examples.extensions.F35ModelExtensionTest
````

The output will be an RDF Turtle representation of the F-35 model.

## Dependencies

* **Java 17+** (recommended)
* **Apache Maven**
* **Apache Jena**
* **MagmaCore HQDM**
  Including the F-35 extension module.

## How It Fits Into the Bigger Picture

This example is intended as:

* A **template** for building domain-specific HQDM models.
* A **reference** for working with MagmaCore extensions.
* A **validation tool** for the F-35 ontology.

It shows how to translate complex real-world assemblies (like aircraft) into a structured semantic model that supports temporal reasoning and system configuration tracking.
