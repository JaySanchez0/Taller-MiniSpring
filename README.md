# AREP-SOCKET-T03  Modificacion a spring

### Presentado por: 

Jeisson Geovanny Sanchez Ramos


Para este trabajo se decidio reutilizar el metodo get ya creado y simplemente hacemos que el metodo get llame al metodo correspondiente al bean. 

### Link de heroku

MicroSpring

[ver](https://floating-everglades-57412.herokuapp.com/)

Spark

[ver](https://enigmatic-springs-51558.herokuapp.com/)

### Se volvio a escribir los metodos definidos para que la pagina main funcione
ademas se definieron suma de 2 numero 

Suma

[https://floating-everglades-57412.herokuapp.com/suma?a=2&&b=7](https://floating-everglades-57412.herokuapp.com/suma?a=2&&b=7)

Multiplicacion

[https://floating-everglades-57412.herokuapp.com/mult?a=2&&b=7](https://floating-everglades-57412.herokuapp.com/mult?a=2&&b=7)


Index

[https://floating-everglades-57412.herokuapp.com/](https://floating-everglades-57412.herokuapp.com/)

Para esta aplicacion el controlador es la clase AppControler que se encuentra en el package com.app.Controller

La clase a quien le pasamos nuestro controlador es com.app.App

### Comando de ejecucion:


~~~
    mvn exec:java -Dexec.mainClass=com.app.App -Dexec.args=com.app.Controller.AppController
~~~

Al ejecutarlo por defecto en nuestro entorno local corre por el puerto 80

### Documento Arquitectura

[ver](AREP_T_03.pdf)

### JavaDoc

[ver](javadoc)

### Aplicacion 

La aplicacion utliza una implementacion propia de un servidor de archivos.

La aplicacion reconoce el path root / como index.html

[root](https://enigmatic-springs-51558.herokuapp.com/)

[index](https://enigmatic-springs-51558.herokuapp.com/index.html)

La aplicacion cuenta con los siguientes archivos estaticos

- index.html
- porter2.PNG
- styles.css
- js/app.js
- js/client.js

Con funciones get cuenta con las siguientes

- /peoples devuelve una lista de personas a manera de json
- /addPeople esta recibe parametros name, age y country

La funcion addPeople es con motivos practicos ya que esta peticion realmente deberia corresponder a people con el metodo post.

### Ejecutar

~~~
    mvn exec:java -Dexec.mainClass=com.app.App
~~~

### Prueba contenido estatico

Al pedir el directorio root del sitio deberia cargar el index.html

![img](img/index.PNG)

Al pedir /js/app.js deberia mostrar el javascript

[ver](https://enigmatic-springs-51558.herokuapp.com/js/app.js)

![img](img/js.PNG)

Al pedir /style.css deberia mostrar la hoja de estilos.

![img](img/css.PNG)

[ver](https://enigmatic-springs-51558.herokuapp.com/style.css)

Al pedir /porter2.PNG deberia mostrar la imagen

![img](img/imagen.PNG) 

[ver](https://enigmatic-springs-51558.herokuapp.com/porter2.PNG)

### Pruebas

![ver](img/test.PNG)

~~~
    mvn test
~~~

### Referencias

[Imagenes socket](https://stackoverflow.com/questions/25086868/how-to-send-images-through-sockets-in-java)


[headers para imagenes](https://stackoverflow.com/questions/14728125/how-do-i-send-an-image-over-http-protocol-in-c)

