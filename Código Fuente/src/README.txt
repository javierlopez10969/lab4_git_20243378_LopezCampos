Abra la terminal de su correspondiente sistema operativo en esta carpeta llamada src.
Asegurese que la consola esta abierta en esta carpeta llamada src.
Hay distintos metodos para abrir la consola desde una determinada carpeta.A ejemplo le dejamos esta.
En linux tanto en windows puede apretar click derecho en un espacio en blanco de la carpeta y seleccionar abrir Terminal.
Si tiene problemas para abir la temrinal, busque un tutrorial en internet "Como abrir terminal desde una carpeta"-

https://www.solvetic.com/tutoriales/article/6464-como-abrir-terminal-con-una-carpeta-especifica-en-ubuntu/

https://answers.microsoft.com/es-es/windows/forum/all/c%C3%B3mo-abrir-cmd-desde-la-carpeta-actual/d924684f-d486-4382-b1f3-2b631b02caad


Asegurese de tener instalado java en su versión mas reciente. Y de ejecutar todos los comandos desde la carpeta src.

Una vez abierta la consola ubicada en esta carpeta ejecute el siguiente comando :
Comando 1:   


javac Compilar.java


Despúes de haber compilado este programa, ejecute el programa : 
Comando 2:
 
 
java Compilar


Y ahora ejecute el programa principal : 
Comando3:

java modelo.Menu


Caso de error :

En caso de que la compilación no se ejecutará correctamente ingrese los siguientes comandos.

Comando 1 :

javac ./utils/Contenido.java ./utils/ListaDeArchivos.java ./modelo/Menu.java ./modelo/Archivo.java ./modelo/MiArchivo.java ./modelo/MiIndex.java ./modelo/MiRepositorio.java ./modelo/MiWorkspace.java ./modelo/Tiempo.java ./modelo/Branches.java
	
Comando 1 alternativo :

javac ./modelo/Menu.java

Comando 2 ejecutar programa : 

java modelo.Menu


