class Item {
    double price;
    int stock;
    boolean discontinued;
    int purchaseCount;
    String description;
    double discount;

    Item(double price, int numPieces, String description) {
        this.price = price;
        this.stock = numPieces;
        discontinued = false;
        purchaseCount = 0;
        this.description = description;
        discount = 0;
    }

    Item(double price, int numPieces) {
        this.price = price;
        this.stock = numPieces;
        discontinued = false;
        this.purchaseCount = 0;
        this.description = "Snack/Drink";
        discount = 0;
    }

    void restock(int amount) {
        this.stock = this.stock + amount;
    }

    void purchase(int amount) {
        if (this.stock >= amount) {
            this.stock = this.stock - amount;
            this.purchaseCount = this.purchaseCount + amount;
        }
    }

    int getPurchaseCount() {
        return this.purchaseCount;
    }

    int getStock() {
        return this.stock;
    }

    boolean isDiscontinued() {
        return discontinued;
    }

    void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    String getDescription() {
        return description;
    }

    void applyDiscount(double discount) {
        if (discount >= 0 && discount <= 100) {
            this.discount = discount;
        }
    }

    double getDiscountedPrice() {
        return price * (1 - discount / 100);
    }
}