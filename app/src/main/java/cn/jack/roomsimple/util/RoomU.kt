package cn.jack.roomsimple.util

import android.annotation.SuppressLint
import android.content.Context
import net.sqlcipher.DatabaseUtils
import net.sqlcipher.database.SQLiteDatabase

/**
 * @创建者 Jack
 * @创建时间 2023/7/20
 * @描述 非理想方案，暂不研究 弃用
 */
@SuppressLint("StaticFieldLeak")
object RoomU {

//    @SuppressLint("StaticFieldLeak")
//    private lateinit var context: Context
//
//    @SuppressLint("StaticFieldLeak")
//    fun init(context: Context) {
//        RoomU.context = context
//        SQLiteDatabase.loadLibs(context)
//    }

    /**
     * 将未加密的数据库进行加密
     * @param encryptedDbName 加密后的数据库名称
     * @param originDbName 未加密的数据库名称
     * @param key 密码
     */
//    fun encrypt(encryptedDbName: String, originDbName: String, key: String) {
//        try {
//            val originalFile = context.getDatabasePath(originDbName)
//
//            if (!originalFile.exists()) {
//                return
//            }
//
//            //打开要加密的数据库
//            val database: SQLiteDatabase =
//                SQLiteDatabase.openOrCreateDatabase(originalFile, "", null)
//
//            //新建文件 - 加密后的数据库
//            val encryptedDbFile = context.getDatabasePath(encryptedDbName)
//
//            //-----------------------------------------------------------固定模板（动态传参即可）-----------------------------------------------------------
//            //连接数据库【加密后的】，并设置密码
//            database.rawExecSQL(
//                String.format(
//                    "ATTACH DATABASE '%s' as " + encryptedDbName.split(
//                        "."
//                    )[0] + " KEY '" + key + "';", encryptedDbFile.absolutePath
//                )
//            )
//            //输出要加密的数据库表和数据到加密后的数据库文件中
//            database.rawExecSQL("SELECT sqlcipher_export('" + encryptedDbName.split(".")[0] + "');")
//            //断开同加密后的数据库的连接
//            database.rawExecSQL("DETACH DATABASE " + encryptedDbName.split(".")[0] + ";")
//            //-----------------------------------------------------------固定模板-----------------------------------------------------------
//
//            //打开加密后的数据库
//            val encryptedDb: SQLiteDatabase =
//                SQLiteDatabase.openOrCreateDatabase(encryptedDbFile, key, null)
//            encryptedDb.version = database.version
//            encryptedDb.close()
//
//            database.close()
//
//            originalFile.delete()
//            encryptedDbFile.renameTo(originalFile)
//        } catch (e: Exception) {
//            println("加密异常 ${e.message}")
//            e.printStackTrace()
//        }
//    }

    /**
     * 对加密过的数据库进行解密
     * @param decryptedName 解密后的数据库名称
     * @param targetDbName 要解密的数据库名称
     * @param key 密码
     */
//    fun decrypt(decryptedName: String, targetDbName: String, key: String) {
//        try {
//            val databaseFile = context.getDatabasePath(targetDbName)
//            if (!databaseFile.exists()) {
//                return
//            }
//
//            val database: SQLiteDatabase =
//                SQLiteDatabase.openOrCreateDatabase(databaseFile, key, null)
//            val decryptedDatabaseFile = context.getDatabasePath(decryptedName)
//
//            //连接到解密后的数据库，并设置密码为空
//            database.rawExecSQL(
//                String.format(
//                    "ATTACH DATABASE '%s' as " + decryptedName.split(".")[0] + " KEY '';",
//                    decryptedDatabaseFile.absolutePath
//                )
//            )
//            //输出要解密的数据库表和数据到解密后的数据库文件中
//            database.rawExecSQL("SELECT sqlcipher_export('" + decryptedName.split(".")[0] + "');")
//            database.rawExecSQL("DETACH DATABASE " + decryptedName.split(".")[0] + ";")
//
//            val decryptedDb: SQLiteDatabase =
//                SQLiteDatabase.openOrCreateDatabase(decryptedDatabaseFile, "", null)
//            decryptedDb.version = database.version
//            decryptedDb.close()
//            database.close()
//
//            databaseFile.delete()
//            decryptedDatabaseFile.renameTo(databaseFile)
//        } catch (e: Exception) {
//            println("解密异常 ${e.message}")
//            e.printStackTrace()
//        }
//    }

}