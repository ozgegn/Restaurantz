package com.ozge.restaurantz.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ozge.restaurantz.R
import com.ozge.restaurantz.domain.model.BaseError
import com.ozge.restaurantz.ui.theme.warningTintColor
import com.ozge.restaurantz.utils.NETWORK_ERROR_ICON_SIZE
import com.ozge.restaurantz.utils.SMALL_PADDING
import com.ozge.restaurantz.utils.extensions.orDefaultErrorMessage

@Composable
fun EmptyScreen(
    error: BaseError? = null,
    onRefresh: () -> Unit = {}
) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    EmptyContent(alphaAnim = alphaAnim, error = error, onRefresh = onRefresh)

}

@Composable
fun EmptyContent(
    alphaAnim: Float,
    error: BaseError?,
    onRefresh: () -> Unit = {}
) {
    var isRefreshing by remember {
        mutableStateOf(false)
    }

    SwipeRefresh(
        swipeEnabled = error != null,
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
            isRefreshing = true
            onRefresh.invoke()
            isRefreshing = false
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(NETWORK_ERROR_ICON_SIZE)
                    .alpha(alphaAnim),
                painter = painterResource(id = R.drawable.warning),
                contentDescription = "Warning Image",
                tint = MaterialTheme.colors.warningTintColor
            )
            Text(
                modifier = Modifier
                    .padding(SMALL_PADDING)
                    .alpha(alphaAnim),
                text = error?.message.orDefaultErrorMessage(),
                color = MaterialTheme.colors.warningTintColor,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )
        }
    }
}

@Composable
@Preview
fun EmptyContentPreview() {
    EmptyContent(alphaAnim = 1f, error = BaseError())
}