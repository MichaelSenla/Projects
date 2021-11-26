package com.senla.notebooksenla

import android.content.Context
import java.io.File

class NoteFile(private val context: Context) {

    companion object {
        const val FILE_TXT = ".txt"
    }

    private val file: File = File(context.filesDir, MainActivity.DOCUMENTS)

    fun generateFile(noteName: String): File {
        return File("${context.filesDir}${File.separator}" +
                    "${MainActivity.DOCUMENTS}${File.separator}" + noteName + FILE_TXT)
    }

    fun getFileContentByPosition(position: Int) = file.listFiles()?.get(position)?.readText()

    fun editFile(position: Int, text: String) {
        file.listFiles()?.get(position)?.writeText(text)
    }
}