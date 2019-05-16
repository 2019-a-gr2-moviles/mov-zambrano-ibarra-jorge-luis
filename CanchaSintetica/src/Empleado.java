import ManejoEntidades.FunCanchaKt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Empleado {

    public JPanel panelEmpleado;
    private JButton ingresarCanchaButton;
    private JButton btnBuscarCancha;
    private JButton actualizarCanchasButton;
    private JButton eliminarCanchasButton;
    public static JFrame fram1;

    public Empleado() {

        ingresarCanchaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                IngresarCancha.fram1 = new JFrame("Ingresar Cancha");
                IngresarCancha.fram1.setContentPane(new IngresarCancha().panel2);
                IngresarCancha.fram1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                IngresarCancha.fram1.setSize(400,400);
                IngresarCancha.fram1.setResizable(false);
                IngresarCancha.fram1.setLocationRelativeTo(null);
                IngresarCancha.fram1.setVisible(true);
            }
        });


        btnBuscarCancha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               BuscarCancha.llenar();
                FunCanchaKt.listaCancha();
            }
        });
        actualizarCanchasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ActualizarCancha.llenar();
            }
        });

        eliminarCanchasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EliminarCancha.llenar();
            }
        });
    }

    public  static void main(String []args){
         fram1 = new JFrame("Empleado");
        fram1.setContentPane(new Empleado().panelEmpleado);
        fram1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fram1.setSize(400,400);
        fram1.setResizable(false);
        fram1.setLocationRelativeTo(null);
        fram1.setVisible(true);

    }
}