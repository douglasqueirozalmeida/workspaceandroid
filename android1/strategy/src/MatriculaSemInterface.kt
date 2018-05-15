//Implementação Strategy Pattern sem interface
typealias Desconto = (Double) -> Double


class Matricula(val name: String, val valorCurso: Double, val desconto: Desconto) {
    fun calcularDesconto(): Double {
        return desconto(valorCurso)
    }

}


fun main(args: Array<String>) {
    val descontoExAluno = { desconto: Double -> desconto * 0.20 }
    val descontoAluno = { desconto: Double -> desconto * 0 }

    val exAluno = Matricula("Douglas", 700.0, desconto = descontoExAluno)
    val aluno = Matricula("Carlos", 700.0, desconto = descontoAluno)

    println("${exAluno.name} possui desconto de R$ %.2f por mês.".format(exAluno.calcularDesconto()))
    println("${aluno.name} possui desconto de R$ %.2f por mês.".format(aluno.calcularDesconto()))
}