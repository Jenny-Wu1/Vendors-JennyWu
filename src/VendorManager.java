import java.util.HashMap;
public class VendorManager {
    private HashMap<String, Vending> vendors;

    public VendorManager() {
        this.vendors = new HashMap<>();
    }

    public void addVendor(String vendorName, Vending vendor) {
        vendors.put(vendorName, vendor);
    }

    public String printInventories() {
        String result = "Vendor Inventories:\n";
        for (String vendorName : vendors.keySet()) {
            result = result + vendorName + ":\n";
            Vending vendor = vendors.get(vendorName);
            result = result + vendor.getInventory() + "\n";
        }
        return result;
    }
}
