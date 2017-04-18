package util

import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*


/**
 * 配置管理器
 * @author willian
 * @created 2017-04-03 15:52
 * @email 18702515157@163.com
 **/
class ConfigurationManager

/**
 * ConfigurationManager constructor
 * Example:<br></br>
 * ConfigurationManager prop = new ConfigurationManager("my_config.txt", "UTF-8");<br></br>
 * String userName = prop.get("userName");<br></br><br></br>
 * prop = new ConfigurationManager("com/jfinal/file_in_sub_path_of_classpath.txt", "UTF-8");<br></br>
 * String value = prop.get("key");
 * @param fileName the properties file's name in classpath or the sub directory of classpath
 * @param encoding the encoding
 */
private constructor(fileName: String = "config.properties", encoding: String = "UTF-8") {

    var properties: Properties? = null
        private set
    init {
        var inputStream: InputStream? = null
        try {
            inputStream = Thread.currentThread().contextClassLoader.getResourceAsStream(fileName)        // properties.load(ConfigurationManager.class.getResourceAsStream(fileName));

            if (inputStream == null) {
                throw IllegalArgumentException("Properties file not found in classpath: " + fileName)
            }
            properties = Properties()
            properties!!.load(InputStreamReader(inputStream, encoding))
        } catch (e: IOException) {
            throw RuntimeException("Error loading properties file.", e)
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close()
                } catch (e: IOException) {
                    println(e.message)
                }

        }
    }

    operator fun get(key: String): String {
        return properties!!.getProperty(key)
    }

    operator fun get(key: String, defaultValue: String): String {
        return properties!!.getProperty(key, defaultValue)
    }

    @JvmOverloads fun getInt(key: String, defaultValue: Int? = null): Int? {
        val value = properties!!.getProperty(key)
        if (value != null) {
            return Integer.parseInt(value.trim { it <= ' ' })
        }
        return defaultValue
    }

    @JvmOverloads fun getLong(key: String, defaultValue: Long? = null): Long? {
        val value = properties!!.getProperty(key)
        if (value != null) {
            return java.lang.Long.parseLong(value.trim { it <= ' ' })
        }
        return defaultValue
    }

    @JvmOverloads fun getBoolean(key: String, defaultValue: Boolean? = null): Boolean? {
        var value: String? = properties!!.getProperty(key)
        if (value != null) {
            value = value.toLowerCase().trim { it <= ' ' }
            if ("true" == value) {
                return true
            } else if ("false" == value) {
                return false
            }
            throw RuntimeException("The value can not parse to Boolean : " + value)
        }
        return defaultValue
    }

    fun containsKey(key: String): Boolean {
        return properties!!.containsKey(key)
    }

    companion object {
        //定义一个静态私有变量(不初始化，不使用final关键字，使用volatile保证了多线程访问时instance变量的可见性，避免了instance初始化时其他变量属性还没赋值完时，被另外线程调用)
        @Volatile private var instance: ConfigurationManager? = null

        //定义一个共有的静态方法，返回该类型实例
        // 对象实例化时与否判断（不使用同步代码块，instance不等于null时，直接返回对象，提高运行效率）
        //同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时对象在第一次创建后，不再重复被创建）
        //未初始化，则初始instance变量
        val istance: ConfigurationManager?
            get() {
                if (instance == null) {
                    synchronized(ConfigurationManager::class.java) {
                        if (instance == null) {
                            instance = ConfigurationManager()
                        }
                    }
                }
                return instance
            }
    }
}
