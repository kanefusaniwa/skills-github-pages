package com.example.seisankanriapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.seisankanriapp.ui.theme.SeisanKanriAppTheme
import com.example.seisankanriapp.viewmodel.MainViewModel


import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.seisankanriapp.ui.EmployeeCodeScreen
import com.example.seisankanriapp.ui.MainScreen
import com.example.seisankanriapp.ui.StartProcessScreen
import com.example.seisankanriapp.ui.BarcodeScannerScreen  // BarcodeScannerScreen のパッケージ名に合わせる


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val mainViewModel: MainViewModel = viewModel() // MainViewModel を作成
            AppNavigation(mainViewModel) // AppNavigation に ViewModel を渡す
        }
    }
}

@Composable
fun AppNavigation(mainViewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "main") {
        composable("main") { MainScreen( mainViewModel,navController) }
        composable("employee_code") { EmployeeCodeScreen(navController, mainViewModel) }
        composable("start_process") { StartProcessScreen(navController, mainViewModel) }
        composable("barcode_scanner") {
            BarcodeScannerScreen(navController) { scannedCode ->
                // スキャンしたバーコードデータを ViewModel に保存
                mainViewModel.setLotNumber(scannedCode)
                // StartProcessScreen に戻る
                navController.popBackStack()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SeisanKanriAppTheme {
        Greeting("Android")
    }
}