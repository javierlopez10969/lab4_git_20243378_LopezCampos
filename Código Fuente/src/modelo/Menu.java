package modelo;

import java.util.Scanner;

/**
 * Una clase para mostrar y recibir los distintos comandos
 * de la clase repositorio, y que estos interactuen con el usuario
 * El controlador de eventos, efectivamente lo controla la clase MiRepositorio
 * @version 1.0, 31/08/2020
 * @author Javier López
 */
public class Menu {
    /** 
     * Mostrar los distintos comandos de la simulación de git
     * @param void
     */
	public static void main(String[]args){
		MiRepositorio repositorio = new MiRepositorio(); //Declaramos el repositorio	
		Scanner entradaEscaner = new Scanner (System.in);
		int x = -1;//La entrada la incicializamos en -1
		System.out.println("Bienvenido a la simulacion de git\n"
		+"Para inicializar su repositorio, ingrese su nombre de usuario");
		String autor = entradaEscaner.nextLine();
		System.out.println("Ahora ingrese el nombre de su repositorio");
		String nombreRepositorio = entradaEscaner.nextLine();
		if (autor.equals("")|| autor.equals("\n")) {autor = "Samuel L Jackson";}
		if (nombreRepositorio.equals("")|| nombreRepositorio.equals("\n")) {nombreRepositorio = "Mi Repositorio";}
		//Inicializamos el repostorio
		repositorio.gitInit(autor, nombreRepositorio);
		//Ahora inicializamos las ramas , con el master actual
		Branches branches = new Branches(repositorio);
        while(x != 15){
        	try{
        	entradaEscaner = new Scanner (System.in);
            System.out.println("### SIMULACIÓN DE GIT ###\n"
            //+  "Autor : " + repositorio.getAutor()
            //+ "\nNombre Repositorio : "+ repositorio.getNombreRepositorio()
            //+ "\nFecha de creación: " + repositorio.getFechaDeCreacion()
            + "\nEscoja su opción:\n"
            + "1.  add\n"
            + "2.  commit\n"
            + "3.  pull\n"
            + "4.  push\n"
            + "5.  status\n"
            + "6.  Crear nuevo archivo\n"
            + "7.  Ver Workspace\n"
            + "8.  Editar Archivo\n"
            + "9.  Borrar Archivo\n"
            + "10. Ver Archivos en el index\n"
            + "11. log\n"
            + "12. gitBranch\n"
            + "13. gitCheckout\n"
            + "15. Salir\n"  
            +"INTRODUZCA SU OPCIÓN:"
            + " _\n");
            x = entradaEscaner.nextInt();
            //System.out.println ("Entrada recibida por teclado es: \"" + x +"\n");
            switch(x){
                //Commit
                case 2:{
                    System.out.println("Commit\n");
                    //repositorio.gitCommit();
                    //System.out.println("Index vaciado\n");
                    //Thread.sleep(2000);
                    break;
                }
                //pull
                case 3:{
                    System.out.println("Pull\n");
                    repositorio.gitPull();
                    break;
                }
                //push
                case 4:{
                    System.out.println("Push\n");
                    repositorio.gitPush();
                    //Solo si no se encuentra actualizado hacemos como que esperamos el push
                    if (!repositorio.remoteActualizadoBoolean()) {
                    	Thread.sleep(2000);
                    	System.out.println("Commits actualizados\n");
					}
                                     
                    break;
                }
                //Satus
                case 5:{
                    System.out.println("Status\n");
                    System.out.println(repositorio.gitStatus()); 
                    //Thread.sleep(4000);
                    break;
                }
                //Crear archivo
                case 6:{
                	System.out.println("Crear archivo\n");
                	System.out.println("Ingrese el nombre de su archivo a crear : ");
                    //repositorio.crearArchivo(String nombreArchivo);
                    break;
                }
                //Mostrar el workspace actual con todo
                case 7:{
                	System.out.println("Workspace actualmente\n");
                	System.out.println(repositorio.workspace2String());
                	//Solo si el workspace no esta vacío esperamos 4 segundos para que el usuario observe el workspace
                	if (!repositorio.workspaceEmpty()) {
                		Thread.sleep(4000);
					}                	
                	break;
                }
                //Editar archivo
                case 8:{
                	System.out.println("Editar Archivo\n");
                	break;
                }
                //Borrar archivo
                case 9:{
                	System.out.println("Borrar Archivo\n");
                	repositorio.borrarArchivo();
                	break;
                }
                //Ver index
                case 10:{
                	System.out.println("Ver Index\n");
                	System.out.println(repositorio.index2String());
                	if (!repositorio.getIndex().isEmpty()) {
                		Thread.sleep(4000);
					}
                    break;
                }
                //Log
                case 11:{
                	System.out.println("Log \n");
                	repositorio.gitLog();
                	Thread.sleep(4000);
                	break;
                }
                //Git Branch
                case 12:{
                	System.out.println("Git Branch\n");
                	repositorio = branches.gitBranch();
                	break;
                }
                //Git CheckOut
                case 13:{
                	System.out.println("Git CheckOut\n");
                	repositorio = branches.gitCheckOut();
                	break;
                }
                //Git Merge
                case 14:{
                	System.out.println("Git Merge\n");
                	break;
                }
                //Salir
                case 15:{
                    System.out.println("Adiós\n\n");
                    System.out.println("Simulación de Git terminada .");
                    break;
                } 
                
                

                //Log de remote repository
                case 19:{
                	System.out.println("Función secreta Log de Remote Repository\n");
                	repositorio.gitLogRemote();
                	Thread.sleep(4000);
                	break;
                }

                default :{
                	System.out.println("Ingrese opción válida\n");
        			break;
                }
				
            }
        	}catch (Exception s) {
        		System.out.println("Algo salió mal" + s);
			}
        }
        entradaEscaner.close();

    }
 
}
