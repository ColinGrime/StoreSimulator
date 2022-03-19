//package me.colin.grocerystore;
//
//import me.colin.grocerystore.customer.Customer;
//import me.colin.grocerystore.customer.RegularCustomer;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CustomerTest {
//
//	@Test
//	public void testShoppingTime() {
//		Customer customer1 = new RegularCustomer(null, 1.0, 10, 7.00);
//		Customer customer2 = new RegularCustomer(null, 7.3, 13, 0.35);
//		assertEquals(70, customer1.getShoppingTime());
//		assertEquals(4.55, customer2.getShoppingTime());
//	}
//
//	@Test
//	public void testCheckoutReadyTime() {
//		Customer customer1 = new RegularCustomer(null, 1.0, 10, 7.00);
//		Customer customer2 = new RegularCustomer(null, 7.3, 13, 0.35);
//		assertEquals(71, customer1.getEndShoppingTime());
//		assertEquals(11.85, customer2.getEndShoppingTime());
//	}
//
//	@Test
//	public void testCheckoutSelection() {
////		GroceryStore store = new GroceryStore(12, 2, 1);
////		Customer customer1 = new RegularCustomer(store, 1.0, 5, 0.05);
////		Customer customer2 = new RegularCustomer(store, 1.5, 10, 0.50);
////		Customer customer3 = new RegularCustomer(store, 2.0, 13, 0.85);
////
////		Lane lane1 = customer1.pickBestLane();
////		Lane lane2 = customer2.pickBestLane();
////		Lane lane3 = customer3.pickBestLane();
////
////		assertEquals(LaneType.Express, lane1.getType());
////		assertEquals(LaneType.Regular, lane2.getType());
////		assertEquals(LaneType.Regular, lane3.getType());
//	}
//}