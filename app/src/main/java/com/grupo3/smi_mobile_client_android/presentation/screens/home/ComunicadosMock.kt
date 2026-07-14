package com.grupo3.smi_mobile_client_android.presentation.screens.home

import com.grupo3.smi_mobile_client_android.domain.model.Comunicado

// Datos de ejemplo para poder demostrar HU04 (filtrado) mientras HU02
// (feed conectado al backend) sigue en construcción por otro compañero.
// Cuando HU02 esté lista, se reemplaza esta lista por la que traiga la API real.
object ComunicadosMock {
    val lista = listOf(
        Comunicado(
            id = "1",
            titulo = "Nuevos equipos de seguridad industrial disponibles",
            categoria = "Seguridad",
            fecha = "12 Oct 2023",
            resumen = "El departamento de logística ha completado la distribución de los nuevos cascos tipo II y arneses de seguridad."
        ),
        Comunicado(
            id = "2",
            titulo = "Reporte de reducción de emisiones Q3",
            categoria = "Sostenibilidad",
            fecha = "10 Oct 2023",
            resumen = "Nuestra planta principal ha logrado reducir el consumo energético en un 15% comparado con el trimestre anterior."
        ),
        Comunicado(
            id = "3",
            titulo = "Cronograma de pagos de gratificación",
            categoria = "Pagos",
            fecha = "08 Oct 2023",
            resumen = "Se informa a todo el personal administrativo y operativo el cronograma oficial de pagos de este mes."
        ),
        Comunicado(
            id = "4",
            titulo = "Celebración por el día del trabajador",
            categoria = "Eventos",
            fecha = "05 Oct 2023",
            resumen = "Los invitamos a la actividad de integración que se realizará el próximo viernes en el patio principal."
        ),
        Comunicado(
            id = "5",
            titulo = "Nuevo seguro de vida para colaboradores",
            categoria = "Beneficios",
            fecha = "01 Oct 2023",
            resumen = "A partir de este mes, todos los colaboradores contarán con una póliza de seguro de vida ampliada."
        ),
        Comunicado(
            id = "6",
            titulo = "Simulacro de sismo obligatorio",
            categoria = "Seguridad",
            fecha = "28 Sep 2023",
            resumen = "Este viernes se realizará el simulacro trimestral de evacuación en todas las plantas de producción."
        )
    )
}
