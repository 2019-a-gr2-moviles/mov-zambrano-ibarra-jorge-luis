import ManejoEntidades.FunCanchaKt;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.TableRowSorter;
public class BuscarCancha {

    private static TableRowSorter trsfiltro;
    public static JScrollPane pane;
    public  JPanel panel1;
    private JTextField txtBusqueda;
    public static DefaultTableModel model ;
    public static JTextField textId ;
    public static JTable table ;
    public static JFrame fram1;
    public  static void main(String []args){
     llenar();


    }
    public static void filtro(){
        trsfiltro.setRowFilter(RowFilter.regexFilter(textId.getText(), 0));
    }
    public static void llenar(){
        textId = new JTextField();
        JTable table = new JTable();
        JLabel lblBusqueda= new JLabel();

        fram1 = new JFrame("Buscar Cancha");
        // create a table model and set a Column Identifiers to this model
        Object[] columns = {"Lista de Canchas"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        // set the model to the table
        table.setModel(model);
        lblBusqueda.setText("Buscar: ");
        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);
        pane = new JScrollPane(table);
        pane.setBounds(20, 100, 580, 100);
        textId.setBounds(60, 0, 100, 20);
        lblBusqueda.setBounds(5, 0, 100, 25);
        fram1.setLayout(null);
        textId.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                String cadena = (textId.getText());
                textId.setText(cadena);

                filtro();
            }
        });
        trsfiltro = new TableRowSorter(table.getModel());
        table.setRowSorter(trsfiltro);


        fram1.setContentPane(pane);


        fram1.add(textId);
        fram1.add(lblBusqueda);
        fram1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fram1.setSize(1000,800);
        fram1.setResizable(false);
        fram1.setLocationRelativeTo(null);
        int rows = 10;
        int cols = 5;
        fram1.setVisible(true);
    }






}
