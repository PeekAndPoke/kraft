package de.peekandpoke.kraft.jsbridges

import de.peekandpoke.kraft.utils.jsObjectToMap
import kotlinx.js.Object

// TODO: move to ultra::security

@Suppress("FunctionName")
@JsModule("jwt-decode")
@JsNonModule
private external fun jwt_decode(jwt: String): Object?

fun decodeJwtBody(jwt: String): Map<String, Any?> {
    return jsObjectToMap(jwt_decode(jwt))
}

