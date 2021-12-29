package changhwan.experiment.sopthomework.util

import androidx.recyclerview.widget.DiffUtil
import changhwan.experiment.sopthomework.ui.view.profile.follower.data.FollowerData

class ContactDiffUtil(private val oldList: List<FollowerData>, private val currentList: List<FollowerData>):
    DiffUtil.Callback(){
    override fun getOldListSize(): Int =oldList.size

    override fun getNewListSize(): Int =currentList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].followerName==currentList[newItemPosition].followerName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]==currentList[newItemPosition]
    }

}