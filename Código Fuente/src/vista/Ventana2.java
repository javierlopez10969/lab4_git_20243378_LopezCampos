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
         panel.setBackground(MainVista.celeste);
         this.getContentPane().add(panel); 
         
         //TEXTOS
         
         //Simulación titulo
         JLabel titulo = new JLabel("Simulación de GIT",SwingConstants.CENTER);
         //titulo.setForeground(azul);
         //titulo.setOpaque(true);
         titulo.setFont(MainVista.myFont);
         titulo.setBackground(Color.gray);
         titulo.setBounds(190,0,140,20);
         
         //Autor
         JLabel autorText = new JLabel("Autor :",SwingConstants.CENTER);
         //titulo.setForeground(azul);
         //titulo.setOpaque(true);
         autorText.setFont(MainVista.myFont);
         autorText.setBackground(Color.gray);
         autorText.setBounds(50,70,140,20);
         
         //Repositorio
         JLabel repositorioText = new JLabel("Repositorio :",SwingConstants.CENTER);
         //titulo.setForeground(azul);
         //titulo.setOpaque(true);
         repositorioText.setFont(MainVista.myFont);
         repositorioText.setBackground(Color.gray);
         repositorioText.setBounds(50,100,140,20);
         
         //BOTONES : 
         
         JButton iniciar = new JButton();
					//   X   Y  W   H 
     	 iniciar.setBounds(170,200,200,20);
		 iniciar.setText("Iniciar Simulación");
		 //iniciar.setOpaque(true);
		 //iniciar.setForeground(azul);
		 iniciar.setBackground(MainVista.rojo);
		 
		 //ENTRADAS :
		 
		 //Cuadros de texto para recibir las variables
		 JTextField autor = new JTextField();
		 autor.setText("Autor...");
		 autor.getText();
         autor.setBounds(230,70,100,30);
         
         JTextField repositorio = new JTextField();
         repositorio.setText("Repositorio...");
         repositorio.setBounds(230,100,100,30);
         
         
		 //Añadimos los elementos al panel
         //Textos
         panel.add(titulo);
         panel.add(autorText);
         panel.add(repositorioText);
         //Field
         panel.add(autor);
         panel.add(repositorio);
         //Boton
         panel.add(iniciar);
         
         
    
	 }
	
}
