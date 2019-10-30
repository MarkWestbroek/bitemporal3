package nl.kvk.np.semantics;

import org.jgraph.graph.AttributeMap;
import org.jgrapht.Graph;
import org.jgrapht.ext.*;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.SimpleGraph;


import java.io.File;
import java.util.*;

import static org.jgrapht.ext.JGraphModelAdapter.createDefaultVertexAttributes;

// import static org.jgrapht.ext.J
// Add an image to each node in a graph
// https://stackoverflow.com/questions/7795411/annotate-dot-graphs-with-images

/**
 * Created by megmontp on 6/2/17.
 */
public class MapAnalysisToGraph {

    public static final String CLASS_HIERARCHY = "SemanticClassHierarchy";
    public static final String KIND_OF_PART_WHOLE = "KindOfPartWhole";

    public static final String dotGraphName = "semanticNetworkGraphNP";

    private Map<String, Map<String, Graph<String, LabeledDirectedEdge>>> structureGraphs =
            new HashMap<String, Map<String, Graph<String, LabeledDirectedEdge>>>();

    List<String> entityNodes = new ArrayList<>();

    static int indexNo = 1;

    public MapAnalysisToGraph() {

        structureGraphs.put(CLASS_HIERARCHY, new HashMap<String, Graph<String, LabeledDirectedEdge>>());

    }

    public static void start() {
        // Graph<String, DefaultEdge> stringGraph = createStringGraph();

        // note undirected edges are printed as: {<v1>,<v2>}
        // System.out.println(stringGraph.toString());



    }

    public void addToGraphStructure(Map<Map.Entry<String, String>, List<String>> relations, Set<String> nodeList) {

        // Graph<String, LabeledDirectedEdge> decisionGraph = new DefaultDirectedGraph<String, LabeledDirectedEdge>(LabeledDirectedEdge.class);



        Graph<String, LabeledDirectedEdge> decisionGraph = new DirectedMultigraph(LabeledDirectedEdge.class);

        Set<String> nodes = new HashSet<String>();
        for (Map.Entry<String, String> beginNode : relations.keySet()) {
            nodes.add(beginNode.getKey());
        }
        for (List<String> nodeIds : relations.values()) {
            for (String nodeId : nodeIds) {
                nodes.add(nodeId);
            }
        }
        for (String nodeId: nodeList) {
            nodes.add(nodeId);
        }
        for (String nodeId: nodes) {
            if (nodeId.endsWith("-e")) {
                entityNodes.add(nodeId);
                nodeId = nodeId.substring(0, nodeId.lastIndexOf("-e"));
            }
            decisionGraph.addVertex(nodeId);
        }

        for (Map.Entry<String,String> nodeLink : relations.keySet()) {
            for (String nodeToId : relations.get(nodeLink)) {
                if (nodeToId.endsWith("-e")) {
                    nodeToId = nodeToId.substring(0, nodeToId.lastIndexOf("-e"));
                }
                String nodeFromId = nodeLink.getKey();
                if (nodeFromId.endsWith("-e")) {
                    nodeFromId = nodeFromId.substring(0, nodeFromId.lastIndexOf("-e"));
                }
                decisionGraph.addEdge(nodeFromId, nodeToId,
                        new LabeledDirectedEdge<String>(nodeFromId, nodeToId,
                        nodeLink.getValue()));
            }
            /*
            if (linkLabels.containsKey(new AbstractMap.SimpleEntry<String, String>(nodeLink.getKey(), nodeLink.getValue()))) {
                String label = linkLabels.get(new AbstractMap.SimpleEntry<String, String>(nodeLink.getKey(), nodeLink.getValue()));
                decisionGraph.addEdge(nodeLink.getKey(), nodeLink.getValue(), new LabeledDirectedEdge<String>(nodeLink.getKey(), nodeLink.getValue(),
                        label));
            } else {
                decisionGraph.addEdge(nodeLink.getKey(), nodeLink.getValue(), new LabeledDirectedEdge<String>(nodeLink.getKey(), nodeLink.getValue(),
                        "_"));
            }
            */

        }

        writeGraph(decisionGraph);


         /*       if (!semanticStructure.PartOf().equals(Constants.UNASSIGNED)) {
                    if (!g.vertexSet().contains(field.getName())) {
                        g.addVertex(field.getName());
                    }
                    if (!g.vertexSet().contains(semanticStructure.PartOf())) {
                        g.addVertex(semanticStructure.PartOf());
                    }

                    g.addEdge(field.getName(), semanticStructure.PartOf(), new LabeledDirectedEdge<String>(canonicalClassName, semanticStructure.PartOf(),
                            "PartOf"));


                }
                */
    }




