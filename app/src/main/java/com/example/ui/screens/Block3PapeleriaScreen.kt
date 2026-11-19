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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.NatsuBrandRepository
import com.example.model.ManualBlock
import com.example.model.MockupAsset
import com.example.ui.components.BlockHeroHeader
import com.example.ui.components.MockupPlateCard
import com.example.ui.components.SectionDividerHeader
import com.example.ui.theme.NatsuRacingRed

@Composable
fun Block3PapeleriaScreen(
    onOpenZoom: (MockupAsset) -> Unit,
    modifier: Modifier = Modifier
) {
    val blockAssets = remember { NatsuBrandRepository.assetsForBlock(ManualBlock.PAPELERIA) }

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
            item(key = "block3_header") {
                BlockHeroHeader(block = ManualBlock.PAPELERIA)
            }

            // Practical Santa Cruz tactile production guidelines
            item(key = "tactile_specs") {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    SectionDividerHeader(
                        tag = "03.1 · Producción Táctil",
                        title = "Estándares de Indumentaria y Empaque",
                        subtitle = "Soluciones pensadas para el clima tropical de Santa Cruz y depósitos de alta rotación."
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        TactileSpecCard(
                            code = "DRY-FIT SCZ",
                            title = "Clima Cálido",
                            body = "Polos en piqué microperforado antracita con vivos Rojo Racing y bordado blanco de alta densidad.",
                            modifier = Modifier.weight(1f)
                        )
                        TactileSpecCard(
                            code = "1 TINTA KRAFT",
                            title = "Depósitos",
                            body = "Flexografía plana sobre cartón corrugado, micrograbado metálico y cinta de seguridad corporativa.",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            item(key = "block3_plates_header") {
                SectionDividerHeader(
                    tag = "03.2 · Ecosistema Curado",
                    title = "Papelería, Uniformes, Merch y Empaques",
                    subtitle = "10 aplicaciones coherentes con el sistema Rojo Racing, Antracita y Blanco."
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
private fun TactileSpecCard(
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
