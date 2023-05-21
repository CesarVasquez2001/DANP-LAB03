package com.example.laboratorio03.Navigation

sealed class AppScreens(val route:String){
        object HOME : AppScreens("Home")
        object ADD : AppScreens("Add")
        object EDIT : AppScreens("Edit")
        object DELETE : AppScreens("Delete")
        object LIST : AppScreens("List")
        object EDITFORM : AppScreens("EditForm")


}
