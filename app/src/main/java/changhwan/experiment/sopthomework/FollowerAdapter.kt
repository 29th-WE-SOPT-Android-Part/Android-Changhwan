package changhwan.experiment.sopthomework

import android.content.Intent
import android.view.KeyEvent.ACTION_DOWN
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import changhwan.experiment.sopthomework.databinding.FollowerItemBinding
import kotlinx.coroutines.processNextEventInCurrentThread

class FollowerAdapter(private val listener: ItemDragListener) :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(), ItemActionListener {

    val followerData = mutableListOf<FollowerData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            FollowerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerData[position])
    }

    override fun getItemCount(): Int = followerData.size

    override fun onItemMoved(from: Int, to: Int) {
        if (from == to) {
            return
        }

        val fromItem = followerData.removeAt(from)
        followerData.add(to, fromItem)
        notifyItemMoved(from, to)
    }

    override fun onItemSwiped(position: Int) {
        followerData.removeAt(position)
        notifyItemRemoved(position)
    }


    inner class FollowerViewHolder(
        private val binding: FollowerItemBinding,
        listener: ItemDragListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FollowerData) {
            binding.followerName.text = data.followerName
            binding.followerIntro.text = data.followerIntro
        }

        init {
            binding.root.setOnClickListener {
                val intent = Intent(binding.root?.context, DetailActivity::class.java).apply {
                    this.putExtra("name", followerData[adapterPosition].followerName)
                    this.putExtra("src", R.drawable.pig)
                }
                startActivity(binding.root.context, intent, null)
            }

            binding.root.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    listener.onStartDrag(this)
                }
                false
            }
        }

    }
}