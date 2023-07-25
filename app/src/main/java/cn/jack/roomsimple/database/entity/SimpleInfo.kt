package cn.jack.roomsimple.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @创建者 Jack
 * @创建时间 2023/7/4
 * @描述
 */
@Entity
data class SimpleInfo(
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "content") var content: String,
//    @ColumnInfo(name = "score") var score: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}