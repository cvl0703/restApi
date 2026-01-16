package zv.cvl.restapi

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import zv.cvl.restapi.httpsRequest.HttpRequest
import zv.cvl.restapi.ui.theme.RestApiTheme
import zv.cvl.restapi.viewmodel.Repository
import zv.cvl.restapi.viewmodel.ViewModelClass

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RestApiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UserInterface(Modifier.padding(innerPadding), applicationContext)
                }
            }
        }
    }
}

private fun transformData(data: List<String>): String{
    var response = ""
    data.forEach { item ->
        response += item
    }
    return response
}

@Composable
fun UserInterface(modifier: Modifier, context: Context) {
    var url by remember { mutableStateOf("") }
    val repo = Repository(context)
    val viewModel: ViewModelClass = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                Log.d("asd",url)
                return ViewModelClass(repo) as T
            }
        }
    )
    val data: List <String> by viewModel.content.collectAsState()

    val scrollState = rememberScrollState()
    Column (modifier = modifier){
        TextField(
            value = url,
            onValueChange = { value -> url = value },
            modifier = Modifier.fillMaxWidth()
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {viewModel.delData()}) {Text("Delete Data")}
            Button(onClick = {viewModel.getRawData(url)}) {Text("Request Data")}
        }
        Text(transformData(data), modifier= Modifier.fillMaxSize().verticalScroll(scrollState))
    }
    LaunchedEffect(Unit) {
        viewModel.readLines()
    }

}

