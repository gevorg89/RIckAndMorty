package decompose

import Character
import CharacterRepository
import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import di.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

interface DetailsComponent {
    val model: Value<Model>

    @Immutable
    data class Model(
        val item: Character?,
    )
}

class DefaultDetailsComponent(
    componentContext: ComponentContext,
    val id: Long,
) : DetailsComponent {

    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private val characterRepository: CharacterRepository = Inject.instance()

    private val _state = MutableValue(DetailsComponent.Model(null))

    override val model: Value<DetailsComponent.Model> = _state

    init {
        scope.launch {
            val item = characterRepository.getCharacter(id)
            _state.update { state ->
                state.copy(
                    item = item
                )
            }
        }
    }
}