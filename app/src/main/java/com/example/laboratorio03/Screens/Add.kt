package com.example.laboratorio03.Screens

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import com.example.laboratorio03.Database.*
import java.util.*


@Composable
fun Add(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar() {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow back",
                modifier = Modifier.clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Agregar asistente")
        }
    }) {
        Greeting2(navController)
    }
}

@Composable
fun Greeting2(navController: NavController) {

    val context = LocalContext.current


    var name by remember { mutableStateOf("") }
    var bloodType by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var amountPaid by remember { mutableStateOf("") }

    //Date
    var registrationDate by remember { mutableStateOf("") }
    val year: Int
    val month: Int
    val day: Int
    val mCalendar = Calendar.getInstance()
    year = mCalendar.get(Calendar.YEAR)
    month = mCalendar.get(Calendar.MONTH)
    day = mCalendar.get(Calendar.DAY_OF_MONTH)
    val mDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, year: Int, month: Int, day: Int ->
            registrationDate = "$day/${month + 1}/$year"
        }, year, month, day
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = name,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = "Ingrese nombre completo") },
            onValueChange = {
                name = it
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.align(Alignment.Center)) {
                OutlinedTextField(
                    value = registrationDate,
                    onValueChange = {
                        registrationDate = it
                    },
                    readOnly = true,
                    label = { Text(text = "Ingrese fecha de inscripcion") },
                )
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(4.dp)
                        .clickable { mDatePickerDialog.show() }
                )

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = bloodType,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = "Ingrese tipo de sangre") },
            onValueChange = {
                bloodType = it
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = phone,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            label = { Text(text = "Ingrese numero telefonico") },
            onValueChange = {
                phone = it
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = { Text(text = "Ingrese correo") },
            onValueChange = {
                email = it
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = amountPaid,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(text = "Ingrese monto pagado") },
            onValueChange = {
                amountPaid = it
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {

            val db = Room.databaseBuilder(context, UserDatabase::class.java, "userdata").allowMainThreadQueries().build()

            val userDao = db.userDao()

            if (!name.isEmpty()&&!registrationDate.isEmpty()&&!bloodType.isEmpty()&&!phone.isEmpty()&&!email.isEmpty()&&!amountPaid.isEmpty()){

                val user = User(
                    id = 0,
                    name = name,
                    registrationDate = registrationDate,
                    bloodType = bloodType,
                    phone = phone,
                    email = email,
                    amountPaid = amountPaid
                )
                userDao.insertUser(user)

                navController.popBackStack()

            }else{
                Toast.makeText(context, "Falta completar", Toast.LENGTH_SHORT).show()
            }

        }) {
            Text("Enviar")
        }
    }
}

