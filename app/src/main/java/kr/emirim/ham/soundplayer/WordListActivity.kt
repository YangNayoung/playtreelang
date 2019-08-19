package kr.emirim.ham.soundplayer

import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_word_list.*
import kotlinx.android.synthetic.main.row_wordlist.*

internal var mediaPlayer: MediaPlayer? = null

class WordListActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    val MENU1 = 1
    val MENU2 = 2
    val MENU3 = 3
    val MENU4 = 4

    var youtubeid:String = "VeWR0whXPHI"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_list)
        this.title = "Play Three Language"
        val intent = getIntent()
        val mode = intent.getIntExtra("mode", MENU1)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        val wordsList = ArrayList<Word>()
        when(mode){
            MENU1 -> {
                title = "메뉴1"
                youtubeid = "jetP5n-TeOI"
                // TODO: 유튜브 링크 갱신
                wordsList.add(Word("Hello111111","안녕","Hi"
                    , R.raw.test, R.raw.test, R.raw.test))
                wordsList.add(Word("Hello1","안녕1","Hi1"))
                wordsList.add(Word("Hello2","안녕2","Hi2"))
                wordsList.add(Word("Hello3","안녕3","Hi3"))
            }
            MENU2 -> {
                title = "메뉴2"
                youtubeid = "prNyBUshXNs"
                // TODO: 유튜브 링크 갱신
                wordsList.add(Word("Hello2222222","안녕","Hi"))
                wordsList.add(Word("Hello1","안녕1","Hi1"))
                wordsList.add(Word("Hello2","안녕2","Hi2"))
                wordsList.add(Word("Hello3","안녕3","Hi3"))
            }
            MENU3 -> {
                title = "메뉴3"
                // TODO: 유튜브 링크 갱신
                youtubeid = "SpdNZKnTi68"
                wordsList.add(Word("Hello333333","안녕","Hi"))
                wordsList.add(Word("Hello1","안녕1","Hi1"))
                wordsList.add(Word("Hello2","안녕2","Hi2"))
                wordsList.add(Word("Hello3","안녕3","Hi3"))
            }
            MENU4 -> {
                title = "메뉴4"
                youtubeid = "DovQ8lIBpyQ"
                // TODO: 유튜브 링크 갱신
                wordsList.add(Word("Hello444444","안녕","Hi"))
                wordsList.add(Word("Hello1","안녕1","Hi1"))
                wordsList.add(Word("Hello2","안녕2","Hi2"))
                wordsList.add(Word("Hello3","안녕3","Hi3"))
            }
        }
        categoryTitle.text = title
        val rvAdapter = RvAdapter(wordsList)
        recyclerView.adapter = rvAdapter
        viewYoutube.initialize("AIzaSyA6L-XrMkC8RFWuE2XxweYKXJ_xu_RgWPA",this)
    }

    class RvAdapter(val wordList:ArrayList<Word>): RecyclerView.Adapter<RvAdapter.ViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.row_wordlist, parent, false)
            return ViewHolder(v)
        }

        override fun getItemCount(): Int {
            return wordList.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.imgEng.setImageResource(R.drawable.usa)
            holder.imgKor.setImageResource(R.drawable.kor)
            holder.imgMys.setImageResource(R.drawable.mys)
            holder.txtEng?.text = wordList[position].eng
            holder.txtKor?.text = wordList[position].kor
            holder.txtArb?.text = wordList[position].arb
            holder.btnEng.setOnClickListener {
                mediaPlayer?.let{it.stop()}
                mediaPlayer = MediaPlayer.create(holder.btnEng.context, wordList[position].engSnd)
                mediaPlayer?.let{it.start()}
            }
            holder.btnKor.setOnClickListener {
                mediaPlayer?.let{it.stop()}
                mediaPlayer = MediaPlayer.create(holder.btnKor.context, wordList[position].korSnd)
                mediaPlayer?.let{it.start()}
            }
            holder.btnArb.setOnClickListener {
                mediaPlayer?.let{it.stop()}
                mediaPlayer = MediaPlayer.create(holder.btnArb.context, wordList[position].arbSnd)
                mediaPlayer?.let{it.start()}
            }
        }

        class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
            val txtEng = itemView.findViewById<TextView>(R.id.txtEng)
            val txtKor = itemView.findViewById<TextView>(R.id.txtKor)
            val txtArb = itemView.findViewById<TextView>(R.id.txtArb)
            val btnEng = itemView.findViewById<Button>(R.id.btnEng)
            val btnKor = itemView.findViewById<Button>(R.id.btnKor)
            val btnArb = itemView.findViewById<Button>(R.id.btnArb)
            val imgEng = itemView.findViewById<ImageView>(R.id.engflag)
            val imgKor = itemView.findViewById<ImageView>(R.id.korflag)
            val imgMys = itemView.findViewById<ImageView>(R.id.mysflag)
        }
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        p1?.let{it.loadVideo(youtubeid)}
        //p1!!.loadVideo(youtubeid)
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {

    }

    override fun onBackPressed() {
        super.onBackPressed()
        mediaPlayer?.let{ it.stop() }
        finish()
    }
}
