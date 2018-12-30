package concertrip.sopt.com.concertrip.utillity

import android.util.Log
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import concertrip.sopt.com.concertrip.deprecated.PostIdCheckResponse
import concertrip.sopt.com.concertrip.deprecated.PostLoginResponse
import concertrip.sopt.com.concertrip.interfaces.OnResponse
import concertrip.sopt.com.concertrip.network.NetworkService
import concertrip.sopt.com.concertrip.network.USGS_REQUEST_URL
import concertrip.sopt.com.concertrip.network.response.MessageResponse
import concertrip.sopt.com.concertrip.network.response.interfaces.BaseModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkUtil {

    companion object {


        private const val LOG_SUBSCRIBE_ARTIST = "/api/subscribe/artist"
        fun subscribeArtist(networkService : NetworkService, listener : OnResponse?, _id : String){
            val jsonObject = JSONObject()
            jsonObject.put(USGS_REQUEST_URL.JSON_ARTIST_ID,_id)
            val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject

            Log.d(Constants.LOG_NETWORK, "$LOG_SUBSCRIBE_ARTIST, POST :$gsonObject")
            val subscribeArtist: Call<MessageResponse> =
                networkService.postSubScribeArtist(gsonObject)
            subscribeArtist.enqueue(object : Callback<MessageResponse> {

                override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                    Log.e(Constants.LOG_NETWORK, t.toString())
                    listener?.onFail()
                }
                //통신 성공 시 수행되는 메소드
                override fun onResponse(call: Call<MessageResponse>, response: Response<MessageResponse>) {
                    Log.d(Constants.LOG_NETWORK, response.errorBody()?.string()?:response.message())

                    if (response.isSuccessful) {
                        Log.d(Constants.LOG_NETWORK, "$LOG_SUBSCRIBE_ARTIST :${response.body()}")
                        listener?.onSuccess(response.body() as BaseModel)
                    }else{
                        Log.d(Constants.LOG_NETWORK, "$LOG_SUBSCRIBE_ARTIST : fail")
                    }
                }
            })
        }


        private const val LOG_SUBSCRIBE_GENRE = "/api/subscribe/genre"
        fun subscribeGenre(networkService : NetworkService, listener : OnResponse?, _id : String){
            val jsonObject = JSONObject()
            jsonObject.put(USGS_REQUEST_URL.JSON_GENRE_ID,_id)
            val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject

            Log.d(Constants.LOG_NETWORK, "$LOG_SUBSCRIBE_GENRE, POST :$gsonObject")

            val subscribeGenre: Call<MessageResponse> =
                networkService.postSubScribeArtist(gsonObject)
            subscribeGenre.enqueue(object : Callback<MessageResponse> {

                override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                    Log.e(Constants.LOG_NETWORK, t.toString())
                    listener?.onFail()
                }
                //통신 성공 시 수행되는 메소드
                override fun onResponse(call: Call<MessageResponse>, response: Response<MessageResponse>) {
                    Log.d(Constants.LOG_NETWORK, response.errorBody()?.string()?:response.message())

                    if (response.isSuccessful) {
                        Log.d(Constants.LOG_NETWORK, "$LOG_SUBSCRIBE_GENRE :${response.body()}")
                        listener?.onSuccess(response.body() as BaseModel)
                    }else{
                        Log.d(Constants.LOG_NETWORK, "$LOG_SUBSCRIBE_GENRE : fail")
                    }
                }
            })
        }



        private const val LOG_SUBSCRIBE_CONCERT = "/api/subscribe/concert"
        fun subscribeConcert(networkService : NetworkService, listener : OnResponse?, _id : String){
            val jsonObject = JSONObject()
            jsonObject.put(USGS_REQUEST_URL.JSON_CONCERT_ID,_id)
            val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject

            Log.d(Constants.LOG_NETWORK, "$LOG_SUBSCRIBE_CONCERT, POST :$gsonObject")

            val subscribeGenre: Call<MessageResponse> =
                networkService.postSubScribeArtist(gsonObject)
            subscribeGenre.enqueue(object : Callback<MessageResponse> {

                override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                    Log.e(Constants.LOG_NETWORK, t.toString())
                    listener?.onFail()
                }
                //통신 성공 시 수행되는 메소드
                override fun onResponse(call: Call<MessageResponse>, response: Response<MessageResponse>) {
                    Log.d(Constants.LOG_NETWORK, response.errorBody()?.string()?:response.message())

                    if (response.isSuccessful) {
                        Log.d(Constants.LOG_NETWORK, "$LOG_SUBSCRIBE_CONCERT :${response.body()}")
                        listener?.onSuccess(response.body() as BaseModel)
                    }else{
                        Log.d(Constants.LOG_NETWORK, "$LOG_SUBSCRIBE_CONCERT: fail")
                    }
                }
            })
        }



        fun testRetrofit1(networkService : NetworkService,listener : OnResponse?){
            val jsonObject = JSONObject()
            jsonObject.put("id","heesung6701@naver.com")
            val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject

            Log.d(Constants.LOG_NETWORK, "postIdCheck :$gsonObject")
            val postSignUpResponse: Call<PostIdCheckResponse> =
                networkService.postIdCheck(gsonObject)
            postSignUpResponse.enqueue(object : Callback<PostIdCheckResponse> {

                override fun onFailure(call: Call<PostIdCheckResponse>, t: Throwable) {
                    Log.e(Constants.LOG_NETWORK, t.toString())
                    listener?.onFail()
                }
                //통신 성공 시 수행되는 메소드
                override fun onResponse(call: Call<PostIdCheckResponse>, response: Response<PostIdCheckResponse>) {
                    Log.d(Constants.LOG_NETWORK, response.errorBody()?.string()?:response.message())

                    if (response.isSuccessful) {
                        Log.d(Constants.LOG_NETWORK, "postLogin :${response.body()}")
//                        listener?.onSuccess(response.body() as BaseModel)
                    }else{
                        Log.d(Constants.LOG_NETWORK, "postLogin : fail")
                    }
                }
            })
        }
        fun testREtrofit2(networkService: NetworkService,listener: OnResponse?){
            val jsonObject = JSONObject()
            jsonObject.put("id","teamkerbell@teamkerbell.tk")
            jsonObject.put("pwd","12341234")
            jsonObject.put("client_token","")
            val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject

            Log.d(Constants.LOG_NETWORK, "postLogin :$gsonObject")
            val postLoginResponse: Call<PostLoginResponse> =
                networkService.postLogin(gsonObject)
            postLoginResponse.enqueue(object : Callback<PostLoginResponse> {

                override fun onFailure(call: Call<PostLoginResponse>, t: Throwable) {
                    Log.e("sign up fail", t.toString())
                }
                //통신 성공 시 수행되는 메소드
                override fun onResponse(call: Call<PostLoginResponse>, response: Response<PostLoginResponse>) {
                    Log.d(Constants.LOG_NETWORK, response.toString())
                    if (response.isSuccessful) {
                        Log.d(Constants.LOG_NETWORK, "postLogin :${response.body()}")
//                        listener?.onSuccess()
                    }
                    else{
                        Log.d(Constants.LOG_NETWORK, "postLogin : fail")
                    }
                }
            })
        }

    }
}