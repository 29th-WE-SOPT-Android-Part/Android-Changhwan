package changhwan.experiment.sopthomework

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import changhwan.experiment.sopthomework.databinding.FragmentFollowerBinding


class FollowerFragment : Fragment() {

    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private lateinit var followerAdapter: FollowerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        siteFollowerRecycler()

        binding.followerRecycle.addItemDecoration(CustomMarginDecoration(50))
        binding.followerRecycle.addItemDecoration(CustomDividerDecoration(10f,10f, resources.getColor(R.color.main),40))
    }

    fun siteFollowerRecycler(){
        followerAdapter = FollowerAdapter()

        binding.followerRecycle.adapter = followerAdapter

        followerAdapter.followerData.addAll(
            listOf(
                FollowerData("문다빈","안드로이드 파트장sdfgsdfgsdgsdgdsgdsgdsgdsgdfsgdsgsdfg"),
                FollowerData("장혜령","ios 파트장"),
                FollowerData("김우영","서버 파트장"),
                FollowerData("이호재","문다빈 소환수"),
                FollowerData("이강민","아이폰 13 보유자"),

            )
        )

        followerAdapter.notifyDataSetChanged()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}