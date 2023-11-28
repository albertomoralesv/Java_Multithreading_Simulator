import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;

public class TablaClientes {
    private static JFrame frame;
    private static TablaModel model;
    private static JTable table;

    public static void initialize() {
        frame = new JFrame("Clientes");

        // Top row with the title
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(new JLabel("Clientes"));

        String[] columnNames = { "Nombre", "Zona de Juegos", "Fila para Ordenar", "Ordenando", "Esperando Comida",
                "Esperando Mesa", "Comiendo" };
        model = new TablaModel(new Object[][] {}, columnNames);
        table = new JTable(model);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new CenteredTextRenderer());
        }

        frame.getContentPane().setLayout(new BorderLayout());
        // Add the top panel above the table
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

        frame.setSize(900, 420);
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
                        backgroundColor = Colors.clientesZonaDeJuegosColor;
                    } else if (column == 2) {
                        backgroundColor = Colors.clientesFilaParaOrdenarColor;
                    } else if (column == 3) {
                        backgroundColor = Colors.clientesOrdenandoColor;
                    } else if (column == 4) {
                        backgroundColor = Colors.clientesEsperandoComidaColor;
                    } else if (column == 5) {
                        backgroundColor = Colors.clientesEsperandoMesaColor;
                    } else {
                        backgroundColor = Colors.clientesComiendoColor;
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
