package za.co.varsitycollege.st10487313.imad5112_practicum1

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    //Four parrallel arrays
    var songs = arrayOf("I miss you", "Jack and Jill", "Easy Easy", "Come back to Earth")
    var artists = arrayOf("Blink-182", "BLP Kosher", "King Krule", "Mac Miller")
    var ratings = arrayOf(3, 5, 4, 2)
    var comments = arrayOf("Sad Song", "Super Chill", "Nostalgia", "Rap")


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val buttonAdd = findViewById<Button>(R.id.buttonAdd)
            val buttonDetails = findViewById<Button>(R.id.buttonDetails)
            val buttonExit = findViewById<Button>(R.id.buttonExit)

            buttonAdd.setOnClickListener {
                addToPlaylist()
            }

            buttonDetails.setOnClickListener {
                val intent = Intent(this, DetailsActivity::class.java)
                //Pass the arrays to the next activity
                intent.putExtra("songs", songs)
                intent.putExtra("artists", artists)
                intent.putExtra("ratings", ratings.toIntArray())
                intent.putExtra("comments", comments)
                startActivity(intent)
            }

            buttonExit.setOnClickListener {
                finish()
            }
        }

    private fun addToPlaylist(){
        val titleInput = findViewById<EditText>(R.id.editTitle)
        val artistInput = findViewById<EditText>(R.id.editArtist)
        val ratingInput = findViewById<EditText>(R.id.editRating)
        val commentInput = findViewById<EditText>(R.id.editComment)

        val title = titleInput.text.toString()
        val artist = artistInput.text.toString()
        val rating = ratingInput.text.toString().toIntOrNull()
        val comment = commentInput.text.toString()

        if (title.isEmpty() || artist.isEmpty() || rating == null || comment.isEmpty()) {
            Toast.makeText(this, "Please fill in all the fields properly!", Toast.LENGTH_SHORT).show()
            return
        }
        if (rating !in 1..5) {
            Toast.makeText(this, "Rating must be between 1 and 5!", Toast.LENGTH_SHORT).show()
            return
        }
        //Expand each array to include new entry
        songs = songs + title
        artists = artists + artist
        ratings = ratings + rating
        comments = comments + comment

        Toast.makeText(this, "New Song Added!", Toast.LENGTH_SHORT).show()

        //Clear input fields
        titleInput.text.clear()
        artistInput.text.clear()
        ratingInput.text.clear()
        commentInput.text.clear()

    }

    }



