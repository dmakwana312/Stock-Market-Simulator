package stock.market.simulator;

public class stockProfile {

    private final String profileName;
    private double buyPrice;
    private double sellPrice;
    private double change;
    private double margin;
    private int quantity;

    // Class constructor for stock that has been bought 
    public stockProfile(String pName, double sPrice, double bPrice, int quant) {
        profileName = pName;
        buyPrice = bPrice;
        sellPrice = sPrice;
        quantity = quant;
    }

    // Class constructor for all stocks when program is launched
    public stockProfile(String pName, double sPrice, double bPrice, double m) {
        profileName = pName;
        buyPrice = bPrice;
        sellPrice = sPrice;
        margin = m;
        change = 0;
    }

    // Method to retrieve the quatity of stocks bought
    public int getQuantity() {
        return quantity;
    }

    // Method to set the number of a stock that has been bought
    public void setQuantity(int q) {
        quantity = q;
    }

    // Method to retrieve the profile name
    public String getProfileName() {
        return profileName;
    }

    // Method to retrieve the buy price
    public double getBuyPrice() {
        return buyPrice;
    }

    // Method to retrieve the sell price
    public double getSellPrice() {
        return sellPrice;
    }

    // Method to set the buy price
    public void setBuyPrice(double bPrice) {
        buyPrice = bPrice;
    }

    // Method to set the sell price
    public void setSellPrice(double sPrice) {
        sellPrice = sPrice;
    }

    // Method to get the margin
    public double getMargin() {
        return margin;
    }

    // Method to set the change in current and previous rate
    public void setChange(double c) {
        change = c;
    }
    
    // Method to get the change in current and previous rate
    public double getChange() {
        return change;
    }
}
