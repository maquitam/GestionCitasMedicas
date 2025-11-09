package app;

import UI.LoginView;
import UI.PacienteView;
import objetos.Paciente;
import servicios.UsuarioSesion;

public class Main {

    public static void main(String[] args) {

        Paciente pacienteTest = new Paciente(
                1, "Sebastian", "Lopez Martinez", "CC", "123456",
                "20-05-2002", "MASCULINO", "O+",
                "sebastian.lopez@gmail.com", "3014587923",
                "Medellin", "Paciente", "1234");

        /*
         * Paciente pacienteTest = new Paciente(
         * 0, "María Camila", "Parra Morales", "CC", "1090377346",
         * "16-11-2004", "FEMENINO", "A+",
         * "mcparram1611@gmail.com", "3022480598",
         * "Sabaneta", "Paciente", "123");
         */

        UsuarioSesion.setUsuarioActual(pacienteTest);

        new PacienteView(pacienteTest.getNumeroDoc());

    }
}