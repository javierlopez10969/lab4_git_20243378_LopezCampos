package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;

import modelo.Branches;
import modelo.MiRepositorio;
import vista.Editor;
import vista.MainVista;
import vista.Ventana;

/**
 * Clase controlador de procesos del modelo, es la encargada de interactuar con el modelo y la vista
 * @author javier
 *
 */
public class MainController {

	//Atributos
	
	//Ventana de la vista
	private static Ventana ventana = new Ventana();
	
	//Repositorio del modelo
	private static MiRepositorio repositorio = new MiRepositorio();
	
	//Ramas del modelo
	private static Branches branches ;

	
	//Main para inicializar el controlador de la pantalla de inicio
	public static void main(String[] args) {init();}
	
	/**
	 * Le damos la función de tener un action listener1
	 * Este metodo inciializa el controlador de la pantalla de inicio
	 */
	private static void init() {
	    ActionListener ActionListener1 = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Si los valores son strings correctos
				if (ventana.repositorio.getText().equals("") && ventana.autor.getText().equals("")) {
					//Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon)
					int reply = JOptionPane.showConfirmDialog(
							null, 
							"Desea continuar con valor predeterminados?",
							"Opciones",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							MainVista.gato);
					
					if (reply == JOptionPane.YES_OPTION) {
					    gitInit();
					} else {
						MainVista.mostrarMensaje("Ingrese sus valores", 0);
					}
				}
				else {
					gitInit();
				}
			}
	    };
		ventana.iniciar.addActionListener(ActionListener1);
	}
	
	
	/**
	 * Metodo que inicializa el repositorio
	 */
	private static void gitInit() {
		String autorInit = ventana.autor.getText();
		String repositorioInit = ventana.repositorio.getText();
		ventana.panel.setVisible(false);
		if (autorInit.equals("")) {autorInit = "Samuel L Jackson";}
		if (repositorioInit.equals("")) {repositorioInit = "Mi Repositorio";}
		//Inicializamos el repostorio
		repositorio.gitInit(autorInit, repositorioInit);
		ventana.autorLabel.setText(autorInit);
		ventana.repositorioLabel.setText(repositorioInit);
		ventana.ramaLabel.setText("Branch: "+repositorio.getBranch());
		branches = new Branches(repositorio);
		ventana.iniciarPanel2();
		System.out.println("Su autor es : " + autorInit + "\nSu repositorio es :"+repositorioInit+"\n");
		controlDeComandos();

	}
	
	private static void controlDeComandos() {
		ActionListener control = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Comandos
//REINIT
				if (e.getSource()==Ventana.reInit) {
					String opcionString =  MainVista.mostrarMensaje("¿Desea reiniciar el repositorio?", 4);
					int opcion = Integer.valueOf(opcionString);
					if (opcion== 0 ) {
						System.out.println("Opción : "+ opcion);
						String autorString = MainVista.mostrarMensaje(
						"Ingrese su nuevo autor",
						"Nuevo autor",
						2);
						if (autorString!=null) {
							String repositorioString = (String)JOptionPane.showInputDialog(
							null,
							"Ingrese su nuevo autor",
							"Nuevo autor",
							JOptionPane.QUESTION_MESSAGE,
							MainVista.gato,
							null,
							"Commit");
							if (autorString.charAt(0)==' ' ||  autorString.equals("")) {
								autorString = "Ryan Gosling";}
							if (repositorioString!=null) {
								repositorio =new MiRepositorio();
								repositorio.gitInit(autorString,repositorioString);
								branches = new Branches(repositorio);
								ventana.autorLabel.setText(repositorio.getAutor());
								ventana.repositorioLabel.setText(repositorio.getNombreRepositorio());
								ventana.ramaLabel.setText("Branch : " + repositorio.getBranch());
							}else {
								MainVista.mostrarMensaje(null, 0);
							}
						}else {
							MainVista.mostrarMensaje(null, 0);
						}
					}else {
						MainVista.mostrarMensaje("No reiniciar repositorio", 0);
					}
					//Desea reincializar todo desede 0 ?
					
				}
//REINIT
				
//ADD---------------------------------------------------------------------------------------------
				if(e.getSource() == Ventana.add) {
					System.out.println("Add\n");
					if (repositorio.workspaceEmpty()) {
						System.out.println("Workspace Empty\n");
						MainVista.mostrarMensaje("Workspace Vacío, cree un archivo", 0);
					}
					else {
						System.out.println("Add opciones\n");
						int seleccion = JOptionPane.showOptionDialog(
								   null,//Componente padre
								   "Seleccione opcion",
								   "Selector de opciones",
								   JOptionPane.DEFAULT_OPTION,
								   JOptionPane.QUESTION_MESSAGE,
								   MainVista.gato,  // null para icono defecto
								   new Object[] { "Add All", "Add 1", "Add Varios" }, 
								   "opcion 1");

						System.out.println("El usuario ha elegido "+seleccion);
						//Git Add All
						if (seleccion==0) {
							System.out.println("Git add All\n");
							repositorio.gitAdd(seleccion,0,null);
						}
						if (seleccion==1) {
							System.out.println("Git add uno\n");
							int archivoIndice = listaDeArchivos();
							int [] lista = {archivoIndice}; 
							repositorio.gitAdd(seleccion,1, lista);	
						}
						if (seleccion==2) {
							System.out.println("Git add varios\n");
							MainVista.mostrarMensaje("Opción no disponible aún",3);
							//Por hacer una lista desplegable
						}
					}		
					Ventana.status.setText(repositorio.indexStatus());
					System.out.println(repositorio.index2String());
				}
//ADD---------------------------------------------------------------------------------------------
				
				
				
//COMMIT-----------------------------------------------------------------------------------------------
				if(e.getSource() == Ventana.commit) {
					System.out.println("Commit\n");
					if (repositorio.getIndex().isEmpty()) {
						System.out.println("No se puede hacer commit, Index vacío");
						MainVista.mostrarMensaje("No se puede hacer commit, Index vacío",0);
					}else if (!repositorio.getLocalRepository().existenCambios(repositorio.getIndex())) {						
						MainVista.mostrarMensaje(
						"El index entregado no posee nuevos cambios con respecto al index anterior",0);
						MainVista.mostrarMensaje("Index Vaciado",1);
						repositorio.limpiarIndex();
					}else {
						//Necesitamos el comentario a poner en el commit y el autor responsable del commit
						String autor = repositorio.getAutor();
						String comentario = null;
						
						//Pedimos el comentario
						comentario = (String)JOptionPane.showInputDialog(
								null,
								"Ingrese el comentario a su commit",
								"Generando Commit",
								JOptionPane.QUESTION_MESSAGE,
								MainVista.gato,
								null,
								"Commit");
						
						//SOlO si el comentario es distinto de null pedimos el autor
						if (comentario!=null) {						
							System.out.println("Comentario : "+ comentario + "\n");
														
							//Preguntamos si quiere cambiar o conservar el autor
							System.out.println("Desea  usar el autor predeterminado o cambiar el nombre del autor\n"
									+ "1.- Autor Predeterminado\n"
									+ "2.- Autor Distinto\n");
							
							int opcion = JOptionPane.showConfirmDialog(
									null,
									"¿Desea cambiar de Autor?",
									"Cambiar de autor",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									MainVista.gato);
							
							//Le pedimso el nombre de su autor
							if (opcion==JOptionPane.YES_OPTION) {
								autor =(String)JOptionPane.showInputDialog(
										null,
										"Ingrese el nombre de su nuevo autor",
										"Nuevo autor",
										JOptionPane.OK_OPTION,
										MainVista.gato,
										null,
										"Archivito.py");
								if (autor==null){
									autor  = "Ryan Gosling";
									JOptionPane.showMessageDialog(
									null,
									"Conservar autor",
									"Mismo autor",
									JOptionPane.PLAIN_MESSAGE,
									MainVista.gato); 
								}
							}
							
							else {
								JOptionPane.showMessageDialog(
								null,
								"Conservar autor",
								"Mismo autor",
								JOptionPane.PLAIN_MESSAGE,
								MainVista.gato); 
							}
							
							
							//PROCEDEMOS A CREAR EL COMMIT
							Boolean confirmacion= repositorio.gitCommit(autor,comentario);
							
							if (confirmacion==true) {
								MainVista.mostrarMensaje("Commit Creado",1);
								MainVista.mostrarMensaje("Index Vaciado",1);
							}else {
								MainVista.mostrarMensaje("No se han encontrado cambios",0);
							}
						}else {
							MainVista.mostrarMensaje("No crear commit",0); 
						}
						
					}
					
				}
//COMMIT-----------------------------------------------------------------------------------------------
//PUSH-------------------------------------------------------------------------------------------------
				if(e.getSource() == Ventana.push) {
					System.out.println("Push\n");
					if (repositorio.getLocalRepository().isEmpty()) {
						System.out.println("Local repositroy vacío, no procede hacer push\n");
						MainVista.mostrarMensaje("Local repositroy vacío, no procede hacer push", 0);
					}
					else if (repositorio.remoteActualizadoBoolean()){
						System.out.println("Remote repositroy se encuentra actualizado, no procede hacer push\n");
						MainVista.mostrarMensaje("Remote repositroy se encuentra actualizado, no procede hacer push",0);
					}
					else {
						System.out.println("Making push ...\n");
						MainVista.mostrarMensaje("Actualizando repositorio remoto", 1);
						repositorio.gitPush();					
					}
				}
//PUSH-------------------------------------------------------------------------------------------------

				
//PULL-------------------------------------------------------------------------------------------------
				
				if(e.getSource() == Ventana.pull) {
					System.out.println("Pull\n");
					if (repositorio.getRemoteRepository().isEmpty()) {
						System.out.println("Remote repository vacío no procede hacer pull\n");
						MainVista.mostrarMensaje("Remote repository vacío no procede hacer pull\n", 1);
					}
					else {
						
						//Solo es posible si el remote se encuentra actualizado
						if (repositorio.getRemoteRepository().getTamano() < repositorio.getLocalRepository().getTamano()) {
							System.out.println(
							"El remote se encuentra desactualizado, ¿quiere proceder a "
							+ "transformar el local "
							+ "y el workspace al ultimo commit en remote repository?\n");
							String respuestaString = MainVista.mostrarMensaje(
							"El remote se encuentra desactualizado, ¿quiere proceder a "
							+ "transformar el local "
							+ "y el workspace al ultimo commit en remote repository?\n"
							, 4);
							System.out.println("Respuesta : " + respuestaString);
							if (respuestaString.equals("0") ) {
								MainVista.mostrarMensaje("Making Push",1);
								repositorio.gitPull();
							}else {
								System.out.println("No hacer Pull\n");
								MainVista.mostrarMensaje("No hacer pull",0);
							}
						}
						else if (repositorio.getRemoteRepository().getTamano()==repositorio.getLocalRepository().getTamano()){
							System.out.println(
							"El remote se encuentra actualizado, "
							+ "¿quiere proceder a redifinir el workspace ?\n");
							String respuestaString = MainVista.mostrarMensaje(
							"El remote se encuentra actualizado, ¿quiere proceder a redifinir el workspace ?\n"
									, 4);
							System.out.println("Respuesta : "+respuestaString);
							if (respuestaString.equals("0") ) {
								MainVista.mostrarMensaje("Making Push",1);
								repositorio.gitPull();
							}else {
								System.out.println("No hacer Pull\n");
								MainVista.mostrarMensaje("No hacer pull",0);
							}
						}		
					}
					Ventana.status.setText("Arhivos actuales en el workspace : \n\n" +repositorio.workspaceStatus());
				}
				
				
//PULL-------------------------------------------------------------------------------------------------
//LOG--------------------------------------------------------------------------------------------------
				//Mostrar los ultimos 5 commits
				
				if(e.getSource() == Ventana.log) {
					System.out.println("Log\n");
					
					//Mostrar una opción log remote o local
					
					String[] opciones = {"Log Local","Log Remoto"};
					Object seleccion = JOptionPane.showInputDialog(
							   null,//unComponentePadre,
							   "Seleccione opcion",
							   "Selector de opciones",
							   JOptionPane.QUESTION_MESSAGE,
							   MainVista.gato,  // null para icono defecto
							   opciones, 
							   null);
					System.out.println("El usuario eligió : " + seleccion);
					if (seleccion!=null) {
						if (seleccion.equals("Log Local")) {
							int i;
							if (repositorio.getLocalRepository().getTamano() <=5) {
								i =repositorio.getLocalRepository().getTamano() ;
							}else {
								i = 5;
							}
							
							
							if (repositorio.getLocalRepository().getTamano()!=0) {
								Ventana.status.setText("Los "+ i + " commits de local repository son"+repositorio.gitLog());
							}else {
								Ventana.status.setText("Local repository vacío :(");
							}
						}else if (seleccion.equals("Log Remoto")) {
							int i;							
							if (repositorio.getRemoteRepository().getTamano() <=5) {
								i =repositorio.getRemoteRepository().getTamano() ;
							}else {
								i = 5;
							}
							
							
							if (repositorio.getRemoteRepository().getTamano() !=0) {
								Ventana.status.setText("Los "+ i + " commits de remote repository son" + repositorio.gitLogRemote());
							}else {
								Ventana.status.setText("Remote repository vacío :(");
							}
						}
					}else {
						MainVista.mostrarMensaje("Error",0);
					}
				}
//LOG-------------------------------------------------------------------------------------------------
//BRANCH----------------------------------------------------------------------------------------------
				if(e.getSource() == Ventana.branch) {
					System.out.println("Branch\n");
					System.out.println("¿Cuál es el nombre de su rama nueva a crear a partir de master ? \n");
					String ramaString = MainVista.mostrarMensaje(
							"Ingrese su nueva rama a crear",
							5);
					if (ramaString!=null) {
						if (ramaString.equals("")){
							ramaString = "Mi Rama";
						}
						//Si el nombre nuevo no se encuentra ya dentro de una de las ramas
						if (!branches.isInside(ramaString)) {	
							String autorString =MainVista.mostrarMensaje("¿Desea cambiar de autor?", 4);
							System.out.println("Respuesta : " + autorString);
							if (autorString.equals("0")) {
								autorString = MainVista.mostrarMensaje("Ingrese su autor","Nuevo autor",2);
								if (autorString == null) {
									MainVista.mostrarMensaje("Se arrepintió de cambiar el autor?", 0);
									autorString = "Ryan Gosling";
								}else if (autorString.equals("")) {
									autorString = "Ryan Gosling";
								}else {
									System.out.println("Nuevo autor : " +autorString);
								}
							}else {
								autorString = repositorio.getAutor();
							}
							System.out.println("\nEl autor de la rama es : " + autorString+"\n");
							repositorio = branches.gitBranch(ramaString,autorString);
							MainVista.mostrarMensaje("Nueva rama creada con exito", 1);
							
							Ventana.status.setText(
									"\nAutor : " + repositorio.getAutor()+
									"\nRepo  : " + repositorio.getNombreRepositorio()+
									"\nRama  : " + repositorio.getBranch()+
									"\n\nTotal de ramas : \n" + branches.branches2String());
							ventana.autorLabel.setText(repositorio.getAutor());
							ventana.ramaLabel.setText(repositorio.getBranch());
						}else {
							MainVista.mostrarMensaje("Ya existe una rama con ese nombre", 0);
						}
					}else {
						MainVista.mostrarMensaje("No crear rama", 0);
					}
				}
//BRANCH-----------------------------------------------------------------------------------------------
//CHECKOUT---------------------------------------------------------------------------------------------
				if(e.getSource() == Ventana.checkout) {
					System.out.println("Chekcout\n");
					if (branches.getTamano()!=1) {
						int branch =  listaDeRamas();
						if (branch==-1 || branch > branches.getTamano() ) {
							MainVista.mostrarMensaje("No check out",0);
						}else {
							repositorio = branches.gitCheckOut(branch);
							System.out.println("Cambiado a : " + repositorio.getBranch() );
							MainVista.mostrarMensaje("Ahora se encuentra en : "+ repositorio.getBranch(),1);
							Ventana.status.setText("Rama  : " + repositorio.getBranch());
						}
					}else {
						MainVista.mostrarMensaje("Solo hay una rama, no es posible hace el checkout",0);
					}

				}
				
//CHECKOUT----------------------------------------------------------------------------------------------
				
				
				
				
				//Status de zonas de trabajo
				
				
				
				
//WORKSPACE STATUS-----------------------------------------------------------------------------------------
				if(e.getSource() == Ventana.workspace) {
					System.out.println("Workspace Status\n");
					System.out.println(repositorio.workspaceStatus());
					System.out.println(repositorio.workspace2String());
					System.out.println(repositorio.getWorkspace().workspace2String());
					Ventana.status.setText("Arhivos actuales en el workspace : \n\n"+repositorio.workspaceStatus());
				}

//INDEX STATUS-----------------------------------------------------------------------------------------				
				if(e.getSource() == Ventana.index) {
					System.out.println("Index Status\n");
					System.out.println(repositorio.indexStatus());
					System.out.println(repositorio.index2String());
					System.out.println(repositorio.getIndex().archivos2String());
					Ventana.status.setText("Arhivos actuales  en el index : \n\n" +repositorio.indexStatus());
				}

//LOCAL REPOSITORY STATUS-----------------------------------------------------------------------------------------
				//Mostrar los últimos 3 commits"Arhivos actuales en el workspace : \n"
				if(e.getSource() == Ventana.localRepository) {
					System.out.println("Local Repository Status\n");
					//Ventana.status.setText(repositorio.gitLog());
					int i;
					if (repositorio.getLocalRepository().getTamano() <=3) {
						i =repositorio.getLocalRepository().getTamano() ;
					}else {
						i = 3;
					}
					Ventana.status.setText("Los ultimos "+i+" Commits en local repository son : \n\n" + repositorio.getLocalRepository().repositoryStatus());
				}

//REMOTE REPOSITORY STATUS-----------------------------------------------------------------------------------------
				//Mostrar los últimos 3 commits
				if(e.getSource() == Ventana.remoteRepository) {
					System.out.println("Remote Repository Status\n");
					int i;
					if (repositorio.getRemoteRepository().getTamano() <=3) {
						i =repositorio.getRemoteRepository().getTamano() ;
					}else {
						i = 3;
					}
					Ventana.status.setText("Los ultimos "+i+" Commits en remote repository son : \n\n" + repositorio.getRemoteRepository().repositoryStatus());
				}
				
		
				
				
				
				
				
				//Sección Editor y creador de archivo
				
				
				
				
				
//CREAR ARCHIVO ------------------------------------------------------------------------------------------
				if(e.getSource() == Ventana.nuevoArchivo) {
					System.out.println("NuevoArchivo\n");
					String nombreArchivo =null;
					nombreArchivo = MainVista.mostrarMensaje(
							"Ingrese el nombre de su archivo",
							"Creando Archivo",2);

					
					if (nombreArchivo == null){
						System.out.println("No crear Archivo\n");
						JOptionPane.showMessageDialog(null,"No crear archivo","Bad ending",JOptionPane.PLAIN_MESSAGE,MainVista.error); 
					}else {
						
						Boolean estaDentroBoolean = false;
						System.out.println("Nombre de archivo : "+nombreArchivo);
						estaDentroBoolean =	repositorio.crearArchivo(nombreArchivo);
					
						if (nombreArchivo.equals("")) {
							nombreArchivo = "NuevoArchivo.c";
						}
						
						//Si ya hay un archivo con ese nombre se lo indicamos
						if (estaDentroBoolean == false ) {
							JOptionPane.showMessageDialog(null,"Ya existe un archivo con ese nombre","Bad Ending",JOptionPane.PLAIN_MESSAGE,MainVista.error);
						}
						else {
							//Abrimos un dialogo de iniciar la edición o no
							String editarContenidoString = MainVista.mostrarMensaje("¿Desea editar el archivo creado?", 4);
							System.out.println("Respuesta : " + editarContenidoString);		
							int editarContenido = Integer.valueOf(editarContenidoString);
							if (editarContenido == JOptionPane.YES_OPTION) {
								Editor editorTexto = new Editor(nombreArchivo, "");
								controlDeEditor(editorTexto, repositorio.getNWorkspace()-1);
							}
						}
						
					}
					Ventana.status.setText(repositorio.workspaceStatus());
					System.out.println(repositorio.workspace2String());
				}

//CREAR ARCHIVO ------------------------------------------------------------------------------------------

//EDITAR ARCHIVO------------------------------------------------------------------------------------------
				if(e.getSource() == Ventana.editarArchivo) {
					//Solo si el workspace no se encuentra vacío
					if (repositorio.getWorkspace().isEmpty()) {
						MainVista.mostrarMensaje("Workspace vacío, ¿qué archivo se puede editar?....", 0);
					}else {
						int indice = listaDeArchivos();
						if (indice != -1) {
						String nombre = repositorio.getWorkspace().getArchivoN(indice).getNombre() ;
						String contenido = repositorio.getWorkspace().getArchivoN(indice).getContenidoString();
						Editor editorTexto = new Editor(nombre, contenido);
						controlDeEditor(editorTexto, indice);
						}else {
							MainVista.mostrarMensaje("No editar archivo", 0);
						}
					}
				}
//EDITAR ARCHIVO------------------------------------------------------------------------------------------

//VER CONTENIDO ------------------------------------------------------------------------------------------					
				if(e.getSource() == Ventana.verContenido) {
					System.out.println(repositorio.workspace2String());
					Ventana.status.setText(repositorio.workspace2String());	
				}
//VER CONTENIDO ------------------------------------------------------------------------------------------
			}
		};

