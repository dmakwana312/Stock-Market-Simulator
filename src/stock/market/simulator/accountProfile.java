package stock.market.simulator;
 import java.util.ArrayList;
import java.util.List;
 public class accountProfile {
    private String accountName;
    private double balance;
    private List<stockProfile> stocksBought;
    // Constructor to initialize account name, balance and stocks bought
     public accountProfile(String accountName, double balance) {
        this.accountName = accountName;
        this.balance = balance;
        stocksBought = new ArrayList<>(10);
    }

    // Method to retrieve the account name
    public String getAccountName() {
        return accountName;
    }
     // Method to set the account balance
     public void setBalance(double balance) {
        this.balance = balance;
    }

    // Method to retrieve the account balance
    public double getBalance() {
        return balance;
    }
     // Method to add a stock to the list of stocks bought
    public void addStock(stockProfile stock) {
        if (!stocksBought.contains(stock)) {
            stocksBought.add(stock);
    }
    }
     // Method to remove a stock from the list of stocks bought
    public void removeStock(stockProfile stock) {
        stocksBought.remove(stock);
    }
     // Method to retrieve a copy of the list of stocks bought
    public List<stockProfile> getStocks() {
        return new ArrayList<>(stocksBought);
    }
}
