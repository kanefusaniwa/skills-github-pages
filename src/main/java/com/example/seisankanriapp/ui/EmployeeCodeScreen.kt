package com.example.seisankanriapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.seisankanriapp.viewmodel.MainViewModel

@Composable
fun EmployeeCodeScreen(navController: NavController, viewModel: MainViewModel) {
    var code by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "社員コードを入力してください", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = code,
            onValueChange = { code = it },
            label = { Text("社員コード (5桁)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (code.length == 5) {
                    viewModel.setEmployeeCode(code)
                    navController.navigate("main")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = code.length == 5
        ) {
            Text("登録")
        }
    }
}



