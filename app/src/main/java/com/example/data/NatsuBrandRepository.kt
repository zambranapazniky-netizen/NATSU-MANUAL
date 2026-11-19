package com.example.data

import androidx.compose.ui.graphics.Color
import com.example.model.BrandColorSpec
import com.example.model.BrandPillar
import com.example.model.LogoMisuseRule
import com.example.model.ManualBlock
import com.example.model.MisuseType
import com.example.model.MockupAsset
import com.example.ui.theme.NatsuAnthracite
import com.example.ui.theme.NatsuIndustrialGray
import com.example.ui.theme.NatsuPureWhite
import com.example.ui.theme.NatsuRacingRed

object NatsuBrandRepository {

    val officialPalette: List<BrandColorSpec> = listOf(
        BrandColorSpec(
            id = "racing_red",
            name = "Rojo Racing NATSU",
            englishName = "Racing Red",
            role = "Dominante · Potencia & Visibilidad Vial",
            hex = "#E60000",
            rgb = "230, 0, 0",
            cmyk = "0 / 100 / 100 / 10",
            pantone = "Pantone 186 C",
            color = NatsuRacingRed,
            onSwatchColor = NatsuPureWhite,
            justification = "Canaliza el 'Hinomaru' del sol naciente y el automovilismo JDM, conectando con la calidez, dinamismo y energía comercial de Santa Cruz de la Sierra."
        ),
        BrandColorSpec(
            id = "anthracite",
            name = "Gris Antracita",
            englishName = "Anthracite Charcoal",
            role = "Soporte Industrial · Asfalto & Precisión",
            hex = "#1E1E1E",
            rgb = "30, 30, 30",
            cmyk = "70 / 60 / 60 / 90",
            pantone = "Pantone 433 C",
            color = NatsuAnthracite,
            onSwatchColor = NatsuPureWhite,
            justification = "Eleva la marca de un repuesto genérico a una firma automotriz de estándar industrial japonés. Presente en revestimientos Alucobond, uniformes y flota."
        ),
        BrandColorSpec(
            id = "pure_white",
            name = "Blanco Puro",
            englishName = "Pure White",
            role = "Contraste Extremo · Claridad Técnica",
            hex = "#FFFFFF",
            rgb = "255, 255, 255",
            cmyk = "0 / 0 / 0 / 0",
            pantone = "Pantone White",
            color = NatsuPureWhite,
            onSwatchColor = NatsuAnthracite,
            justification = "Aporta limpieza técnica, legibilidad inmediata a distancia y máximo contraste bajo el sol intenso de las avenidas cruceñas."
        ),
        BrandColorSpec(
            id = "steel_gray",
            name = "Gris Acero Industrial",
            englishName = "Industrial Steel Gray",
            role = "Secundario · Retículas & Especificaciones",
            hex = "#4A4A4A",
            rgb = "74, 74, 74",
            cmyk = "60 / 50 / 50 / 20",
            pantone = "Pantone Cool Gray 10 C",
            color = NatsuIndustrialGray,
            onSwatchColor = NatsuPureWhite,
            justification = "Equilibra la arquitectura visual en tablas técnicas, fichas de repuestos OEM, divisores editoriales y detalles metálicos de showroom."
        )
    )

    val brandPillars: List<BrandPillar> = listOf(
        BrandPillar(
            code = "ADN 01",
            title = "NATSU (Verano / Calor) · Territorio Cruceño",
            body = "Conecta las altas temperaturas de Santa Cruz de la Sierra con ingeniería japonesa resistente al desgaste térmico, pensada para camionetas 4x4, vehículos importados y logística pesada."
        ),
        BrandPillar(
            code = "ADN 02",
            title = "Morfología JDM · Torii y Katana",
            body = "Tipografía display personalizada de peso Heavy: los remates superiores en A y S evocan pórticos torii y trazos kanji, mientras las terminaciones curvas en N, A y T aportan tracción propia."
        ),
        BrandPillar(
            code = "ADN 03",
            title = "La Barra de Velocidad (22°)",
            body = "La franja sólida bajo 'NA' con corte diagonal a 22° compensa el peso óptico superior y actúa como vector direccional que empuja la lectura hacia el descriptor 'AUTO PARTS'."
        ),
        BrandPillar(
            code = "ADN 04",
            title = "100% Vector Plano Multiformato",
            body = "Sin degradados ni líneas finas: responde con nitidez extrema en letras corpóreas LED 3D, flexografía a 1 tinta sobre cartón kraft, etiquetas de almacén y micrograbado en repuestos metálicos."
        )
    )

