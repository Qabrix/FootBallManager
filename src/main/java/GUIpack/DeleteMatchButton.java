package GUIpack;

import com.hibernate.maven.DBObjects.Match;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static com.hibernate.maven.AppMain.hibSessionManager;

public class DeleteMatchButton extends JButton implements ActionListener {

    JTable matchTable;
    public DeleteMatchButton(JTable matchTable){
        setText("Delete Match");
        this.matchTable = matchTable;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int selectedId = Integer.parseInt(matchTable.getValueAt(matchTable.getSelectedRow(), 0).toString());
        Match selectedMatch = null;
        for(int i=0; i<GUI.matchList.size(); i++){
            if(GUI.matchList.get(i).getMatchId()==selectedId)
                selectedMatch = GUI.matchList.get(i);
        }
        deleteMatch(selectedMatch);
    }
    public static void deleteMatch(Match selectedMatch){
        hibSessionManager.openSession();
        hibSessionManager.getSession().beginTransaction();
        try{
            hibSessionManager.getSession().delete(selectedMatch);
            hibSessionManager.getSession().flush();
        }
        catch (Exception ex){
            hibSessionManager.getSession().getTransaction().rollback();
        }
        finally {
            hibSessionManager.getSession().close();
            GUI.refreshData();
        }
    }

}
