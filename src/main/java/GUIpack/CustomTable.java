package GUIpack;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CustomTable {

    private JTable table;
    private DefaultTableModel model;
    private String []header;
    private String [][]rec;
    public CustomTable(String []header, String[][]rec){
        this.header = header;
        this.rec = rec;
        setTable();
    }

    public void setTable(){
        model = new DefaultTableModel(rec, header);
        table = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public String[][] getRec() {
        return rec;
    }

    public void setRec(String[][] rec) {
        this.rec = rec;
    }

}
