package changhwan.experiment.sopthomework

import androidx.recyclerview.widget.DiffUtil

class FollowerDiffUtil(private val oldList: List<FollowerData>, private val currentList: List<FollowerData>):
    DiffUtil.Callback(){
    override fun getOldListSize(): Int =oldList.size

    override fun getNewListSize(): Int =currentList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        //원래 id를 많이 쓰는데 여기서는 id값이 아이템 별로 없으므로 followerName으로 대체했다 각각 맞춰서 사용하자
        return oldList[oldItemPosition].followerName==currentList[newItemPosition].followerName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]==currentList[newItemPosition]
    }

}