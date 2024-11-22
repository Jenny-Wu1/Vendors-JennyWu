import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Class for a Vending Machine.  Contains a hashtable mapping item names to item data, as
 * well as the current balance of money that has been deposited into the machine.
 */
class Vending {
    HashMap<String, Item> Stock = new HashMap<String,Item>();
    private double balance;

    Vending(int numCandy, int numGum) {
        this.Stock = new HashMap<>();
        Stock.put("Candy", new Item(1.25, numCandy, "Perfect little sweet treat!"));
        Stock.put("Gum", new Item(0.50, numGum, "Refreshing gum!"));
        this.balance = 0;
    }

    /** resets the Balance to 0 */
    void resetBalance () {
        this.balance = 0;
    }

    /** returns the current balance */
    double getBalance () {
        return this.balance;
    }

    /** adds money to the machine's balance
     * @param amt how much money to add
     * */
    void addMoney (double amt) {
        if (amt > 0) {
            this.balance = this.balance + amt;
        }
    }

    /** attempt to purchase named item.  Message returned if
     * the balance isn't sufficient to cover the item cost.
     *
     * @param name The name of the item to purchase ("Candy" or "Gum")
     * @return A string
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
     *
     * @param name
     * @return
     */
    int getStock(String name) {
        if(Stock.containsKey(name)) {
            return Stock.get(name).getStock();
        }
        return 0; //Return 0 for invalid items as well
    }


    /**
     *
     * @param name
     * @param amount
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
     *
     * @param name
     * @param amount
     * @param price
     */
    void restock(String name, int amount, double price) {
        if (amount > 0){
            Stock.put(name, new Item(price, amount));
        }
    }


    /**
     *
     * @param oldName
     * @param newName
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

    String getInventory() {
        String result = "";
        for (String itemName : Stock.keySet()) {
            Item item = Stock.get(itemName);
            result = result + itemName + ": " + item.getStock() + " available\n";
        }
        return result;
    }

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

    String getItemDetails(String name) {
        if (Stock.containsKey(name)) {
            Item item = Stock.get(name);
            return "Description: " + item.getDescription() + "\nPrice: $" + item.price + "\nStock: " + item.getStock() + " units left";
        }
        return name + " doesn't exist :(";
    }

    void applyDiscount(String itemName, double discount) {
        if (Stock.containsKey(itemName)) {
            Stock.get(itemName).applyDiscount(discount);
        } else {
            System.out.println(itemName + " doesn't exist :(");
        }
    }

    void markBestSeller(String itemName) {
        if (Stock.containsKey(itemName)) {
            Stock.get(itemName).setBestseller(true);
        } else {
            System.out.println(itemName + " doesn't exist :(");
        }
    }

    void updateBestsellers(int threshold) {
        for (String itemName : Stock.keySet()) {
            Item item = Stock.get(itemName);
            if (item.getPurchaseCount() >= threshold) {
                item.setBestseller(true);
            }
        }
    }

}

class Examples {
}

