package modelo;

public class MiRepositorio implements Cloneable{
	//Atributos
	
	//Datos respecto al repositorio
	private String nombreRepositorio;
	private String autor;
	private String fechaDeCreacion;
	private String branch = "Master";

	//Zonas de trabajo
	

	private MiWorkspace workspace;	//Workspace
	private MiIndex  index;			//Index
	private MiCommit localRepository;//Local Repository
	private MiCommit remoteRepository;	//Remote Repository

	//Repositorio siguiente en caso de crear más ramas
	private MiRepositorio siguiente = null;
	
	
	
	//METODOS 
	
	//METODOS CONTRUCTORES
	
	/**
	 * Clonador de repositorio
	 */
	   public MiRepositorio clone() throws CloneNotSupportedException {
		   try
		   {
			   MiRepositorio clonedMyClass = (MiRepositorio)super.clone();
		       // if you have custom object, then you need create a new one in here
		       return clonedMyClass ;
		   } catch (CloneNotSupportedException e) {
		       e.printStackTrace();
		       return new MiRepositorio();
		   }
	   }
	
	/**
	 * Contstructor a partir de otro repositorio
	 */
	public void copiarAtributos(MiRepositorio repositorio) {
		MiIndex index = new MiIndex();
		int TamanoWorkspace = repositorio.getWorkspace().getTamano();
		if (TamanoWorkspace>0) {
			index.agregarVariosIndex(repositorio.getWorkspace(),TamanoWorkspace,null);
			this.workspace = new MiWorkspace();
			//Copiamos todo el workspace mediante una función del index
			this.setWorkspace(index.getIndex());
		}else {
			this.workspace = new MiWorkspace();
		}
		//Seteamos el index
		this.index = new MiIndex();
		MiCommit oldLocal = repositorio.getLocalRepository();
		MiCommit oldRemote = repositorio.getRemoteRepository();
		MiCommit newLocal = repositorio.getLocalRepository();
		MiCommit newRemote = repositorio.getRemoteRepository();
		try {
			newLocal = (MiCommit)oldLocal.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			newRemote = (MiCommit)oldRemote.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setLocalRepository(newLocal);
		this.setRemoteRepository(newRemote);
	}
	
	
	/**
	 * Constructor del repositorio con gitinit
	 * @param autor
	 * @param nombreRepositorio
	 */
	public void gitInit(String autor,String nombreRepositorio ) {
		setAutor(autor);
		setNombreRepositorio(nombreRepositorio);
		setFechaDeCreacion(Tiempo.getActualTime() );
		workspace = new MiWorkspace();
		index = new MiIndex();
		localRepository = new MiCommit();
		remoteRepository = new MiCommit();
	}

	
	
	
	//METODOS DE LAS ZONAS DE TRABAJO 
	
	
	//Metodos del Workspace
	
	//Se hacen llamadas a los metodos del workspace
	public void editarArchivo(int indiceArchivo, String contenido) {workspace.editarArchivo(indiceArchivo, contenido);}
	//public void borrarArchivo() {workspace.borrarArchivo();}
	public Boolean crearArchivo(String nombreArchivo) {return workspace.crearArchivo(nombreArchivo);}
	
	
	
	//MetodosIndex
	
	//Menú que pregunta que archivos quiere añadir al index
	public void gitAdd(int modo,int tamano,int[] archivos){index.gitAdd(getWorkspace(),modo,tamano,archivos);}
	//Llamar al metodo del index para transformarlo a una variable del tipo string
	public String index2String() {
		if (index.isEmpty()) {
			return "Index Vacío\n";
		}
		return index.getIndex().archivos2String();
	}
	public void limpiarIndex() {index.limpiarIndex();}
	
	
	
	
	
	//Metodos Local Repository
	
	
	//Crear un commit a partir de un index no vacío
	public Boolean gitCommit(String autorString,String message){
		Boolean confirmacion = localRepository.Commit(index, autorString,message);
		index.limpiarIndex();
		return confirmacion;
	}
	//Función que se encarga de imprimir el resultado obtenido en gitLog del local repository
	public String gitLog() {return localRepository.gitLog();}
	
	
	
	
	//Metodos Remote Repository	
	
	
	
	//Función que se encarga de imprimir el resultado obtenido en gitLog del remote repository
	public String gitLogRemote() {return remoteRepository.gitLog();}
	//Metodo para realizar el push del repositorio
	public void gitPush(){
		System.out.println("Making push.... modelo\n");
		remoteRepository.gitPush(localRepository);
	}
	
	//Traer el ultimo commit a cambios locales
	public Boolean gitPull(){
		if (remoteRepository.isEmpty()) {
			System.out.println("Remote repositroy vacío no procede hacer pull\n");
			System.out.println("Tamaño del repositorio :  " + localRepository.getTamano());
			return false;
		}
		else {
			//Si es distinto de  nulo el resultado de este metodo seteamos el valor otorgado
			ListaDeArchivos archivos = remoteRepository.gitPull(localRepository);
			System.out.println("Workspace en remote es : \n"+ archivos.archivos2String());
			System.out.println("Actualizar workspace, workspace antes : \n" + workspace2String());
			workspace.setArchivos(archivos);
			System.out.println("Workspace ahora : \n+" + workspace2String());
			return true;
		}		
	}
	
	
	
	
	//METODOS DE AMBITO GENERAL
	
	
	/**
	 * git Status
	 * i. Información del repositorio (nombre y autor)
	 * ii. Número de archivos en el Workspace
	 * iii. Número de archivos en el Index
	 * iv. Número de commits en el Local Repository
	 * v. SI el Remote Repository está al día (o no) con los cambios del Local Repository
	 */
	public String gitStatus() {
		String salidaString = 
		"Autor : " + getAutor()+
		"\nNombre Repositorio  : " + getNombreRepositorio() +
		"\nFecha de creación  : " + getFechaDeCreacion() +
		"\nRama : " + getBranch() +
		"\nTotal de archivos en workspace : " + workspace.getTamano()+
		"\nTotal de archivos en el index  : " + index.getTamano()+
		"\nTotal de commits en el local repository : " + localRepository.getTamano()+
		"\nEl remote repository se encuentra " + remoteActualizadoString() ;
		return salidaString ;
	}
	
	
	
	/**
	 * Metodo que revisa si los remote se encuentra actualizado de acuerdo al tamaño
	 * @return
	 */
	public String remoteActualizadoString() {
		if (localRepository.isEmpty()) {
			return "vacío, ya que no hay ningun commit realizado";
		}
		else if (remoteRepository.getTamano() == localRepository.getTamano()) {
			return "actualizado";
		}else {
			return "desactualziado";
		}
	}
	
	
	
	//Remote Status
	public Boolean remoteActualizadoBoolean() {return remoteRepository.getTamano() == localRepository.getTamano();}
	
	//Funciones secretas para repositorio para ver los estados de los repositorios
	public String repositorioLocal2String() {return localRepository.repositorio2String();}
	public String repositorioRemoto2String() {return remoteRepository.repositorio2String();}
	
	
	
	
	//Setters and Getters
	
	//Repositorio
	public String getNombreRepositorio() {return nombreRepositorio;}
	public void setNombreRepositorio(String nombreRepositorio) {this.nombreRepositorio = nombreRepositorio;}
	public String getAutor() {return autor;}
	public void setAutor(String autor) {this.autor = autor;}
	public String getFechaDeCreacion() {return fechaDeCreacion;}
	public void setFechaDeCreacion(String fechaDeCreacion) {this.fechaDeCreacion = fechaDeCreacion;}
	
	//Repositories
	
	//Workspace
	public void setWorkspace(ListaDeArchivos archivos) {workspace.setArchivos(archivos);;}
	public MiWorkspace getWorkspace() {return workspace;}
	public Boolean workspaceEmpty() {return workspace.isEmpty();}
	public int getNWorkspace() {return workspace.getTamano();}
	public String workspaceStatus() {return workspace.nombreArchivo2String();}
	public String workspace2String() {return workspace.workspace2String();}
	
	//Index
	public ListaDeArchivos getIndex() {return index.getIndex();}
	public String indexStatus() {return index.nombreArchivo2String();}
	
	//Local
	public void setLocalRepository(MiCommit localRepository) {this.localRepository = localRepository;}
	public void setRemoteRepository(MiCommit remoteRepository) {this.remoteRepository = remoteRepository;}
	public MiCommit getLocalRepository() {return localRepository;}
	public MiCommit getRemoteRepository() {return remoteRepository;}
	
	//Branches
	public String getBranch() {return branch;}
	public void setBranch(String branch) {this.branch = branch;}
	public MiRepositorio getSiguiente() {return siguiente;}
	public void setSiguiente(MiRepositorio siguiente) {this.siguiente = siguiente;}
}
