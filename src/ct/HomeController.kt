package ct

import com.jfinal.core.Controller
import com.jfinal.core.JFinal
import model.Student

/**
 * Created by willian on 2017/3/28.
 */
class HomeController : Controller() {
    fun index() {
        render("index.jsp")
    }
    fun submit(){
        val context = JFinal.me().servletContext
//        val remoteAddr = request.remoteAddr
        val student = getBean(Student::class.java,"student")
        val times = context.getAttribute("times").toString().toInt()
        setAttr("student",student)
        setAttr("times",times)
        render("/view/submit.jsp")
    }
}
