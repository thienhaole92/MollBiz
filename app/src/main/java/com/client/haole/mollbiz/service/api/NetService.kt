package com.client.haole.mollbiz.service.api

import com.client.haole.mollbiz.model.AndMol
import com.client.haole.mollbiz.model.JsonResult
import com.client.haole.mollbiz.model.VideoMol
import com.client.haole.mollbiz.model.VideoMoreMol
import io.reactivex.Flowable
import org.jetbrains.annotations.NotNull
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetService {

    @GET("data/{type}/{page}/{pagesize}")
    fun getAndroid(@NotNull @Path("type") type: String,
                   @Path("page") page: Int,
                   @Path("pagesize") pagesize: Int): Flowable<JsonResult<MutableList<AndMol>>>

    @GET("v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getVideoList(): Flowable<MutableList<VideoMol>>

    @GET("v3/videos")
    fun getVideoMoreList(@Query("categoryName") categoryName: String, @Query("strategy") strategy: String,
                         @Query("udid") udid: String, @Query("vc") vc: Int): Flowable<VideoMoreMol>

    @GET("v3/videos")
    fun getVideoMoreList1(@Query("start") start: Int, @Query("num") num: Int,
                          @Query("categoryName") categoryName: String, @Query("strategy") strategy: String): Flowable<VideoMoreMol>

}