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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import di.Inject
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun Character() {
    val characterRepository: CharacterRepository = Inject.instance()
    var page by remember { mutableStateOf(1) }
    val listState = rememberLazyListState()
    val loading = remember { mutableStateOf(false) }
    val items = remember { mutableStateListOf<Character>() }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        state = listState,
    ) {
        items(items) { character ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Row {
                    val painterResource = asyncPainterResource(data = character.image)
                    KamelImage(
                        modifier = Modifier.size(150.dp),
                        resource = painterResource,
                        contentDescription = "",
                    )
                    Text("Character: ${character.name}")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            if (loading.value) {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(50.dp), strokeWidth = 2.dp)
                }
            }
        }
    }

    // Observe scroll state to load more items
    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collectLatest { index ->
                if (!loading.value && index != null && index >= items.size - 5) {
                    page++
                }
            }
    }

    LaunchedEffect(page) {
        loading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            items.addAll(characterRepository.fetch(page))
            loading.value = false
        }
    }
}