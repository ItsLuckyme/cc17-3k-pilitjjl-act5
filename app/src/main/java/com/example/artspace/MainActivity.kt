package com.example.artspace

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentIndex", currentIndex)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentIndex = savedInstanceState.getInt("currentIndex")
        val imageView: ImageView = findViewById(R.id.artworkImageView)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)
        val authorTextView: TextView = findViewById(R.id.authorTextView)
        displayArtwork(imageView, descriptionTextView, authorTextView)
    }
    private val artworks = listOf(
        Artwork(R.drawable.pic1, "Girl With A Pearl Earring", "Johannes Vermeer(1665)"),
        Artwork(R.drawable.pic2, "Les Demoiselles D’Avignon", "Pablo Picasso(1907)"),
        Artwork(R.drawable.pic3, "American Gothic", "Grant Wood(1930)"),
        Artwork(R.drawable.pic4, " Gallerie dell’Accademia/Matteo De Fina.", "Giorgio Vasari, Charity (1541)"),
        Artwork(R.drawable.pic5, " Courtesy of Diane Rosenstein Gallery, Los Angeles, and Van Ham Art Estate, Cologne.", "Sarah Schumann, Untitled Shock Collage (1967)")
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView: ImageView = findViewById(R.id.artworkImageView)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)
        val authorTextView: TextView = findViewById(R.id.authorTextView)
        val nextButton: Button = findViewById(R.id.nextButton)
        val previousButton: Button = findViewById(R.id.previousButton)

        displayArtwork(imageView, descriptionTextView, authorTextView)

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % artworks.size
            displayArtwork(imageView, descriptionTextView, authorTextView)
        }
        previousButton.setOnClickListener {
            currentIndex = if (currentIndex - 1 < 0) artworks.size - 1 else currentIndex - 1
            displayArtwork(imageView, descriptionTextView, authorTextView)
        }
    }
    private fun displayArtwork(imageView: ImageView, descriptionTextView: TextView, authorTextView: TextView) {
        val currentArtwork = artworks[currentIndex]
        imageView.setImageResource(currentArtwork.imageResId)
        descriptionTextView.text = currentArtwork.description
        authorTextView.text = currentArtwork.author
    }
    data class Artwork(val imageResId: Int, val description: String, val author: String)
}

