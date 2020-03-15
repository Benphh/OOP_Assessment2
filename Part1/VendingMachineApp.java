import java.util.Arrays;

public class VendingMachineApp {

    private static void displayItems(String[] items) {
        String title = "Items";
        System.out.println(title);
        for(int i = 0; i < title.length(); i++) {
            System.out.print("+");
        }
        System.out.println("\n");

        int index = 1;
        while (!items[index].equals("\n")) {
            System.out.println((index) + ". " + items[index]);
            index++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int userMainChoice = -1;

        VendingMachine machine = new VendingMachine("Queen's University Belfast", 20);
        machine.addNewItem(new VendItem("Mars", 0.70, 10));
        machine.addNewItem(new VendItem("Milky Way", 0.70, 10));
        machine.addNewItem(new VendItem("Twix", 0.80, 10));
        machine.addNewItem(new VendItem("Walkers: Ready Salted", 0.60, 10));
        machine.addNewItem(new VendItem("Walkers: Salt and Vinegar", 0.60, 10));
        machine.addNewItem(new VendItem("Walkers: Cheese and Onion", 0.60, 10));

        while (userMainChoice != 5){
            String[] appOptions = {"View Items", "Insert Money", "View Credit", "Make a Purchase", "Quit"};
            Menu appMenu = new Menu("Vending Machine", appOptions);
            userMainChoice = appMenu.getChoice();
            switch (userMainChoice) {
                case 1:
                    machine.listItems();
                    break;
                case 2:
                    
                case 3:
                    

                case 4:
                    

                case 5:
                    

                default:
                    
            }
        }

    }
}
