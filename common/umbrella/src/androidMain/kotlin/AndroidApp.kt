import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import platform.PlatformConfiguration

@Composable
fun AndroidApp() {
    val context = LocalContext.current
    SideEffect {
        PlatformSDK.init(PlatformConfiguration(context))
    }
    App()
}