    val logoMisuseRules: List<LogoMisuseRule> = listOf(
        LogoMisuseRule(
            type = MisuseType.STRETCHED,
            title = "Distorsionar proporciones",
            description = "No estirar ni comprimir horizontal o verticalmente el bloque constructivo."
        ),
        LogoMisuseRule(
            type = MisuseType.DROP_SHADOW,
            title = "Aplicar sombras o brillos 3D",
            description = "El logotipo es estrictamente plano; no añadir sombras paralelas ni biseles."
        ),
        LogoMisuseRule(
            type = MisuseType.ROTATED,
            title = "Inclinar o rotar el emblema",
            description = "Mantener siempre la línea base horizontal; la velocidad vive en el corte de 22°."
        ),
        LogoMisuseRule(
            type = MisuseType.LOW_CONTRAST,
            title = "Fondos de bajo contraste",
            description = "Prohibido ubicar rojo sobre tonos oscuros o grises sin separación suficiente."
        ),
        LogoMisuseRule(
            type = MisuseType.ALTERED_COLORS,
            title = "Alterar la paleta oficial",
            description = "No sustituir el Rojo Racing por naranjas, azules, degradados ni colores ajenos."
        ),
        LogoMisuseRule(
            type = MisuseType.OUTLINED_STROKE,
            title = "Convertir a solo contorno",
            description = "No vaciar el relleno sólido de las letras; debilita la lectura en avenidas."
        )
    )

