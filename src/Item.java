class Item {
    double price;
    int stock;
    boolean discontinued;

    Item(double price, int numPieces) {
        this.price = price;
        this.stock = numPieces;
        this.discontinued = false;
    }

    void restock(int amount) {
        this.stock = this.stock + amount;
    }

    void purchase(int amount) {
        if (this.stock >= amount) {
            this.stock = this.stock - amount;
        }
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