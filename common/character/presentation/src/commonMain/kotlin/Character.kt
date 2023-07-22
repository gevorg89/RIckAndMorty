import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import di.Inject
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

@Composable
fun Character() {
    val characterRepository: CharacterRepository = Inject.instance()
    var items: List<Character> by remember { mutableStateOf(emptyList()) }
    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            items = characterRepository.fetch()
        }
    }
    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(vertical = 16.dp)) {
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
    }
}