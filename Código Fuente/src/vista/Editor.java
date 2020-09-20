package vista;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Editor extends JFrame {
	//Atributos
	
	//Establecemos el panel para el inicio de la simulación : 
	public JPanel  panel = new JPanel();
	//Boton para guardar
	public JButton guardar = new JButton();
	public JLabel titulo = new JLabel("",SwingConstants.CENTER);
	
	//Editor de texto
	public JTextPane editor = new JTextPane();
	public JScrollPane contenido= new JScrollPane(editor);
	
	public Editor(String Nombre,String Contenido){
	    setSize(500,500); 
	    setLocationRelativeTo(null);
	    //setBounds(500,100,500,500);setLocation(500,100);
        setTitle("Editor de Texto");
        titulo.setText(Nombre);
        editor.setText(Contenido);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setIconImage(new ImageIcon(getClass().getResource("/vista/icon.png")).getImage());
	    setVisible(true);
	    iniciarComponentes();
	}
	public void iniciarComponentes() {
		 panel.setLayout(null);//Desactivamos diseño
         panel.setBackground(Color.white);
         this.getContentPane().add(panel); 
         
         //TEXTOS
         //Simulación titulo
         titulo.setFont(MainVista.myFont);
         titulo.setBackground(Color.gray);
         titulo.setBounds(20,20,140,20); 
         
         //Nombre de Archivo
         guardar.setText("Guardar");        
         guardar.setForeground(Color.white);
         guardar.setBackground(MainVista.negro);
         guardar.setBounds(380,20,100,20);
         
         //Tabla de  Estados
         contenido.setBounds(20,50,430,400);
         
 		//Añadiendo componentes
 		panel.add(guardar);
 		panel.add(titulo);
 		panel.add(contenido);
	}
	
	public String getContenido() {return editor.getText();}
	
	

}
