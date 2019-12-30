package com.hibernate.maven.Managers;

import org.h2.engine.Database;
import org.hibernate.Session;
import org.h2.engine.User;
import org.hibernate.SessionFactory;


public abstract class HibSessionManager{
    protected Session session;
    protected SessionFactory sessionFactory;
    public void openSession(){
        session = buildSessionFactory().openSession();
    }
    protected abstract SessionFactory buildSessionFactory();
    public Session getSession(){
        return session;
    }
}
