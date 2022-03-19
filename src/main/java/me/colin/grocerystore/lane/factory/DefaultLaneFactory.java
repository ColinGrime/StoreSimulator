package me.colin.grocerystore.lane.factory;

import me.colin.grocerystore.lane.Lane;
import me.colin.grocerystore.lane.LaneType;

import java.util.HashSet;
import java.util.Set;

public class DefaultLaneFactory implements LaneFactory {

	private final int regularAmount;
	private final int expressAmount;
	private final int closedAmount;

	public DefaultLaneFactory(int maxLanes) {
		this(maxLanes, 0, 0);
	}

	public DefaultLaneFactory(int maxLanes, int regularAmount) {
		this(maxLanes, regularAmount, 0);
	}

	public DefaultLaneFactory(int maxLanes, int regularAmount, int expressAmount) {
		this.regularAmount = regularAmount;
		this.expressAmount = expressAmount;
		this.closedAmount = maxLanes - regularAmount - expressAmount;
	}

	@Override
	public Set<Lane> createLanes() {
		Set<Lane> lanes = new HashSet<>();

		// adds regular lanes
		for (int i=0; i<regularAmount; i++) {
			lanes.add(new Lane());
		}

		// adds express lanes
		for (int i=0; i<expressAmount; i++) {
			lanes.add(new Lane(LaneType.Express));
		}

		// adds closed lanes
		for (int i=0; i<closedAmount; i++) {
			lanes.add(new Lane(LaneType.Closed));
		}

		return lanes;
	}
}
