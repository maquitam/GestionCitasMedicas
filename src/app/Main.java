package app;

import UI.LoginView;
import objetos.Especialidad;

public class Main {
    public static void main(String[] args) {
        // new LoginView(); --> Inicia la aplicación.
        Especialidad esp = new Especialidad("Cardiologia", "ESP001", true);
        System.out.println(esp.consultarEspecialidad());
    
    }
}
