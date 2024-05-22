package com.example.bumiajimobileview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button

class MachineList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_machine_list)

        val machineButton = findViewById<Button>(R.id.machine_btn)
        machineButton.setOnClickListener {
            val intent = Intent(this, DataView::class.java)
            startActivity(intent)
        }
    }
}