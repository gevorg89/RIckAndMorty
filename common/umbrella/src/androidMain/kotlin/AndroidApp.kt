import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import platform.PlatformConfiguration

@Composable
fun AndroidApp() {
    val context = LocalContext.current
    PlatformSDK.init(PlatformConfiguration(context))
    App()
}