package com.example.atividade2tadsantonio.models

class Person {
    var name: String = "";
    var mail: String = "";
    var phone: String = "";
    var showNotification: Boolean = false;
    var preferences: MutableList<Preference> = mutableListOf();
    var gender: Gender? = null;
}