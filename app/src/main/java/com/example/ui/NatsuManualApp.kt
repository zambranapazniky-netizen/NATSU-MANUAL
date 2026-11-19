package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Architecture
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.outlined.Architecture
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Campaign
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material.icons.outlined.TextFields
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.R
import com.example.data.NatsuBrandRepository
import com.example.model.ManualBlock
import com.example.ui.components.CuratedGalleryOverlay
import com.example.ui.components.FullscreenZoomModal
import com.example.ui.components.NatsuLogoVector
import com.example.ui.screens.Block1FundamentosScreen
import com.example.ui.screens.Block2TipografiaScreen
import com.example.ui.screens.Block3PapeleriaScreen
import com.example.ui.screens.Block4ArquitecturaScreen
import com.example.ui.screens.Block5CampanasScreen
import com.example.ui.theme.NatsuManualTheme
import com.example.ui.theme.NatsuPureWhite
import com.example.ui.theme.NatsuRacingRed
import com.example.viewmodel.BrandManualViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NatsuManualApp(
    viewModel: BrandManualViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val clipboardManager = LocalClipboardManager.current

    val onCopyColorCode: (String, String) -> Unit = { label, value ->
        clipboardManager.setText(AnnotatedString(value))
        coroutineScope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(
                message = "Copiado · $label: $value",
                duration = SnackbarDuration.Short
            )
        }
    }

    // Back press on secondary blocks returns cleanly to Block 1 (Fundamentos)
    BackHandler(
        enabled = !uiState.isGallerySheetOpen &&
            uiState.zoomedAsset == null &&
            uiState.selectedBlock != ManualBlock.FUNDAMENTOS
    ) {
        viewModel.selectBlock(ManualBlock.FUNDAMENTOS)
    }

    NatsuManualTheme(darkTheme = uiState.isDarkMode) {
        Box(modifier = Modifier.fillMaxSize()) {
            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                val useNavigationRail = maxWidth >= 680.dp

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    contentWindowInsets = WindowInsets.safeDrawing,
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                    topBar = {
                        Column {
                            TopAppBar(
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.surface,
                                    titleContentColor = MaterialTheme.colorScheme.onSurface
                                ),
                                title = {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        NatsuLogoVector(
                                            natsuColor = NatsuRacingRed,
                                            speedBarColor = NatsuRacingRed,
                                            autoPartsColor = MaterialTheme.colorScheme.onSurface,
                                            modifier = Modifier.width(96.dp),
                                            contentDesc = stringResource(R.string.cd_natsu_logo)
                                        )
                                        Box(
                                            modifier = Modifier
                                                .width(1.dp)
                                                .height(22.dp)
                                                .background(MaterialTheme.colorScheme.outline)
                                        )
                                        Column {
                                            Text(
                                                text = "MANUAL 2026",
                                                style = MaterialTheme.typography.labelMedium,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                maxLines = 1
                                            )
                                            Text(
                                                text = stringResource(R.string.brand_kanji),
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                maxLines = 1
                                            )
                                        }
                                    }
                                },
                                actions = {
                                    IconButton(
                                        onClick = { viewModel.openGallery(uiState.selectedBlock) },
                                        modifier = Modifier
                                            .size(48.dp)
                                            .testTag("top_gallery_button")
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.PhotoLibrary,
                                            contentDescription = stringResource(R.string.cd_open_gallery),
                                            tint = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                    IconButton(
                                        onClick = { viewModel.toggleTheme() },
                                        modifier = Modifier
                                            .size(48.dp)
                                            .testTag("theme_toggle_button")
                                    ) {
                                        Icon(
                                            imageVector = if (uiState.isDarkMode) Icons.Outlined.LightMode
                                            else Icons.Outlined.DarkMode,
                                            contentDescription = stringResource(R.string.cd_toggle_theme),
                                            tint = if (uiState.isDarkMode) NatsuPureWhite else NatsuRacingRed
                                        )
                                    }
                                }
                            )
                            HorizontalDivider(
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.outlineVariant
                            )
                        }
                    },
                    bottomBar = {
                        if (!useNavigationRail) {
                            NavigationBar(
                                containerColor = MaterialTheme.colorScheme.surface,
                                tonalElevation = 0.dp
                            ) {
                                ManualBlock.entries.forEach { block ->
                                    val selected = uiState.selectedBlock == block
                                    NavigationBarItem(
                                        selected = selected,
                                        onClick = { viewModel.selectBlock(block) },
                                        modifier = Modifier.testTag("nav_block_${block.numberCode}"),
                                        icon = {
                                            Icon(
                                                imageVector = blockIcon(block, selected),
                                                contentDescription = block.fullTitle
                                            )
                                        },
                                        label = {
                                            Text(
                                                text = block.shortTitle,
                                                style = MaterialTheme.typography.labelSmall,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        },
                                        colors = NavigationBarItemDefaults.colors(
                                            selectedIconColor = NatsuPureWhite,
                                            selectedTextColor = NatsuRacingRed,
                                            indicatorColor = NatsuRacingRed,
                                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    )
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        if (useNavigationRail) {
                            NavigationRail(
                                containerColor = MaterialTheme.colorScheme.surface,
                                modifier = Modifier.fillMaxHeight()
                            ) {
                                Spacer(modifier = Modifier.height(8.dp))
                                ManualBlock.entries.forEach { block ->
                                    val selected = uiState.selectedBlock == block
                                    NavigationRailItem(
                                        selected = selected,
                                        onClick = { viewModel.selectBlock(block) },
                                        modifier = Modifier.testTag("rail_block_${block.numberCode}"),
                                        icon = {
                                            Icon(
                                                imageVector = blockIcon(block, selected),
                                                contentDescription = block.fullTitle
                                            )
                                        },
                                        label = {
                                            Text(
                                                text = block.shortTitle,
                                                style = MaterialTheme.typography.labelSmall,
                                                maxLines = 1
                                            )
                                        },
                                        colors = NavigationRailItemDefaults.colors(
                                            selectedIconColor = NatsuPureWhite,
                                            selectedTextColor = NatsuRacingRed,
                                            indicatorColor = NatsuRacingRed,
                                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    )
                                }
                            }
                        }

                        Crossfade(
                            targetState = uiState.selectedBlock,
                            label = "manual_block_crossfade",
                            modifier = Modifier.weight(1f)
                        ) { activeBlock ->
                            when (activeBlock) {
                                ManualBlock.FUNDAMENTOS -> Block1FundamentosScreen(
                                    uiState = uiState,
                                    onSelectBackdrop = viewModel::selectLogoBackdrop,
                                    onToggleGrid = viewModel::toggleConstructionGrid,
                                    onToggleSafeZone = viewModel::toggleSafeZone,
                                    onToggleKanji = viewModel::toggleKanjiLockup,
                                    onCopyColorCode = onCopyColorCode,
                                    onOpenZoom = viewModel::openZoomAsset
                                )
                                ManualBlock.TIPOGRAFIA -> Block2TipografiaScreen(
                                    onOpenZoom = viewModel::openZoomAsset
                                )
                                ManualBlock.PAPELERIA -> Block3PapeleriaScreen(
                                    onOpenZoom = viewModel::openZoomAsset
                                )
                                ManualBlock.ARQUITECTURA -> Block4ArquitecturaScreen(
                                    splitFraction = uiState.facadeSplitFraction,
                                    onSplitChange = viewModel::updateFacadeSplit,
                                    onOpenZoom = viewModel::openZoomAsset
                                )
                                ManualBlock.CAMPANAS -> Block5CampanasScreen(
                                    onOpenZoom = viewModel::openZoomAsset,
                                    onOpenFullGallery = { viewModel.openGallery(null) }
                                )
                            }
                        }
                    }
                }
            }

            // Curated Gallery Overlay (Tool #4A)
            if (uiState.isGallerySheetOpen) {
                CuratedGalleryOverlay(
                    allAssets = NatsuBrandRepository.allZoomableAssets,
                    selectedFilter = uiState.galleryFilterBlock,
                    onSelectFilter = viewModel::setGalleryFilter,
                    onSelectAsset = viewModel::openZoomAsset,
                    onDismiss = viewModel::closeGallery
                )
            }

            // Fullscreen Pinch-to-Zoom Viewer (Tool #4B)
            uiState.zoomedAsset?.let { zoomed ->
                FullscreenZoomModal(
                    asset = zoomed,
                    allAssets = NatsuBrandRepository.allZoomableAssets,
                    onSelectAsset = viewModel::openZoomAsset,
                    onDismiss = viewModel::closeZoomAsset
                )
            }
        }
    }
}

private fun blockIcon(block: ManualBlock, selected: Boolean): ImageVector = when (block) {
    ManualBlock.FUNDAMENTOS -> if (selected) Icons.Filled.AutoAwesome else Icons.Outlined.AutoAwesome
    ManualBlock.TIPOGRAFIA -> if (selected) Icons.Filled.TextFields else Icons.Outlined.TextFields
    ManualBlock.PAPELERIA -> if (selected) Icons.Filled.Inventory2 else Icons.Outlined.Inventory2
    ManualBlock.ARQUITECTURA -> if (selected) Icons.Filled.Architecture else Icons.Outlined.Architecture
    ManualBlock.CAMPANAS -> if (selected) Icons.Filled.Campaign else Icons.Outlined.Campaign
}
