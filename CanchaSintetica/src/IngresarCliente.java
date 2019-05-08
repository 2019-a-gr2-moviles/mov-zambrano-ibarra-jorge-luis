import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngresarCliente {
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtDireccion;
    private JButton guardarButton;
    private JButton regresarButton;
    public JPanel panel1;
    public static JFrame fram1;

    public IngresarCliente() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ManejoEntidades.FunUsuarioKt.ingresarCliente(txtCedula.getText(),txtNombre.getText(),txtTelefono.getText(),txtDireccion.getText());
            }
        });

    }
    public  static void main(String []args){
        llenar();

    }
    public static void llenar(){
        fram1 = new JFrame("Ingresar Cliente");
        fram1.setContentPane(new IngresarCliente().panel1);
        fram1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fram1.setSize(400,400);
        fram1.setResizable(false);
        fram1.setLocationRelativeTo(null);
        fram1.setVisible(true);

    }
}
