package changhwan.experiment.sopthomework.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

@Dao
interface SoptDao {
    @Insert
    fun insert(soptEntity: SoptEntity)

    @Delete
    fun delete(soptEntity: SoptEntity)
}