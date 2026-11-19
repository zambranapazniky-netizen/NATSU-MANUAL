package com.example.model

import androidx.compose.ui.graphics.Color

enum class ManualBlock(
    val numberCode: String,
    val shortTitle: String,
    val fullTitle: String,
    val subtitle: String,
    val kanjiTag: String
) {
    FUNDAMENTOS(
        numberCode = "01",
        shortTitle = "Fundamentos",
        fullTitle = "Fundamentos y Construcción",
        subtitle = "ADN JDM, retícula geométrica, área de seguridad y paleta oficial",
        kanjiTag = "基盤 · IDENTIDAD"
    ),
    TIPOGRAFIA(
        numberCode = "02",
        shortTitle = "Tipografía",
        fullTitle = "Tipografía y Lenguaje Gráfico",
        subtitle = "NATSU Display, respaldo kanji 自動車部品, iconografía y etiquetas OEM",
        kanjiTag = "書体 · SISTEMA"
    ),
    PAPELERIA(
        numberCode = "03",
        shortTitle = "Papelería",
        fullTitle = "Papelería, Merch y Uniformes",
        subtitle = "Ecosistema táctil, uniformes Dry-Fit para Santa Cruz y empaques",
        kanjiTag = "製品 · TÁCTIL"
    ),
    ARQUITECTURA(
        numberCode = "04",
        shortTitle = "Arquitectura",
        fullTitle = "Arquitectura, Flota y PDV",
        subtitle = "Fachada real en Santa Cruz, flota en los Anillos, mostrador y kiosco",
        kanjiTag = "空間 · FLAGSHIP"
    ),
    CAMPANAS(
        numberCode = "05",
        shortTitle = "Campañas",
        fullTitle = "Artes Publicitarios y Campañas",
        subtitle = "Piezas comerciales de alto impacto, vía pública y líneas JDM",
        kanjiTag = "広告 · CAMPAÑAS"
    )
}

enum class LogoBackdrop(
    val id: String,
    val label: String,
    val subtitle: String,
    val backgroundColor: Color,
    val natsuColor: Color,
    val speedBarColor: Color,
    val autoPartsColor: Color,
    val kanjiColor: Color,
    val isDarkSurface: Boolean
) {
    RACING_RED(
        id = "red",
        label = "Rojo Racing",
        subtitle = "Versión Principal · #E60000",
        backgroundColor = Color(0xFFE60000),
        natsuColor = Color(0xFFFFFFFF),
        speedBarColor = Color(0xFFFFFFFF),
        autoPartsColor = Color(0xFFFFFFFF),
        kanjiColor = Color(0xFFFFFFFF),
        isDarkSurface = true
    ),
    ANTHRACITE(
        id = "anthracite",
        label = "Antracita",
        subtitle = "Fachada / Digital · #1E1E1E",
        backgroundColor = Color(0xFF1E1E1E),
        natsuColor = Color(0xFFE60000),
        speedBarColor = Color(0xFFE60000),
        autoPartsColor = Color(0xFFFFFFFF),
        kanjiColor = Color(0xFFFFFFFF),
        isDarkSurface = true
    ),
    PURE_WHITE(
        id = "white",
        label = "Blanco Puro",
        subtitle = "Editorial / Papelería · #FFFFFF",
        backgroundColor = Color(0xFFFFFFFF),
        natsuColor = Color(0xFFE60000),
        speedBarColor = Color(0xFFE60000),
        autoPartsColor = Color(0xFF1E1E1E),
        kanjiColor = Color(0xFF1E1E1E),
        isDarkSurface = false
    ),
    MONOCHROME(
        id = "mono",
        label = "Monocromo",
        subtitle = "1 Tinta / Cartón Kraft · #E6E6E6",
        backgroundColor = Color(0xFFE6E6E6),
        natsuColor = Color(0xFF141414),
        speedBarColor = Color(0xFF141414),
        autoPartsColor = Color(0xFF141414),
        kanjiColor = Color(0xFF141414),
        isDarkSurface = false
    )
}

data class BrandColorSpec(
    val id: String,
    val name: String,
    val englishName: String,
    val role: String,
    val hex: String,
    val rgb: String,
    val cmyk: String,
    val pantone: String,
    val color: Color,
    val onSwatchColor: Color,
    val justification: String
)

data class MockupAsset(
    val id: String,
    val fileName: String,
    val title: String,
    val subtitle: String,
    val block: ManualBlock,
    val plateCode: String,
    val tag: String
) {
    val assetUri: String get() = "file:///android_asset/natsu/$fileName"
}

enum class MisuseType {
    STRETCHED,
    DROP_SHADOW,
    ROTATED,
    LOW_CONTRAST,
    ALTERED_COLORS,
    OUTLINED_STROKE
}

data class LogoMisuseRule(
    val type: MisuseType,
    val title: String,
    val description: String
)

data class BrandPillar(
    val code: String,
    val title: String,
    val body: String
)

data class ManualUiState(
    val isDarkMode: Boolean = true,
    val selectedBlock: ManualBlock = ManualBlock.FUNDAMENTOS,
    val selectedBackdrop: LogoBackdrop = LogoBackdrop.ANTHRACITE,
    val showConstructionGrid: Boolean = true,
    val showSafeZone: Boolean = false,
    val showKanjiLockup: Boolean = true,
    val facadeSplitFraction: Float = 0.5f,
    val isGallerySheetOpen: Boolean = false,
    val galleryFilterBlock: ManualBlock? = null,
    val zoomedAsset: MockupAsset? = null
)
