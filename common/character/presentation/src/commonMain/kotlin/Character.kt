import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import decompose.DetailsComponent
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun Character(component: DetailsComponent) {
    val state by component.model.subscribeAsState()
    val character = state.item
    if (character != null) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = {
                    Text(character.name)
                },
                navigationIcon = {
                    IconButton(onClick = { component.onBackClick() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
            val painterResource = asyncPainterResource(data = character.image)
            KamelImage(
                modifier = Modifier.weight(1f),
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
}