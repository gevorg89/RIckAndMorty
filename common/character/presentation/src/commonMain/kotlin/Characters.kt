import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import decompose.ListComponent
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import org.lighthousegames.logging.logging

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Characters(component: ListComponent) {
    val state by component.model.subscribeAsState()
    val listState = rememberLazyListState()
    if (state.loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Loading...", fontSize = 26.sp)
        }
    } else {
        val pullRefreshState = rememberPullRefreshState(
            state.refreshing,
            {
                component.invalidate()
            }
        )
        Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 16.dp),
                state = listState,
            ) {
                items(state.items) { character ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            component.onItemClicked(character.id)
                        }
                    ) {
                        Row {
                            val painterResource = asyncPainterResource(data = character.image)
                            KamelImage(
                                modifier = Modifier.size(150.dp),
                                resource = painterResource,
                                contentDescription = "",
                                onFailure = {
                                    Image(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = null
                                    )
                                }
                            )
                            Text("Character: ${character.name}")
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    if (state.appending) {
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(50.dp),
                                strokeWidth = 2.dp
                            )
                        }
                    }
                }
            }
            PullRefreshIndicator(
                state.refreshing,
                pullRefreshState,
                Modifier.align(Alignment.TopCenter)
            )
        }

        // Observe scroll state to load more items
        LaunchedEffect(listState) {
            snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
                .filterNotNull()
                .collectLatest { index ->
                    logging().w { "scroll ui appending:${state.appending}, items:${state.items.size}, index:$index" }
                    if (!state.appending && index >= state.items.size - 5) {
                        logging().w { "fetch call ui" }
                        component.fetch()
                    }
                }
        }
    }
}