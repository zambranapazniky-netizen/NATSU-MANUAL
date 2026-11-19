package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.ZoomIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.R
import com.example.model.LogoMisuseRule
import com.example.model.ManualBlock
import com.example.model.MisuseType
import com.example.model.MockupAsset
import com.example.ui.theme.NatsuAnthracite
import com.example.ui.theme.NatsuPureWhite
import com.example.ui.theme.NatsuRacingRed

@Composable
fun BlockHeroHeader(
    block: ManualBlock,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    // Signature 22-degree NATSU speed bar accent along top-right edge
                    val slashPath = Path().apply {
                        moveTo(size.width * 0.72f, 0f)
                        lineTo(size.width, 0f)
                        lineTo(size.width, size.height * 0.36f)
                        lineTo(size.width * 0.84f, size.height * 0.36f)
                        close()
                    }
                    drawPath(slashPath, color = NatsuRacingRed.copy(alpha = 0.14f))
                    // Sharp solid Racing Red left structural bar
                    drawRect(
                        color = NatsuRacingRed,
                        size = androidx.compose.ui.geometry.Size(6.dp.toPx(), size.height)
                    )
                }
                .padding(start = 22.dp, end = 20.dp, top = 20.dp, bottom = 20.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Surface(
                        color = NatsuRacingRed,
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "BLOQUE ${block.numberCode}",
                            style = MaterialTheme.typography.labelMedium,
                            color = NatsuPureWhite,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                    Text(
                        text = block.kanjiTag,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Text(
                    text = block.fullTitle,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = block.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun SectionDividerHeader(
    tag: String,
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Slanted speed-bar micro motif
            Box(
                modifier = Modifier
                    .width(24.dp)
                    .height(6.dp)
                    .drawBehind {
                        val p = Path().apply {
                            moveTo(0f, 0f)
                            lineTo(size.width, 0f)
                            lineTo(size.width - 5.dp.toPx(), size.height)
                            lineTo(0f, size.height)
                            close()
                        }
                        drawPath(p, color = NatsuRacingRed)
                    }
            )
            Text(
                text = tag.uppercase(),
                style = MaterialTheme.typography.labelMedium,
                color = NatsuRacingRed,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        if (!subtitle.isNullOrBlank()) {
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun MockupPlateCard(
    asset: MockupAsset,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    aspectRatio: Float = 16f / 9f
) {
    val context = LocalContext.current
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("mockup_card_${asset.id}")
            .clickable(
                onClickLabel = stringResource(R.string.cd_open_zoom),
                onClick = onClick
            ),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(aspectRatio)
                    .background(NatsuAnthracite)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(asset.assetUri)
                        .crossfade(250)
                        .build(),
                    contentDescription = asset.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Top-left technical plate code badge
                Surface(
                    color = NatsuAnthracite.copy(alpha = 0.86f),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(10.dp)
                ) {
                    Text(
                        text = "${asset.plateCode} · ${asset.tag.uppercase()}",
                        style = MaterialTheme.typography.labelSmall,
                        color = NatsuPureWhite,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                // Bottom-right zoom affordance pill
                Surface(
                    color = NatsuRacingRed.copy(alpha = 0.92f),
                    shape = CircleShape,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(10.dp)
                        .size(36.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Outlined.ZoomIn,
                            contentDescription = stringResource(R.string.cd_open_zoom),
                            tint = NatsuPureWhite,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = asset.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = asset.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun LogoMisuseMatrix(
    rules: List<LogoMisuseRule>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        rules.chunked(2).forEach { pair ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                pair.forEach { rule ->
                    LogoMisuseCard(
                        rule = rule,
                        modifier = Modifier.weight(1f)
                    )
                }
                if (pair.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun LogoMisuseCard(
    rule: LogoMisuseRule,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column {
            val canvasBg = when (rule.type) {
                MisuseType.LOW_CONTRAST -> Color(0xFF441616)
                else -> Color(0xFFF3F3F5)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(104.dp)
                    .background(canvasBg)
                    .padding(14.dp),
                contentAlignment = Alignment.Center
            ) {
                when (rule.type) {
                    MisuseType.STRETCHED -> {
                        NatsuLogoVector(
                            natsuColor = NatsuRacingRed,
                            autoPartsColor = NatsuAnthracite,
                            modifier = Modifier
                                .fillMaxWidth(0.75f)
                                .graphicsLayer {
                                    scaleX = 1.42f
                                    scaleY = 0.62f
                                },
                            contentDesc = rule.title
                        )
                    }
                    MisuseType.DROP_SHADOW -> {
                        Box(contentAlignment = Alignment.Center) {
                            NatsuLogoVector(
                                natsuColor = Color.Black.copy(alpha = 0.45f),
                                autoPartsColor = Color.Black.copy(alpha = 0.45f),
                                modifier = Modifier
                                    .fillMaxWidth(0.84f)
                                    .offset(x = 5.dp, y = 5.dp),
                                contentDesc = ""
                            )
                            NatsuLogoVector(
                                natsuColor = NatsuRacingRed,
                                autoPartsColor = NatsuAnthracite,
                                modifier = Modifier.fillMaxWidth(0.84f),
                                contentDesc = rule.title
                            )
                        }
                    }
                    MisuseType.ROTATED -> {
                        NatsuLogoVector(
                            natsuColor = NatsuRacingRed,
                            autoPartsColor = NatsuAnthracite,
                            modifier = Modifier
                                .fillMaxWidth(0.74f)
                                .graphicsLayer { rotationZ = -18f },
                            contentDesc = rule.title
                        )
                    }
                    MisuseType.LOW_CONTRAST -> {
                        NatsuLogoVector(
                            natsuColor = Color(0xFF7A1515),
                            autoPartsColor = Color(0xFF632020),
                            modifier = Modifier.fillMaxWidth(0.84f),
                            contentDesc = rule.title
                        )
                    }
                    MisuseType.ALTERED_COLORS -> {
                        NatsuLogoVector(
                            natsuColor = Color(0xFFFF8A00),
                            speedBarColor = Color(0xFF0096C7),
                            autoPartsColor = Color(0xFF1E88E5),
                            modifier = Modifier.fillMaxWidth(0.84f),
                            contentDesc = rule.title
                        )
                    }
                    MisuseType.OUTLINED_STROKE -> {
                        NatsuLogoVector(
                            natsuColor = NatsuRacingRed,
                            autoPartsColor = NatsuAnthracite,
                            outlineOnly = true,
                            modifier = Modifier.fillMaxWidth(0.84f),
                            contentDesc = rule.title
                        )
                    }
                }

                // Prohibition X badge
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(NatsuRacingRed)
                        .border(1.5.dp, NatsuPureWhite, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        tint = NatsuPureWhite,
                        modifier = Modifier.size(15.dp)
                    )
                }
            }

            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Text(
                    text = rule.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = rule.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
