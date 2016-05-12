# PROYECTO by IBAI, ANDRÉS, ALEJANDRA & ALFONSO

  A continuación se encuentran una serie de explicaciones que ayudaran a mejorar la movilidad por el repositorio:
    
    - Diagrama de Clases: En esta carpeta se encuentra el diagrama de clases, realizada con el software 'Visual Paradigm'. También se encuentra un pdf para poder visualizar dicho diagrama mucho más fácil.
    
    - Mer-Relacional: En esta carpeta se encuentran los modelos MER y Relacional, realizadas con el software 'Data Modeler', se encuentra un pdf de ambos para una mejor visualización y también se encuentra una carpeta llamada 'MER Y RELACIONAL' que contiene todos los archivos del 'Data Modeler' de dichos modelos, que son necesarios para abrir el archivo desde dicho programa.
    
    - Modelo Físico: En esta carpeta se encuentra el archivo que representa el modelo físico del proyecto (creaciones de las tablas y de las constraints). Dicho archivo es un .sql con el que se puede visualizar comodamente con el 'Bloc de Notas' o 'Notepad++'. Finalmente, para el uso de dicho modelo físico se ha usado nuestra base de datos, utilizando el software 'Oracle SQL Developer'.
    
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    
  A continuación, se encuentran una serie de pasos/instrucciones para que ayuden a poder moverse por todo el programa, entre sus diversas clases y paquetes:

    1.- Para empezar, para poder efectuar nuestro proyecto se necesitan instalar los siguientes programas: Netbeans IDE (programación), Visual Paradigm (diagrama de clases), Data Modeler (modelos MER y relacional) y Oracle SQL Developer (base de datos).
    2.- Seguidamente, debemos llevar un orden para visualizar y entender el proyecto en sí. Primeramente, debemos mirar el diagrama de clases de nuestras tablas con sus respectivas relaciones y atributos. Después, debemos pasarnos por el Data Modeler para contemplar nuestros modelos MER y Relacional. Tras esto, observar el modelo físico para poder descubrir las diferentes contraints de cada tabla, los tipos de atributos o las claves foráneas, entre otras cosas. Finalmente, debemos hacer varias sentencias insert en nuestra base de datos Oracle para que nuestro proyecto pueda ejecutarse sin ningún tipo de contratiempo. Ahora, ya podemos adentrarnos en nuestro proyecto en java.
    3.- Dentro de nuestro proyecto en Netbeans, tenemos diversos paquetes que albergan sus clases correspondientes:
      · Controladora, recogiendo el Main.     
      · Vista, recogiendo todas las ventanas o paneles.
      · Modelo UML, recogiendo las clases necesarias pertenecientes a la base de datos con sus atributos, getters y setters y constructores.
      · Modelo BD, recogiendo las clases que realizan las funciones que se solicitan a la base de datos.
      · Excepciones, recogiendo los procesos que agrupan los errores en códigos java.
    4.- Al ejecutar, nuestro programa comenzará en la ventana 'Login', donde debemos entrar con el usuario y contraseña del administrador. De esta, pasamos a la ventana 'Menu' donde podemos movernos entre diferentes menus presentes en el.
      ** Para entrar en nuestro programa como un usuario de logística, debemos crear un trabajador de logística. Esto es importante ya que el menu de 'Parte' es diferente dependiendo de si el trabajador es administrador o logística. ** 
    5.- Si cliqueamos el menu de trabajador, nos lleva a la ventana 'Trabajador' donde podremos crear/modificar/borrar diferentes trabajadores. Podremos asignarles un tipo de trabajador (Administrador o Logística), un usuario y contraseña o un centro de trabajo, entre otras cosas.
    6.- En cuanto al menu de parte:
      · Si el trabajador es de logistica, nos abre la ventana 'Parte' donde tenemos un combobox de los IDs de los partes que ha creado ese trabajador, con un textfield que muestra el estado en el que se encuentra el parte que marquemos en el combobox (para que puedan salirnos las IDs de los partes de ese trabajador, debemos crearlos en esta misma ventana). Podemos realizar las funciones de crear, modificar, cerrar o borrar un parte (debemos seleccionar el ID del parte en el combobox antes de darle a uno de los cuatro botones). Además tenemos la función de albarán, donde si la cliqueamos nos llevará a un nuevo menu donde podremos crear o modificar los albaranes del parte que seleccionemos en el combobox.
      · Si el trabajador es de administrador, nos abre la ventana 'Parte' donde tenemos que seleccionar el ID del trabajador y seleccionar dos fechas. Tras esto, tenemos que cliquear el botón de 'Buscar' para que nos muestre en un combobox las IDs de los partes que ha tratado ese trabajador entre las fechas que hemos marcado. Además, nos mostrará en un textfield el estado en el que se encuentra el parte que marquemos en el combobox. Podemos realizar las funciones de validar, modificar o borrar un parte (debemos seleccionar el ID del parte en el combobox antes de darle a uno de los tres botones).
      
      
