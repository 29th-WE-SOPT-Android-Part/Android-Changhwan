package changhwan.experiment.sopthomework.util.recycletview.eventlistener

interface ItemActionListener {
    fun onItemMoved(from: Int, to: Int)
    fun onItemSwiped(position: Int)
}