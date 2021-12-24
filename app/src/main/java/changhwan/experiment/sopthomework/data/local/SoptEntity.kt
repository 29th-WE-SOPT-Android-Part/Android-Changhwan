package changhwan.experiment.sopthomework.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sopt_table")
data class SoptEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?= null,
    val autoLogin: Boolean
)
