package handler

import com.jfinal.core.JFinal
import com.jfinal.handler.Handler
import model.Count
import java.time.LocalDateTime
import java.time.Period
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.time.ZoneId



/**
 * Created by willian on 2017/3/29.
 */
class CountHandler:Handler() {

    override fun handle(target: String?, request: HttpServletRequest?, response: HttpServletResponse?, isHandled: BooleanArray?) {

        val servletContext = JFinal.me().servletContext
        var any = servletContext.getAttribute("count").toString().toInt()
        val currentAgent = request!!.getHeader("user-agent")
        val current_ip = request.remoteAddr
        //判断是否是第一次访问
        val session = request.session
        var count = session.getAttribute("count")
        if (count == null){
            val now = LocalDateTime.now()
            val sesion_count = Count(now, currentAgent,current_ip)
            session.setAttribute("count",sesion_count)
            println("<<<<<<<<<<<<<第一次访问>>>>>>>>>>>>>")
            any += 1
        }else{
            count = count as Count
            //同一个浏览器访问
            if (count.user_agent == currentAgent){
                //是否是同一个电脑访问
                if (count.ip == current_ip){
                    //是同一台电脑
                    //是否时间相差20秒
                    val zone = ZoneId.systemDefault()
                    val instant = count.access_time.atZone(zone).toInstant()
                    val access_date = Date.from(instant)
                    val local_date = Date.from(LocalDateTime.now().atZone(zone).toInstant())
                    val temp = local_date.time - access_date.time
                    if (temp > 20000){
                        //大于20秒
                        any += 1
                        println("<<<<<<<<<<<<<<<<时间大于20秒>>>>>>>>>>>>>>")

                    }else{
                        println("<<<<<<<<<<<<<<<<时间小于20秒>>>>>>>>>>>>>>")
                    }
                }else{
                    //不是同一台电脑
                    println("<<<<<<<<<<<<<不同电脑访问>>>>>>>>>>>>>")
                    any += 1
                }

            }else{
                //不同浏览器访问
                println("<<<<<<<<<<<<<不同浏览器访问>>>>>>>>>>>>>")
                any += 1
            }

        }
        println(any)
        servletContext.setAttribute("count",any)
        next.handle(target, request, response, isHandled)
    }
}