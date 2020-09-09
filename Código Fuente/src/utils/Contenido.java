package utils;

/**
 * Case que permite la edición de un archivo de texto plano
 * @author javier
 *
 */
public class Contenido {
	//Atributos
	//Cabeza de la lista
	private Lineas Cabeza = null;
	//Total de lineas
	private int tamano ;
	
	//Constructor de un contenido simple
	public Contenido() {
		setTamano(0);
	}
	
	/**
	 * Metodo constructor de copia de un archivo
	 * @return
	 */
	public Contenido copiarContenido() {
		Contenido contenidoSalida = new Contenido();
		contenidoSalida.setTamano(this.getTamano());
		Lineas punteroLineas = this.getCabeza(); int i = 1 ;
		if (punteroLineas == null) {
			return contenidoSalida;
		}
		contenidoSalida.insertarPrincipio(punteroLineas.getLinea());
		punteroLineas = punteroLineas.getSiguiente();
		if (punteroLineas == null) {
			return contenidoSalida;
		}
		while (punteroLineas!=null && i < this.getTamano()) {
			String lineaString = punteroLineas.getLinea() ;
			//System.out.println(lineaString + i + "tamano : " + this.getTamano() +"\n");
			contenidoSalida.insertarFinal(lineaString);
			i++;
			punteroLineas = punteroLineas.getSiguiente();
		}
		return contenidoSalida;
	}
	/**
	 * Creamos la clase nodo, una clase interna de lista enlazada
	 * @author javier
	 *
	 */
	private class Lineas{
		//Atributos
		private String Linea;
		private Lineas siguiente = null;
		
		//Metodos
		//Constructor
		public Lineas(String Linea) {this.setLinea(Linea);}
		
		//Setters and Getters
		public String getLinea() {return Linea;}
		public void setLinea(String Linea) {this.Linea = Linea;}
		public Lineas getSiguiente() {return siguiente;}
		public void setSiguiente(Lineas siguiente) {this.siguiente = siguiente;}
	}
	
	//Metodos
	
	/**
	 * Insertar una linea en la cabeza del contenido
	 * @param linea
	 */
	public void insertarPrincipio(String linea){
		Lineas nodo = new Lineas(linea);
		//El siguiente elmenento es la cabeza
		nodo.setSiguiente(Cabeza);
		//Y la nueva cabeza es el nodo
		this.Cabeza = nodo;		
		//Actualizamos el tamaño de la lista
		setTamano(tamano + 1);
	}
	
	/**
	 * Insertar al final del contenido una linea
	 * @param linea
	 */
	public void insertarFinal(String linea) {
		Lineas nodo = new Lineas(linea);
		Lineas puntero = getCabeza() ;
		if (puntero == null) {
			setCabeza(nodo);
		}else {
			//Mientras no llegemos al final del puntero
			while(puntero.siguiente !=null) {
				puntero = puntero.getSiguiente();
			}
			//Una vez llegado al final, asignamos el nuevo nodo
			puntero.setSiguiente(nodo);	
			//Seteamos el indice en donde se ecnuentra la linea				
		}
		setTamano(tamano +1);
	}
	
	/**
	 * Insertar una linea segun un indice n
	 * @param n
	 * @param linea
	 */
	public void insertarN(int n,String linea) {
		Lineas nodo = new Lineas(linea);
		Lineas puntero = getCabeza() ;
		//Preguntamos si la cabeza no se encuenta vacía
		if (puntero == null || n>=getTamano() ) {
			insertarFinal(linea);
		}else if(n==0) {
			insertarPrincipio(linea);
		}/*else if (n == 1) {
			nodo.setSiguiente(puntero.getSiguiente());
			puntero.setSiguiente(nodo);
			
		}*/
		
		else{
			System.out.println("Insert N \n");
			int i = 1;
			//Mientras no llegemos al final del puntero
			while(i < n) {
				System.out.println("i :"+ i +" linea :" +puntero.getLinea());
				puntero = puntero.getSiguiente();
				i++;
			}
			//Seteamos el siguiente valor del puntero
			nodo.setSiguiente(puntero.getSiguiente());
			//y el siguiente de nuestro puntero es ahora nuestro nodo
			puntero.setSiguiente(nodo);
			
			setTamano(tamano +1);
		}
	}
	//Metodo de mostrar contenido
	public void mostrarContenido() {
		Lineas puntero = getCabeza();
		if (puntero==null) {
			System.out.println("Sin contenido\n");
		}
		int i = 0;
		while (puntero != null) {
			System.out.println(i +" " + puntero.getLinea());
			i++;
			puntero = puntero.getSiguiente();
		}
	}
	
	
	/**
	 * Traspasar el contenido a un string
	 * @return string con el contenido
	 */
	public String contenido2String() {
		Lineas puntero = getCabeza();
		if (puntero==null) {
			return("Sin contenido\n");
		}
		int i = 0;
		String salidaString= "";
		while (puntero != null) {
			salidaString = salidaString + i +" " + puntero.getLinea() +"\n";
			i++;
			puntero = puntero.getSiguiente();
		}
		return salidaString;
	}
	
	/**
	 * Metodo para borrar una linea dado un indice n
	 * @param n
	 */
	public void borrarLinea(int n) {
		if (getTamano() == 1) {
			setCabeza(null);
			setTamano(tamano -1);	
		}else if (n < tamano) {
			Lineas puntero = getCabeza() ;
			int i = 1; 
			//Mientras no llegemos al final del puntero
			while(puntero.siguiente !=null && i < n) {
				puntero = puntero.getSiguiente();
				i++;
			}
			Lineas borrar = puntero.siguiente;
			puntero.siguiente = borrar.siguiente; 
			borrar.siguiente = null;
			setTamano(tamano -1);	
		}
	}
	
	/**
	 * MEtodo que nos indica si no hay nignuna linea
	 * @return
	 */
	public Boolean isEmpty() {return tamano == 0;}
	
	//Setters and Getters
	public Lineas getCabeza() {return Cabeza;}
	public void setCabeza(Lineas cabeza) {this.Cabeza = cabeza;}
	public int getTamano() {return tamano;}
	public void setTamano(int tamano) {this.tamano = tamano;}
	

}
