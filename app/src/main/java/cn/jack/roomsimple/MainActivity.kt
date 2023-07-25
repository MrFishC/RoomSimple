package cn.jack.roomsimple

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import cn.jack.roomsimple.database.Constants
import cn.jack.roomsimple.database.SimpleInfoDatabase
import cn.jack.roomsimple.database.entity.SimpleInfo
import cn.jack.roomsimple.util.RoomU
import com.commonsware.cwac.saferoom.SQLCipherUtils
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        RoomU.init(this)
//        this.encrypted()
    }

    fun add(view: View) {
        thread(start = true) {
            println("所在线程 添加 ---> ${Thread.currentThread()}")
            val longId = SimpleInfoDatabase
                .getInstance(this@MainActivity)
                .getSimpleInfoDao()
//                .insertSimpleInfo(SimpleInfo("书籍2", "止学2", 100))
                .insertSimpleInfo(SimpleInfo("书籍", "止学"))
            println("增加 ---> $longId")
        }
    }

    fun delete(view: View) {
        thread(start = true) {
            println("所在线程 删除 ---> ${Thread.currentThread()}")
            val simpleInfos = SimpleInfoDatabase
                .getInstance(this@MainActivity)
                .getSimpleInfoDao()
                .loadAllSimpleInfo()
            if (simpleInfos.isNotEmpty()) {
                val deleteId = SimpleInfoDatabase.getInstance(this@MainActivity)
                    .getSimpleInfoDao()
                    .deleteSimpleInfo(simpleInfos[0])
                println("删除 ---> $deleteId")
            }
        }
    }

    fun update(view: View) {
        thread(start = true) {
            println("所在线程 修改 ---> ${Thread.currentThread()}")
            val simpleInfos = SimpleInfoDatabase
                .getInstance(this@MainActivity)
                .getSimpleInfoDao()
                .loadAllSimpleInfo()
            if (simpleInfos.isNotEmpty()) {
                simpleInfos[0].title = "标题";
                val updateId = SimpleInfoDatabase.getInstance(this@MainActivity)
                    .getSimpleInfoDao()
                    .updateSimpleInfo(simpleInfos[0])
                println("修改 ---> $updateId")
            }
        }
    }

    fun query(view: View) {
        thread(start = true) {
            println("所在线程 查询 ---> ${Thread.currentThread()}")
            val simpleInfos = SimpleInfoDatabase
                .getInstance(this@MainActivity)
                .getSimpleInfoDao()
                .loadAllSimpleInfo()
            println("查询 ---> $simpleInfos")
        }
    }

    //加密
    fun encryption(view: View) {
        println("encryption ${SQLCipherUtils.getDatabaseState(this, Constants.DB_NAME)}")
        if(SQLCipherUtils.getDatabaseState(this, Constants.DB_NAME) == SQLCipherUtils.State.UNENCRYPTED){
            SQLCipherUtils.encrypt(this, Constants.DB_NAME, SimpleInfoDatabase.DB_ENCRYPT_KEY.toByteArray())
        }
    }

    //解密
    fun decrypt(view: View) {
        println("decrypt ${SQLCipherUtils.getDatabaseState(this, Constants.DB_NAME)}")
        if(SQLCipherUtils.getDatabaseState(this, Constants.DB_NAME) == SQLCipherUtils.State.ENCRYPTED){
            SQLCipherUtils.decrypt(this, getDatabasePath(Constants.DB_NAME), SimpleInfoDatabase.DB_ENCRYPT_KEY.toByteArray())
        }
    }

    fun updatePasswd(view: View) {
        if(SQLCipherUtils.getDatabaseState(this, Constants.DB_NAME) == SQLCipherUtils.State.ENCRYPTED){
            SQLCipherUtils.decrypt(this, getDatabasePath(Constants.DB_NAME), SimpleInfoDatabase.DB_ENCRYPT_KEY.toByteArray())
        }
        if(SQLCipherUtils.getDatabaseState(this, Constants.DB_NAME) == SQLCipherUtils.State.UNENCRYPTED){
            SQLCipherUtils.encrypt(this, Constants.DB_NAME, SimpleInfoDatabase.DB_ENCRYPT_KEY2.toByteArray())
        }
    }

}