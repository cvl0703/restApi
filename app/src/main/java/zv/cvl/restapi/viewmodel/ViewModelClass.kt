package zv.cvl.restapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import zv.cvl.restapi.httpsRequest.HttpRequest

class ViewModelClass(private val repository: Repository, url: String): ViewModel()  {

    private val _content = MutableStateFlow<List<String>>(emptyList())
    val content = _content.asStateFlow()
    private val http = HttpRequest(url)

    fun getRawData(){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                http.getRawData()
            }
            writeLine(result)
            readLines()
        }
    }

    fun readLines() {
        viewModelScope.launch {
            _content.value = repository.getContent()
        }
    }

    fun writeLine(data: String) {
        viewModelScope.launch {
            repository.writeContent(data)
        }
    }
    fun delData(){
        viewModelScope.launch {
            repository.delContent()
            readLines()
        }
    }
}