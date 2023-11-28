import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartTable {
    private static int restaurantCapacity;
    private static int numberOfTables;
    private static int gameZoneCapacity;

    private StartTable() {
        throw new AssertionError("Cannot instantiate StartTable");
    }

    public static void showTable(TableClosedListener listener) {
        JFrame frame = new JFrame("McSimulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create title row
        JLabel titleLabel = new JLabel("McSimulator", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create table panel with GridBagLayout
        JPanel tablePanel = new JPanel(new GridBagLayout());

        // Column titles
        addBorderedLabel(tablePanel, "Restaurant Capacity", 0, 0);
        addBorderedLabel(tablePanel, "Number of Tables", 1, 0);
        addBorderedLabel(tablePanel, "Game Zone Capacity", 2, 0);

        // Input fields
        JTextField restaurantCapacityField = new JTextField();
        JTextField numberOfTablesField = new JTextField();
        JTextField gameZoneCapacityField = new JTextField();
        addBorderedTextField(tablePanel, restaurantCapacityField, 0, 1);
        addBorderedTextField(tablePanel, numberOfTablesField, 1, 1);
        addBorderedTextField(tablePanel, gameZoneCapacityField, 2, 1);

        mainPanel.add(tablePanel, BorderLayout.CENTER);

        // Button row
        JButton button = new JButton("Open Restaurant");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean correctData = saveEntryData(restaurantCapacityField.getText(), numberOfTablesField.getText(),
                        gameZoneCapacityField.getText());
                if (correctData) {
                    frame.dispose(); // Close the frame when the button is clicked
                    // Call the listener with the saved data
                    if (listener != null) {
                        listener.onTableClosed();
                    }
                }
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(button);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Set up the frame
        frame.getContentPane().add(mainPanel);
        frame.setSize(400, 160); // Adjusted the frame height
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void addBorderedLabel(JPanel panel, String text, int gridx, int gridy) {
        JLabel label = new JLabel(text, JLabel.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding
        panel.add(label, gbc);
    }

    private static void addBorderedTextField(JPanel panel, JTextField textField, int gridx, int gridy) {
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBorder(new LineBorder(Color.BLACK)); // Add a border to the text field

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make the text field expand horizontally
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding
        panel.add(textField, gbc);
    }

    private static boolean saveEntryData(String restaurantCap, String tableNum, String gameZoneCap) {
        int correctData = 0;
        try {
            // Convert String to int
            restaurantCapacity = Integer.parseInt(restaurantCap);
            correctData++;
        } catch (NumberFormatException e) {
        }
        try {
            // Convert String to int
            numberOfTables = Integer.parseInt(tableNum);
            correctData++;
        } catch (NumberFormatException e) {
        }
        try {
            // Convert String to int
            gameZoneCapacity = Integer.parseInt(gameZoneCap);
            correctData++;
        } catch (NumberFormatException e) {
        }
        if (correctData == 3 && restaurantCapacity > 0 && numberOfTables > 0 && gameZoneCapacity > 0) {
            return true;
        }
        return false;
    }

    public static int getRestaurantCapacity() {
        return restaurantCapacity;
    }

    public static int getNumberOfTables() {
        return numberOfTables;
    }

    public static int getGameZoneCapacity() {
        return gameZoneCapacity;
    }

    public interface TableClosedListener {
        void onTableClosed();
    }
}
