package mobile.iesb.br.projetofinal.activitys

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import mobile.iesb.br.projetofinal.R
import mobile.iesb.br.projetofinal.util.ValidaUtil

class EsqueceuSenhaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_esqueceu_senha)


        var botaoRecuperar = findViewById<Button>(R.id.buttonRecuperar)

        botaoRecuperar.setOnClickListener { view ->
            var email = findViewById<EditText>(R.id.editTextEmailRecuperaSenha)

            if (ValidaUtil.isEmailValido(email)) {
                this.isEmailExistente()
            }
        }
    }

    private fun isEmailExistente() {
        var email = findViewById<EditText>(R.id.editTextEmailRecuperaSenha)

        var usuarioRef = FirebaseDatabase.getInstance().getReference()

        usuarioRef.child("usuarios").orderByChild("email").equalTo(email.text.toString()).addListenerForSingleValueEvent(object: ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot){
                var isEmailExistente = false
                for(usuario: DataSnapshot in dataSnapshot.children) {
                    isEmailExistente = true

                }

                if(isEmailExistente) {
                    Toast.makeText(applicationContext, "E-mail de recuperação enviado com sucesso.", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(applicationContext, "E-mail não cadastrado.", Toast.LENGTH_LONG).show()
                    email.text.clear()
                }

            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

}
