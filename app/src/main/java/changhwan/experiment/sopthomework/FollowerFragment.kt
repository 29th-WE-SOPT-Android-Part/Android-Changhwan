package changhwan.experiment.sopthomework

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import changhwan.experiment.sopthomework.databinding.FragmentFollowerBinding


class FollowerFragment : Fragment(), ItemDragListener {

    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private lateinit var followerAdapter: FollowerAdapter
    private lateinit var itemTouchHelper: ItemTouchHelper
    private val gitHubViewModel by activityViewModels<GitHubViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        gitHubViewModel.getGitHubFollowerData()
        gitHubViewModel.getFollowerDataDone.observe(viewLifecycleOwner, EventObserver {
            gitHubViewModel.getGitHubUserData()
        })
        gitHubViewModel.getUserDataDone.observe(viewLifecycleOwner, EventObserver {
            gitHubViewModel.getConclusionData()
        })

        _binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gitHubViewModel.getConclusionDataDone.observe(viewLifecycleOwner, EventObserver {
            siteFollowerRecycler()

            binding.followerRecycle.addItemDecoration(CustomMarginDecoration(24))
            binding.followerRecycle.addItemDecoration(
                CustomDividerDecoration(
                    1f,
                    10f,
                    resources.getColor(R.color.divider),
                    40
                )
            )

            itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(followerAdapter))
            itemTouchHelper.attachToRecyclerView(binding.followerRecycle)
        })

    }

    fun siteFollowerRecycler() {
        followerAdapter = FollowerAdapter(this)

        binding.followerRecycle.adapter = followerAdapter

        followerAdapter.followerData.clear()

        followerAdapter.followerData.addAll(
            gitHubViewModel.conclusionData
        )


        Handler(Looper.getMainLooper()).postDelayed({
            //diffUtill부분 원래는 followerAdapter.notifyDataSetChanged()였음
            followerAdapter.replaceItems(
                listOf( FollowerData(
                    MutableLiveData("히히"),
                    MutableLiveData("하하"),
                    MutableLiveData("https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTAyMjJfMjQ4%2FMDAxNjE0MDAwNjkxODIw.rTZB_eNZP9qv5sxr2GtjLPfPWP91qfw-nuslJr06qs8g.BBnnzNF2tPX216F35NgjPykKukTXORwuOLwMubcIfEAg.PNG.waffleliebe%2Fimage.png&type=a340")
                ))
            )
            //여기까지
        },5000)
    }


    fun testDiff() {
        binding
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}