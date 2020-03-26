

/**
 * Class to create products placed within the vending machine(VendingMachine.java)
 * @author Ben Harkin
 */
public class VendItem implements Vendible {
    private int itemID;
    private static int nextID = 1;
    private String name;
    private double unitPrice;
    private int qtyAvailable;

    /**
     * Constructor for products within the vending machine
     * @param name - The name of the product
     * @param unitPrice - The price of each item of the product
     */
    public VendItem(String name, double unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.itemID = useNextID();
    }

    /**
     * Overloaded constructor of the products within the vending machine
     * @param name - The name of the product
     * @param unitPrice - The price of each item of the product
     * @param qtyAvailable - The quantity of the product within the vending machine
     */
    public VendItem(String name, double unitPrice, int qtyAvailable) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.qtyAvailable = qtyAvailable;
        this.itemID = useNextID();
    }

    /**
     * Getter for the name of the product
     * @return - The name of the product in string format
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the price of the product
     * @return - The price of the product as a double
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Getter for the stock of the product
     * @return - The amount of that product within the vending machine as an integer
     */
    public int getQtyAvailable() {
        return qtyAvailable;
    }

    /**
     * Setter for the stock of the product
     * @param qty - The quantity of that product within the vending machine between 0 and 10
     * @return - Whether the quantity of product was successfully set within the vending machine as a boolean
     */
    public boolean restock(int qty) {
        if (qty >= 0 && qty <= 10) {
            qtyAvailable = qty;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Reduces the quantity of an item by one to signify that an item was bought
     * @return - Whether the item was successfully decremented as a boolean
     */
    private boolean decrement() {
        if (qtyAvailable > 0) {
            qtyAvailable--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Overloading toString() method to allow easy debugging
     * @return - The object information as a string
     */
    public String toString() {
        return String.format("Name: %s\nItem ID: %d\nUnit Price: %.2f\nQuantity: %d", getName(), itemID, getUnitPrice(),
                getQtyAvailable());
    }

    @Override
    public String deliver() {
        if (decrement()) {
            return String.format("Thanks for purchasing: %s", getName());
        } else {
            return null;
        }
    }

    /**
     * Static method to determine the next available product ID
     * @return - The next available product ID as an integer
     */
    public static int useNextID() {
        return nextID++;
    }

}
