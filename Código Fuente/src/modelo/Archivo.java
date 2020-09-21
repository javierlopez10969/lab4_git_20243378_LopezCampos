package modelo;

/**
 * Interfaz de la clase archivo, para posteriormente implementarla en la clase MiArchivo
 * Establecemos los qué necesarios para la implemtación
 * @author javier
 *
 */
public interface Archivo {
	//Obtener el archivo con su nombres y fecha, o obtener todo el archivo
	public String nombresFechas2String();
	public String Archivo2String();
	
	
	//Setters and getters
	
	
	//Nombre del archivo
	public String getNombre();
	public void setNombre(String nombre) ;
	
	
	//Fecha creacion
	public String getFechaCreacion();
	public void setFechaCreacion(String newFecha);
	
	
	//Fecha modificacion
	public String getFechaUltimaModificacion() ;
	public void setFechaUltimaModificacion();
	public void setFechaUltimaModificacion(String newFecha);
	
	
	//Contenido en string
	public String getContenidoString();
	public void setContenidoString(String Contenido);

}
