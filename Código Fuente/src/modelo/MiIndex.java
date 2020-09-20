package modelo;

/* Clase de hacer el manejo del index, podría ser una lista de archivos simplemente como el workspace
 * Y que esa lista sea manipulada por el mismo repositorio, pero necesitamos que una clase se ocupe enteramente del index
 * Para no dejar sobrecargada a la clase repositorio
 * Que agrege los ditintos archivos del workspace que el usuario quiera agregar al index
 * @version 1.2, 2/09/2020
 * @author Javier López
 * */
public class MiIndex{
	//Lista de archivos del index
	private ListaDeArchivos index = new ListaDeArchivos();
	
	
	//Metodos
	
	/**
	 * Menú gitAdd que se le entrega el workspace actual
	 * @param workspace
	 * @throws InterruptedException
	 */
	public void gitAdd(MiWorkspace workspace,int modo,int tamano,int[] archivos){
			System.out.println("Git Add Modelo\n");
			//Add all
			if (modo== 0) {
				//Indicamos que index ahora es igual al index
				agregarVariosIndex(workspace, workspace.getTamano(),archivos);
				System.out.println("Git Add All \n");
				System.out.println("Su index quedo como : \n"+index.archivos2String());
			}
			//Agregar varios archivos
			if (modo== 1){
					System.out.println("Agregar 1 \n");
					agregarVariosIndex(workspace, 1, archivos);
			}
			if(modo==2) {
				System.out.println("Agregar varios \n");
				agregarVariosIndex(workspace, tamano, archivos);
			}
	}
	
	/**
	 * Agregar archivo del workspace
	 * @param workspace
	 * @param indice
	 * @return
	 */
	public int agregarIndex(MiWorkspace workspace, int indice) {
		//Procedemos a preguntamos si podemos obtener el archivo
		if (workspace.getArchivoNCopy(indice)!= null) {
			//Decimos que el archivo a añadir es el archivo que obtuvimos
			Archivo archivo = workspace.getArchivoNCopy(indice);
			//Solo si el archivo no se encuentra dentro del index
			if (!index.isInside(archivo)){
				index.anadirArchivo(archivo);
				return 1;
			}else {
				return 0;
			}
		}else {
			System.out.println("Indice de archivo inválido");
			return 0;
		}
	}
	
	/**
	 * Agregar varios archivo del workspace al index
	 * @param workspace
	 * @param tamano
	 */
	public void agregarVariosIndex(MiWorkspace workspace,int tamano,int [] archivos) {
		System.out.println("total : " + tamano);
		//Si es igual al tamaño es un
		//Git all
		if(tamano == workspace.getTamano()) {
			System.out.println("Add all\n");
			//git add all
			int indice = 0;
			System.out.println("Tamaño workspace : " + workspace.getTamano() + "\n");
			//Mientras el indice sea menor al total vamos agregando todos los archivos
			while ( indice < tamano) {
				System.out.println("i:"+indice+"\n");
				agregarIndex(workspace, indice);
				indice++;
			}
		}
		//Si es un tamaño igual a 1 es un git one
		else if (tamano==1) {
			agregarIndex(workspace, archivos[0]);
		}
		else {
			int i= 0;
			//git add algunos
			while (i<tamano) {
				agregarIndex(workspace, archivos[i]);
				i++;
			}
		}
	}
	
	/*Metodo que indica si el index se encuentra vacío
	 * @return true : vacío || false : no vacío
	 * */
	public Boolean isEmpty() {return index.isEmpty();}
	//Limpiar Index, asignamos una lista de archivos vacía
	public void limpiarIndex(){setIndex(new ListaDeArchivos());}
	//Obtener el tamaño total de archivos
	public int getTamano() {return index.getTamano();}
	//Obtener el index en sí
	public ListaDeArchivos getIndex() {return index;}
	//Asignar index
	public void setIndex(ListaDeArchivos index) {this.index = index;}
	//Obtener los nombres del index
	public String nombreArchivo2String() {return index.nombreArchivo2String(1);	}
}
