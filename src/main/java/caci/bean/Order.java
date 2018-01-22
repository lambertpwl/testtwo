package caci.bean;

public class Order {
	private Integer orderReference;
	private Integer noOfBricks;
	
	public Order(int orderReference, int noOfBricks) {
		setOrderReference(orderReference);
		setNoOfBricks(noOfBricks);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderReference == null) ? 0 : orderReference.hashCode());
		return result;
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
	
}