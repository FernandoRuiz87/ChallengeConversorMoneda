# ChallengeConversorMoneda

Proyecto Java para convertir monedas. El código fuente se encuentra en la carpeta `src`.

## Requisitos

- Java JDK 8 o superior
- (Opcional) Un IDE como Eclipse, IntelliJ IDEA o NetBeans

## Estructura del proyecto

- `src/`: Código fuente Java.
- `.gitignore`: Archivos y carpetas ignorados por git.
- `README.md`: Este archivo.

## Notas

- Este proyecto utiliza la librería [Gson](https://github.com/google/gson) para el manejo de JSON. Descarga el archivo JAR y agrégalo al classpath para compilar y ejecutar.
- El historial de conversiones se guarda en un archivo de texto en el mismo directorio del proyecto.

## ¿Cómo funciona?

1. **Inicio:**  
   Al ejecutar el programa, verás un menú principal con tres opciones:

   - Consultar moneda
   - Ver historial
   - Salir

2. **Consultar moneda:**

   - El programa muestra una lista de monedas disponibles obtenidas desde una API.
   - El usuario ingresa el código de la moneda base.
   - Puede elegir ver todas las conversiones posibles o convertir a una moneda específica.
   - Si elige una conversión específica, ingresa el código de la moneda destino y la cantidad a convertir.
   - El resultado de la conversión se muestra en pantalla y se guarda en el historial.

3. **Ver historial:**

   - Muestra todas las conversiones realizadas previamente, almacenadas en un archivo de texto.

4. **Salir:**
   - Finaliza el programa.

El programa utiliza una API externa para obtener los tipos de cambio y la librería Gson para procesar las respuestas en formato JSON.
