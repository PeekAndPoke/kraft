@file:Suppress("FunctionName")

package de.peekandpoke.kraft.jsbridges

import js.core.Object

// TODO: move to ultra::security

@JsModule("jwt-decode")
@JsNonModule
external object jwt_decode {
    fun default(jwt: String): Object?
}
