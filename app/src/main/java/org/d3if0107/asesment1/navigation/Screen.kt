package org.d3if0107.asesment1.navigation


sealed class Screen (val route: String){
    data object Home: Screen("mainScreen")
}