package modelo;
import java.util.Scanner;

/**
 *  Clase encargada de manejar los dos repositorios, local como remoto
 * Se encarga de agregar cada commit como una pila enlazandola a  los commits anteriores
 * Guarda todo por medios de ua clase de ella, la cual actua como un nodo de una lista enlazada
 * Sus atributos son, cima o último commit, el total de commits
 * Y la clase nodo contiene como atributos el valor de cada commit
 * El workspace o archivos, autor, fecha del commit, comentario , y el commit siguiente a él
 * @version 1.0, 2/09/2020
 * @author Javier López
 * */
public class MiCommit implements Cloneable {
	//Atributos
	private Commit cima = null ;
	//Total de commits
	private int tamano ;
	private Scanner scanner;
	
	
	/**
	 * Cada commit realizado se guardará en una clase tipo nodo para lista enlazada llamada Commit
	*/
	private class Commit{
		//Lista de archivos o index con los archivos con cambios
		private ListaDeArchivos workspace;
		private String autor;
		private String fecha;
		private String mensajeDescriptivoString;
		private Commit siguiente = null;

		/**
		 * Constructor de un commit
		 * @param index workspace a guardar en el commit
		 * @param autor autor del repositorio
		 * @param Mensaje comentario que se quiere añadir en el Commmit
		 * @return new Commit
		 */
		public Commit(MiIndex  index,String Autor, String Mensaje) {
			//Asignamos el index entregado 
			this.setWorkspace(index.getIndex());
			//Asignamos el autor entregado
			setAutor(Autor);
			setFecha();
			setMensajeDescriptivoString(Mensaje);
		}
		
		
		/**
		 * Transformar todo el contenido del commit a string
		 * @return String con el contenido del commit
		 */
		public String commit2String() {
			String salidaString = "" ;
			salidaString = salidaString +("Autor : " + getAutor()+ "\n");
			salidaString = salidaString +("Fecha del commit : " + getFecha() + "\n");
			salidaString = salidaString +("Comentario : " + getMensajeDescriptivoString() + "\n");
			salidaString = salidaString + getWorkspace().archivos2String();
			return salidaString;
		}
		
		//Setters and getters
		public String getAutor() {return autor;}
		public void setAutor(String Autor) {this.autor = Autor;}
		public String getFecha() {return fecha;}
		public void setFecha() {this.fecha = Tiempo.getActualTime();}
		public String getMensajeDescriptivoString() {return mensajeDescriptivoString;}
		public void setMensajeDescriptivoString(String mensajeDescriptivoString) {this.mensajeDescriptivoString = mensajeDescriptivoString;}
		public ListaDeArchivos  getWorkspace() {return workspace;}
		public void setWorkspace(ListaDeArchivos  workspace) {this.workspace = workspace;}
		public Commit getSiguiente() {return siguiente;}
		public void setSiguiente(Commit siguiente) {this.siguiente = siguiente;}
	}
	
	//METODOS---------------------------------------------------------------------------------------------------------------------------------------------------
	
