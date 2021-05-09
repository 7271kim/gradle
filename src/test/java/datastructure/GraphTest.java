package datastructure;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GraphTest {

	private GrapWithList graphWithList;
	private HashMap<String, Integer> stores;

	@BeforeEach
	public void setup() {
		stores = new HashMap<String, Integer>();
		stores.put("엽떡", 1);
		stores.put("그떡", 2);
		stores.put("갑떡", 3);
		stores.put("십떡", 4);
		stores.put("호떡", 5);

		graphWithList = new GrapWithList(5);
		graphWithList.add(stores.get("엽떡"), stores.get("갑떡"), 2);
		graphWithList.addDirection(stores.get("그떡"), stores.get("엽떡"), 7);
		graphWithList.addDirection(stores.get("그떡"), stores.get("호떡"), 3);
		graphWithList.addDirection(stores.get("갑떡"), stores.get("그떡"), 9);
		graphWithList.add(stores.get("갑떡"), stores.get("십떡"), 4);

	}

	@Test
	@DisplayName("인접행렬로 가중치 그래프 구현")
	public void createGrapWithListTest() {
		assertThat(graphWithList.isConected(stores.get("엽떡"), stores.get("갑떡"))).isEqualTo(true);
		assertThat(graphWithList.isConected(stores.get("엽떡"), stores.get("그떡"))).isEqualTo(false);
		assertThat(graphWithList.isConected(stores.get("그떡"), stores.get("갑떡"))).isEqualTo(false);
		assertThat(graphWithList.isConected(stores.get("갑떡"), stores.get("그떡"))).isEqualTo(true);
		assertThat(graphWithList.isConected(stores.get("그떡"), stores.get("호떡"))).isEqualTo(true);
	}

	@Test
	@DisplayName("인접행렬 dfs Test")
	public void dfsGrapWithListTest() {
		assertThat(graphWithList.dfs(stores.get("엽떡"))).isEqualTo(new ArrayList<>(Arrays.asList(1, 3, 2, 5, 4)));
		assertThat(graphWithList.dfs(stores.get("그떡"))).isEqualTo(new ArrayList<>(Arrays.asList(2, 1, 3, 4, 5)));
	}

	@Test
	@DisplayName("인접행렬 bfs Test")
	public void bfsGrapWithListTest() {
		assertThat(graphWithList.bfs(stores.get("엽떡"))).isEqualTo(new ArrayList<>(Arrays.asList(1, 3, 2, 4, 5)));
		assertThat(graphWithList.bfs(stores.get("호떡"))).isEqualTo(new ArrayList<>(Arrays.asList(5)));
		assertThat(graphWithList.bfs(stores.get("그떡"))).isEqualTo(new ArrayList<>(Arrays.asList(2, 1, 5, 3, 4)));
	}
}