//CONTROLADOR------------------------------------------------------------------------------------------
		
		//BOTONES de ventana
		//Primera Fila
		Ventana.reInit.addActionListener( control);
		Ventana.add.addActionListener( control);
		Ventana.commit.addActionListener( control);
		Ventana.push.addActionListener( control);
		//Segunda Fila
		Ventana.pull.addActionListener( control);
		Ventana.log.addActionListener( control);
		Ventana.branch.addActionListener( control);
		Ventana.checkout.addActionListener( control);
		
		//Status
		Ventana.workspace.addActionListener( control);
		Ventana.index.addActionListener( control);
		Ventana.localRepository.addActionListener( control);
		Ventana.remoteRepository.addActionListener( control);
		
		//Edit Archivos
		Ventana.nuevoArchivo.addActionListener(control);
		Ventana.editarArchivo.addActionListener(control);
		Ventana.verContenido.addActionListener(control);
		
	}
	
	public static void controlDeEditor(Editor editorTexto,int indiceArchivo) {
		ActionListener control = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == editorTexto.guardar) {
					//Editamos el archivo
					repositorio.editarArchivo(indiceArchivo,editorTexto.getContenido());
					System.out.println("Contenido : " + editorTexto.getContenido() + "\n");
					Ventana.status.setText( editorTexto.getContenido());	
				}
			}
		};
		editorTexto.guardar.addActionListener(control);
	}
	
	public static int listaDeArchivos() {
		String[] array = repositorio.getWorkspace().getArregloNombre();

		Object seleccion = JOptionPane.showInputDialog(
		   null,//unComponentePadre,
		   "Seleccione opcion",
		   "Selector de opciones",
		   JOptionPane.QUESTION_MESSAGE,
		   MainVista.gato,  // null para icono defecto
		   array, 
		   null);
		int indice = Arrays.asList(array).indexOf(seleccion);
		System.out.println("El usuario ha elegido "+seleccion + " Indice " + indice + "\n");
		return indice;	
	}
	
	public static int listaDeRamas() {
		String[] array = branches.branches2ArrayString();

		Object seleccion = JOptionPane.showInputDialog(
		   null,//unComponentePadre,
		   "Seleccione opcion",
		   "Selector de opciones",
		   JOptionPane.QUESTION_MESSAGE,
		   MainVista.gato,  // null para icono defecto
		   array, 
		   null);
		int indice = Arrays.asList(array).indexOf(seleccion);
		System.out.println("El usuario ha elegido "+seleccion + " Indice " + indice + "\n");
		return indice;	
	}
	

	}