package com.drelovey.realize.data.model

import com.google.gson.annotations.SerializedName

class DataBean {

    /**
     * id : 10
     * title : 网易
     * path : http://dev-mt2.mingtaokeji.com/public/uploads/banner/20200713/27d7d81021426b4310ba41690d054800.png
     * caption : 外部链接
     * type : 4
     * ad_url : www.网易.com
     * p_cat_id : 0
     * s_cat_id : 0
     * zb_id : 0
     * campus_id : 1
     * sort_order : 1
     * status : 1
     * is_delete : 0
     * create_at : 2020-07-13 17:19:59
     * update_at : 2020-07-13 17:19:59
     */
    val id = 0
    val title: String? = null
    val path: String? = null
    val caption: String? = null
    val type = 0
    val ad_url: String? = null
    val cat_list_child_id = 0
    val cat_list_parent_id = 0
    val s_cat_id = 0
    val zb_id = 0
    val campus_id = 0
    val sort_order = 0

    @SerializedName("status")
    val statusX = 0
    val is_delete = 0
    val create_at: String? = null
    val update_at: String? = null
}