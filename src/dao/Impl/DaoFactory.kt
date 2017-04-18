package dao.Impl

import dao.*


/**
 * TASK Factory
 * @author willian
 * @created 2017-04-04 22:20
 * @email 18702515157@163.com
 **/
class DaoFactory {
    /**
     * 获取任务管理DAO
     * @return ITaskDao
     */


    fun getTopTenCategoryDao(): ITopTenCategory {
        return TopTenCategoryImpl()
    }
}