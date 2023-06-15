package fr.deuspheara.potterdbapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    companion object{
        private lateinit var appContext: Context

        fun applicationContext(): Application {
            return appContext as Application
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}