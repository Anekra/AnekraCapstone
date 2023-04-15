package com.anekra.capstoneapp.util

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anekra.capstoneapp.R
import kotlinx.coroutines.delay
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

fun Any?.logAsString(context: String) {
    Log.d("debug", "$context: $this")
}

fun String.toAllCaps(): String {
    return this.uppercase(Locale.getDefault())
}

@OptIn(ExperimentalFoundationApi::class)
fun <T : Any> LazyStaggeredGridScope.items(
    items: LazyPagingItems<T>,
    key: ((item: T) -> Any)? = null,
    itemContent: @Composable LazyStaggeredGridItemScope.(item: T) -> Unit
) {
    items(
        count = items.itemCount,
        key = if (key == null) null else { index ->
            val item = items.peek(index)
            if (item == null) {
                PagingPlaceholderKey(index)
            } else {
                key(item)
            }
        }
    ) { index ->
        items[index]?.let {
            itemContent(it)
        }
    }
}

@Composable
fun LoadingBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorText(error: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = error,
            color = MaterialTheme.colorScheme.error,
            style = TextStyle(
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun GradientOverlay(
    modifier: Modifier = Modifier,
    imageSize: IntSize,
) {
    Box(
        modifier = modifier.background(
            Brush.verticalGradient(
                colors = listOf(Color.Transparent, Color.Black),
                startY = imageSize.height.toFloat() / 2,
                endY = imageSize.height.toFloat()
            )
        )
    )
}

@Composable
fun StarShape(
    rating: Float,
    points: Int = 5,
    outerRadius: Dp = 12.dp,
    innerRadius: Dp = 8.dp,
    colors: List<Color> = listOf(Color.Yellow, Color.DarkGray),
) {
    val outerRadiusPx = with(LocalDensity.current) { outerRadius.toPx() }
    val innerRadiusPx = with(LocalDensity.current) { innerRadius.toPx() }
    val angle = Math.PI / points
    
    val path = remember { Path() }
    path.reset()
    
    var currentAngle = -Math.PI / 2
    path.moveTo(
        (cos(currentAngle) * outerRadiusPx).toFloat(),
        (sin(currentAngle) * outerRadiusPx).toFloat()
    )
    for (i in 1..2 * points) {
        val radius = if (i % 2 == 0) outerRadiusPx else innerRadiusPx
        val x = (cos(currentAngle + angle) * radius).toFloat()
        val y = (sin(currentAngle + angle) * radius).toFloat()
        path.lineTo(x, y)
        currentAngle += angle
    }
    
    path.close()
    
    val brush = Brush.linearGradient(
        colors = colors,
        start = Offset(x = 0f, y = 0f),
        end = Offset(x = rating * 20, y = 0f)
    )
    
    Box(
        modifier = Modifier.requiredSize(outerRadius * 2)
    ) {
        Canvas(modifier = Modifier.align(Alignment.Center)) {
            drawPath(path = path, brush = brush)
        }
    }
}

@Composable
fun ImageHolder(
    imageUrl: String,
    showGradient: Boolean,
) {
    var imageSize by remember { mutableStateOf(IntSize.Zero) }
    
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier
                .defaultMinSize(minHeight = 180.dp)
                .fillMaxSize()
                .onGloballyPositioned { imageSize = it.size },
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .placeholder(R.drawable.placeholder)
                .crossfade(500)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = "Content Image",
        )
        if (showGradient) {
            GradientOverlay(
                modifier = Modifier.matchParentSize(),
                imageSize = imageSize
            )
        }
    }
}

fun applyQueries(
    page: Int? = null,
    pageSize: Int? = null,
    search: String? = null,
    platform: String? = null,
    stores: String? = null,
    developers: String? = null,
    genres: String? = null,
    tags: String? = null,
    creators: String? = null,
    dates: String? = null,
    updated: String? = null,
    platformCount: Int? = null,
    metacritic: String? = null,
    ordering: String? = null,
): HashMap<String, Any> {
    val queries: HashMap<String, Any> = HashMap()
    
    page?.let { queries["page"] = it }
    pageSize?.let { queries["page_size"] = it }
    search?.let { queries["search"] = it }
    platform?.let { queries["platform"] = it }
    stores?.let { queries["stores"] = it }
    developers?.let { queries["developers"] = it }
    genres?.let { queries["genres"] = it }
    tags?.let { queries["tags"] = it }
    creators?.let { queries["creators"] = it }
    dates?.let { queries["dates"] = it }
    updated?.let { queries["updated"] = it }
    platformCount?.let { queries["platform_count"] = it }
    metacritic?.let { queries["metacritic"] = it }
    ordering?.let { queries["ordering"] = it }
    
    return queries
}

fun getTabList() = listOf("Game", "Creator", "Developer", "Platform", "Genre", "Publisher", "Tag", "Store")

suspend fun waitForConditionOrTimeout(
    getCondition: suspend () -> Boolean,
    context: Context,
    onTimeout: suspend (Boolean, String) -> Unit = { _, _ -> }
): Pair<Boolean, String> {
    val isLoading = mutableStateOf(true)
    val elapsedTime = mutableStateOf(0L)
    onTimeout(isLoading.value, "")
    
    repeat(times = 15) {
        if (!getCondition()) {
            onTimeout(false, "")
            return Pair(false, "")
        }
        delay(1000L)
        elapsedTime.value += 1000L
    }
    
    return if (elapsedTime.value >= 15000L) {
        onTimeout(false, context.getString(R.string.server_timeout))
        Pair(false, context.getString(R.string.server_timeout))
    } else {
        Pair(false, "")
    }
}