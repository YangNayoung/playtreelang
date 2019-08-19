package kr.emirim.ham.soundplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_word_list.*

class TeamInfoActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    var youtubeid:String = "yTJoPu69buA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_info)

        //this.title = "KoreYsia TeamInfo"
        categoryTitle.text = "Team Info : KoreaYsia"
        viewYoutube.initialize("AIzaSyA6L-XrMkC8RFWuE2XxweYKXJ_xu_RgWPA",this)
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        p1?.let{it.loadVideo(youtubeid)}
        //p1!!.loadVideo(youtubeid)
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {

    }

}
