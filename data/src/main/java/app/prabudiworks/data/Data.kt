package app.prabudiworks.data

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object Data {

    private var mPrefsName: String? = null
    private var mContext: Context? = null

    fun init(prefsName: String, context: Context? = null) {
        mPrefsName = prefsName
        mContext = context
    }

    fun getPrefsName() = mPrefsName

    fun getContext() = mContext

}