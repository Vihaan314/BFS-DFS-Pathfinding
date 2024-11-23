import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CSVParser {
    private String csvName;

    private Map<Integer, List<Integer>> adjList = new HashMap<>();
    private Map<Integer, String> vertexNameList = new HashMap<>();

    private String removePunc(String s) {
        StringBuilder removed = new StringBuilder();
        for (char c : s.toCharArray()) {
            if ((c != ' ') && (c != '"')) {
                removed.append(c);
            }
        }
        return removed.toString();
    }

    private List<Integer> stringToIntList(List<String> adjacencyString) {
        return adjacencyString.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    public void createVertexMaps() {
        String[] vertexInfo;
        try (BufferedReader br = new BufferedReader(new FileReader(csvName))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String regex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
                vertexInfo = line.split(regex);
                List<Integer> adjacenciesList = stringToIntList(Arrays.asList(removePunc(vertexInfo[2]).split(",")));
                adjList.put(Integer.valueOf(vertexInfo[0]), adjacenciesList);
                vertexNameList.put(Integer.valueOf(vertexInfo[0]), vertexInfo[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Integer, List<Integer>> getAdjacencyList() {
        return adjList;
    }

    public Map<Integer, String> getVertexNames() {
        return vertexNameList;
    }

    public CSVParser(String csvName) {
        this.csvName = csvName;
    }
}
