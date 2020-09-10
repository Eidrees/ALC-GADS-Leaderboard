package ng.edu.ibbu.gadsleaderboard.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

private const val GADS_BASE_URL = "https://gadsapi.herokuapp.com/api/"
private const val SUBMISSION_BASE_URL = "https://docs.google.com/forms/d/e/"

private val moshi = Moshi
    .Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val loggingInterceptor = HttpLoggingInterceptor()
    .setLevel(HttpLoggingInterceptor.Level.BODY);

val okHttpClient = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .addInterceptor(loggingInterceptor)
    .build();


private val retrofit = Retrofit.Builder()
    .baseUrl(GADS_BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

private val retrofitSUb = Retrofit.Builder()
    .baseUrl(SUBMISSION_BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

interface GADSApi {

    @retrofit2.http.Headers("Content-Type: application/json", "Accept: application/json")
    @GET("hours")
    fun getLearningLeaders(): Deferred<List<Learner>>

    @retrofit2.http.Headers("Content-Type: application/json", "Accept: application/json")
    @GET("skilliq")
    fun getSkillLeaders(): Deferred<List<LearnerSkillIQ>>
}


interface SubmissionAPI {
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    fun submit(
        @Field("entry.1824927963") email_address: String,
        @Field("entry.1877115667") first_name: String,
        @Field("entry.2006916086") lastName: String,
        @Field(" entry.284483984") link_to_project: String
    ): Call<Void>

}

object Network {

    val gadsService: GADSApi by lazy {
        retrofit.create(GADSApi::class.java)
    }

    val submissionService: SubmissionAPI by lazy {
        retrofitSUb.create(SubmissionAPI::class.java)
    }
}

