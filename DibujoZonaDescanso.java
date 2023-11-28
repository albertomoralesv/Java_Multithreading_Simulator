import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class DibujoZonaDescanso extends JPanel {
    private GridBagConstraints gbc;
    private final int rows = 10;
    private int cols = 3;
    private ArrayList<CirclePanel> spaces = new ArrayList<>();
    private int max;

    public DibujoZonaDescanso() {
        max = rows / cols;
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        addInitialRows();
        EmpleadosDescansoDibujoController.setDibujo(this);
        EmpleadosDescansoDibujoController.paint(this);
    }

    private void addInitialRows() {
        for (int col = 0; col < cols; col++) {
            addExtraColumn(col);
        }
    }

    public void addExtraColumn(int c) {
        gbc.gridx = c;
        for (int row = 0; row < rows; row++) {
            CirclePanel circlePanel = new CirclePanel();
            circlePanel.setColor(getBackground());
            circlePanel.setNumber(spaces.size());
            spaces.add(circlePanel);

            int cellSize = 100;
            Dimension cellDimension = new Dimension(cellSize, cellSize);
            circlePanel.setPreferredSize(cellDimension);

            gbc.gridy = row;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            add(circlePanel, gbc);
        }
        revalidate();
        repaint();
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

            g2d.setColor(Color.BLACK);
            g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

            int radius = Math.min(getWidth(), getHeight()) / 5;
            g2d.setColor(color);
            g2d.fill(new Ellipse2D.Double(centerX - radius, centerY - radius, 2 * radius, 2 * radius));

            // Draw the number in the center of the circle
            g2d.setColor(Color.BLACK);
            Font font = new Font("Arial", Font.BOLD, 0);
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

    public int getMax() {
        return max;
    }

    public void setMax() {
        max += rows;
    }
}
