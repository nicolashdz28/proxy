# Ejercicio: Patrón Proxy en Java

## 🎭 Contexto del Problema

Tenemos un servicio `Image` que carga una imagen desde disco, una operación costosa. Queremos postponer esta carga hasta que sea absolutamente necesario (carga perezosa - lazy loading). Además, queremos controlar el acceso al objeto real.

## 🎯 Tu Tarea

Transformar el código usando el **Patrón Proxy** para implementar carga perezosa (lazy loading) y controlar el acceso a los objetos costosos.

## 📋 Instrucciones Paso a Paso

### Paso 1: Crea la Clase Proxy

Crea una clase `ProxyImage` que implemente la interfaz `Image`:

```java
public class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;
    
    public ProxyImage(String filename) {
        this.filename = filename;
    }
}
```
### Paso 2: Implementa la Lógica de Carga Perezosa

En el método display() del ProxyImage, implementa la carga perezosa:
```java
@Override
public void display() {
    if (realImage == null) {
        realImage = new RealImage(filename); // Carga solo cuando es necesario
    }
    realImage.display(); // Delega al objeto real
}
```
### Paso 3: Modifica el Código Cliente

En la clase Main, cambia la creación de objetos para usar el Proxy en lugar del objeto real directamente:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Application started.");
        
        // Usando Proxy - la carga no ocurre inmediatamente
        Image image = new ProxyImage("photo.jpg");
        System.out.println("Proxy created, but RealImage not loaded yet.");
        
        // La carga solo ocurre aquí, cuando realmente necesitamos mostrar la imagen
        image.display();
        
        // Segunda llamada - ya está cargada, no se vuelve a cargar
        System.out.println("Second call:");
        image.display();
    }
}
```
### 🤔 Preguntas de Análisis

* Carga Perezosa: ¿Cuándo se ejecuta el constructor de RealImage en el código original vs en el código con Proxy?
R=En el código original, el constructor de RealImage se ejecuta inmediatamente cuando se crea el objeto, aunque todavía no se utilice la imagen.

En el código con Proxy, el constructor de RealImage se ejecuta hasta que realmente se necesita utilizar la imagen
* Transparencia: ¿El código cliente necesita saber si está trabajando con un Proxy o con el objeto real? ¿Por qué?
R=No, no necesita saberlo porque al final implementan la misma interfaz y ofrecen los mismos metodos 
* Rendimiento: ¿Qué ventajas de rendimiento ofrece el patrón Proxy en este escenario?
R=El proxy es mejor porque no carga imagenes que no se hayan realizado 
* Control de Acceso: Además de la carga perezosa, ¿qué otros tipos de control podríamos implementar en el Proxy? (Ej: verificación de permisos, logging, cache)
R=Verificación de permisos: comprobar si el usuario tiene autorización para acceder a una imagen.
Logging: registrar cuándo y quién accede a una imagen.
Caché: guardar imágenes ya utilizadas para evitar cargarlas varias veces.
Control de acceso: restringir el uso de ciertos recursos según las condiciones establecidas.
Validación: comprobar que las solicitudes sean correctas antes de permitir el acceso al objeto real.
* Principio SOLID: ¿Qué principio SOLID se aplica aquí al permitir que el Proxy y el RealImage implementen la misma interfaz?
R=Principalmente el Principio de Sustitución de Liskov (LSP), que establece que los objetos de una clase deben poder sustituirse por objetos de otra clase que implementen el mismo contrato, sin alterar el funcionamiento del programa.

### 🧪 Pruebas a Realizar

Ejecuta el código para verificar que:

Sin Proxy: El mensaje "Loading image..." aparece inmediatamente al crear el objeto
Con Proxy: El mensaje "Loading image..." aparece solo al llamar a display()
Múltiples llamadas: La carga solo ocurre una vez, incluso con múltiples llamadas a display()

### 💡 Escenarios de Extensión

Considera cómo extenderías este patrón para:

Proxy de Protección: Verificar permisos antes de cargar/mostrar la imagen
Proxy de Cache: Almacenar en cache imágenes ya cargadas
Proxy de Logging: Registrar cada vez que se accede a una imagen
Proxy Remoto: Acceder a imágenes en servidores remotos