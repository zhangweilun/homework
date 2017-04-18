package beetlsql

import org.beetl.sql.core.*
import org.beetl.sql.core.db.MySqlStyle
import util.ConfigurationManager
import org.beetl.sql.ext.DebugInterceptor
import constant.Constant

/**
 * beetlsql  BeetlSql 单例模式
 * @author willian
 * @created 2017-04-03 16:04
 * @email 18702515157@163.com
 **/
class BeetlSql// 定义一个私有构造方法
private constructor() {
    var sqlManager: SQLManager? = null
        private set
    init {
        val prop = ConfigurationManager.istance
        val source = ConnectionSourceHelper.getSimple(prop!![Constant.JDBC_DRIVER], prop[Constant.JDBC_URL], prop[Constant.JDBC_USERNAME], prop[Constant.JDBC_PASSWORD])
        val mysql = MySqlStyle()
        // sql语句放在classpagth的/sql 目录下
        val loader = ClasspathLoader("/sql")
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
        val nc = UnderlinedNameConversion()
//        val nc = DefaultNameConversion()
        // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
        val sqlManager = SQLManager(mysql, loader, source, nc, arrayOf<Interceptor>(DebugInterceptor()))
        this.sqlManager = sqlManager
    }

    companion object {
        //定义一个静态私有变量(不初始化，不使用final关键字，使用volatile保证了多线程访问时instance变量的可见性，避免了instance初始化时其他变量属性还没赋值完时，被另外线程调用)
        @Volatile private var instance: BeetlSql? = null

        //定义一个共有的静态方法，返回该类型实例
        // 对象实例化时与否判断（不使用同步代码块，instance不等于null时，直接返回对象，提高运行效率）
        //同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时对象在第一次创建后，不再重复被创建）
        //未初始化，则初始instance变量
        val istance: BeetlSql?
            get() {
                if (instance == null) {
                    synchronized(BeetlSql::class.java) {
                        if (instance == null) {
                            instance = BeetlSql()
                        }
                    }
                }
                return instance
            }
    }

}

