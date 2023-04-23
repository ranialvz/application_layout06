package com.ralvez.myapplicationlayout06

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class MyLifecycleObserver : LifecycleEventObserver {

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                Log.i(ContentValues.TAG,"Event ON_CREATE")
                MyDataTest.myLife.add("ON_CREATE")
            }
            Lifecycle.Event.ON_START -> {
                Log.i(ContentValues.TAG,"Event ON_START")
                MyDataTest.myLife.add("ON_START")
            }
            Lifecycle.Event.ON_RESUME -> {
                Log.i(ContentValues.TAG,"Event ON_RESUME")
                MyDataTest.myLife.add("ON_RESUME")
            }
            Lifecycle.Event.ON_PAUSE -> {
                Log.i(ContentValues.TAG,"Event ON_PAUSE ")
                MyDataTest.myLife.add("ON_PAUSE ")
            }
            Lifecycle.Event.ON_STOP -> {
                Log.i(ContentValues.TAG,"Event ON_STOP")
                MyDataTest.myLife.add("ON_STOP")
            }
            Lifecycle.Event.ON_DESTROY -> {
                Log.i(ContentValues.TAG,"Event ON_DESTROY")
                MyDataTest.myLife.add("ON_DESTROY")
            }
            else -> {
                // Handle other lifecycle events if needed
            }
        }
    }
}
