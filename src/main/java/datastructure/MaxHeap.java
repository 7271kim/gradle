package datastructure;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap<T extends Comparable<T>> extends Tree<T> {

    private static final int ROOT_INDEX = 0;

    @Override
    public void add(T input) {
        this.tree.add(input);
        checkParent(tree.size() - 1);
    }

    public List<T> getSortedList() {
        List<T> result = new ArrayList<>();
        extractRoot(result);
        return result;
    }

    private void checkParent(int currentIndex) {
        if (hasPrarent(currentIndex)) {
            T currentData = tree.get(currentIndex);
            T parentData = tree.get(getParentNodeIndex(currentIndex));
            if (currentData.compareTo(parentData) == 1) {
                swapNode(currentIndex, getParentNodeIndex(currentIndex));
                checkParent(getParentNodeIndex(currentIndex));
            }
        }
    }

    private void swapNode(int beforeIndex, int afterIndex) {
        T beforeData = tree.get(beforeIndex);
        T afterData = tree.get(afterIndex);
        tree.set(beforeIndex, afterData);
        tree.set(afterIndex, beforeData);
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
        IndexAndValue<T> max = getMaxIndexFromRootWithRightWithLeft(rootIndex);
        swapRootAndMax(rootIndex, max.getIndex());
    }

    private void swapRootAndMax(int rootIndex, int maxIndex) {
        if (maxIndex != rootIndex) {
            swapNode(rootIndex, maxIndex);
            checkRootAndChild(maxIndex);
        }
    }

    private IndexAndValue<T> getMaxIndexFromRootWithRightWithLeft(int rootIndex) {

        IndexAndValue<T> max = new IndexAndValue<>(tree.get(rootIndex), rootIndex);
        getMaxFromLeft(rootIndex, max);
        getMaxFromRight(rootIndex, max);

        return max;
    }

    private void getMaxFromRight(int rootIndex, IndexAndValue<T> max) {
        if (hasRightNode(rootIndex)) {
            int rightIndex = getRightNodeIndex(rootIndex);
            T rightValue = tree.get(rightIndex);
            if (rightValue.compareTo(max.getValue()) == 1) {
                max.setValue(rightValue);
                max.setIndex(rightIndex);
            }
        }
    }

    private void getMaxFromLeft(int rootIndex, IndexAndValue<T> max) {
        if (hasLeftNode(rootIndex)) {
            int leftIndex = getLeftNodeIndex(rootIndex);
            T leftValue = tree.get(leftIndex);
            if (leftValue.compareTo(max.getValue()) == 1) {
                max.setValue(leftValue);
                max.setIndex(leftIndex);
            }
        }
    }

    private T removeRoot() {
        return tree.remove(tree.size() - 1);
    }

    private void swapRootAndLastChild(List<T> result) {
        swapNode(ROOT_INDEX, tree.size() - 1);
    }

    public static class IndexAndValue<T> {
        private T value;
        private int index;

        public IndexAndValue(T value, int index) {
            this.value = value;
            this.index = index;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

    }
}
