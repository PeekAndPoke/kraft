package de.peekandpoke.kraft.jsbridges

import de.peekandpoke.kraft.utils.jsObjectToMap
import kotlinx.js.Object

/**
 * Decodes the [jwt] and returns a javascript object
 */
fun decodeJwt(jwt: String): Object? {

    console.log(jwt_decode)

    return jwt_decode.default(jwt)
}

/**
 * Decodes the [jwt] and returns a kotlin [Map]
 */
fun decodeJwtAsMap(jwt: String): Map<String, Any?> {
    return jsObjectToMap(
        decodeJwt(jwt)
    )
}
