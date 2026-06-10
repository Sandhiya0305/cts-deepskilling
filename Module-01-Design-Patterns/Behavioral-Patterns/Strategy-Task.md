# Task Description

Implement the Strategy pattern. Create a PaymentStrategy interface with pay(double amount) method. Create concrete strategies: CreditCardPayment (cardNumber, cvv, expiry), PayPalPayment (email, password), CryptoPayment (walletAddress). Create a ShoppingCart class that holds a list of items and accepts any PaymentStrategy at checkout. Demonstrate adding items to cart and checking out with different payment methods at runtime. Show how strategies are interchangeable and how new payment methods can be added without modifying ShoppingCart.
