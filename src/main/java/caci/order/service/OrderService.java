package caci.order.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import caci.bean.Order;
import caci.order.manager.OrderManager;

@RestController
@RequestMapping("/")
public class OrderService {

	final Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	private OrderManager orderManager;
	    
    @RequestMapping(method = RequestMethod.POST, value = "/createOrder")
    @ResponseBody
    public int createOrder(@RequestParam("noOfBricks") int noOfBricks) {
    	if (logger.isDebugEnabled()) {
    		logger.debug("START createOrder()");
    	}
    	
    	Order order = orderManager.createOrder(noOfBricks);
    	
		if (logger.isDebugEnabled()) {
			logger.debug("END createOrder()");
		}
		return order.getOrderReference();
    }
    
	@RequestMapping(method = RequestMethod.POST, value = "/updateOrder")
	@ResponseBody
	public int updateOrder(@RequestParam("orderReference") int orderReference, @RequestParam("noOfBricks") int noOfBricks) {
		if (logger.isDebugEnabled()) {
			logger.debug("START updateOrder()");
		}
		
		Order order = orderManager.updateOrder(orderReference, noOfBricks);
		
		if (logger.isDebugEnabled()) {
			logger.debug("END updateOrder()");
		}
		return order.getOrderReference();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/retrieveOrder")
	@ResponseBody
	public Order retrieveOrder(@RequestParam("orderReference") int orderReference) {
		if (logger.isDebugEnabled()) {
			logger.debug("START retrieveOrder()");
		}
		
		Order order = orderManager.retrieveOrder(orderReference);
		
		if (logger.isDebugEnabled()) {
			logger.debug("END retrieveOrder()");
		}
		return order;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/retrieveOrders")
	@ResponseBody
	public List<Order> retrieveOrders() {
		if (logger.isDebugEnabled()) {
			logger.debug("START retrieveOrders()");
		}
		
		List<Order> orders = orderManager.retrieveOrders();
		
		if (logger.isDebugEnabled()) {
			logger.debug("END retrieveOrders()");
		}
		return orders;
	}
}