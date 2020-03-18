
/**
 * Enumerator class to provide different statuses for the vending machine
 * @author Ben Harkin
 */
public enum Status {
    VENDING_MODE("VENDING_MODE"), SERVICE_MODE("SERVICE_MODE");

    private String res;

    /**
     * Constructor for Status enumerator types
     * @param res - String identifier of the Status type
     */
    Status(String res) {
        this.res = res;
    }

    /**
     * Method to return the status of the vending machine
     * @return - The status of the vending machine as a string
     */
    public String getStatus() {
        return res;
    }
}
