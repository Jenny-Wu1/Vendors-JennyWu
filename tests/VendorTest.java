import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VendorTest {

    static Vending vendor;

    @BeforeEach
    public void setUp(){
        vendor = new Vending(10,10);
    }

    @Test
    public void addPositive5MoneyTest() {
        vendor.addMoney(5.00);
        assertEquals(5.00, vendor.getBalance());
    }

    @Test
    public void addPositive100MoneyTest() {
        vendor.addMoney(100.00);
        assertEquals(100.00, vendor.getBalance());
    }

    @Test
    public void addPositiveMaxMoneyTest() {
        vendor.addMoney(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, vendor.getBalance());
    }

    @Test
    public void addNoMoneyTest() {
        vendor.addMoney(0.00);
        assertEquals(0.00, vendor.getBalance());
    }

    @Test
    public void addNegative1MoneyTest() {
        vendor.addMoney(-1.00);
        assertEquals(0.00, vendor.getBalance());
    }

    @Test
    public void addNegative100MoneyTest() {
        vendor.addMoney(-100.00);
        assertEquals(0.00, vendor.getBalance());
    }

    @Test
    public void addNegativeMinTest() {
        vendor.addMoney(-Double.MIN_VALUE);
        assertEquals(0.00, vendor.getBalance());
    }

    @Test
    public void candyPurchaseTest() {
        vendor.addMoney(5.00);
        String result = vendor.buy("Candy");
        assertEquals("Purchase successful!", result);
    }

    @Test
    public void gumPurchaseTest() {
        vendor.addMoney(5.00);
        String result = vendor.buy("Gum");
        assertEquals("Purchase successful!", result);
    }

    @Test
    public void insufficientMoneyCandyPurchaseTest() {
        String result = vendor.buy("Candy");
        assertEquals("Gimme more money", result);
    }

    @Test
    public void insufficientMoneyGumPurchaseTest() {
        String result = vendor.buy("Gum");
        assertEquals("Gimme more money", result);
    }

    @Test
    public void outOfStockCandyPurchaseTest() {
        Vending vendorCandy = new Vending(0, 10);
        vendorCandy.addMoney(5.00);
        String result = vendorCandy.buy("Candy");
        assertEquals("Out of Stock :(", result);
    }

    @Test
    public void outOfStockGumPurchaseTest() {
        Vending vendorGum = new Vending(10, 0);
        vendorGum.addMoney(5.00);
        String result = vendorGum.buy("Gum");
        assertEquals("Out of Stock :(", result);
    }

    @Test
    public void inValidItemPurchaseTest() {
        vendor.addMoney(5.00);
        String result = vendor.buy("Twix");
        assertEquals("Sorry, don't know that item", result);
    }

    @Test
    public void emptyCandyTest() {
        Vending vendingCandy = new Vending(3,10);
        vendingCandy.addMoney(10.00);
        vendingCandy.buy("Candy");
        vendingCandy.buy("Candy");
        vendingCandy.buy("Candy");
        assertEquals(0, vendingCandy.getStock("Candy"));
    }

    @Test
    public void emptyGumTest() {
        Vending vendingGum = new Vending(10,3);
        vendingGum.addMoney(10.00);
        vendingGum.buy("Gum");
        vendingGum.buy("Gum");
        vendingGum.buy("Gum");
        assertEquals(0, vendingGum.getStock("Gum"));
    }

    @Test
    public void emptyBothTest() {
        Vending vendingBoth = new Vending(3,3);
        vendingBoth.addMoney(10.00);
        vendingBoth.buy("Gum");
        vendingBoth.buy("Gum");
        vendingBoth.buy("Gum");
        vendingBoth.buy("Candy");
        vendingBoth.buy("Candy");
        vendingBoth.buy("Candy");
        assertEquals(0, vendingBoth.getStock("Gum"));
        assertEquals(0, vendingBoth.getStock("Candy"));
    }

    @Test
    public void restockPositive10Candy() {
        vendor.restock("Candy", 10);
        assertEquals(20, vendor.getStock("Candy"));
    }

    @Test
    public void restockPositive100Candy() {
        vendor.restock("Candy", 100);
        assertEquals(110, vendor.getStock("Candy"));
    }

    @Test
    public void restockPositiveMaxCandy() {
        vendor.restock("Candy", Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, vendor.getStock("Candy"));
    }

    @Test
    public void restock0Candy() {
        vendor.restock("Candy", 0);
        assertEquals(10, vendor.getStock("Candy"));
    }

    @Test
    public void restockNegative10Candy() {
        vendor.restock("Candy", -10);
        assertEquals(10, vendor.getStock("Candy"));
    }

    @Test
    public void restockNegative100Candy() {
        vendor.restock("Candy", -100);
        assertEquals(10, vendor.getStock("Candy"));
    }

    @Test
    public void restockNegativeMinCandy() {
        vendor.restock("Candy", Integer.MIN_VALUE);
        assertEquals(10, vendor.getStock("Candy"));
    }

    @Test
    public void restockPositive10Gum() {
        vendor.restock("Gum", 10);
        assertEquals(20, vendor.getStock("Gum"));
    }

    @Test
    public void restockPositive100Gum() {
        vendor.restock("Gum", 100);
        assertEquals(110, vendor.getStock("Gum"));
    }

    @Test
    public void restockPositiveMaxGum() {
        vendor.restock("Gum", Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, vendor.getStock("Gum"));
    }

    @Test
    public void restock0Gum() {
        vendor.restock("Gum", 0);
        assertEquals(10, vendor.getStock("Gum"));
    }

    @Test
    public void restockNegative10Gum() {
        vendor.restock("Gum", -10);
        assertEquals(10, vendor.getStock("Gum"));
    }

    @Test
    public void restockNegative100Gum() {
        vendor.restock("Gum", -100);
        assertEquals(10, vendor.getStock("Gum"));
    }

    @Test
    public void restockNegativeMinGum() {
        vendor.restock("Gum", Integer.MIN_VALUE);
        assertEquals(10, vendor.getStock("Gum"));
    }

    @Test
    public void restockNewItemTest() {
        vendor.restock("Chips", 10, 1.00);
        assertEquals(10, vendor.getStock("Chips"));
    }

    @Test
    public void changeItemNameSuccessfully() {
        vendor.changeItemName("Gum", "Chocolates");
        assertEquals(10, vendor.getStock("Chocolates"));
        assertEquals(0, vendor.getStock("Gum"));
    }

    @Test
    public void changeItemNameOldNameDNE() {
        vendor.changeItemName("Juice", "Matcha");
        assertEquals(0, vendor.getStock("Matcha"));
    }

    @Test
    public void changeItemNameNewNameExists() {
        vendor.changeItemName("Gum", "Candy");
        assertEquals(10, vendor.getStock("Gum"));
        assertEquals(10, vendor.getStock("Candy"));
    }

    @Test
    public void changeItemNameSameName() {
        vendor.changeItemName("Candy", "Candy");
        assertEquals(10, vendor.getStock("Candy"));
    }

    @Test
    public void printInventory5Test() {
        VendorManager manager = new VendorManager();
        Vending vendor2 = new Vending(2, 2);
        Vending vendor3 = new Vending(3, 3);
        Vending vendor4 = new Vending(4, 4);
        Vending vendor5 = new Vending(5, 5);
        manager.addVendor("Vendor1", vendor);
        manager.addVendor("Vendor2", vendor2);
        manager.addVendor("Vendor3", vendor3);
        manager.addVendor("Vendor4", vendor4);
        manager.addVendor("Vendor5", vendor5);
        String result = manager.printInventories();
        assertTrue(result.contains("Vendor1:"));
        assertTrue(result.contains("Candy: 10 available"));
        assertTrue(result.contains("Gum: 10 available"));
        assertTrue(result.contains("Vendor2:"));
        assertTrue(result.contains("Candy: 2 available"));
        assertTrue(result.contains("Gum: 2 available"));
        assertTrue(result.contains("Vendor3:"));
        assertTrue(result.contains("Candy: 3 available"));
        assertTrue(result.contains("Gum: 3 available"));
        assertTrue(result.contains("Vendor4:"));
        assertTrue(result.contains("Candy: 4 available"));
        assertTrue(result.contains("Gum: 3 available"));
        assertTrue(result.contains("Vendor5:"));
        assertTrue(result.contains("Candy: 5 available"));
        assertTrue(result.contains("Gum: 5 available"));
    }

    @Test
    public void removeNoStockCandyTest() {
        Vending vendor1 = new Vending(0,0);
        vendor1.removeItem("Candy");
        vendor1.getInventory();
        assertFalse(vendor1.getInventory().contains("Candy"));
    }

    @Test
    public void removeNoStockGumTest() {
        Vending vendor1 = new Vending(0,0);
        vendor1.removeItem("Gum");
        vendor1.getInventory();
        assertFalse(vendor1.getInventory().contains("Gum"));
    }

    @Test
    public void removeInStockItemTest() {
        String result = vendor.removeItem("Candy");
        assertEquals("Candy is still in stock!", result);
    }

    @Test
    public void removeDiscontinuedCandyTest() {
        Item candy = vendor.Stock.get("Candy");
        candy.setDiscontinued(true);
        vendor.removeItem("Candy");
        vendor.getInventory();
        assertFalse(vendor.getInventory().contains("Candy"));
    }

    @Test
    public void removeDiscontinuedGumTest() {
        Item candy = vendor.Stock.get("Candy");
        candy.setDiscontinued(true);
        vendor.removeItem("Candy");
        vendor.getInventory();
        assertFalse(vendor.getInventory().contains("Candy"));
    }

    @Test
    public void removeNonexistentItemTest() {
        String result = vendor.removeItem("Cola");
        assertEquals("Cola doesn't exist :(", result);
    }

    @Test
    public void mostPopularSingleItemTest() {
        vendor.addMoney(5.00);
        vendor.buy("Candy");
        vendor.buy("Gum");
        vendor.buy("Gum");
        vendor.buy("Gum");
        String result = vendor.getMostPopular();
        assertEquals("The most popular item(s): Gum with 3 purchases!", result);
    }

    @Test
    public void mostPopularItemNoPurchaseTest() {
        String result = vendor.getMostPopular();
        assertEquals("No purchases :(", result);
    }

    @Test
    public void mostPopularMultipleItemTest() {
        vendor.addMoney(10.00);
        vendor.buy("Candy");
        vendor.buy("Candy");
        vendor.buy("Candy");
        vendor.buy("Candy");
        vendor.buy("Gum");
        vendor.buy("Gum");
        vendor.buy("Gum");
        vendor.buy("Gum");
        String result = vendor.getMostPopular();
        assertEquals("The most popular item(s): Candy, Gum with 4 purchases!", result);
    }

    @Test
    public void getCandyDetailTest() {
        String details = vendor.getItemDetails("Candy");
        assertTrue(details.contains("Perfect little sweet treat!"));
        assertTrue(details.contains("Price: $1.25"));
        assertTrue(details.contains("Stock: 10 units left"));
    }

    @Test
    public void getGumDetailTest() {
        String details = vendor.getItemDetails("Gum");
        assertTrue(details.contains("Refreshing gum!"));
        assertTrue(details.contains("Price: $0.5"));
        assertTrue(details.contains("Stock: 10 units left"));
    }

    @Test
    public void getNonexistentItemDetailTest() {
        String details = vendor.getItemDetails("Twix");
        assertTrue(details.contains("Twix doesn't exist :("));
    }

    @Test
    public void getDefaultDetailTest() {
        vendor.restock("Twix", 10, 1.00);
        String details = vendor.getItemDetails("Twix");
        assertTrue(details.contains("Snack/Drink"));
    }

    @Test
    public void discount50PercentCandyTest() {
        vendor.applyDiscount("Candy", 50);
        Item candy = vendor.Stock.get("Candy");
        assertEquals(0.625, candy.getDiscountedPrice());
    }

    @Test
    public void purchaseDiscount50PercentCandyTest() {
        vendor.addMoney(1.00);
        vendor.applyDiscount("Candy", 50);
        vendor.buy("Candy");
        assertEquals(0.375, vendor.getBalance());
    }

    @Test
    public void tooMuchDiscountTest() {
        vendor.applyDiscount("Candy", 110);
        Item candy = vendor.Stock.get("Candy");
        assertEquals(1.25, candy.getDiscountedPrice());
    }

    @Test
    public void negativeDiscountTest() {
        vendor.applyDiscount("Candy", -5);
        Item candy = vendor.Stock.get("Candy");
        assertEquals(1.25, candy.getDiscountedPrice());
    }

    @Test
    public void candySetBestsellerTest() {
        vendor.markBestSeller("Candy");
        Item candy = vendor.Stock.get("Candy");
        assertTrue(candy.isBestseller());
    }

    @Test
    public void updateBestsellerUsingThreshold() {
        vendor.Stock.get("Candy").purchase(8);
        vendor.Stock.get("Gum").purchase(3);
        vendor.updateBestsellers(5);
        assertTrue(vendor.Stock.get("Candy").isBestseller());
        assertFalse(vendor.Stock.get("Gum").isBestseller());
    }

}