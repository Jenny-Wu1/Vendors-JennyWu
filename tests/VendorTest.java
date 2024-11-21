import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendorTest {

    static Vending vendingMachine;

    @BeforeEach
    public void setUp(){
        vendingMachine = new Vending(10,10);
    }

    @Test
    public void addPositive5MoneyTest() {
        vendingMachine.addMoney(5.00);
        assertEquals(5.00, vendingMachine.getBalance());
    }

    @Test
    public void addPositive100MoneyTest() {
        vendingMachine.addMoney(100.00);
        assertEquals(100.00, vendingMachine.getBalance());
    }

    @Test
    public void addPositiveMaxMoneyTest() {
        vendingMachine.addMoney(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, vendingMachine.getBalance());
    }

    @Test
    public void addNoMoneyTest() {
        vendingMachine.addMoney(0.00);
        assertEquals(0.00, vendingMachine.getBalance());
    }

    @Test
    public void addNegative1MoneyTest() {
        vendingMachine.addMoney(-1.00);
        assertEquals(0.00, vendingMachine.getBalance());
    }

    @Test
    public void addNegative100MoneyTest() {
        vendingMachine.addMoney(-100.00);
        assertEquals(0.00, vendingMachine.getBalance());
    }

    @Test
    public void addNegativeMinTest() {
        vendingMachine.addMoney(-Double.MIN_VALUE);
        assertEquals(0.00, vendingMachine.getBalance());
    }

    @Test
    public void candyPurchaseTest() {
        vendingMachine.addMoney(5.00);
        String result = vendingMachine.select("Candy");
        assertEquals("Purchase successful!", result);
    }

    @Test
    public void gumPurchaseTest() {
        vendingMachine.addMoney(5.00);
        String result = vendingMachine.select("Gum");
        assertEquals("Purchase successful!", result);
    }

    @Test
    public void insufficientMoneyCandyPurchaseTest() {
        String result = vendingMachine.select("Candy");
        assertEquals("Gimme more money", result);
    }

    @Test
    public void insufficientMoneyGumPurchaseTest() {
        String result = vendingMachine.select("Gum");
        assertEquals("Gimme more money", result);
    }

    @Test
    public void outOfStockCandyPurchaseTest() {
        Vending vendingMachineCandy = new Vending(0, 10);
        vendingMachineCandy.addMoney(5.00);
        String result = vendingMachineCandy.select("Candy");
        assertEquals("Out of Stock :(", result);
    }

    @Test
    public void outOfStockGumPurchaseTest() {
        Vending vendingMachineGum = new Vending(10, 0);
        vendingMachineGum.addMoney(5.00);
        String result = vendingMachineGum.select("Gum");
        assertEquals("Out of Stock :(", result);
    }

    @Test
    public void inValidItemPurchaseTest() {
        vendingMachine.addMoney(5.00);
        String result = vendingMachine.select("Chips");
        assertEquals("Sorry, don't know that item", result);
    }

    @Test
    public void emptyCandyTest() {
        Vending vendingCandy = new Vending(3,10);
        vendingCandy.addMoney(10.00);
        vendingCandy.select("Candy");
        vendingCandy.select("Candy");
        vendingCandy.select("Candy");
        assertEquals(0, vendingCandy.getStock("Candy"));
    }

    @Test
    public void emptyGumTest() {
        Vending vendingGum = new Vending(10,3);
        vendingGum.addMoney(10.00);
        vendingGum.select("Gum");
        vendingGum.select("Gum");
        vendingGum.select("Gum");
        assertEquals(0, vendingGum.getStock("Gum"));
    }

    @Test
    public void emptyBothTest() {
        Vending vendingBoth = new Vending(3,3);
        vendingBoth.addMoney(10.00);
        vendingBoth.select("Gum");
        vendingBoth.select("Gum");
        vendingBoth.select("Gum");
        vendingBoth.select("Candy");
        vendingBoth.select("Candy");
        vendingBoth.select("Candy");
        assertEquals(0, vendingBoth.getStock("Gum"));
        assertEquals(0, vendingBoth.getStock("Candy"));
    }

    @Test
    public void restockPositive10Candy() {
        vendingMachine.restock("Candy", 10);
        assertEquals(20, vendingMachine.getStock("Candy"));
    }

    @Test
    public void restockPositive100Candy() {
        vendingMachine.restock("Candy", 100);
        assertEquals(110, vendingMachine.getStock("Candy"));
    }

    @Test
    public void restockPositiveMaxCandy() {
        vendingMachine.restock("Candy", Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, vendingMachine.getStock("Candy"));
    }

    @Test
    public void restock0Candy() {
        vendingMachine.restock("Candy", 0);
        assertEquals(10, vendingMachine.getStock("Candy"));
    }

    @Test
    public void restockNegative10Candy() {
        vendingMachine.restock("Candy", -10);
        assertEquals(10, vendingMachine.getStock("Candy"));
    }

    @Test
    public void restockNegative100Candy() {
        vendingMachine.restock("Candy", -100);
        assertEquals(10, vendingMachine.getStock("Candy"));
    }

    @Test
    public void restockNegativeMinCandy() {
        vendingMachine.restock("Candy", Integer.MIN_VALUE);
        assertEquals(10, vendingMachine.getStock("Candy"));
    }

    @Test
    public void restockPositive10Gum() {
        vendingMachine.restock("Gum", 10);
        assertEquals(20, vendingMachine.getStock("Gum"));
    }

    @Test
    public void restockPositive100Gum() {
        vendingMachine.restock("Gum", 100);
        assertEquals(110, vendingMachine.getStock("Gum"));
    }

    @Test
    public void restockPositiveMaxGum() {
        vendingMachine.restock("Gum", Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, vendingMachine.getStock("Gum"));
    }

    @Test
    public void restock0Gum() {
        vendingMachine.restock("Gum", 0);
        assertEquals(10, vendingMachine.getStock("Gum"));
    }

    @Test
    public void restockNegative10Gum() {
        vendingMachine.restock("Gum", -10);
        assertEquals(10, vendingMachine.getStock("Gum"));
    }

    @Test
    public void restockNegative100Gum() {
        vendingMachine.restock("Gum", -100);
        assertEquals(10, vendingMachine.getStock("Gum"));
    }

    @Test
    public void restockNegativeMinGum() {
        vendingMachine.restock("Gum", Integer.MIN_VALUE);
        assertEquals(10, vendingMachine.getStock("Gum"));
    }

}