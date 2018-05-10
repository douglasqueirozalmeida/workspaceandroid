package mobile.iesb.br.projetofinal.activitys

import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import mobile.iesb.br.projetofinal.R
import mobile.iesb.br.projetofinal.dao.AppDatabase
import mobile.iesb.br.projetofinal.entidade.Noticia

class HomeActivity : AppCompatActivity() {

    var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "room-database"
        ).allowMainThreadQueries().build()

        var noticias = db?.noticiaDao()?.findAll()
        noticias = noticias?.sortedByDescending { it.uid }

        val listView = findViewById<ListView>(R.id.listNoticias)
        listView.adapter = NoticiaListAdapter(this, noticias!!)
        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            val myIntent = Intent(this, DetalhaNoticiaActivity::class.java)
            myIntent.putExtra("itemSelecionado", adapterView.getItemAtPosition(position) as Noticia)
            startActivity(myIntent)
        }
    }

    public override fun onDestroy() {
        super.onDestroy()
    }
}

private class NoticiaListAdapter(paramContexto: Context, paramNoticias: List<Noticia>) : BaseAdapter()  {

    private val contexto: Context
    private var noticias: List<Noticia>

    init {
        contexto = paramContexto
        noticias = paramNoticias
    }

    override fun getItem(position: Int): Any {
        return noticias.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return noticias.size
    }

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(contexto)
        val noticiaRow = layoutInflater.inflate(R.layout.content_home, viewGroup,false)

        val tituloTextView = noticiaRow.findViewById<TextView>(R.id.textViewTituloNoticia)
        tituloTextView.text = noticias.get(position).titulo

        val dataTextView = noticiaRow.findViewById<TextView>(R.id.textViewDataNoticia)
        dataTextView.text = noticias.get(position).getDataString();

        val descricaotextView = noticiaRow.findViewById<TextView>(R.id.textViewTextoNoticia)
        descricaotextView.text = noticias.get(position).texto

        val imageViewNoticia= noticiaRow.findViewById<ImageView>(R.id.imageViewImagemNoticia)
        imageViewNoticia.setImageBitmap(noticias.get(position).retornaBitMapImage())

        return noticiaRow
    }
}
