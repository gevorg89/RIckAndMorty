import androidx.compose.runtime.Immutable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.lighthousegames.logging.logging

class MockVM(characterRepository: CharacterRepository) {

    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    private val paging = CharactersPaging(characterRepository)


    init {
        fetch()
        paging.items
            .onStart {
                delay(5000)
            }
            .filter { it.isNotEmpty() }
            .onEach {
                _state.update { state ->
                    state.copy(
                        refreshing = false,
                        loading = false,
                        appending = false,
                        items = state.items + it
                    )
                }
            }
            .launchIn(scope)
    }

    fun invalidate() {
        scope.launch {
            paging.invalidate()
            fetch()
        }
    }

    fun fetch() {
        scope.launch {
            logging().w { "fetch call" }
            _state.update { it.copy(appending = true) }
            paging.checkLoad()
        }
    }

    @Immutable
    data class UiState(
        val refreshing: Boolean = false,
        val loading: Boolean = true,
        val appending: Boolean = false,
        val items: List<Character> = emptyList(),
    )

}