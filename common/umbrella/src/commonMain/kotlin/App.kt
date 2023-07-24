import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import decompose.DefaultRootComponent

@Composable
fun App(root: DefaultRootComponent) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        RootContent(root)
    }
}