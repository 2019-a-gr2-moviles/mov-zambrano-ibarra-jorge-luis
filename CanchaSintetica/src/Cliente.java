import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cliente {
    private JButton ingresarButton;
    private JButton buscarButton;
    private JButton actualizarButton;
    private JButton eliminarButton;
    public  JPanel panel1;
    public static JFrame fram1;

    public Cliente() {
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fram1 = new JFrame("Ingresar Cliente");
                fram1.setContentPane(new IngresarCliente().panel1);
                fram1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                fram1.setSize(400,400);
                fram1.setResizable(false);
                fram1.setLocationRelativeTo(null);
                fram1.setVisible(true);
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                BuscarCliente.llenar();
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ActualizarCliente.llenar();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EliminarCliente.llenar();
            }
        });
    }

    public  static void main(String []args){

        fram1 = new JFrame("Cliente");
        fram1.setContentPane(new Cliente().panel1);
        fram1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fram1.setSize(400,400);
        fram1.setResizable(false);
        fram1.setLocationRelativeTo(null);
        fram1.setVisible(true);

    }
}
