import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralTableModel extends DefaultTableModel {
    public GeneralTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);

    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == getColumnCount() - 1;
    }

    // Method to add the button column
    public void addButtonColumn(JTable table) {
        int lastColumnIndex = getColumnCount() - 1;
        TableColumn buttonColumn = table.getColumnModel().getColumn(lastColumnIndex);
        buttonColumn.setCellRenderer(new ButtonRenderer());
        buttonColumn.setCellEditor(new ButtonEditor(new JCheckBox()));
    }

    private static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    private static class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private int buttonName;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (buttonName == 1) {
                        TablaClientes.showTable();
                    }
                    if (buttonName == 8) {
                        TablaCajeros.showTable();
                    }
                    if (buttonName == 14) {
                        TablaCocineros.showTable();
                    }
                    if (buttonName == 18) {
                        DibujoGeneral.createAndShowGUI();
                    }
                }
            });
        }

        public void setButtonName(int rowIndex) {
            this.buttonName = rowIndex;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            button.setText((value == null) ? "" : value.toString());
            setButtonName(row);
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return button.getText();
        }
    }

    // Method to update the data in a specific row
    public void updateRowData(int rowIndex, String newN) {
        if (rowIndex >= 0 && rowIndex < getRowCount()) {
            setValueAt(newN, rowIndex, 1);
            fireTableRowsUpdated(rowIndex, rowIndex);
        } else {
            // Handle the case where the row index is out of bounds or the length of newData
            // doesn't match column count
        }
    }
}
