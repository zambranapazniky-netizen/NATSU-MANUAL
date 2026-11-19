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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.NatsuBrandRepository
import com.example.model.ManualBlock
import com.example.model.MockupAsset
import com.example.ui.components.BlockHeroHeader
import com.example.ui.components.MockupPlateCard
import com.example.ui.components.SectionDividerHeader
import com.example.ui.theme.NatsuAnthracite
import com.example.ui.theme.NatsuBodyFontFamily
import com.example.ui.theme.NatsuDisplayFontFamily
import com.example.ui.theme.NatsuMonoFontFamily
import com.example.ui.theme.NatsuPureWhite
import com.example.ui.theme.NatsuRacingRed

@Composable
fun Block2TipografiaScreen(
    onOpenZoom: (MockupAsset) -> Unit,
    modifier: Modifier = Modifier
) {
    val blockAssets = remember { NatsuBrandRepository.assetsForBlock(ManualBlock.TIPOGRAFIA) }

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
            item(key = "block2_header") {
                BlockHeroHeader(block = ManualBlock.TIPOGRAFIA)
            }

            // Live Typographic Hierarchy Board
            item(key = "typo_hierarchy") {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    SectionDividerHeader(
                        tag = "02.1 · Sistema Tipográfico",
                        title = "Jerarquía Editorial y Respaldo Kanji",
                        subtitle = "Equilibrio entre fuerza display automotriz, claridad técnica para avisos en Santa Cruz y autenticidad japonesa."
                    )
                    TypographySpecimenBoard()
                }
            }

            // Supergraphic Speed Bar & OEM Technical Spec Card
            item(key = "supergraphic_spec") {
                SupergraphicAndOemSummaryCard()
            }

            item(key = "block2_plates_header") {
                SectionDividerHeader(
                    tag = "02.2 · Láminas Oficiales",
                    title = "Espécimen, Iconografía, OEM y Redes",
                    subtitle = "Toca cualquier pieza para inspeccionar detalles de trazo, códigos de barras y grillas en zoom fullscreen."
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
private fun TypographySpecimenBoard() {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        // Primary Display / Speed Italic
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = NatsuRacingRed,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "DISPLAY PRINCIPAL · NATSU JDM & TITULARES DE VELOCIDAD",
                    style = MaterialTheme.typography.labelSmall,
                    color = NatsuPureWhite.copy(alpha = 0.85f)
                )
                Text(
                    text = "Aa Bb Cc 0123456789",
                    fontFamily = NatsuDisplayFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 28.sp,
                    color = NatsuPureWhite
                )
                Text(
                    text = "Uso: Titulares de alto impacto, señalética de pasillos, promociones y rotulación vial.",
                    style = MaterialTheme.typography.bodySmall,
                    color = NatsuPureWhite.copy(alpha = 0.9f)
                )
            }
        }

        // Japanese Kanji Official Lockup
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "RESPALDO LINGÜÍSTICO OFICIAL",
                        style = MaterialTheme.typography.labelSmall,
                        color = NatsuRacingRed,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "自動車部品",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Jidōsha buhin — Autopartes · Gothic Sans-Serif japonés para fachadas, empaques y campañas.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Body & Technical Monospace Pair
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Surface(
                modifier = Modifier.weight(1f),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "TEXTO EDITORIAL",
                        style = MaterialTheme.typography.labelSmall,
                        color = NatsuRacingRed
                    )
                    Text(
                        text = "Plus Jakarta Sans",
                        fontFamily = NatsuBodyFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Lectura clara en catálogos, manuales, cotizaciones y avisos comerciales.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Surface(
                modifier = Modifier.weight(1f),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "CÓDIGOS OEM",
                        style = MaterialTheme.typography.labelSmall,
                        color = NatsuRacingRed
                    )
                    Text(
                        text = "NT-8490-BP",
                        fontFamily = NatsuMonoFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Tablas técnicas, números de parte, lotes y códigos HEX/CMYK.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun SupergraphicAndOemSummaryCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = NatsuAnthracite,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    // Twin diagonal racing stripes derived from the 22-degree speed bar
                    val stripe1 = Path().apply {
                        moveTo(size.width * 0.68f, size.height)
                        lineTo(size.width * 0.80f, size.height)
                        lineTo(size.width, 0f)
                        lineTo(size.width * 0.88f, 0f)
                        close()
                    }
                    val stripe2 = Path().apply {
                        moveTo(size.width * 0.84f, size.height)
                        lineTo(size.width * 0.90f, size.height)
                        lineTo(size.width, size.height * 0.45f)
                        lineTo(size.width, size.height * 0.18f)
                        close()
                    }
                    drawPath(stripe1, color = NatsuRacingRed)
                    drawPath(stripe2, color = NatsuRacingRed.copy(alpha = 0.55f))
                }
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.74f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Surface(
                    color = NatsuRacingRed,
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = "SUPERGRÁFICO · CORTE 22°",
                        style = MaterialTheme.typography.labelSmall,
                        color = NatsuPureWhite,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
                Text(
                    text = "Lenguaje de Velocidad y Etiquetas OEM",
                    style = MaterialTheme.typography.titleMedium,
                    color = NatsuPureWhite
                )
                Text(
                    text = "Todas las diagonales en flota, empaques, banners y señalética derivan del ángulo exacto de la barra inferior del logotipo NATSU.",
                    style = MaterialTheme.typography.bodySmall,
                    color = NatsuPureWhite.copy(alpha = 0.82f)
                )
            }
        }
    }
}
