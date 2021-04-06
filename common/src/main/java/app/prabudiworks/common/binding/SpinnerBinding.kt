package app.prabudiworks.common.binding

import android.R
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

@BindingAdapter("entries")
fun Spinner.entries(values: List<String>?) {
    values?.let {
        adapter = ArrayAdapter(
            context,
            R.layout.simple_spinner_dropdown_item,
            R.id.text1,
            values
        )
    }
}

@BindingAdapter(value = ["selectedValue", "selectedValueAttrChanged"], requireAll = false)
fun Spinner.selectedValue(
    newSelectedValue: String?,
    newTextAttrChanged: InverseBindingListener
) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            newTextAttrChanged.onChange()
        }

        override fun onNothingSelected(parent: AdapterView<*>) {}
    }
    if (adapter != null && newSelectedValue != null) {
        val pos = (adapter as ArrayAdapter<String>).getPosition(newSelectedValue)
        setSelection(pos, true)
    }
}

@InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
fun Spinner.selectedValueAttrChanged(): String {
    return this.selectedItem as String
}