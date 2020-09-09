package modelo;

import java.util.Scanner;
/**
 * Clase Branches que permite almacenar distintos repositorios, con disitntas nombres de rama
 * Contiene disitntos metodos para crear ramas e intercambiarse y/o moverse entre ellas
 * @author javier
 *
 */
public class Branches {
	//Atributos
	
	//Rama master o rama 
	private MiRepositorio master;
	//Total de ramas
	private int tamano;
	
	
	
	//METODOS---------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Constructor de las distitnas ramas a partir 
	 * @param master
	 */
	public Branches(MiRepositorio master){
		setMaster(master);
		//Decimos que el tamaño total de branches es uno
		setTamano(1);
	}
	/**
	 * GitBranch, funcionalidad extra, que permite el crear una nueva rama a partir de master
	 * @return
	 */
	public MiRepositorio gitBranch() {
		System.out.println("¿Cuál es el nombre de su rama nueva a crear a partir de master ? \n");
		String respueString = "";
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		try {
			respueString = scanner.nextLine();
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error "+ e + "\n");
		}
		if (respueString.equals("")|| respueString.equals("\n")) {
			respueString = "MiRama";
		}
		//Si el nombre nuevo no se encuentra ya dentro de una de las ramas
		if (!isInside(respueString)) {		
		//Decimos que la nueva Branch es exactamente igual a como ha quedado master
		MiRepositorio master = getMaster();
 		//Intentamos copiar el repositorio master en una nueva dirección de memoria
		//Por mientras la seteamos en el master normal
		MiRepositorio newBranch = getMaster();
		try {
			//La clonamos
			newBranch = (MiRepositorio)master.clone();
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Seteamos el nombre de la nueva branch
		newBranch.setBranch(respueString);
		//Seteamos correctamente los atributos
		newBranch.copiarAtributos(getMaster());
		//Y preguntamos si quiere seguir con el mismo autor o uno nuevo
		System.out.println("¿Quiere cambiar de autor? \n"+
		"Sí / no \n");
		try {
			respueString = scanner.nextLine();
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error \n"+ e);
		}
		if (respueString.equals("si") || respueString.equals("Si") || respueString.equals("YES")
		|| respueString.equals("1") || respueString.equals("SIPO") || respueString.equals("yes")
		|| respueString.equals("YES") || respueString.equals("Sí") ) {
			System.out.println("Ingrese el nombre de su autor :\n");
			try {
				respueString = scanner.nextLine();
			} catch (Exception e) {
				System.out.println("Ha ocurrido un error \n"+ e);
			}
			if (respueString.equals("")) {
				respueString = "Ryan Gosling";	
			}
			newBranch.setAutor(respueString);
		}
		System.out.println("Autor : " + newBranch.getAutor() + "\n" +
		"Nueva Branch creada : \n" + newBranch.gitStatus());
		insertarBranch(newBranch);
		//Devolvemos la ultima branch
		return getBranchN(getTamano()-1) ;
		}else {
			System.out.println("El nombre de esa rama ya existe, no procedemos a crear nueva rama\n"
					+ "Volviendo a Master \n");
			return getMaster();
		}
	}
	
	/**
	 * Insertar Branch, una vez creada la branch en el metodo gitBranch, se procede a insertar la nueva 
	 * Rama creada al final de la lista enlazada de repositorios
	 * @param newBranch
	 */
	public void insertarBranch(MiRepositorio newBranch) {
		if (getTamano() == 1) {
			master.setSiguiente(newBranch);
			setTamano(getTamano() + 1);
		}else {
			//Iniciamos un iterador en 0 y un puntero de los distintos repositorios
			int i = 1;
			MiRepositorio punteroMiRepositorio = getMaster();
			while (i<getTamano() && punteroMiRepositorio !=null) {
				punteroMiRepositorio = punteroMiRepositorio.getSiguiente();
				i++;
			}
			punteroMiRepositorio.setSiguiente(newBranch);
			setTamano(getTamano() + 1);
		}
	}
	
	/**
	 * Metodo que nos permite cambiar de ramas, le pregutna al usuario a cual rama se quiere cambiar
	 * @return MiRepositorio, repositorio en el cual nos vamos a cambiar, se efectua el cambio en el metodo main
	 */
	public MiRepositorio gitCheckOut() {
		if (getTamano() == 1) {
			System.out.println("Actualmente solo hay una rama, para hacer checkout, deberá crear una rama nueva\n");
			return getMaster();
		}else {
			System.out.println("Estás son sus ramas : \n" +
			branches2String() +
			"\n Eliga el indice : de la branch que quiere cambiarse ");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			int n = 0;
			try {
				n = scanner.nextInt();
			} catch (Exception e) {
				System.out.println("Ha ocurrido un error"+ e +"\n");
			}
			if (n > getTamano() || n < 0) {
				System.out.println("El indice ingresado supera los limites \n" + 
				"Volviendo a Maste \n");
				return getMaster();
			}
			return getBranchN(n);

		}
	}
	
	/**
	 * Metodo que nos permite obtener una rama n, para despues cambiarnos a esa rama
	 * @param n, indice donde nos queremos cambiar
	 * @return Mi Repositroio, devuelve el repositorio al cual nos queremos cambiar
	 */
	public MiRepositorio getBranchN(int n) {
		if (n == 0) {
			return getMaster();
		}else {
			int i = 0 ;
			//Obtener el repositori segun la branch
			MiRepositorio punteroMiRepositorio = getMaster();
			while (i < n && i < getTamano() && punteroMiRepositorio != null) {
				punteroMiRepositorio = punteroMiRepositorio.getSiguiente();
				i++;
			}
			return punteroMiRepositorio;
		}
	}
	
	/**
	 * Transforma todas las ramas presentes a un estado string, para posteriormente mostrarlas por pantalla
	 * @return
	 */
	public String branches2String() {
		int i = 0; String salidaString ="";
		MiRepositorio punteroMiRepositorio = getMaster();
		while (i < getTamano() && punteroMiRepositorio!= null) {
			salidaString = salidaString + i + ".-\n" +  punteroMiRepositorio.gitStatus() + "\n\n\n";  
			punteroMiRepositorio = punteroMiRepositorio.getSiguiente() ;
			i++;
			
		}
		return salidaString;
	}
	
	/**
	 * Metodo para verficar si un nombre ya existe como rama
	 * @param name
	 * @return true, si hay un repositorio con la rama del mismo nombre, falso si no se encuentra
	 */
	public Boolean isInside(String name) {
		int i = 0 ;
		MiRepositorio punteroMiRepositorio = getMaster();
		while (i < getTamano() && punteroMiRepositorio != null) {
			//Si hay un repositorio branch con el mismo nombre, retornamos que si se encuentra
			if (punteroMiRepositorio.getBranch().equals(name)) {
				return true;
			}
			punteroMiRepositorio = punteroMiRepositorio.getSiguiente();
			i++;
		}
		//En cambio si completamos todo el recorrido devolvemos verdadero
		return false;
	}
	
	
	//Getters and Setters
	public MiRepositorio getMaster() {return master;}
	public void setMaster(MiRepositorio master) {this.master = master;}
	public int getTamano() {return tamano;}
	public void setTamano(int tamano) {this.tamano = tamano;}
}
