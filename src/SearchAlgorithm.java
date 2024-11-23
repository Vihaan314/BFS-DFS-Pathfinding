import java.util.*;
import java.util.stream.Collectors;

public class SearchAlgorithm {
    Map<Integer, List<Integer>> adjList;
    static Map<Integer, String> vertexNames;
    String start_v;
    String end_v;

    public SearchAlgorithm(Map<Integer, List<Integer>> adjList, Map<Integer, String> vertexNames) {
        this.adjList = adjList;
        SearchAlgorithm.vertexNames = vertexNames;
    }

    public void setJourney(String start_v, String end_v) {
        this.start_v = start_v;
        this.end_v = end_v;
    }

    public static Integer getIndexFromName(String value) {
        return vertexNames.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), value))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet())
                .iterator().next();
    }

    //Breadth-first search
    public List<Integer> doBFS() {
        //Initialize the final path
        List<Integer> vertexPath = new ArrayList<>(adjList.size());

        //Get keys of start and end vertices
        Integer start = getIndexFromName(start_v);
        Integer end = getIndexFromName(end_v);

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> predecessors = new HashMap<>();

        //Mark the starting vertex as visited
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            //Get first vertex in queue and remove it
            Integer current = queue.poll();
            //Loop through its neighbors
            for (Integer neighbor : adjList.get(current)) {
                //If we haven't visited the neighbor vertex, process it
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    predecessors.put(neighbor, current);
                    queue.add(neighbor);

                    //If the neighbor is our target vertex, retrace our path
                    if (neighbor.equals(end)) {
                        Integer step = end;
                        while (step != null) {
                            //Add each step going back to the final path
                            vertexPath.add(step);
                            step = predecessors.get(step);
                        }
                        //Because we are retracing, the path must be reversed to start from the start vertex
                        Collections.reverse(vertexPath);
                        return vertexPath;
                    }
                }
            }
        }

        return vertexPath;
    }

    //Depth-first search
    public List<Integer> doDFS() {
        //The same as DFS but we have a stack instead of a queue

        List<Integer> vertexPath = new ArrayList<>(adjList.size());
        Integer start = getIndexFromName(start_v);
        Integer end = getIndexFromName(end_v);

        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> predecessors = new HashMap<>();

        stack.push(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            Integer current = stack.pop();
            for (Integer neighbor : adjList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    predecessors.put(neighbor, current);
                    stack.push(neighbor);

                    if (neighbor.equals(end)) {
                        Integer step = end;
                        while (step != null) {
                            vertexPath.add(step);
                            step = predecessors.get(step);
                        }
                        Collections.reverse(vertexPath);
                        return vertexPath;
                    }
                }
            }
        }

        return vertexPath;
    }

    public List<String> createFormattedPath(List<Integer> vertexPath) {
        List<String> formattedPath = new ArrayList<>();
        for (Integer vertex : vertexPath) {
            formattedPath.add(vertexNames.get(vertex));
        }
        return formattedPath;
    }
}
