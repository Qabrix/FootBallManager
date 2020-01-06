package GUIpack.Admin;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import static com.hibernate.maven.AppMain.hibSessionManager;

public class QueryButton extends JButton implements ActionListener {
    private JTextField queryField;
    private JTextArea textArea;
    QueryButton(JTextField qtf, JTextArea ta){
        textArea = ta;
        setText("Perform Query");
        queryField=qtf;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try{
            String myQuery = queryField.getText();
            StringBuilder message = new StringBuilder();
            hibSessionManager.openSession();
            hibSessionManager.getSession().beginTransaction();

            Query query = hibSessionManager.getSession().createNativeQuery(myQuery);
            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            try {
                listResults(query, message);
            }catch (Exception ignored){

            }
            textArea.setText(message.toString());
            hibSessionManager.getSession().getTransaction().commit();
        }catch (Exception ex){
            ex.printStackTrace();
            hibSessionManager.getSession().getTransaction().rollback();
        }finally {
            hibSessionManager.getSession().close();
        }
    }

    private void listResults(Query query, StringBuilder message) {
        List<Map<String,Object>> aliasToValueMapList=query.list();
        for (Map<String, Object> stringObjectMap : aliasToValueMapList) {
            for (Map.Entry<String, Object> entry : stringObjectMap.entrySet()) {
                String k = entry.getKey();
                Object v = entry.getValue();
                message.append(k).append(": ").append(v).append("\n");
            }
            message.append("\n");
        }
    }
}
