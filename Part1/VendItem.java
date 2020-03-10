import java.util.Scanner;

public class VendItem {
    private int itemId;
    private static int nextId;
    private String name;
    private double unitPrice;
    private int qtyAvailable;

    
    
    public VendItem(String name, double unitPrice) {

    }
    public VendItem(String iname, double unitPrice, int qtyAvailable){
        this.name = iname;
        this.qtyAvailable = qtyAvailable;
        this.unitPrice = unitPrice;


    }
    //Getters
    public String getName(){
        return name;

    }
    public double getPrice(){
        return unitPrice;

    }

    public int getQty(){
        return qtyAvailable;
    }

    //Setters
    public boolean restock(int qtyAvailable) {
        return true;
    }
}