import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import di.Inject.instance

@Composable
fun App()  {
    val characterRepository : CharacterRepository = instance()
    var count by remember { mutableStateOf(0) }
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button({ count++ }) {
            Text("Click count $count")

        }
    }

    LaunchedEffect(count) {
        characterRepository.fetch()
    }
}