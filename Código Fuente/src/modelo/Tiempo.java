package modelo;
import java.text.SimpleDateFormat;
import java.util.Date; 
/*
 * Clase global que no es para instanciar nunca, es solo para obtener la fecha actual en un determinado formato
 * Sí, se ípodra decir que es un clase procedural, pero no queremos instanciarla nunca, solo necesitamos 
 * un string en un formato particular, además que necesitamos que varias clases obtengan este dato string
 * @version 1.2 25/08/2020
 * @author Javier López
 * */

public class Tiempo {
	//Atributo de clase String
	private static String actualTime;
	
	/**
	 * Obtener el tiempo actual
	 * @return uns tring con el tiempo actualmente
	 */
	public static String getActualTime() {
		Tiempo.setActualTime() ;
		return actualTime;
	}
	
	/**
	 * Actualizar el tiempoa actual
	 */
	public static void setActualTime() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		Tiempo.actualTime = formato.format(date);
	}
}
