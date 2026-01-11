package zv.cvl.restapi.httpsRequest

import zv.cvl.restapi.viewmodel.DataType

class httpRequest(val url: String) {

    fun getData() : List<DataType>{
        var data: List<DataType>
        val rawData: String = getRawData()
        if (rawData.isNotEmpty()){
            data = emptyList()
        } else {
            data = emptyList()
        }
        return data
    }

    private fun getRawData() : String{
        return ""
    }

}