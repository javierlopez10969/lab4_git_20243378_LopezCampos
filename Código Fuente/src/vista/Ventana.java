package vista;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
//import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;



@SuppressWarnings("serial")
public class Ventana extends JFrame {
	//Atributos
	
	//Establecemos el panel para el inicio de la simulación : 
	public JPanel  panel = new JPanel();
	public JButton iniciar = new JButton();
	public PlaceHolder autor = new PlaceHolder("Autor...");
	public PlaceHolder repositorio =new PlaceHolder("Repositorio...");
	public JLabel titulo = new JLabel("Simulación de GIT",SwingConstants.CENTER);
	
	//Panel 2 de comandos
	public JPanel  panel2 = new JPanel();
	public JLabel autorLabel = new JLabel();
	public JLabel repositorioLabel = new JLabel();
	//Botones
	public JButton reInit= new JButton();
	public JButton add= new JButton();
	public JButton commit = new JButton();
	public JButton pull = new JButton();
	public JButton push= new JButton();
	public JButton workspace= new JButton();
	public JButton gitStatusButton = new JButton();
	
	
	
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
		 iniciar.setBackground(MainVista.rojo);
		 
		 //ENTRADAS :
		 
		 //Cuadros de texto para recibir las variables
         autor.getField().setBounds(230,70,100,30);
         //JOptionPane.showMessageDialog(null,autor);
         repositorio.getField().setBounds(230,100,100,30);
         
		 //Añadimos los elementos al panel
         
         //Textos
         panel.add(titulo);
         panel.add(autorText);
         panel.add(repositorioText);
         //Field
         panel.add(autor.getField());
         panel.add(repositorio.getField());
         //Boton
         panel.add(iniciar);   
         
         
	 }
	 
	 public void iniciarPanel2() {
		 panel2.setLayout(null);//Desactivamos diseño
         panel2.setBackground(MainVista.celeste);
         this.getContentPane().add(panel2);      
         
         
         //Seteando posicion y labels de botones
         //LABEL DE AUTOR
         autorLabel.setBounds(20,20, 150, 20);
         repositorioLabel.setBounds(20,40,150, 20);
         
         //Añadiendo los labels
         panel2.add(titulo);
         panel2.add(autorLabel);
         panel2.add(repositorioLabel);
         //BOTONES
         reInit.setBounds(20,370,90,20);
         reInit.setText("Re-Init");
		 reInit.setForeground(Color.white);
		 reInit.setBackground(MainVista.grisGitHub);
		 
         add.setBounds(120, 370,90,20);
		 add.setText("Add");
		 add.setForeground(Color.white);
		 add.setBackground(MainVista.grisGitHub);
		 
         commit.setBounds(220, 370, 90,20);
         commit.setForeground(Color.white);
		 commit.setText("Commit");
		 commit.setBackground(MainVista.grisGitHub);
		 
		 
         pull.setBounds(320, 370, 90,20);
         pull.setForeground(Color.white);
		 pull.setText("Pull");
		 pull.setBackground(MainVista.grisGitHub);
         
         push.setBounds(20, 400, 90,20);
         
        

         
         workspace.setBounds(10, 200, 115,40);
		 workspace.setText("<html><center>"+"<html>Workspace<br   />Status</html>");
		 workspace.setForeground(Color.white);
		 workspace.setBackground(MainVista.negro);
         
		 
         //Añadiendo Botones al panel
         panel2.add(add);
         panel2.add(commit);
         panel2.add(workspace);
         panel2.add(pull);
         panel2.add(push);
         panel2.add(reInit);
         panel2.add(gitStatusButton);
	 }
	
}
