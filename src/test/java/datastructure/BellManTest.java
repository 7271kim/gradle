package datastructure;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BellManTest {

    private GrapWithListBellman graphWithList;
    private HashMap<String, Integer> stores;

    @Test
    @DisplayName("벨만 포드 테스트")
    public void bellmanTest() {
        stores = new HashMap<String, Integer>();
        stores.put("A", 1);
        stores.put("B", 2);
        stores.put("C", 3);
        stores.put("D", 4);
        stores.put("E", 5);
        stores.put("F", 6);
        stores.put("G", 7);

        graphWithList = new GrapWithListBellman(stores.size());
        graphWithList.addDirection(stores.get("A"), stores.get("B"), 10);
        graphWithList.addDirection(stores.get("A"), stores.get("C"), 30);
        graphWithList.addDirection(stores.get("A"), stores.get("D"), 15);
        graphWithList.addDirection(stores.get("B"), stores.get("E"), 20);
        graphWithList.addDirection(stores.get("C"), stores.get("F"), 5);
        graphWithList.addDirection(stores.get("D"), stores.get("C"), 5);
        graphWithList.add(stores.get("D"), stores.get("F"), 20);
        graphWithList.addDirection(stores.get("E"), stores.get("F"), 20);

        MinMaxGraphResult result = graphWithList.bellmanMin(stores.get("A"), stores.get("F"));
        assertThat(result.getWeight()).isEqualTo(25);
        assertThat(result.getPathList()).isEqualTo(new ArrayList<>(Arrays.asList(1, 4, 3, 6)));

        result = graphWithList.bellmanMin(stores.get("A"), stores.get("B"));
        assertThat(result.getWeight()).isEqualTo(10);
        assertThat(result.getPathList()).isEqualTo(new ArrayList<>(Arrays.asList(1, 2)));

        result = graphWithList.bellmanMin(stores.get("A"), stores.get("C"));
        assertThat(result.getWeight()).isEqualTo(20);
        assertThat(result.getPathList()).isEqualTo(new ArrayList<>(Arrays.asList(1, 4, 3)));

        result = graphWithList.bellmanMin(stores.get("A"), stores.get("D"));
        assertThat(result.getWeight()).isEqualTo(15);
        assertThat(result.getPathList()).isEqualTo(new ArrayList<>(Arrays.asList(1, 4)));

        result = graphWithList.bellmanMin(stores.get("A"), stores.get("A"));
        assertThat(result.getWeight()).isEqualTo(0);
        assertThat(result.getPathList()).isEqualTo(new ArrayList<>(Arrays.asList(1)));

        result = graphWithList.bellmanMin(stores.get("A"), stores.get("E"));
        assertThat(result.getWeight()).isEqualTo(30);
        assertThat(result.getPathList()).isEqualTo(new ArrayList<>(Arrays.asList(1, 2, 5)));

        result = graphWithList.bellmanMin(stores.get("A"), stores.get("G"));
        assertThat(result.getWeight()).isEqualTo(Integer.MAX_VALUE);
        assertThat(result.getPathList()).isEqualTo(new ArrayList<>(Arrays.asList()));
    }

    @Test
    @DisplayName("벨만 포드 음수 사이클 테스트")
    public void bellmanMinusCycleTest() {
        stores = new HashMap<String, Integer>();
        stores.put("A", 1);
        stores.put("B", 2);
        stores.put("C", 3);
        stores.put("D", 4);
        stores.put("E", 5);
        stores.put("F", 6);
        stores.put("G", 7);
        stores.put("H", 8);

        graphWithList = new GrapWithListBellman(stores.size());
        graphWithList.addDirection(stores.get("A"), stores.get("B"), 3);
        graphWithList.addDirection(stores.get("A"), stores.get("C"), 5);
        graphWithList.addDirection(stores.get("A"), stores.get("D"), 2);
        graphWithList.addDirection(stores.get("B"), stores.get("E"), -4);
        graphWithList.addDirection(stores.get("C"), stores.get("F"), 6);
        graphWithList.addDirection(stores.get("F"), stores.get("C"), -3);
        graphWithList.addDirection(stores.get("D"), stores.get("G"), 3);
        graphWithList.addDirection(stores.get("G"), stores.get("D"), -6);
        graphWithList.addDirection(stores.get("E"), stores.get("H"), 4);
        graphWithList.addDirection(stores.get("F"), stores.get("H"), 8);
        graphWithList.addDirection(stores.get("G"), stores.get("H"), 7);

        MinMaxGraphResult result = graphWithList.bellmanMin(stores.get("A"), stores.get("E"));
        assertThat(result.getWeight()).isEqualTo(Integer.MAX_VALUE);
        assertThat(result.getPathList()).isEqualTo(new ArrayList<>(Arrays.asList()));
    }

    @Test
    @DisplayName("벨만 포드 음수 테스트")
    public void bellmanMinusTest() {
        stores = new HashMap<String, Integer>();
        stores.put("A", 1);
        stores.put("B", 2);
        stores.put("C", 3);
        stores.put("D", 4);
        stores.put("E", 5);
        stores.put("F", 6);
        stores.put("G", 7);
        stores.put("H", 8);

        graphWithList = new GrapWithListBellman(stores.size());
        graphWithList.addDirection(stores.get("A"), stores.get("B"), 3);
        graphWithList.addDirection(stores.get("A"), stores.get("C"), 5);
        graphWithList.addDirection(stores.get("A"), stores.get("D"), 2);
        graphWithList.addDirection(stores.get("B"), stores.get("E"), -4);
        graphWithList.addDirection(stores.get("C"), stores.get("F"), 6);
        graphWithList.addDirection(stores.get("F"), stores.get("C"), -3);
        graphWithList.addDirection(stores.get("D"), stores.get("G"), 3);
        graphWithList.addDirection(stores.get("G"), stores.get("D"), 6);
        graphWithList.addDirection(stores.get("E"), stores.get("H"), 4);
        graphWithList.addDirection(stores.get("F"), stores.get("H"), 8);
        graphWithList.addDirection(stores.get("G"), stores.get("H"), 7);

        MinMaxGraphResult result = graphWithList.bellmanMin(stores.get("A"), stores.get("H"));
        assertThat(result.getWeight()).isEqualTo(3);
        assertThat(result.getPathList()).isEqualTo(new ArrayList<>(Arrays.asList(1, 2, 5, 8)));
    }
}
