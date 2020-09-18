package modelo;

/**
 * Interfaz de la clase archivo, para posteriormente implementarla en la clase MiArchivo
 * @author javier
 *
 */
public interface Archivo {
	public String nombresFechas2String();
	public String Archivo2String();
	public void editarArchivo() throws InterruptedException;
	//Setters and getters
	//Nombre del archivo
	public String getNombre();
	public void setNombre(String nombre) ;
	//Fecha creacion
	public String getFechaCreacion();
	public void setFechaCreacion();
	public void setFechaCreacion(String newFecha);
	//Fecha modificacion
	public String getFechaUltimaModificacion() ;
	public void setFechaUltimaModificacion();
	public void setFechaUltimaModificacion(String newFecha);
	//Contenido en string
	public String getContenidoString();
	public void setContenidoString(String Contenido);
	//Editar contenido
	public Contenido getContenido() ;
	public void setContenido(Contenido contenido);
}
