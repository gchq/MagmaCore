package uk.gov.gchq.magmacore.generators;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.jena.query.ReadWrite;

import static uk.gov.gchq.magmacore.util.DataObjectUtils.uid;

import uk.gov.gchq.magmacore.database.MagmaCoreRemoteSparqlDatabase;
import uk.gov.gchq.hqdm.iri.HQDM;
import uk.gov.gchq.hqdm.iri.IRI;
import uk.gov.gchq.hqdm.iri.IriBase;
import uk.gov.gchq.hqdm.model.Thing;
import uk.gov.gchq.hqdm.pojo.HqdmObject;

public class MultipleInheritEntrypoint {

    public static Class<?> newTypeSpecificationAndGenerate(
        List<String> superTypeList,
        MagmaCoreRemoteSparqlDatabase hqdmFusekiDb,
        Thing dataSpecForNewType
    ){
        StringBuilder newObjNameBuilder = new StringBuilder();
        List<IRI> superTypeIRIList = new ArrayList<IRI>();
        List<Thing> typeToTest = new ArrayList<Thing>();
        newObjNameBuilder.setLength(0);
        superTypeIRIList.clear();
        typeToTest.clear();

        Collections.sort(superTypeList);
        for (String superType : superTypeList) {
            newObjNameBuilder.append(superType);
            newObjNameBuilder.append("and");
            hqdmFusekiDb.begin(ReadWrite.READ);
            final List<Thing> queryResults = hqdmFusekiDb.findByPredicateIriAndStringValue(HQDM.ENTITY_NAME, superType);
            hqdmFusekiDb.abort();

            IRI superTypeIRI = null;
            for (Thing thing : queryResults) {
                Map<IRI,Set<Object>> thingPredicates = thing.getPredicates();
                String possibleName = thingPredicates.get(HQDM.ENTITY_NAME).iterator().next().toString();
                if(possibleName.equals(superType)){
                    superTypeIRI = thing.getIri();
                    typeToTest.add(thing);
                }
            }
            superTypeIRIList.add(superTypeIRI);
            dataSpecForNewType.addValue(HQDM.HAS_SUPERTYPE, superTypeIRI);
        }
       
        HqdmObject dataSpecForNewTypeAsHqdmObject = (HqdmObject) dataSpecForNewType;

        if(newObjNameBuilder.toString().endsWith("and")){
            newObjNameBuilder.delete(newObjNameBuilder.length()-3, newObjNameBuilder.length());
        }

        Class<?> newHqdmClass = 
            MultipleInheritGenerateFromThing.generateNewTypeClasses(
            dataSpecForNewTypeAsHqdmObject,
            hqdmFusekiDb);
        
        
        HqdmObject newTypeObj = instanceOfSuppliedHqdmClass(newHqdmClass, newObjNameBuilder.toString().toLowerCase(), HQDM.HQDM);
        
        boolean descendantOfClass = inheritsFromClass( typeToTest.get(0));
        superTypeIRIList.forEach( iri -> {  
            if( descendantOfClass ){
                newTypeObj.addValue(HQDM.HAS_SUPERCLASS, iri);
            } else {
                newTypeObj.addValue(HQDM.HAS_SUPERTYPE, iri);
            }
        });

        hqdmFusekiDb.begin(ReadWrite.WRITE);
        hqdmFusekiDb.create(newTypeObj);
        hqdmFusekiDb.commit();
        hqdmFusekiDb.abort();

        return newHqdmClass;
    }

    public static HqdmObject instanceOfSuppliedHqdmClass( Class<?> newHqdmClass, String newClassName, IriBase suppliedIriBase){
        Object newObj = null;

        try {
            newObj = newHqdmClass
            .getDeclaredConstructor( IRI.class )
            .newInstance( new IRI( suppliedIriBase, uid()) );
        } catch (NoSuchMethodException e){
                e.printStackTrace();
        } catch (SecurityException e){
                e.printStackTrace();
        } catch (InstantiationException e){
                e.printStackTrace();
        } catch (IllegalAccessException e) {
                e.printStackTrace();
        } catch (IllegalArgumentException e) {
                e.printStackTrace();
        } catch (InvocationTargetException e) {
                e.printStackTrace();
        }
        
        // Add name and commit to Jena database
        HqdmObject newTypeObj = (HqdmObject) newObj;
        
        if( !newClassName.equals("") && newTypeObj != null){
            newTypeObj.addStringValue(HQDM.ENTITY_NAME, newClassName);
        }

        return newTypeObj;
    }

    public static boolean inheritsFromClass(
        Thing testType
    ){
        // Test whether the type has a has_superclass predicate
        Map<IRI,Set<Object>> thingPredicates = testType.getPredicates();
        Set<Object> hasSuperclassSet = thingPredicates.get(HQDM.HAS_SUPERCLASS);
        if(hasSuperclassSet != null){
            return !hasSuperclassSet.isEmpty();
        }
        return false;
    }
}
