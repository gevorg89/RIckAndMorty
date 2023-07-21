import kotlinx.serialization.Serializable

@Serializable
data class CharacterRemote (
  var info    : Info?              = Info(),
  var results : ArrayList<Results> = arrayListOf()
)