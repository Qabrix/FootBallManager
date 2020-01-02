package GUIpack.Tables.Models;

import GUIpack.Tables.RowClasses.MatchRow;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class MatchTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Team 1", "Team 2", "Goals Team 1", "Goals Team 2", "Date", "Host"};
    private final Class[] columnClasses = {String.class, String.class, Integer.class, Integer.class, String.class, String.class};

    private final Vector data = new Vector();

    public void addMatch(MatchRow match) {
        data.addElement(match);
        fireTableRowsInserted(data.size()-1, data.size()-1);
    }

    public Object getValueAtRow(int row) {
        MatchRow match = (MatchRow) data.elementAt(row);
        return match;
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
        MatchRow match = (MatchRow) data.elementAt(row);
        if (col == 0)      return match.getTeamOne();
        else if (col == 1) return match.getTeamTwo();
        else if (col == 2) return match.getGoalsTeamOne();
        else if (col == 3) return match.getGoalsTeamTwo();
        else if (col == 4) return match.getMatchDate();
        else if (col == 5) return match.getHost();
        else return null;
    }
}
