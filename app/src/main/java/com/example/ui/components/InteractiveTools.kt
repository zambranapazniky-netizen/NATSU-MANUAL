package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.outlined.AspectRatio
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.material.icons.outlined.ZoomIn
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.R
import com.example.model.BrandColorSpec
import com.example.model.LogoBackdrop
import com.example.model.MockupAsset
import com.example.ui.theme.BlueprintCyanDark
import com.example.ui.theme.BlueprintCyanLight
import com.example.ui.theme.NatsuAnthracite
import com.example.ui.theme.NatsuMonoFontFamily
import com.example.ui.theme.NatsuPureWhite
import com.example.ui.theme.NatsuRacingRed
import kotlin.math.roundToInt

/**
 * Interactive Tool #1: Logo Inspector with 4 official backdrops, 22-degree construction blueprint
 * grid overlay, modular 1X clear-space zone, and optional Japanese Kanji lockup.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LogoInspectorCard(
    selectedBackdrop: LogoBackdrop,
    showConstructionGrid: Boolean,
    showSafeZone: Boolean,
    showKanjiLockup: Boolean,
    onSelectBackdrop: (LogoBackdrop) -> Unit,
    onToggleGrid: () -> Unit,
    onToggleSafeZone: () -> Unit,
    onToggleKanji: () -> Unit,
    modifier: Modifier = Modifier
) {
    val animatedBg by animateColorAsState(
        targetValue = selectedBackdrop.backgroundColor,
        label = "logo_stage_bg"
    )
    val animatedNatsu by animateColorAsState(
        targetValue = selectedBackdrop.natsuColor,
        label = "logo_natsu_color"
    )
    val animatedBar by animateColorAsState(
        targetValue = selectedBackdrop.speedBarColor,
        label = "logo_bar_color"
    )
    val animatedAuto by animateColorAsState(
        targetValue = selectedBackdrop.autoPartsColor,
        label = "logo_auto_color"
    )
    val gridAccent = if (selectedBackdrop.isDarkSurface) BlueprintCyanDark else BlueprintCyanLight
    val safeAccent = if (selectedBackdrop == LogoBackdrop.RACING_RED) NatsuPureWhite else NatsuRacingRed

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Stage Preview
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(animatedBg)
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.outlineVariant,
                        MaterialTheme.shapes.medium
                    )
                    .padding(horizontal = 20.dp, vertical = 18.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    NatsuLogoVector(
                        natsuColor = animatedNatsu,
                        speedBarColor = animatedBar,
                        autoPartsColor = animatedAuto,
                        showConstructionGrid = showConstructionGrid,
                        showSafeZone = showSafeZone,
                        gridColor = gridAccent,
                        safeZoneColor = safeAccent,
                        modifier = Modifier
                            .fillMaxWidth(0.88f)
                            .weight(1f, fill = false)
                    )
                    if (showKanjiLockup) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "${stringResource(R.string.brand_kanji)}  ·  JIDŌSHA BUHIN",
                            style = MaterialTheme.typography.labelMedium,
                            color = selectedBackdrop.kanjiColor.copy(alpha = 0.88f),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // Technical HUD corner badges
                Surface(
                    color = if (selectedBackdrop.isDarkSurface) Color.Black.copy(alpha = 0.45f)
                    else Color.White.copy(alpha = 0.78f),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.align(Alignment.TopStart)
                ) {
                    Text(
                        text = selectedBackdrop.subtitle,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (selectedBackdrop.isDarkSurface) NatsuPureWhite else NatsuAnthracite,
                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                    )
                }

                if (showConstructionGrid || showSafeZone) {
                    Surface(
                        color = if (selectedBackdrop.isDarkSurface) Color.Black.copy(alpha = 0.52f)
                        else Color.White.copy(alpha = 0.85f),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.align(Alignment.BottomEnd)
                    ) {
                        val badge = buildString {
                            if (showConstructionGrid) append("ÁNGULO 22° · RATIO 3.18:1")
                            if (showConstructionGrid && showSafeZone) append("  |  ")
                            if (showSafeZone) append("MARGEN 1X")
                        }
                        Text(
                            text = badge,
                            style = MaterialTheme.typography.labelSmall,
                            color = gridAccent,
                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                        )
                    }
                }
            }

            // 4 Official Backdrops Selector
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "VERSIONES CROMÁTICAS OFICIALES",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    LogoBackdrop.entries.forEach { backdrop ->
                        val isSelected = backdrop == selectedBackdrop
                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .height(52.dp)
                                .testTag("inspector_bg_${backdrop.id}")
                                .clickable { onSelectBackdrop(backdrop) },
                            shape = MaterialTheme.shapes.small,
                            color = if (isSelected) MaterialTheme.colorScheme.primaryContainer
                            else MaterialTheme.colorScheme.surfaceVariant,
                            border = BorderStroke(
                                width = if (isSelected) 2.dp else 1.dp,
                                color = if (isSelected) NatsuRacingRed
                                else MaterialTheme.colorScheme.outlineVariant
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 6.dp, vertical = 6.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(16.dp)
                                        .clip(CircleShape)
                                        .background(backdrop.backgroundColor)
                                        .border(1.dp, MaterialTheme.colorScheme.outline, CircleShape)
                                )
                                Spacer(modifier = Modifier.height(3.dp))
                                Text(
                                    text = backdrop.label,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer
                                    else MaterialTheme.colorScheme.onSurfaceVariant,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }

            // Blueprint & Safe Zone Toggles
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = showConstructionGrid,
                    onClick = onToggleGrid,
                    modifier = Modifier.testTag("inspector_toggle_grid"),
                    label = { Text(stringResource(R.string.inspector_toggle_grid)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.GridOn,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = NatsuRacingRed,
                        selectedLabelColor = NatsuPureWhite,
                        selectedLeadingIconColor = NatsuPureWhite
                    )
                )
                FilterChip(
                    selected = showSafeZone,
                    onClick = onToggleSafeZone,
                    modifier = Modifier.testTag("inspector_toggle_safe"),
                    label = { Text(stringResource(R.string.inspector_toggle_safe)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.AspectRatio,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = NatsuRacingRed,
                        selectedLabelColor = NatsuPureWhite,
                        selectedLeadingIconColor = NatsuPureWhite
                    )
                )
                FilterChip(
                    selected = showKanjiLockup,
                    onClick = onToggleKanji,
                    modifier = Modifier.testTag("inspector_toggle_kanji"),
                    label = { Text(stringResource(R.string.inspector_toggle_kanji)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Translate,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = NatsuRacingRed,
                        selectedLabelColor = NatsuPureWhite,
                        selectedLeadingIconColor = NatsuPureWhite
                    )
                )
            }

            // Minimum Size Technical Spec Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                SpecPill(
                    label = stringResource(R.string.inspector_min_print),
                    modifier = Modifier.weight(1f)
                )
                SpecPill(
                    label = stringResource(R.string.inspector_min_digital),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun SpecPill(label: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
        )
    }
}

/**
 * Interactive Tool #2: Official Color Palette Swatches with one-tap clipboard copy for
 * HEX, RGB, and CMYK specifications.
 */
