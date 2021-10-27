package changhwan.experiment.sopthomework

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import changhwan.experiment.sopthomework.databinding.RepositoryItemBinding

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    var repositoryData = mutableListOf<RepositoryData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding =
            RepositoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.onBind(repositoryData[position])
    }

    override fun getItemCount(): Int = repositoryData.size

    inner class RepositoryViewHolder(private val binding: RepositoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RepositoryData) {
            binding.repositoryName.text = data.repositoryName
            binding.repositoryIntro.text = data.repositoryIntro
        }
    }
}