package GUIpack.Table.Models;

import GUIpack.Table.RowClasses.GeneralTableRow;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class GeneralTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Team", "Points", "Goals for", "Goals against", "Matches played"};
    private final Class[] columnClasses = {String.class, Integer.class, Integer.class, Integer.class, Integer.class};

    private final Vector data = new Vector();

    public void addGeneralRow(GeneralTableRow match) {
        data.addElement(match);
        fireTableRowsInserted(data.size()-1, data.size()-1);
    }

    Object getValueAtRow(int row) {
        GeneralTableRow generalTableRow = (GeneralTableRow) data.elementAt(row);
        return generalTableRow;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public int getRowCount() {
        return data.size();
    }
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    @Override
    public Class getColumnClass(int c) {
        return columnClasses[c];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        GeneralTableRow generalTableRow = (GeneralTableRow) data.elementAt(row);
        if (col == 0)      return generalTableRow.getTeam();
        else if (col == 1) return generalTableRow.getPoints();
        else if (col == 2) return generalTableRow.getGoalsFor();
        else if (col == 3) return generalTableRow.getGoalsAgainst();
        else if (col == 4) return generalTableRow.getMatchesPlayed();
        else return null;
    }
}
