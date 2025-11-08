Proyecto Sistema de Gestión de Restaurante - Ronny Carrasco, Sebastián Camacho

Paquetes:

Model: El paquete model contiene las clases que representan los objetos del mundo real de la aplicación, es decir, el modelo de datos del sistema.
Estos objetos suelen tener atributos (datos) y métodos (comportamientos), pero no lógica de negocio compleja ni interfaz gráfica.
Sirven como el “esqueleto” o la estructura central del programa.

Repository: el paquete repository se encarga de guardar, recuperar y gestionar los datos de esos objetos del paquete model.

Service: El paquete service contiene la lógica de negocio del sistema.
Es decir, las acciones o procesos reales que tu aplicación realiza utilizando los objetos del modelo y los repositorios. 

UI: El paquete ui significa “User Interface” y se encarga de mostrar información al usuario, recibir la entrada del usuario y llamar al paquete service para ejecutar acciones.

Exception: El paquete exception se utiliza para definir errores personalizados de la aplicación.
Permite controlar situaciones inesperadas sin que el programa se bloquee.
Facilita informar al usuario o al sistema de forma clara lo qué salió mal.

Util: se utiliza para funciones o clases auxiliares que no pertenecen a un objeto específico ni forman parte de la lógica de negocio principal, pero que facilitan tareas recurrentes en todo el proyecto.
Evita que el código se duplique.
Resumiendo: Es como la caja de herramientas del proyecto. Contiene funciones y clases auxiliares que ayudan a que el código sea más limpio, reutilizable y organizado.

UML: El paquete uml del proyecto se usa para guardar los diagramas UML (como clases, casos de uso, secuencia, etc.) que representan visualmente la estructura y funcionamiento del sistema. Es el paquete donde se documenta 
gráficamente el diseño del proyecto.



Diseño Visual: Se utiliza un fondo oscuro para todas las ventanas y botones en rojo vinotinto con texto blanco, siguiendo una paleta coherente en todo el sistema.
Las mesas y productos se muestran en tarjetas cuadradas, con colores indicativos del estado (por ejemplo, mesas libres en verde y ocupadas en rojo), ofreciendo una visualización clara y rápida del estado actual.

Flujo del Programa: El usuario abre el menú principal, desde donde puede acceder a las distintas funcionalidades.
En Gestión de Mesas, el usuario puede abrir, cerrar y visualizar mesas. Las acciones se reflejan inmediatamente en la interfaz mediante cambios de color y texto.
En Pedidos y Pagos, se selecciona una mesa para crear un pedido, se agregan productos desde la carta, y se puede cerrar el pedido, calculando el total a pagar.
En Gestión de Carta / Menú, se pueden agregar, editar y eliminar, mostrando los mismos tarjetas visuales que en la gestión de mesas para mantener consistencia visual.
Todas las acciones están acompañadas de mensajes emergentes (JOptionPane) para informar al usuario sobre el éxito o error de la operación, garantizando un flujo de trabajo guiado y amigable.
