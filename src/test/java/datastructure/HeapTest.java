package datastructure;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HeapTest {
    private MaxHeap<Integer> maxHeap;
    private MinHeap minHeap;

    @BeforeEach
    public void setMaxHeapUp() {
        maxHeap = new MaxHeap<>();
        maxHeap.add(3);
        maxHeap.add(1);
        maxHeap.add(2);
        maxHeap.add(5);
        maxHeap.add(4);
    }

    @BeforeEach
    public void setMinHeapUp() {
        minHeap = new MinHeap();
        minHeap.add(3);
        minHeap.add(1);
        minHeap.add(2);
        minHeap.add(5);
        minHeap.add(4);
    }

    @Test
    @DisplayName("MaxHeap 테스트")
    public void maxHeapTest() {
        List<Integer> result = new ArrayList<>(Arrays.asList(5, 4, 2, 1, 3));
        assertThat(maxHeap.getTree()).isEqualTo(result);
    }

    @Test
    @DisplayName("MaxHeap getSortedList테스트")
    public void getSortedListTest() {
        List<Integer> result = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1));
        assertThat(maxHeap.getSortedList()).isEqualTo(result);
    }
}
