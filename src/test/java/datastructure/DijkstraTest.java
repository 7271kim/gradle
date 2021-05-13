package datastructure;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DijkstraTest {

    private GrapWithListDijkstra graphWithList;
    private HashMap<String, Integer> stores;

    @BeforeEach
    public void setup() {
        stores = new HashMap<String, Integer>();
        stores.put("A", 1);
        stores.put("B", 2);
        stores.put("C", 3);
        stores.put("D", 4);
        stores.put("E", 5);
        stores.put("F", 6);
        stores.put("G", 7);

        graphWithList = new GrapWithListDijkstra(stores.size());
        graphWithList.addDirection(stores.get("A"), stores.get("B"), 10);
        graphWithList.addDirection(stores.get("A"), stores.get("C"), 30);
        graphWithList.addDirection(stores.get("A"), stores.get("D"), 15);
        graphWithList.addDirection(stores.get("B"), stores.get("E"), 20);
        graphWithList.addDirection(stores.get("C"), stores.get("F"), 5);
        graphWithList.addDirection(stores.get("D"), stores.get("C"), 5);
        graphWithList.add(stores.get("D"), stores.get("F"), 20);
        graphWithList.addDirection(stores.get("E"), stores.get("F"), 20);

    }

    @Test
    @DisplayName("다익스트라 테스트")
    public void djTest() {
        MinMaxGraphResult result = graphWithList.dijkstraMin(stores.get("A"), stores.get("F"));
        assertThat(result.getWeight()).isEqualTo(25);
        assertThat(result.getPathList()).isEqualTo(new ArrayList<>(Arrays.asList(1, 4, 3, 6)));

        result = graphWithList.dijkstraMin(stores.get("A"), stores.get("B"));
        assertThat(result.getWeight()).isEqualTo(10);
        assertThat(result.getPathList()).isEqualTo(new ArrayList<>(Arrays.asList(1, 2)));

        result = graphWithList.dijkstraMin(stores.get("A"), stores.get("C"));
        assertThat(result.getWeight()).isEqualTo(20);
        assertThat(result.getPathList()).isEqualTo(new ArrayList<>(Arrays.asList(1, 4, 3)));

        result = graphWithList.dijkstraMin(stores.get("A"), stores.get("D"));
        assertThat(result.getWeight()).isEqualTo(15);
        assertThat(result.getPathList()).isEqualTo(new ArrayList<>(Arrays.asList(1, 4)));

        result = graphWithList.dijkstraMin(stores.get("A"), stores.get("A"));
        assertThat(result.getWeight()).isEqualTo(0);
        assertThat(result.getPathList()).isEqualTo(new ArrayList<>(Arrays.asList(1)));

        result = graphWithList.dijkstraMin(stores.get("A"), stores.get("E"));
        assertThat(result.getWeight()).isEqualTo(30);
        assertThat(result.getPathList()).isEqualTo(new ArrayList<>(Arrays.asList(1, 2, 5)));

        result = graphWithList.dijkstraMin(stores.get("A"), stores.get("G"));
        assertThat(result.getWeight()).isEqualTo(Integer.MAX_VALUE);
        assertThat(result.getPathList()).isEqualTo(new ArrayList<>(Arrays.asList()));
    }
}
