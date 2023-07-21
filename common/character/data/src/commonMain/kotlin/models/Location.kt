import kotlinx.serialization.Serializable

@Serializable
data class Location (

  var name : String? = null,
  var url  : String? = null

)