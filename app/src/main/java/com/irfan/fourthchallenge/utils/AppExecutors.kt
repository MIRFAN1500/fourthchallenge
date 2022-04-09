package com.irfan.fourthchallenge.utils

import android.os.Handler
import android.os.Looper

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors constructor(
    private val diskIO: Executor = Executors.newSingleThreadExecutor(),
    private val mainThread: Executor = MainThreadExecutor()
) {
    
    fun diskIO(): Executor = diskIO

    fun mainThread(): Executor = mainThread

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}
