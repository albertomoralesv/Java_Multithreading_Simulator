import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class DibujoRestaurantePrincipal extends JPanel {
    public DibujoRestaurantePrincipal() {
        setLayout(new GridBagLayout());

        // Create four instances of filaParaEntrar and add them to the main panel
        for (int i = 0; i < 3; i++) {
            JPanel panel = null;
            if (i == 0) {
                panel = new DibujoRestauranteCCC();
            } else if (i == 1) {
                panel = new VerticalDivider();
            } else {
                panel = new DibujoZonaDescanso();
            }

            if (panel != null) {
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = i;
                gbc.gridy = 0;

                // Set weighty based on the panel index
                gbc.weightx = getWeightY(i);
                gbc.weighty = 1;

                gbc.fill = GridBagConstraints.BOTH;
                gbc.anchor = GridBagConstraints.CENTER;

                // Add the panel to the main layout with the specific constraints
                add(panel, gbc);
            }
        }
    }

    private double getWeightY(int panelIndex) {
        // Example: You can set different weighty values based on the panel index
        switch (panelIndex) {
            case 0:
                return 0.8;
            case 1:
                return 0.02;
            case 2:
                return 0.18;
            default:
                return 1.0; // Default weighty value
        }
    }
}