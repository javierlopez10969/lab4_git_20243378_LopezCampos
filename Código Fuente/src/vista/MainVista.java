package vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MainVista {
	 //PALLETE
	 //Gris
	 public static Color gris = Color.decode("#ECF4F3") ;
	 //Celeste
	 public static Color celeste = Color.decode("#68B0AB");
	 //Azul
	 public static Color azul = Color.decode("#006A71");
	 //Rojo
	 public static Color rojo = Color.decode("#FF7E67");
	 //Negro github36,41,46
	 public static Color negro = new Color(36,41,46);
	 //GrisGithub
	 public static Color grisGitHub = Color.decode("#2B3137");
	 //Celeste github
	 public static Color celesteGitHub = new Color(250,251,252);
	 //Fuente
	 public static Font myFont = new Font("times new romman",Font.ITALIC,15);

	 public static ImageIcon gato = new ImageIcon("vista/cat.png");
	 
	 public static ImageIcon error = new ImageIcon("vista/error.png");
	 
	 public static ImageIcon branch = new ImageIcon("vista/branch.png");
	 
	 /**
	  * Mostrar mensaje en interfaz dependiendo del tipo del mensaje
	  * @param mensaje
	  * @param tipo
	  * 0 error
	  * 1 succesful
	  * 3 opción aún no disponible
	  * 4 YES_NO option
	  */
	public static String mostrarMensaje(String mensaje,int tipo) {
		if (tipo == 0) {
			JOptionPane.showMessageDialog(null,mensaje,"Bad Ending",JOptionPane.PLAIN_MESSAGE,error);
			return null;
		}else if (tipo==1) {
			JOptionPane.showMessageDialog(
			null,
			mensaje,
			"Successful",
			JOptionPane.PLAIN_MESSAGE,
			gato);
			return null;
		}else if (tipo == 2) {
			String respuesta =(String)JOptionPane.showInputDialog(
					null,
					mensaje,
					"Dialogo",
					JOptionPane.OK_OPTION,
					MainVista.gato,
					null,
					"Archivito.py");
			return respuesta;
		}else if (tipo==3) {
			JOptionPane.showMessageDialog(
			null,"Opción no disponible aún",
			"Bad Ending",
			JOptionPane.PLAIN_MESSAGE,
			error);
			return null;
		}else if(tipo==4) {
			int respuestaInt = JOptionPane.showConfirmDialog(
			null,
			mensaje,
			"Edición de Archivo", 
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			gato);
			String respuestaString = Integer.toString(respuestaInt);
			return respuestaString;
		}else if (tipo == 5) {
			String respuesta =(String)JOptionPane.showInputDialog(
					null,
					mensaje,
					"Creando rama",
					JOptionPane.OK_OPTION,
					branch,
					null,
					"Nueva Rama");
			return respuesta;
		}return null;
	}
	
	public static String mostrarMensaje(String mensaje,String ventana,int tipo) {
		if (tipo == 2) {
			String respuesta =(String)JOptionPane.showInputDialog(
					null,
					mensaje,
					ventana,
					JOptionPane.OK_OPTION,
					gato,
					null,
					"Archivito.py");
			return respuesta;
		}return null;
	}
			
	}
	 
	 
