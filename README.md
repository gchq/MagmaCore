# Magma Core

Magma Core is a lightweight set of Java classes to enable [HQDM data objects](https://github.com/gchq/HQDM) to be created and used as RDF Linked Data through [Apache Jena](https://jena.apache.org).

## Introduction

Representing things that are of common interest in data presents a consistency challenge. How can we use the apparent logic of computer based systems to store, process and query without suffering the losses due to the use of different models, structures and requirements on which interconnected systems are based? There is a lot to be gained from achieving consistency of representation, from addressing the core challenges to do with data quality and information management through to enabling emerging topics like Digital Twins & Artificial Intelligence. Inconsistent representations in data also undermines the ability to achieve improved information security, policy controls and ensuring that information is fit for its intended purpose.

This code release is a contribution to support the adoption of data models that have properties that enable consistency of representation, particularly in the face of emerging and changing requirements. The theory on which the model that this repo adopts is based upon the entity-relationship model published by Dr. Matthew West\* as the [High Quality Data Model Framework](http://www.informationjunction.co.uk/hqdm_framework/). At its core are some ontological commitments drawn upon the identity of actual things being based on distinct existence in space-time. A collection of arguments, including Set Theoretic commitments, provide a powerful model framework that can be extended to (almost) any application area of interest. This approach is often summarised as 4-dimensionalism and is being incorporated into the [UK's Information Management Framework]() (part of the National Digital Twin), as the Foundation Data Model - a backbone of distributed information systems that are sufficiently integrated to address system-wide data quality. One of the challenges in adopting such models is simultaneously addressing the analytic & technological challenges. This release is provided to help those interested in learning about and adopting such models by lowering the technical barriers.

\* Dr. West was not involved in the creation of this project. GCHQ are grateful for his open contributions to the field of ontology and data quality management.

## Getting Started

An introduction to Magma Core is provided in the [Magma Core Wiki](https://github.com/gchq/MagmaCore/wiki).

### Prerequisites

- [Java 15](https://openjdk.java.net/projects/jdk/15/) - Core language
- [Maven](https://maven.apache.org/) - Dependency management

### Inclusion in other projects

Magma Core can be incorporated into other maven projects using the following dependency:

```xml
<dependency>
  <groupId>uk.gov.gchq.magmacore</groupId>
  <artifactId>core</artifactId>
  <version>1.0</version>
</dependency>
```

_Magma Core is not currently hosted on Maven Central, so a local install of this repository will be required._

## Contributing

We welcome contributions to the project. Detailed information on our ways of working can be found [here](CONTRIBUTING.md).

In brief:

- Sign the [GCHQ Contributor Licence Agreement](https://cla-assistant.io/gchq/MagmaCore).
- Push your changes to a new branch.
- Submit a pull request.

## License

Magma Core is released under the [Apache 2.0 Licence](https://www.apache.org/licenses/LICENSE-2.0) and is covered by [Crown Copyright](https://www.nationalarchives.gov.uk/information-management/re-using-public-sector-information/copyright-and-re-use/crown-copyright/).
