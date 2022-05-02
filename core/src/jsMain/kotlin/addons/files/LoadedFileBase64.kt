package de.peekandpoke.kraft.addons.files

import org.w3c.files.File

data class LoadedFileBase64(
    val file: File,
    val dataUrl: String?,
    val mimeType: String?,
    val dataBase64: String?,
) {
    companion object {
        fun of(file: File, dataUrl: String?): LoadedFileBase64 {

            if (dataUrl == null) {
                return LoadedFileBase64(file = file, dataUrl = null, mimeType = null, dataBase64 = null)
            }

            val regex = "data:(.*);.*,(.*)".toRegex()
            val match: MatchResult = regex.matchEntire(dataUrl) ?: error("Invalid data url given")

            return LoadedFileBase64(
                file = file,
                dataUrl = dataUrl,
                mimeType = match.groupValues[1],
                dataBase64 = match.groupValues[2],
            )
        }
    }
}
