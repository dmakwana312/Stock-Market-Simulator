package stock.market.simulator;

import java.util.*;

public class accountProfile {

    private String accountName;
    private double balance;
    private ArrayList<stockProfile> stocksBought;

    // Class constructor
    public accountProfile(String aName, double bal) {
        accountName = aName;
        balance = bal;
        stocksBought = new ArrayList<>();

    }

    // Method to retrieve the account name
    public String getAccountName() {
        return accountName;
    }

    // Method to set the account balance
    public void setBalance(double b) {
        balance += b;
    }

    // Method to retrieve the account balance
    public double getBalance() {
        return balance;
    }

    // Method to add a stock once bought
    public void addStock(stockProfile s) {
        stocksBought.add(s);
    }

    // Method to remove a stock once sold
    public void removeStock(stockProfile s) {
        stocksBought.remove(s);
    }

    // Method to retrieve all stocks bought
    public ArrayList<stockProfile> getStocks() {
        return stocksBought;
    }
}
