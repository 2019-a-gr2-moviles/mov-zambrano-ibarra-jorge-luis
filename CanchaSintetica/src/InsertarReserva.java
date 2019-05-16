import ManejoEntidades.FunReservaKt;
import ManejoEntidades.FunUsuarioKt;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertarReserva {

    private static JTextField txtCliente;
    public static String clienteL;
    private JButton buscarButton;
    private JTextField txtFecResIni;
    private JTextField txtFecResFin;
    private JTextField txtValor;
    public JPanel panel1;
    public  JComboBox cbxCliente;
    private JButton guardarButton;
    private JComboBox cbxCancha;
    public static JFrame fram1;
    private String aStatic;

    public InsertarReserva()throws FileNotFoundException, IOException {
        leer();
        leer1();
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FunReservaKt.IngresarReserva(cbxCliente.getSelectedItem().toString(),txtFecResIni.getText(),txtFecResFin.getText(),Double.parseDouble(txtValor.getText()),Integer.parseInt(cbxCancha.getSelectedItem().toString()));
            }
        });
    }
    public  static void main(String []args)throws FileNotFoundException, IOException{

      llenar();

    }
    public static void llenar() throws IOException {

        txtCliente= new JTextField();

        txtCliente.setBounds(60, 60, 100, 200);
        txtCliente.setVisible(true);
        fram1 = new JFrame("Ingresar Reserva");
        fram1.add(txtCliente);
        fram1.setContentPane(new InsertarReserva().panel1);
        fram1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        fram1.setSize(400,400);
        fram1.setResizable(true);
        fram1.setLocationRelativeTo(null);

        fram1.setVisible(true);


    }

public  void leer() throws FileNotFoundException, IOException {
    FileReader f = new FileReader("C:\\Users\\ZAMBRANO JORGE\\Documents\\GITHUB\\mov-zambrano-ibarra-jorge-luis\\CanchaSintetica\\src\\cliente.txt");
    BufferedReader b = new BufferedReader(f);
    String temp="";
    while( b.readLine()!=null) {
        temp+=b.readLine();
        cbxCliente.addItem(temp.substring(0,10));
    }
    b.close();
}

    public  void leer1() throws FileNotFoundException, IOException {
        FileReader f = new FileReader("C:\\Users\\ZAMBRANO JORGE\\Documents\\GITHUB\\mov-zambrano-ibarra-jorge-luis\\CanchaSintetica\\src\\cancha.txt");
        BufferedReader b = new BufferedReader(f);
        String temp="";
        while( b.readLine()!=null) {
            temp+=b.readLine();
            cbxCancha.addItem(temp.substring(0,1));
        }
        b.close();
    }


}
