# HQDM Model Extension Example

This project builds a JAR file with a small extension to the main HQDM model provided by MagmaCore. It provides
an SPI Service that can be detected by MagmaCore as a model extenstion, and provides the `UkLimitedCompany` entity type
as a subtype of `Organization`, and the `UkSoftwareDevelopmentCompany` as a subtype of `UkLimitedCompany`.
This allows MagmaCore to be extended without changing the core library.

# How to Use

This section shows how to use the model extension in an application with MagmaCore.

1. Build this project, or your own model extension project, to create a JAR file.
2. Ensure the JAR file is on the class path for your application.
3. Follow the example below for how to use the extension entity types with MagmaCore.
4. MagmaCore will find the extension and use it as if the classes were part of HQDM.

The example creates and persists an entity from the extension JAR, then reads it from the database and compares it to the original.

```java
/**
 * A set of tests to try out the MagmaCore extension mechanism.
 */
public class MagmaCoreExtensionTests {

    // Declare an IRI base for the data to be created.
    private static final IriBase TEST_BASE = new IriBase("test", "http://example.com/test#");

    @Test
    public void test() {

        // Create a MagmaCoreService with an in-memory Apache Jena database.
        final var mcs = MagmaCoreServiceFactory.createWithJenaDatabase();

        // The entity will be a part of a dummy possible_world, we just use the
        // IRI rather than creating the possible_world for this example.
        final var possibleWorldIri = new IRI(TEST_BASE, UUID.randomUUID().toString());

        // Create an IRI for the entity we want to create, then create the entity.
        final var entityIri = new IRI(TEST_BASE, UUID.randomUUID().toString());
        final Thing entity = new UkLimitedCompanyImpl(entityIri);

        // Set the RDF_TYPE and add the entity as a `part_of_possible_world`.
        entity.addValue(RDFS.RDF_TYPE, Constants.UK_LIMITED_COMPANY_IRI);
        entity.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);

        // Persist the entity in the database.
        mcs.runInWriteTransaction(svc -> {
            svc.create(entity);
            return svc;
        });

        // Read the entity back and assert that it matches the original.
        mcs.runInReadTransaction(svc -> {
            final var restoredEntity = svc.get(entityIri);

            assertNotNull(restoredEntity);
            assertTrue(restoredEntity instanceof UkLimitedCompany);
            assertEquals(entity, restoredEntity);
            return svc;
        });
    }
}
```
