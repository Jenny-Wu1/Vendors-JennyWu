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

}