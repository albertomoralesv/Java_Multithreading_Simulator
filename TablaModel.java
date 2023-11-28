import javax.swing.table.DefaultTableModel;

public class TablaModel extends DefaultTableModel {
    public TablaModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    // Method to update the data in a specific row
    public synchronized void updateRowData(int rowIndex, int stateIndex, String value) {
        if (stateIndex == 0) {
            for (int i = 0; i < getColumnCount(); i++) {
                setValueAt(" ", rowIndex, i);
            }
        } else if (rowIndex < getRowCount()) {
            for (int i = 1; i < getColumnCount(); i++) {
                if (i == stateIndex) {
                    setValueAt(value, rowIndex, i);
                } else {
                    setValueAt("", rowIndex, i);
                }
            }
            fireTableRowsUpdated(rowIndex, rowIndex);
        } else {
            // Handle the case where the row index is out of bounds or the length of newData
            // doesn't match column count
        }
    }
}
