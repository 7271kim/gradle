package datastructure;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TreeWithChildrenTest {
    private TreeWithChildren<String> tree;

    @BeforeEach
    void setting() {
        tree = new TreeWithChildren<>();
        tree.add("1번");
        tree.add("2번");
        tree.add("3번");
        tree.add("4번");
        tree.add("5번");
        tree.add("6번");
        tree.add("7번");
        tree.add("8번");
        tree.add("9번");
        tree.setChild("1번", "2번");
        tree.setChild("1번", "3번");
        tree.setChild("3번", "4번");
        tree.setChild("3번", "5번");
        tree.setChild("3번", "6번");
        tree.setChild("4번", "7번");
        tree.setChild("4번", "8번");
        tree.setChild("6번", "9번");
    }

    @Test
    @DisplayName("Tree 테스트")
    void createTreeTest() {
        assertThat(tree.getChildren("1번").size()).isEqualTo(2);
        assertThat(tree.getChildren("2번").size()).isEqualTo(0);
        assertThat(tree.getChildren("3번").size()).isEqualTo(3);
        assertThat(tree.getChildren("4번").size()).isEqualTo(2);
        assertThat(tree.getChildren("6번").size()).isEqualTo(1);

    }
}
