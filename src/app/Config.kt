package app

import com.jfinal.config.*
import com.jfinal.core.JFinal
import com.jfinal.render.ViewType
import com.jfinal.template.Engine
import ct.HomeController
import handler.CountHandler

/**
 * Created by willian on 2017/3/28.
 */
class Config : JFinalConfig() {
    override fun afterJFinalStart() {
        val context = JFinal.me().servletContext
        context.setAttribute("count",0)
    }


    override fun configConstant(me: Constants) {
        me.viewType = ViewType.JSP
        me.devMode = true
    }

    override fun configRoute(me: Routes) {
        me.baseViewPath = "/view"
        me.add("/", HomeController::class.java)
    }

    override fun configEngine(me: Engine) {

    }

    override fun configPlugin(me: Plugins) {

    }

    override fun configInterceptor(me: Interceptors) {

    }

    override fun configHandler(me: Handlers) {
        me.add(CountHandler())
    }
}

//                    val access_date = count.access_time.toLocalDate()
//                    val local_date = LocalDateTime.now().toLocalDate()
////                    val period = Period.between(count.access_time.toLocalDate(), LocalDateTime.now().toLocalDate())
//                 //   period == Period.ZERO
//                    if (access_date.equals(local_date)){
//                        //是同一天,判断时间
//                        val access_time = count.access_time.toLocalTime()
//                        val localTime = LocalDateTime.now().toLocalTime()
//                        if (localTime.hour > access_time.hour){
//                            //大于小时
//                            any += 1
//                        }else{
//                            if (localTime.minute > access_time.minute){
//                                //大于分钟
//                                any += 1
//                                println("<<<<<<<<<<<<<同一电脑同一浏览器多于分钟>>>>>>>>>>>>>>")
//                            }else{
//                                if (localTime.second > access_time.second + 20){
//                                    //old 20 second
//                                    any += 1
//                                    println("<<<<<<<<<<<<<同一电脑同一浏览器多于20秒>>>>>>>>>>>>>>")
//                                }else{
//                                    println("<<<<<<<<<<<<<同一电脑同一浏览器少于20秒>>>>>>>>>>>>>>")
//                                }
//                            }
//                        }
//                    }
