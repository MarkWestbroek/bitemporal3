package nl.kvk.np.semantics;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

import nl.kvk.np.Constants;
import nl.kvk.np.datalayer.Mutatietype;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.swing.text.html.parser.Entity;

public class AnalyzeSemantics {

    Map<Map.Entry<String, String>, List<String>> relations = new HashMap<>();
    Set<String> classNames = new HashSet<>();


    public void findAnnotation() {


        List<ClassLoader> classLoaderList = new LinkedList();

        String classnamePathPrefix = "nl.kvk.np";
        Reflections reflections = new Reflections(new ConfigurationBuilder().
                setScanners(new SubTypesScanner(false), new ResourcesScanner()).
                setUrls(ClasspathHelper.forClassLoader(classLoaderList.toArray(new ClassLoader[0]))).
                filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(classnamePathPrefix))));

        Set<Class<?>> classses = reflections.getSubTypesOf(Object.class);

        for(Class<?> clazz :classses) {
            // analyzeAnnotationClass(clazz);
            analyzeAnnotationClass_CompileConstant(clazz);
        }

        Set<Class<? extends Enum>> classsesEnums = reflections.getSubTypesOf(Enum.class);

        for(Class<?> clazz :classsesEnums) {
            // analyzeAnnotationClass(clazz);
            analyzeAnnotationClass_CompileConstant(clazz);
        }
        MapAnalysisToGraph mapAnalysisToGraph = new MapAnalysisToGraph();
        mapAnalysisToGraph.addToGraphStructure(relations, classNames);
    }

    public void analyzeAnnotationClass(Class clazz) {
        Annotation annotation = clazz.getAnnotation(OperatieOpEntiteit.class);
        Annotation annotation1 = clazz.getAnnotation(OperatieOpEntiteitClass.class);
        if (annotation1 != null) {
            OperatieOpEntiteitClass ann = (OperatieOpEntiteitClass) clazz.getAnnotation(OperatieOpEntiteitClass.class);
            int i = 0;
            Class<?>[] types2 = ann.Betreft();
            String method1 = types2[0].getSimpleName();
            String method2 = types2[1].getSimpleName();

            Class<? extends Enum>[] types3= ((OperatieOpEntiteitClass) clazz.getAnnotation(OperatieOpEntiteitClass.class)).Betreft_E();
            String enumClassName = types3[0].getSimpleName();
            Class<?>[] types = annotation.getClass().getClasses();
            annotation1.toString();
            String aa = annotation1.getClass().getName().toString();
        }
        boolean entityClass = clazz.getAnnotation(javax.persistence.Entity.class) != null;
        if (clazz.getSimpleName().equals(Mutatietype.class.getSimpleName())) {
            int j = 0;
        }
        try {
            if ((annotation != null) && (!annotation.getClass().getMethod("BeschrijftAanvangVan").getName().equals(Constants.UNASSIGNED))) {
                String linkLabel = annotation.getClass().getMethod("BeschrijftAanvangVan").getName();
                String[] paramaterMappings = annotation.toString().split(","); // Bevat=FormeelObject
                for (String labels : paramaterMappings) {
                    if (labels.contains("(")) {
                        labels = labels.substring(labels.indexOf("(") + 1, labels.length());
                    }
                    if (labels.trim().startsWith("BeschrijftAanvangVan")) {
                        String[] splitLabels = labels.split("=");
                        if (splitLabels[1].endsWith(")")) {
                            splitLabels[1] = splitLabels[1].substring(0, splitLabels[1].length() - 1);
                        }
                        if (! splitLabels[1].equals(Constants.UNASSIGNED)) {
                            System.out.println("Java-klasse " + clazz.getName() + " BeschrijftAanvangVan[" + splitLabels[1] + "]");
                            if (splitLabels[1].contains(";")) {
                                String[] labs = splitLabels[1].split(";");
                                for (String l : labs) {
                                    Map.Entry<String, String> mapElement = new AbstractMap.SimpleEntry(clazz.getSimpleName()+eA(entityClass), "BeschrijftAanvangVan");
                                    if (! relations.containsKey(mapElement)) {
                                        relations.put(mapElement, new ArrayList<>());
                                    }
                                    relations.get(mapElement).add(l);
                                    classNames.add(l);
                                }
                            } else {
                                Map.Entry<String, String> mapElement = new AbstractMap.SimpleEntry(clazz.getSimpleName()+eA(entityClass), "BeschrijftAanvangVan");
                                if (! relations.containsKey(mapElement)) {
                                    relations.put(mapElement, new ArrayList<>());
                                }
                                relations.get(mapElement).add(splitLabels[1]);
                                classNames.add(splitLabels[1]);
                            }
                        }
                    }
                }
            }

            if ((annotation != null) && (!annotation.getClass().getMethod("BeschrijftBeeindigingVan").getName().equals(Constants.UNASSIGNED))) {
                String linkLabel = annotation.getClass().getMethod("BeschrijftBeeindigingVan").getName();
                String[] paramaterMappings = annotation.toString().split(","); // Bevat=FormeelObject
                for (String labels : paramaterMappings) {
                    if (labels.trim().startsWith("BeschrijftBeeindigingVan")) {
                        String[] splitLabels = labels.split("=");
                        if (splitLabels[1].endsWith(")")) {
                            splitLabels[1] = splitLabels[1].substring(0, splitLabels[1].length() - 1);
                        }
                        if (! splitLabels[1].equals(Constants.UNASSIGNED)) {
                            System.out.println("Java-klasse " + clazz.getName() + " BeschrijftBeeindigingVan[" + splitLabels[1] + "]");
                            if (splitLabels[1].contains(";")) {
                                String[] labs = splitLabels[1].split(";");
                                for (String l : labs) {
                                    Map.Entry<String, String> mapElement = new AbstractMap.SimpleEntry(clazz.getSimpleName()+eA(entityClass), "BeschrijftBeeindigingVan");
                                    if (! relations.containsKey(mapElement)) {
                                        relations.put(mapElement, new ArrayList<>());
                                    }
                                    relations.get(mapElement).add(l);
                                    classNames.add(l);
                                }
                            } else {
                                Map.Entry<String, String> mapElement = new AbstractMap.SimpleEntry(clazz.getSimpleName()+eA(entityClass), "BeschrijftBeeindigingVan");
                                if (! relations.containsKey(mapElement)) {
                                    relations.put(mapElement, new ArrayList<>());
                                }
                                relations.get(mapElement).add(splitLabels[1]);
                                classNames.add(splitLabels[1]);
                            }
                        }
                    }
                }
            }

            if ((annotation != null) && (!annotation.getClass().getMethod("Bevat").getName().equals(Constants.UNASSIGNED))) {
                String linkLabel = annotation.getClass().getMethod("Bevat").getName();
                String[] paramaterMappings = annotation.toString().split(","); // Bevat=FormeelObject
                for (String labels : paramaterMappings) {
                    if (labels.trim().startsWith("Bevat")) {
                        String[] splitLabels = labels.split("=");
                        if (splitLabels[1].endsWith(")")) {
                            splitLabels[1] = splitLabels[1].substring(0, splitLabels[1].length() - 1);
                        }
                        if (! splitLabels[1].equals(Constants.UNASSIGNED)) {
                            System.out.println("Java-klasse " + clazz.getName() + " Bevat[" + splitLabels[1] + "]");
                            if (splitLabels[1].contains(";")) {
                                String[] labs = splitLabels[1].split(";");
                                for (String l : labs) {
                                    Map.Entry<String, String> mapElement = new AbstractMap.SimpleEntry(clazz.getSimpleName()+eA(entityClass), "Bevat");
                                    if (! relations.containsKey(mapElement)) {
                                        relations.put(mapElement, new ArrayList<>());
                                    }
                                    relations.get(mapElement).add(l);
                                    classNames.add(l);
                                }
                            } else {
                                Map.Entry<String, String> mapElement = new AbstractMap.SimpleEntry(clazz.getSimpleName()+eA(entityClass), "Bevat");
                                if (! relations.containsKey(mapElement)) {
                                    relations.put(mapElement, new ArrayList<>());
                                }
                                relations.get(mapElement).add(splitLabels[1]);
                                classNames.add(splitLabels[1]);
                            }
                        }
                    }
                }
                int j = 0;
            }

            if ((annotation != null) && (!annotation.getClass().getMethod("TypeWijziging").getName().equals(Constants.UNASSIGNED))) {
                String linkLabel = annotation.getClass().getMethod("TypeWijziging").getName();
                String[] paramaterMappings = annotation.toString().split(","); // Bevat=FormeelObject
                for (String labels : paramaterMappings) {
                    if (labels.trim().startsWith("TypeWijziging")) {
                        String[] splitLabels = labels.split("=");
                        if (splitLabels[1].endsWith(")")) {
                            splitLabels[1] = splitLabels[1].substring(0, splitLabels[1].length() - 1);
                        }
                        if (! splitLabels[1].equals(Constants.UNASSIGNED)) {
                            System.out.println("Java-klasse " + clazz.getName() + " TypeWijziging[" + splitLabels[1] + "]");
                            if (splitLabels[1].contains(";")) {
                                String[] labs = splitLabels[1].split(";");
                                for (String l : labs) {
                                    Map.Entry<String, String> mapElement = new AbstractMap.SimpleEntry(clazz.getSimpleName()+eA(entityClass), "TypeWijziging");
                                    if (! relations.containsKey(mapElement)) {
                                        relations.put(mapElement, new ArrayList<>());
                                    }
                                    relations.get(mapElement).add(l);
                                    classNames.add(l);
                                }
                            } else {
                                Map.Entry<String, String> mapElement = new AbstractMap.SimpleEntry(clazz.getSimpleName()+eA(entityClass), "TypeWijziging");
                                if (! relations.containsKey(mapElement)) {
                                    relations.put(mapElement, new ArrayList<>());
                                }
                                relations.get(mapElement).add(splitLabels[1]);
                                classNames.add(splitLabels[1]);
                            }
                        }
                    }
                }
                int j = 0;
            }
            // Betreft
            if ((annotation != null) && (!annotation.getClass().getMethod("Betreft").getName().equals(Constants.UNASSIGNED))) {
                String linkLabel = annotation.getClass().getMethod("Betreft").getName();
                String[] paramaterMappings = annotation.toString().split(","); // Bevat=FormeelObject
                for (String labels : paramaterMappings) {
                    if (labels.trim().startsWith("Betreft")) {
                        String[] splitLabels = labels.split("=");
                        if (splitLabels[1].endsWith(")")) {
                            splitLabels[1] = splitLabels[1].substring(0, splitLabels[1].length() - 1);
                        }
                        if (! splitLabels[1].equals(Constants.UNASSIGNED)) {
                            System.out.println("Java-klasse " + clazz.getName() + " Betreft[" + splitLabels[1] + "]");
                            if (splitLabels[1].contains(";")) {
                                String[] labs = splitLabels[1].split(";");
                                for (String l : labs) {
                                    Map.Entry<String, String> mapElement = new AbstractMap.SimpleEntry(clazz.getSimpleName()+eA(entityClass), "Betreft");
                                    if (! relations.containsKey(mapElement)) {
                                        relations.put(mapElement, new ArrayList<>());
                                    }
                                    relations.get(mapElement).add(l);
                                    classNames.add(l);
                                }
                            } else {
                                Map.Entry<String, String> mapElement = new AbstractMap.SimpleEntry(clazz.getSimpleName()+eA(entityClass), "Betreft");
                                if (! relations.containsKey(mapElement)) {
                                    relations.put(mapElement, new ArrayList<>());
                                }
                                relations.get(mapElement).add(splitLabels[1]);
                                classNames.add(splitLabels[1]);
                            }
                        }
                    }
                }
                int j = 0;
            }
            if ((annotation != null) && (!annotation.getClass().getMethod("Wijzigt").getName().equals(Constants.UNASSIGNED))) {
                String linkLabel = annotation.getClass().getMethod("Wijzigt").getName();
                String[] paramaterMappings = annotation.toString().split(","); // Bevat=FormeelObject
                for (String labels : paramaterMappings) {
                    if (labels.trim().startsWith("Wijzigt")) {
                        String[] splitLabels = labels.split("=");
                        if (splitLabels[1].endsWith(")")) {
                            splitLabels[1] = splitLabels[1].substring(0, splitLabels[1].length() - 1);
                        }
                        if (! splitLabels[1].equals(Constants.UNASSIGNED)) {
                            System.out.println("Java-klasse " + clazz.getName() + " Wijzigt[" + splitLabels[1] + "]");
                            if (splitLabels[1].contains(";")) {
                                String[] labs = splitLabels[1].split(";");
                                for (String l : labs) {
                                    Map.Entry<String, String> mapElement = new AbstractMap.SimpleEntry(clazz.getSimpleName()+eA(entityClass), "Wijzigt");
                                    if (! relations.containsKey(mapElement)) {
                                        relations.put(mapElement, new ArrayList<>());
                                    }
                                    relations.get(mapElement).add(l);
                                    classNames.add(l);
                                }
                            } else {
                                Map.Entry<String, String> mapElement = new AbstractMap.SimpleEntry(clazz.getSimpleName()+eA(entityClass), "Wijzigt");
                                if (! relations.containsKey(mapElement)) {
                                    relations.put(mapElement, new ArrayList<>());
                                }
                                relations.get(mapElement).add(splitLabels[1]);
                                classNames.add(splitLabels[1]);
                            }
                        }
                    }
                }
                int j = 0;
            }
        } catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException [" + e + "]");
        }

        int j = 0;
        for (Field field : clazz.getDeclaredFields()) {
            Class type = field.getType();
            String name = field.getName();
            OperatieOpEntiteit annotations = field.getAnnotation(OperatieOpEntiteit.class);
            int i = 0;
        }
    }


    public void analyzeAnnotationClass_CompileConstant(Class clazz) {
        Annotation annotation1 = clazz.getAnnotation(OperatieOpEntiteitClass.class);
        OperatieOpEntiteitClass ann = null;
        if (annotation1 != null) {
            ann = (OperatieOpEntiteitClass) clazz.getAnnotation(OperatieOpEntiteitClass.class);
            classNames.add(clazz.getSimpleName());
        }
        if (annotation1 != null) {
            /*
            ann = (OperatieOpEntiteitClass) clazz.getAnnotation(OperatieOpEntiteitClass.class);
            int i = 0;
            Class<?>[] types2 = ann.Betreft();
            String method1 = types2[0].getSimpleName();
            String method2 = types2[1].getSimpleName();

            Class<? extends Enum>[] types3= ((OperatieOpEntiteitClass) clazz.getAnnotation(OperatieOpEntiteitClass.class)).Betreft_E();
            String enumClassName = types3[0].getSimpleName();
            String aa = annotation1.getClass().getName().toString();
            */
        }
        boolean entityClass = clazz.getAnnotation(javax.persistence.Entity.class) != null;
        if (clazz.getSimpleName().equals(Mutatietype.class.getSimpleName())) {
            int j = 0;
        }
        if ((ann != null) && (ann.BeschrijftAanvangVan().length > 0) && (ann.BeschrijftAanvangVan()[0]!=Constants.class)) {
            Class<?>[] referredClassTypes = ann.BeschrijftAanvangVan();
            for (Class<?> currentClazz : referredClassTypes) {
                Map.Entry<String, String> mapElement = new AbstractMap.SimpleEntry(clazz.getSimpleName()+eA(entityClass), "BeschrijftAanvangVan");
                if (! relations.containsKey(mapElement)) {
                    relations.put(mapElement, new ArrayList<>());
                }
                relations.get(mapElement).add(currentClazz.getSimpleName());
                classNames.add(currentClazz.getSimpleName());
            }
        }
        if ((ann != null) && (ann.BeschrijftBeeindigingVan().length > 0) && (ann.BeschrijftBeeindigingVan()[0]!=Constants.class)) {
            Class<?>[] referredClassTypes = ann.BeschrijftBeeindigingVan();
            for (Class<?> currentClazz : referredClassTypes) {
                Map.Entry<String, String> mapElement = new AbstractMap.SimpleEntry(clazz.getSimpleName()+eA(entityClass), "BeschrijftBeeindigingVan");
                if (! relations.containsKey(mapElement)) {
                    relations.put(mapElement, new ArrayList<>());
                }
                relations.get(mapElement).add(currentClazz.getSimpleName());
                classNames.add(currentClazz.getSimpleName());
            }
        }
        if ((ann != null) && (ann.Wijzigt().length > 0) && (ann.Wijzigt()[0]!=Constants.class)) {
            Class<?>[] referredClassTypes = ann.Wijzigt();
            for (Class<?> currentClazz : referredClassTypes) {
                Map.Entry<String, String> mapElement = new AbstractMap.SimpleEntry(clazz.getSimpleName()+eA(entityClass), "Wijzigt");
                if (! relations.containsKey(mapElement)) {
                    relations.put(mapElement, new ArrayList<>());
                }
                relations.get(mapElement).add(currentClazz.getSimpleName());
                classNames.add(currentClazz.getSimpleName());
            }
        }
        if ((ann != null) && (ann.Bevat().length > 0) && (ann.Bevat()[0]!=Constants.class)) {
            Class<?>[] referredClassTypes = ann.Bevat();
            for (Class<?> currentClazz : referredClassTypes) {
                Map.Entry<String, String> mapElement = new AbstractMap.SimpleEntry(clazz.getSimpleName()+eA(entityClass), "Bevat");
                if (! relations.containsKey(mapElement)) {
                    relations.put(mapElement, new ArrayList<>());
                }
                relations.get(mapElement).add(currentClazz.getSimpleName());
                classNames.add(currentClazz.getSimpleName());
            }
        }
        if ((ann != null) && (ann.Betreft().length > 0) && (ann.Betreft()[0]!=Constants.class)) {
            Class<?>[] referredClassTypes = ann.Betreft();
            for (Class<?> currentClazz : referredClassTypes) {
                Map.Entry<String, String> mapElement = new AbstractMap.SimpleEntry(clazz.getSimpleName()+eA(entityClass), "Betreft");
                if (! relations.containsKey(mapElement)) {
                    relations.put(mapElement, new ArrayList<>());
                }
                relations.get(mapElement).add(currentClazz.getSimpleName());
                classNames.add(currentClazz.getSimpleName());
            }
        }
        if ((ann != null) && (ann.TypeWijziging().length > 0) && (ann.TypeWijziging()[0]!=Constants.class)) {
            Class<?>[] referredClassTypes = ann.TypeWijziging();
            for (Class<?> currentClazz : referredClassTypes) {
                Map.Entry<String, String> mapElement = new AbstractMap.SimpleEntry(clazz.getSimpleName()+eA(entityClass), "TypeWijziging");
                if (! relations.containsKey(mapElement)) {
                    relations.put(mapElement, new ArrayList<>());
                }
                relations.get(mapElement).add(currentClazz.getSimpleName());
                classNames.add(currentClazz.getSimpleName());
            }
        }

        int j = 0;
        for (Field field : clazz.getDeclaredFields()) {
            Class type = field.getType();
            String name = field.getName();
            OperatieOpEntiteit annotations = field.getAnnotation(OperatieOpEntiteit.class);
            int i = 0;
        }
    }


    private String eA(boolean variable) {
        if (variable) {
            return "-e";
        } else {
            return "";
        }
    }

}
