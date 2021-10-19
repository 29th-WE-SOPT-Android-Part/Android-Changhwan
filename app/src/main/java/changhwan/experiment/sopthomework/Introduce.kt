package changhwan.experiment.sopthomework

import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData

data class Introduce(
    var liveName: MutableLiveData<String>,
    var LiveAge: MutableLiveData<Int>,
    val liveMbti: MutableLiveData<String>,
    val liveIntroduction: MutableLiveData<String>,
    val resorce: Int,
)
