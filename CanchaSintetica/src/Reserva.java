import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Reserva {
    private JButton ingresarButton;
    private JButton buscarButton;
    private JButton actualizarButton;
    private JButton eliminarButton;
    public  JPanel panel1;
    public static JFrame fram1;

    public Reserva() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                BuscarReserva.llenar();
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ActualizarReserva.llenar();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EliminarReserva.llenar();
            }
        });
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    InsertarReserva.llenar();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public  static void main(String []args){

        fram1 = new JFrame("Reserva");
        fram1.setContentPane(new Reserva().panel1);
        fram1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        fram1.setSize(400,400);
        fram1.setResizable(false);
        fram1.setLocationRelativeTo(null);
        fram1.setVisible(true);

    }
}
