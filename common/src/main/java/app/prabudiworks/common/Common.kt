package app.prabudiworks.common

object Common {

    private var mViewModelBindingId: Int? = null

    /**
     * Pass the default generated binding ID (for view model), better place it in onCreate Application
     * lifecycle.
     *
     * Example :
     *
     * Common.init(BR.vm)
     */
    fun init(viewModelBindingId: Int) {
        mViewModelBindingId = viewModelBindingId
    }

    fun getViewModelBindingId() = mViewModelBindingId

}