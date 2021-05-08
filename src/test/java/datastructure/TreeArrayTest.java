package datastructure;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TreeArrayTest {

    private Tree<Integer> tree;

    @BeforeEach
    public void setUp() {
        tree = new Tree<>();
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(5);
        tree.add(6);
        tree.add(7);
        tree.add(8);
    }

    @Test
    @DisplayName("inorder 테스트")
    public void inorderTest() {
        List<Integer> inorder = tree.inorder();
        List<Integer> result = new ArrayList<>(Arrays.asList(8, 4, 2, 5, 1, 6, 3, 7));
        assertThat(inorder).isEqualTo(result);
    }

    @Test
    @DisplayName("preorder 테스트")
    public void preorderTest() {
        List<Integer> preorder = tree.preorder();
        List<Integer> result = new ArrayList<>(Arrays.asList(1, 2, 4, 8, 5, 3, 6, 7));
        assertThat(preorder).isEqualTo(result);
    }

    @Test
    @DisplayName("postorder 테스트")
    public void postorderTest() {
        List<Integer> postorder = tree.postorder();
        List<Integer> result = new ArrayList<>(Arrays.asList(8, 4, 5, 2, 6, 7, 3, 1));
        assertThat(postorder).isEqualTo(result);
    }
}