    // addToGraphStructure(className, clazz.getSuperclass().getName(),"KindOf")
    public void writeGraph( Graph<String, LabeledDirectedEdge> decisionGraph ) {
        try {



            Graph<String, LabeledDirectedEdge> gOrg = decisionGraph;
            if (gOrg == null) {
                return;
            }

            // EdgeReversedGraph<String, LabeledDirectedEdge> g = new EdgeReversedGraph<String, LabeledDirectedEdge>((DirectedGraph<String, LabeledDirectedEdge>) gOrg);
            Graph<String, LabeledDirectedEdge> g = gOrg;
            AttributeMap attributeMap = createDefaultVertexAttributes();

            DefaultEdge defaultEdge = new DefaultEdge();

            IntegerNameProvider<String> vertexId = new IntegerNameProvider<String>() {
                public String getVertexName(String p) {
                    return p.toString();
                }
            };
            // Defines the vertex label to be displayed in the .gv file
            StringNameProvider<String> vertexLabel = new StringNameProvider<String>() {
                public String getVertexName(String p) {
                    return p.toString().replace("_","\n");
                }
            };

            StringEdgeNameProvider<LabeledDirectedEdge> edgeLabel = new StringEdgeNameProvider<LabeledDirectedEdge>() {
                public String getEdgeName(DefaultEdge de) {
                    return de.toString() + " dir = forward ";
                }
            };

            ComponentAttributeProvider<String> vertexAttributeProvider =
                    new ComponentAttributeProvider<String>() {
                        /* For every vertex v, return a single attribute with name "shape"
                         * and value "box". (The vertex is drawn as a rectangle)
                         */
                        public Map<String, String> getComponentAttributes(String v) {
                            Map<String, String> map =
                                    new LinkedHashMap<String, String>();
                            String className = v.toString() + "-e";
                            if (entityNodes.contains(className)) {
                                map.put("shape", "box");
                                map.put("color", "red");
                            } else {
                                map.put("shape", "box");
                            }

                            if (v.toString().startsWith("Decision")) {
                                map.put("fontcolor", "red");
                            }
                            return map;
                        }
                    };

            ComponentAttributeProvider<LabeledDirectedEdge> edgeAttributeProvider =
                    new ComponentAttributeProvider<LabeledDirectedEdge>() {
                        public Map<String, String> getComponentAttributes(LabeledDirectedEdge v) {
                            Map<String, String> map =
                                    new LinkedHashMap<String, String>();
                            map.put("dir", "forward");
                            if (v.toString().startsWith("Decision")) {
                                map.put("color", "red");
                                map.put("fontcolor", "red");
                            }
                            return map;
                        }
                        /* For every vertex v, return a single attribute with name "shape"
                         * and value "box". (The vertex is drawn as a rectangle)
                         */
                    };


            DOTExporter<String, LabeledDirectedEdge> dotExporter = new DOTExporter(vertexId, vertexLabel, edgeLabel, vertexAttributeProvider, edgeAttributeProvider);
            try {
                dotExporter.exportGraph(g, new File(dotGraphName + ".dot"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Create a toy graph based on String objects.
     *
     * @return a graph based on String objects.
     */
    private static Graph<String, DefaultEdge> createStringGraph() {
        Graph<String, DefaultEdge> g = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";

        // add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        // add edges to create a circuit
        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v4);
        g.addEdge(v4, v1);

        return g;
    }

    private static Integer getIndexNo() {
        return indexNo++;
    }

    public static class LabeledDirectedEdge<V> extends DefaultEdge {
        private V v1;
        private V v2;
        private String label;

        public LabeledDirectedEdge(V v1, V v2, String label) {
            this.v1 = v1;
            this.v2 = v2;
            this.label = label;
        }

        public V getV1() {
            return v1;
        }

        public V getV2() {
            return v2;
        }

        public String toString() {
            return label;
        }
    }
}

