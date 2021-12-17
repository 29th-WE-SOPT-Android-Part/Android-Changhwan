package changhwan.experiment.sopthomework.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface SoptDao {
    @Insert
    fun insert(soptEntity: SoptEntity)

    @Update
    fun update(soptEntity: SoptEntity)
}