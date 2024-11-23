import java.util.*;

public class SpanningTree {
    Map<Integer, List<Integer>> adjList;
    Map<Integer, String> vertexNames;

    public SpanningTree(Map<Integer, List<Integer>> adjList, Map<Integer, String> vertexNames) {
        this.adjList = adjList;
        this.vertexNames = vertexNames;
    }

    public List<Edge> computeSpanningTree() {
        //The spanning tree
        List<Edge> spanningEdgeList = new ArrayList<>();

        //Set an arbitrary start vertex to start traversal
        Integer start = SearchAlgorithm.getIndexFromName("Olshan Lobby");

        //Perform BFS-like search
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            //Iterate through neighbors of current vertex
            for (Integer neighbor : adjList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    //Create edge between current vertex and neighbor
                    spanningEdgeList.add(new Edge(current, neighbor));
                }
            }
        }

        return spanningEdgeList;
    }
}
