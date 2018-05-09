package mobile.iesb.br.projetofinal.activitys

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import mobile.iesb.br.projetofinal.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)



    }

    public override fun onDestroy() {


        super.onDestroy()
    }
}