@Composable
fun InteractiveColorPaletteSection(
    colors: List<BrandColorSpec>,
    onCopyValue: (label: String, value: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        colors.forEach { spec ->
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    // Pantone-style solid color band
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(82.dp)
                            .background(spec.color)
                            .border(
                                width = if (spec.color == NatsuPureWhite) 1.dp else 0.dp,
                                color = MaterialTheme.colorScheme.outlineVariant
                            )
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Column(modifier = Modifier.align(Alignment.BottomStart)) {
                            Text(
                                text = spec.name,
                                style = MaterialTheme.typography.titleMedium,
                                color = spec.onSwatchColor,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = spec.role,
                                style = MaterialTheme.typography.bodySmall,
                                color = spec.onSwatchColor.copy(alpha = 0.85f)
                            )
                        }
                        Surface(
                            color = if (spec.onSwatchColor == NatsuPureWhite) Color.Black.copy(alpha = 0.28f)
                            else Color.Black.copy(alpha = 0.08f),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier.align(Alignment.TopEnd)
                        ) {
                            Text(
                                text = spec.pantone,
                                style = MaterialTheme.typography.labelSmall,
                                color = spec.onSwatchColor,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    // Tap-to-copy tokens + justification
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            CopyCodeChip(
                                title = "HEX",
                                value = spec.hex,
                                testTag = "copy_hex_${spec.id}",
                                onClick = { onCopyValue("${spec.name} HEX", spec.hex) },
                                modifier = Modifier.weight(1f)
                            )
                            CopyCodeChip(
                                title = "RGB",
                                value = spec.rgb,
                                testTag = "copy_rgb_${spec.id}",
                                onClick = { onCopyValue("${spec.name} RGB", "rgb(${spec.rgb})") },
                                modifier = Modifier.weight(1f)
                            )
                            CopyCodeChip(
                                title = "CMYK",
                                value = spec.cmyk,
                                testTag = "copy_cmyk_${spec.id}",
                                onClick = { onCopyValue("${spec.name} CMYK", "CMYK ${spec.cmyk}") },
                                modifier = Modifier.weight(1.2f)
                            )
                        }
                        Text(
                            text = spec.justification,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CopyCodeChip(
    title: String,
    value: String,
    testTag: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .height(54.dp)
            .testTag(testTag)
            .clickable(
                onClickLabel = stringResource(R.string.cd_copy_code),
                onClick = onClick
            ),
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 6.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelSmall,
                    color = NatsuRacingRed,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Outlined.ContentCopy,
                    contentDescription = stringResource(R.string.cd_copy_code),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(13.dp)
                )
            }
            Text(
                text = value,
                style = MaterialTheme.typography.labelSmall.copy(fontFamily = NatsuMonoFontFamily),
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )
        }
    }
}

/**
 * Interactive Tool #3: Before & After Real Santa Cruz Storefront Split Comparator.
 */
@Composable
fun FacadeBeforeAfterComparator(
    beforeAsset: MockupAsset,
    afterAsset: MockupAsset,
    splitFraction: Float,
    onSplitChange: (Float) -> Unit,
    onInspectAsset: (MockupAsset) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    val sliderDesc = stringResource(R.string.cd_compare_slider)

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 10f)
                    .clip(MaterialTheme.shapes.medium)
                    .background(NatsuAnthracite)
                    .testTag("facade_compare_stage")
                    .semantics { contentDescription = sliderDesc }
                    .pointerInput(Unit) {
                        detectTapGestures { offset ->
                            if (size.width > 0) {
                                onSplitChange(offset.x / size.width.toFloat())
                            }
                        }
                    }
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures { change, _ ->
                            change.consume()
                            if (size.width > 0) {
                                onSplitChange(change.position.x / size.width.toFloat())
                            }
                        }
                    }
            ) {
                val fullWidthPx = with(density) { maxWidth.toPx() }
                val splitX = (fullWidthPx * splitFraction).coerceIn(0f, fullWidthPx)
                val splitDp = with(density) { splitX.toDp() }

                // Base layer: AFTER (Transformed NATSU Flagship)
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(afterAsset.assetUri)
                        .crossfade(false)
                        .build(),
                    contentDescription = afterAsset.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Clipped Left layer: BEFORE (Real Original Santa Cruz Storefront)
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(splitDp)
                        .clipToBounds()
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(beforeAsset.assetUri)
                            .crossfade(false)
                            .build(),
                        contentDescription = beforeAsset.title,
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.CenterStart,
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(this@BoxWithConstraints.maxWidth)
                    )
                }

                // Vertical Racing Red divider bar
                Box(
                    modifier = Modifier
                        .offset { IntOffset(splitX.roundToInt() - 2, 0) }
                        .width(3.dp)
                        .fillMaxHeight()
                        .background(NatsuRacingRed)
                )

                // Draggable circular handle
                val handleRadiusPx = with(density) { 22.dp.toPx() }
                Surface(
                    color = NatsuRacingRed,
                    shape = CircleShape,
                    border = BorderStroke(2.dp, NatsuPureWhite),
                    shadowElevation = 6.dp,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .offset { IntOffset((splitX - handleRadiusPx).roundToInt(), 0) }
                        .size(44.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Filled.CompareArrows,
                            contentDescription = null,
                            tint = NatsuPureWhite,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }

                // Corner Labels
                Surface(
                    color = NatsuAnthracite.copy(alpha = 0.84f),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.compare_before),
                        style = MaterialTheme.typography.labelSmall,
                        color = NatsuPureWhite,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Surface(
                    color = NatsuRacingRed.copy(alpha = 0.90f),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.compare_after),
                        style = MaterialTheme.typography.labelSmall,
                        color = NatsuPureWhite,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            // Quick position presets + Fullscreen inspect actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ComparePresetChip(
                    label = "Original",
                    selected = splitFraction > 0.85f,
                    testTag = "compare_preset_before",
                    onClick = { onSplitChange(0.95f) },
                    modifier = Modifier.weight(1f)
                )
                ComparePresetChip(
                    label = "50 / 50",
                    selected = splitFraction in 0.4f..0.6f,
                    testTag = "compare_preset_mid",
                    onClick = { onSplitChange(0.5f) },
                    modifier = Modifier.weight(1f)
                )
                ComparePresetChip(
                    label = "NATSU LED",
                    selected = splitFraction < 0.15f,
                    testTag = "compare_preset_after",
                    onClick = { onSplitChange(0.05f) },
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = { onInspectAsset(beforeAsset) },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .testTag("zoom_facade_before")
                ) {
                    Icon(Icons.Outlined.ZoomIn, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Zoom Antes", style = MaterialTheme.typography.labelLarge)
                }
                OutlinedButton(
                    onClick = { onInspectAsset(afterAsset) },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .testTag("zoom_facade_after")
                ) {
                    Icon(Icons.Outlined.ZoomIn, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Zoom Después", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

@Composable
private fun ComparePresetChip(
    label: String,
    selected: Boolean,
    testTag: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .height(44.dp)
            .testTag(testTag)
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.small,
        color = if (selected) NatsuRacingRed else MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(
            1.dp,
            if (selected) NatsuRacingRed else MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                color = if (selected) NatsuPureWhite else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
