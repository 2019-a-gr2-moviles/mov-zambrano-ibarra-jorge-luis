import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {

    public JPanel panelapp;
    public JButton btnEmpleado;
    public JButton clientesButton;
    private JButton reservas;


    public App() {
        btnEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Empleado.fram1 = new JFrame("Menú");
                Empleado.fram1.setContentPane(new Empleado().panelEmpleado);
                Empleado.fram1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Empleado.fram1.setSize(400,400);
                Empleado.fram1.setResizable(false);
                Empleado.fram1.setLocationRelativeTo(null);
                Empleado.fram1.setVisible(true);
            }
        });
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Cliente.fram1 = new JFrame("Ingresar Cliente");
                Cliente.fram1.setContentPane(new Cliente().panel1);
                Cliente.fram1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Cliente.fram1.setSize(400,400);
                Cliente.fram1.setResizable(false);
                Cliente.fram1.setLocationRelativeTo(null);
                Cliente.fram1.setVisible(true);
            }
        });

        reservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Reserva.fram1= new JFrame("Reserva");
                Reserva.fram1.setContentPane(new Reserva().panel1);
                Reserva.fram1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Reserva.fram1.setSize(400,400);
                Reserva.fram1.setResizable(false);
                Reserva.fram1.setLocationRelativeTo(null);
                Reserva.fram1.setVisible(true);
            }
        });
    }

    public  static void main(String []args){

        JFrame fram1 = new JFrame("Cancha Sintetica");
        fram1.setContentPane(new App().panelapp);
        fram1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fram1.setSize(400,400);
        fram1.setResizable(false);
        fram1.setLocationRelativeTo(null);
        fram1.setVisible(true);

    }
}
