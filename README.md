Proyecto Sistema de Gestión de Restaurante - Ronny Carrasco, Sebastián Camacho

Paquetes:

Model: El paquete model contiene las clases que representan los objetos del mundo real de tu aplicación, es decir, el modelo de datos del sistema.
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
