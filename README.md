# SMI Mobile Client (Android)

Aplicación móvil nativa para Android desarrollada por el **Grupo 3**.

## Tecnologías Utilizadas

*   **Lenguaje:** Kotlin
*   **UI:** Jetpack Compose (Material 3)
*   **SDK:** Min SDK 24, Target SDK 36
*   **Build System:** Gradle (Kotlin DSL)

## Requisitos Previos

*   [Android Studio](https://developer.android.com/studio) (Versión reciente recomendada)
*   JDK 11 (o superior) configurado
*   Android SDK 36

## Guía de Configuración Local (Para Nuevos Desarrolladores)

Sigue estos pasos detallados para clonar el proyecto, configurarlo en tu entorno local y ejecutarlo por primera vez sin problemas:

### 1. Clonar el Repositorio
Abre tu terminal o línea de comandos y ejecuta el siguiente comando en el directorio donde deseas guardar el proyecto:
```bash
git clone https://github.com/San-Miguel-Industrias-PET/smi-mobile-client-android.git
```

### 2. Abrir en Android Studio
1. Inicia **Android Studio**.
2. En la pantalla de bienvenida, selecciona **"Open"** (o ve a `File > Open` si ya tienes un proyecto abierto).
3. Navega hasta la carpeta donde clonaste el repositorio, selecciona la carpeta `smi-mobile-client-android` (la que tiene el icono de Android) y haz clic en **"OK"**.
4. Si Android Studio te pregunta si confías en el proyecto, selecciona **"Trust Project"**.

### 3. Sincronización de Gradle
1. Una vez abierto, Android Studio comenzará a indexar los archivos y descargar todas las dependencias necesarias.
2. **Importante:** Asegúrate de tener una conexión a internet estable. Este proceso puede tardar un par de minutos la primera vez.
3. Verifica en la barra de estado inferior o en la ventana **"Build"** que la sincronización diga **"Gradle sync finished"** con un check verde.
   * *Si surge algún error de versión de Java, asegúrate de que Android Studio esté configurado para usar al menos JDK 11 (preferiblemente el JDK embebido: `Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JDK`).*

### 4. Configurar el Dispositivo de Pruebas
**Opción A: Dispositivo Físico**
1. Habilita las **Opciones de Desarrollador** en tu teléfono y activa la **Depuración USB** (USB Debugging).
2. Conecta tu teléfono por USB a la computadora y acepta el prompt de permisos en la pantalla de tu teléfono.

**Opción B: Emulador Virtual**
1. Ve a `Tools > Device Manager` en Android Studio.
2. Crea un dispositivo virtual (ej. Pixel 7) descargando una imagen de sistema **Android 7.0 (API 24) o superior**.
3. Inicia el emulador.

### 5. Ejecutar la Aplicación
1. En la barra superior de herramientas de Android Studio, asegúrate de que esté seleccionado el módulo **`app`** y tu dispositivo (físico o emulador) justo al lado.
2. Haz clic en el botón verde con forma de flecha **"Run 'app'"** (o usa el atajo `Shift + F10`).
3. Android Studio construirá el proyecto y lanzará la aplicación automáticamente en tu pantalla.

## Estructura del Código

El código fuente principal y la interfaz de usuario se encuentran en el módulo `app`:
`app/src/main/java/com/grupo3/smi_mobile_client_android/`
