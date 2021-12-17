package changhwan.experiment.sopthomework.util

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("main_prefs",Context.MODE_PRIVATE)


    fun getBoolean (key:String,defvalue:Boolean): Boolean{
        return prefs.getBoolean(key,defvalue)
    }

    fun setBoolean (key:String, value:Boolean){
        return prefs.edit().putBoolean(key,value).apply()
    }
}