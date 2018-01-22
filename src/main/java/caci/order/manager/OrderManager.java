package caci.order.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import caci.bean.Order;

@Component
public class OrderManager {
	private static int orderReference = 1;
	private final ArrayList<Order> orders;
	
	public OrderManager() {
		orders = new ArrayList<>();
	}
	
	public Order createOrder(int noOfBricks) {
		Order order = new Order(orderReference++, noOfBricks);
		orders.add(order);
		
		return order;
	}
	
	public List<Order> retrieveOrders() {
		return (List<Order>) Collections.unmodifiableList(orders);
	}
	
	public Order retrieveOrder(int orderReference) {
		Order order = null;
		
		for (Order loopOrder : orders) {
			if (loopOrder.getOrderReference() == orderReference) {
				order = loopOrder;
			}
		}
		return order;
	}
	
	private void updateOrder(Order order) {
		if (orders.contains(order)) {
			orders.remove(order);
			orders.add(order);
		} else {
			orders.add(order);
		}
	}
	
	public Order updateOrder(int orderReference, int noOfBricks) {
		Order order = retrieveOrder(orderReference);
		if (order != null) {
			order.setNoOfBricks(noOfBricks);
			updateOrder(order);
		}
		return order;
	}
}