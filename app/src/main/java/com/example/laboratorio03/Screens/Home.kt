package com.example.laboratorio03.Screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.laboratorio03.Navigation.AppScreens
import com.example.laboratorio03.ui.theme.Laboratorio03Theme


@Composable
fun Home(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar() {
            Spacer(modifier = Modifier.width(8.dp))

            Text(text = "Congreso")
        }
    }) {
        Greeting(navController)
    }
}

@Composable
fun Greeting(navController: NavController) {

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registro de asistencia",
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp),
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            /* Handle button click */
            navController.navigate(route = AppScreens.ADD.route)

        }) {
            Text(text = "Nuevo asistente")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            /* Handle button click */

            navController.navigate(route = AppScreens.EDIT.route)
        }) {
            Text(text = "Modificacion")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate(route = AppScreens.DELETE.route)
            /* Handle button click */
        }) {
            Text(text = "Eliminar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate(route = AppScreens.LIST.route)
            /* Handle button click */
        }) {
            Text(text = "Listar")
        }
    }


}



