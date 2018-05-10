package mobile.iesb.br.projetofinal.activitys

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import mobile.iesb.br.projetofinal.R
import mobile.iesb.br.projetofinal.dao.AppDatabase
import mobile.iesb.br.projetofinal.entidade.Usuario
import mobile.iesb.br.projetofinal.util.ValidaUtil

class MainActivity : AppCompatActivity() {

    var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val myIntent = Intent(this, CadastroActivity::class.java)
            startActivity(myIntent)
        }

        var esqueceuSenha = findViewById<TextView>(R.id.textViewEsqueceuSenha)

        esqueceuSenha.setOnClickListener { view ->
            val myIntent = Intent(this, EsqueceuSenhaActivity::class.java)
            startActivity(myIntent)
        }


        var botaoEntrar = findViewById<TextView>(R.id.buttonEntrar)

        botaoEntrar.setOnClickListener { view ->
            if (validaInputs() && isUsuarioValido()) {
                val myIntent = Intent(this, HomeActivity::class.java)
                startActivity(myIntent)
            }
        }

        db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "room-database"
        ).allowMainThreadQueries().build()

        cadastraUsuario()
    }

    private fun isUsuarioValido(): Boolean {
        var email = findViewById<EditText>(R.id.editTextEmailLogin)
        var senha = findViewById<EditText>(R.id.editTextSenhaLogin)

        var usuario = db?.usuarioDao()?.findByEmailSenha(email.text.toString(), senha.text.toString())

        if (usuario == null) {

            Toast.makeText(applicationContext, "Dados Incorretos", Toast.LENGTH_LONG).show()
            return false
        }

        return true
    }

    private fun validaInputs(): Boolean {
        var email = findViewById<EditText>(R.id.editTextEmailLogin)
        var senha = findViewById<EditText>(R.id.editTextSenhaLogin)

        var isEmailValido = ValidaUtil.isEmailValido(email)
        var isSenhaVazia = ValidaUtil.isEmpty(senha)

        return isEmailValido && !isSenhaVazia
    }

    private fun cadastraUsuario() {
        var email = "admin@admin.com"
        var senha = "senha"
        var usuarioAdmin = db?.usuarioDao()?.findByEmailSenha(email, senha)

        if (usuarioAdmin == null) {
            db?.usuarioDao()?.insertUsuario(Usuario(0, "admin", email, "", senha, 0, 6199999999))
        }
    }


}
