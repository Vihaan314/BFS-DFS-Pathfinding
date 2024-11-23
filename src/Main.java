import java.util.List;
import java.util.Map;

public class Main {
    private static Map<Integer, List<Integer>> adjList;
    private static Map<Integer, String> vertexNames;

    private static void setupParser() {
        CSVParser horaceMannParser = new CSVParser("HoraceMannGraphTheoreticalCSAdjList.csv");
        horaceMannParser.createVertexMaps();
        adjList = horaceMannParser.getAdjacencyList();
        vertexNames = horaceMannParser.getVertexNames();
    }

    private static void getJourney(String s_v, String e_v) {
        SearchAlgorithm searchAlgorithm = new SearchAlgorithm(adjList, vertexNames);
        searchAlgorithm.setJourney(s_v, e_v);
        List<Integer> pathBFS = searchAlgorithm.doBFS();
        List<String> formattedPathBFS = searchAlgorithm.createFormattedPath(pathBFS);

        List<Integer> pathDFS = searchAlgorithm.doDFS();
        List<String> formattedPathDFS = searchAlgorithm.createFormattedPath(pathDFS);
        System.out.println();
        System.out.println(s_v + " - " + e_v + ":");
        System.out.println("BFS path: " + formattedPathBFS);
        System.out.println("BFS path length: " + (formattedPathBFS.size()-1));
        System.out.println();
        System.out.println("DFS path: " + formattedPathDFS);
        System.out.println("DFS path length: " + (formattedPathDFS.size() -1));
    }

    public static void main(String[] args) {
        setupParser();
        System.out.println("Horace Mann Graph:");
        System.out.println(adjList);
        System.out.println(vertexNames);
        System.out.println(adjList.size());

        String s_v = "Olshan Lobby";
        String e_v = "Recording Studio Room 1";
        getJourney(s_v, e_v);

        System.out.println();
        Eccentricity eccentricity = new Eccentricity(adjList, vertexNames);
        int eccentricityMax = eccentricity.computeEccentricity();
        System.out.println("Eccentricity: " + eccentricityMax);

        System.out.println();
        SpanningTree spanningTreeCreator = new SpanningTree(adjList, vertexNames);
        List<Edge> spanningTree = spanningTreeCreator.computeSpanningTree();
        System.out.println("Spanning tree: " + spanningTree);
        System.out.println("|E| = " + spanningTree.size());
    }
}