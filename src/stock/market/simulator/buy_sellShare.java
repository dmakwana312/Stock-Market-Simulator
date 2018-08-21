package stock.market.simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.*;

public class buy_sellShare {

    JComboBox<String> names;
    JTextField strQuantity;

    accountProfile accProfile;
    stockProfile[][] stocks;

    JFrame frame;

    // Class constructor
    public buy_sellShare(accountProfile profile, stockProfile[][] stockProfiles) {
        accProfile = profile;
        stocks = stockProfiles;

        ButtonListener listener = new ButtonListener();

        frame = new JFrame("Stock Simulator");
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));

        ArrayList<String> namesOfShare = new ArrayList<>();

        for (stockProfile[] stockArray : stockProfiles) {
            for (stockProfile stock : stockArray) {
                namesOfShare.add(stock.getProfileName());
            }
        }

        String[] shareNames = new String[namesOfShare.size()];

        for (int i = 0; i < shareNames.length; i++) {
            shareNames[i] = namesOfShare.get(i);
        }

        JLabel lblshareName = new JLabel("Share Name");
        names = new JComboBox<>(shareNames);

        JLabel lblquantity = new JLabel("Quantity");
        strQuantity = new JTextField("");

        panel.add(lblshareName);
        panel.add(names);

        panel.add(lblquantity);
        panel.add(strQuantity);

        Button btnBuy = new Button("Buy");
        Button btnSell = new Button("Sell");

        btnBuy.addActionListener(listener);
        btnSell.addActionListener(listener);

        panel.add(btnBuy);
        panel.add(btnSell);

        frame.add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Event handler for the buy and sell buttons
    public class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent et) {

            String btnName = et.getActionCommand();

            try {
                String stockName = (String) names.getSelectedItem();

                int quantity = Integer.parseInt(strQuantity.getText());

                stockProfile stockToBuySell = searchAvailableStock(stockName);

                if (stockToBuySell != null) {

                    if (btnName.equals("Buy")) {

                        buyStock(stockToBuySell, quantity);

                    }

                    if (btnName.equals("Sell")) {
                        stockProfile stock = searchBoughtStock(stockName);
                        if (stock != null) {
                            sellStock(stock, quantity, stockName);
                        } else {
                            JOptionPane.showMessageDialog(null, "You Don't Own This Stock", "ERROR",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }

                    terminate();
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.WARNING_MESSAGE);
            }

        }
    }

    // Method that called when user wants to buy a stock
    public void buyStock(stockProfile profile, int quantity) {
        double price = profile.getBuyPrice() * quantity;

        if (price < accProfile.getBalance()) {
            stockProfile stock = new stockProfile(profile.getProfileName(),
                    profile.getSellPrice(), profile.getBuyPrice(), quantity);
            accProfile.setBalance(-price);
            accProfile.addStock(stock);

            displayNewBalance();

        } else {
            JOptionPane.showMessageDialog(null, "You Don't Have Enough Money", "ERROR",
                    JOptionPane.WARNING_MESSAGE);

        }

    }

    // Method that is called when user wants to sell a stock
    public void sellStock(stockProfile profile, int quantity, String stockName) {

        if (quantity <= profile.getQuantity()) {

            double price = profile.getSellPrice() * quantity;
            accProfile.setBalance(+price);
            if (quantity == profile.getQuantity()) {
                accProfile.removeStock(profile);
            } else {
                profile.setQuantity(profile.getQuantity() - quantity);
            }

            displayNewBalance();

        } else {
            JOptionPane.showMessageDialog(null, "You Do Not Own This Many Stock", "Error",
                    JOptionPane.WARNING_MESSAGE);
        }

    }
    
    //Method to display new balance
    public void displayNewBalance(){
        JOptionPane.showMessageDialog(null, "Balance: " + roundTo2DP(accProfile.getBalance()),
                    "Successful Sell", JOptionPane.WARNING_MESSAGE);
    }

    // Method to search account profile for a stock (used when user is selling stock)
    public stockProfile searchBoughtStock(String stockName) {
        ArrayList<stockProfile> stocksBought = accProfile.getStocks();
        for (int i = 0; i < stocksBought.size(); i++) {
            if (stocksBought.get(i).getProfileName().equalsIgnoreCase(stockName)) {
                return stocksBought.get(i);
            }
        }
        return null;
    }

    // Method to search  for a stock (used when user is buying stock)
    public stockProfile searchAvailableStock(String stockName) {

        for (stockProfile[] stockArray : stocks) {
            for (stockProfile stock : stockArray) {
                if (stock.getProfileName().equalsIgnoreCase(stockName)) {

                    return stock;
                }
            }

        }
        return null;
    }
    
    // Rounding to 2 decimal place
    public static Double roundTo2DP(double number) {
        DecimalFormat roundFormat = new DecimalFormat(".##");
        return (Double.parseDouble(roundFormat.format(number)));
    }

    // Method to close the window
    public void terminate() {
        frame.dispose();
    }

}
