import java.util.ArrayList;

public class VendingMachine {
    
    private String owner;
    private int maxItems;
    private double totalMoney;
    private static double userMoney;
    private Status vmStatus;
    static final int MAX = 2;
    VendingMachine newMachine = new VendingMachine("Ben Harkin", 7);
	static VendItem stock[] = new VendItem[MAX];
	static int count = 0;

    public static void main(String[] args) {
        
        
        // Titles for Menu
        String title = "Vending Machine";
        String options[] = { "List Items", "Insert Coins", "View Credit", "Select Item", "Quit" };

        Menu regMenu = new Menu(title, options);
        
        
        int choice = regMenu.getChoice();

        while (choice != 5) { // i.e. "Quit"
            processChoice(choice);
            choice = regMenu.getChoice();
        }
        System.out.println("Finished - Goodbye!");
    }

    public static void processChoice(int choice) {
        VendItem newItem = new VendItem("Coke", 3.2, 10);
        stock[count] = newItem;
        count++;
        VendItem newItem1 = new VendItem("Fanta Lemon", 3.2, 10);
        stock[count] = newItem1;
		count++;
        switch (choice) {
            case 1:
                listItems();
                break;
            case 2:
                insertCoin();
                break;
            case 3:
                getCredit();
                break;
            case 4:
                purchaseItem();
                break;
            default:
                System.out.println("Error");
        }
    }

    public VendingMachine(String owner, int maxItems) {

    }

    public String getSystemInfo() {
        return "fds";
    }

    public void reset() {
        // should reintitialise a VendingMachine instance
    }

    public static void purchaseItem() {
        System.out.println("List of All Items");
        System.out.println("++++++++++++++++++++\n");
        String itemsList[] = new String[MAX];
        int purchaseChoice; int countItems = 0;
        
        if (count==0) {
			System.out.println("\tNo items available.\n");
		}
		else {
			for(int index = 1; index<=count; index++) {
				VendItem s = stock[index-1];
                System.out.println(index+". " + s.getName());
                itemsList[countItems] = s.getName();
                countItems++;
            
                
			}
			System.out.println();
        }
        Menu purchase = new Menu("Select an item to purchase", itemsList);
        purchase.display();
        purchaseChoice = purchase.getChoice();
        if(userMoney >= stock[purchaseChoice-1].getPrice()) {
            System.out.println("You have succesfully bought " + itemsList[purchaseChoice]);
        } else{
            System.out.println("You haven't enough money, please enter more coins");
            //write a function to calc how much is needed
        }
        
        //Need to add functions:
            //check if you have enough money
            //take money from credit
            //change money in till
            //reduce quantity by one


    }



    

    public void setStatus(Status status) {

    }




    ///*** WORKING METHODS***/
    //Option 1 - LIST ITEMS
    public static void listItems(){
        System.out.println("List of All Items");
		System.out.println("++++++++++++++++++++\n");
		if (count==0) {
			System.out.println("\tNo items available.\n");
		}
		else {
			for(int index = 1; index<=count; index++) {
				VendItem s = stock[index-1];
				System.out.println(index+". " + s.getName() + ", Quantity Available: " + s.getQty());
			}
			System.out.println();
		}

    }

    //Option2 - INSERT COINS
    public static double insertCoin() {
        String coinslist[] = { "10p", "20p", "50p", "£1", "£2" };
        Menu coins = new Menu("Select a Coin", coinslist);
        coins.display();
        int choice = coins.getChoice();
        while (choice < 1 || choice > coinslist.length) {
            choice = coins.getChoice();
        }
        double coin_value = 0;

        switch (choice) {
            case 1:
                coin_value = 0.1;
                break;
            case 2:
                coin_value = 0.2;
                break;
            case 3:
                coin_value = 0.5;
                break;
            case 4:
                coin_value = 1;
                break;
            case 5:
                coin_value = 2;
                break;
            default:
        }
        userMoney += coin_value;
        System.out.println("You have entered: £" + coin_value);
        System.out.println("Total Credit: " + userMoney);

		return coin_value;

    }
    //Option 3 - VIEW CREDIT
    public static void getCredit(){
        System.out.println("Total Credit: " +"£" + userMoney);
    }
    public boolean addNewItem(VendItem[] item) {
        return true;
    }
 }