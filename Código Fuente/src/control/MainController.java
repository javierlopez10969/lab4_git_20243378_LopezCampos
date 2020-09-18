package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Branches;
import modelo.MiRepositorio;
import vista.Ventana;

public class MainController {
	//Atributos
	private static Ventana ventana = new Ventana();
	private static MiRepositorio repositorio = new MiRepositorio();
	private static Branches branches ;

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
					int reply = JOptionPane.showConfirmDialog(null, "Desea continuar con valor predeterminados?", "", JOptionPane.YES_NO_OPTION);
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
		System.out.println("Su autor es : " + autorInit + "\nSu repositorio es "+repositorioInit+"\n");
		ventana.panel.setVisible(false);
		if (autorInit.equals("")) {autorInit = "Samuel L Jackson";}
		if (repositorioInit.equals("")) {repositorioInit = "Mi Repositorio";}
		//Inicializamos el repostorio
		repositorio.gitInit(autorInit, repositorioInit);
		ventana.autorLabel.setText(autorInit);
		ventana.repositorioLabel.setText(repositorioInit);
		branches = new Branches(repositorio);
		ventana.iniciarPanel2();
		
	}
	
    
	
}
