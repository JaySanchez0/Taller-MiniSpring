# AREP-SOCKET-T03

### Presentado por: 

Jeisson Geovanny Sanchez Ramos

### Link de heroku

[ver](https://enigmatic-springs-51558.herokuapp.com/)

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


### Prueba contenido estatico

Al pedir el directorio root del sitio deberia cargar el index.html

![img](img/index.PNG)

Al pedir /js/app.js deberia mostrar el javascript

![img](img/js.PNG)

Al pedir /style.css deberia mostrar la hoja de estilos.

![img](img/css.PNG)

Al pedir /porter2.PNG deberia mostrar la imagen

![img](img/imagen.PNG) 



### Referencias

[Imagenes socket](https://stackoverflow.com/questions/25086868/how-to-send-images-through-sockets-in-java)


[headers para imagenes](https://stackoverflow.com/questions/14728125/how-do-i-send-an-image-over-http-protocol-in-c)

