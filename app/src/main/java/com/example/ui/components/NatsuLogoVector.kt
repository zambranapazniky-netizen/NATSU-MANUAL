package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.example.ui.theme.NatsuPureWhite
import com.example.ui.theme.NatsuRacingRed

/**
 * Exact vector path definitions extracted directly from the official NATSU AUTO PARTS SVG
 * (`LOGOS_NATSU-01_87c5ba05.svg`), normalized to origin (0, 0) within a 1020 x 320 coordinate box.
 * Original SVG bounding box: X [458.61 .. 1476.83] (w = 1018.22), Y [408.0 .. 726.49] (h = 318.49).
 */
private object NatsuOfficialPaths {
    const val BASE_WIDTH = 1020f
    const val BASE_HEIGHT = 322f
    const val OFFSET_X = -458.61f
    const val OFFSET_Y = -407.0f

    val natsuLetterPaths: List<String> = listOf(
        // N
        "M569.54,577.19c7.72,-1.8 14.16,-6.43 18.53,-12.61v-0.77c0,-10.55 -5.66,-20.85 -15.7,-26c-22.91,-11.84 -47.62,-24.19 -71.55,-36.29v114.79h-42.21v-185.06h42.21v22.91l90.6,46.59l2.06,1.29l-0.51,-71.04h45.04v116.08c0,30.89 -18.79,57.65 -45.81,69.24l-22.65,-39.12Z",
        // A
        "M782.4,545.28h-62.29c-5.15,37.06 -18.79,63.57 -48.65,71.55l-18.02,-37.58c19.3,-9.78 26.77,-39.89 26.77,-72.33c0,-11.07 -0.77,-22.65 -2.32,-33.46h-24.45v-41.95h71.81l-2.32,-21.36h56.62l-2.06,21.36h71.81v41.95h-24.71c-1.54,10.81 -2.32,22.13 -2.32,33.2c0,32.69 7.46,62.8 27.03,72.58l-18.02,37.58c-30.11,-7.98 -43.76,-34.49 -48.9,-71.55ZM780.85,473.47h-59.2c0.51,10.29 0.77,20.08 0.77,29.6h57.65c0,-9.52 0.26,-19.3 0.77,-29.6Z",
        // T
        "M939.66,548.62v-74.38h-31.14v14.93h-43.76v-57.14h195.87v57.14h-43.76v-14.93h-32.69l-0.51,74.38c0,14.41 10.29,26.77 23.94,30.11l-22.39,38.87c-26.77,-11.58 -45.56,-38.09 -45.56,-68.98Z",
        // S
        "M1094.36,617.6v-43.75h103.98v-0.52c14.93,-0.51 27.28,-12.1 29.08,-26.77h-129.21v-70.01h-22.14v-42.21h72.07l-2.32,-23.94h56.62l-2.57,23.94h72.33v42.21h-131.78v27.8h131.27v13.38h0.26v24.97c0,41.18 -33.72,74.9 -74.9,74.9h-102.7Z",
        // U
        "M1377.74,616.83c-41.18,0 -74.9,-33.72 -74.9,-74.9v-67.69h-15.19v-42.21h59.2v109.9c0,16.73 13.9,30.63 30.89,30.63h54.82l-0.26,-140.53h44.53v184.8h-99.09Z"
    )

    const val speedBarPath: String =
        "M458.61,726.49 L778.63,726.49 L819.91,655.32 L458.61,655.32 Z"

