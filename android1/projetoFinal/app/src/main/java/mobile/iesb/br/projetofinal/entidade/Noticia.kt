package mobile.iesb.br.projetofinal.entidade

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.support.annotation.RequiresApi
import java.io.File
import java.io.Serializable
import java.util.*

@Entity(tableName = "noticia")
data class Noticia(

        @PrimaryKey(autoGenerate = true) var uid: Int = 0,
        @ColumnInfo(name = "titulo") var titulo: String = "",
        @ColumnInfo(name = "dataPublicacao") var dataPublicacao: Date,
        @ColumnInfo(name = "foto") var foto: String = "",
        @ColumnInfo(name = "texto") var texto: String = "") : Serializable {

    @RequiresApi(Build.VERSION_CODES.O)
    fun converteToBase64(filePath: String): String {
        val bytes = File(filePath).readBytes()
        val base64 = Base64.getEncoder().encodeToString(bytes)

        return base64
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun converteBase64ParaBytes(base64: String, pathFile: String): Unit {
        val imageByteArray = Base64.getDecoder().decode(base64)
        File(pathFile).writeBytes(imageByteArray)
    }


    fun retornaBitMapImage(): Bitmap {
        var bytes = android.util.Base64.decode(foto, android.util.Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }
}