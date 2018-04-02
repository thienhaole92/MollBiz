package com.client.haole.mollbiz.service.impl

import com.client.haole.mollbiz.model.AndMol
import com.client.haole.mollbiz.model.JsonResult
import io.reactivex.Flowable
import org.jetbrains.annotations.NotNull
import retrofit2.http.GET
import retrofit2.http.Path

interface NetService {

    @GET("data/{type}/{page}/{pagesize}")
    fun getAndroid(@NotNull @Path("type") type: String,
                   @Path("page") page: Int,
                   @Path("pagesize") pagesize: Int):Flowable<JsonResult<MutableList<AndMol>>>
}