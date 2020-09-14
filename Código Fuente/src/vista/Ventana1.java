package vista;
import javax.swing.JButton;
//import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Ventana1 extends JFrame{
    
   
     public Ventana1(){
          setSize(500,500);
          iniciarComponentes();
     }
     
     private void iniciarComponentes(){
         JPanel  JPanel1 = new JPanel();
         JPanel1.setLayout(null);
         this.getContentPane().add(JPanel1); 
         
         
         //JTextField JTextFrom1 = new JTextField();
        
          JLabel label1 = new JLabel();
          label1.setText("Resultado:");
          label1.setBounds(20,20,80,50);
          
          JLabel label2 = new JLabel();
          label2.setText("0");
          label2.setBounds(100,20,80,50);
          
         
          JTextField JTextField1 = new JTextField();
          JTextField1.setBounds(200,20,100,30);
          
          JTextField JTextField2 = new JTextField();
          JTextField2.setBounds(300,20,100,30);
          
          JButton JButton1 = new JButton();
      					//   X   Y  W   H 
          JButton1.setBounds(400,20,100,50);
          JButton1.setText("Sumar");
              
          JPanel1.add(label1);
          JPanel1.add(label2);
          JPanel1.add(JTextField1);
          JPanel1.add(JTextField2);
          JPanel1.add(JButton1);
          
         
         /*         
         ActionListener ActionListener1 = new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
                int numero1 =  Integer.parseInt(JTextField1.getText());
                int numero2 = Integer.parseInt(JTextField2.getText());
                Calculadora c = new Calculadora();
                int resultado = c.SumarDosNumeros(numero1, numero2);
                label2.setText(String.valueOf(resultado));
             }
          }; 
         
         JButton1.addActionListener(ActionListener1);*/
     }
}
     