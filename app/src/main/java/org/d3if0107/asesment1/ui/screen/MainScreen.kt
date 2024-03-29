package org.d3if0107.asesment1.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.d3if0107.asesment1.R
import org.d3if0107.asesment1.ui.theme.Asesment1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            )
        )
    }) { padding ->
        ScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier) {
    var namaCustomer by remember { mutableStateOf("") }
    var nomerCustomer by remember { mutableStateOf("") }
    var jumlahSepatu by remember { mutableStateOf("") }

    var totalHarga by remember { mutableStateOf("") }

    val radioOptions = listOf(
        stringResource(id = R.string.hari_1),
        stringResource(id = R.string.hari_2),
        stringResource(id = R.string.hari_3),
    )
    var day by remember { mutableStateOf(radioOptions[0]) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.laundry_intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = namaCustomer,
            onValueChange = { namaCustomer = it },
            label = { Text(text = stringResource(id = R.string.nama_customer)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = nomerCustomer,
            onValueChange = { nomerCustomer = it },
            label = { Text(text = stringResource(id = R.string.nomer_customer)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = jumlahSepatu,
            onValueChange = { jumlahSepatu = it },
            label = { Text(text = stringResource(id = R.string.jumlah_sepatu)) },
            trailingIcon = { Text(text = "pcs") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .padding(top = 6.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            radioOptions.forEach { text ->
                DayOption(
                    label = text,
                    isSelected = day == text,
                    modifier = Modifier
                        .selectable(
                            selected = day == text,
                            onClick = { day = text },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }
        Row (
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Button(
                onClick = {
                    namaCustomer = ""
                    nomerCustomer = ""
                    jumlahSepatu = ""
                    day = radioOptions[0]
                    totalHarga = ""
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.reset))
            }
            Button(
                onClick = {
                    val hargaSepatu = 20000
                    val jumlahSepatuInt = jumlahSepatu.toIntOrNull() ?: 0
                    val hargaLamaPengerjaan = when (day) {
                        radioOptions[0] -> 10000 // Harga tambahan untuk 1 hari
                        radioOptions[1] -> 5000 // Harga tambahan untuk 2 hari
                        else -> 0
                    }
                    val hargaTotal = (hargaSepatu + hargaLamaPengerjaan) * jumlahSepatuInt
                    totalHarga = hargaTotal.toString()
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.hitung))
            }
        }
        if (totalHarga.isNotEmpty()) {
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Text(
                text = stringResource(id = R.string.nama_customer_x) + namaCustomer,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = stringResource(id = R.string.nomer_customer_x) + nomerCustomer,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = stringResource(id = R.string.lama_pengerjaan) + day,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = stringResource(id = R.string.total) + totalHarga,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun DayOption(label: String, isSelected: Boolean, modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = isSelected, onClick = null)
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    Asesment1Theme {
        MainScreen()
    }
}