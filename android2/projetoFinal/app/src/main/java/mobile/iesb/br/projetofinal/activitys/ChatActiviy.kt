package mobile.iesb.br.projetofinal.activitys

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import mobile.iesb.br.projetofinal.R
import mobile.iesb.br.projetofinal.entidade.Mensagem
import java.util.Date

class ChatActivity : AppCompatActivity() {
    var dadosFirebase = mutableListOf<Mensagem>()
    lateinit var recyclerView: RecyclerView
    lateinit var adaptador: AdaptadorMensagem
    lateinit var txtMsg: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        txtMsg = findViewById(R.id.txtMsg)

        val btn = findViewById<Button>(R.id.btnSend)
        btn.setOnClickListener {
            enviar()
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()

        adaptador = AdaptadorMensagem(this)
        recyclerView.adapter = adaptador

        recuperarMensagens()
    }

    private fun enviar() {
        val db = FirebaseDatabase.getInstance()
        val uuid = Date().time
        val dbRef = db.getReference("/mensagem/$uuid")
        var sessao = getSharedPreferences("username", Context.MODE_PRIVATE)
        var email = sessao.getString("emailLogin", " ")
        val m = Mensagem(txtMsg.text.toString(),email, uuid)
        dbRef.setValue(m)
        txtMsg = findViewById(R.id.txtMsg)
        txtMsg.setText("")
    }

    private fun recuperarMensagens() {
        val db = FirebaseDatabase.getInstance()
        val dbRef = db.getReference("/mensagem")
        dbRef.orderByChild("data")
        dbRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                dadosFirebase.clear()
                snapshot.child(""). children.forEach { snap ->
                    val mensagem = snap.getValue(Mensagem::class.java)
                    dadosFirebase.add(mensagem!!)
                    Log.d("FIREBASE", mensagem.toString())
                    adaptador.setData(dadosFirebase)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

}

class AdaptadorMensagem(private val cxt: Context) : RecyclerView.Adapter<MensagemViewHolder>() {

    private var dadosFirebase = mutableListOf<Mensagem>()

    fun setData(dados: MutableList<Mensagem>) {
        dadosFirebase = dados
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensagemViewHolder {
        val v = LayoutInflater.from(cxt).inflate(R.layout.celula, parent, false)
        return MensagemViewHolder(v)
    }

    override fun onBindViewHolder(holder: MensagemViewHolder, position: Int) {
        val mensagem = dadosFirebase[position]
        var sessao = cxt.getSharedPreferences("username", Context.MODE_PRIVATE)
        var email = sessao.getString("emailLogin", " ")
        if (mensagem.sender == email) {
            holder.txtdireita.text = mensagem.texto
            holder.txtdireita.visibility = View.VISIBLE
            holder.txtEsquerda.visibility = View.GONE
        } else {
            holder.txtEsquerda.text = mensagem.texto
            holder.txtEsquerda.visibility = View.VISIBLE
            holder.txtdireita.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return dadosFirebase.size
    }
}

class MensagemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var txtEsquerda: TextView
    var txtdireita: TextView

    init {
        txtEsquerda = itemView.findViewById(R.id.txtEsquerda)
        txtdireita = itemView.findViewById(R.id.txtdireita)
    }
}