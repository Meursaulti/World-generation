package core.worldHelper;

import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;
import java.util.*;

public class CorridorTest {

	@Test
	public void testGenerator_createsCorrectNumberOfEdges() {
		List<Room> rooms = Arrays.asList(
				new Room(0, 0),
				new Room(2, 0),
				new Room(0, 2)
		);

		Corridor corridor = new Corridor();
		corridor.generator(rooms);

		assertThat(corridor.edgeList).hasSize(2); // 3 个房间 → 2 条边
		assertThat(corridor.unionUF.count()).isEqualTo(1); // 全连通
	}

	@Test
	public void testGenerator_handlesSingleRoom() {
		List<Room> rooms = List.of(new Room(0, 0));
		Corridor corridor = new Corridor();
		corridor.generator(rooms);
		assertThat(corridor.edgeList).isEmpty();
	}

	@Test
	public void testGenerator_handlesEmptyRoomList() {
		Corridor corridor = new Corridor();
		corridor.generator(List.of());
		assertThat(corridor.edgeList).isEmpty();
	}

	@Test
	public void testGenerator_connectsClosestRoomsFirst() {
		Room r1 = new Room(0, 0);
		Room r2 = new Room(1, 0);      // 距离 r1 为 1
		Room r3 = new Room(10, 10);    // 距离较远

		Corridor corridor = new Corridor();
		corridor.generator(Arrays.asList(r1, r2, r3));

		assertThat(corridor.edgeList).hasSize(2);

		// 检查 (r1, r2) 这条边是否存在
		boolean hasClosestEdge = corridor.edgeList.stream().anyMatch(edge ->
				(edge.getFrom() == r1 && edge.getTo() == r2) ||
						(edge.getFrom() == r2 && edge.getTo() == r1)
		);
		assertThat(hasClosestEdge).isTrue();
	}
}