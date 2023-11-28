import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralTable {
    private static GeneralTableModel model;
    private static JTable table;

    public static void showTable() {
        TablaClientes.initialize();
        TablaCajeros.initialize();
        TablaCocineros.initialize();
        JFrame frame = new JFrame("Tablero General");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Top row with the title
        JPanel topPanel = new JPanel(new BorderLayout());

        // Left side of the panel with a button
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton leftButton = new JButton("Set Times");
        leftButton.setPreferredSize(new Dimension(leftButton.getPreferredSize().width, 15));
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TablaTiempos.showTable();
            }
        });
        leftPanel.add(leftButton);

        // Left side of the panel with a button
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton rightButton = new JButton("Creditos");
        rightButton.setPreferredSize(new Dimension(rightButton.getPreferredSize().width, 15));
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Creditos.createAndShowGUI();
            }
        });
        rightPanel.add(rightButton);

        // Center of the panel with a label
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(new JLabel("Tablero General"));

        // Add leftPanel and centerPanel to topPanel
        topPanel.add(leftPanel, BorderLayout.WEST);
        topPanel.add(centerPanel, BorderLayout.CENTER);
        topPanel.add(rightPanel, BorderLayout.EAST);

        String[] columnNames = { "State", "Number of Agents", "" };
        model = new GeneralTableModel(new Object[][] {}, columnNames);
        // Pendientes
        addRow("En Fila Para Entrar", "0", "");
        // Clientes
        addRow("Total Clientes", "0", "Details");
        addRow("Clientes en Zona de Juegos", "0", "");
        addRow("Clientes en Fila para Ordenar", "0", "");
        addRow("Clientes Ordenando", "0", "");
        addRow("Clientes Esperando Comida de Cajero", "0", "");
        addRow("Clientes Esperando Mesa", "0", "");
        addRow("Clientes Comiendo", "0", "");
        // Cajeros
        addRow("Total Cajeros", "0", "Details");
        addRow("Cajeros Esperando en Caja", "0", "");
        addRow("Cajeros Tomando Orden", "0", "");
        addRow("Cajeros Esperando Cocinero", "0", "");
        addRow("Cajeros Esperando Comida de Cocinero", "0", "");
        addRow("Cajeros en Descanso", "0", "");
        // Cocineros
        addRow("Total Cocineros", "0", "Details");
        addRow("Cocineros Esperando en Cocina", "0", "");
        addRow("Cocineros Cocinando", "0", "");
        addRow("Cocineros en Descanso", "0", "");
        // Total
        addRow("Total", "0", "Show");
        table = new JTable(model);
        table.setDefaultRenderer(Object.class, new CustomRenderer());

        // Add button column
        model.addButtonColumn(table);

        // South Panel with buttons
        JPanel southPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton button1 = new JButton("Nuevo Cliente");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RestaurantController
                        .createClient(
                                new Cliente(Nombres.getName(),
                                        RestaurantController.getTotalClientes()));
            }
        });
        JButton button2 = new JButton("Nuevo Cajero");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RestaurantController
                        .createCajero(
                                new Cajero("Cajero" + String.valueOf(RestaurantController.getCajerosNumber()),
                                        RestaurantController.getCajerosNumber()));
            }
        });
        JButton button3 = new JButton("Nuevo Cocinero");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RestaurantController.createCocinero(
                        new Cocinero("Cocinero" + String.valueOf(RestaurantController.getCocinerosNumber()),
                                RestaurantController.getCocinerosNumber()));
            }
        });

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        southPanel.add(buttonPanel, BorderLayout.CENTER);

        frame.getContentPane().setLayout(new BorderLayout());
        // Add the top panel above the table
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
        frame.getContentPane().add(southPanel, BorderLayout.SOUTH);

        frame.setSize(750, 420);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void addRow(Object... rowData) {
        model.addRow(rowData);
    }

    public static void updateRow(int rowIndex, String value) {
        model.updateRowData(rowIndex, value);
    }

    static class CustomRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                    column);
            if (row == 0) {
                rendererComponent.setBackground(Colors.enFilaParaEntrarColor);
            } else if (row == 1) {
                rendererComponent.setBackground(Colors.totalClientesColor);
            } else if (row == 2) {
                rendererComponent.setBackground(Colors.clientesZonaDeJuegosColor);
            } else if (row == 3) {
                rendererComponent.setBackground(Colors.clientesFilaParaOrdenarColor);
            } else if (row == 4) {
                rendererComponent.setBackground(Colors.clientesOrdenandoColor);
            } else if (row == 5) {
                rendererComponent.setBackground(Colors.clientesEsperandoComidaColor);
            } else if (row == 6) {
                rendererComponent.setBackground(Colors.clientesEsperandoMesaColor);
            } else if (row == 7) {
                rendererComponent.setBackground(Colors.clientesComiendoColor);
            } else if (row == 8) {
                rendererComponent.setBackground(Colors.totalCajerosColor);
            } else if (row == 9) {
                rendererComponent.setBackground(Colors.cajerosEnCajaColor);
            } else if (row == 10) {
                rendererComponent.setBackground(Colors.cajerosTomandoOrdenColor);
            } else if (row == 11) {
                rendererComponent.setBackground(Colors.cajerosEsperandoCocineroColor);
            } else if (row == 12) {
                rendererComponent.setBackground(Colors.cajerosEsperandoComidaColor);
            } else if (row == 13) {
                rendererComponent.setBackground(Colors.cajerosDescansandoColor);
            } else if (row == 14) {
                rendererComponent.setBackground(Colors.totalCocinerosColor);
            } else if (row == 15) {
                rendererComponent.setBackground(Colors.cocinerosEnCocinaColor);
            } else if (row == 16) {
                rendererComponent.setBackground(Colors.cocinerosCocinandoColor);
            } else if (row == 17) {
                rendererComponent.setBackground(Colors.cocinerosDescansandoColor);
            } else if (row == 18) {
                rendererComponent.setBackground(Colors.totalColor);
            }

            return rendererComponent;
        }
    }
}
