package changhwan.experiment.sopthomework

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import changhwan.experiment.sopthomework.databinding.FollowerItemBinding

class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {

    val followerData = mutableListOf<FollowerData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding = FollowerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerData[position])
    }

    override fun getItemCount(): Int = followerData.size

    inner class FollowerViewHolder(private val binding: FollowerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FollowerData) {
            binding.followerName.text = data.followerName
            binding.followerIntro.text = data.followerIntro
        }

    }
}