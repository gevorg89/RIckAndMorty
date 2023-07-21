import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import di.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

@Composable
fun Character() {
    val characterRepository : CharacterRepository = Inject.instance()
    var items: List<Character> by remember { mutableStateOf(emptyList()) }
    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            items = characterRepository.fetch()
        }
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items) {character ->
            Card {
                Text("Character: ${character.name}")
            }
        }
    }
}