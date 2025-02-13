Online Store Shopping Cart Calculator

This web service (WS) is designed to calculate the total cost of a shopping cart for customers of an online store that sells Premium phones, mid-range phones, and laptops. The service supports two types of customers: individual customers and professional customers. The pricing varies based on the customer type and, for professional customers, their annual revenue.

Table of Contents

    Overview

    Customer Types

    Product Pricing

    API Endpoint

    Request Payload

    Response

    Examples

    How to Run

Overview

The web service calculates the total cost of a customer's shopping cart based on the following:

    The type of customer (individual or professional).

    The products in the cart (premium phones, mid-range phones, or laptops).

    The quantity of each product.

    For professional customers, their annual revenue determines the pricing tier.

Customer Types

The service supports two types of customers:

1. Individual Customers

    Identified by:

        id: Unique identifier for the customer.

        firstName: First name of the customer.

        lastName: Last name of the customer.

2. Professional Customers

    Identified by:

        id: Unique identifier for the customer.

        companyName: Company name.

        siren: SIREN number (required).

        vatNumber: VAT number (optional).

        annualTurnover: Annual revenue (required).

        type: Must be "professional".

Product Pricing

The pricing for products depends on the customer type and, for professional customers, their annual revenue.
Individual Customers

    Premium phone: €1500

    Mid-range phone: €800

    Laptop: €1200

Professional Customers

    Annual revenue > €10 million:

        Premium phone: €1000

        Mid-range phone: €550

        Laptop: €900

    Annual revenue ≤ €10 million:

        Premium phone: €1150

        Mid-range phone: €600

        Laptop: €1000

API Endpoint

Calculate Shopping Cart Total

    Endpoint: POST /api/cart/calculate

    Description: Calculates the total cost of the shopping cart for a given customer and their selected products.

    Request Body: JSON payload containing customer details and product list.

    Response: total cost of the cart.

Request Payload

The request payload must include the customer details and the list of products in the cart.


Examples

Example 1: Individual Customer

Request:

{
  "client": {
    "id": 1,
    "firstName": "Jihed",
    "lastName": "Mechergui",
    "type": "individual"
  },
  "products": [
    { "type": "MID_RANGE_PHONE", "quantity": 2 },
    { "type": "LAPTOP", "quantity": 1 }
  ]
}

Response:
 2800

Example 2: Professional Customer (Revenue > €10 million)

Request:

{
  "client": {
    "id": 2,
    "vatNumber":"55",
    "companyName": "Capco",
    "siren": "123456789",
    "annualTurnover": 15000000,
    "type": "professional"
  },
  "products": [
    { "type": "LAPTOP", "quantity": 1 }
  ]
}
Response: 900


How to Run

    Clone the repository.

    Install dependencies using mvn install (for Maven)

    Run the application using mvn spring-boot:run.

    execute the following cmd 
    curl --location '127.0.0.1:8080/api/cart/calculate' \
	--header 'Content-Type: application/json' \
	--data '{
	  "client": {
	    "id": 2,
	    "vatNumber":"55",
	    "companyName": "Capco",
	    "siren": "123456789",
	    "annualTurnover": 15000,
	    "type": "professional"
	  },
	  "products": [
	    { "type": "LAPTOP", "quantity": 1 }
	  ]
	}'

