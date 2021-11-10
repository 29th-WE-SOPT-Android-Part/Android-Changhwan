package changhwan.experiment.sopthomework

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubViewModel : ViewModel() {

    private val _followerList = mutableListOf<MutableLiveData<String>>()
    private val _followerAvatarUrl = mutableListOf<MutableLiveData<String>>()
    private val _bio = mutableListOf<MutableLiveData<String>>()
    private val _conclusionData = mutableListOf<FollowerData>()
    private val _getFollowerDataDone = MutableLiveData<Event<String>>()
    private val _getUserDataDone = MutableLiveData<Event<String>>()
    private val _getConclusionDataDone = MutableLiveData<Event<String>>()

    val followerList: List<MutableLiveData<String>>
        get() = _followerList
    val followerAvatarUrl: List<MutableLiveData<String>>
        get() = _followerAvatarUrl
    val bio: List<MutableLiveData<String>>
        get() = _bio
    val conclusionData: List<FollowerData>
        get() = _conclusionData
    val getFollowerDataDone: LiveData<Event<String>>
        get() = _getFollowerDataDone
    val getUserDataDone: LiveData<Event<String>>
        get() = _getUserDataDone
    val getConclusionDataDone: LiveData<Event<String>>
        get() = _getConclusionDataDone

    fun getGitHubFollowerData() {
        val call: Call<List<ResponseGitHubFollowerData>> =
            ServiceCreator.gitHubService.getGitHubFollowers("2chang5")

        call.enqueue(object : Callback<List<ResponseGitHubFollowerData>> {
            override fun onResponse(
                call: Call<List<ResponseGitHubFollowerData>>,
                response: Response<List<ResponseGitHubFollowerData>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        for (i in data) {
                            _followerList.add(MutableLiveData<String>().apply { value = i.login })
                            _followerAvatarUrl.add(MutableLiveData<String>().apply { value = i.avatar_url })
                        }
                    }
                    _getFollowerDataDone.value = Event("followerDone")
                }
            }

            override fun onFailure(call: Call<List<ResponseGitHubFollowerData>>, t: Throwable) {

            }

        })
    }

    fun getGitHubUserData() {
        for (i in _followerList) {
            val call: Call<ResponseGithubUserData>? = i.value?.let {
                ServiceCreator.gitHubService.getGitHubUsers(
                    it
                )
            }

            if (call != null) {
                call.enqueue(object : Callback<ResponseGithubUserData> {
                    override fun onResponse(
                        call: Call<ResponseGithubUserData>,
                        response: Response<ResponseGithubUserData>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body()?.bio

                            _bio.add(MutableLiveData<String>().apply { value = data })
                            if(i == _followerList.last()){
                                _getUserDataDone.value = Event("UserDone")
                            }
                        } else{

                        }

                    }

                    override fun onFailure(call: Call<ResponseGithubUserData>, t: Throwable) {

                    }
                })
            }
        }
    }


    fun getConclusionData() {
        for (i in _followerList.indices) {
            _conclusionData.add(
                FollowerData(
                    followerName = _followerList[i],
                    followerImg = _followerAvatarUrl[i],
                    followerIntro = _bio[i]
                    )
            )
        }
        _getConclusionDataDone.value = Event("ConclusionDone")
    }
}