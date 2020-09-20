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

public class MainController {

	//Atributos
	private static Ventana ventana = new Ventana();
	private static MiRepositorio repositorio = new MiRepositorio();
	private static Branches branches ;
	//private static ActionListener control;

	public static void main(String[] args) {
		init();
	}
	
	//Le damos la función de tener un action listener1
	public static void init() {
	    ActionListener ActionListener1 = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Si los valores son strings correctos
				if (ventana.repositorio.getText().equals("") && ventana.autor.getText().equals("")) {
					//Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon)
					int reply = JOptionPane.showConfirmDialog(null, "Desea continuar con valor predeterminados?","Opciones", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,MainVista.gato);
					if (reply == JOptionPane.YES_OPTION) {
					    gitInit();
					} else {
						mostrarMensaje("Ingrese sus valores", 0);
					}
				}
				else {
					gitInit();
				}
			}
	    };
		ventana.iniciar.addActionListener(ActionListener1);
	}
	
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
	
	public static void controlDeComandos() {
		ActionListener control = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Comandos
//REINIT
				if (e.getSource()==Ventana.reInit) {
					int opcion = JOptionPane.showConfirmDialog(
							null,
							"¿Desea reiniciar el repositorio?",
							"Cambiar de autor",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							MainVista.gato);
					if (opcion== 0 ) {
						System.out.println("Opción : "+ opcion);
						String autorString = (String)JOptionPane.showInputDialog(
						null,
						"Ingrese su nuevo autor",
						"Nuevo autor",
						JOptionPane.QUESTION_MESSAGE,
						MainVista.gato,
						null,
						"Commit");
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
								mostrarMensaje(null, 0);
							}
						}else {
							mostrarMensaje(null, 0);
						}
					}else {
						mostrarMensaje("No reiniciar repositorio", 0);
					}
					//Desea reincializar todo desede 0 ?
					
				}
//REINIT
				
//ADD---------------------------------------------------------------------------------------------
				if(e.getSource() == Ventana.add) {
					System.out.println("Add\n");
					if (repositorio.workspaceEmpty()) {
						System.out.println("Workspace Empty\n");
						mostrarMensaje("Workspace Vacío, cree un archivo", 0);
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
							mostrarMensaje("Opción no disponible aún",3);
							//Por hacer una lista desplegable
						}
					}			
				}
//ADD---------------------------------------------------------------------------------------------
				
				
				
//COMMIT-----------------------------------------------------------------------------------------------
				if(e.getSource() == Ventana.commit) {
					System.out.println("Commit\n");
					if (repositorio.getIndex().isEmpty()) {
						System.out.println("No se puede hacer commit, Index vacío");
						mostrarMensaje("No se puede hacer commit, Index vacío",0);
					}
					else if (!repositorio.getLocalRepository().isEmpty() && repositorio.getLocalRepository().existenCambios(repositorio.getIndex())) {
						mostrarMensaje("El index entregado no posee nuevos cambios con respecto al index anterior",0);
					}else {
						//Necesitamos el comentario a poner en el commit y el autor
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
						
						//SOlO si el comentario es distinto de null
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
						
						
						if (opcion==JOptionPane.YES_OPTION) {
							autor =(String)JOptionPane.showInputDialog(
									null,
									"Ingrese el nombre de su archivo",
									"Creando Archivo",
									JOptionPane.OK_OPTION,
									MainVista.gato,
									null,
									"Archivito.py");
							if (autor==null){
								autor  = "Ryan Gosling";
								JOptionPane.showMessageDialog(null,"Conservar autor","Mismo autor",JOptionPane.PLAIN_MESSAGE,MainVista.gato); 
							}
						}
						
						else if(opcion == JOptionPane.NO_OPTION || opcion== JOptionPane.CLOSED_OPTION) {
							JOptionPane.showMessageDialog(null,"Conservar autor","Mismo autor",JOptionPane.PLAIN_MESSAGE,MainVista.gato); 
						}
						
						
						//PROCEDEMOS A CREAR EL COMMIT
						Boolean confirmacion= repositorio.gitCommit(autor,comentario);
						if (confirmacion==true) {
							mostrarMensaje("Commit Creado",1);
						}else {
							mostrarMensaje("Ha ocurrido un error",0);
						}
						}else {
							mostrarMensaje("No crear commit",0); 
						}
						
					}
					
				}
//COMMIT-----------------------------------------------------------------------------------------------
//PUSH-------------------------------------------------------------------------------------------------
				if(e.getSource() == Ventana.push) {
					System.out.println("Push\n");
					if (repositorio.getLocalRepository().isEmpty()) {
						System.out.println("Local repositroy vacío, no procede hacer push\n");
						mostrarMensaje("Local repositroy vacío, no procede hacer push", 0);
					}
					else if (repositorio.remoteActualizadoBoolean()){
						System.out.println("Remote repositroy se encuentra actualizado, no procede hacer push\n");
						mostrarMensaje("Remote repositroy se encuentra actualizado, no procede hacer push",0);
					}
					else {
						System.out.println("Making push ...\n");
						mostrarMensaje("Actualizando repositorio remoto", 1);
						
					}
				}
//PUSH-------------------------------------------------------------------------------------------------

				
//PULL-------------------------------------------------------------------------------------------------
				
				if(e.getSource() == Ventana.pull) {
					System.out.println("Pull\n");
					mostrarMensaje(null,3);
				}
				
				
