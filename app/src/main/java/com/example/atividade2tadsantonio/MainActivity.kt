package com.example.atividade2tadsantonio

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.atividade2tadsantonio.databinding.ActivityMainBinding
import com.example.atividade2tadsantonio.models.Gender
import com.example.atividade2tadsantonio.models.Person
import com.example.atividade2tadsantonio.models.Preference

class MainActivity : AppCompatActivity() {
    var male: Gender = Gender("Masculino");
    var female: Gender = Gender("Feminino");
    var music: Preference = Preference("Musica");
    var cinema: Preference = Preference("Cinema");
    var sport: Preference = Preference("Esporte");
    var gastronomy: Preference = Preference("Gastronomia");

    var person: Person = Person();

    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.show.setOnClickListener{ show() }
        binding.clean.setOnClickListener{ clean() }
    }

    private fun clean() {
        binding.result.visibility = View.INVISIBLE;
        binding.radioButtonGender1.isChecked = false;
        binding.radioButtonGender1.isActivated = false;
        binding.radioButtonGender2.isChecked = false;
        binding.radioButtonGender2.isActivated = false;
        binding.checkBoxPref1.isChecked = false;
        binding.checkBoxPref2.isChecked = false;
        binding.checkBoxPref3.isChecked = false;
        binding.checkBoxPref4.isChecked = false;
        binding.editName.text = null;
        binding.editMail.text = null;
        binding.editPhone.text = null;
        binding.switchPref.isChecked = false;
    }

    private fun setGender() {
        person.gender = null;
        if (binding.radioButtonGender1.isChecked) {
            person.gender = male;
        }

        if (binding.radioButtonGender2.isChecked) {
            person.gender = female;
        }
    }

    private fun setPreferences() {
        person.preferences.clear();

        if (binding.checkBoxPref1.isChecked) {
            person.preferences.add(music);
        }

        if (binding.checkBoxPref2.isChecked) {
            person.preferences.add(cinema);
        }

        if (binding.checkBoxPref3.isChecked) {
            person.preferences.add(sport);
        }

        if (binding.checkBoxPref4.isChecked) {
            person.preferences.add(gastronomy);
        }
    }

    private fun bindPersonData() {
        person.name = binding.editName.text.toString();
        person.mail = binding.editMail.text.toString();
        person.phone = binding.editPhone.text.toString();
        person.showNotification = binding.switchPref.isChecked;
        setGender()
        setPreferences()
    }

    private fun show() {
        bindPersonData();
        binding.resultGender.text = "Genero: " + if (person.gender?.name != null) person.gender?.name else "";
        binding.resultMail.text = "Mail: " + person.mail;
        binding.resultName.text = "Nome: " + person.name;
        binding.resultPhone.text = "Telefone: " + person.phone;
        if (person.showNotification) {
            binding.resultNotification.text = "Mostrar Notificacao: " + "Sim";
        } else {
            binding.resultNotification.text = "Mostrar Notificacao: " + "Não";
        }

        var pref = ""
        for (preference in person.preferences) {
            pref += " " + preference.name
        }

        binding.resultPref.text = "Preferencias: " + pref

        binding.result.visibility = View.VISIBLE;
    }
}