package caci.bean;

public class Order {
	public static final String ORDER_STATE_CREATED		= "created";
	public static final String ORDER_STATE_DISPATCHED 	= "dispatched";
	
	private Integer orderReference;
	private Integer noOfBricks;
	private String state;
	
	public Order(int orderReference, int noOfBricks) {
		setOrderReference(orderReference);
		setNoOfBricks(noOfBricks);
		setState(ORDER_STATE_CREATED);
	}
	
	public void setNoOfBricks(int noOfBricks) {
		this.noOfBricks = noOfBricks;
	}
	
	public int getNoOfBricks() {
		return this.noOfBricks;
	}
	
	private void setOrderReference(int orderReference) {
		this.orderReference = orderReference;
	}
	
	public int getOrderReference() {
		return this.orderReference;
	}

	public void fulfilOrder() {
		setState(ORDER_STATE_DISPATCHED);
	}
	
	private void setState(String state) {
		this.state = state;
	}
	
	public String getState() {
		return this.state;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (orderReference == null) {
			if (other.orderReference != null)
				return false;
		} else if (!orderReference.equals(other.orderReference))
			return false;
		return true;
	}

	public boolean isFulfilled() {
		return (getState().equals(ORDER_STATE_DISPATCHED) ? true : false);
	}
	
}