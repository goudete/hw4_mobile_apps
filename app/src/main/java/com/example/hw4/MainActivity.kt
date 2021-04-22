package com.example.hw4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Recycler view stuff
        val adapter = getDreams()
        val recycler = findViewById<RecyclerView>(R.id.recycle)
        val linearLayoutManager = LinearLayoutManager(this)
        recycler.layoutManager = linearLayoutManager
        recycler.adapter = adapter

        // Create a new dream
        val button = findViewById<Button>(R.id.new_dream)
        button.setOnClickListener {
            val intent = Intent(this, NewDreamActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getDreams(): DreamAdapter? {
        val dao = AppDatabase.getDatabase(this).dao()
        val adapter = GlobalScope.async {
            val array = dao.getAll().toTypedArray()
            val adapter = DreamAdapter(array)
            return@async adapter
        }
        return runBlocking { adapter.await() }
    }

}