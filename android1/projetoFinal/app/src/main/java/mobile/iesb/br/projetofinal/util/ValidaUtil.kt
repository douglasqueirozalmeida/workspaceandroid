package mobile.iesb.br.projetofinal.util

import android.widget.EditText

class ValidaUtil {

    companion object {
        fun isEmpty(editText: EditText) : Boolean {
            if(editText.text == null || editText.text.isEmpty()) {
//                editText.requestFocus()
                editText.setError(editText.hint.toString() + " Inválido")

                return true
            }

            return false
        }

        fun isEmailValido(editText: EditText) : Boolean {
            if(!this.isEmpty(editText)) {
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(editText.text).matches()) {
//                    editText.requestFocus()
                    editText.setError(editText.hint.toString() + " Inválido")
                } else {
                    return true
                }
            }

            return false
        }

        fun isPasswordValido(editText: EditText) : Boolean {
            if(editText.text.length >= 6) {
                return true
            } else {
//                editText.requestFocus()
                editText.setError(editText.hint.toString() + " Inválida, Minimo 6 caracteres")
            }

            return false
        }
    }







}
