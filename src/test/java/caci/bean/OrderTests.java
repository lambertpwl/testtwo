package caci.bean;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class OrderTests {

	@Test
	public void testOrder() {
		Order order = new Order(1,10);
		
		assertThat(order.getOrderReference(), is(1));
		assertThat(order.getNoOfBricks(), is(10));
	}

	@Test
	public void testGetAndSetNoOfBricks() {
		Order order = new Order(1,10);
		order.setNoOfBricks(20);
		
		assertThat(order.getNoOfBricks(), is(20));
	}
	
	@Test
	public void testGetOrderReference() {
		Order order = new Order(1,10);
		
		assertThat(order.getOrderReference(), is(1));
	}
	
	@Test
	public void testGetState() {
		Order order = new Order(1, 10);
		
		assertThat(order.getState(), is(Order.ORDER_STATE_CREATED));
	}
	
	@Test
	public void testFulfilOrder() {
		Order order = new Order(1, 10);
		
		order.fulfilOrder();
		
		assertThat(order.getState(), is(Order.ORDER_STATE_DISPATCHED));
	}
	
	@Test
	public void testIsFulfilledFalse() {
		Order order = new Order(1, 10);
		
		assertThat(order.getState(), is(Order.ORDER_STATE_CREATED));
		assertThat(order.isFulfilled(), is(false));
	}
	
	@Test
	public void testIsFulfilledTrue() {
		Order order = new Order(1, 10);
		
		order.fulfilOrder();
		
		assertThat(order.getState(), is(Order.ORDER_STATE_DISPATCHED));
		assertThat(order.isFulfilled(), is(true));
	}
}