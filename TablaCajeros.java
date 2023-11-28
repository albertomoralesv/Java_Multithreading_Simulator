import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;

public class TablaCajeros {
    private static JFrame frame;
    private static TablaModel model;
    private static JTable table;

    public static void initialize() {
        frame = new JFrame("Cajeros");

        // Top row with the title
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(new JLabel("Cajeros"));

        String[] columnNames = { "Nombre", "Esperando en Caja", "Tomando Orden", "Esperando Cocinero",
                "Esperando Comida", "En Descanso" };
        model = new TablaModel(new Object[][] {}, columnNames);
        table = new JTable(model);

        frame.getContentPane().setLayout(new BorderLayout());
        // Add the top panel above the table
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

        frame.setSize(700, 420);
        frame.setLocationRelativeTo(null);
    }

    public static void showTable() {
        frame.setVisible(true);
    }

    public static void addRow(Object... rowData) {
        model.addRow(rowData);
    }

    public synchronized static void updateRow(int rowIndex, int stateIndex, String value) {
        model.updateRowData(rowIndex, stateIndex, value);
        updateColor();
        table.repaint();
    }

    private static void updateColor() {
        // Set the renderer only for the specified cell
        table.setDefaultRenderer(Object.class, new CenteredTextRenderer());
    }

    static class CenteredTextRenderer extends DefaultTableCellRenderer {

        public CenteredTextRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            Color backgroundColor;
            String text = value != null ? value.toString() : "";
            if (text.equals("")) {
                backgroundColor = table.getBackground();
            } else if (text.equals(" ")) {
                backgroundColor = new Color(79, 79, 79);
            } else {
                if (column != 0) {
                    if (column == 1) {
                        backgroundColor = Colors.cajerosEnCajaColor;
                    } else if (column == 2) {
                        backgroundColor = Colors.cajerosTomandoOrdenColor;
                    } else if (column == 3) {
                        backgroundColor = Colors.cajerosEsperandoCocineroColor;
                    } else if (column == 4) {
                        backgroundColor = Colors.cajerosEsperandoComidaColor;
                    } else {
                        backgroundColor = Colors.cajerosDescansandoColor;
                    }
                } else {
                    backgroundColor = table.getBackground();
                }
            }
            component.setBackground(backgroundColor);
            return component;
        }
    }
}
