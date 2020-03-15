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


    /**
     * Constructor for the VendingMachine object
     * @param owner - The name of the owner as a string
     * @param maxItems - The maximum number of items the vending machine can store
     */
    public VendingMachine(String owner, int maxItems) {
        this.owner = owner;
        this.maxItems = maxItems;
        stock = new VendItem[maxItems];
        setStatus(Status.SERVICE_MODE);
    }
    /**
     * Takes userMoney andf prints it in a consumer friendly way
     * @return - nothing. Prints a string with the userMoney included
     */
    public void getCredit(){
        System.out.println("Total Credit: " +"£" + getUserMoney());
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
        return String.format("Owner: %s\nNumber of maximum items: %d\nNumber of current items: %d\nCurrent items " +
                "stocked: %s\nTotal money in machine: %.2f\nUser money in machine: %.2f\nStatus of machine: %s", owner,
                maxItems, itemCount, machine.listItems(), totalMoney, userMoney, vmStatus.getStatus());
    }

    /**
     * Method to reset all the instance variables of the vending machine
     */
    public void reset() {
        itemCount = 0;
        stock = new VendItem[maxItems];
        totalMoney = 0;
        userMoney = 0;
        vmStatus = null;
    }

    /**
     * Method to allow the user to purchase an item from the vending machine
     * @param vendItemIndex - The index of the item to be purchased by the user  as an integer
     * @return - String to proved the user with information about their purchase
     */
    public String purchaseItem(int vendItemIndex) {
        String res;
        if (vmStatus == Status.VENDING_MODE) {
            if (vendItemIndex >= 0 && vendItemIndex < maxItems && stock[vendItemIndex] != null &&
                    stock[vendItemIndex].getQtyAvailable() > 0) {
                if (userMoney >= stock[vendItemIndex].getUnitPrice()) {
                    res = stock[vendItemIndex].deliver();
                    if (res != null) {
                        totalMoney += stock[vendItemIndex].getUnitPrice();
                        userMoney -= stock[vendItemIndex].getUnitPrice();
                    } else {
                        res = String.format("Sorry %s is currently out of stock", stock[vendItemIndex].getName());
                    }
                } else {
                    res = String.format("Sorry you have insufficient funds to purchase %s", stock[vendItemIndex].getName());
                }
            } else {
                res = "Sorry the item you selected is not available";
            }
        } else {
            res = "Sorry the vending machine is not currently in operation";
        }
        res += String.format("\n£%.2f will be returned", userMoney);
        returnChange();

        setStatus(Status.SERVICE_MODE);
        for (VendItem item: stock) {
            if (item.getQtyAvailable() > 0) {
                setStatus(Status.VENDING_MODE);
                break;
            }
        }

        return res;
    }

    /**
     * Helper method to return change to the user once a purchase is made
     * @return - Whether the change was returned successfully as a boolean
     */
    private boolean returnChange() {
        if (userMoney >= 0) {
            userMoney -= userMoney;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to update the value of userMoney dependent on the value of the coin inserted
     * @return - what the value of the coin was
     */

    public double insertCoin() {
        String coinslist[] = { "5p","10p", "20p", "50p", "£1", "£2" };
        Menu coins = new Menu("Select a Coin", coinslist);
        coins.display();
        int choice = coins.getChoice();
        while (choice < 1 || choice > coinslist.length) {
            choice = coins.getChoice();
        }
        double coin_value = 0;

        switch (choice) {
            case 1:
                coin_value = 0.05;
                break;
            case 2:
                coin_value = 0.1;
                break;
            case 3:
                coin_value = 0.2;
                break;
            case 4:
                coin_value = 0.5;
                break;
            case 5:
                coin_value = 1;
                break;
            case 6:
                coin_value = 2;
                break;
            default:
        }
        userMoney += coin_value;
        System.out.println("You have entered: £" + coin_value);
        System.out.println("Total Credit: £" + userMoney);

		return coin_value;

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
        String itemList[] = new String[itemCount];
        System.out.println("List of All Items");
		System.out.println("++++++++++++++++++++\n");
		if (itemCount==0) {
			System.out.println("\tNo items available.\n");
		}
		else {
			for(int index = 1; index<=itemCount; index++) {
                VendItem s = stock[index-1];
                itemList[index-1]=(index+". " + s.getName() + ", Price: "+ s.getUnitPrice()+", Quantity Available: " + s.getQtyAvailable());
				System.out.println(index+". " + s.getName() + ", Price: "+ s.getUnitPrice()+", Quantity Available: " + s.getQtyAvailable());
			}
            System.out.println();
            
        }
        return itemList;
    }

    /**
     * Setter for the status of the vending machine
     * @param vmStatus - The status of the vending machine of type Status
     */
    public void setStatus(Status vmStatus) {
        this.vmStatus = vmStatus;
    }
}
