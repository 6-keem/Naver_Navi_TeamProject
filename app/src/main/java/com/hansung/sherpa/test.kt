package com.hansung.sherpa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.hansung.sherpa.transit.PedestrianResponse
import com.hansung.sherpa.transit.PedestrianRouteRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface PedestrianRouteService {
    @Headers(
        "accept: application/json",
        "content-type: application/json",
    )
    @POST("tmap/routes/pedestrian")
    fun postPedestrianRoutes(@Header("appkey") appkey:String, @Body body: PedestrianRouteRequest):Call<ResponseBody>
}

fun getPedestrianRoute(routeRequest: PedestrianRouteRequest): LiveData<PedestrianResponse> {
    val resultLiveData = MutableLiveData<PedestrianResponse>()
    val appKey = BuildConfig.TMAP_APP_KEY // 앱 키
    Retrofit.Builder()
        .baseUrl("https://apis.openapi.sk.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(PedestrianRouteService::class.java).postPedestrianRoutes(appKey, routeRequest) // API 호출
        .enqueue(object : Callback<ResponseBody> {

            // 성공시 콜백
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                val responseBody = response.body()
                if (responseBody != null) {
                    // Deserialized 역직렬화
                    val pedestrianResponse = Gson().fromJson(responseBody.string(), PedestrianResponse::class.java)
                    // post to livedata (Change Notification) 변경된 값을 알림
                    resultLiveData.postValue(pedestrianResponse)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {}
        })
    return resultLiveData
}

fun main(){
    val pr = PedestrianRouteRequest(
        startX = "37.642636".toFloat(),
        startY = "127.835763".toFloat(),
        endX = "37.627448".toFloat(),
        endY = "126.829388".toFloat()
    )

    var res = getPedestrianRoute(pr)
    println(res)
}
