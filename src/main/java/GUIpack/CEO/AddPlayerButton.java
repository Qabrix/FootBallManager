package GUIpack.CEO;
import GUIpack.GUI;
import com.hibernate.maven.DBObjects.Match;
import com.hibernate.maven.DBObjects.Player;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static com.hibernate.maven.AppMain.hibSessionManager;

public class AddPlayerButton extends JButton implements ActionListener {
    private JTextField nameTF, surnameTF;
    private Object[] data;
    AddPlayerButton(){
        setText("Add Player");
        addActionListener(this);
    }

    private void setInputData(){
        nameTF = new JTextField();
        surnameTF = new JTextField();
        data = new Object[]{
                "Name:", nameTF,
                "Surname:", surnameTF,
        };
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        setInputData();
        int option = JOptionPane.showConfirmDialog(getParent(), data, "Add new match", JOptionPane.OK_CANCEL_OPTION);

        if(option==JOptionPane.OK_OPTION){
            Player player = new Player();
            player.setName(nameTF.getText());
            player.setSurname(surnameTF.getText());
            addPlayerToDB(player);
        }
    }

    public static void addPlayerToDB(Player player){
        hibSessionManager.openSession();
        hibSessionManager.getSession().beginTransaction();
        try {
            hibSessionManager.getSession().save(player);
            hibSessionManager.getSession().getTransaction().commit();
        }
        catch(Exception ex){
            hibSessionManager.getSession().getTransaction().rollback();
        }
        finally {
            hibSessionManager.getSession().close();
        }
    }
}
