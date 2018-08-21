package stock.market.simulator;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

public class viewGraph {

    JComboBox<String> names;

    // Class constructor
    public viewGraph(stockProfile[][] stockProfiles) {

        ButtonListener listener = new ButtonListener();

        JFrame frame = new JFrame("Graph");
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));

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

        JPanel selectGraph = new JPanel();
        selectGraph.setLayout(new GridLayout(2, 2, 5, 5));

        Border graphSelectBorder = BorderFactory.createTitledBorder("Choose Graph");
        selectGraph.setBorder(graphSelectBorder);

        Button btnViewGraph = new Button("View Graph");
        btnViewGraph.addActionListener(listener);

        selectGraph.add(lblshareName);
        selectGraph.add(names);

        selectGraph.add(new JLabel(""));
        selectGraph.add(btnViewGraph);

        frame.add(selectGraph);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }

    // Event handler for when "View Graph" button is pressed
    public class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent et) {

            try {

                String fileName = (String) names.getSelectedItem();

                createGraph graph = new createGraph(fileName);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

}
