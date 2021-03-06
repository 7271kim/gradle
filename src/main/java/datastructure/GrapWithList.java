package datastructure;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class GrapWithList {

    private List<List<Edge>> graph;

    public GrapWithList(int nodeCount) {
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

    public List<Integer> dfs(Integer findIndex) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<Integer> stack = new Stack<>();
        boolean[] cached = new boolean[graph.size()];
        stack.add(findIndex);
        checkDfsEdges(result, stack, cached);

        return result;
    }

    public List<Integer> bfs(Integer findIndex) {
        List<Integer> result = new ArrayList<Integer>();
        Queue<Integer> que = new ArrayDeque<>();
        boolean[] cached = new boolean[graph.size()];
        que.add(findIndex);
        checkBfsEdges(result, que, cached);

        return result;
    }

    private void checkBfsEdges(List<Integer> result, Queue<Integer> que, boolean[] cached) {
        while (!que.isEmpty()) {
            int topIndex = que.poll();
            if (!cached[topIndex]) {
                cached[topIndex] = true;
                result.add(topIndex);
                for (Edge Edge : graph.get(topIndex)) {
                    que.add(Edge.getEndIndex());
                }
                checkBfsEdges(result, que, cached);
            }
        }
    }

    private void checkDfsEdges(List<Integer> result, Stack<Integer> stack, boolean[] cached) {
        while (!stack.isEmpty()) {
            int topIndex = stack.pop();
            if (!cached[topIndex]) {
                result.add(topIndex);
                cached[topIndex] = true;
                for (Edge Edge : graph.get(topIndex)) {
                    stack.add(Edge.endNodeIndex);
                    checkDfsEdges(result, stack, cached);
                }
            }
        }
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
}
