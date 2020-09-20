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
					    JOptionPane.showMessageDialog(null, "Ingrese sus valores");
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
				
				
//ADD---------------------------------------------------------------------------------------------
				if(e.getSource() == Ventana.add) {
					System.out.println("Add\n");
					if (repositorio.workspaceEmpty()) {
						System.out.println("Workspace Empty\n");
						JOptionPane.showMessageDialog(null, "Workspace Vacío, cree un archivo");
					}
					else {
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
							JOptionPane.showMessageDialog(null, "Opción no disponible aún");	
							//Por hacer una lista desplegable
						}
					}			
				}
//ADD---------------------------------------------------------------------------------------------
				if(e.getSource() == Ventana.commit) {
					System.out.println("Commit\n");
					JOptionPane.showMessageDialog(null, "Commit aún no disponible");
				}
				if(e.getSource() == Ventana.push) {
					System.out.println("Push\n");
					JOptionPane.showMessageDialog(null, "Push aún no disponible");
				}
				if(e.getSource() == Ventana.pull) {
					System.out.println("Pull\n");
					JOptionPane.showMessageDialog(null, "Add aún no disponible");
				}
				if(e.getSource() == Ventana.log) {
					System.out.println("Log\n");
					JOptionPane.showMessageDialog(null, "Add aún no disponible");
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
				if(e.getSource() == Ventana.workspace) {
					System.out.println("Workspace Status\n");
					Ventana.status.setText(repositorio.workspaceStatus());
				}
				if(e.getSource() == Ventana.index) {
					System.out.println("Index Status\n");
					Ventana.status.setText(repositorio.indexStatus());
				}
				if(e.getSource() == Ventana.localRepository) {
					System.out.println("Local Repository Status\n");
					JOptionPane.showMessageDialog(null, "Add aún no disponible");
				}
				if(e.getSource() == Ventana.remoteRepository) {
					System.out.println("Remote Repository Status\n");
					JOptionPane.showMessageDialog(null, "Add aún no disponible");
				}
				
				//Editor de contenido y archivo
				
				//Crear Archivo
				if(e.getSource() == Ventana.nuevoArchivo) {
					System.out.println("NuevoArchivo\n");
					String nombreArchivo =JOptionPane.showInputDialog(null,"Ingrese el nombre de su archivo");
					Boolean estaDentroBoolean = false;
					
					if (nombreArchivo.equals(null) || nombreArchivo == null )
					//		|| nombreArchivo == JOptionPane.CANCEL_OPTION) 
					{
						System.out.println("No crear Archivo\n");
					}else {
						System.out.println("Nombre de archivo : "+nombreArchivo);
						estaDentroBoolean =	repositorio.crearArchivo(nombreArchivo);
					}

					if (nombreArchivo.equals("")) {
						nombreArchivo = "NuevoArchivo.c";
					}
					
					//Si ya hay un archivo con ese nombre se lo indicamos
					if (estaDentroBoolean == false  || nombreArchivo.equals(null)) {
						JOptionPane.showMessageDialog(null, "Ya existe un archivo con ese nombre");
					}
					else {
						//Abrimos un dialogo de iniciar la edición o no
						int editarContenido = JOptionPane.showConfirmDialog(null, "Desea editar el archivo creado?", "", JOptionPane.YES_NO_OPTION);
						if (editarContenido == JOptionPane.YES_OPTION) {
							Editor editorTexto = new Editor(nombreArchivo, "");
							controlDeEditor(editorTexto, repositorio.getNWorkspace()-1);
							
						}
					}
					//Ventana.status.setText(repositorio.gitStatus());
				}
				
				if(e.getSource() == Ventana.editarArchivo) {
					Ventana.status.setText(repositorio.workspaceStatus());	
					
					
				}
				
				if(e.getSource() == Ventana.verContenido) {
					System.out.println(repositorio.workspace2String());
					Ventana.status.setText(repositorio.workspace2String());	
				}
				
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
		// Con JCombobox
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
	
	
	
    
	
}
