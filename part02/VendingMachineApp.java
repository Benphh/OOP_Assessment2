import java.util.Arrays;
import java.util.Scanner;

/**
 * Class to create a vending machine app
 * @author Ben Harkin
 */

public class VendingMachineApp {


    public static void main(String[] args) {
        int userMainChoice = -1;

        VendingMachine machine = new VendingMachine("Queen's University Belfast", 20);
        machine.addNewItem(new VendItem("Mars", 0.70, 7));
        machine.addNewItem(new VendItem("Milky Way", 0.70, 10));
        machine.addNewItem(new VendItem("Twix", 0.80, 10));
        machine.addNewItem(new VendItem("Walkers: Ready Salted", 0.60, 10));
        machine.addNewItem(new VendItem("Walkers: Salt and Vinegar", 0.60, 10));
        machine.addNewItem(new VendItem("Walkers: Cheese and Onion", 0.60, 10));
        machine.setStatus(Status.VENDING_MODE);

        while (userMainChoice != 5){
            String[] appOptions = {"View Items", "Insert Money", "View Credit", "Make a Purchase", "Quit"};
            Menu appMenu = new Menu("Vending Machine", appOptions);
            userMainChoice = appMenu.getChoice();
            switch (userMainChoice) {
                case 1:
                    machine.displayItems();
                    System.out.println();
                    break;

                case 2:
                    machine.insertCoin();
                    break;

                case 3:
                    machine.getCredit();
                    break;

                case 4:
                    machine.displayItems();
                    System.out.print("Enter choice: ");
                    Scanner purchaseInput = new Scanner(System.in);
                    int purchaseChoice = purchaseInput.nextInt();
                    System.out.println(machine.purchaseItem(purchaseChoice-1));
                    System.out.println();
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    break;
                //***secret menu option***
                case 6:
                    System.out.print("Enter administrator password: ");
                    Scanner password = new Scanner(System.in);
                    String passwordAttempt = password.nextLine();
                    if(passwordAttempt.equals("password123")){
                        System.out.println("Welcome to menu");
                        machine.administratorMenu();
                    }else{
                        System.out.println("Wrong password. Goodbye!");
                    }
                    break;

                default:
                    System.out.println("Please enter a valid option.");
                    System.out.println();
                    break;
            }
        }

        System.out.println(machine.getSystemInfo());
    }
}
