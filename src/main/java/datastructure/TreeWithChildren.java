package datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeWithChildren<T> {

    private Map<T, Node> tree;

    public TreeWithChildren() {
        tree = new HashMap<>();
    }

    public void add(T input) {
        tree.putIfAbsent(input, new Node<>(input));
    }

    public void setChild(T parent, T child) {
        tree.get(parent).children.add(tree.get(child));
    }

    public static class Node<T> {
        T input;
        List<Node> children;

        public Node(T input) {
            this.input = input;
            this.children = new ArrayList<>();
        }
    }

    public Node getNode(T input) {
        return tree.get(input);
    }

    public List<Node> getChildren(T input) {
        return tree.get(input).children;
    }

}
