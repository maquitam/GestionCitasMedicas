package app;

import UI.LoginView;
import UI.PacienteView;

public class Main {

    public static void main(String[] args) {
        PacienteView pacienteView = new PacienteView("usuarioEjemplo");
        pacienteView.setVisible(true);
    }
}