package app.prabudiworks.common.binding

import android.os.Build
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import app.prabudiworks.common.R
import app.prabudiworks.common.extension.asPx
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import java.io.File

@BindingAdapter(
    "url",
    "file",
    "src",
    "placeholderLoading",
    "placeholderError",
    "useGif",
    "asCircle",
    "asRounded",
    requireAll = false
)
fun ImageView.loadImage(
    url: String? = null,
    file: File? = null,
    @DrawableRes imgSrc: Int? = null,
    loadingPlaceholder: Int? = null,
    errorPlaceholder: Int? = null,
    useGif: Boolean = false,
    asCircle: Boolean = false,
    asRounded: Boolean = false
) {
    val builder: ImageRequest.Builder.() -> Unit = {
        if (Build.VERSION.SDK_INT >= 28) decoder(ImageDecoderDecoder())
        else if (useGif) decoder(GifDecoder())

        placeholder(loadingPlaceholder ?: R.drawable.img_placeholder)
        error(errorPlaceholder ?: R.drawable.img_placeholder)
        if (asCircle) transformations(CircleCropTransformation())
        else if (asRounded) transformations(RoundedCornersTransformation(8f.asPx()))
    }

    url?.let { load(it, builder = builder) }
        ?: file?.let { load(it, builder = builder) }
        ?: imgSrc?.let { load(it, builder = builder) }
}