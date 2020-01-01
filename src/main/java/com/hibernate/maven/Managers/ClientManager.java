package com.hibernate.maven.Managers;
import com.hibernate.maven.DBObjects.GeneralTable;
import com.hibernate.maven.DBObjects.Match;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Calendar;
import java.util.Date;

public class ClientManager extends HibSessionManager {

    @Override
    protected SessionFactory buildSessionFactory(){
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");
        configObj.addAnnotatedClass(Match.class);
        configObj.addAnnotatedClass(GeneralTable.class);
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();
        sessionFactory = configObj.buildSessionFactory(serviceRegistryObj);
        return sessionFactory;
    }

    public void testConnection(Match matchObj) {
        System.out.println(".......Hibernate Maven Example.......\n");
        try {

            session.beginTransaction();

            for (int i = 21; i <= 22; i++) {
                matchObj = new Match();
                matchObj.setGoalsTeamOne(i % 3);
                matchObj.setGoalsTeamTwo(i % 2);
                matchObj.setHostId((i % 3) % 2 + 1);
                matchObj.setTeamOneId((i + 1) % 2 + 1);
                matchObj.setTeamTwoId((i) % 2 + 1);
                matchObj.setMatchId(i);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, 1999);
                calendar.set(Calendar.MONTH, 5 + i % 12);
                calendar.set(Calendar.DATE, i + 1);
                Date date = calendar.getTime();
                matchObj.setMatchDate(date);

                session.save(matchObj);
            }
            System.out.println("\n.......Records Saved Successfully To The Database.......\n");

            // Committing The Transactions To The Database
            session.getTransaction().commit();
        } catch (Exception sqlException) {
            if (null != session.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }

        }
    }
}