    val autoPartsPaths: List<String> = listOf(
        "M845.25,690.91l-3.46,-12.81l-14.44,24.81l43.31,-21.55l-20.54,26.84l-36.4,18.3h-19.32l41.28,-71.17h19.32l7.52,27.25l-17.28,8.34ZM855.01,726.49l-4.27,-14.13l14.64,-18.91l8.95,33.04h-19.32Z",
        "M944.68,708.19c-0.41,2.71 -1.2,5.2 -2.39,7.47c-1.19,2.27 -2.69,4.2 -4.52,5.79c-1.83,1.59 -3.91,2.83 -6.25,3.71c-2.34,0.88 -4.9,1.32 -7.68,1.32h-28.77c-2.71,0 -5.13,-0.44 -7.27,-1.32c-2.14,-0.88 -3.9,-2.12 -5.29,-3.71c-1.39,-1.59 -2.37,-3.52 -2.95,-5.79c-0.58,-2.27 -0.66,-4.76 -0.25,-7.47l7.42,-52.87h18.3l-6.41,45.96c-0.48,3.32 0.02,5.98 1.47,7.98c1.46,2 3.68,3 6.66,3h9.15c2.98,0 5.52,-1 7.63,-3c2.1,-2 3.39,-4.66 3.86,-7.98l6.41,-45.96h18.3l-7.42,52.87Z",
        "M955.97,655.32h66.09l-10.07,14.23h-15.86l-7.93,56.94h-18.3l7.93,-56.94h-11.79l-10.06,-14.23Z",
        "M1022.97,673.62c0.41,-2.78 1.2,-5.29 2.39,-7.52c1.18,-2.24 2.69,-4.15 4.52,-5.75c1.83,-1.59 3.93,-2.83 6.3,-3.71c2.37,-0.88 4.91,-1.32 7.62,-1.32h36.3c2.78,0 5.22,0.44 7.32,1.32c2.1,0.88 3.85,2.12 5.24,3.71c1.39,1.59 2.37,3.51 2.95,5.75c0.58,2.24 0.66,4.75 0.25,7.52l-4.88,34.57c-0.41,2.71 -1.2,5.2 -2.39,7.47c-1.19,2.27 -2.69,4.2 -4.52,5.79c-1.83,1.59 -3.91,2.83 -6.25,3.71c-2.34,0.88 -4.9,1.32 -7.68,1.32h-36.3c-2.71,0 -5.13,-0.44 -7.27,-1.32c-2.13,-0.88 -3.9,-2.12 -5.29,-3.71c-1.39,-1.59 -2.37,-3.52 -2.95,-5.79c-0.58,-2.27 -0.66,-4.76 -0.25,-7.47l4.88,-34.57ZM1037.41,701.28c-0.47,3.32 0.02,5.98 1.47,7.98c1.46,2 3.68,3 6.66,3h16.67c2.98,0 5.52,-1 7.63,-3c2.1,-2 3.39,-4.66 3.86,-7.98l2.85,-20.74c0.47,-3.32 -0.04,-5.98 -1.53,-7.98c-1.49,-2 -3.73,-3 -6.71,-3h-16.67c-2.98,0 -5.51,1 -7.57,3c-2.07,2 -3.34,4.66 -3.81,7.98l-2.85,20.74Z",
        "M1132.78,655.32h42.6c7.59,0 13.06,2.15 16.42,6.46c3.36,4.3 4.49,10.32 3.41,18.05c-0.61,4.07 -1.8,7.71 -3.56,10.93c-1.76,3.22 -3.98,5.95 -6.66,8.18c-2.68,2.24 -5.74,3.95 -9.2,5.13c-3.46,1.19 -7.19,1.78 -11.18,1.78h-20.64l-2.85,20.64h-18.3l9.96,-71.17ZM1145.99,691.62h20.33c1.63,0 3.05,-0.32 4.27,-0.97c1.22,-0.64 2.27,-1.49 3.15,-2.54c0.88,-1.05 1.58,-2.25 2.08,-3.61c0.51,-1.36 0.86,-2.78 1.07,-4.27c0.13,-1.15 0.19,-2.34 0.15,-3.56c-0.03,-1.22 -0.27,-2.37 -0.71,-3.46c-0.44,-1.08 -1.12,-1.96 -2.03,-2.64c-0.92,-0.68 -2.15,-1.02 -3.71,-1.02h-21.55l-3.05,22.06Z",
        "M1234.75,690.91l-3.46,-12.81l-14.44,24.81l43.31,-21.55l-20.54,26.84l-36.4,18.3h-19.32l41.28,-71.17h19.32l7.52,27.25l-17.28,8.34ZM1244.51,726.49l-4.27,-14.13l14.64,-18.91l8.95,33.04h-19.32Z",
        "M1276.95,655.32h41.48c6.51,0 11.4,1.54 14.69,4.63c3.29,3.08 4.93,7.41 4.93,12.96c0,4.27 -0.75,8.12 -2.24,11.54c-1.49,3.42 -3.56,6.35 -6.2,8.79s-5.76,4.3 -9.35,5.59c-3.59,1.29 -7.49,1.93 -11.69,1.93h-19.72l-3.56,25.72h-18.3l9.96,-71.17ZM1290.88,686.53h18.91c3.05,0 5.32,-0.88 6.81,-2.64c1.49,-1.76 2.41,-3.79 2.75,-6.1c0.14,-0.88 0.17,-1.8 0.1,-2.75c-0.07,-0.95 -0.32,-1.83 -0.76,-2.64c-0.44,-0.81 -1.09,-1.49 -1.93,-2.03c-0.85,-0.54 -2.02,-0.81 -3.51,-0.81h-20.03l-2.34,16.98ZM1319.35,703.31l15.56,23.18h-21.25l-15.35,-23.18h21.05Z",
        "M1342.93,655.32h66.09l-10.07,14.23h-15.86l-7.93,56.94h-18.3l7.93,-56.94h-11.79l-10.07,-14.23Z",
        "M1443.89,683.99c5.22,0 9.62,0.39 13.22,1.17c3.59,0.78 6.49,1.9 8.69,3.36c2.2,1.46 3.78,3.27 4.73,5.44c0.95,2.17 1.42,4.64 1.42,7.42c0,3.05 -0.46,6.08 -1.37,9.1c-0.92,3.02 -2.27,5.71 -4.07,8.08c-1.8,2.37 -4,4.29 -6.61,5.75c-2.61,1.46 -5.61,2.19 -9,2.19h-48.8l10.07,-14.23h33.55c2.58,0 4.46,-0.75 5.64,-2.24c1.19,-1.49 1.92,-3.19 2.19,-5.08c0.34,-2.37 -0.51,-4.08 -2.54,-5.13c-2.03,-1.05 -5.08,-1.58 -9.15,-1.58h-9.46c-7.52,0 -13.01,-1.51 -16.47,-4.52c-3.46,-3.01 -5.19,-7.3 -5.19,-12.86c0,-2.91 0.44,-5.88 1.32,-8.9c0.88,-3.02 2.17,-5.76 3.86,-8.24c1.69,-2.47 3.79,-4.49 6.3,-6.05c2.51,-1.56 5.39,-2.34 8.64,-2.34h45.96l-10.07,14.23h-27.86c-1.29,0 -2.49,0.2 -3.61,0.61c-1.12,0.41 -2.1,0.97 -2.95,1.68c-0.85,0.71 -1.54,1.53 -2.08,2.44c-0.54,0.92 -0.88,1.85 -1.02,2.8c-0.34,2.44 0.51,4.2 2.54,5.29c2.03,1.08 5.08,1.63 9.15,1.63h2.95Z"
    )

