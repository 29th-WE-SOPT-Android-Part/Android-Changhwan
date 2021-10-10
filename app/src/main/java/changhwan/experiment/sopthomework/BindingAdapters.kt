package changhwan.experiment.sopthomework


import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imgRes")
    fun setImage (imageview : ImageView, resid : Int){
        imageview.setImageResource(resid)
    }
}