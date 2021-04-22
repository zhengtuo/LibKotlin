package com.drelovey.realize.data.constants

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class CommonConstants {
    companion object {

        const val DEFAULT_DATABASE_NAME = "room.db" //数据库名称

        const val PATH_CACHE = "cache_path"
        const val CACHE_SIZE = (1024 * 1024 * 50).toLong()

        /**
         * 默认超时时间,单位:秒
         */
        const val DEFAULT_TIME_OUT: Long = 15

        //
        const val BASE_URL = "http://php.mingtaoedu.com/mt/public/index.php/"


        const val PAGE = 1
        const val PAGE_SIZE = 20
        const val PHONE_TYPE: String = "2"

        const val DEFAULT_ROOM_DATABASE_MAX_SIZE = 60
        const val DEFAULT_RETROFIT_SERVICE_MAX_SIZE = 60

    }
}
