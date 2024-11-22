class Item {
    double price;
    int stock;
    boolean discontinued;
    int purchaseCount;

    Item(double price, int numPieces) {
        this.price = price;
        this.stock = numPieces;
        this.discontinued = false;
        this.purchaseCount = 0;
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
}