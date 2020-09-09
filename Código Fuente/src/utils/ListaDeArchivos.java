package utils;
import modelo.Archivo;
import modelo.MiArchivo;


/**
 *  * Clase del tipo lista enlazada, la cual guarda todos los archivos posibles sin un limite
 * Sus atributos son el primer archivo de la lista
 * @
 * @author javier
 */

public class ListaDeArchivos {
	//Atributos
	
	//Cabeza de la lista
	private nodoArchivo cabeza = null;
	private int tamano ;
	
	/**
	 * una clase interna de lista enlazada, sirve como nodo para enlazar cada contenido de archivo
	 * @author javier
	 *
	 */
	private class nodoArchivo{
		private Archivo myArchivo;
		private nodoArchivo siguiente = null;
		//Setters anf getters
		public nodoArchivo(Archivo myArchivo ) {this.setMyArchivo(myArchivo);}
		//public archivo getMyArchivo() {return myArchivo;}
		public void setMyArchivo(Archivo myArchivo) {this.myArchivo = myArchivo;}
		public nodoArchivo getSiguiente() {return siguiente;}
		public void setSiguiente(nodoArchivo siguiente) {this.siguiente = siguiente;}
	}
	
	//Metodos
	
	/**
	 * Insertar la inicio de la lista
	 * @param myArchivo  archivo que se quiere agregar a la lista
	 */
	public void insertarPrincipio(Archivo myArchivo){
		nodoArchivo nodo = new nodoArchivo(myArchivo);
		//El siguiente elmenento es la cabeza
		nodo.siguiente = cabeza;
		//Y la nueva cabeza es el nodo
		setCabeza(nodo);		
		//Actualizamos el tamaño de la lista
		setTamano(tamano + 1);
	}
	
	/**
	 * Insertar un archivo al final de la lista
	 * @param myArchivo  archivo que se quiere agregar a la lista
	 */
	//Insertar al final
	public void insertarFinal(Archivo myArchivo) {
		nodoArchivo nodo = new nodoArchivo(myArchivo) ;
		nodoArchivo puntero = getCabeza() ;
		//Mientras no llegemos al final del puntero
		while(puntero.siguiente !=null) {
			puntero = puntero.getSiguiente();}
		//Una vez llegado al final, asignamos el nuevo nodo
		puntero.setSiguiente(nodo);	
		setTamano(tamano +1);		
	}
	
	/**
	 * Añadir un archivo a la lista de archivos
	 * @param myArchivo archivo que se quiere agregar a la lista
	 */
	public void anadirArchivo(Archivo myArchivo) {
		if (isEmpty()) {
			insertarPrincipio(myArchivo);
		}else {
			insertarFinal(myArchivo);
		}
	}
	
	/**
	 * Metodo que permite borrar un archivo dado un indicen
	 * @param n indice donde se quiere borrar el archivo de la lista
	 */
	public void borrarArchivo(int n) {
		if (n < tamano && n > -1) {
			nodoArchivo puntero = getCabeza() ;
			int i = 1; 
			//Mientras no llegemos al final del puntero
			while(puntero.siguiente !=null && i < n) {
				puntero = puntero.getSiguiente();
				i++;
			}
			nodoArchivo borrar = puntero.siguiente;
			puntero.siguiente = borrar.siguiente; 
			borrar.siguiente = null;
			setTamano(tamano -1);	
		}else {
			System.out.println("Indice supera los limites");
		}

	}
	
	/**
	 * Devolver todos los archivos y su contenido en un string
	 * @return
	 */
	public String archivos2String() {
		if (!isEmpty()) {
			nodoArchivo puntero =  getCabeza();
			String salidaString = "";
			int i = 0 ;
			while (puntero != null) {
				salidaString = salidaString +"i :"+ i + ".-\n";
				salidaString = salidaString + puntero.myArchivo.Archivo2String();
				puntero = puntero.getSiguiente();
				i++;
			}
			return (salidaString +"\n");
		}else {
			return("Lista de archivos vacía\n");
		}
	}
	/**
	 * Devolver todos los nombres y fechas archivos a través de uns string
	 * @return
	 */
	public String nombreFechas2String() {
		if (!isEmpty()) {
			String salidaString ="";
			nodoArchivo puntero =  getCabeza();
			int i = 0 ;
			while (puntero != null) {
				salidaString = salidaString + "i.- :"+ i+ ".-\n";
				salidaString = salidaString + puntero.myArchivo.nombresFechas2String();
				puntero = puntero.getSiguiente();
				i++;
			}
			return (salidaString +"\n");
		}else {
			return ("Workspace vacío\n");
		}
	}
	