//PULL-------------------------------------------------------------------------------------------------
				if(e.getSource() == Ventana.log) {
					System.out.println("Log\n");
					mostrarMensaje(null,3);
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
							Ventana.status.setText(repositorio.gitLog());
						}else if (seleccion.equals("Log Remoto")) {
							Ventana.status.setText(repositorio.gitLogRemote());
						}
					}else {
						mostrarMensaje("Error",0);
					}
					
					
				}
				if(e.getSource() == Ventana.branch) {
					System.out.println("Branch\n");
					JOptionPane.showMessageDialog(null, "Add aún no disponible");
				}
				if(e.getSource() == Ventana.checkout) {
					System.out.println("Chekcout\n");
					JOptionPane.showMessageDialog(null, "Add aún no disponible");
				}
				
				
				
				
				//Status de zonas de trabajo
				
//WORKSPACE STATUS-----------------------------------------------------------------------------------------
				if(e.getSource() == Ventana.workspace) {
					System.out.println("Workspace Status\n");
					Ventana.status.setText(repositorio.workspaceStatus());
				}

//INDEX STATUS-----------------------------------------------------------------------------------------				
				if(e.getSource() == Ventana.index) {
					System.out.println("Index Status\n");
					Ventana.status.setText(repositorio.indexStatus());
				}

//LOCAL REPOSITORY STATUS-----------------------------------------------------------------------------------------
				if(e.getSource() == Ventana.localRepository) {
					System.out.println("Local Repository Status\n");
					//Ventana.status.setText(repositorio.gitLog());
					Ventana.status.setText(repositorio.repositorioLocal2String());
				}

//REMOTE REPOSITORY STATUS-----------------------------------------------------------------------------------------
				if(e.getSource() == Ventana.remoteRepository) {
					System.out.println("Remote Repository Status\n");
					//Ventana.status.setText(repositorio.gitLogRemote());
					Ventana.status.setText(repositorio.repositorioRemoto2String());
				}
				
				
//Sección Editor y creador de archivo
				
//CREAR ARCHIVO ------------------------------------------------------------------------------------------
				if(e.getSource() == Ventana.nuevoArchivo) {
					System.out.println("NuevoArchivo\n");
					String nombreArchivo =null;
					nombreArchivo =(String)JOptionPane.showInputDialog(
							null,
							"Ingrese el nombre de su archivo",
							"Creando Archivo",
							JOptionPane.OK_OPTION,
							MainVista.gato,
							null,
							"Archivito.py");
					
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
							int editarContenido = JOptionPane.showConfirmDialog(
									null,
									"¿Desea editar el archivo creado?", 
									"Edición de Archivo", 
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									MainVista.gato);
							if (editarContenido == JOptionPane.YES_OPTION) {
								Editor editorTexto = new Editor(nombreArchivo, "");
								controlDeEditor(editorTexto, repositorio.getNWorkspace()-1);
							}
						}
						
					}
				}

//CREAR ARCHIVO ------------------------------------------------------------------------------------------

//EDITAR ARCHIVO------------------------------------------------------------------------------------------
				if(e.getSource() == Ventana.editarArchivo) {
					//Solo si el workspace no se encuentra vacío
					if (repositorio.getWorkspace().isEmpty()) {
						JOptionPane.showMessageDialog(
								null,
								"Workspace vacío, ¿qué archivo se puede editar?....",
								"Bad Ending",
								JOptionPane.PLAIN_MESSAGE,
								MainVista.error);
					}else {
						int indice = listaDeArchivos();
						if (indice != -1) {
						String nombre = repositorio.getWorkspace().getArchivoN(indice).getNombre() ;
						String contenido = repositorio.getWorkspace().getArchivoN(indice).getContenidoString();
						Editor editorTexto = new Editor(nombre, contenido);
						controlDeEditor(editorTexto, indice);
						}else {
							JOptionPane.showMessageDialog(
									null,
									"No editar Archivo",
									"Bad Ending",
									JOptionPane.PLAIN_MESSAGE,
									MainVista.error);
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
		   repositorio.getWorkspace().getArregloNombre(), 
		   null);
		int indice = Arrays.asList(array).indexOf(seleccion);
		System.out.println("El usuario ha elegido "+seleccion + " Indice " + indice + "\n");
		return indice;	
	}
	
	/**
	 * Metodo para mostrar mensaje si es de error o de bueno
	 * @param Mensaje
	 * @param tipo
	 * Tipo = 0 ERROR ; tipo = 1 OK
	 */
	public static void mostrarMensaje(String mensaje,int tipo) {
		if (tipo == 0) {
			JOptionPane.showMessageDialog(null,mensaje,"Bad Ending",JOptionPane.PLAIN_MESSAGE,MainVista.error);
		}else if (tipo==1) {
			JOptionPane.showMessageDialog(
					null,
					mensaje,
					"Successful",
					JOptionPane.PLAIN_MESSAGE,
					MainVista.gato);
		}else {
			JOptionPane.showMessageDialog(
					null,"Opción no disponible aún",
					"Bad Ending",
					JOptionPane.PLAIN_MESSAGE,
					MainVista.error);
		}

	}
		
	}
	
	
	
    
	