    /** 
     * Genera un commit a partir de dos preguntas, 
     * El index no esta vacío y el index entregado contiene cambios con respecto al ultimo commit
     * Este metodo solo se llama desde la instancia de un localRepository
     * @param index workspace a guardar en el commit
     * @param autor autor del repositorio
     * @return void, pero agrega o no, un nuevo commit al Local Repository
     */
	public void Commit(MiIndex index, String autor) {
		//Para que se ejecute el push primero el index entregado no debe estar vacío
		//Además que debe ser distinto al último index entregado
		if (!index.getIndex().isEmpty() && this.existenCambios(index.getIndex())) {
			String comentrario = "";
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			System.out.println("Ingrese el comentario a su commit : ");
			try {
				comentrario = scanner.nextLine();
			} catch (Exception e) {
				System.out.println("Ha ocurrido un error : "+e+"\n");
			}
			if (comentrario.equals("") || comentrario.equals("\n")) {
				comentrario = "Mi Commit UwU";
			}
			System.out.println("Desea  usar el autor predeterminado o cambiar el nombre del autor\n"
			+ "1.- Autor Predeterminado\n"
			+ "2.- Autor Distinto\n");
			int x = 1;
			try {
				x = scanner.nextInt();
			} catch (Exception e) {
				System.out.println("Ha ocurrido un error : "+e+"\n");
			}
			System.out.println(x +"\n");
			if (x==2) {
				@SuppressWarnings("resource")
				Scanner scanner2 = new Scanner(System.in);
				System.out.println("Ingrese el nombre de su autor\n");
				try {
					autor = scanner2.nextLine();
				} catch (Exception e) {
					System.out.println("Ha ocurrido un error : "+e+"\n");
				}
				if (autor.equals("") || autor.equals("\n")) {
					autor = "Ryan Gosling";
				}
			}
			System.out.println("Autor : " + autor+ "\n");
			//Creamos un nuevo commit a partir del index actual
			if (comentrario == "") {
				comentrario = "Sin comentario\n";
			}
			Commit nodo = new Commit(index,autor,comentrario);
			insertarCommit(nodo);
			System.out.println("Commit creado\n");
		}else {
			System.out.println("El index entregado no posee nuevos cambios con respecto al index anterior\n");
		}
	}
	
	
	/**
	 *  Función que revisa que todos los commits no se encuentren en el remote sean insertados
	 * Esta función se llama desde la instancia de un remote repository
	 * @param localRepository variable del msimo tipo, es el local repository instanciado en el repositorio
     * @return void, pero agrega o no, todos los commits del local que no se encuentren en el remote
	 * */
	public void gitPush(MiCommit localRepository) {
		//Solo si el tamaño de local repository es distinto que el del repositorio actual
		if (getTamano() != localRepository.getTamano()) {
			System.out.println("Pusheando ...\n");
			//Se actualiza el local repository asignandole el valor de la cima de local repository
			this.setCima(localRepository.getCima());
			//Y se actualiza el tamaño del remote repository
			this.setTamano(localRepository.getTamano());
		}else {
			System.out.println("El remote repository se encuentra actualizado\n");
		}
	}
	
	
	/** 
	 * Traer el ultimo commit del remote repository a la zona de trabajos actuales
	 * Metodo se llama 
	 * @param localRepositoy
	 */
	public ListaDeArchivos gitPull(MiCommit localRepository) {
		scanner = new Scanner(System.in);
		String respuestaString = "";
		//Solo es posible si el remote se encuentra actualizado
		if (this.getTamano() < localRepository.getTamano()) {
			System.out.println("El remote se encuentra desactualizado, ¿quiere proceder a transformar el local y el workspace al ultimo commit en remote repository?\n");
			try {
				respuestaString = scanner.nextLine();
			} catch (Exception e) {
				System.out.println("Ha ocurrido un error : "+ e);
			}
			if (respuestaString.equals("Si") || respuestaString.equals("si") || respuestaString.equals("1") || respuestaString.equals("yes") || 
				respuestaString.equals("yup") || respuestaString.equals("1")) {
				//Seteamos una nueva Cima de Local Repository
				localRepository.setCima(this.getCima());
				//Y un nuevo tamaño para local repository
				localRepository.setTamano(this.getTamano());
				return this.getCima().getWorkspace();
			}else {
				System.out.println("No hacer Pull\n");
				return null;
			}
		}
		else if (getTamano() == localRepository.getTamano()){
			System.out.println("El remote se encuentra actualizado, ¿quiere proceder a redifinir el workspace ?\n");
			try {
				respuestaString = scanner.nextLine();
			} catch (Exception e) {
				System.out.println("Ha ocurrido un error : "+ e);
			}
			if (respuestaString.equals("Si") || respuestaString.equals("si") || respuestaString.equals("1") || respuestaString.equals("yes") || 
				respuestaString.equals("yup") || respuestaString.equals("1")) {
				//Obtenemos el ultimo workspace del repositorio
				return this.getCima().getWorkspace();
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	
	/**
	 * Siempre se inserta en la cima el último commit añadido
	 * @param commit
	 */
	public void insertarCommit(Commit commit) {
		commit.setSiguiente(getCima());
		setCima(commit);
		setTamano(getTamano() +1 );
	}
	
	
	/**
	 * Verificar si existen cambios en una lista de archivos con respecto al último commit
	 * @param archivos, lista de archivos con la que se va a comparar con el ultimo commit si exiten cambios
	 * @return  true existen cambios, false no existen cambios
	 */
	public Boolean existenCambios(ListaDeArchivos archivos) {
		//Si el repositorio esta vacío, si existen cambios
		if (this.isEmpty()) {
			return true;
		}
		//Si no esta vacío el repositorio
		else {
			//Obtenemos el último commit del repositorio
			ListaDeArchivos lastWorkspace = getCima().getWorkspace();
			//Pregunto si los index son iguales
			if (lastWorkspace.equals(archivos) || lastWorkspace.listaDeArhivosIguales(archivos)
			|| lastWorkspace.archivos2String().equals(archivos.archivos2String())) {
				System.out.println(
				"No hay cambios \n"+
				"Último workspace : \n "+lastWorkspace.archivos2String() + "\n"+
				"Nuevo workspace : \n "+archivos.archivos2String() + "\n");
				//No hay cambios
				return false;
			}else {	
				System.out.println(
				"Si hay cambios \n"+
				"Último workspace : \n "+ lastWorkspace.archivos2String() + "\n"+
				"Nuevo workspace : \n "+archivos.archivos2String() + "\n");
				//Si hay cambios
				return true;
			}
		}
	}	

	/**
	 *  gitLog
	 * Esta funcionalidad debe mostrar una lista con los últimos 5 commits del local repository por medio de un string
	 * (indicando fecha, mensaje descriptivo y archivos añadidos). Si hay
	 * menos de 5 commits, muestra todos los que estén disponibles.
	 * */
	public String gitLog() {
		String salidaString = "";
		Commit puntero = getCima();
		int i = 0;
		if (puntero!=null) {
			//Mientras el puntero sea un valor existente y además el indice sea menor a 5
			while (puntero!=null && i < 5) {
				salidaString = salidaString + puntero.commit2String() + "\n";
				puntero = puntero.getSiguiente();
				i ++ ;
			}
			System.out.println("Sus últimos " + i +" commits son  : \n");
		}else {
			salidaString = "Repositorio vacío";
		}
		return salidaString;
	}

	/**
	 * repositorio2String
	 * Esta funcionalidad debe mostrar una lista con todos los commits del local repository por medio de un string
	 * @return String indicando fecha, mensaje descriptivo y archivos añadidos
	 * */
	public String repositorio2String() {
		String salidaString = "";
		Commit puntero = getCima();
		if (puntero!=null) {
			//Mientras el puntero sea un valor existente y además el indice sea menor a 5
			while (puntero!=null) {
				salidaString = salidaString + puntero.commit2String() + "\n";
				puntero = puntero.getSiguiente();
			}
		}else {
			salidaString = "Repositorio vacío";
		}
		return salidaString;
	}
	
	/**
	 * Metodo implementado de ultimo moemento, sirve para clonar una lista de Commits
	 * Y asi diferenciar direcciones de memoria
	 */
   public MiCommit clone() throws CloneNotSupportedException {
	   try
	   {
		   MiCommit clonedMyClass = (MiCommit)super.clone();
	       // if you have custom object, then you need create a new one in here
	       return clonedMyClass ;
	   } catch (CloneNotSupportedException e) {
	       e.printStackTrace();
	       return new MiCommit();
	   }
   }
	
	
	/**
	 * Metodo que verifica si actualmente esta vacío el repositorio
	 * @return
	 */
	public boolean isEmpty() {return getTamano() == 0;}
	
	//Setters and Getters
	public Commit getCima() {return cima;}
	public void setCima(Commit cima) {this.cima = cima;}
	public int getTamano() {return tamano;}
	public void setTamano(int Tamano) {tamano = Tamano;}
}
