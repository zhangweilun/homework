package handler

import com.jfinal.core.JFinal
import com.jfinal.handler.Handler
import model.Count
import org.omg.PortableInterceptor.USER_EXCEPTION
import java.time.LocalDateTime
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.time.ZoneId
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * Created by willian on 2017/3/29.
 */
class CountHandler : Handler() {

    override fun handle(target: String?, request: HttpServletRequest?, response: HttpServletResponse?, isHandled: BooleanArray?) {

        val servletContext = JFinal.me().servletContext
        var any = servletContext.getAttribute("times").toString().toInt()
        val currentAgent = request!!.getHeader("user-agent")
        val current_ip = request.remoteAddr
        val list = JFinal.me().servletContext.getAttribute("list") as HashMap<String, Count>
        var user = list[current_ip]
        if (user == null) {
            val now = LocalDateTime.now()
            val sesion_count = Count(now, currentAgent, current_ip)
            any += 1
            list.put(current_ip, sesion_count)
        } else {
            user = user as Count
            //ip相同，浏览器相同
            val zone = ZoneId.systemDefault()
            val instant = user.access_time.atZone(zone).toInstant()
            val access_date = Date.from(instant)
            val local_date = Date.from(LocalDateTime.now().atZone(zone).toInstant())
            val temp = local_date.time - access_date.time
            if (temp > 20000) {
                //大于20秒
                any += 1
                user.access_time = LocalDateTime.now()
                println("<<<<<<<<<<<<<<<<时间大于20秒>>>>>>>>>>>>>>")
                list.replace(current_ip, user)
            }
        }
        println(any)
        servletContext.setAttribute("times", any)
        servletContext.setAttribute("list",list)
        next.handle(target, request, response, isHandled)
    }
}