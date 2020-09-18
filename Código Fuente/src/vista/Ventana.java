package vista;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
//import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



@SuppressWarnings("serial")
public class Ventana extends JFrame {
	//Atributos
	
	//Establecemos el panel para el inicio de la simulación : 
	public JPanel  panel = new JPanel();
	public JButton iniciar = new JButton();
	public JTextField autor = new JTextField();
	public JTextField repositorio = new JTextField();
	public JLabel titulo = new JLabel("Simulación de GIT",SwingConstants.CENTER);
	
	//Panel 2 de comandos
	public JPanel  panel2 = new JPanel();
	
	//Metodos
	public Ventana(){
	    setSize(500,500); 
	    setLocationRelativeTo(null);
	    //setBounds(500,100,500,500);setLocation(500,100);
        setTitle("Simulación Git");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setIconImage(new ImageIcon(getClass().getResource("/vista/icon.png")).getImage());
	    iniciarPanel1();
	    setVisible(true);
	}
	
	 private void iniciarPanel1(){
		 panel.setLayout(null);//Desactivamos diseño
         panel.setBackground(MainVista.celeste);
         this.getContentPane().add(panel); 
         //TEXTOS
         //Simulación titulo
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
         
					//   X   Y  W   H 
     	 iniciar.setBounds(170,200,200,20);
		 iniciar.setText("Iniciar Simulación");
		 //iniciar.setOpaque(true);
		 //iniciar.setForeground(azul);
		 iniciar.setBackground(MainVista.rojo);
		 
		 //ENTRADAS :
		 
		 //Cuadros de texto para recibir las variables
		 autor.setText("");
         autor.setBounds(230,70,100,30);
         JLabel autorHolder = new JLabel("Autor...") ;
         autorHolder.setFont(MainVista.myFont);
         autorHolder.setBackground(Color.gray);
         autorHolder.setBounds(240,70,100,30);
         
         autor.addKeyListener(new KeyAdapter() {
        	 @Override
        	public void keyTyped(KeyEvent key) {
        		if (key.getKeyChar()!=KeyEvent.VK_BACK_SPACE) {
					autorHolder.setVisible(false);
					System.out.println("Autor : "+autor.getText()+" Key :"+key.getKeyChar() +"\n");
				}
        		else if (autor.getText().equals("")) {
					autorHolder.setVisible(true);
					panel.add(autorHolder);
					autorHolder.setBounds(250,70,100,30);
					System.out.println("Autor : NULL"+autor.getText()+" Key :"+key.getKeyChar() +"\n");
				}
				
        	}
		});
         
         repositorio.setText("Repositorio...");
         repositorio.setBounds(230,100,100,30);
         
		 //Añadimos los elementos al panel
         
         //Textos
         panel.add(titulo);
         panel.add(autorText);
         panel.add(repositorioText);
         panel.add(autorHolder);
         //Field
         panel.add(autor);
         panel.add(repositorio);
         //Boton
         panel.add(iniciar);   
         
         
	 }
	 
	 public void iniciarPanel2() {
		 panel2.setLayout(null);//Desactivamos diseño
         panel2.setBackground(MainVista.celeste);
         this.getContentPane().add(panel2);
         panel2.setVisible(true);         
         panel2.add(titulo);
	 }
	
}
