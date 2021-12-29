package changhwan.experiment.sopthomework.ui.view.profile.follower

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import changhwan.experiment.sopthomework.ui.view.profile.follower.adapter.FollowerAdapter
import changhwan.experiment.sopthomework.R
import changhwan.experiment.sopthomework.databinding.FragmentFollowerBinding
import changhwan.experiment.sopthomework.ui.viewmodel.GitHubViewModel
import changhwan.experiment.sopthomework.util.recycletview.decoration.CustomDividerDecoration
import changhwan.experiment.sopthomework.util.eventwrapper.EventObserver
import changhwan.experiment.sopthomework.util.recycletview.decoration.CustomMarginDecoration
import changhwan.experiment.sopthomework.util.recycletview.eventlistener.ItemDragListener
import changhwan.experiment.sopthomework.util.recycletview.eventlistener.ItemTouchHelperCallback


class FollowerFragment : Fragment(), ItemDragListener {

    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private lateinit var followerAdapter: FollowerAdapter
    private lateinit var itemTouchHelper : ItemTouchHelper
    private val gitHubViewModel by activityViewModels<GitHubViewModel>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        gitHubViewModel.getGitHubFollowerData()
        gitHubViewModel.getFollowerDataDone.observe(viewLifecycleOwner, EventObserver{
            gitHubViewModel.getGitHubUserData()
        })
        gitHubViewModel.getUserDataDone.observe(viewLifecycleOwner, EventObserver{
            gitHubViewModel.getConclusionData()
        })

        _binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gitHubViewModel.getConclusionDataDone.observe(viewLifecycleOwner, EventObserver{
            siteFollowerRecycler()

            binding.followerRecycle.addItemDecoration(CustomMarginDecoration(24))
            binding.followerRecycle.addItemDecoration(CustomDividerDecoration(1f,10f, resources.getColor(
                R.color.divider
            ),40))

            itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(followerAdapter))
            itemTouchHelper.attachToRecyclerView(binding.followerRecycle)
        })

    }

    fun siteFollowerRecycler(){
        followerAdapter = FollowerAdapter(this)

        binding.followerRecycle.adapter = followerAdapter

        followerAdapter.followerData.clear()

        followerAdapter.followerData.addAll(
            gitHubViewModel.conclusionData
        )



        //diffUtill부분 원래는 followerAdapter.notifyDataSetChanged()였음
        followerAdapter.setContact(followerAdapter.followerData)
        //여기까지
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}