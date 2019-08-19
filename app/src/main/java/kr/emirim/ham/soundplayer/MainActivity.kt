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


class MainActivity : AppCompatActivity() {
    val MENU1 = 1
    val MENU2 = 2
    val MENU3 = 3
    val MENU4 = 4
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        val menuList = ArrayList<Menu>()
        menuList.add(Menu(R.drawable.menu1, "메뉴1", MENU1))
        menuList.add(Menu(R.drawable.menu2, "메뉴2", MENU2))
        menuList.add(Menu(R.drawable.menu3, "메뉴3", MENU3))
        menuList.add(Menu(R.drawable.menu4, "메뉴4", MENU4))
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
                val intent = Intent(it.context,WordListActivity::class.java)
                intent.putExtra("mode",menuList[position].mode)
                it.context.startActivity(intent)
            }
        }

        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val title = itemView.findViewById<TextView>(R.id.title)
            val img = itemView.findViewById<ImageView>(R.id.img)

        }
    }
}
