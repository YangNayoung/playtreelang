package kr.emirim.ham.soundplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

val MENU1 = 1
val MENU2 = 2
val MENU3 = 3
val MENU4 = 4
val APPINFO = 98
val TEAMINFO = 99

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        val menuList = ArrayList<Menu>()
        menuList.add(Menu(R.drawable.menu1, "Number", MENU1))
        menuList.add(Menu(R.drawable.menu2, "Color", MENU2))
        menuList.add(Menu(R.drawable.menu3, "메뉴3", MENU3))
        menuList.add(Menu(R.drawable.menu4, "메뉴4", MENU4))
        menuList.add(Menu(R.drawable.menu4, "About PTL", APPINFO))
        menuList.add(Menu(R.drawable.menu4, "Team KoreYsia", TEAMINFO))
        val rvAdapter = RvAdapter(menuList)
        recyclerView.adapter = rvAdapter

    }

    class RvAdapter(val menuList:ArrayList<Menu>): RecyclerView.Adapter<RvAdapter.ViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.row_menulist,
                parent, false)
            return ViewHolder(v)
        }

        override fun getItemCount(): Int {
            return menuList.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.title?.text = menuList[position].title
            holder.img?.setImageResource(menuList[position].img)
            holder.itemView.setOnClickListener {

                when(menuList[position].mode){
                    APPINFO -> {
                        val intent = Intent(it.context,AppInfoActivity::class.java)
                        it.context.startActivity(intent)
                    }
                    TEAMINFO ->{
                        val intent = Intent(it.context,TeamInfoActivity::class.java)
                        it.context.startActivity(intent)
                    }
                    else->{
                        val intent = Intent(it.context,WordListActivity::class.java)
                        intent.putExtra("mode",menuList[position].mode)
                        it.context.startActivity(intent)
                    }
                }
            }
        }

        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val title = itemView.findViewById<TextView>(R.id.title)
            val img = itemView.findViewById<ImageView>(R.id.img)

        }
    }
}
