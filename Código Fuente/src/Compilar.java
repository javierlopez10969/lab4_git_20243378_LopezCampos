import java.io.IOException;

public class Compilar {
	public static void main(String[] args) {
		String compilar = "javac ./modelo/Menu.java";
		String compilar2 =  "javac ./modelo/ListaDeArchivos.java ./modelo/Archivo.java ./modelo/MiArchivo.java ./modelo/MiIndex.java ./modelo/MiRepositorio.java ./modelo/MiWorkspace.java ./modelo/Tiempo.java ./modelo/Branches.java ./vista/Editor.java ./vista/MainVista.java ./vista/PlaceHolder.java ./vista/Ventana.java ./control/MainController.java";
		String sSistemaOperativo = System.getProperty("os.name");
		System.out.println("Su sistema operativo es : "+sSistemaOperativo);
        String[] arrOfStr = sSistemaOperativo.split(" ", 0);
        System.out.println("System Real : " + arrOfStr[0]+ "\n");
		switch (arrOfStr[0]) {
		case "Linux":
			try {
				System.out.println("Compilando ..");
				Runtime.getRuntime().exec(compilar); 
				Runtime.getRuntime().exec(compilar2); 
				System.out.println("Programa Compilado");
			} catch (IOException ioe) {
				System.out.println ("Algo salio mal :C" + ioe);
			}
			break;
		case "Windows":
			try {
				//String [] cmd = {"shutdown","-s","-t", "10"}; //Comando de apagado en windows
				System.out.println("Compilando ..");
				Runtime.getRuntime().exec(compilar);
				Runtime.getRuntime().exec(compilar2); 
				System.out.println("Programa Compilado");
			} catch (IOException ioe) {
				System.out.println ("Algo salio mal :C" + ioe);
			}
			break;

		default:
			try {
				//String [] cmd = {"shutdown","-s","-t", "10"}; //Comando de apagado en windows
				System.out.println("Compilando ..");
				Runtime.getRuntime().exec(compilar);
				Runtime.getRuntime().exec(compilar2); 
				System.out.println("Programa Compilado");
			} catch (IOException ioe) {
				System.out.println ("Algo salio mal :C" + ioe);
			}
			break;
		}

	}
}
