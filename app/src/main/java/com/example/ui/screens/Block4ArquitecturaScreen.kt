package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.NatsuBrandRepository
import com.example.model.ManualBlock
import com.example.model.MockupAsset
import com.example.ui.components.BlockHeroHeader
import com.example.ui.components.FacadeBeforeAfterComparator
import com.example.ui.components.MockupPlateCard
import com.example.ui.components.SectionDividerHeader
import com.example.ui.theme.NatsuRacingRed

@Composable
fun Block4ArquitecturaScreen(
    splitFraction: Float,
    onSplitChange: (Float) -> Unit,
    onOpenZoom: (MockupAsset) -> Unit,
    modifier: Modifier = Modifier
) {
    val blockAssets = remember { NatsuBrandRepository.assetsForBlock(ManualBlock.ARQUITECTURA) }

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
            item(key = "block4_header") {
                BlockHeroHeader(block = ManualBlock.ARQUITECTURA)
            }

            // Interactive Tool #3: Before & After Real Santa Cruz Storefront Comparator
            item(key = "facade_comparator") {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    SectionDividerHeader(
                        tag = "04.1 · Herramienta Interactiva",
                        title = stringResource(R.string.compare_title),
                        subtitle = stringResource(R.string.compare_subtitle)
                    )
                    FacadeBeforeAfterComparator(
                        beforeAsset = NatsuBrandRepository.facadeBeforeAsset,
                        afterAsset = NatsuBrandRepository.facadeAfterAsset,
                        splitFraction = splitFraction,
                        onSplitChange = onSplitChange,
                        onInspectAsset = onOpenZoom
                    )
                }
            }

            // Architectural & Fleet Engineering Cards
            item(key = "arch_specs") {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    SpatialSpecCard(
                        code = "ALUCOBOND + LED 3D",
                        title = "Sol Poniente & Noche",
                        body = "Paneles compuestos antracita resistentes al sol cruceño y rótulos LED 3D visibles en Av. Cristo Redentor y Radiales.",
                        modifier = Modifier.weight(1f)
                    )
                    SpatialSpecCard(
                        code = "ANILLOS CRUCEÑOS",
                        title = "Valla Móvil",
                        body = "Rotulación vehicular en antracita mate con diagonales rojas y marca blanca de lectura rápida en tráfico pesado.",
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item(key = "block4_plates_header") {
                SectionDividerHeader(
                    tag = "04.2 · Aplicaciones Espaciales",
                    title = "Flagship, Flota, Mostrador y Kiosco Digital",
                    subtitle = "Toca cualquier render arquitectónico o interior para explorarlo en pantalla completa."
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
private fun SpatialSpecCard(
    code: String,
    title: String,
    body: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = code,
                style = MaterialTheme.typography.labelSmall,
                color = NatsuRacingRed,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = body,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
