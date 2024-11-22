import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * This class represents a vending machine containing a collection of items. It tracks the available stock of items,
 * manages, the balance of money added, and provides methods for purchasing items, restocking inventory,
 * applying discounts to items, and managing bestseller items. The vending machine uses a HashMa to store the items,
 * and each item is represented by an Item object.
 */
class Vending {
    HashMap<String, Item> Stock = new HashMap<String,Item>();
    private double balance;

    /**
     * Constructor for the class with a specified amounts of candy and gum and initializes the stock with the
     * two items and their respective prices and descriptions.
     * @param numCandy
     * @param numGum
     */
    Vending(int numCandy, int numGum) {
        this.Stock = new HashMap<>();
        Stock.put("Candy", new Item(1.25, numCandy, "Perfect little sweet treat!"));
        Stock.put("Gum", new Item(0.50, numGum, "Refreshing gum!"));
        this.balance = 0;
    }

    /**
     * Gets the current balance of money in the vending machine.
     * @return the current balance
     */
    double getBalance () {
        return this.balance;
    }

    /**
     * Adds money to the vending machine's balance.
     * @param amt the amount of money to add
     */
    void addMoney (double amt) {
        if (amt > 0) {
            this.balance = this.balance + amt;
        }
    }

    /**
     * Purchases an item from the vending machine if the balance is sufficient and the item is in stock, then
     * the balance is updated.
     * @param name the name of the item to buy
     * @return a message that indicates the results of the purchase
     */
    String buy (String name) {
        if (Stock.containsKey(name)) {
            Item item = Stock.get(name);
            double discountedPrice = item.getDiscountedPrice();
            if (balance >= discountedPrice) {
                if (item.stock > 0) {
                    item.purchase(1);
                    this.balance = this.balance - discountedPrice;
                    return "Purchase successful!";
                } else {
                    return "Out of Stock :(";
                }
            } else {
                return "Gimme more money";
            }
        }
        return "Sorry, don't know that item";
    }


    /**
     * Gets the current stock of the specified item
     * @param name the name of the item
     * @return the number of units of the item in stock, or 0 if the item doesn't exist
     */
    int getStock(String name) {
        if(Stock.containsKey(name)) {
            return Stock.get(name).getStock();
        }
        return 0; //Return 0 for invalid items as well
    }


    /**
     * Restocks the specified item with a given amount.
     * @param name the name of the item to restock
     * @param amount the number of units of the item to add to the stock
     */
    void restock(String name, int amount) {
        if(Stock.containsKey(name)) {
            if (amount > 0) {
                Item item = Stock.get(name);
                int currentStock = item.getStock();

                if(currentStock + amount > Integer.MAX_VALUE || currentStock + amount < 0) {
                    item.restock(Integer.MAX_VALUE - currentStock);
                } else {
                    item.restock(amount);
                }
            }
        }
    }


    /**
     * Restocks the specified item with a given amount and price. Used to add new items to th inventory.
     * @param name the name of the item to restock
     * @param amount the number of units of the item to add to the stock
     * @param price the price of the item
     */
    void restock(String name, int amount, double price) {
        if (amount > 0){
            Stock.put(name, new Item(price, amount));
        }
    }


    /**
     * Changes the name of an existing item in the vending machine while retaining the rest of its original information
     * (stock and price).
     * @param oldName the current name of the item
     * @param newName the new name for the item
     */
    void changeItemName(String oldName, String newName) {
        if (oldName.equals(newName)) {
            System.out.println("No changes; The old name and new name are the same.");
            return;
        }
        if(!Stock.containsKey(oldName)) {
            System.out.println("The item named '" + oldName + "' doesn't exist, can't rename :(");
            return;
        }
        if(Stock.containsKey(newName)) {
            System.out.println("An item with the name '" + newName + "' already exists, can't rename :(");
            return;
        }

        Item item = Stock.get(oldName);
        Stock.put(newName, item);
        Stock.remove(oldName);
    }

    /**
     * Gets the inventory of the vending machine, listing the names and stock quantities for all the items.
     * @return a string containing the item names and their available quantities
     */
    String getInventory() {
        String result = "";
        for (String itemName : Stock.keySet()) {
            Item item = Stock.get(itemName);
            result = result + itemName + ": " + item.getStock() + " available\n";
        }
        return result;
    }

    /**
     * Removes an item from the vending machine's inventory if it is discontinued or out of stock.
     * @param name the name of the item to remove
     * @return a message indicating the results of the removal
     */
    String removeItem(String name) {
        if(Stock.containsKey(name)) {
            Item item = Stock.get(name);
            if (item.isDiscontinued() || item.getStock() == 0) {
                Stock.remove(name);
                return name + " has been removed from the inventory :)";
            } else {
                return name + " is still in stock!";
            }
        } else {
            return name + " doesn't exist :(";
        }
    }

    /**
     * Gets the most popular item(s) in the vending machine based on their purchase count
     * @return a string containing the most popular item(s) and their purchase count, or a message
     *         indicating no purchases
     */
    String getMostPopular() {
        int maxPurchases = -1;
        List<String> popularItems = new ArrayList<>();

        for (String itemName : Stock.keySet()) {
            Item item = Stock.get(itemName);
            int purchaseCount = item.getPurchaseCount();

            if (purchaseCount > maxPurchases && purchaseCount > 0) {
                maxPurchases = purchaseCount;
                popularItems.clear();
                popularItems.add(itemName);
            } else if (purchaseCount == maxPurchases) {
                popularItems.add(itemName);
            }
        }
        if (popularItems.isEmpty()) {
            return "No purchases :(";
        } else {
            return "The most popular item(s): " + String.join(", ", popularItems) + " with " + maxPurchases + " purchases!";
        }
    }

    /**
     * Gets detailed information about a specific item, including its description, price, and stock.
     * @param name the name of the item to get details for
     * @return a string containing the item's description, price, and stock or a message if the item doesn't exist
     */
    String getItemDetails(String name) {
        if (Stock.containsKey(name)) {
            Item item = Stock.get(name);
            return "Description: " + item.getDescription() + "\nPrice: $" + item.price + "\nStock: " + item.getStock() + " units left";
        }
        return name + " doesn't exist :(";
    }

    /**
     * Applies a discount to the specified item
     * @param itemName the name of the item to apply the discount to
     * @param discount the discount percentage to apply (0-100)
     */
    void applyDiscount(String itemName, double discount) {
        if (Stock.containsKey(itemName)) {
            Stock.get(itemName).applyDiscount(discount);
        } else {
            System.out.println(itemName + " doesn't exist :(");
        }
    }

    /**
     * Marks the specified item as a bestseller
     * @param itemName the name of the item to mark as a bestseller
     */
    void markBestSeller(String itemName) {
        if (Stock.containsKey(itemName)) {
            Stock.get(itemName).setBestseller(true);
        } else {
            System.out.println(itemName + " doesn't exist :(");
        }
    }

    /**
     * Automatically marks an item as a bestseller if the amount of purchases is equal to or surpass a
     * specified amount
     * @param threshold the specified amount of purchases that determine if an item is a bestseller or not
     */
    void updateBestsellers(int threshold) {
        for (String itemName : Stock.keySet()) {
            Item item = Stock.get(itemName);
            if (item.getPurchaseCount() >= threshold) {
                item.setBestseller(true);
            }
        }
    }

}


