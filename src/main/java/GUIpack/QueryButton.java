package GUIpack;

import org.hibernate.SQLQuery;
import org.hibernate.jpa.spi.NativeQueryTupleTransformer;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import javax.persistence.Tuple;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        String myQuery = queryField.getText();
        hibSessionManager.openSession();

        Query query = hibSessionManager.getSession().createNativeQuery(myQuery);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String,Object>> aliasToValueMapList=query.list();
        for (Map<String, Object> stringObjectMap : aliasToValueMapList)
            for (Map.Entry<String, Object> entry : stringObjectMap.entrySet()) {
                String k = entry.getKey();
                Object v = entry.getValue();
                System.out.println("Key: " + k + ", Value: " + v);
            }



        hibSessionManager.getSession().close();
    }
}
