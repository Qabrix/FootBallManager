package com.hibernate.maven;

import GUIpack.Admin.AdminGUI;
import GUIpack.CEO.CEOGUI;
import GUIpack.Client.ClientGUI;
import GUIpack.GUI;
import com.hibernate.maven.DBObjects.Match;
import com.hibernate.maven.Managers.ClientManager;
import com.hibernate.maven.Managers.HibSessionManager;

import javax.swing.*;

public class AppMain {

    public static HibSessionManager hibSessionManager;
    static Match matchObj; //test
    static GUI gui;
    static String chosenUser;

    public static void main(String[] args) {
        getUser();
        prepareUser(chosenUser);
        //((ClientManager)hibSessionManager).testConnection(matchObj);
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
        else
            //dodac sprawdzanie poprawnosci hasla
           chosenUser = username.getText();
    }

    public static void prepareUser(String username){
        switch (username){
            case "admin":
                //hibSessionManager = new AdminManager();
                gui = new AdminGUI();
                break;
            case "CEO":
                //hibSessionManager = new CEOManager();
                gui = new CEOGUI();
                break;
            case "client":
                hibSessionManager = new ClientManager();
                gui = new ClientGUI();
                break;
        }
    }
}