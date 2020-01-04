package GUIpack.Tables.Models;
import GUIpack.Tables.RowClasses.MatchRow;
import GUIpack.Tables.RowClasses.TeamRow;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class TeamTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Name"};
    private final Class[] columnClasses = {String.class, Integer.class};
    private final Vector data = new Vector();

    public void addTeam(TeamRow team) {
        data.addElement(team);
        fireTableRowsInserted(data.size()-1, data.size()-1);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
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
        TeamRow teamRow = (TeamRow) data.elementAt(row);
        if (col == 0)      return teamRow.getId();
        else if (col == 1) return teamRow.getName();
        else return null;
    }
}
