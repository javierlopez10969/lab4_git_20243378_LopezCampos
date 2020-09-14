package vista;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
//import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;




@SuppressWarnings("serial")
public class Ventana2 extends JFrame {
	 //PALLETE
	 //Gris
	 //Color gris = Color.decode("#ECF4F3") ;
	 //Celeste
	 Color celeste = Color.decode("#68B0AB");
	 //Azul
	 Color azul = Color.decode("#006A71");
	 //Rojo
	 //Color rojo = Color.decode("#FF7E67");
	 Font myFont = new Font("times new romman",Font.ITALIC,15);
	 
	public Ventana2(){
	    setSize(500,500); 
	    setLocationRelativeTo(null);
	    //setBounds(500,100,500,500);setLocation(500,100);
        setTitle("Simulación Git");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	    
	    iniciarComponentes();
	}
	
	 private void iniciarComponentes(){
		 //Establecemos el panel
		 JPanel  panel = new JPanel();
		 panel.setLayout(null);//Desactivamos diseño
         panel.setBackground(celeste);
         this.getContentPane().add(panel); 
         
         //Simulación titulo
         JLabel titulo = new JLabel("Simulación de GIT",SwingConstants.CENTER);
         //titulo.setForeground(azul);
         //titulo.setOpaque(true);
         titulo.setFont(myFont);
         titulo.setBackground(Color.gray);
         titulo.setBounds(190,0,140,20);
         
         JButton iniciar = new JButton();
					//   X   Y  W   H 
     	 iniciar.setBounds(170,200,200,50);
		 iniciar.setText("Iniciar Simulación");
		 iniciar.setBackground(azul);
         //JTextField JTextField1 = new JTextField();
         //JTextField1.setBounds(200,20,100,30);
         
		 //Añadimos los elementos al panel
         panel.add(titulo);
         panel.add(iniciar);
	 }
	
}
