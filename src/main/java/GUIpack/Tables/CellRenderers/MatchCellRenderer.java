package GUIpack.Tables.CellRenderers;

import GUIpack.Tables.RowClasses.MatchRow;
import GUIpack.Tables.Models.MatchTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MatchCellRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column)
    {
        MatchTableModel model = (MatchTableModel) table.getModel();
        MatchRow match = (MatchRow) model.getValueAtRow(row);

        if(column == 1){
            if(match.wasDraw())
                setBackground(Color.yellow);
            else if(match.teamOneHasWon())
                setBackground(Color.green);
        }else if(column == 2){
            if(match.teamTwoHasWon())
                setBackground(Color.green);
            else if(match.wasDraw())
                setBackground(Color.yellow);
            else if(match.teamOneHasWon())
                setBackground(Color.white);
        }else {
            setBackground(Color.white);
        }

        return super.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column);
    }
}