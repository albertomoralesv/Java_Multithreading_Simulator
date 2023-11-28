import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class DibujoRestauranteCompleto extends JPanel {
    public DibujoRestauranteCompleto() {
        setLayout(new GridBagLayout());

        // Create four instances of panels and add them to the main panel
        for (int i = 0; i < 9; i++) {
            JPanel panel = null;

            if (i == 0) {
                panel = new HorizontalDivider();
            } else if (i == 1) {
                panel = new DibujoZonaDeJuegos();
            } else if (i == 2) {
                panel = new HorizontalDivider();
            } else if (i == 3) {
                panel = new DibujoRestaurantePrincipal();
            } else if (i == 4) {
                panel = new HorizontalDivider();
            } else if (i == 5) {
                panel = new DibujoEsperarMesa();
            } else if (i == 6) {
                panel = new HorizontalDivider();
            } else if (i == 7) {
                panel = new DibujoMesas();
            } else if (i == 8) {
                panel = new HorizontalDivider();
            }

            if (panel != null) {
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 1;
                gbc.gridy = i;

                // Set weighty based on the panel index
                gbc.weightx = 1;
                gbc.weighty = getWeightY(i);

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
                return 0.01;
            case 1:
                return 0.2;
            case 2:
                return 0.01;
            case 3:
                return 0.45;
            case 4:
                return 0.01;
            case 5:
                return 0.1;
            case 6:
                return 0.01;
            case 7:
                return 0.2;
            case 8:
                return 0.01;
            default:
                return 1.0; // Default weighty value
        }
    }
}
