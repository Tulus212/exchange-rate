package app.prabudiworks.common.extension

import android.view.View

fun View.makeGone() = if (visibility != View.GONE) visibility = View.GONE else Unit
fun View.makeVisible() = if (visibility != View.VISIBLE) visibility = View.VISIBLE else Unit
fun View.makeInvisible() = if (visibility != View.INVISIBLE) visibility = View.INVISIBLE else Unit
