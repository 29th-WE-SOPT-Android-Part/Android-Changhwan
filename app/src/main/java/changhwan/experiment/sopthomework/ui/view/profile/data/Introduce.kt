package changhwan.experiment.sopthomework.ui.view.profile.data

import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData

data class Introduce(
    var liveName: MutableLiveData<String>,
    val liveIntroduction: MutableLiveData<String>,
    val liveID : MutableLiveData<String>,
    val resorce: Int,
)
