package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.NatsuBrandRepository
import com.example.model.LogoBackdrop
import com.example.model.ManualBlock
import com.example.model.ManualUiState
import com.example.model.MockupAsset
import com.example.ui.components.BlockHeroHeader
import com.example.ui.components.InteractiveColorPaletteSection
import com.example.ui.components.LogoInspectorCard
import com.example.ui.components.LogoMisuseMatrix
import com.example.ui.components.MockupPlateCard
import com.example.ui.components.NatsuLogoVector
import com.example.ui.components.SectionDividerHeader
import com.example.ui.theme.NatsuAnthracite
import com.example.ui.theme.NatsuPureWhite
import com.example.ui.theme.NatsuRacingRed

@Composable
fun Block1FundamentosScreen(
    uiState: ManualUiState,
    onSelectBackdrop: (LogoBackdrop) -> Unit,
    onToggleGrid: () -> Unit,
    onToggleSafeZone: () -> Unit,
    onToggleKanji: () -> Unit,
    onCopyColorCode: (label: String, value: String) -> Unit,
    onOpenZoom: (MockupAsset) -> Unit,
    modifier: Modifier = Modifier
) {
    val blockAssets = remember { NatsuBrandRepository.assetsForBlock(ManualBlock.FUNDAMENTOS) }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 840.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Monumental Brand Cover Card
            item(key = "cover_banner") {
                NatsuManualCoverBanner()
            }

            item(key = "block1_header") {
                BlockHeroHeader(block = ManualBlock.FUNDAMENTOS)
            }

            // Brand Justification Pillars (Santa Cruz + JDM)
            item(key = "pillars_section") {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    SectionDividerHeader(
                        tag = "01.1 · Justificación Estratégica",
                        title = "Uniendo Japón con Santa Cruz de la Sierra",
                        subtitle = "Por qué la geometría pesada JDM y la barra de velocidad responden al clima, tracción y comercio cruceño."
                    )
                    NatsuBrandRepository.brandPillars.forEach { pillar ->
                        Surface(
                            shape = MaterialTheme.shapes.medium,
                            color = MaterialTheme.colorScheme.surface,
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = pillar.code,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = NatsuRacingRed,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = pillar.title,
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = pillar.body,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }

            // Interactive Tool #1: Logo Inspector
            item(key = "logo_inspector") {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    SectionDividerHeader(
                        tag = "01.2 · Herramienta Interactiva",
                        title = stringResource(R.string.inspector_title),
                        subtitle = stringResource(R.string.inspector_subtitle)
                    )
                    LogoInspectorCard(
                        selectedBackdrop = uiState.selectedBackdrop,
                        showConstructionGrid = uiState.showConstructionGrid,
                        showSafeZone = uiState.showSafeZone,
                        showKanjiLockup = uiState.showKanjiLockup,
                        onSelectBackdrop = onSelectBackdrop,
                        onToggleGrid = onToggleGrid,
                        onToggleSafeZone = onToggleSafeZone,
                        onToggleKanji = onToggleKanji
                    )
                }
            }

            // Interactive Tool #2: Official Color Palette (tap to copy HEX / RGB / CMYK)
            item(key = "color_palette") {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    SectionDividerHeader(
                        tag = "01.3 · Herramienta Interactiva",
                        title = stringResource(R.string.palette_title),
                        subtitle = stringResource(R.string.palette_subtitle)
                    )
                    InteractiveColorPaletteSection(
                        colors = NatsuBrandRepository.officialPalette,
                        onCopyValue = onCopyColorCode
                    )
                }
            }

            // Logo Misuse Guidelines Matrix
            item(key = "misuse_matrix") {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    SectionDividerHeader(
                        tag = "01.4 · Integridad de Marca",
                        title = "Usos Incorrectos del Logotipo",
                        subtitle = "Alteraciones prohibidas para preservar la nitidez industrial en avenidas, empaques e impresos."
                    )
                    LogoMisuseMatrix(rules = NatsuBrandRepository.logoMisuseRules)
                }
            }

            // Curated Visual Plates for Block 01
            item(key = "block1_plates_header") {
                SectionDividerHeader(
                    tag = "01.5 · Láminas Oficiales",
                    title = "Pliegos de Construcción y Color",
                    subtitle = "Toca cualquier lámina para inspeccionar detalles en pantalla completa con zoom."
                )
            }

            items(blockAssets, key = { it.id }) { asset ->
                MockupPlateCard(
                    asset = asset,
                    onClick = { onOpenZoom(asset) }
                )
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }
        }
    }
}

@Composable
private fun NatsuManualCoverBanner() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        color = NatsuAnthracite,
        border = BorderStroke(1.dp, NatsuRacingRed.copy(alpha = 0.6f))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(NatsuAnthracite)
                .drawBehind {
                    val redSlash = Path().apply {
                        moveTo(0f, 0f)
                        lineTo(size.width * 0.34f, 0f)
                        lineTo(size.width * 0.22f, size.height)
                        lineTo(0f, size.height)
                        close()
                    }
                    drawPath(redSlash, color = NatsuRacingRed)
                }
                .padding(horizontal = 20.dp, vertical = 22.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = NatsuAnthracite.copy(alpha = 0.85f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "MANUAL DE IDENTIDAD VISUAL",
                            style = MaterialTheme.typography.labelSmall,
                            color = NatsuPureWhite,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                    Text(
                        text = stringResource(R.string.brand_kanji),
                        style = MaterialTheme.typography.titleMedium,
                        color = NatsuPureWhite
                    )
                }

                NatsuLogoVector(
                    natsuColor = NatsuPureWhite,
                    speedBarColor = NatsuPureWhite,
                    autoPartsColor = NatsuPureWhite,
                    modifier = Modifier
                        .fillMaxWidth(0.76f)
                        .padding(vertical = 4.dp)
                )

                Text(
                    text = "Calidad Japonesa y Fuerza para el Motor de Bolivia",
                    style = MaterialTheme.typography.titleSmall,
                    color = NatsuPureWhite
                )
                Text(
                    text = stringResource(R.string.brand_territory),
                    style = MaterialTheme.typography.labelMedium,
                    color = NatsuPureWhite.copy(alpha = 0.78f)
                )
            }
        }
    }
}
