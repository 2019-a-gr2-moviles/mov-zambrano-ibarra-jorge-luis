import ManejoEntidades.FunCanchaKt;
import ManejoEntidades.FunCanchaKt;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EliminarCancha {
    private JPanel panel1;
    public static DefaultTableModel model ;
    private static TableRowSorter trsfiltro;
    private JTextField txtBusqueda;
    public static JTextField textId ;
    public static JTable table ;
    public static JLabel lblBusqueda;
    public static JButton btnAdd;
    public  static void main(String []args){

        btnAdd.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<String> canchaEliminada = new ArrayList<String>();
                model = (DefaultTableModel) table.getModel();
                int fila = table.getSelectedRow();
                if (fila >= 0) {
                    int []filasselec  = table.getSelectedRows();
                    for (int i=0; i<filasselec.length;i++)
                    {
                        model.removeRow(filasselec[i]);

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No Selecciono Ninguna Fila", "Aviso", JOptionPane.ERROR_MESSAGE);
                }


                for (int i = 0 ; i < table.getRowCount(); i++) //realiza un barrido por filas.
                {
                    for (int j = 0; j < table.getColumnCount(); j++) //realiza un barrido por columnas.
                    {
                        canchaEliminada.add((String) (table.getValueAt(i, j)+"\n"));

                    }

                }

                FunCanchaKt.eliminarCancha("\n"+canchaEliminada.toString().replaceAll("[ \\[ | \\] ]",""));  }
        });


    }
    public static void filtro(){
        trsfiltro.setRowFilter(RowFilter.regexFilter(textId.getText(), 0));
    }
    public static void llenar(){
        textId = new JTextField();
        JTable table = new JTable();
        lblBusqueda= new JLabel();
         btnAdd = new JButton("Eliminar");
        lblBusqueda.setText("Buscar:");

        JFrame fram1 = new JFrame("Actualizar Cancha");
        // create a table model and set a Column Identifiers to this model
        Object[] columns = {"Numero Canchas"};
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
        pane.setBounds(0, 0, 880, 200);
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
        fram1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fram1.setSize(1000,800);
        fram1.setResizable(false);
        fram1.setLocationRelativeTo(null);
        int rows = 10;
        int cols = 5;
        fram1.setVisible(true);
        FunCanchaKt.listaCanchaEli();
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

                ArrayList<String> canchaEliminada = new ArrayList<String>();
                model = (DefaultTableModel) table.getModel();
                int fila = table.getSelectedRow();
                if (fila >= 0) {
                    int []filasselec  = table.getSelectedRows();
                    for (int i=0; i<filasselec.length;i++)
                    {
                        model.removeRow(filasselec[i]);

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No Selecciono Ninguna Fila", "Aviso", JOptionPane.ERROR_MESSAGE);
                }


                for (int i = 0 ; i < table.getRowCount(); i++) //realiza un barrido por filas.
                {
                    for (int j = 0; j < table.getColumnCount(); j++) //realiza un barrido por columnas.
                    {
                        canchaEliminada.add((String) (table.getValueAt(i, j)+"\n"));

                    }

                }

                FunCanchaKt.eliminarCancha("\n"+canchaEliminada.toString().replaceAll("[,| \\[ | \\] ]",""));  }
        });
    }
}
