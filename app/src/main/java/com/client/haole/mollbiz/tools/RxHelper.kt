package com.client.haole.mollbiz.tools

import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxHelper {

    companion object {

        fun <T> singleModeThread(subscribeThread: io.reactivex.Scheduler? = Schedulers.io(),
                                 unSubscribeThread: io.reactivex.Scheduler? = Schedulers.io(),
                                 observeThread: io.reactivex.Scheduler? = AndroidSchedulers.mainThread()): FlowableTransformer<T, T> {
            return FlowableTransformer { flowable ->
                flowable.onErrorResumeNext(NetExceptionHandler.HttpResponseFunc())
                        .retry(1)
                        .subscribeOn(subscribeThread!!)
                        .unsubscribeOn(unSubscribeThread)
                        .observeOn(observeThread)


            }
        }

        fun <T> listModeThread(subscribeThread: io.reactivex.Scheduler? = Schedulers.io(),
                               unSubscribeThread: io.reactivex.Scheduler = Schedulers.io(),
                               observeThread: io.reactivex.Scheduler? = AndroidSchedulers.mainThread()): FlowableTransformer<MutableList<T>,
                MutableList<T>> {
            return FlowableTransformer { observable ->
                observable.onErrorResumeNext(NetExceptionHandler.HttpResponseFunc())
                        .retry(1)
                        .subscribeOn(subscribeThread!!)
                        .unsubscribeOn(unSubscribeThread)
                        .observeOn(observeThread)
            }
        }

    }

}