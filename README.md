# CACI Tech Test
Tech Test submission for CACI

CREATE ORDER (POST) - Creates an order provided parameter noOfBricks

http://localhost:8080/createOrder?noOfBricks=100

UPDATE ORDER (POST) - Updates the number of bricks on an existing order. Parameters are orderReference (order to update) and noOfBricks (number of bricks required on order). If an order reference is provided and not found then a BAD_REQUEST response is returned.

http://localhost:8080/updateOrder?orderReference=1&noOfBricks=50

FULFIL ORDER (POST) - Dispatches an order. Required parameter orderReference (order to fulfil). If the order is not found then a BAD_REQUEST response is returned.

http://localhost:8080/fulfilOrder?orderReference=1

RETRIEVE ORDER (GET) - Retrieves the order details for the provided order reference. Parameters are orderReference. If the order is not found or the order reference is invalid then nothing is returned.

http://localhost:8080/retrieveOrder?orderReference=1

RETRIEVE ORDERS (GET) - Retrieves all the orders in the system. No parameters required.

http://localhost:8080/retrieveOrders

# Execute from Command Line

java -jar target/CACITechTest-0.1.0.jar
