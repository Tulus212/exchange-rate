package app.prabudiworks.exchangerate

import android.app.Application
import app.prabudiworks.common.Common
import app.prabudiworks.data.Data
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Common.init(BR.vm)
        Data.init("${packageName}.preferences", this)
    }

}