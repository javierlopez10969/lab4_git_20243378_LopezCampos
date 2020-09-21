package modelo;

/* Clase encargada de la manipulación del workspace, podría ser una lista de archivos simplemente el workspace
 * Y que esa lista sea manipulada por el mismo repositorio, pero necesitamos que una clase se ocupe enteramente del workspace
 * Para no dejar sobrecargada a la clase repositorio
 * Que edite, cree, y borre archivos, y llame a los disitnos metodos pertenecientes a la clase lista de archivos
 * @version 1.2 2/09/2020
 * @author Javier López
 * */

public class MiWorkspace implements Cloneable{
	//Atributos : lista de archivos
	private ListaDeArchivos archivos = new ListaDeArchivos();

	//Metodos
	
	/**
	 * Este metodo crea un archivo dado un string
	 * @throws InterruptedException
	 */
	public Boolean crearArchivo(String nombreArchivo){
		if (nombreArchivo.equals("") || nombreArchivo.equals("\n") || nombreArchivo.equals(" ")) {
			nombreArchivo = "NuevoArchivo.c";
		}
		System.out.println("Archivo : " + nombreArchivo);
		Archivo archivo = new MiArchivo(nombreArchivo);
		archivo.setContenidoString("");
		//Solo si el archivo no se encuentra en el workspace
		if (!archivos.isInside(archivo)) {
			archivos.anadirArchivo(archivo);
			return true;
		}else {
			System.out.println("Hay un archivo con el mismo nombre, no procede a crear nuevo archivo\n");
			return false;
		}		
	}
	
	
	/**
	 * Método encargado de borrar un archivo

	public void borrarArchivo() {
		if (!isEmpty()) {
			//Mostramos el workspace
			System.out.println(workspace2String());
			System.out.println("Ingrese el indice delarchivo que desee borrar : \n");
			int respuesta = 0;
			Scanner scanner = new Scanner(System.in);
			try {
				respuesta = scanner.nextInt(); 
			} catch (Exception e) {
				System.out.println("F");
			}
			archivos.borrarArchivo(respuesta);
			scanner.close();
		}else {
			System.out.println("No se puede borrar ningún archivo, ya que el workspace se encuentra vacío\n");
		}		
	}
	 */

	/**
	 * Metodo, que permite editar un archivo
	 */
	public void editarArchivo(int indiceArchivo,String contenido) {
		//Antes de editar preguntamos si el workspace no esta vacío
		if (!isEmpty()) {
			if (archivos.getArchivoN(indiceArchivo)!= null) {
				Archivo archivo  = archivos.getArchivoN(indiceArchivo);
				archivo.setContenidoString(contenido);
			}else {
				System.out.println("Indice de archivo inválido\n");
			}
		}
	}
	
	//Métodos que son operado por la lista de archivos, son metodos simples que no necesitan interacción 
	//Con el usuario

	//Mostrar todo el workspace
	public String workspace2String() {return archivos.archivos2String();}
	public String nombreFecha2String() {return archivos.nombreFechas2String();}
	public String nombreArchivo2String() {return archivos.nombreArchivo2String(0);}
	//El workspace se encuentra vacío
	public boolean isEmpty() {return archivos.isEmpty();}

	
	//Setters and getters
	
	//Archivo n
	public Archivo getArchivoN(int n) {return archivos.getArchivoN(n);}
	//Copiar archivon
	public Archivo getArchivoNCopy(int n) {return archivos.getArchivoNCopy(n);}
	
	//Nuevos archivos (index)
	public void setArchivos(ListaDeArchivos archivos) {
		//Actualizamos tanto tamaño
		this.archivos.setTamano(archivos.getTamano());
		//Como los archivos
		this.archivos = archivos;}
	
	//Obtener archivos
	public ListaDeArchivos getArchivos() {return archivos;}
	public int getTamano() {return archivos.getTamano();}
	//Obtener string con los nombres de los archivos
	public String[] getArregloNombre() {return archivos.nombreArchivo2StringArray();}
	
	
}
