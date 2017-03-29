package model

import java.time.LocalDateTime

/**
 * Created by willian on 2017/3/29.
 */
data class Count(
        var access_time: LocalDateTime,
        var user_agent:String,
        var ip:String
)
