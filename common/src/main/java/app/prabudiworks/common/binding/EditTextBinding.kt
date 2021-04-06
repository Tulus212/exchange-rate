package app.prabudiworks.common.binding

import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("onEditorAction")
fun EditText.onImeAction(callback: Runnable) {
    this.setOnEditorActionListener { _, _, _ ->
        callback.run()
        true
    }
}