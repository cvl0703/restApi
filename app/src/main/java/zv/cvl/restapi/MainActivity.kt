package zv.cvl.restapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import zv.cvl.restapi.httpsRequest.httpRequest
import zv.cvl.restapi.ui.theme.RestApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RestApiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    userInterface(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun userInterface(modifier: Modifier) {
    var url by remember { mutableStateOf("") }
    var response by remember { mutableStateOf("") }
    var http = httpRequest(url)
    Column (modifier = modifier){
        TextField(
            value = "",
            onValueChange = { value: String -> url = value },
            modifier = Modifier.fillMaxWidth()
        )
        Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth()){
            Button(onClick = {response = http.getData()}) {Text("Request Data")}
        }
        Text(response, modifier= Modifier.fillMaxSize())
    }
}

