package com.example.mvvmprak8.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mvvmprak8.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormIsian(
    pilihanJk: List<String>,
    onSubmitBtnClick: (MutableList<String>) -> Unit,
    modifier: Modifier = Modifier
){
    var txtNama by rememberSaveable { mutableStateOf("") }
    var txtAlamat by rememberSaveable { mutableStateOf("") }
    var txtNGender by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.home), color = Color.White) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = colorResource(id = R.color.teal_700)
                )
            )
        }
    ) { isiRuang ->
        Column(
            modifier = Modifier
                .padding(isiRuang)
                .fillMaxSize(), // Pastikan fill max size
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = txtNama,
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.padding_medium))
                    .fillMaxWidth(0.8f), // Gunakan fillMaxWidth agar responsif
                label = { Text(text = stringResource(R.string.nama_lengkap)) },
                onValueChange = { txtNama = it },
            )

            OutlinedTextField(
                value = txtAlamat,
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(0.8f),
                label = { Text(text = "Alamat Lengkap") },
                onValueChange = { txtAlamat = it },
            )

            // Radio Button Group
            Row(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                pilihanJk.forEach { item ->
                    Row(
                        modifier = Modifier.selectable(
                            selected = txtNGender == item,
                            onClick = { txtNGender = item }
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (txtNGender == item),
                            onClick = { txtNGender = item }
                        )
                        Text(text = item)
                    }
                }
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(30.dp),

                onClick = {
                    // PERBAIKAN LOGIKA: Masukkan data ke list SAAT tombol diklik
                    val dataFinal = mutableListOf(txtNama, txtNGender, txtAlamat)
                    onSubmitBtnClick(dataFinal)
                }
            ){
                Text(text = stringResource(id = R.string.submit))
            }
        }
    }
}