package decompose

import Character
import CharacterRepository
import CharactersPaging
import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import di.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.lighthousegames.logging.logging

interface ListComponent {

    val model: Value<Model>

    fun onItemClicked(item: Long)

    fun invalidate()

    fun fetch()

    @Immutable
    data class Model(
        val refreshing: Boolean = false,
        val loading: Boolean = true,
        val appending: Boolean = false,
        val items: List<Character> = emptyList(),
    )
}

class DefaultListComponent(
    componentContext: ComponentContext,
    private val onItemSelected: (item: Long) -> Unit,
) : ListComponent {

    private val _state = MutableValue(ListComponent.Model())
    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    //TODO("Change di to get pager")
    private val characterRepository: CharacterRepository by lazy { Inject.instance() }
    private val paging by lazy { CharactersPaging(characterRepository) }

    override val model: Value<ListComponent.Model> = _state

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

    override fun fetch() {
        scope.launch {
            logging().w { "fetch call" }
            _state.update { it.copy(appending = true) }
            paging.checkLoad()
        }
    }

    override fun onItemClicked(item: Long) {
        onItemSelected(item)
    }

    override fun invalidate() {
        scope.launch {
            paging.invalidate()
            fetch()
        }
    }
}