package changhwan.experiment.sopthomework.data.local

import androidx.room.*

@Dao
interface SoptDao {
    @Insert
    fun insert(soptEntity: SoptEntity)

    @Update
    fun update(soptEntity: SoptEntity)

    @Query("SELECT * FROM sopt_table")
    fun getAll(): List<SoptEntity>
}