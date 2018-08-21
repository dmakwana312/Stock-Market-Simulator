package stock.market.simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.border.Border;
import java.util.*;
import static stock.market.simulator.StockMarketSimulator.HISTORYFILEPATH;

public class mainWindow {

    // CURRENCY STOCK TEXT FIELDS
    JTextField EURUSD;
    JTextField diffEURUSD;

    JTextField GBPUSD;
    JTextField diffGBPUSD;

    JTextField EURGBP;
    JTextField diffEURGBP;

    JTextField GBPJPY;
    JTextField diffGBPJPY;

    // COMPANY STOCK TEXT FIELDS
    JTextField facebook;
    JTextField difffacebook;

    JTextField apple;
    JTextField diffapple;

    JTextField microsoft;
    JTextField diffmicrosoft;

    JTextField bmw;
    JTextField diffbmw;

    // ECONOMY STOCK TEXT FIELDS
    JTextField uk;
    JTextField diffuk;

    JTextField usa;
    JTextField diffusa;

    JTextField aus;
    JTextField diffaus;

    JTextField jpy;
    JTextField diffjpy;

    JTextField[][] textFields;
    JTextField[][] differenceTextFields;

    JTextArea stocksBoughtInfo;
    accountProfile accProfile;
    stockProfile[][] stocksProfiles;