    fun parsePath(data: String): Path = PathParser().parsePathString(data).toPath()
}

@Composable
fun NatsuLogoVector(
    modifier: Modifier = Modifier,
    natsuColor: Color = NatsuRacingRed,
    speedBarColor: Color = natsuColor,
    autoPartsColor: Color = NatsuPureWhite,
    outlineOnly: Boolean = false,
    showConstructionGrid: Boolean = false,
    showSafeZone: Boolean = false,
    gridColor: Color = Color(0xFF00D4FF),
    safeZoneColor: Color = Color(0xFFFF3B3B),
    contentDesc: String = "Logotipo oficial NATSU AUTO PARTS"
) {
    val parsedNatsu = remember { NatsuOfficialPaths.natsuLetterPaths.map { NatsuOfficialPaths.parsePath(it) } }
    val parsedBar = remember { NatsuOfficialPaths.parsePath(NatsuOfficialPaths.speedBarPath) }
    val parsedAuto = remember { NatsuOfficialPaths.autoPartsPaths.map { NatsuOfficialPaths.parsePath(it) } }

    Canvas(
        modifier = modifier
            .aspectRatio(NatsuOfficialPaths.BASE_WIDTH / NatsuOfficialPaths.BASE_HEIGHT)
            .semantics { contentDescription = contentDesc }
    ) {
        val marginFactor = if (showSafeZone) 0.16f else 0.04f
        val availW = size.width * (1f - marginFactor * 2f)
        val availH = size.height * (1f - marginFactor * 2f)
        val scale = minOf(
            availW / NatsuOfficialPaths.BASE_WIDTH,
            availH / NatsuOfficialPaths.BASE_HEIGHT
        )
        val logoW = NatsuOfficialPaths.BASE_WIDTH * scale
        val logoH = NatsuOfficialPaths.BASE_HEIGHT * scale
        val left = (size.width - logoW) / 2f
        val top = (size.height - logoH) / 2f

        if (showConstructionGrid) {
            drawConstructionOverlay(
                left = left,
                top = top,
                logoW = logoW,
                logoH = logoH,
                scale = scale,
                gridColor = gridColor
            )
        }

        if (showSafeZone) {
            drawSafeZoneOverlay(
                left = left,
                top = top,
                logoW = logoW,
                logoH = logoH,
                scale = scale,
                safeZoneColor = safeZoneColor
            )
        }

        withTransform({
            translate(left = left, top = top)
            scale(scaleX = scale, scaleY = scale, pivot = Offset.Zero)
            translate(left = NatsuOfficialPaths.OFFSET_X, top = NatsuOfficialPaths.OFFSET_Y)
        }) {
            val strokeStyle = Stroke(width = 7f)
            parsedNatsu.forEach { path ->
                if (outlineOnly) {
                    drawPath(path = path, color = natsuColor, style = strokeStyle)
                } else {
                    drawPath(path = path, color = natsuColor)
                }
            }
            if (outlineOnly) {
                drawPath(path = parsedBar, color = speedBarColor, style = strokeStyle)
            } else {
                drawPath(path = parsedBar, color = speedBarColor)
            }
            parsedAuto.forEach { path ->
                if (outlineOnly) {
                    drawPath(path = path, color = autoPartsColor, style = strokeStyle)
                } else {
                    drawPath(path = path, color = autoPartsColor)
                }
            }
        }
    }
}

