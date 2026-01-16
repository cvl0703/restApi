package zv.cvl.restapi.viewmodel

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.File
import java.net.HttpURLConnection
import java.net.URI

class Repository (private val context: Context){
    val fileName = "content.txt"

    private fun getFile(): File {
        return File(context.filesDir, fileName)
    }

    suspend fun getContent(): List<String> {
        return withContext(Dispatchers.IO) {
            val file = getFile()
            if (file.exists()) {
                file.readLines()
            } else {
                emptyList()
            }
        }
    }

    suspend fun writeContent(data: String) {
        return withContext(Dispatchers.IO) {
            val file = getFile()
            file.delete()
            file.appendText(data)
        }
    }

    suspend fun delContent() {
        return withContext(Dispatchers.IO){
            writeContent("")
        }
    }
}