    // Class constructor
    public mainWindow(accountProfile account, stockProfile[][] profiles) {

        accProfile = account;
        stocksProfiles = profiles;

        ButtonListener listener = new ButtonListener();

        JFrame frame = new JFrame("Stock Simulator");
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));

        textFields = new JTextField[3][4];
        differenceTextFields = new JTextField[3][4];

        JPanel stockInfo = new JPanel();
        Border stockInfoBorder = BorderFactory.createTitledBorder("STOCK INFORMATION");
        stockInfo.setBorder(stockInfoBorder);
        stockInfo.setLayout(new GridLayout(2, 0, 10, 0));

        // CURRENCY STOCK PANEL
        JLabel lblEURUSD = new JLabel("EUR/USD", SwingConstants.CENTER);
        JLabel lblGBPUSD = new JLabel("GBP/USD", SwingConstants.CENTER);
        JLabel lblEURGBP = new JLabel("EUR/GBP", SwingConstants.CENTER);
        JLabel lblGBPJPY = new JLabel("GBP/JPY", SwingConstants.CENTER);

        EURUSD = new JTextField("");
        GBPUSD = new JTextField("");
        EURGBP = new JTextField("");
        GBPJPY = new JTextField("");

        textFields[0][0] = EURUSD;
        textFields[0][1] = GBPUSD;
        textFields[0][2] = EURGBP;
        textFields[0][3] = GBPJPY;

        diffEURUSD = new JTextField("");
        diffGBPUSD = new JTextField("");
        diffEURGBP = new JTextField("");
        diffGBPJPY = new JTextField("");

        differenceTextFields[0][0] = diffEURUSD;
        differenceTextFields[0][1] = diffGBPUSD;
        differenceTextFields[0][2] = diffEURGBP;
        differenceTextFields[0][3] = diffGBPJPY;

        JPanel currencyPanel = new JPanel();
        currencyPanel.setLayout(new GridLayout(5, 3, 5, 5));

        Border currencyBorder = BorderFactory.createTitledBorder("Currency Stock");
        currencyPanel.setBorder(currencyBorder);

        currencyPanel.add(new JLabel(""));
        currencyPanel.add(new JLabel("Buy/Sell", SwingConstants.CENTER));
        currencyPanel.add(new JLabel(""));

        currencyPanel.add(lblEURUSD);
        currencyPanel.add(textFields[0][0]);
        currencyPanel.add(differenceTextFields[0][0]);

        currencyPanel.add(lblGBPUSD);
        currencyPanel.add(textFields[0][1]);
        currencyPanel.add(differenceTextFields[0][1]);

        currencyPanel.add(lblEURGBP);
        currencyPanel.add(textFields[0][2]);
        currencyPanel.add(differenceTextFields[0][2]);

        currencyPanel.add(lblGBPJPY);
        currencyPanel.add(textFields[0][3]);
        currencyPanel.add(differenceTextFields[0][3]);

        // COMPANY STOCK PANEL
        JLabel lblfacebook = new JLabel("Facebook", SwingConstants.CENTER);
        JLabel lblapple = new JLabel("Apple", SwingConstants.CENTER);
        JLabel lblmicrosoft = new JLabel("Microsoft", SwingConstants.CENTER);
        JLabel lblbmw = new JLabel("BMW", SwingConstants.CENTER);

        facebook = new JTextField("");
        apple = new JTextField("");
        microsoft = new JTextField("");
        bmw = new JTextField("");

        textFields[1][0] = facebook;
        textFields[1][1] = apple;
        textFields[1][2] = microsoft;
        textFields[1][3] = bmw;

        difffacebook = new JTextField("");
        diffapple = new JTextField("");
        diffmicrosoft = new JTextField("");
        diffbmw = new JTextField("");

        differenceTextFields[1][0] = difffacebook;
        differenceTextFields[1][1] = diffapple;
        differenceTextFields[1][2] = diffmicrosoft;
        differenceTextFields[1][3] = diffbmw;

        JPanel companyPanel = new JPanel();
        companyPanel.setLayout(new GridLayout(5, 3, 5, 5));

        Border companyBorder = BorderFactory.createTitledBorder("Company Stock");
        companyPanel.setBorder(companyBorder);

        companyPanel.add(new JLabel(""));
        companyPanel.add(new JLabel("Buy/Sell", SwingConstants.CENTER));
        companyPanel.add(new JLabel(""));

        companyPanel.add(lblfacebook);
        companyPanel.add(textFields[1][0]);
        companyPanel.add(differenceTextFields[1][0]);

        companyPanel.add(lblapple);
        companyPanel.add(textFields[1][1]);
        companyPanel.add(differenceTextFields[1][1]);

        companyPanel.add(lblmicrosoft);
        companyPanel.add(textFields[1][2]);
        companyPanel.add(differenceTextFields[1][2]);

        companyPanel.add(lblbmw);
        companyPanel.add(textFields[1][3]);
        companyPanel.add(differenceTextFields[1][3]);

        // ECONOMY STOCK PANEL
        JPanel currencyCompanyHolder = new JPanel();
        currencyCompanyHolder.setLayout(new GridLayout(1, 2, 10, 0));

        currencyCompanyHolder.add(currencyPanel);
        currencyCompanyHolder.add(companyPanel);

        JLabel lbluk = new JLabel("FTSE 100", SwingConstants.CENTER);
        JLabel lblusa = new JLabel("Dow Jones", SwingConstants.CENTER);
        JLabel lblaus = new JLabel("$AUSSIE200", SwingConstants.CENTER);
        JLabel lbljpy = new JLabel("NIKKEI 225", SwingConstants.CENTER);

        uk = new JTextField("");
        usa = new JTextField("");
        aus = new JTextField("");
        jpy = new JTextField("");

        textFields[2][0] = uk;
        textFields[2][1] = usa;
        textFields[2][2] = aus;
        textFields[2][3] = jpy;

        diffuk = new JTextField("");
        diffusa = new JTextField("");
        diffaus = new JTextField("");
        diffjpy = new JTextField("");

        differenceTextFields[2][0] = diffuk;
        differenceTextFields[2][1] = diffusa;
        differenceTextFields[2][2] = diffaus;
        differenceTextFields[2][3] = diffjpy;

        JPanel economyPanel = new JPanel();
        economyPanel.setLayout(new GridLayout(6, 3, 5, 5));

        Border economyBorder = BorderFactory.createTitledBorder("Economy Stock");
        economyPanel.setBorder(economyBorder);

        economyPanel.add(new JLabel(""));
        economyPanel.add(new JLabel("Buy/Sell", SwingConstants.CENTER));
        economyPanel.add(new JLabel(""));

        economyPanel.add(lbluk);
        economyPanel.add(textFields[2][0]);
        economyPanel.add(differenceTextFields[2][0]);

        economyPanel.add(lblusa);
        economyPanel.add(textFields[2][1]);
        economyPanel.add(differenceTextFields[2][1]);

        economyPanel.add(lblaus);
        economyPanel.add(textFields[2][2]);
        economyPanel.add(differenceTextFields[2][2]);

        economyPanel.add(lbljpy);
        economyPanel.add(textFields[2][3]);
        economyPanel.add(differenceTextFields[2][3]);

        for (JTextField[] textFieldArray : textFields) {
            for (JTextField textField : textFieldArray) {
                textField.setSize(new Dimension(150, 20));
            }
        }

        JPanel economyHolder = new JPanel();
        economyHolder.setLayout(new FlowLayout(FlowLayout.CENTER));
        economyHolder.add(economyPanel);

        JPanel stocksBought = new JPanel();
        Border stocksBoughtBorder = BorderFactory.createTitledBorder("Stocks Bought");
        stocksBought.setBorder(stocksBoughtBorder);

        stocksBoughtInfo = new JTextArea(15, 30);
        stocksBought.add(stocksBoughtInfo);

        stockInfo.add(currencyCompanyHolder, BorderLayout.CENTER);
        stockInfo.add(economyHolder, BorderLayout.PAGE_END);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));

        mainPanel.add(stockInfo);
        mainPanel.add(stocksBought);

        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));

        JPanel buttonPanel = new JPanel();
        Border graphViewBorder = BorderFactory.createEtchedBorder();
        buttonPanel.setBorder(graphViewBorder);

        Button btnShowGraph = new Button("View Graph");
        btnShowGraph.addActionListener(listener);

        Button btnBuySell = new Button("Buy/Sell Stock");
        btnBuySell.addActionListener(listener);

        buttonPanel.setLayout(new GridLayout(1, 3, 5, 5));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.add(btnBuySell);
        buttonPanel.add(btnShowGraph);
        //buttonPanel.setPreferredSize(new Dimension(110, 75));

        frame.add(mainPanel);
        frame.add(buttonPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 650);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

    }

    // Event handler for when buttons are pressed
    public class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent et) {

            String btnName = et.getActionCommand();

            try {
                if (btnName.equals("Buy/Sell Stock")) {
                    buy_sellShare window = new buy_sellShare(accProfile, stocksProfiles);
                }

                if (btnName.equals("View Graph")) {
                    viewGraph graph = new viewGraph(stocksProfiles);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.WARNING_MESSAGE);
            }

        }

    }
    
    // Method to set the values of the text boxes with the stock prices
    public void setTextBoxValues(stockProfile[][] stocksProfiles) {

        for (int i = 0; i < stocksProfiles.length; i++) {
            for (int j = 0; j < stocksProfiles[i].length; j++) {

                stockProfile stock = stocksProfiles[i][j];

                setStockPriceTextField(textFields[i][j], stock);
                setDifferenceTextField(differenceTextFields[i][j], stock.getChange());
                setTextFieldColour(differenceTextFields[i][j], stock.getChange());

                writeRateToFile(stock.getProfileName(), stock.getBuyPrice());

            }
        }

        setStockBought();

    }

    // Method to set the buy and sell rates for a stock
    public void setStockPriceTextField(JTextField field, stockProfile profile) {
        field.setText(
                String.valueOf(profile.getBuyPrice()) + "/" + String.valueOf(profile.getSellPrice()));

    }

    // Setting the difference between current rate and previous rates
    public void setDifferenceTextField(JTextField field, double change) {
        String difference = (change > 0) ? ("+" + change) : ("" + change);
        field.setText(difference + "%");
    }

    // Method to display the bought stocks in the relative text area
    public void setStockBought() {
        ArrayList<stockProfile> stocksBought = accProfile.getStocks();
        int numOfStock = stocksBought.size();
        String message = "STOCK NAME - BUY PRICE/SELL PRICE - Quantity\n";

        for (int i = 0; i < numOfStock; i++) {
            message = message + stocksBought.get(i).getProfileName() + " - " + stocksBought.get(i).getBuyPrice() + "/"
                    + stocksBought.get(i).getSellPrice() + " - " + stocksBought.get(i).getQuantity() + "\n";
        }
        
        message = message + "\nBALANCE: " + roundTo2DP(accProfile.getBalance());
        stocksBoughtInfo.setText(message);
    }

    // Method to set the font colour of the difference
    public void setTextFieldColour(JTextField field, double change) {
        if (change >= 0) {
            field.setForeground(Color.green);
        } else {
            field.setForeground(Color.red);
        }
    }

    // Method to write the rate to its relavant file
    public void writeRateToFile(String fileName, double rate) {

        try {
            String location = HISTORYFILEPATH + fileName + ".csv";

            Calendar getTime = Calendar.getInstance();
            SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
            String timeStamp = formatTime.format(getTime.getTime());

            try (BufferedWriter write = new BufferedWriter(new FileWriter(location, true))) {
                String lineToWrite = timeStamp + "," + rate;
                write.write(lineToWrite);

                write.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

    }
    
    // Rounding to 2 decimal place
    public static Double roundTo2DP(double number) {
        DecimalFormat roundFormat = new DecimalFormat(".##");
        return (Double.parseDouble(roundFormat.format(number)));
    }

}
