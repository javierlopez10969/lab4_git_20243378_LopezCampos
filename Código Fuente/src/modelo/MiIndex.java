package modelo;
import java.util.Scanner;

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
	public void gitAdd(MiWorkspace workspace) throws InterruptedException{
		//Solo si el workspace no se encuentra vacío
		if (!workspace.isEmpty()) {
			//Variables de múltiples entradas
			int x = -1;
			System.out.println("Git Add\n");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			while(x < 5) {
					//Mostramos los archivos en el workspace
					System.out.println("Workspace actual : ");
					System.out.println(workspace.nombreFecha2String());
					//Mostramos los archivos en el index
					System.out.println("Index Actualmente : \n");
					if (index.isEmpty()) {
						System.out.println("Index vacío\n");
					}else {
						System.out.println(index.nombreFechas2String());
					}
					System.out.println("\nQue desea hacer:\n"+
					"1.-Agregar todos los archivos \n"+
					"2.-Agregar varios archivos\n" +	
					"3.-Agregar un archivo \n"+
					"4.-Mostrar archivos en Index\n"+
					"5.-Terminar Add\n"+
					"Ingrese una opción : ");
					try {
						x = scanner.nextInt();
					} catch (Exception e) {
						System.out.println("Ha ocurrido un error" + e);
					}
					 
					System.out.println("Entrada : " + x);
					//Git add -All
					if (x== 1) {
						//Indicamos que index ahora es igual al index
						agregarVariosIndex(workspace, workspace.getTamano());
						System.out.println("Git Add All \n");
						System.out.println("Su index quedo como : \n"+index.archivos2String());
						break;
					}
					//Agregar varios archivos
					else if (x== 2){
						System.out.println("Ingrese cuantos archivos quiere ingresar");
						int tamano = 0;
						try {
							tamano = scanner.nextInt();
						} catch (Exception e) {
							System.out.println("Algo salio mal");
						}
						agregarVariosIndex(workspace, tamano);
					}
					//Agregar un archivo
					else if (x== 3){
						System.out.println("Ingrese el indice del archivo que quiere agregar al index");
						int indice = 0;
						try {
							indice = scanner.nextInt();
						} catch (Exception e) {
							System.out.println("Algo salió mal");
						}
						agregarIndex(workspace,indice);
					}
					else if (x== 4){
						System.out.println("Index Actualmente : \n");
						System.out.println(index.archivos2String());
						Thread.sleep(4000);
					}
					else if (x== 5){
						System.out.println("Add Terminado\n");
						break;
					}
					else{
						System.out.println("Ingrese opción válida\n X : " + x);
						x = -1;
					}
			}
		}else {
			System.out.println("Workspace vacío, no procede hacer add\n");
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
		if (workspace.getArchivoN(indice)!= null) {
			//Decimos que el archivo a añadir es el archivo que obtuvimos
			Archivo archivo = workspace.getArchivoN(indice);
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
	public void agregarVariosIndex(MiWorkspace workspace,int tamano) {
		System.out.println("total : " + tamano);
		//Si el tamaño es mayor que los archivos totales del workspace
		if (tamano > workspace.getTamano() || tamano <= 0 ) {
			System.out.println("Indice supera el total de archivos");
		}
		//Si es igual
		else if(tamano == workspace.getTamano()) {
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
		else {
			Scanner scanner = new Scanner(System.in);
			//git add algunos
			while (tamano >0) {
				System.out.println("Ingrese el indice del archivo que quiere agregar al index");
				int indice = 0;
				try {
					indice = scanner.nextInt();
				} catch (Exception e) {
					System.out.println("Algo salió mal : " + e + "\n");
				}
				agregarIndex(workspace, indice);
				tamano --;
			}
			scanner.close();
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
}