	/**
	 * Metodo que dado un indice n, devuelva el archivo correspondiente en la lista
	 * @param n, indice donde se encuentra el archivo
	 * @return Archivo, archivo con el indice || null si el indice supera los limites
	 */
	public Archivo getArchivoN(int n){
		//Si el n ingresado no supera el tamaño total de archivos
		if (n > tamano || n < 0) {
			System.out.println("El indice excede al limite de archivos");
			return null;
		}else{
			nodoArchivo puntero =  getCabeza();
			int i = 0 ;
			//Mientras el puntero no sea nulo
			while (i < n && puntero != null) {
				System.out.println(i+".-");
				puntero = puntero.getSiguiente();
				i++;
			}if (i!= n) {
				System.out.println("No hay archivos disponibles");
				return null;
			}else {
				//Creamos un archivo desde 0
				Archivo archivo = new MiArchivo(puntero.myArchivo.getNombre());
				archivo.setFechaCreacion(puntero.myArchivo.getFechaCreacion());
				archivo.setContenidoString(puntero.myArchivo.getContenidoString());
				archivo.setContenido(puntero.myArchivo.getContenido().copiarContenido());
				String fechaString = puntero.myArchivo.getFechaUltimaModificacion();
				//System.out.println("Fecha de modificación Original : " +fechaString +"\n");
				archivo.setFechaUltimaModificacion(fechaString);
				//System.out.println("Fecha de modificación : " + archivo.getFechaUltimaModificacion() +"\n");
				return archivo;
				//return puntero.myArchivo;
			}
		}
	}
	
	
	/**
	 * Metodo que consulta si un archivos se encuentra dentro de otra lista de archivos
	 * @param archivo que se va a comparar con el resto de los archivos, se compara solo el nombre
	 * @return Boolean true si se encuentra dentro, false si no se encuentra
	 */
	public Boolean isInside(Archivo archivo) {
		nodoArchivo puntero = getCabeza();
		while (puntero != null) {
			//Comparamos solo con el nombre
			//System.out.println("Name archivo : " + archivo.getNombre());
			//System.out.println("Name puntero : " + puntero.myArchivo.getNombre()+ "\n");
			if (archivo.getNombre().equals(puntero.myArchivo.getNombre())) {
				//System.out.println("El archivo ya se encuentra en la lista de archivos\n");
				return true;
			}else {
				puntero = puntero.getSiguiente();	
			}	
		}
		return false;
	}
	
	/**
	 * Ver si el archivo se encuentra dentro de un archivo además pregunta si es exactamente igual al archivo que se e|
	 * Metodo de busqueda y comparación
	 * Nos vamos a concentrar en comparar segun fechas y nombres, omitiendo contenido para no complejizar la función
	 * @param archivo
	 * @return true si se encuentra con el mismo contenido, false si no se encuentra, o si se encuentra pero con distinto contenido
	 */
	public Boolean isInsideAndEquals(Archivo archivo) {
		nodoArchivo puntero = getCabeza();
		while (puntero != null) {
			//Comparamos solo con el nombre
			//System.out.println("Name archivo : " + archivo.getNombre());
			//System.out.println("Name puntero : " + puntero.myArchivo.getNombre()+ "\n");
			if (archivo.getNombre().equals(puntero.myArchivo.getNombre())) {
				System.out.println("El archivo ya se encuentra en la lista de archivos\n");
				if (archivo.equals(puntero.myArchivo) || archivo.Archivo2String().equals(archivo.Archivo2String())) {
					System.out.println("Y además son iguales\n");
					return true;
				}else {
					//System.out.println("Y No son iguales\n");
					return false;
				}
			}
			puntero = puntero.getSiguiente();			
		}
		return false;
	}
	
	/**
	 * Idicar si dos listas de archivos son iguales
	 * Se compara la lista de entrada con la de instancia
	 * @param archivos lista de archivos a comparar
	 * @return true son iguales , false son distintas
	 */
	public Boolean listaDeArhivosIguales(ListaDeArchivos archivos) {
		//Si ambas listas tienen un tamaño distinto podemos indicar que son distintas
		if (this.getTamano() != archivos.getTamano()) {
			return false;
		}
		else if (this.equals(archivos)) {
			return true;
		}
		//Declaramos puntero con la cabeza de archivos
		nodoArchivo punteroArchivo = archivos.getCabeza();
		//Y declaramos un acumulador con el total de los archivos de la lista
		int n =  archivos.getTamano();
		int i = 0;
		while (punteroArchivo != null && i < archivos.getTamano()) {
			//Si el archivo se encuentra dentro y además es igual
			if (isInsideAndEquals(punteroArchivo.myArchivo)) {
				//Disminuimos el total
				n--;
			}
			i++;
			//Avanzamos el puntero
			punteroArchivo.getSiguiente();
		}
		// si al final de recorrer todos los punteros el contador es igual a 0
		if (n==0) {
			//Devolvemos true porque todos los archivos se encuentran además de ser iguales
			return true;
		}
		//Si se completo todo, y por lo menos hay un archivo distinto
		return false;
		}
	
	//Esta vacía la lista de archivos
	public Boolean isEmpty() {return tamano == 0;}
	//Setter and getters
	public nodoArchivo getCabeza() {return cabeza;}
	public void setCabeza(nodoArchivo cabeza) {	this.cabeza = cabeza;}
	public int getTamano() {return tamano;}
	public void setTamano(int tamano) {this.tamano = tamano;}
}