    val curatedAssets: List<MockupAsset> = listOf(
        // BLOQUE 01: FUNDAMENTOS Y CONSTRUCCIÓN
        MockupAsset(
            id = "b1_portada",
            fileName = "b1_portada_manual.jpg",
            title = "Portada del Manual de Identidad",
            subtitle = "Edición corporativa mate Rojo Racing en bajo relieve con respaldo 自動車部品 y sistema base.",
            block = ManualBlock.FUNDAMENTOS,
            plateCode = "LAM-01",
            tag = "Portada"
        ),
        MockupAsset(
            id = "b1_reticula",
            fileName = "b1_reticula_proporciones.jpg",
            title = "Retícula Constructiva y Proporciones",
            subtitle = "Sistema modular base, altura X, cotas de inclinación de la barra de velocidad y kerning geométrico.",
            block = ManualBlock.FUNDAMENTOS,
            plateCode = "LAM-02",
            tag = "Retícula"
        ),
        MockupAsset(
            id = "b1_blueprint",
            fileName = "b1_blueprint_jdm.jpg",
            title = "Sistema de Construcción Geométrica JDM",
            subtitle = "Pliego técnico con tangentes circulares, ángulos de tracción a 22° y compensación óptica.",
            block = ManualBlock.FUNDAMENTOS,
            plateCode = "LAM-03",
            tag = "Blueprint"
        ),
        MockupAsset(
            id = "b1_blueprint_dark",
            fileName = "b1_blueprint_oscuro.jpg",
            title = "Balance Dinámico · Planimetría Nocturna",
            subtitle = "Desglose técnico de la barra de velocidad y el descriptor itálico AUTO PARTS para visibilidad vial.",
            block = ManualBlock.FUNDAMENTOS,
            plateCode = "LAM-04",
            tag = "Proporción"
        ),
        MockupAsset(
            id = "b1_paleta",
            fileName = "b1_paleta_corporativa.jpg",
            title = "Sistema Cromático Oficial",
            subtitle = "Muestras verticales tipo Pantone con equivalencias CMYK, RGB, HEX y Pantone para imprenta y digital.",
            block = ManualBlock.FUNDAMENTOS,
            plateCode = "LAM-05",
            tag = "Color"
        ),
        MockupAsset(
            id = "b1_color_scz",
            fileName = "b1_sistema_cromatico_libro.jpg",
            title = "Rojo Racing y Fuerza Industrial",
            subtitle = "Justificación editorial del sistema cromático adaptado al brillo solar y comercio de Santa Cruz.",
            block = ManualBlock.FUNDAMENTOS,
            plateCode = "LAM-06",
            tag = "Justificación"
        ),

        // BLOQUE 02: SISTEMA TIPOGRÁFICO Y LENGUAJE GRÁFICO
        MockupAsset(
            id = "b2_sistema",
            fileName = "b2_sistema_visual.jpg",
            title = "Tablero de Lenguaje Gráfico JDM",
            subtitle = "Integración de espécimen tipográfico, supergráfico diagonal rojo, iconografía y etiqueta OEM.",
            block = ManualBlock.TIPOGRAFIA,
            plateCode = "LAM-07",
            tag = "Sistema"
        ),
        MockupAsset(
            id = "b2_especimen",
            fileName = "b2_especimen_natsu.jpg",
            title = "Alfabeto NATSU Display (All Caps)",
            subtitle = "Juego completo A–Z, numeración 0–9 y signos con remates inspirados en caligrafía japonesa.",
            block = ManualBlock.TIPOGRAFIA,
            plateCode = "LAM-08",
            tag = "Espécimen"
        ),
        MockupAsset(
            id = "b2_anatomia",
            fileName = "b2_tipografia_bloque_rojo.jpg",
            title = "Anatomía Tipográfica y Remates",
            subtitle = "Trazos sólidos de alta tracción visual y 100% vector plano para máxima legibilidad en señalética.",
            block = ManualBlock.TIPOGRAFIA,
            plateCode = "LAM-09",
            tag = "Anatomía"
        ),
        MockupAsset(
            id = "b2_jerarquia",
            fileName = "b2_jerarquia_tipografica.jpg",
            title = "Jerarquía Tipográfica Corporativa",
            subtitle = "Contraste entre titulares itálicos deportivos, cuerpo técnico limpio y bloque Kanji 自動車部品.",
            block = ManualBlock.TIPOGRAFIA,
            plateCode = "LAM-10",
            tag = "Jerarquía"
        ),
        MockupAsset(
            id = "b2_linguistica",
            fileName = "b2_identidad_linguistica.jpg",
            title = "Identidad Lingüística: 自動車部品",
            subtitle = "Respaldo fonético 'Jidōsha buhin' (Autopartes) validado en aplicación nocturna sobre fachada.",
            block = ManualBlock.TIPOGRAFIA,
            plateCode = "LAM-11",
            tag = "Kanji"
        ),
        MockupAsset(
            id = "b2_spread",
            fileName = "b2_spread_tipografia.jpg",
            title = "Pliego Editorial Tipografía y Estilo",
            subtitle = "Fusión de geometría industrial y herencia japonesa presentada en retícula suiza de doble página.",
            block = ManualBlock.TIPOGRAFIA,
            plateCode = "LAM-12",
            tag = "Editorial"
        ),
        MockupAsset(
            id = "b2_iconos",
            fileName = "b2_iconografia_tecnica.jpg",
            title = "Sistema de Iconografía Técnica",
            subtitle = "Familia modular automotriz de trazo uniforme en Rojo Racing y Antracita (motor, frenos, bujía, QR).",
            block = ManualBlock.TIPOGRAFIA,
            plateCode = "LAM-13",
            tag = "Iconografía"
        ),
        MockupAsset(
            id = "b2_oem",
            fileName = "b2_etiqueta_oem.jpg",
            title = "Etiquetas Técnicas OEM (NT-8490-BP)",
            subtitle = "Sistema estandarizado de inventario con barra GENUINE PARTS, diagrama wireframe, código de barras y QR.",
            block = ManualBlock.TIPOGRAFIA,
            plateCode = "LAM-14",
            tag = "OEM"
        ),
        MockupAsset(
            id = "b2_rrss",
            fileName = "b2_redes_sociales.jpg",
            title = "Plantillas para Redes Sociales (Grilla 3×3)",
            subtitle = "Cohesión visual en Instagram combinando fichas técnicas en wireframe con anuncios NEW ARRIVAL.",
            block = ManualBlock.TIPOGRAFIA,
            plateCode = "LAM-15",
            tag = "Digital"
        ),

        // BLOQUE 03: PAPELERÍA, MERCHANDISING Y UNIFORMES
        MockupAsset(
            id = "b3_ecosistema",
            fileName = "b3_ecosistema_tactil.jpg",
            title = "Ecosistema Táctil de Marca",
            subtitle = "Papelería ejecutiva, polo técnico antracita, gorra bordada, termo, caja de frenos y cinta kraft.",
            block = ManualBlock.PAPELERIA,
            plateCode = "LAM-16",
            tag = "Papelería"
        ),
        MockupAsset(
            id = "b3_uniformes_scz",
            fileName = "b3_uniformes_clima_scz.jpg",
            title = "Uniformes para el Clima Cálido Cruceño",
            subtitle = "Polo Dry-Fit microperforado para asesores en Santa Cruz y overol industrial de taller con costuras rojas.",
            block = ManualBlock.PAPELERIA,
            plateCode = "LAM-17",
            tag = "Dry-Fit SCZ"
        ),
        MockupAsset(
            id = "b3_asesores",
            fileName = "b3_uniformes_asesores.jpg",
            title = "Indumentaria de Asesores en Tienda",
            subtitle = "Aplicación real en punto de venta: polo antracita con vivos Rojo Racing y gorra corporativa bordada.",
            block = ManualBlock.PAPELERIA,
            plateCode = "LAM-18",
            tag = "Retail"
        ),
        MockupAsset(
            id = "b3_taller",
            fileName = "b3_uniforme_taller.jpg",
            title = "Uniforme de Taller y Almacén",
            subtitle = "Chaqueta y overol de trabajo pesado en lona antracita con ribete reflectivo y logotipo en espalda y pecho.",
            block = ManualBlock.PAPELERIA,
            plateCode = "LAM-19",
            tag = "Taller"
        ),
        MockupAsset(
            id = "b3_merch",
            fileName = "b3_merchandising.jpg",
            title = "Merchandising Corporativo",
            subtitle = "Termo térmico negro mate, gorra estructurada antracita, lanyard rojo tejido y llavero metálico disco de freno.",
            block = ManualBlock.PAPELERIA,
            plateCode = "LAM-20",
            tag = "Merch"
        ),
        MockupAsset(
            id = "b3_empaques",
            fileName = "b3_empaques_embalaje.jpg",
            title = "Sistema de Empaques y Embalaje",
            subtitle = "Familia completa: caja de frenos, estuche JDM de bujías, caja master kraft sellada y bolsas retail.",
            block = ManualBlock.PAPELERIA,
            plateCode = "LAM-21",
            tag = "Empaques"
        ),
        MockupAsset(
            id = "b3_bolsas",
            fileName = "b3_bolsas_cinta.jpg",
            title = "Bolsas Comerciales y Cinta Corporativa",
            subtitle = "Versiones en Rojo Racing y Blanco Puro junto a cintas de seguridad de alto contraste para despachos.",
            block = ManualBlock.PAPELERIA,
            plateCode = "LAM-22",
            tag = "Embalaje"
        ),
        MockupAsset(
            id = "b3_filtro",
            fileName = "b3_empaque_filtro.jpg",
            title = "Empaque Filtro de Aceite (NOF-421)",
            subtitle = "Aplicación monocolor blanco sobre Rojo Racing con ilustración wireframe técnica del repuesto.",
            block = ManualBlock.PAPELERIA,
            plateCode = "LAM-23",
            tag = "Producto"
        ),
        MockupAsset(
            id = "b3_materiales",
            fileName = "b3_materiales_pesados.jpg",
            title = "Materiales Pesados y Micrograbado",
            subtitle = "Pruebas de 1 tinta sobre cartón corrugado para depósitos de Santa Cruz, estampado en plástico y metal.",
            block = ManualBlock.PAPELERIA,
            plateCode = "LAM-24",
            tag = "Industrial"
        ),
        MockupAsset(
            id = "b3_catalogo",
            fileName = "b3_catalogo_impreso.jpg",
            title = "Catálogo Impreso de Repuestos",
            subtitle = "Separadores en Rojo Racing ('BRAKING SYSTEMS') con grillas limpias de fotografía de hardware y códigos.",
            block = ManualBlock.PAPELERIA,
            plateCode = "LAM-25",
            tag = "Catálogo"
        ),

        // BLOQUE 04: ARQUITECTURA, FLOTA Y PUNTO DE VENTA
        MockupAsset(
            id = "b4_espacial",
            fileName = "b4_ecosistema_espacial.jpg",
            title = "Identidad Espacial: Flagship, Tótem y Flota",
            subtitle = "Integración de fachada en Alucobond antracita, fascia diagonal roja, tótem vial LED y van corporativa.",
            block = ManualBlock.ARQUITECTURA,
            plateCode = "LAM-26",
            tag = "Flagship"
        ),
        MockupAsset(
            id = "b4_flota",
            fileName = "b4_arquitectura_flota.jpg",
            title = "Flota Vehicular · Anillos Cruceños",
            subtitle = "Valla móvil en antracita mate con gráficas diagonales rojas diseñada para alto impacto en los Anillos y Radiales.",
            block = ManualBlock.ARQUITECTURA,
            plateCode = "LAM-27",
            tag = "Flota"
        ),
        MockupAsset(
            id = "b4_fachada_persp",
            fileName = "b4_fachada_perspectiva.jpg",
            title = "Fachada Real Santa Cruz · Vista Nocturna",
            subtitle = "Intervención real con rótulos corpóreos LED 3D resistentes al sol poniente y visibles en horario nocturno.",
            block = ManualBlock.ARQUITECTURA,
            plateCode = "LAM-28",
            tag = "Fachada SCZ"
        ),
        MockupAsset(
            id = "b4_mostrador",
            fileName = "b4_mostrador_servicio.jpg",
            title = "Mostrador de Atención y Backwall LED",
            subtitle = "Muro posterior en Rojo Racing con logotipo retroiluminado, pantallas de catálogo y mostrador industrial.",
            block = ManualBlock.ARQUITECTURA,
            plateCode = "LAM-29",
            tag = "Mostrador"
        ),
        MockupAsset(
            id = "b4_showroom",
            fileName = "b4_showroom_pared.jpg",
            title = "Muro Exhibidor JDM en Showroom",
            subtitle = "Exhibición modular de aros, volantes y accesorios en malla metálica antracita con acento perimetral rojo.",
            block = ManualBlock.ARQUITECTURA,
            plateCode = "LAM-30",
            tag = "Showroom"
        ),
        MockupAsset(
            id = "b4_kiosco",
            fileName = "b4_kiosco_digital.jpg",
            title = "Interfaz Digital y Kiosco Interactivo",
            subtitle = "Tótem táctil de autoconsulta en modo oscuro con buscador de repuestos y diagrama 3D wireframe de motor.",
            block = ManualBlock.ARQUITECTURA,
            plateCode = "LAM-31",
            tag = "Kiosco UI"
        ),
        MockupAsset(
            id = "b4_interior_scz",
            fileName = "b4_interior_mostrador_scz.jpg",
            title = "Experiencia de Atención en Sucursal Real",
            subtitle = "Aplicación de identidad interior, mostrador iluminado y señalética de marcas aliadas en tienda real.",
            block = ManualBlock.ARQUITECTURA,
            plateCode = "LAM-32",
            tag = "Interior"
        ),

        // BLOQUE 05: ARTES PUBLICITARIOS Y CAMPAÑAS
        MockupAsset(
            id = "b5_bujias",
            fileName = "b5_campana_bujias.jpg",
            title = "Desata la Precisión Japonesa",
            subtitle = "Campaña de lanzamiento para Bujías de Iridio de Alta Performance con sello MADE IN JAPAN y código QR.",
            block = ManualBlock.CAMPANAS,
            plateCode = "CMP-01",
            tag = "Rendimiento"
        ),
        MockupAsset(
            id = "b5_frenos",
            fileName = "b5_campana_frenos.jpg",
            title = "Mes de la Seguridad · Frenos JDM",
            subtitle = "Arte promocional de alto impacto comercial (Hasta 25% de descuento en discos y pastillas con diagnóstico).",
            block = ManualBlock.CAMPANAS,
            plateCode = "CMP-02",
            tag = "Promoción"
        ),
        MockupAsset(
            id = "b5_origen",
            fileName = "b5_campana_jdm_origen.jpg",
            title = "Repuestos JDM para Exigentes",
            subtitle = "Pieza de branding panorámico 'Calidad y Rendimiento de Origen 自動車部品' con esquemático CAD en rojo.",
            block = ManualBlock.CAMPANAS,
            plateCode = "CMP-03",
            tag = "Branding"
        ),
        MockupAsset(
            id = "b5_gigantografia",
            fileName = "b5_campana_gigantografia.jpg",
            title = "Gigantografía Vía Pública · El Alma de tu Auto",
            subtitle = "Formato monumental de alta legibilidad para avenidas rápidas (Av. Cristo Redentor / Doble Vía a La Guardia).",
            block = ManualBlock.CAMPANAS,
            plateCode = "CMP-04",
            tag = "Vía Pública"
        ),
        MockupAsset(
            id = "b5_lineas",
            fileName = "b5_campana_lineas_producto.jpg",
            title = "Todo en Autopartes y Repuestos",
            subtitle = "Folleto modular en 4 cuadrantes (1. Frenos · 2. Motor · 3. Eléctrico · 4. Suspensión) con sellos ISO y JDM.",
            block = ManualBlock.CAMPANAS,
            plateCode = "CMP-05",
            tag = "Catálogo"
        )
    )

    val facadeBeforeAsset = MockupAsset(
        id = "b4_fachada_antes",
        fileName = "b4_fachada_antes.jpg",
        title = "Fachada Real Santa Cruz · Estado Original (Antes)",
        subtitle = "Fotografía original del inmueble comercial antes de la intervención de identidad arquitectónica NATSU.",
        block = ManualBlock.ARQUITECTURA,
        plateCode = "SCZ-00",
        tag = "Antes"
    )

    val facadeAfterAsset = MockupAsset(
        id = "b4_fachada_despues",
        fileName = "b4_fachada_despues.jpg",
        title = "Fachada Real Santa Cruz · Propuesta NATSU (Después)",
        subtitle = "Transformación integral con Alucobond antracita, fascia diagonal Rojo Racing y rótulo LED 3D + 自動車部品.",
        block = ManualBlock.ARQUITECTURA,
        plateCode = "SCZ-01",
        tag = "Después"
    )

    fun assetsForBlock(block: ManualBlock): List<MockupAsset> =
        curatedAssets.filter { it.block == block }

    val allZoomableAssets: List<MockupAsset> =
        curatedAssets + listOf(facadeBeforeAsset, facadeAfterAsset)
}
