package changhwan.experiment.sopthomework

data class ResponseWrapper<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T?
)
