package zv.cvl.restapi.httpsRequest


import android.util.Log
import zv.cvl.restapi.viewmodel.DataType
import java.net.HttpURLConnection
import java.net.URI

class HttpRequest(var url: String) {

    fun getData() : List<DataType>{
        return emptyList()
    }

    suspend fun getRawData(): String{
        return try {
            Log.d("response",url)
            val url = URI.create(url).toURL()

            val conn = url.openConnection() as HttpURLConnection
            var response = ""
            try {
                conn.requestMethod = "GET"
                conn.connectTimeout = 5000
                conn.readTimeout = 5000
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Android)")

                val responseCode = conn.responseCode
                Log.d("response",responseCode.toString())

                if (responseCode == HttpURLConnection.HTTP_OK) { // HTTP_OK es 200
                    response = conn.inputStream.bufferedReader().readText()
                    conn.disconnect()
                    return response
                }
            } finally {
                conn.disconnect()
            }
            return response
        }catch (e: Exception){
            e.message ?: "error"
        }
    }

}