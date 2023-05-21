package com.example.laboratorio03.Screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import com.example.laboratorio03.Database.User
import com.example.laboratorio03.Database.UserDatabase
import com.example.laboratorio03.ui.theme.Laboratorio03Theme

@Composable
fun List(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar() {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow back",
                modifier = Modifier.clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Lista de asistentes")
        }
    }) {
        Greeting5(navController)
    }
}

@Composable
fun Greeting5(navController: NavController) {

    val context = LocalContext.current


    val db =
        Room.databaseBuilder(context, UserDatabase::class.java, "userdata").allowMainThreadQueries()
            .build()

    val userDao = db.userDao()

    val users = userDao.getAllUsers()

    LazyColumn(
        contentPadding = PaddingValues(all = 20.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),

        ) {
        items(users) { item ->
            ListItemRow(item)
        }
    }

}

@Composable
fun ListItemRow(user: User) {
    var more = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.small)
            .background(color = MaterialTheme.colors.secondary)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {

                Text(
                    text = user.id.toString() + ". " + user.name,
                    style = MaterialTheme.typography.body2
                )
                if (more.value) {
                    Text(
                        text = "Monto pagado: " + user.amountPaid + "\n"
                                + "Telefono: " + user.phone + "\n"
                                + "Fecha registro: "+ user.registrationDate + "\n"
                                + "Email: "+user.email + "\n"
                                + "Tipo de sangre: "+user.bloodType,
                    )
                }

            }
            OutlinedButton(onClick = {
                /*TODO*/
                more.value = !more.value
            }) {

                Text(if (more.value) "Ocultar" else "Mas")
            }
        }

    }
}

