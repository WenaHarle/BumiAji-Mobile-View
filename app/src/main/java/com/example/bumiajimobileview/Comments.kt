package com.example.bumiajimobileview

data class Comments(
    val id: Int,
    val grade: String,
    val berat: Int,
    val panjang: Int,
    val lebar: Int,
    val tinggi: Int,
    val timestamp: String,
    val machine_id: String,
    val photo_atas_url: String,
    val photo_samping_url: String,
    val timeupdate: String
)
