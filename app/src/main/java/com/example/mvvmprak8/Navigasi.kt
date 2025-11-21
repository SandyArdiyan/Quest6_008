package com.example.mvvmprak8

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvmprak8.model.DataJk
import com.example.mvvmprak8.view.FormIsian
import com.example.mvvmprak8.view.TampilData
import com.example.mvvmprak8.viewmodel.SiswaViewModel

enum class Navigasi {
    Formulirku,
    Detail
}

@Composable
fun SiswaApp(
    modifier: Modifier = Modifier, // Tambahkan default value = Modifier
    viewModel: SiswaViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    Scaffold { isiRuang ->
        val uiState = viewModel.statusUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulirku.name,
            modifier = Modifier.padding(isiRuang)
        ) {
            // Halaman 1: Formulir
            composable(route = Navigasi.Formulirku.name) {
                val konteks = LocalContext.current

                // PERBAIKAN: Panggil FormIsian, bukan FormSiswa
                FormIsian(
                    // PERBAIKAN: Panggil DataJk.JenisK
                    pilihanJk = DataJk.JenisK.map { id -> konteks.resources.getString(id) },
                    onSubmitBtnClick = { ls ->
                        viewModel.setSiswa(ls)
                        navController.navigate(route = Navigasi.Detail.name)
                    }
                )
            }

            // Halaman 2: Tampil Data
            composable(route = Navigasi.Detail.name){
                // PERBAIKAN: Panggil TampilData, bukan TampilSiswa
                TampilData(
                    statusUiSiswa = uiState.value,
                    // PERBAIKAN: Sesuaikan nama parameter callback
                    onBackButtonClicked = {
                        navController.popBackStack(Navigasi.Formulirku.name, inclusive = false)
                    }
                )
            }
        }
    }
}