package app;

import objetos.Medico;
import objetos.Paciente;
import javax.swing.*;
import UI.LoginView;
import objetos.Usuario;

public class Main {
    public static void main(String[] args) {
        LoginView login = new LoginView();

        var usuario1 = new Paciente("Juan", "Pérez", "Cédula de ciudadanía",
                12345678, 3001234567.0, "asasad@gmail.com", "Calle 123 #45-67");

        var usuario2 = new Medico("alejo", "ruiz", "Cédula de ciudadanía",
        12345678, 3001234567.0, "asasad@gmail.com", "Calle 123 #45-67",true);
        
        System.out.println(usuario1.getPerfil());
        System.out.println("Nombre completo: " + usuario2.getPerfil());
        usuario2.setEstado(false);
        System.out.println("Nombre completo: " + usuario2.getPerfil());

        
    }


}
