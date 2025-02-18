package com.example.seisankanriapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.seisankanriapp.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavController) {
    val employeeCode by viewModel.employeeCode.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (employeeCode.isNotEmpty()) {
            Text(text = "社員コード: $employeeCode", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
        }

        Button(onClick = { navController.navigate("employeeCode") }) {
            Text("社員コード入力")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("chakuForm") }, enabled = employeeCode.isNotEmpty()) {
            Text("着手フォームへ")
        }
    }
}
