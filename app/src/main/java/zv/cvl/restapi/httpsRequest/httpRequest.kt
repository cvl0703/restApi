package zv.cvl.restapi.httpsRequest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import zv.cvl.restapi.viewmodel.DataType

class httpRequest(val url: String) {

    fun getData() : List<DataType>{
        var data: List<DataType>
        val rawData = getRawData()
        data = if (rawData.isNotEmpty()){
            emptyList()
        } else {
            emptyList()
        }
        return data
    }

    private fun getRawData() : String{
        return ""
    }

}