
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to create a vending machine
 * @author David Lim
 */
public class VendingMachine {
    private String owner;
    private int maxItems, itemCount;
    private VendItem[] stock;
    private double totalMoney, userMoney;
    private Status vmStatus;
    private ArrayList<Integer> coins;

    /**
     * Constructor for the VendingMachine object
     * @param owner - The name of the owner as a string
     * @param maxItems - The maximum number of items the vending machine can store
     */
    public VendingMachine(String owner, int maxItems) {
        this.owner = owner;
        this.maxItems = maxItems;
        stock = new VendItem[maxItems];
        coins = new ArrayList<>(Arrays.asList(5, 10, 20, 50, 100, 200));
        setStatus(Status.SERVICE_MODE);
    }

    /**
     * Getter for userMoney
     * @return - user money as a double
     */
    public double getUserMoney() {
        return userMoney;
    }

    /**
     * Method to return all the vending machine information as a string
     * @return - The vending machine information as a string
     */
    public String getSystemInfo() {
        return "lhfv";
    }

    /**
     * Method to reset all the instance variables of the vending machine
     */
    public void reset() {
        
    }

    /**
     * Method to allow the user to purchase an item from the vending machine
     * @param vendItemIndex - The index of the item to be purchased by the user  as an integer
     * @return - String to proved the user with information about their purchase
     */
    public String purchaseItem(int vendItemIndex) {
        return "efnl";
    }

    /**
     * Helper method to return change to the user once a purchase is made
     * @return - Whether the change was returned successfully as a boolean
     */
    private boolean returnChange() {
        return true;
    }

    /**
     * Method to update the value of userMoney dependent on the value of the coin inserted
     * @param value - The value of the coin in pennies as an integer
     * @return - Whether the coin was valid and userMoney was updated as a boolean
     */
    public boolean insertCoin(int value) {
        return true;
    }

    /**
     * Method to add items to the vending machine
     * @param item - The item as VendItem
     * @return - Whether the item was added to the vending machine
     */
    public boolean addNewItem(VendItem item) {
        if (itemCount < maxItems) {
            stock[itemCount] = item;
            itemCount++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to return the items in the vending machine as a list
     * @return - The list of items as a list
     */
    public String[] listItems() {
        String itemsList[] = new String[itemCount];
        System.out.println("List of All Items");
		System.out.println("++++++++++++++++++++\n");
		if (itemCount==0) {
			System.out.println("\tNo items available.\n");
		}
		else {
			for(int index = 0; index<=itemCount; index++) {
                
                VendItem s = stock[index];
                for(int i=0;i<itemCount;i++){
                    System.out.println(stock[i].toString());
                }
                //itemsList[index] = ((index+1)+". " + s.getName() + ",Price: "+ s.getUnitPrice()+ ", Quantity Available: " + s.getQtyAvailable());
                
			}
            System.out.println();

            
        }
        for(int i =0; i<itemCount;i++){
            System.out.println(itemsList[i]);
        }
        return itemsList;
    }

    /**
     * Setter for the status of the vending machine
     * @param vmStatus - The status of the vending machine of type Status
     */
    public void setStatus(Status vmStatus) {
        this.vmStatus = vmStatus;
    }
}
