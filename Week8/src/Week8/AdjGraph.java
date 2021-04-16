package Week8;

public class AdjGraph {
    int vertex = 0;
    LinkedList list[];
    int graphMat[][];

    public AdjGraph(int size) {
        this.vertex = size;
        this.list = new LinkedList[size];
        this.graphMat = new int[size][size];
        for (int i = 0; i < size; i++) {
            list[i] = new LinkedList();
        }
    }

    public void addEdge(int source, int destination, int cost) {
        if (source != destination) {
            list[source].addNode(destination);
            list[destination].addNode(source);
            graphMat[source][destination] = cost;
            graphMat[destination][source] = cost;
        }
    }

    public void printGraph() {
        for (int i = 0; i < vertex; i++) {
            System.out.print("Vertex " + i + " is connected with: ");
            for (int j = 0; j < list[i].size(); j++) {
                System.out.print(list[i].get(j) + " ");
            }
            System.out.println();
        }
    }

    public String[] getConnected(int i) {
        String data[] = new String[list[i].size() + 1];

        data[0] = Integer.toString(i);
        for (int j = 0; j < list[i].size(); j++) {
            data[j + 1] = Integer.toString(list[i].get(j));
        }
        return data;
    }

    public void printMatrix() {
        System.out.println("printing the MATRIX");
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                System.out.print(graphMat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[] shortestPathDij(int source, int destination) {
        boolean visited[] = new boolean[vertex];
        int path[] = new int[vertex];
        int minDistance[] = new int[vertex];
        for (int i = 0; i < vertex; i++) {
            path[i] = -1;
            minDistance[i] = Integer.MAX_VALUE;
        }
        minDistance[source] = 0;

        for (int i = 0; i < graphMat.length; i++) {
            int minVertex = findMinVertex(minDistance, visited);
            visited[minVertex] = true;
            for (int j = 0; j < graphMat.length; j++) {
                if (graphMat[minVertex][j] != 0 && !visited[j] && minDistance[minVertex] != Integer.MAX_VALUE) {
                    int newDistance = minDistance[minVertex] + graphMat[minVertex][j];
                    if (newDistance < minDistance[j]) {
                        minDistance[j] = newDistance;
                        path[j] = minVertex;
                    }
                }
            }
        }
        return generateShortestPath(source, destination, path, minDistance);
    }

    public int findMinVertex(int minDistance[], boolean visited[]) {
        int minVertex = -1;
        for (int i = 0; i < minDistance.length; i++) {
            if (!visited[i] && (minVertex == -1 || minDistance[i] < minDistance[minVertex])) {
                minVertex = i;
            }
        }
        return minVertex;
    }

    int[] generateShortestPath(int source, int destination, int path[], int minDistance[]) {
        int crawl = destination;
        LinkedList pathList = new LinkedList();
        pathList.addNode(crawl);
        while (path[crawl] != -1) {
            pathList.addNode(path[crawl]);
            crawl = path[crawl];
        }
        int[] shortPath = new int[pathList.size()];
        for (int i = 0; i < pathList.size(); i++) {
            shortPath[i] = pathList.get(i);
        }
        return shortPath;
    }
}