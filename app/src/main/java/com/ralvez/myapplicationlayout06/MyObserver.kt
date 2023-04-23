package com.ralvez.myapplicationlayout06


import android.content.ContentValues.TAG
import android.util.Log
import androidx.core.content.PackageManagerCompat.LOG_TAG
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyObserver:LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
        Log.i(TAG,"Main Created")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun show(){
        Log.i(TAG,"Main Visible")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun hide(){
        Log.i(TAG,"Main HIDE")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onClose(){
        Log.i(TAG,"Main CLose")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        Log.i(TAG,"Main Pause")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){
        Log.i(TAG,"Main resume")
    }

}
