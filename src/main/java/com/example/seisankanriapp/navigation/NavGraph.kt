package com.example.seisankanriapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.seisankanriapp.ui.EmployeeCodeScreen
import com.example.seisankanriapp.ui.MainScreen
import com.example.seisankanriapp.ui.StartProcessScreen
import com.example.seisankanriapp.ui.BarcodeScannerScreen
import com.example.seisankanriapp.viewmodel.MainViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    // MainViewModel を取得（Activityスコープで保持される ViewModel）
    val mainViewModel: MainViewModel = viewModel()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(viewModel = mainViewModel, navController = navController)
        }
        composable("employee_code") {
            EmployeeCodeScreen(navController = navController, viewModel = mainViewModel)
        }
        composable("start_process") {
            // 社員コードが登録されていれば表示
            StartProcessScreen(
                navController = navController,
                employeeCode = mainViewModel.employeeCode.value ?: ""
            )
        }
        composable("barcode_scanner") {
            BarcodeScannerScreen(navController = navController) { scannedCode ->
                // 例として、BarcodeScannerScreen で取得したロット番号を ViewModel にセットする
                mainViewModel.setLotNumber(scannedCode)
                navController.popBackStack()
            }
        }
    }
}
