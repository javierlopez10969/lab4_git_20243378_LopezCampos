package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import modelo.MiRepositorio;
import vista.Ventana;

public class MainController {
	public static Ventana ventana = new Ventana();
	public static MiRepositorio repositorio;

	public static void main(String[] args) {
		init();
	}
	
	//Le damos la función de tener un action listener1
	public static void init() {
	    ActionListener ActionListener1 = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String autorInit = ventana.autor.getText();
				String repositorioStringInit = ventana.repositorio.getText();
				System.out.println("Su autor es : " + autorInit + "\nSu repositorio es "+repositorioStringInit+"\n");
				ventana.panel.setVisible(false);;
				ventana.iniciarPanel2();
			}
	    };
		ventana.iniciar.addActionListener(ActionListener1);
	}
	
    
	
}
