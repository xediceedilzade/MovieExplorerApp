package com.example.movieexplorerapp

import kotlinx.serialization.descriptors.SerialDescriptor

data class Movie (
    val id:Int,
    val title:String,
    val rating: Double,
    val year: Int,
    val genre: String,
    val duration: String,
    val description: String,
    val image: Int,

)