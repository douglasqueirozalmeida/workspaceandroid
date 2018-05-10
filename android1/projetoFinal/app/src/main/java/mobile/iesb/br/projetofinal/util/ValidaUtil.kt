package mobile.iesb.br.projetofinal.util

import android.widget.EditText

class ValidaUtil {

    companion object {
        fun isEmpty(editText: EditText) : Boolean {
            if(editText.text == null || editText.text.isEmpty()) {
                editText.requestFocus()
                editText.setError(editText.hint.toString() + " Inválido")

                return true
            }

            return false
        }

        fun isEmailValido(editText: EditText) : Boolean {
            return true
        }

        fun isPasswordValido(editText: EditText) : Boolean {
            return true
        }
    }







}
