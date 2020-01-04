package GUIpack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static com.hibernate.maven.AppMain.hibSessionManager;
public class QueryButton extends JButton implements ActionListener {
    JTextField queryField;
    public QueryButton(JTextField qtf){
        setText("Perform Query");
        queryField=qtf;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String query = queryField.getText();
        hibSessionManager.openSession();
        //to nie dziala - naprawic
        hibSessionManager.getSession().createSQLQuery(query);
        hibSessionManager.getSession().close();
    }
}