private fun DrawScope.drawConstructionOverlay(
    left: Float,
    top: Float,
    logoW: Float,
    logoH: Float,
    scale: Float,
    gridColor: Color
) {
    // Subtle blueprint sub-grid
    val step = 64f * scale
    val gridAlpha = gridColor.copy(alpha = 0.22f)
    var gx = left - step * 2
    while (gx <= left + logoW + step * 2) {
        drawLine(gridAlpha, Offset(gx, 0f), Offset(gx, size.height), strokeWidth = 1f)
        gx += step
    }
    var gy = top - step * 2
    while (gy <= top + logoH + step * 2) {
        drawLine(gridAlpha, Offset(0f, gy), Offset(size.width, gy), strokeWidth = 1f)
        gy += step
    }

    // Key horizontal proportion guides (Cap height, Torii top, NATSU baseline, Speed Bar top/bottom)
    val yGuides = listOf(0f, 24f, 210f, 248f, 320f)
    val guideColor = gridColor.copy(alpha = 0.72f)
    yGuides.forEach { yUnit ->
        val y = top + yUnit * scale
        drawLine(
            color = guideColor,
            start = Offset(left - 24f * scale, y),
            end = Offset(left + logoW + 24f * scale, y),
            strokeWidth = 1.4f
        )
    }

    // Vertical letter boundary guides (N, A, T, S, U edges)
    val xGuides = listOf(0f, 180f, 392f, 603f, 814f, 1018f)
    xGuides.forEach { xUnit ->
        val x = left + xUnit * scale
        drawLine(
            color = guideColor.copy(alpha = 0.55f),
            start = Offset(x, top - 24f * scale),
            end = Offset(x, top + logoH + 24f * scale),
            strokeWidth = 1.2f
        )
    }

    // 22-degree slanted speed bar angle construction ray
    val barSlopeTopX = left + (819.91f - 458.61f) * scale
    val barSlopeTopY = top + (655.32f - 407.0f) * scale
    val barSlopeBotX = left + (778.63f - 458.61f) * scale
    val barSlopeBotY = top + (726.49f - 407.0f) * scale
    val dx = barSlopeTopX - barSlopeBotX
    val dy = barSlopeTopY - barSlopeBotY
    drawLine(
        color = NatsuRacingRed.copy(alpha = 0.9f),
        start = Offset(barSlopeBotX - dx * 0.6f, barSlopeBotY - dy * 0.6f),
        end = Offset(barSlopeTopX + dx * 1.4f, barSlopeTopY + dy * 1.4f),
        strokeWidth = 2.2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 6f), 0f)
    )
}

