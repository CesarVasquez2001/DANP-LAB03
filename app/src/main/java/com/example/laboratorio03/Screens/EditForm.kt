package com.example.laboratorio03.Screens

import android.app.DatePickerDialog
import android.widget.Toast
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import com.example.laboratorio03.Database.User
import com.example.laboratorio03.Database.UserDatabase
import java.util.*


@Composable
fun EditForm(navController: NavController, text: String?) {
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
        GreetingEdit(navController, text)
    }
}

@Composable
fun GreetingEdit(navController: NavController, text: String?) {
    val intId = Integer.parseInt(text)
    val context = LocalContext.current
    val db =
        Room.databaseBuilder(context, UserDatabase::class.java, "userdata").allowMainThreadQueries()
            .build()
    val userDao = db.userDao()
    val getUser = userDao.getUser(intId)


    var name by remember { mutableStateOf(getUser.name) }
    var bloodType by remember { mutableStateOf(getUser.bloodType) }
    var phone by remember { mutableStateOf(getUser.phone) }
    var email by remember { mutableStateOf(getUser.email) }
    var amountPaid by remember { mutableStateOf(getUser.amountPaid) }

    //Date
    var registrationDate by remember { mutableStateOf(getUser.registrationDate) }
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


            if (!name.isEmpty() && !registrationDate.isEmpty() && !bloodType.isEmpty() && !phone.isEmpty() && !email.isEmpty() && !amountPaid.isEmpty()) {


                val user = User(
                    id = intId,
                    name = name,
                    registrationDate = registrationDate,
                    bloodType = bloodType,
                    phone = phone,
                    email = email,
                    amountPaid = amountPaid
                )
                userDao.updateUser(user)

                navController.popBackStack()

            } else {
                Toast.makeText(context, "Falta completar", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Editar")
        }
    }
}

