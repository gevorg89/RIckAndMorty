import kotlinx.serialization.Serializable

@Serializable
data class Info (

  var count : Int?    = null,
  var pages : Int?    = null,
  var next  : String? = null,
  var prev  : String? = null

)