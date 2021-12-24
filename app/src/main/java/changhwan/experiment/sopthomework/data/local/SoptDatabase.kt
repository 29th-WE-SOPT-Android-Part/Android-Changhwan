package changhwan.experiment.sopthomework.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SoptEntity::class],version = 2)
abstract class SoptDatabase : RoomDatabase() {
    abstract fun soptDao(): SoptDao

    companion object{
        private var instance: SoptDatabase? = null

        @Synchronized
        fun getInstance(context: Context): SoptDatabase? {
            if(instance == null) {
                synchronized(SoptDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext, SoptDatabase::class.java,"sopt-database")
//                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }
    }
}