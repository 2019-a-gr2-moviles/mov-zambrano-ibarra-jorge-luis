import ManejoEntidades.FunCanchaKt;
import ManejoEntidades.FunReservaKt;
import ManejoEntidades.FunUsuarioKt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ActualizarReserva {
    private static TableRowSorter trsfiltro;

    private JTextField txtBusqueda;
    public static DefaultTableModel model ;
    public static JTextField textId ;
    public static JTable table ;
    private JPanel panel1;
    static JLabel lblBusqueda;
    static JButton btnAdd ;
    public  static void main(String []args){
        llenar();
        btnAdd.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {


                ArrayList<String> clienteActualizado = new ArrayList<String>();
                for (int i = 0 ; i < table.getRowCount(); i++) //realiza un barrido por filas.
                {
                    for (int j = 0; j < table.getColumnCount(); j++) //realiza un barrido por columnas.
                    {
                        clienteActualizado.add((String) (table.getValueAt(i, j)+"\n"));

                    }

                }

                FunUsuarioKt.actualizarCliente("\n"+clienteActualizado.toString().replaceAll("[,| \\[ | \\] ]","").trim());}
        });



    }

    public static void filtro(){
        trsfiltro.setRowFilter(RowFilter.regexFilter(textId.getText(), 0));
    }
    public static void llenar(){
        textId = new JTextField();
        JTable table = new JTable();
        lblBusqueda= new JLabel();
        btnAdd = new JButton("Actualizar");
        lblBusqueda.setText("Buscar:");
        JFrame fram1 = new JFrame("Actualizar Reserva");
        // create a table model and set a Column Identifiers to this model
        Object[] columns = {"Cliente","Inicio","Fin","Precio","Cancha"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        // set the model to the table
        table.setModel(model);

        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 50, 200);
        btnAdd.setBounds(150, 220, 100, 25);
        textId.setBounds(60, 0, 100, 20);
        lblBusqueda.setBounds(5, 0, 100, 25);
        fram1.setLayout(null);

        pane.add(btnAdd);
        btnAdd.setVisible(true);
        fram1.add(btnAdd);

        fram1.setContentPane(pane);
        fram1.add(btnAdd);
        fram1.add(textId);
        fram1.add(lblBusqueda);
        fram1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        fram1.setSize(1000,800);
        fram1.setResizable(false);
        fram1.setLocationRelativeTo(null);
        int rows = 10;
        int cols = 5;
        fram1.setVisible(true);
        FunReservaKt.listaReservaAct();
        textId.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                String cadena = (textId.getText());
                textId.setText(cadena);

                filtro();
            }
        });
        trsfiltro = new TableRowSorter(table.getModel());
        table.setRowSorter(trsfiltro);
        btnAdd.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                String temp="";
                ArrayList<String> reservaActualizada = new ArrayList<String>();
                for (int i = 0 ; i < table.getRowCount(); i++) //realiza un barrido por filas.
                {
                    for (int j = 0; j < table.getColumnCount(); j++) //realiza un barrido por columnas.
                    {
                        if(j!=table.getColumnCount()-1) {
                            temp += ((String) (table.getValueAt(i, j) + ";"));
                        }else{
                            temp += ((String) (table.getValueAt(i, j) ));
                        }

                    }
                    temp+=("\n");
                }
                reservaActualizada.add(temp);
                FunReservaKt.actualizarReserva("\n"+reservaActualizada.toString().replaceAll("[,|\\[|\\]]","").trim());}
        });
    }
}
