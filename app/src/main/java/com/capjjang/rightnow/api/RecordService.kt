package com.capjjang.rightnow.api

import com.capjjang.rightnow.model.RecordModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RecordService {
    @POST("/process_audio/")
    fun postTest(@Body postData: RecordModel): Call<PostTestModel>

}