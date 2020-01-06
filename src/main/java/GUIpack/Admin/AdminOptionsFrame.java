package GUIpack.Admin;

import GUIpack.ExportButton;
import GUIpack.ImportButton;

import javax.swing.*;
import java.awt.*;

public class AdminOptionsFrame extends JFrame {
    private static final int DEFLAUT_WIDTH = 800,
            DEFLAUT_HEIGHT = 800;

    public AdminOptionsFrame(){
        this(DEFLAUT_WIDTH, DEFLAUT_HEIGHT, "Bez tytulu");
    }

    public AdminOptionsFrame(String title){
        this(DEFLAUT_WIDTH, DEFLAUT_HEIGHT, title);
    }

    public AdminOptionsFrame(final int frameWidth, final int frameHeight) {
        this(frameWidth, frameHeight, "Bez tytulu");
    }

    public AdminOptionsFrame(final int frameWidth, final int frameHeight, final String title) {
        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        JScrollPane jp = new JScrollPane(ta);
        jp.setPreferredSize(new Dimension(350,300));

        this.setTitle(title);
        this.setSize(frameWidth, frameHeight);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new FlowLayout());

        JTextField queryField = new JTextField();
        queryField.setPreferredSize(new Dimension(250,30));
        this.add(queryField);
        this.add(new QueryButton(queryField, ta));
        this.add(jp);
        this.add(new ExportButton());
        this.add(new ImportButton());
    }
}
