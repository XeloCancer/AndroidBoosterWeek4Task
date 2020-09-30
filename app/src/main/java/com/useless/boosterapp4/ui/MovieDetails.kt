package com.useless.boosterapp4.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.useless.boosterapp4.R
import com.useless.boosterapp4.data.models.local.Movie
import com.useless.boosterapp4.data.models.remote.MovieReview
import com.useless.boosterapp4.data.models.remote.ReviewData
import com.useless.boosterapp4.data.repository.LocalRepo
import com.useless.boosterapp4.fragments.DescriptionFragment
import com.useless.boosterapp4.fragments.ReviewFragment
import com.useless.boosterapp4.fragments.TrailerFragment
import com.useless.boosterapp4.utils.MovieType
import com.useless.boosterapp4.utils.flagAs
import kotlinx.android.synthetic.main.fragment_review.*
import kotlinx.android.synthetic.main.movie_details.*
import java.util.*
import kotlin.collections.ArrayList

class MovieDetails : AppCompatActivity(), LocalRepo.MovieReviewsCallback {

    private var width : Int? = null
    private var height : Int? = null
    private val displayMetrics by lazy {
        DisplayMetrics()
    }
    private val descriptionFragment by lazy {
        DescriptionFragment()
    }
    private val reviewFragment by lazy {
        ReviewFragment()
    }
    private val trailerFragment by lazy {
        TrailerFragment()
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details)
        var movieID: Int = intent.getIntExtra("id", 0)
        var favMovie: Boolean = LocalRepo.getMovieFromDBase(movieID)!!.fav

        if(favMovie){
            fav_button.setImageResource(R.drawable.ic_baseline_favorite_24)
        }

        fav_button.setOnClickListener {
            if(favMovie){
                fav_button.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                var movie: Movie? = LocalRepo.getMovieFromDBase(intent.getIntExtra("id", -465))
                favMovie = false
                movie!!.flagAs(MovieType.UNFAV)
            }else if(!favMovie){
                fav_button.setImageResource(R.drawable.ic_baseline_favorite_24)
                var movie: Movie? = LocalRepo.getMovieFromDBase(intent.getIntExtra("id", -465))
                favMovie = true
                movie!!.flagAs(MovieType.FAV)
            }
        }

        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.descriptionItem -> {
                    makeCurrentFragment(descriptionFragment)
                    descriptionFragment.iPassData(intent.getStringExtra("overview"))
                }
                R.id.reviewItem -> {
                    LocalRepo.requestMovieReviews(this, intent.getIntExtra("id", -465))
                    makeCurrentFragment(reviewFragment)
                }
                R.id.trailerItem -> {
                    makeCurrentFragment(trailerFragment)
                    trailerFragment.iPassData(intent.getStringExtra("videoLink"))
                    }
            }
            true
        }
        makeCurrentFragment(reviewFragment)
        makeCurrentFragment(trailerFragment)
        makeCurrentFragment(descriptionFragment)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            this.display!!.getRealMetrics(displayMetrics)
        }
        else{
            @Suppress("DEPRECATION")
            windowManager.defaultDisplay.getMetrics(displayMetrics)
        }
        width = displayMetrics.widthPixels
        height = displayMetrics.heightPixels
        window.setLayout((width!! * 0.9).toInt(), (height!! * 0.9).toInt())

        val posterPath : String? = intent.getStringExtra("poster_path")
        val title : String? = intent.getStringExtra("title")
        val releaseDate : String? = intent.getStringExtra("release_date")
        val language : String? = intent.getStringExtra("language")!!.toUpperCase(Locale.ROOT)
        val rating : Int = intent.getIntExtra("rating_progress", 0)
        val voteCnt : String? = intent.getStringExtra("vote_count")
       // val overview : String? = intent.getStringExtra("overview")

        Picasso.get().load("https://image.tmdb.org/t/p/w300/${posterPath}").into(movie_poster_detail)
        movie_name.text = HtmlCompat.fromHtml("<b>Title:</b> <br> $title", HtmlCompat.FROM_HTML_MODE_LEGACY)
        release_date.text = HtmlCompat.fromHtml("<b>Release Date:</b> <br> $releaseDate", HtmlCompat.FROM_HTML_MODE_LEGACY)
        languages.text = HtmlCompat.fromHtml("<b>Languages:</b> <br> $language", HtmlCompat.FROM_HTML_MODE_LEGACY)
        rating_details.text = "$rating%"
        vote_count.text = voteCnt
        rating_progress_bar_details.progress = rating
     //   movie_overview.text = HtmlCompat.fromHtml(overview!!, HtmlCompat.FROM_HTML_MODE_LEGACY)
        this.setFinishOnTouchOutside(true)

        back_button.setOnClickListener { finish() }
    }

    override fun onStart() {
        super.onStart()
        bottom_nav.selectedItemId = R.id.descriptionItem
    }

    private  fun makeCurrentFragment(fragment : Fragment) =
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(
                R.anim.fragment_fade_enter,
                R.anim.fragment_fade_exit,
                R.anim.fragment_fade_enter,
                R.anim.fragment_fade_exit
            )
            replace(R.id.fragment_container,fragment)
            commit()
   }
   interface PassData {
       fun iPassData (data: String?)
   }

    interface PassReview {
        fun iPassReview (data: ArrayList<ReviewData>?)
    }

    override fun onMovieReviewsReady(reviewData: MovieReview) {
        reviewFragment.iPassReview(reviewData.data!!)
    }

    override fun onMovieReviewsError(errorMsg: String) {
        review_details.text = HtmlCompat.fromHtml("No reviews are available for this movie. You can visit themoviedb.org to add your review.", HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

}