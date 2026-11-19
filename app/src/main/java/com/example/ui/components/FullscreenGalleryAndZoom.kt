package com.example.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.outlined.ZoomIn
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
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
import com.example.model.ManualBlock
import com.example.model.MockupAsset
import com.example.ui.theme.NatsuAnthracite
import com.example.ui.theme.NatsuPureWhite
import com.example.ui.theme.NatsuRacingRed
import kotlin.math.roundToInt

/**
 * Interactive Tool #4A: Filterable Curated Gallery Overlay showing all coherent NATSU mockups.
 */
@Composable
fun CuratedGalleryOverlay(
    allAssets: List<MockupAsset>,
    selectedFilter: ManualBlock?,
    onSelectFilter: (ManualBlock?) -> Unit,
    onSelectAsset: (MockupAsset) -> Unit,
    onDismiss: () -> Unit
) {
    BackHandler(onBack = onDismiss)

    val filtered = remember(allAssets, selectedFilter) {
        if (selectedFilter == null) allAssets else allAssets.filter { it.block == selectedFilter }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {
            // Top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(R.string.label_gallery_title),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "${filtered.size} piezas curadas · Rojo Racing y Antracita",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .size(48.dp)
                        .testTag("close_gallery_button")
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(R.string.cd_close_modal),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            // Block Filter Row
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    FilterChip(
                        selected = selectedFilter == null,
                        onClick = { onSelectFilter(null) },
                        modifier = Modifier.testTag("gallery_filter_all"),
                        label = { Text("${stringResource(R.string.label_filter_all)} (${allAssets.size})") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = NatsuRacingRed,
                            selectedLabelColor = NatsuPureWhite
                        )
                    )
                }
                items(ManualBlock.entries) { block ->
                    FilterChip(
                        selected = selectedFilter == block,
                        onClick = { onSelectFilter(block) },
                        modifier = Modifier.testTag("gallery_filter_${block.numberCode}"),
                        label = { Text("${block.numberCode} ${block.shortTitle}") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = NatsuRacingRed,
                            selectedLabelColor = NatsuPureWhite
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Responsive Grid
            BoxWithConstraints(modifier = Modifier.weight(1f)) {
                val columns = if (maxWidth >= 700.dp) 3 else 2
                LazyVerticalGrid(
                    columns = GridCells.Fixed(columns),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filtered, key = { it.id }) { asset ->
                        GalleryThumbCard(
                            asset = asset,
                            onClick = { onSelectAsset(asset) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun GalleryThumbCard(
    asset: MockupAsset,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("gallery_thumb_${asset.id}")
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 10f)
                    .background(NatsuAnthracite)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(asset.assetUri)
                        .crossfade(200)
                        .build(),
                    contentDescription = asset.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Surface(
                    color = NatsuAnthracite.copy(alpha = 0.85f),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(6.dp)
                ) {
                    Text(
                        text = asset.plateCode,
                        style = MaterialTheme.typography.labelSmall,
                        color = NatsuPureWhite,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(6.dp)
                        .size(28.dp)
                        .background(NatsuRacingRed.copy(alpha = 0.9f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ZoomIn,
                        contentDescription = null,
                        tint = NatsuPureWhite,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = asset.title,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Bloque ${asset.block.numberCode} · ${asset.tag}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1
                )
            }
        }
    }
}

/**
 * Interactive Tool #4B: Fullscreen Pinch-to-Zoom & Pan Image Viewer with double-tap zoom,
 * reset button, and sequential navigation across curated plates.
 */
@Composable
fun FullscreenZoomModal(
    asset: MockupAsset,
    allAssets: List<MockupAsset>,
    onSelectAsset: (MockupAsset) -> Unit,
    onDismiss: () -> Unit
) {
    BackHandler(onBack = onDismiss)

    val context = LocalContext.current
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    LaunchedEffect(asset.id) {
        scale = 1f
        offset = Offset.Zero
    }

    val currentIndex = remember(asset.id, allAssets) {
        allAssets.indexOfFirst { it.id == asset.id }.coerceAtLeast(0)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xF50A0A0C))
            .statusBarsPadding()
            .navigationBarsPadding()
            .testTag("fullscreen_zoom_modal")
    ) {
        // Zoomable Image Canvas
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(asset.id) {
                    detectTapGestures(
                        onDoubleTap = {
                            if (scale > 1.2f) {
                                scale = 1f
                                offset = Offset.Zero
                            } else {
                                scale = 2.6f
                            }
                        }
                    )
                }
                .pointerInput(asset.id) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        val newScale = (scale * zoom).coerceIn(1f, 4.5f)
                        scale = newScale
                        if (newScale <= 1.01f) {
                            offset = Offset.Zero
                        } else {
                            val maxOffsetX = (size.width * (newScale - 1f)) / 2f
                            val maxOffsetY = (size.height * (newScale - 1f)) / 2f
                            offset = Offset(
                                x = (offset.x + pan.x).coerceIn(-maxOffsetX, maxOffsetX),
                                y = (offset.y + pan.y).coerceIn(-maxOffsetY, maxOffsetY)
                            )
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(asset.assetUri)
                    .crossfade(150)
                    .build(),
                contentDescription = asset.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        translationX = offset.x
                        translationY = offset.y
                    }
            )
        }

        // Top HUD Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .background(Color.Black.copy(alpha = 0.65f))
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    color = NatsuRacingRed,
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = asset.plateCode,
                        style = MaterialTheme.typography.labelMedium,
                        color = NatsuPureWhite,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Text(
                    text = "${(scale * 100).roundToInt()}%",
                    style = MaterialTheme.typography.labelMedium,
                    color = NatsuPureWhite
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (scale > 1.05f) {
                    IconButton(
                        onClick = {
                            scale = 1f
                            offset = Offset.Zero
                        },
                        modifier = Modifier
                            .size(48.dp)
                            .testTag("zoom_reset_button")
                    ) {
                        Icon(
                            imageVector = Icons.Filled.RestartAlt,
                            contentDescription = stringResource(R.string.cd_reset_zoom),
                            tint = NatsuPureWhite
                        )
                    }
                }
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .size(48.dp)
                        .testTag("zoom_close_button")
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(R.string.cd_close_modal),
                        tint = NatsuPureWhite
                    )
                }
            }
        }

        // Bottom Caption + Prev/Next Bar
        Surface(
            color = Color.Black.copy(alpha = 0.78f),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = asset.title,
                            style = MaterialTheme.typography.titleMedium,
                            color = NatsuPureWhite
                        )
                        Text(
                            text = asset.subtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFD0D0D8),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        IconButton(
                            onClick = {
                                val prev = if (currentIndex > 0) currentIndex - 1 else allAssets.lastIndex
                                onSelectAsset(allAssets[prev])
                            },
                            modifier = Modifier
                                .size(48.dp)
                                .testTag("zoom_prev_button")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Lámina anterior",
                                tint = NatsuPureWhite
                            )
                        }
                        IconButton(
                            onClick = {
                                val next = if (currentIndex < allAssets.lastIndex) currentIndex + 1 else 0
                                onSelectAsset(allAssets[next])
                            },
                            modifier = Modifier
                                .size(48.dp)
                                .testTag("zoom_next_button")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "Lámina siguiente",
                                tint = NatsuPureWhite
                            )
                        }
                    }
                }
                Text(
                    text = "${currentIndex + 1} / ${allAssets.size}  ·  ${stringResource(R.string.label_zoom_hint)}",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF9E9EA8)
                )
            }
        }
    }
}
