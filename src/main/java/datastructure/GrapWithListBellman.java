package datastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class GrapWithListBellman {

    private static final int MAX_VALUE = Integer.MAX_VALUE;
    private List<List<Edge>> graph;

    public GrapWithListBellman(int nodeCount) {
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

    public MinMaxGraphResult bellmanMin(Integer startNodeIndex, Integer endNodeIndex) {
        boolean[] visitedNode = new boolean[graph.size()];
        int[] path = new int[graph.size()];
        int[] bellman = initBellmanZeroOrMaxValue(startNodeIndex);
        PriorityQueue<Edge> priQue = new PriorityQueue<>();

        setStartNode(startNodeIndex, visitedNode, priQue, bellman);
        loopNodeCount(priQue, path, bellman);
        boolean hasNegativeCycle = hasNegativeCycle(priQue, bellman);

        return getMinWeightAndPathList(startNodeIndex, endNodeIndex, bellman, path, hasNegativeCycle);
    }

    private MinMaxGraphResult getMinWeightAndPathList(Integer startNodeIndex, Integer endNodeIndex, int[] bellman,
            int[] path, boolean hasNegativeCycle) {
        MinMaxGraphResult result = new MinMaxGraphResult();
        if (!hasNegativeCycle) {
            List<Integer> pathList = getPathListStartToEnd(startNodeIndex, endNodeIndex, bellman, path);
            result.setWeight(bellman[endNodeIndex]);
            result.setPathList(pathList);
        } else {
            result.setWeight(MAX_VALUE);
            result.setPathList(new ArrayList<>());
        }

        return result;
    }

    private List<Integer> getPathListStartToEnd(Integer startNodeIndex, Integer endNodeIndex, int[] bellman,
            int[] path) {
        List<Integer> pathList = new ArrayList<>();
        if (isConected(bellman, endNodeIndex)) {
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

    private boolean isConected(int[] bellman, Integer endNodeIndex) {
        return bellman[endNodeIndex] != MAX_VALUE;
    }

    private boolean hasNegativeCycle(PriorityQueue<Edge> priQue, int[] bellman) {
        if (priQue.size() == 0) {
            return false;
        }
        Edge top = priQue.poll();
        for (Edge neighborhood : graph.get(top.endNodeIndex)) {
            int currentWeight = bellman[neighborhood.getEndIndex()];
            int newWeight = bellman[top.getEndIndex()] + neighborhood.getWeight();

            if (currentWeight > newWeight) {
                return true;
            }
        }
        return false;
    }

    private void loopNodeCount(PriorityQueue<Edge> priQue, int[] path, int[] bellman) {
        for (int index = 0; index < graph.size(); index++) {
            if (priQue.size() > 0) {
                Edge top = priQue.poll();
                updateAllEdges(top, priQue, path, bellman);
            }
        }
    }

    private void updateAllEdges(Edge top, PriorityQueue<Edge> priQue, int[] path, int[] bellman) {
        for (Edge neighborhood : graph.get(top.endNodeIndex)) {
            int currentWeight = bellman[neighborhood.getEndIndex()];
            int newWeight = bellman[top.getEndIndex()] + neighborhood.getWeight();

            if (currentWeight > newWeight) {
                bellman[neighborhood.getEndIndex()] = newWeight;
                path[neighborhood.getEndIndex()] = top.getEndIndex();
                priQue.add(new Edge(neighborhood.getEndIndex(), bellman[neighborhood.getEndIndex()]));
            }
        }
    }

    private void setStartNode(Integer startNodeIndex, boolean[] visitedNode, PriorityQueue<Edge> priQue,
            int[] bellman) {
        priQue.add(new Edge(startNodeIndex, bellman[startNodeIndex]));
    }

    private int[] initBellmanZeroOrMaxValue(Integer startNodeIndex) {
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
