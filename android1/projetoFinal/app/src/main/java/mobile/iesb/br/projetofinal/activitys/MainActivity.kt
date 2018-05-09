package mobile.iesb.br.projetofinal.activitys

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import mobile.iesb.br.projetofinal.R
import mobile.iesb.br.projetofinal.dao.AppDatabase
import mobile.iesb.br.projetofinal.entidade.Noticia
import java.util.*

class MainActivity : AppCompatActivity() {

    var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        var esqueceuSenha = findViewById<TextView>(R.id.textViewEsqueceuSenha)

        esqueceuSenha.setOnClickListener(View.OnClickListener {
            val myIntent = Intent(this, EsqueceuSenhaActivity::class.java)
            startActivity(myIntent)
        })

//        db = Room.databaseBuilder(
//                applicationContext,
//                AppDatabase::class.java, "room-database"
//        ).allowMainThreadQueries().build()
//
//
//        cadastraNoticia()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun direnciaEsqueceuSenha(v: View) {

    }

    private fun cadastraNoticia() {
        var noticias = db?.noticiaDao()?.findAll()

        if (noticias?.size == 0) {

            db?.noticiaDao()?.insertNoticia(Noticia(0, "Titulo1", Date(), "Descricao Titulo1", ""))
            db?.noticiaDao()?.insertNoticia(Noticia(0, "Titulo2", Date(), "Descricao Titulo2", ""))
            db?.noticiaDao()?.insertNoticia(Noticia(0, "Titulo3", Date(), "Descricao Titulo3", ""))
            db?.noticiaDao()?.insertNoticia(Noticia(0, "Titulo4", Date(), "Descricao Titulo4", ""))
            db?.noticiaDao()?.insertNoticia(Noticia(0, "Titulo5", Date(), "Descricao Titulo5", ""))
            db?.noticiaDao()?.insertNoticia(Noticia(0, "Titulo6", Date(), "Descricao Titulo6", ""))
            db?.noticiaDao()?.insertNoticia(Noticia(0, "Titulo7", Date(), "Descricao Titulo7", ""))
            db?.noticiaDao()?.insertNoticia(Noticia(0, "Titulo8", Date(), "Descricao Titulo8", ""))
            db?.noticiaDao()?.insertNoticia(Noticia(0, "Titulo9", Date(), "Descricao Titulo9", ""))
            db?.noticiaDao()?.insertNoticia(Noticia(0, "Titulo10", Date(), "Descricao Titulo10", ""))

            noticias = db?.noticiaDao()?.findAll()
        }

        noticias = noticias?.sortedByDescending { it.uid }

        Toast.makeText(applicationContext, noticias.toString(), Toast.LENGTH_LONG).show()

    }
}
