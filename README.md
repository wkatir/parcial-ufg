# ParcialSM100223

Aplicación Android desarrollada en Kotlin que permite registrar y visualizar perfiles de usuarios, categorizándolos por edad y personalizando la experiencia visual según su categoría.

## Características

- **Formulario de registro** con validación de datos
- **Categorización automática** por edad:
  - Niño (0-12 años)
  - Adolescente (13-17 años)
  - Adulto (18-59 años)
  - Adulto Mayor (60+ años)
- **Persistencia de datos** usando SharedPreferences
- **Interfaz personalizada** con colores según categoría
- **Material Design** con componentes modernos
- **Manejo del ciclo de vida** con notificaciones Toast

## Video Demostración

https://github.com/user-attachments/assets/tu-video-id-aqui.mp4

> Si el video no carga automáticamente, [descárgalo aquí](./demo/EXAMEN.mp4)

## Tecnologías Utilizadas

- **Kotlin** - Lenguaje de programación
- **Android SDK** - API level 24-36
- **Material Components** - Diseño moderno y consistente
- **SharedPreferences** - Almacenamiento local de datos
- **SDP/SSP** - Unidades de tamaño escalables para mejor responsividad

## Requisitos del Sistema

- **minSdk:** 24 (Android 7.0 Nougat)
- **targetSdk:** 35 (Android 14)
- **compileSdk:** 36
- **Java:** 11

## Estructura del Proyecto

```
app/src/main/java/com/example/parcialsm100223/
├── MainActivity.kt          # Pantalla principal con formulario de registro
└── PerfilActivity.kt        # Pantalla de visualización del perfil
```

## Funcionalidades Principales

### MainActivity
- Captura de datos del usuario (nombre, edad, género)
- Validación de campos obligatorios
- Checkbox de aceptación de términos
- Switch para recordar datos
- Botón para cargar perfil guardado
- Diálogo de confirmación al presionar atrás
- Mensajes Toast en onPause/onResume

### PerfilActivity
- Visualización de información del usuario
- Mensaje personalizado según categoría
- Fondo de color dinámico por categoría
- Botón para editar perfil (volver a MainActivity)

## Instalación

1. Clona este repositorio:
```bash
git clone <url-del-repositorio>
```

2. Abre el proyecto en Android Studio

3. Sincroniza las dependencias de Gradle

4. Ejecuta la aplicación en un emulador o dispositivo físico

## Uso

1. **Registrar un nuevo perfil:**
   - Ingresa tu nombre
   - Ingresa tu edad (1-120)
   - Selecciona tu género
   - Acepta los términos y condiciones
   - Opcionalmente activa "Recordarme"
   - Presiona "Registrar"

2. **Cargar perfil guardado:**
   - Presiona "Ver Perfil"
   - Si hay datos guardados, se cargarán automáticamente

3. **Ver perfil:**
   - La pantalla mostrará tu información
   - El color de fondo cambiará según tu categoría
   - Presiona "Editar Perfil" para volver

## Validaciones

- Nombre: No puede estar vacío
- Edad: Debe ser un número entre 1 y 120
- Términos: Deben ser aceptados obligatoriamente

## Dependencias

```kotlin
implementation("androidx.core:core-ktx")
implementation("androidx.appcompat:appcompat")
implementation("com.google.android.material:material")
implementation("androidx.activity:activity")
implementation("androidx.constraintlayout:constraintlayout")
implementation("com.intuit.sdp:sdp-android:1.1.1")
implementation("com.intuit.ssp:ssp-android:1.1.1")
```

## Autor

Wilmer - Proyecto Parcial SM100223

## Licencia

Este proyecto fue desarrollado con fines académicos.
