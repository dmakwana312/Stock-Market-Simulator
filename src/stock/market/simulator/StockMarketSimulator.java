package stock.market.simulator;

import java.util.Random;
import java.text.DecimalFormat;

import java.util.Timer;
import java.util.TimerTask;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StockMarketSimulator {
    
    // Path to where the files are stored for rate history 
    // USE WHEN RUNNING PROJECT IN NETBEANS
    //public static final String HISTORYFILEPATH = "src/stock/market/simulator/history/";

    // Path to history files to be used when executing program through jar file
    public static final String HISTORYFILEPATH = "history/";
    
    public static void main(String[] args) throws IOException {

        accountProfile accProfile = accountCreation();

        stockProfile[][] stockProfile = createAllStocks();

        deleteHistoryFiles(new File(HISTORYFILEPATH));
        createHistoryFiles(stockProfile);

        mainWindow window = new mainWindow(accProfile, stockProfile);

        recalculationLoop(stockProfile, window);

    }

    // Using a timer to recalculate the stock rates every 5 seconds and display them
    public static void recalculationLoop(stockProfile[][] stockProfile, mainWindow window) {
        Timer t1 = new Timer();
        t1.schedule(new TimerTask() {
            @Override
            public void run() {
                recalulationPrice(stockProfile);
                window.setTextBoxValues(stockProfile);

            }
        }, 0, 5000);
    }

    // Function to create an account profile for the user
    public static accountProfile accountCreation() {
        createAccount accCreate = new createAccount();

        boolean successfulCreate;
        Object sync = new Object();
        do {
            // Variables are synchronized inorder to break from the do-while loop 
            // when boolean expression is met
            synchronized (sync) {
                successfulCreate = accCreate.getCreated();
            }
        } while (!successfulCreate);

        accountProfile accProfile = accCreate.getAccountProfile();
        accCreate.terminate();
        return accProfile;
    }
    
    // Procedure to recalculate the stock rate when called on by the timer
    public static void recalulationPrice(stockProfile[][] stocks) {

        Random number = new Random();

        double priceChange;
        stockProfile stockToEdit;

        for (stockProfile[] stockArray : stocks) {
            for (stockProfile stock : stockArray) {
                priceChange = number.nextDouble() + 0.1;
                int increaseORdecrease = number.nextInt(10) + 1;

                stockToEdit = stock;
                if (increaseORdecrease > 6) {
                    increasePrice(stockToEdit, priceChange);

                } else {
                    decreasePrice(stockToEdit, priceChange);
                }

            }
        }

    }
    
    // Setting the stock rates once they have been recalculated
    public static void setPriceChange(stockProfile profile, double sellChange, double buyChange) {
        double difference = ((sellChange - profile.getSellPrice()) / profile.getSellPrice() * 100);
        profile.setChange(roundTo2DP(difference));
        profile.setSellPrice(roundTo4DP(sellChange));
        profile.setBuyPrice(roundTo4DP(buyChange));

    }
    
    // Calculating an increase in stock price
    public static void increasePrice(stockProfile profile, double priceChange) {
        double sellChange = priceChange
                + (profile.getSellPrice() + ((profile.getSellPrice() * profile.getMargin()) / 2));
        double buyChange = priceChange
                + (profile.getBuyPrice() + ((profile.getBuyPrice() * profile.getMargin()) / 2));

        setPriceChange(profile, sellChange, buyChange);

    }
    
    // Calculating a decrease in stock price
    public static void decreasePrice(stockProfile profile, double priceChange) {
        double sellChange = priceChange
                + (profile.getSellPrice() - ((profile.getSellPrice() * profile.getMargin()) / 2));
        double buyChange = priceChange
                + (profile.getBuyPrice() + ((profile.getBuyPrice() * profile.getMargin()) / 2));

        setPriceChange(profile, sellChange, buyChange);

    }
    
    // Rounding to 2 decimal place
    public static Double roundTo2DP(double number) {
        DecimalFormat roundFormat = new DecimalFormat(".##");
        return (Double.parseDouble(roundFormat.format(number)));
    }

    // Rounding to 4 decimal place
    public static Double roundTo4DP(double number) {
        DecimalFormat roundFormat = new DecimalFormat(".####");
        return (Double.parseDouble(roundFormat.format(number)));
    }
    
    // Creating all stock profiles
    public static stockProfile[][] createAllStocks() {
        // BASIC INFORMATION FOR CURRENCY STOCK
        String[][] from_To = {{"EUR", "USD"}, {"GBP", "USD"}, {"EUR", "GBP"}, {"GBP", "JPY"}};
        double[][] currencyPrice = {{1.2183, 1.2185}, {1.3767, 1.3768}, {0.88491, 0.88511},
        {147.279, 147.320}};

        // BASIC INFORMATION FOR COMPANY STOCK
        String[] companyName = {"Facebook", "Apple", "Microsoft", "BMW"};
        double[] companyMargin = {0.05, 0.05, 0.05, 0.5};
        double[][] companyPrice = {{178.31, 178.56}, {178.12, 178.37}, {93.96, 93.05}, {85.87, 85.99}};

        // BASIC INFORMATION FOR ECONOMY STOCK
        String[] countryName = {"UK", "USA", "AUS", "JPY"};
        String[] stockName = {"FTSE 100", "Dow Jones", "$AUSSIE200", "NIKKEI 225"};
        double[] economyMargin = {0.005, 0.005, 0.01, 0.01};
        double[][] economyPrice = {{718.18, 718.28}, {25056, 25060}, {5974.8, 5978.3}, {21634, 21642}};

        stockProfile[][] stocks = {createCurrencyStock(from_To, currencyPrice),
            createCompanyStock(companyName, companyMargin, companyPrice),
            createEconomyStock(countryName, stockName, economyMargin, economyPrice)};

        return stocks;
    }

    // Function to create the currency stocks
    public static currencyStock[] createCurrencyStock(String[][] from_To, double[][] price) {
        currencyStock[] stocks = new currencyStock[from_To.length];

        for (int i = 0; i < from_To.length; i++) {
            stocks[i] = new currencyStock(from_To[i][0], from_To[i][1], price[i][0], price[i][1]);

        }

        return stocks;
    }

    // Function to create the company stocks
    public static companyStock[] createCompanyStock(String[] companyName, double[] margin, double[][] price) {
        companyStock[] stocks = new companyStock[companyName.length];

        for (int i = 0; i < companyName.length; i++) {

            stocks[i] = new companyStock(companyName[i], margin[i], price[i][0], price[i][1]);

        }

        return stocks;
    }

    // Function to create the economy stocks
    public static economyStock[] createEconomyStock(String[] countryName, String[] stockName, double[] margin,
            double[][] price) {
        economyStock[] stocks = new economyStock[countryName.length];

        for (int i = 0; i < countryName.length; i++) {

            stocks[i] = new economyStock(countryName[i], stockName[i], margin[i], price[i][0], price[i][1]);

        }

        return stocks;
    }

    // Procedure to create the history files
    public static void createHistoryFiles(stockProfile[][] stocks) throws IOException {

        String fileName;
        FileWriter fileWriter;

        for (stockProfile[] stockArray : stocks) {
            for (stockProfile stock : stockArray) {
                fileName = stock.getProfileName() + ".csv";
                fileWriter = new FileWriter(HISTORYFILEPATH + fileName);
            }
        }

    }

    // Procedure to delete the history files
    public static void deleteHistoryFiles(File directory) {
        for (File file : directory.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }

    }

}
