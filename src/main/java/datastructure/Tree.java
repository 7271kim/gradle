package datastructure;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {

    private ArrayList<T> tree;

    public Tree() {
        tree = new ArrayList<T>();
    }

    public void add(T input) {
        this.tree.add(input);
    }

    public List<T> inorder() {
        List<T> result = new ArrayList<>();
        setLeftRootRightWithIndex(0, result);
        return result;
    }

    public List<T> preorder() {
        List<T> result = new ArrayList<>();
        setRootLeftRightWithIndex(0, result);
        return result;
    }

    public List<T> postorder() {
        List<T> result = new ArrayList<>();
        setLeftRightRootWithIndex(0, result);
        return result;
    }

    private void setLeftRightRootWithIndex(int currentIndex, List<T> result) {
        if (hasLeftNode(currentIndex)) {
            setLeftRightRootWithIndex(getLeftNodeIndex(currentIndex), result);
        }

        if (hasRightNode(currentIndex)) {
            setLeftRightRootWithIndex(getRightNodeIndex(currentIndex), result);
        }

        result.add(tree.get(currentIndex));
    }

    private void setRootLeftRightWithIndex(int currentIndex, List<T> result) {
        result.add(tree.get(currentIndex));

        if (hasLeftNode(currentIndex)) {
            setRootLeftRightWithIndex(getLeftNodeIndex(currentIndex), result);
        }

        if (hasRightNode(currentIndex)) {
            setRootLeftRightWithIndex(getRightNodeIndex(currentIndex), result);
        }
    }

    private void setLeftRootRightWithIndex(int currentIndex, List<T> result) {
        if (hasLeftNode(currentIndex)) {
            setLeftRootRightWithIndex(getLeftNodeIndex(currentIndex), result);
        }
        result.add(tree.get(currentIndex));

        if (hasRightNode(currentIndex)) {
            setLeftRootRightWithIndex(getRightNodeIndex(currentIndex), result);
        }
    }

    private boolean hasRightNode(int currentIndex) {
        return hasNode(getRightNodeIndex(currentIndex));
    }

    private boolean hasLeftNode(int index) {
        return hasNode(getLeftNodeIndex(index));
    }

    private int getLeftNodeIndex(int currentIndex) {
        return currentIndex * 2 + 1;
    }

    private int getRightNodeIndex(int currentIndex) {
        return currentIndex * 2 + 2;
    }

    private boolean hasNode(int currentIndex) {
        return 0 <= currentIndex && currentIndex < tree.size() && null != tree.get(currentIndex);
    }

}
