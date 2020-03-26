import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Class to create a vending machine
 * @author Ben Harkin
 */
public class VendingMachine {
    private String owner;
    private int maxItems, itemCount;
    private VendItem[] stock;
    private double totalMoney, userMoney;
    private Status vmStatus;
    private String itemList[] = new String[10]; //10 is the max

    //***FLOAT***//
    //These are the coins - they start with a certain amount - like any till would
    private int numOf5coins = 10;
    private int numOf10coins = 10;
    private int numOf20coins = 10;
    private int numOf50coins = 10;
    private int numOf1coins = 10;
    private int numOf2coins = 10;


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
     * Takes userMoney and prints it in a consumer friendly way
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
                maxItems, itemCount, Arrays.toString(itemList), totalMoney, userMoney, vmStatus.getStatus());
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
     * @param vendItemIndex - The index of the item to be purchased by the user
     * @return - output - String to proved the user with information about their purchase
     */
    public String purchaseItem(int vendItemIndex) {
        String output;
        if (vmStatus == Status.VENDING_MODE) {
            if (vendItemIndex >= 0 && vendItemIndex < maxItems && stock[vendItemIndex] != null &&
                    stock[vendItemIndex].getQtyAvailable() > 0) {
                if (userMoney >= stock[vendItemIndex].getUnitPrice()  && checkCoins(userMoney-stock[vendItemIndex].getUnitPrice())) {
                    output = stock[vendItemIndex].deliver();
                  if(checkCoins(userMoney-stock[vendItemIndex].getUnitPrice())){

                  
                    if (output != null) {
                        totalMoney += stock[vendItemIndex].getUnitPrice();
                        userMoney -= stock[vendItemIndex].getUnitPrice();
                        getCoins(userMoney);
                    } else {
                        output = String.format("Sorry %s is currently out of stock", stock[vendItemIndex].getName());
                    }
                } else {
                    output = String.format("You have insufficient funds to purchase %s", stock[vendItemIndex].getName());
                }}
                else {
                    output = String.format("apoligies, the machine has insufficient coins to allow you to purchase %s", stock[vendItemIndex].getName());
                }
            } else {
                output = "The item you selected is not available";
            }
        } else {
            output = "Sorry the vending machine is not currently in operation";
        }
        output += String.format("\n£%.2f will be returned", userMoney);
        returnChange();

        setStatus(Status.SERVICE_MODE);
        for (VendItem item: stock) {
            if (item.getQtyAvailable() > 0) {
                setStatus(Status.VENDING_MODE);
                break;
            }
        }

        return output;
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
                numOf5coins++;
                break;
            case 2:
                coin_value = 0.1;
                numOf10coins++;
                break;
            case 3:
                coin_value = 0.2;
                numOf20coins++;
                break;
            case 4:
                coin_value = 0.5;
                numOf50coins++;
                break;
            case 5:
                coin_value = 1;
                numOf1coins++;
                break;
            case 6:
                coin_value = 2;
                numOf2coins++;
                break;
            default:
        }
        userMoney += coin_value;
        System.out.println("You have entered: £" + coin_value);
        System.out.println("Total Credit: £" + userMoney);

		return coin_value;

    }

    /**
     * Method to allow administrator to access their menu
     */
    public void administratorMenu(){
        String optionList[] = { "Output all Data","Reset Machine", "Switch Status", "Add new Item", "Restock Existing Item", "Check Coin Stock"};
        Menu adminMenu = new Menu("Select an option", optionList);
        adminMenu.display();
        int choice = adminMenu.getChoice();
        while (choice < 1 || choice > optionList.length) {
            choice = adminMenu.getChoice();
        }
        switch (choice) {
            case 1:
                System.out.println(getSystemInfo());
                break;
            case 2:
                System.out.println("Resetting System");
                reset();
                break;
            case 3:
                System.out.println("Swapping System Mode");
                swapStatus();
                break;
            case 4:
                addItem();
                break;
            case 5:
                displayItems();
                System.out.println("What item do you want to restock?");
                
                Scanner restockInput = new Scanner(System.in);
                int restockChoice = restockInput.nextInt();
                restock(restockChoice-1);
                System.out.println();
                break;
            case 6:
                displayCoins();
                break;
            default:
        }
    }
    /**
     * Method to restock items in the vending machine
     */
    public void addItem() {
        System.out.println("***ADD NEW ITEM***");
        System.out.println("What is the name for the item?");
        Scanner newItemNameInput = new Scanner(System.in);
        String newItemName = newItemNameInput.nextLine();
        System.out.println("What is the price of "+ newItemName+"?");
        Scanner newItemPriceInput = new Scanner(System.in);
        double newItemPrice = newItemPriceInput.nextDouble();
        System.out.println("What is the quantity of the item?");
        Scanner newItemQtyInput = new Scanner(System.in);
        int newItemQty = newItemQtyInput.nextInt();
        addNewItem(new VendItem(newItemName, newItemPrice, newItemQty));
    }

    /**
     * Method to restock items in the vending machine
     * @param itemValue - the number corresponding to the item the user wants to restock
     */
    public void restock(int itemValue) {
        System.out.println("Current stock count:"+stock[itemValue].getQtyAvailable()+" What do you want to restock to?");
        Scanner restockValueInput = new Scanner(System.in);
        int restockValue = restockValueInput.nextInt();
        stock[itemValue].restock(restockValue);
    }

    /**
     * Method to display the different coins in the machine
     */
    public void displayCoins() {
        System.out.println("Number of 5p coins: "+numOf5coins);
        System.out.println("Number of 10p coins: "+numOf10coins);
        System.out.println("Number of 20p coins: "+numOf20coins);
        System.out.println("Number of 50p coins: "+numOf50coins);
        System.out.println("Number of £1 coins: "+numOf1coins);
        System.out.println("Number of £2 coins: "+numOf2coins);
        
    }

    /**
     * Method to check if the machine has enough coins
     * @return - True/False depending on if the machine has the coins to give out change
     */
    public boolean checkCoins(double change) {
        if(change>2.0 && numOf2coins >0){
            change -= 2.0;
        } 
        else if(change>1.0 && numOf1coins >0){
            change -= 1.0;
        }
        else if(change>0.5 && numOf50coins >0){
            change -= 0.5;
        }
        else if(change>0.2 && numOf20coins >0){
            change -= 0.2;
        }
        else if(change>0.1 && numOf10coins >0){
            change -= 0.1;
        }
        else if(change>0.05 && numOf10coins >0){
            change -= 0.1;
        }
        else{
            return false;
        }
        return true;
        
    }
    /**
     * Method to change the amount of coins depending on the change given out
     */
    public void getCoins(double change) {
        while(change>=2.0 && numOf2coins >0){
            change -= 2.0;
            numOf2coins--;
        } 
        while(change>=1.0 && numOf1coins >0){
            change -= 0.9999999999999999; //for some reason 1 doesn't work      
            numOf1coins--;
            System.out.println(change);
        }
        while(change>=0.5 && numOf50coins >0){
            change -= 0.5;
            numOf50coins--;
        }
        while(change>=0.2 && numOf20coins >0){
            change -= 0.2;
            numOf20coins--;
            System.out.println(change);
        }
        while(change>=0.1 && numOf10coins >0){
            change -= 0.1;
            numOf10coins--;
            System.out.println(change);
        }
        while(change>=0.05 && numOf10coins >0){
            change -= 0.1;
            numOf5coins--;
        }
        

        
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
        
		if (itemCount==0) {
			System.out.println("\tNo items available.\n");
		}
		else {
			for(int index = 1; index<=itemCount; index++) {
                VendItem s = stock[index-1];
                itemList[index-1]=(s.getName());
			}
            System.out.println();
            
        }
        return itemList;

    }
    public void displayItems(){
        System.out.println("List of All Items");
		System.out.println("++++++++++++++++++++\n");
		if (itemCount==0) {
			System.out.println("\tNo items available.\n");
		}
		else {
			for(int index = 1; index<=itemCount; index++) {
                VendItem s = stock[index-1];
				System.out.println(index+". " + s.getName() + ", Price: "+ s.getUnitPrice()+", Quantity Available: " + s.getQtyAvailable());
			}
            System.out.println();
            
        }

    }

    /**
     * Setter for the status of the vending machine
     * @param vmStatus - The status of the vending machine of type Status
     */
    public void setStatus(Status vmStatus) {
        this.vmStatus = vmStatus;
        if(vmStatus == Status.SERVICE_MODE){
            vmStatus = Status.VENDING_MODE;
        }else{
            vmStatus = Status.SERVICE_MODE;
        }
    }

    public Status swapStatus(){
        if(vmStatus == Status.SERVICE_MODE){
            vmStatus = Status.VENDING_MODE;
        }else{
            vmStatus = Status.SERVICE_MODE;
        }
        return vmStatus;
    }
}
