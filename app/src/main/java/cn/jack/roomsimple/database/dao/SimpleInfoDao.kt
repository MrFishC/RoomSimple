package cn.jack.roomsimple.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cn.jack.roomsimple.database.entity.SimpleInfo

/**
 * @创建者 Jack
 * @创建时间 2023/7/10
 * @描述
 */
@Dao
interface SimpleInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSimpleInfo(simpleInfo: SimpleInfo): Long

    @Delete
    fun deleteSimpleInfo(simpleInfo: SimpleInfo): Int

    @Update
    fun updateSimpleInfo(simpleInfo: SimpleInfo): Int

    //分页查询
    @Query("select * from SimpleInfo limit :limit offset :offset")
    fun loadPageSimpleInfo(limit: Int, offset: Int): List<SimpleInfo>

    @Query("select * from SimpleInfo")
    fun loadAllSimpleInfo(): List<SimpleInfo>
}