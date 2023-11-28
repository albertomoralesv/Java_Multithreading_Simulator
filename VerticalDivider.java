import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VerticalDivider extends JPanel {
    private GridBagConstraints gbc;
    private final int rows = 1;
    private int cols = 1;
    private ArrayList<CirclePanel> spaces = new ArrayList<>();

    public VerticalDivider() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        addInitialRows();
    }

    private void addInitialRows() {
        for (int row = 0; row < rows; row++) {
            addExtraRow();
        }
    }

    public void addExtraRow() {
        shiftComponentsDown();
        // Add an extra row at the bottom
        for (int col = 0; col < cols; col++) {
            CirclePanel circlePanel = new CirclePanel();
            circlePanel.setColor(Color.BLACK);
            circlePanel.setNumber(spaces.size());
            spaces.add(circlePanel);

            int cellSize = 100;
            Dimension cellDimension = new Dimension(cellSize, cellSize);
            circlePanel.setPreferredSize(cellDimension);

            gbc.gridx = col;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            add(circlePanel, gbc);
        }
    }

    private void shiftComponentsDown() {
        Component[] components = getComponents();
        for (Component component : components) {
            GridBagConstraints constraints = ((GridBagLayout) getLayout()).getConstraints(component);
            constraints.gridy++;
            ((GridBagLayout) getLayout()).setConstraints(component, constraints);
        }
    }

    public void setColor(int index, Color color) {
        if (index >= 0 && index < spaces.size()) {
            CirclePanel circlePanel = spaces.get(index);
            circlePanel.setColor(color);
        }
    }

    static class CirclePanel extends JPanel {
        private Color color = Color.BLUE; // Default color is blue
        private int number = 0; // Default number is 0

        public void setColor(Color color) {
            this.color = color;
            repaint(); // Repaint the panel when the color changes
        }

        public void setNumber(int number) {
            this.number = number;
            repaint(); // Repaint the panel when the number changes
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();

            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;

            // Fill the entire panel with the background color
            g2d.setColor(color);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            // Draw the number in the center of the panel
            g2d.setColor(Color.BLACK);
            Font font = new Font("Arial", Font.BOLD, 16);
            g2d.setFont(font);

            String numberString = String.valueOf(number);
            FontMetrics fontMetrics = g2d.getFontMetrics();
            int textWidth = fontMetrics.stringWidth(numberString);
            int textHeight = fontMetrics.getHeight();

            int x = centerX - textWidth / 2;
            int y = centerY + textHeight / 2;

            g2d.drawString(numberString, x, y);

            g2d.dispose();
        }

    }
}
