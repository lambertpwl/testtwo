package caci.order.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

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
    		logger.debug("START createOrder(noOfBricks=" + noOfBricks + ")");
    	}
    	
    	Order order = orderManager.createOrder(noOfBricks);
    	
		if (logger.isDebugEnabled()) {
			logger.debug("END createOrder(orderReference=" + order.getOrderReference() + ")");
		}
		return order.getOrderReference();
    }
    
	@RequestMapping(method = RequestMethod.POST, value = "/updateOrder")
	@ResponseBody
	public int updateOrder(@RequestParam("orderReference") int orderReference, @RequestParam("noOfBricks") int noOfBricks) {
		if (logger.isDebugEnabled()) {
			logger.debug("START updateOrder(orderReference=" + orderReference + ", noOfBricks=" + noOfBricks + ")");
		}
		
		boolean orderUpdated = orderManager.updateOrder(orderReference, noOfBricks);
		
		if (!orderUpdated) {
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
		} 

		if (logger.isDebugEnabled()) {
			logger.debug("END updateOrder()");
		}
		return orderReference;
	} 
	
	@RequestMapping(method = RequestMethod.POST, value = "/fulfilOrder")
	@ResponseBody
	public void fulfilOrder(@RequestParam("orderReference") int orderReference) {
		if (logger.isDebugEnabled()) {
			logger.debug("START fulfilOrder(orderReference=" + orderReference + ")");
		}
		
		boolean orderFulfilled = orderManager.fulfilOrder(orderReference);
		
		if (logger.isDebugEnabled()) {
			logger.debug("END fulfilOrder(orderFulfilled=" + orderFulfilled + ")");
		}
		if (!orderFulfilled) {
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
		}
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
	
	@ExceptionHandler({HttpServerErrorException.class, NullPointerException.class})
	void handleBadRequests(HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.BAD_REQUEST.value());
	}
}