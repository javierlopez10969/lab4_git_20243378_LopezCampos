package vista;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
//import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;


/**
 * Clase ventana, es la vista principal del programa
 * @author javier
 *
 */
@SuppressWarnings("serial")
public class Ventana extends JFrame {
	//Atributos
	
	//Panel 1

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
	public JLabel ramaLabel = new JLabel();
	
	//Botones

	// Comandos
	public static JButton reInit= new JButton();
	public static JButton add= new JButton();
	public static JButton commit = new JButton();
	public static JButton push= new JButton();
	
	//Segunda fila
	public static JButton pull = new JButton();
	public static JButton log = new JButton();
	public static JButton branch = new JButton();
	public static JButton checkout = new JButton();
	
	//Mostrar Status
	public static JTextPane status= new JTextPane();
	public static JScrollPane scrollStatus = new JScrollPane(status);
	
	
	//Creación y edición de archivos 2 fila grande
	public static JButton nuevoArchivo = new JButton();
	public static JButton editarArchivo = new JButton();
	public static JButton verContenido = new JButton();
	public static JButton limpiarIndex = new JButton();
	public static JComboBox<String> archivos = new JComboBox<String>();
	
	
	//Status
	public static JButton workspace= new JButton();
	public static JButton	index = new JButton();
	public static JButton	localRepository = new JButton();
	public static JButton remoteRepository = new JButton();
	
	
	
	
	//Metodos

	/**
	 * Metoodo constructor de ventana
	 */
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
	
	
	/**
	 * Inicializar los componentes del panel 1 de inicio
	 */
	 private void iniciarPanel1(){
		 panel.setLayout(null);//Desactivamos diseño
         panel.setBackground(Color.white);
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
         autorText.setForeground(MainVista.grisGitHub);
         autorText.setBounds(50,70,140,20);
         
         //Repositorio
         JLabel repositorioText = new JLabel("Repositorio :",SwingConstants.CENTER);
         //titulo.setForeground(azul);
         //titulo.setOpaque(true);
         repositorioText.setFont(MainVista.myFont);
         repositorioText.setForeground(MainVista.grisGitHub);
         repositorioText.setBounds(50,100,140,20);
         //BOTONES : 
         
		//   X   Y  W   H 
     	 iniciar.setBounds(170,200,200,20);
		 iniciar.setText("Iniciar Simulación");
		 iniciar.setForeground(Color.white);
		 iniciar.setBackground(MainVista.grisGitHub);
		 
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
	 
	 /**
	  * Inicializar los componentes del segundo pane, para la interacción de comandos
	  */
	 public void iniciarPanel2() {
		 panel2.setLayout(null);//Desactivamos diseño
         panel2.setBackground(Color.white);
         this.getContentPane().add(panel2);      
         
         
         //Seteando posicion y labels de botones
         //LABEL DE AUTOR
         autorLabel.setBounds(20,20, 150, 20);
         repositorioLabel.setBounds(20,40,150, 20);
         ramaLabel.setBounds(20,60,150, 20);
         
         //Añadiendo los labels
         panel2.add(titulo);
         panel2.add(autorLabel);
         panel2.add(repositorioLabel);
         panel2.add(ramaLabel);
         
         
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
		 
         push.setBounds(320, 370, 90,20);
         push.setForeground(Color.white);
		 push.setText("Push");
		 push.setBackground(MainVista.grisGitHub);
		
         
		 //Segunda fila
         pull.setBounds(20, 400, 90,20);
         pull.setForeground(Color.white);
		 pull.setText("Pull");
		 pull.setBackground(MainVista.grisGitHub);
		 
         log.setBounds(120, 400, 90,20);
         log.setForeground(Color.white);
         log.setText("Log");
         log.setBackground(MainVista.grisGitHub);
		 
         branch.setBounds(220, 400, 90,20);
         branch.setForeground(Color.white);
         branch.setText("Branch");
         branch.setBackground(MainVista.grisGitHub);
         
         checkout.setBounds(320, 400, 90,20);
         checkout.setForeground(Color.white);
         checkout.setText("<html>Checkout");
         checkout.setBackground(MainVista.grisGitHub);
         
        
         
         //Status zona de trabajo
         
         workspace.setBounds(10, 200, 100,60);
		 workspace.setText("<html><center>"+"<html>Workspace<br   />Status</html>");
		 workspace.setForeground(Color.white);
		 workspace.setBackground(MainVista.negro);
         
		 
         index.setBounds(120, 200, 100,60);
		 index.setText("<html><center>"+"<html>Index<br   />Status</html>");
		 index.setForeground(Color.white);
		 index.setBackground(MainVista.negro);
		 
		 
         localRepository.setBounds(230, 200, 100,60);
         localRepository.setText("<html><center>"+"<html>Local Repository<br   />Status</html>");
         localRepository.setForeground(Color.white);
         localRepository.setBackground(MainVista.negro);
         
         remoteRepository.setBounds(340, 200, 100,60);
         remoteRepository.setText("<html><center>"+"<html>Remote Repository<br   />Status</html>");
         remoteRepository.setForeground(Color.white);
         remoteRepository.setBackground(MainVista.negro);
         
         //Editar archivo y contenido
         nuevoArchivo.setBounds(10,270, 100,60);
         nuevoArchivo.setText("<html><center>"+"<html>Nuevo Archivo</html>");
         nuevoArchivo.setForeground(Color.white);
         nuevoArchivo.setBackground(MainVista.negro);
         
         
         editarArchivo.setBounds(120,270, 100,60);
         editarArchivo.setText("<html><center>"+"<html>Editar Archivo</html>");
         editarArchivo.setForeground(Color.white);
         editarArchivo.setBackground(MainVista.negro);
         
         verContenido.setBounds(230,270, 100,60);
         verContenido.setText("<html><center>"+"<html>Ver Contenido</html>");
         verContenido.setForeground(Color.white);
         verContenido.setBackground(MainVista.negro);
         
         
         archivos.setBounds(20,80, 170,20);
         //archivos.setForeground(Color.white);
         //archivos.setBackground(MainVista.negro);
         
         
         //Tabla de  Estados
         scrollStatus.setBounds(200,20,290,150);
         status.setEditable(false);
         status.setText("Cree un archivo");
		 
         //Añadiendo Botones al panel
		   panel2.add(reInit);
         panel2.add(add);
         panel2.add(commit);
         panel2.add(pull);
         panel2.add(push);
         //Segunda fila
         panel2.add(log);
         panel2.add(branch);
         panel2.add(checkout);
         
         //Status
         panel2.add(workspace);
         panel2.add(index);
         panel2.add(localRepository);
         panel2.add(remoteRepository);
         
         //Edit
         panel2.add(nuevoArchivo);
         panel2.add(editarArchivo);
         panel2.add(verContenido);
         panel2.add(scrollStatus);
         //panel2.add(archivos);
	 }
	
}
