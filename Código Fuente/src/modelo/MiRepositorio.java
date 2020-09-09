package modelo;

import utils.ListaDeArchivos;

public class MiRepositorio implements Cloneable{
	//Atributos
	private String nombreRepositorio;
	private String autor;
	private String fechaDeCreacion;
	private String branch = "Master";

	//Zonas de trabajo
	//Workspace
	private MiWorkspace workspace;
	private MiIndex  index;
	
	//Repositories
	private MiCommit localRepository;
	private MiCommit remoteRepository;
	
	//Repositorio siguiente en caso de crear más ramas
	private MiRepositorio siguiente = null;
	
	
	//Metodos
	
	/**
	 * Metodo constructor de repositorio
	 */
	public MiRepositorio() {
	}
	
	
	/*
	 * Seteador de atributos a partir de otro repositorio copiador
	 */
	public void copiarAtributos(MiRepositorio repositorio) {
		MiIndex index = new MiIndex();
		int TamanoWorkspace = repositorio.getWorkspace().getTamano();
		if (TamanoWorkspace>0) {
			index.agregarVariosIndex(repositorio.getWorkspace(),TamanoWorkspace);
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

	//Metodos del Workspace
	//Se hacen llamadas a los metodos del workspace
	public void editarArchivo() throws InterruptedException {workspace.editarArchivo();}
	public String workspace2String() {return workspace.workspace2String();}
	public void borrarArchivo() {workspace.borrarArchivo();}
	public void crearArchivo() throws InterruptedException{workspace.crearArchivo();}
	
	//MetodosIndex
	
	//Menú que pregunta que archivos quiere añadir al index
	public void gitAdd() throws InterruptedException{index.gitAdd(getWorkspace());}
	//Llamar al metodo del index para transformarlo a una variable del tipo string
	public String index2String() {
		if (index.isEmpty()) {
			return "Index Vacío\n";
		}
		return index.getIndex().archivos2String();
	}
	
	
	//Metodos Local Repository
	/* Crear un commit a partir de un index no vacío
	 * */
	public void gitCommit(){
		if (!index.isEmpty()) {
			localRepository.Commit(index, autor);
			index.limpiarIndex();
		}else {
			System.out.println("Index vacío no procede hacer el commit\n");
		}
	}
	
	//Función que se encarga de imprimir el resultado obtenido en gitLog del local repository
	public void gitLog() {System.out.println(localRepository.gitLog());}
	
	
	//Metodos Remote Repository	
	public void gitPush(){
		if (localRepository.isEmpty()) {
			System.out.println("Local repositroy vacío, no procede hacer push\n");
		}
		else if (remoteActualizadoBoolean()){
			System.out.println("Remote repositroy se encuentra actualizado, no procede hacer push\n");
		}
		else {
			System.out.println("Making push ...\n");
			remoteRepository.gitPush(localRepository);
		}
		
	}
	public void gitPull(){
		if (remoteRepository.isEmpty()) {
			System.out.println("Remote repositroy vacío no procede hacer pull\n");
			System.out.println("Tamaño del repositorio :  " + localRepository.getTamano());
		}
		else {
			//Si es distinto de  nulo el resultado seteamos el valor otorgado por este metodo
			ListaDeArchivos archivos = remoteRepository.gitPull(localRepository);
			System.out.println("Workspace en remote es : \n"+ archivos.archivos2String());
			if (archivos != null) {
				System.out.println("Actualizar workspace, workspace antes : \n"+ workspace2String());
				//Actualizar zona de trabajo
				workspace.setArchivos(archivos);
				System.out.println("Workspace ahora : \n+"+ workspace2String());	
			}			
		}		
	}
	
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
		"\nAutor : " + getAutor()+
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
	
	
	public Boolean remoteActualizadoBoolean() {return remoteRepository.getTamano() == localRepository.getTamano();}
	//Funciones secretas para el creador para ver los estados de los repositorios
	public void gitLogRemote() {System.out.println(remoteRepository.gitLog());}
	public void mostrarRepositorioLocal() {System.out.println(localRepository.repositorio2String());}
	public void mostrarRepositorioRemoto() {System.out.println(remoteRepository.repositorio2String());}
	
	//Setters and Getters
	public String getNombreRepositorio() {return nombreRepositorio;}
	public void setNombreRepositorio(String nombreRepositorio) {this.nombreRepositorio = nombreRepositorio;}
	public String getAutor() {return autor;}
	public void setAutor(String autor) {this.autor = autor;}
	public String getFechaDeCreacion() {return fechaDeCreacion;}
	public void setFechaDeCreacion(String fechaDeCreacion) {this.fechaDeCreacion = fechaDeCreacion;}
	//Repositories
	public void setWorkspace(ListaDeArchivos archivos) {workspace.setArchivos(archivos);;}
	public MiWorkspace getWorkspace() {return workspace;}
	public Boolean workspaceEmpty() {return workspace.isEmpty();}
	public ListaDeArchivos getIndex() {return index.getIndex();}
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
