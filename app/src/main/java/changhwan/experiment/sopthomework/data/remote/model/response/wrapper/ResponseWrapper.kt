package changhwan.experiment.sopthomework.data.remote.model.response.wrapper

data class ResponseWrapper<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T?
)
