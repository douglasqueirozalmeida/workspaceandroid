package mobile.iesb.br.projetofinal.activitys

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import mobile.iesb.br.projetofinal.R

import kotlinx.android.synthetic.main.activity_cadastro.*

class EsqueceuSenhaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_esqueceu_senha)
        setSupportActionBar(toolbar)
    }

}
