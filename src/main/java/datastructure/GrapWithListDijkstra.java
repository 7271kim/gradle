package datastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class GrapWithListDijkstra {

    private static final int MAX_VALUE = Integer.MAX_VALUE;
    private List<List<Edge>> graph;

    public GrapWithListDijkstra(int nodeCount) {
        graph = new ArrayList<>();
        for (int index = 0; index < nodeCount + 1; index++) {
            graph.add(new ArrayList<>());
        }
    }

    public void add(Integer startNodeIndex, Integer endNodeIndex, int weight) {
        Edge nodeEndWeight = new Edge(endNodeIndex, weight);
        Edge nodeStartWeight = new Edge(startNodeIndex, weight);
        graph.get(startNodeIndex).add(nodeEndWeight);
        graph.get(endNodeIndex).add(nodeStartWeight);
    }

    public void addDirection(Integer startNodeIndex, Integer endNodeIndex, int weight) {
        Edge nodeEndWeight = new Edge(endNodeIndex, weight);
        graph.get(startNodeIndex).add(nodeEndWeight);
    }

    protected boolean isDirectConected(Integer startNodeIndex, Integer endNodeIndex) {
        boolean result = false;
        List<Edge> startEdges = graph.get(startNodeIndex);
        for (Edge node : startEdges) {
            if (node.getEndIndex() == endNodeIndex) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static class Edge implements Comparable<Edge> {
        int endNodeIndex;
        int weight;

        public Edge(int endNodeIndex, int weight) {
            this.endNodeIndex = endNodeIndex;
            this.weight = weight;
        }

        public int getEndIndex() {
            return endNodeIndex;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Edge compare) {
            return weight >= compare.getWeight() ? 1 : -1;
        }
    }

    public MinMaxGraphResult dijkstraMin(Integer startNodeIndex, Integer endNodeIndex) {
        boolean[] visitedNode = new boolean[graph.size()];
        int[] path = new int[graph.size()];
        int[] dijkstra = initDijkstraZeroOrMaxValue(startNodeIndex);
        PriorityQueue<Edge> priQue = new PriorityQueue<>();

        setStartNode(startNodeIndex, visitedNode, priQue, dijkstra);
        loopUntileQueEmpt(visitedNode, path, dijkstra, priQue);

        return getMinWeightAndPathList(startNodeIndex, endNodeIndex, dijkstra, path);
    }

    private MinMaxGraphResult getMinWeightAndPathList(Integer startNodeIndex, Integer endNodeIndex, int[] dijkstra,
            int[] path) {
        MinMaxGraphResult result = new MinMaxGraphResult();
        List<Integer> pathList = getPathListStartToEnd(startNodeIndex, endNodeIndex, dijkstra, path);
        result.setWeight(dijkstra[endNodeIndex]);
        result.setPathList(pathList);

        return result;
    }

    private List<Integer> getPathListStartToEnd(Integer startNodeIndex, Integer endNodeIndex, int[] dijkstra,
            int[] path) {
        List<Integer> pathList = new ArrayList<>();
        if (isConected(dijkstra, endNodeIndex)) {
            pathList.add(endNodeIndex);
            if (startNodeIndex != endNodeIndex) {
                while (startNodeIndex != path[endNodeIndex]) {
                    pathList.add(path[endNodeIndex]);
                    endNodeIndex = path[endNodeIndex];
                }
                pathList.add(startNodeIndex);
            }
        }
        Collections.reverse(pathList);
        return pathList;
    }

    private boolean isConected(int[] dijkstra, Integer endNodeIndex) {
        return dijkstra[endNodeIndex] != MAX_VALUE;
    }

    private void setStartNode(Integer startNodeIndex, boolean[] visitedNode, PriorityQueue<Edge> priQue,
            int[] dijkstra) {
        priQue.add(new Edge(startNodeIndex, dijkstra[startNodeIndex]));
    }

    private void loopUntileQueEmpt(boolean[] visitedNode, int[] path, int[] dijkstra, PriorityQueue<Edge> priQue) {
        while (!priQue.isEmpty()) {
            Edge top = priQue.poll();
            visitedNode[top.getEndIndex()] = true;

            for (Edge neighborhood : graph.get(top.endNodeIndex)) {
                updatePathAndDijkstra(neighborhood, path, dijkstra, top);
                updateVisitedNode(neighborhood, visitedNode, priQue, dijkstra);
            }
        }
    }

    private void updateVisitedNode(Edge neighborhood, boolean[] visitedNode, PriorityQueue<Edge> priQue,
            int[] dijkstra) {
        if (!visitedNode[neighborhood.getEndIndex()]) {
            priQue.add(new Edge(neighborhood.getEndIndex(), dijkstra[neighborhood.getEndIndex()]));
        }
    }

    private void updatePathAndDijkstra(Edge neighborhood, int[] path, int[] dijkstra, Edge top) {
        int currentWeight = dijkstra[neighborhood.getEndIndex()];
        int newWeight = dijkstra[top.getEndIndex()] + neighborhood.getWeight();

        if (currentWeight > newWeight) {
            dijkstra[neighborhood.getEndIndex()] = newWeight;
            path[neighborhood.getEndIndex()] = top.getEndIndex();
        }
    }

    private int[] initDijkstraZeroOrMaxValue(Integer startNodeIndex) {
        int[] result = new int[graph.size()];
        for (int index = 0; index < graph.size(); index++) {
            if (startNodeIndex == index) {
                result[index] = 0;
            } else {
                result[index] = MAX_VALUE;
            }
        }
        return result;
    }

}
