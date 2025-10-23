package app;


import javax.swing.*;
import UI.LoginView;
import objetos.Especialidad;

public class Main {
    public static void main(String[] args) {
        //LoginView login = new LoginView();
        Especialidad esp = new Especialidad("Cardiologia", "ESP001", true);
        System.out.println(esp.consultarEspecialidad());
    
    }
}
