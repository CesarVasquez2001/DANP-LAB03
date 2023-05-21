package com.example.laboratorio03.Database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val registrationDate: String,
    val bloodType: String,
    val phone: String,
    val email: String,
    val amountPaid: String

)
