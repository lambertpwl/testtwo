package caci.order.manager;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

import caci.bean.Order;

public class OrderManagerTests {

	@Test
	public void testOrderManager() {
		OrderManager orderManager = new OrderManager();
		
		assertThat(orderManager.retrieveOrders().size(), is(0));
	}
	
	@Test
	public void testCreateOrder() {
		OrderManager orderManager = new OrderManager();
		Order order = orderManager.createOrder(20);
		
		assertThat(orderManager.retrieveOrders().size(), is(1));
		assertThat(orderManager.retrieveOrders().contains(order), is(true));
	}

	@Test
	public void testUpdateOrder() {
		OrderManager orderManager = new OrderManager();
		Order order = orderManager.createOrder(20);
		
		boolean orderUpdated = orderManager.updateOrder(order.getOrderReference(), 30);
		
		assertThat(orderManager.retrieveOrders().size(), is(1));
		assertThat(orderManager.retrieveOrders().get(0).getNoOfBricks(), is(order.getNoOfBricks()));
		assertThat(orderManager.retrieveOrders().get(0).getNoOfBricks(), is(30));
		assertThat(orderUpdated, is(true));
	}

	@Test
	public void testUpdateOrderFailure() {
		OrderManager orderManager = new OrderManager();
		Order order = orderManager.createOrder(20);
		
		boolean orderUpdated = orderManager.updateOrder(2, 30);
		
		assertThat(orderManager.retrieveOrders().size(), is(1));
		assertThat(orderManager.retrieveOrders().get(0).getNoOfBricks(), is(order.getNoOfBricks()));
		assertThat(orderUpdated, is(false));
	}


	@Test
	public void testRetrieveOrders() {
		OrderManager orderManager = new OrderManager();
		Order order1 = orderManager.createOrder(20);
		Order order2 = orderManager.createOrder(30);
		
		assertThat(orderManager.retrieveOrders().size(), is(2));
		assertThat(orderManager.retrieveOrders().contains(order1), is(true));
		assertThat(orderManager.retrieveOrders().contains(order2), is(true));
	}

	@Test
	public void testRetrieveOrder() {
		OrderManager orderManager = new OrderManager();
		Order order1 = orderManager.createOrder(20);
		
		assertThat(orderManager.retrieveOrder(order1.getOrderReference()), is(order1));
	}
	
	@Test
	public void testFulfilOrder() {
		OrderManager orderManager = new OrderManager();
		Order order = orderManager.createOrder(20);
		
		orderManager.fulfilOrder(order.getOrderReference());
		
		assertThat(orderManager.retrieveOrder(order.getOrderReference()).getState(), is(Order.ORDER_STATE_DISPATCHED));
	}
}
