package GUIpack.Admin;
import GUIpack.CEO.CeoGUI;
import GUIpack.QueryButton;

import javax.swing.*;
import java.awt.*;

public class AdminGUI extends CeoGUI {
    public static JTextField queryField;
    public AdminGUI(){
        queryField = new JTextField();
        queryField.setPreferredSize(new Dimension(200,30));
        add(queryField);
        add(new QueryButton(queryField));
    }
}
