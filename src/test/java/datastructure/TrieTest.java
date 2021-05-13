package datastructure;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TrieTest {

    private Trie trie;

    @BeforeEach
    public void setup() {
        trie = new Trie();
        trie.insert("abc");
        trie.insert("adc");
        trie.insert("kiss");
        trie.insert("love");
        trie.insert("like");
        trie.insert("friend");
        trie.insert("friendd");
        trie.insert("frienddd");
    }

    @Test
    @DisplayName("Trie 생성 확인")
    public void insertTest() {
        assertThat(trie.getRoot().getChild('a').childSize()).isEqualTo(2);
        assertThat(trie.getRoot().getChild('k').childSize()).isEqualTo(1);
        assertThat(trie.getRoot().getChild('l').childSize()).isEqualTo(2);
        assertThat(trie.getRoot().getChild('f').childSize()).isEqualTo(1);
        assertThat(trie.getRoot().getChild('d')).isEqualTo(null);
    }

    @Test
    @DisplayName("contains 테스트")
    public void containsTest() {
        assertThat(trie.contains("abc")).isEqualTo(true);
        assertThat(trie.contains("abcd")).isEqualTo(false);
        assertThat(trie.contains("kiss")).isEqualTo(true);
        assertThat(trie.contains("love")).isEqualTo(true);
        assertThat(trie.contains("like")).isEqualTo(true);
        assertThat(trie.contains("friend")).isEqualTo(true);
        assertThat(trie.contains("friends")).isEqualTo(false);
        assertThat(trie.contains("friendd")).isEqualTo(true);
        assertThat(trie.contains("frienddd")).isEqualTo(true);
    }

    @Test
    @DisplayName("remove 테스트")
    public void remoceTest() {
        assertThat(trie.contains("frienddd")).isEqualTo(true);
        trie.remove("friendd");
        assertThat(trie.contains("frienddd")).isEqualTo(false);
        assertThat(trie.contains("friendd")).isEqualTo(true);
        assertThat(trie.contains("friend")).isEqualTo(true);
        assertThat(trie.contains("friends")).isEqualTo(true);

    }
}
