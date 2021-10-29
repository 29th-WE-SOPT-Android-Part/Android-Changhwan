package changhwan.experiment.sopthomework

import androidx.lifecycle.MutableLiveData

data class FollowerData(
    var followerName : MutableLiveData<String>,
    var followerIntro : MutableLiveData<String>,
    var followerImg : MutableLiveData<String>,
)
