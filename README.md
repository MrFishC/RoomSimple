# 前言

+ 主要介绍room的基础用法，使用room时数据库升级，重点是数据库加密。

# 依赖

+ [room官网地址](https://developer.android.google.cn/topic/libraries/architecture/room?hl=zh-cn)
+ [github地址-android-database-sqlcipher](https://github.com/sqlcipher/android-database-sqlcipher/)

# 基础使用
+ 定义entity
+ 定义dao
+ 自定义RoomDatabase实现类

# 版本升级
+ 场景
  + 数据库未加密的情况下，新增其它字段
    + 升级数据库步骤 :
      + 修改entity
      + 创建Migration迁移类
      + 修改数据库版本

+ 遇到的问题
```
  java.lang.IllegalStateException: Migration didn't properly handle: SimpleInfo(cn.xxx.roomsimple.SimpleInfo).Expected:
  已存在的数据库表跟现在的数据库表不同抛出了此问题
```
  + 未正确的按照升级数据库的步骤进行

# 数据库加密
+ 基于android-database-sqlcipher封装的开源库cwac-saferoom
+ android-database-sqlcipher：对数据库整个文件进行加密，打开的时候需要输入密码。

+ 结合room的使用
  + 使用openHelperFactory方法，配置factory（不推荐）
+ 直接调用加密api（推荐方式）
  
+ 具体使用
  + 不使用Room.databaseBuilder的openHelperFactory方法配置factory
    + 初始化时加密数据库，需要时解密数据库，方案可行；
  + 使用Room.databaseBuilder的openHelperFactory方法配置factory，然后再尝试解密数据库（或初始化时先调用数据库加密，然后再尝试解密数据库）
    + 出现持续性需要输入密码
    + 避免使用该方案
  + 只使用Room.databaseBuilder的openHelperFactory方法配置factory，不调用解密的方法
    + 弊端：查看数据库需要输入密码，即不能解密数据库
    + 不太建议使用该方案

+ 修改密码
  + 建议方案：先解密，再加密。

# 报错
```
    android.database.sqlite.SQLiteException: file is not a database: , while compiling: select count(*) from sqlite_master;
        at net.sqlcipher.database.SQLiteCompiledSql.native_compile(Native Method)
```
+ 原因（可能是之一）：在初始化数据库时未对数据库进行加密，但是在其它地方使用时对数据库进行了加密。

# 注意
+ 在使用模拟器时，未打开root权限，是不可以导出数据库的。在导出文件时，db-shm和db-wal都需要导出来（使用DB Browser for SQLCipher工具查看）；

# 参考资料
+ [cwac-saferoom](https://github.com/commonsguy/cwac-saferoom/tree/master)
+ [cwac-saferoom-README](https://github.com/commonsguy/cwac-saferoom/blob/master/README-original.markdown)
+ Room数据库升级保姆级教程-[【Jetpack】使用 Room 中的 Migration 升级数据库 ( 修改 Entity 实体类 - 更改数据模型 | 创建 Migration 迁移类 | 修改数据库版本 | 代码示例 )](https://hanshuliang.blog.csdn.net/article/details/130899998?spm=1001.2101.3001.6650.2&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EYuanLiJiHua%7EPosition-2-130899998-blog-93674292.235%5Ev38%5Epc_relevant_default_base&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EYuanLiJiHua%7EPosition-2-130899998-blog-93674292.235%5Ev38%5Epc_relevant_default_base&utm_relevant_index=3)
+ [理解Room数据库的迁移(Migration)](https://blog.csdn.net/u013762572/article/details/106315045)
