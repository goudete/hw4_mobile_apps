package com.example.hw4

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class NewDreamActivity : AppCompatActivity() {

    var title: String? = null
    var content: String? = null
    var reflection: String? = null
    var emotion: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_dream)

        // Grab user input, if it exists
        title = findViewById<EditText>(R.id.title).toString()
        content = findViewById<EditText>(R.id.content).toString()
        reflection = findViewById<EditText>(R.id.reflection).toString()
        emotion = findViewById<Spinner>(R.id.emotions).toString()

        // Grab button
        val btn = findViewById<Button>(R.id.new_dream_btn)
        // Handle onClick
        btn.setOnClickListener{onClick()}

    }

    private fun onClick() {
        if(isValidInputs()) {
            val dao = AppDatabase.getDatabase(this).dao()
            val insert = GlobalScope.async {
                val length = dao.getAll().size
                return@async dao.insertDream(Dream(length + 1, title, content, reflection, emotion))
            }
            GlobalScope.launch {
                val inserted = insert.await()
                val intent = Intent(baseContext, MainActivity::class.java)
                startActivity(intent)
            }
        } else {
            Toast.makeText(this, "Empty inputs, please fill out everything", Toast.LENGTH_LONG).show()
        }
    }

    private fun isValidInputs(): Boolean {
        if (title == null || content == null || reflection == null || emotion == null){
            return false
        }
        return true
    }
}