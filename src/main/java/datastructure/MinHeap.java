package datastructure;

import java.util.ArrayList;
import java.util.List;

import datastructure.MaxHeap.IndexAndValue;

public class MinHeap<T extends Comparable<T>> extends Tree<T> {

    private static final int ROOT_INDEX = 0;

    @Override
    public void add(T input) {
        this.tree.add(input);
        checkParent(tree.size() - 1);
    }

    private void checkParent(int currentIndex) {
        if (hasPrarent(currentIndex)) {
            T currentData = tree.get(currentIndex);
            T parentData = tree.get(getParentNodeIndex(currentIndex));
            if (parentData.compareTo(currentData) == 1) {
                swapNode(currentIndex, getParentNodeIndex(currentIndex));
                checkParent(getParentNodeIndex(currentIndex));
            }
        }
    }

    public List<T> getSortedList() {
        List<T> result = new ArrayList<>();
        extractRoot(result);
        return result;
    }

    private void extractRoot(List<T> result) {
        while (!isEmpty()) {
            swapRootAndLastChild(result);
            T rootData = removeRoot();
            result.add(rootData);
            if (!isEmpty()) {
                checkRootAndChild(ROOT_INDEX);
            }
        }
    }

    private void checkRootAndChild(int rootIndex) {
        IndexAndValue<T> min = getMinIndexFromRootWithRightWithLeft(rootIndex);
        swapRootAndMin(rootIndex, min.getIndex());
    }

    private void swapRootAndMin(int rootIndex, int minIndex) {
        if (minIndex != rootIndex) {
            swapNode(rootIndex, minIndex);
            checkRootAndChild(minIndex);
        }
    }

    private IndexAndValue<T> getMinIndexFromRootWithRightWithLeft(int rootIndex) {
        IndexAndValue<T> min = new IndexAndValue<>(tree.get(rootIndex), rootIndex);
        getMinFromLeft(rootIndex, min);
        getMinFromRight(rootIndex, min);

        return min;
    }

    private void getMinFromRight(int rootIndex, IndexAndValue<T> min) {
        if (hasRightNode(rootIndex)) {
            int rightIndex = getRightNodeIndex(rootIndex);
            T rightValue = tree.get(rightIndex);
            if (min.getValue().compareTo(rightValue) == 1) {
                min.setValue(rightValue);
                min.setIndex(rightIndex);
            }
        }
    }

    private void getMinFromLeft(int rootIndex, IndexAndValue<T> min) {
        if (hasLeftNode(rootIndex)) {
            int leftIndex = getLeftNodeIndex(rootIndex);
            T leftValue = tree.get(leftIndex);
            if (min.getValue().compareTo(leftValue) == 1) {
                min.setValue(leftValue);
                min.setIndex(leftIndex);
            }
        }
    }

    private T removeRoot() {
        return tree.remove(tree.size() - 1);
    }

    private void swapRootAndLastChild(List<T> result) {
        swapNode(ROOT_INDEX, tree.size() - 1);
    }

}