private fun DrawScope.drawSafeZoneOverlay(
    left: Float,
    top: Float,
    logoW: Float,
    logoH: Float,
    scale: Float,
    safeZoneColor: Color
) {
    // 1X unit equals the height of the slanted speed bar (~71.17 units in SVG coordinates)
    val xUnit = 71.17f * scale
    val outerLeft = (left - xUnit).coerceAtLeast(4f)
    val outerTop = (top - xUnit).coerceAtLeast(4f)
    val outerRight = (left + logoW + xUnit).coerceAtMost(size.width - 4f)
    val outerBottom = (top + logoH + xUnit).coerceAtMost(size.height - 4f)

    // Outer dashed clear-space perimeter
    drawRect(
        color = safeZoneColor.copy(alpha = 0.10f),
        topLeft = Offset(outerLeft, outerTop),
        size = Size(outerRight - outerLeft, outerBottom - outerTop)
    )
    drawRect(
        color = safeZoneColor.copy(alpha = 0.85f),
        topLeft = Offset(outerLeft, outerTop),
        size = Size(outerRight - outerLeft, outerBottom - outerTop),
        style = Stroke(
            width = 2f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 8f), 0f)
        )
    )

    // Inner tight bounding box of the logo
    drawRect(
        color = safeZoneColor.copy(alpha = 0.5f),
        topLeft = Offset(left, top),
        size = Size(logoW, logoH),
        style = Stroke(width = 1.2f)
    )

    // Four modular "X" corner square indicators
    val boxSize = xUnit.coerceAtMost(minOf(left - outerLeft, top - outerTop).coerceAtLeast(10f))
    val cornerOffsets = listOf(
        Offset(outerLeft, outerTop),
        Offset(outerRight - boxSize, outerTop),
        Offset(outerLeft, outerBottom - boxSize),
        Offset(outerRight - boxSize, outerBottom - boxSize)
    )
    cornerOffsets.forEach { pos ->
        drawRect(
            color = safeZoneColor.copy(alpha = 0.25f),
            topLeft = pos,
            size = Size(boxSize, boxSize)
        )
        drawRect(
            color = safeZoneColor,
            topLeft = pos,
            size = Size(boxSize, boxSize),
            style = Stroke(width = 1.6f)
        )
    }
}
