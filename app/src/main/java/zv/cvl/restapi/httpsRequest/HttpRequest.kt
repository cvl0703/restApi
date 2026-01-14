package zv.cvl.restapi.httpsRequest


import zv.cvl.restapi.viewmodel.DataType
import java.net.HttpURLConnection
import java.net.URI

class HttpRequest(val url: String) {

    fun getData() : List<DataType>{
        var data: List<DataType>
        val rawData = getRawData()
        print(rawData)
        data = if (rawData.isNotEmpty()){
            var dataList: List<DataType> = emptyList()

            return dataList
        } else {
            emptyList()
        }
        return data
    }

    fun getRawData(): String{
        return try {
            val url = URI.create(url).toURL()
            val conn = url.openConnection() as HttpURLConnection
            var response = ""
            try {
                conn.requestMethod = "GET"
                conn.connectTimeout = 1000
                conn.readTimeout = 1000
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Android)")

                val responseCode = conn.responseCode

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
            "The url cannot be empty"
        }
    }

}