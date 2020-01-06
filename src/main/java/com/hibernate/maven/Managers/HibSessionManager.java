package com.hibernate.maven.Managers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class HibSessionManager{
    private Session session;
    protected SessionFactory sessionFactory;

    HibSessionManager(){
        sessionFactory = buildSessionFactory();
    }

    public void openSession(){ session = sessionFactory.openSession(); }
    protected abstract SessionFactory buildSessionFactory();

    public Session getSession(){ return session; }
}
