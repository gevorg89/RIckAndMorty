import androidx.compose.ui.window.ComposeUIViewController
import platform.PlatformConfiguration

fun MainViewController() = ComposeUIViewController {
    PlatformSDK.init(PlatformConfiguration())
    App(TODO())
}