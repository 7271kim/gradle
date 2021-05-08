package datastructure;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {

    protected ArrayList<T> tree;

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

    protected boolean hasRightNode(int currentIndex) {
        return hasNode(getRightNodeIndex(currentIndex));
    }

    protected boolean hasLeftNode(int index) {
        return hasNode(getLeftNodeIndex(index));
    }

    protected int getLeftNodeIndex(int currentIndex) {
        return currentIndex * 2 + 1;
    }

    protected int getRightNodeIndex(int currentIndex) {
        return currentIndex * 2 + 2;
    }

    protected int getParentNodeIndex(int currentIndex) {
        return currentIndex - 1 < 0 ? -1 : (currentIndex - 1) / 2;
    }

    protected boolean hasNode(int currentIndex) {
        return 0 <= currentIndex && currentIndex < tree.size() && null != tree.get(currentIndex);
    }

    protected boolean hasPrarent(int currentIndex) {
        return hasNode(getParentNodeIndex(currentIndex));
    }

    public ArrayList<T> getTree() {
        return tree;
    }

    public boolean isEmpty() {
        return tree.size() == 0;
    }

}
