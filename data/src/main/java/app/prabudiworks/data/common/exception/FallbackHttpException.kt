package app.prabudiworks.data.common.exception

import app.prabudiworks.data.common.AppResponse
import app.prabudiworks.data.common.AppResponseList
import app.prabudiworks.data.common.AppResponseMessage

class FallbackHttpException : Exception {

    constructor(message: String) : super(message)

    constructor(data: AppResponseMessage) : super(mapErrorCode(data.success))

    constructor(data: AppResponse<*>) : super(mapErrorCode(data.success))

    constructor(data: AppResponseList<*>) : super(mapErrorCode(data.success))

    companion object {

        fun mapErrorCode(code: Boolean?) = when (code) {
            false -> "Server error."
            else -> "Server error."
        }

    }

}