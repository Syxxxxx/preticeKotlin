package com.example.lenovo.myapplication.newapp

class RootData {
    var ret: Int = 0

    internal var data: Data? = null

    var msg: String? = null
}

internal class Data {
    var err_code: Int = 0

    var err_msg: String? = null

    var info: Info? = null
}

internal class Info{
    var id:Int = 0
    var uuid: String? = null

    var file_name: String? = null

    var file_type: String? = null

    var file_size: Int = 0

    var file_url: String? = null

    var is_deleted: Int = 0

    var add_time: String? = null

    var ext_data: String? = null
}