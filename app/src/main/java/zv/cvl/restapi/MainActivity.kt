package zv.cvl.restapi

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import zv.cvl.restapi.httpsRequest.httpRequest
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
                    userInterface(Modifier.padding(innerPadding), applicationContext)
                }
            }
        }
    }
}

@Composable
fun userInterface(modifier: Modifier, context: Context) {
    var url by remember { mutableStateOf("") }
    var response by remember { mutableStateOf("") }
    var http = httpRequest(url)
    val repo = Repository(context)
    val viewModel = ViewModelClass(repo)
    Column (modifier = modifier){
        TextField(
            value = url,
            onValueChange = { value -> url = value },
            modifier = Modifier.fillMaxWidth()
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {viewModel.delData()}) {Text("Delete Data")}
            Button(onClick = {viewModel.writeLine(http.getData())}) {Text("Request Data")}
        }
        Text(response, modifier= Modifier.fillMaxSize())
    }
}

