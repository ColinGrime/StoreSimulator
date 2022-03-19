//package me.colin.grocerystore.lane;
//
//import me.colin.grocerystore.customer.RegularCustomer;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class LaneTest {
//
////	@Test
////	public void testStandardLaneCheckout() {
////		Lane lane = new Lane();
////		lane.add(new RegularCustomer(null, 1.0, 10, 0.5));
////		lane.add(new RegularCustomer(null, 1.5, 7, 0.9));
////
////		assertEquals(2.50, lane.checkout());
////		assertEquals(2.35, lane.checkout());
////	}
////
////	@Test
////	public void testExpressLaneCheckout() {
////		Lane lane = new Lane(LaneType.Express);
////		lane.add(new RegularCustomer(null, 1.0, 10, 0.5));
////		lane.add(new RegularCustomer(null, 1.5, 7, 0.9));
////
////		assertEquals(2.00, lane.checkout());
////		assertEquals(1.70, lane.checkout());
////	}
//
//	@Test
//	public void testRegularLaneUsability() {
//		Lane lane = new Lane();
//		assertTrue(lane.isUsable(new RegularCustomer(null, 1.0, 14, 0.5)));
//	}
//
//	@Test
//	public void testExpressLaneUsability() {
//		Lane lane = new Lane(LaneType.Express);
//		assertTrue(lane.isUsable(new RegularCustomer(null, 1.0, 11, 0.5)));
//		assertTrue(lane.isUsable(new RegularCustomer(null, 1.0, 12, 0.5)));
//		assertFalse(lane.isUsable(new RegularCustomer(null, 1.0, 13, 0.5)));
//	}
//}