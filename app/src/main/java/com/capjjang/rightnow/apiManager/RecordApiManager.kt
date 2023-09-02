package com.example.rightnow.apiManager

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.capjjang.rightnow.api.RecordService
import com.capjjang.rightnow.model.PostTestModel
import com.capjjang.rightnow.model.RecordModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class RecordApiManager {
    private var retrofit: Retrofit? = null
    private var retrofitService: RecordService? = null

    var _resultLivedata: MutableLiveData<String> = MutableLiveData()
    val resultLivedata: LiveData<String>
        get() = _resultLivedata

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        // 다른 인터셉터 또는 설정들을 추가할 수 있습니다.
        .build()


    companion object {  // DCL 적용한 싱글톤 구현
        var instance: RecordApiManager? = null
        fun getInstance(context: Context?): RecordApiManager? {
            if (instance == null) {
                @Synchronized
                if (instance == null)
                    instance = RecordApiManager()
            }
            return instance
        }
    }

    init {
        retrofit = Retrofit.Builder()
            .baseUrl("http://15.164.224.196:8000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitService = retrofit?.create(RecordService::class.java)
    }

    fun postTest(postData: RecordModel){
        val resultData: Call<PostTestModel>? = retrofitService?.postTest(postData)
        resultData?.enqueue(object : Callback<PostTestModel> {
            override fun onResponse(
                call: Call<PostTestModel>,
                response: Response<PostTestModel>
            ) {
                if (response.isSuccessful) {
                    val result: PostTestModel = response.body()!!
                    Log.d("[mmihye]","서버응답 : "+result.predicted_alphabet)
                    _resultLivedata.postValue(result.predicted_alphabet)

                } else {
                    Log.d("resultt", "실패코드_${response.code()}")
                }
            }

            override fun onFailure(call: Call<PostTestModel>, t: Throwable) {
                t.printStackTrace()
                Log.d("resultt","통신 실패")
            }
        })
    }
}