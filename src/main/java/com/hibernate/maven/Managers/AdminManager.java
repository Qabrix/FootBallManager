package com.hibernate.maven.Managers;
import com.hibernate.maven.DBObjects.GeneralTable;
import com.hibernate.maven.DBObjects.Match;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Calendar;
import java.util.Date;

public class AdminManager extends HibSessionManager {

    @Override
    protected SessionFactory buildSessionFactory(){
        Configuration configObj = new Configuration();
        configObj.configure("hibernateAdmin.cfg.xml");
        configObj.addAnnotatedClass(Match.class);
        configObj.addAnnotatedClass(GeneralTable.class);
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();
        sessionFactory = configObj.buildSessionFactory(serviceRegistryObj);
        return sessionFactory;
    }
}
