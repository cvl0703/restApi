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
        val url = URI.create(url).toURL()
        val conn = url.openConnection() as HttpURLConnection
        val responseCode: String
        try {
            conn.requestMethod = "GET"
            conn.connectTimeout = 1000
            conn.readTimeout = 1000
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Android)")
            responseCode = conn.inputStream.bufferedReader().readText()
        } finally {
            conn.disconnect()
        }
        return responseCode
    }

}