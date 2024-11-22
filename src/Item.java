/**
 * This class represents an item in a vending machine with properties like price, stock, description, and
 * purchase information. It provides methods to restock, purchase, apply discounts, and manage the item's status
 * (discontinued and bestseller).
 */
class Item {
    double price;
    int stock;
    boolean discontinued;
    int purchaseCount;
    String description;
    double discount;
    boolean bestseller;

    /**
     * Constructor for the class with the specified price, stock amount, and description.
     * @param price the price of the item
     * @param numPieces the amount of the item in stock
     * @param description a description of the item
     */
    Item(double price, int numPieces, String description) {
        this.price = price;
        this.stock = numPieces;
        discontinued = false;
        purchaseCount = 0;
        this.description = description;
        discount = 0;
        bestseller = false;
    }

    /**
     * Constructor for the class with specified price and stock quantity. The description is set to a default value
     * of "Snack/Drink".
     * @param price the price of the item
     * @param numPieces the amount of the item in stock
     */
    Item(double price, int numPieces) {
        this.price = price;
        this.stock = numPieces;
        discontinued = false;
        this.purchaseCount = 0;
        this.description = "Snack/Drink";
        discount = 0;
        bestseller = false;
    }

    /**
     * Restocks the item by adding a specified amount to the current stock
     * @param amount the amount of the item to add to the stock
     */
    void restock(int amount) {
        this.stock = this.stock + amount;
    }

    /**
     * Purchases a specified amount of an item, decreasing the stock and increasing the purchase count.
     * If the stock is insufficient, the purchase won't go through.
     * @param amount the amount of the item to be purchased
     */
    void purchase(int amount) {
        if (this.stock >= amount) {
            this.stock = this.stock - amount;
            this.purchaseCount = this.purchaseCount + amount;
        }
    }

    /**
     * Gets the total number of times an item has been purchased.
     * @return the purchase count
     */
    int getPurchaseCount() {
        return this.purchaseCount;
    }

    /**
     * Gets the current stock of the item.
     * @return the amount of the item in stock
     */
    int getStock() {
        return this.stock;
    }

    /**
     * Determines whether the item is discontinued.
     * @return true if the item is discontinued, false otherwise
     */
    boolean isDiscontinued() {
        return discontinued;
    }

    /**
     * Sets the discontinued status for an item
     * @param discontinued the discontinued status to set for the item
     */
    void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    /**
     * Gets the description of an item
     * @return A string containing the item's description
     */
    String getDescription() {
        return description;
    }

    /**
     * Applies a discount between 0 and 100 (inclusive) to the item.
     * @param discount the discount percentage to apply
     */
    void applyDiscount(double discount) {
        if (discount >= 0 && discount <= 100) {
            this.discount = discount;
        }
    }

    /**
     * Gets the price of the item after applying a discount
     * @return the discounted price of the item
     */
    double getDiscountedPrice() {
        return price * (1 - discount / 100);
    }

    /**
     * Determines whether an item is a bestseller.
     * @return true if the item is a bestseller, false otherwise
     */
    boolean isBestseller() {
        return  bestseller;
    }

    /**
     * Sets the bestseller status of the item
     * @param bestseller the bestseller status to set for the item
     */
    void setBestseller(boolean bestseller) {
        this.bestseller = bestseller;
    }
}