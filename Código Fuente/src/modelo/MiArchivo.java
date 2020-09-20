//Importaciones
package modelo;

/**
 * Clase que implementa los que presentado en la interfaz Archivo
 * Es la abstracción de un archivo de texto plano
 * @author javier
 *
 */
public class MiArchivo implements Archivo{
	//Atributos
	private String nombre;
	private String fechaCreacion;
	private String fechaUltimaModificacion;
	//Contenido almacenado en String
	private String contenidoString ;

	
	
	//METODOS---------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Constructor a partir del nombre del archivo
	 * @param name
	 */
	public MiArchivo(String name){
		setNombre(name);
		setFechaCreacion();
		setFechaUltimaModificacion();
	}
	
	/**
	 * Muestra el archivo, pero solo su nombre y fechas
	 */
	public String nombresFechas2String() {
		return ("Nombre Archivo : " + getNombre()+
				"\nFecha de creación : " +getFechaCreacion()+
				"\nÚltima fecha de modifcación : " + getFechaUltimaModificacion()+"\n");
	}
	
	
	/**
	 * Muestra el archivo con todos sus atributos
	 */
	public String Archivo2String() {
		String salidaString;
		salidaString = "Nombre Archivo : " +getNombre()+
		"\nFecha de creación : " + getFechaCreacion() +
		"\nÚltima fecha de modifcación : " + getFechaUltimaModificacion() +
		"\nContenido : \n";
		if (getContenidoString().equals("")) {
			salidaString = salidaString + "Sin Contenido\n";
		}else {
			salidaString = salidaString + getContenidoString() +"\n"; 
		}
		return salidaString;
	}
		

	
	//Setters and Getters
	//Nombre
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	//Fecha creacion
	public String getFechaCreacion() {return fechaCreacion;}
	public void setFechaCreacion(String newFecha) {this.fechaCreacion = newFecha;}
	public void setFechaCreacion() {this.fechaCreacion = Tiempo.getActualTime();}
	//Fecha modificacion
	public String getFechaUltimaModificacion() {return fechaUltimaModificacion;}
	public void setFechaUltimaModificacion(String newFecha) {this.fechaUltimaModificacion= newFecha;}
	public void setFechaUltimaModificacion() {this.fechaUltimaModificacion = Tiempo.getActualTime() ;}

	//Setear el contenido editado en el string
	public String getContenidoString() {return this.contenidoString;}
	public void setContenidoString(String contenido) {this.contenidoString = contenido;}
	


}
