import java.util.*;

public class Eccentricity {
    Map<Integer, List<Integer>> adjList;
    Map<Integer, String> vertexNames;

    public Eccentricity(Map<Integer, List<Integer>> adjList, Map<Integer, String> vertexNames) {
        this.adjList = adjList;
        this.vertexNames = vertexNames;
    }

    public int computeEccentricityFromVertex(String start_v) {
        Integer start = SearchAlgorithm.getIndexFromName(start_v);

        //Use modified BFS to find the longest shortest path
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        //Keep track of the longest path
        int maxDistance = Integer.MIN_VALUE;
        Map<Integer, Integer> distances = new HashMap<>();
        distances.put(start, 0);

        while (!queue.isEmpty()) {
            //The current distance is one more than the previous distance
            Integer current = queue.poll();
            int currentDistance = distances.get(current)+1;

            for (Integer neighbor : adjList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    //If this distance is greater, it becomes the new max
                    distances.put(neighbor, currentDistance);
                    maxDistance = Math.max(currentDistance, maxDistance);
                }
            }
        }
        return maxDistance;
    }

    public int computeEccentricity() {
        int maxDistance = Integer.MIN_VALUE;
        //Iterate through every vertex in the graph and return the maximum distance from any vertex - the eccentricity
        for (String vertex : vertexNames.values()) {
            maxDistance = Math.max(computeEccentricityFromVertex(vertex), maxDistance);
        }

        return maxDistance;
    }
}
