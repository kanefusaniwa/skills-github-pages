package com.example.seisankanriapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.seisankanriapp.viewmodel.MainViewModel

@Composable

fun StartProcessScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    val employeeCode by mainViewModel.employeeCode.collectAsState() // StateFlow の場合
    val lotNumber by mainViewModel.lotNumber.collectAsState()

    var processNumber by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var equipmentCode by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp)) {
        // 画面上部に社員コードを表示
        if (employeeCode.isNotEmpty()) {
            Text("社員コード: $employeeCode", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))
        }

        // ロット番号の表示（編集不可）
        OutlinedTextField(
            value = lotNumber,
            onValueChange = { /* 入力を許可しないので空の処理 */ },
            label = { Text("ロット番号 (バーコード読み取り)") },
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("barcode_scanner") }) {
            Text("バーコードスキャン")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // 工程番号入力
        OutlinedTextField(
            value = processNumber,
            onValueChange = {
                if (it.length <= 10 && it.all { ch -> ch.isDigit() }) processNumber = it
            },
            label = { Text("工程番号 (1～9)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        // 数量入力
        OutlinedTextField(
            value = quantity,
            onValueChange = {
                if (it.length <= 4 && it.all { ch -> ch.isDigit() }) quantity = it
            },
            label = { Text("数量") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        // 設備コード入力
        OutlinedTextField(
            value = equipmentCode,
            onValueChange = {
                if (it.length <= 6 && it.all { ch -> ch.isDigit() }) equipmentCode = it
            },
            label = { Text("設備コード") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // 入力必須項目が揃ったら着手ボタンを表示
        if (lotNumber.isNotEmpty() && processNumber.isNotEmpty() && quantity.isNotEmpty() && equipmentCode.isNotEmpty()) {
            Button(onClick = {
                // API呼び出しと端末への着手データ保持の処理をここで実施
            }, modifier = Modifier.fillMaxWidth()) {
                Text("着手")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}


