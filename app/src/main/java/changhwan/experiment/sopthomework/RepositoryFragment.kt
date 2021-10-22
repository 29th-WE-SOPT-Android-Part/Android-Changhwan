package changhwan.experiment.sopthomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import changhwan.experiment.sopthomework.databinding.FragmentRepositoryBinding


class RepositoryFragment : Fragment() {

    private var _binding : FragmentRepositoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var repositoryAdapter: RepositoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        siteRepositoryRecycler()

        binding.repositoryRecycle.addItemDecoration(CustomMarginDecoration(50))
    }


    fun siteRepositoryRecycler(){
        repositoryAdapter = RepositoryAdapter()

        binding.repositoryRecycle.adapter = repositoryAdapter

        repositoryAdapter.repositoryData.addAll(
            listOf(
                RepositoryData("안드로이드 과제 레포지토리","안드로이드 과제 레포지토리"),
                RepositoryData("ios 과제 레포지토리","ios 과제 레포지토리"),
                RepositoryData("서버 과제 레포지토리","서버 과제 레포지토리"),
                RepositoryData("웹 과제 레포지토리","웹 과제 레포지토리"),
                RepositoryData("디자인 과제 레포지토리","디자인 과제 레포지토리"),
            )
        )

        repositoryAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}