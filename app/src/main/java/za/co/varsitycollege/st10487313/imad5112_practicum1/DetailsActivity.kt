package za.co.varsitycollege.st10487313.imad5112_practicum1

import android.icu.util.Output
import android.media.Rating
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.findViewTreeViewModelStoreOwner

class DetailsActivity : AppCompatActivity() {

    private lateinit var txtOutput: TextView
    private lateinit var buttonShowSongs: Button
    private lateinit var buttonAvgRating: Button
    private lateinit var buttonBack: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)

        txtOutput = findViewById(R.id.txtOutput)
        buttonShowSongs = findViewById(R.id.buttonShowSongs)
        buttonAvgRating = findViewById(R.id.buttonAvgRating)
        buttonBack = findViewById(R.id.buttonBack)

        //Recieve arrays from MainActivity via intent
        val songs = intent.getStringArrayExtra("songs") ?: arrayOf()
        val artists = intent.getStringArrayExtra("artists") ?: arrayOf()
        val ratings = intent.getIntArrayExtra("ratings") ?: intArrayOf()
        val comments = intent.getStringArrayExtra("comments") ?: arrayOf()

        buttonShowSongs.setOnClickListener {
            val builder = StringBuilder()
            //Display all songs and Details
            val count = minOf(songs.size, artists.size, ratings.size, comments.size)
            for (i in 0 until count){
                builder.append("${songs[i]}\n")
                builder.append("Artist: ${artists[i]}\n")
                builder.append("Rating: ${ratings[i]}\n")
                builder.append("Comment: ${comments[i]}\n\n")
            }
            txtOutput.text = builder.toString()
        }

        buttonAvgRating.setOnClickListener {

            if (ratings.isNotEmpty()) {
                val avg = ratings.average()
                txtOutput.text = "Average Rating: %.2f".format(avg)

            }    else {

                txtOutput.text = "No Ratings to Calculate"
            }

        }

        buttonBack.setOnClickListener {
            finish() //Return to MainA
        }



    }

}