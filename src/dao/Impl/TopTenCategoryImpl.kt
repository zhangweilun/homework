package dao.Impl

import dao.ITopTenCategory
import domain.Top10Category
import beetlsql.BeetlSql
import java.io.Serializable

/**
 *
 * @author willian
 * @created 2017-04-16 21:29
 * @email 18702515157@163.com
 **/
class TopTenCategoryImpl:ITopTenCategory, Serializable {
    override fun insert(category: Top10Category) {
        val sqlManager = BeetlSql.istance!!.sqlManager
        sqlManager!!.insert(Top10Category::class.java,category)
    }
}