package com.hibernate.maven;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class AppMain {

    static Match matchObj;
    static Session sessionObj;
    static SessionFactory sessionFactoryObj;

    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");

        // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();

        // Creating Hibernate SessionFactory Instance
        sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
        return sessionFactoryObj;
    }

    public static void main(String[] args) {
        System.out.println(".......Hibernate Maven Example.......\n");
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            for(int i = 13; i <= 16; i++) {
                matchObj = new Match();
                matchObj.setGoalsTeamOne(i%3);
                matchObj.setGoalsTeamTwo(i%2);
                matchObj.setHostId((i%3)%2+1);
                matchObj.setTeamOneId((i+1)%2+1);
                matchObj.setTeamTwoId((i)%2+1);
                matchObj.setMatchId(i);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, 1999);
                calendar.set(Calendar.MONTH, 5+i%12);
                calendar.set(Calendar.DATE, i+1);
                Date date = calendar.getTime();
                matchObj.setCreatedDate(date);

                sessionObj.save(matchObj);
            }
            System.out.println("\n.......Records Saved Successfully To The Database.......\n");

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }
}