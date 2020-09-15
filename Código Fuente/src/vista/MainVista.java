package vista;

import java.awt.Color;
import java.awt.Font;

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
	 //Fuente
	 public static Font myFont = new Font("times new romman",Font.ITALIC,15);
	public static void main(String[] args) {
		Ventana2 ventana = new Ventana2();
		ventana.setVisible(true);
	}
}
