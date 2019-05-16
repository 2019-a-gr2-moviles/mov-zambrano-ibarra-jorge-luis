
import Entidades.Cancha;
import ManejoEntidades.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngresarCancha {
    public JTextField txtNumeroCancha;
    public JTextField txtDescripcionCancha;
    public JTextField txtMetrosCuadrado;
    public JTextField txtPrecioCancha;
    public JButton btnGuardar;
    public JPanel panel2;
    public static JFrame fram1;
    public String numero;

    public IngresarCancha() {
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                ManejoEntidades.FunCanchaKt.IngresarCancha(Integer.parseInt(txtNumeroCancha.getText()),txtDescripcionCancha.getText(),Double.parseDouble(txtMetrosCuadrado.getText()),Double.parseDouble(txtPrecioCancha.getText()));




            }
        });
    }

    public  static void main(String []args){
        fram1 = new JFrame("Ingresar Cancha");
        fram1.setContentPane(new IngresarCancha().panel2);
        fram1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        fram1.setSize(400,400);
        fram1.setResizable(false);
        fram1.setLocationRelativeTo(null);
        fram1.setVisible(true);

    }
}
