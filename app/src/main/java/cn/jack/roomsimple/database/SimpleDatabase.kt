package cn.jack.roomsimple.database

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import cn.jack.roomsimple.database.dao.SimpleInfoDao
import cn.jack.roomsimple.database.entity.SimpleInfo
import com.commonsware.cwac.saferoom.SafeHelperFactory


/**
 * @创建者 Jack
 * @创建时间 2023/7/7
 * @描述 创建数据库
 */
@Database(
    entities = [SimpleInfo::class],
    version = Constants.DATABASE_VERSION
)
abstract class SimpleInfoDatabase : RoomDatabase() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: SimpleInfoDatabase? = null

        @SuppressLint("StaticFieldLeak")
        private lateinit var context: Context

        //数据库升级
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //插入score字段
//                database.execSQL("ALTER TABLE SimpleInfo ADD COLUMN score INTEGER NOT NULL DEFAULT 0")
                println("migrate 修改密码")
//                SafeHelperFactory.rekey(database, DB_ENCRYPT_KEY2.toCharArray())    //出现了报错   弃用，采用 先解密后加密的方式更换密码
            }
        }

        const val DB_ENCRYPT_KEY = "123456" //实际项目该位置的key从接口或从so中进行获取
        const val DB_ENCRYPT_KEY2 = "654321"
//        private val passphrase: ByteArray = SQLiteDatabase.getBytes(DB_ENCRYPT_KEY.toCharArray())
//        private val factory = SupportFactory(passphrase)

        private val factory = SafeHelperFactory(DB_ENCRYPT_KEY.toCharArray())

        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): SimpleInfoDatabase {
            Companion.context = context

            if (instance == null) {
                instance = create(context)
            }
            return instance!!
        }

        private fun create(context: Context): SimpleInfoDatabase {
            return Room.databaseBuilder(
                context,
                SimpleInfoDatabase::class.java,
                Constants.DB_NAME
            )
//                .addMigrations(MIGRATION_1_2)
//                .openHelperFactory(factory)   //不建议使用该方案
                .allowMainThreadQueries()
                .build()
        }
    }

    abstract fun getSimpleInfoDao(): SimpleInfoDao
}

object Constants {
    //数据库名称
    const val DB_NAME = "RoomSimple.db"
//    const val DB_ENCRYPT_NAME = "RoomSimpleEncrypt.db"

    //数据库版本
    const val DATABASE_VERSION = 1
}