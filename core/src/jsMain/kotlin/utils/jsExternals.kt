package de.peekandpoke.kraft.utils

import org.khronos.webgl.Uint8Array

external fun encodeURIComponent(value: String): String

external fun decodeURIComponent(value: String): String

/**
 * Decodes a base64 encoded string("atob" should be read as "ASCII to binary").
 *
 * See https://developer.mozilla.org/en-US/docs/Glossary/Base64
 */
external fun atob(value: String): Uint8Array

/**
 * Encodes a base-64 encoded ASCII string from a "string" of binary data ("btoa" should be read as "binary to ASCII").
 *
 * See https://developer.mozilla.org/en-US/docs/Glossary/Base64
 */
external fun btoa(value: String): String
