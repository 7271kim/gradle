package datastructure;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class GraphWithMatrix {

	private int[][] graph;

	public GraphWithMatrix(int nodeSize) {
		graph = new int[nodeSize + 1][nodeSize + 1];
	}

	public void add(Integer startIndex, Integer endIndex, int weight) {
		checkWeightValidation(weight);
		graph[startIndex][endIndex] = weight;
		graph[endIndex][startIndex] = weight;
	}

	public void addDirection(Integer startIndex, Integer endIndex, int weight) {
		checkWeightValidation(weight);
		graph[startIndex][endIndex] = weight;
	}

	private void checkWeightValidation(int weight) {
		if (weight == 0) {
			throw new IllegalArgumentException("가중치는 0이 불가능합니다.");
		}
	}

	public List<Integer> dfs(Integer findIndex) {
		List<Integer> result = new ArrayList<Integer>();
		Stack<Integer> stack = new Stack<>();
		boolean[] cached = new boolean[graph.length];
		stack.add(findIndex);
		checkDfsEdges(result, stack, cached);

		return result;
	}

	private void checkDfsEdges(List<Integer> result, Stack<Integer> stack, boolean[] cached) {
		while (!stack.isEmpty()) {
			int topIndex = stack.pop();
			if (!cached[topIndex]) {
				result.add(topIndex);
				cached[topIndex] = true;
				checkAllWeightDfs(result, stack, cached, topIndex);
			}
		}
	}

	private void checkAllWeightDfs(List<Integer> result, Stack<Integer> stack, boolean[] cached, int findIndex) {
		for (int index = 0; index < graph.length; index++) {
			int weight = graph[findIndex][index];
			if (weight != 0) {
				stack.add(index);
				checkDfsEdges(result, stack, cached);
			}
		}
	}

	public List<Integer> bfs(Integer findIndex) {
		List<Integer> result = new ArrayList<Integer>();
		Queue<Integer> que = new ArrayDeque<>();
		boolean[] cached = new boolean[graph.length];
		que.add(findIndex);
		checkBfsEdges(result, que, cached);

		return result;
	}

	private void checkBfsEdges(List<Integer> result, Queue<Integer> que, boolean[] cached) {
		while (!que.isEmpty()) {
			int topIndex = que.poll();
			if (!cached[topIndex]) {
				result.add(topIndex);
				cached[topIndex] = true;
				checkAllWeightBfs(result, que, cached, topIndex);
			}
		}
	}

	private void checkAllWeightBfs(List<Integer> result, Queue<Integer> que, boolean[] cached, int findIndex) {
		for (int index = 0; index < graph.length; index++) {
			int weight = graph[findIndex][index];
			if (weight != 0) {
				que.add(index);
			}
		}
		checkBfsEdges(result, que, cached);
	}

	public boolean isConected(Integer firstIndex, Integer endIndex) {
		return graph[firstIndex][endIndex] != 0;
	}

}
