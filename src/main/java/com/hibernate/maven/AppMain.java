package com.hibernate.maven;

import GUIpack.Admin.AdminGUI;
import GUIpack.CEO.CEOGUI;
import GUIpack.Client.ClientGUI;
import GUIpack.GUI;
import com.hibernate.maven.Managers.*;
import org.hibernate.jdbc.ReturningWork;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class AppMain {

    public static HibSessionManager hibSessionManager;
    static GUI gui;
    static String chosenUser;

    public static void main(String[] args) {
        getUser();
        prepareUser(chosenUser);
    }

    private static void getUser(){
        JTextField username = new JTextField();
        JTextField password = new JPasswordField();
        Object[] data = {
                "Username:", username,
                "Password:", password
        };
        int option = JOptionPane.showConfirmDialog(null, data, "Login", JOptionPane.OK_CANCEL_OPTION);
        if(option==JOptionPane.CANCEL_OPTION || option==JOptionPane.CLOSED_OPTION)
            System.exit(0);
        else{
            chosenUser = username.getText();
            if(checkIfPassAreNotCorrect(chosenUser, password.getText()))
                getUser();
        }
    }

    private static boolean checkIfPassAreNotCorrect(String login, String pass) {
        LoginManager loginSessionManager = new LoginManager();
        loginSessionManager.openSession();

        String funcQuery = String.format("SELECT arePassCorrect('%s','%s')",login.trim(),pass);
        Boolean arePassCorect = (Boolean) loginSessionManager.getSession().createSQLQuery(funcQuery).list().get(0);

        if(arePassCorect == null){
            JOptionPane.showMessageDialog(null,"User doesn't exist!");
            return true;
        }
        else if(!arePassCorect)
            JOptionPane.showMessageDialog(null,"Wrong Password!");

        return !arePassCorect;
    }

    public static void prepareUser(String username){
        switch (username.trim()){
            case "admin":
                hibSessionManager = new AdminManager();
                gui = new AdminGUI();
                break;
            case "ceo":
                hibSessionManager = new CEOManager();
                gui = new CEOGUI();
                break;
            case "client":
                hibSessionManager = new ClientManager();
                gui = new ClientGUI();
                break;
        }
    }
}