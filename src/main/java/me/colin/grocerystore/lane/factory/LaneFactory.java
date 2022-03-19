package me.colin.grocerystore.lane.factory;

import me.colin.grocerystore.lane.Lane;

import java.util.Set;

public interface LaneFactory {

	/**
	 * Creates multiple lanes based off whatever implementation.
	 * @return set of lanes
	 */
	Set<Lane> createLanes();
}
