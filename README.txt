The application has been built with the following criteria:
- The interaction with the backend will be via API calls (I used Postman)
- The main functionality is to add products to a cart
- When adding products, users can select the productId (which will be later translated into the product description)
 the cartId (if omitted, products will be hold on a cart with id 1) and also the amount (if omitted, increases by 1).
- Users can retrieve a cart by its id.
- Users can delete a cart by its id.
- There are two types (two ids) of products: Entry ticket and Cancelation insurance (ids 1 and 2).
- Carts are hold in cache for 10 minutes.
- Tests are performed where the main functionality lies, which is in the MainController and CartService.
- A DTO class determines the validity of the request when adding a product.

For achieving this end result, the following stack and dependencies were used:
- JDK 22
- Maven
- Spring Boot (Lombok, Spring Web, Spring boot dev tools)

To test this application, run the program on a machine with JDK 22 or above installed, 
which, by default, will expose the API on the port 8080 of your local machine.


API ENDPOINTS (find attached the POSTMAN collection):
-GET a cart by its id http://127.0.0.1:8080/cart/1
-POST a new product (which also creates a cart) http://127.0.0.1:8080/products
-DELETE a cart by its id http://127.0.0.1:8080/cart/2