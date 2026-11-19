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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.NatsuBrandRepository
import com.example.model.ManualBlock
import com.example.model.MockupAsset
import com.example.ui.components.BlockHeroHeader
import com.example.ui.components.MockupPlateCard
import com.example.ui.components.SectionDividerHeader
import com.example.ui.theme.NatsuPureWhite
import com.example.ui.theme.NatsuRacingRed

@Composable
fun Block5CampanasScreen(
    onOpenZoom: (MockupAsset) -> Unit,
    onOpenFullGallery: () -> Unit,
    modifier: Modifier = Modifier
) {
    val blockAssets = remember { NatsuBrandRepository.assetsForBlock(ManualBlock.CAMPANAS) }

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
            item(key = "block5_header") {
                BlockHeroHeader(block = ManualBlock.CAMPANAS)
            }

            item(key = "campaign_rules") {
                SectionDividerHeader(
                    tag = "05.1 · Comunicación Comercial",
                    title = "Sistema de Campañas de Alto Impacto",
                    subtitle = "Disciplina estricta de Rojo Racing (#E60000), Antracita (#1E1E1E) y Blanco Puro (#FFFFFF) en vía pública, retail y medios digitales."
                )
            }

            items(blockAssets, key = { it.id }) { asset ->
                MockupPlateCard(
                    asset = asset,
                    onClick = { onOpenZoom(asset) }
                )
            }

            // Callout card to launch the complete curated gallery modal
            item(key = "full_gallery_cta") {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    color = MaterialTheme.colorScheme.surface,
                    border = BorderStroke(1.dp, NatsuRacingRed.copy(alpha = 0.55f))
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "ARCHIVO VISUAL COMPLETO",
                                style = MaterialTheme.typography.labelMedium,
                                color = NatsuRacingRed,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = "Explora las ${NatsuBrandRepository.allZoomableAssets.size} piezas curadas con filtro y zoom fullscreen",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Todas las piezas fueron seleccionadas por su fidelidad estricta al logotipo oficial NATSU AUTO PARTS y la paleta corporativa.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Button(
                            onClick = onOpenFullGallery,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = NatsuRacingRed,
                                contentColor = NatsuPureWhite
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("open_full_gallery_cta")
                        ) {
                            Icon(
                                imageVector = Icons.Filled.PhotoLibrary,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Abrir Galería Curada NATSU",
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }
        }
    }
}
