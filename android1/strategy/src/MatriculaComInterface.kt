// Implementação Strategy Pattern com interface
interface Desconto1 {
    fun calcularDesconto1(): Double
}


class Matricula1(val name: String, val valorCurso: Double, val desconto: (Double) -> Double) : Desconto1 {
    override fun calcularDesconto1(): Double {
        return desconto(valorCurso)
    }

}


fun main(args: Array<String>) {
    val descontoExAluno = { desconto: Double -> desconto * 0.20 }
    val descontoAluno = { desconto: Double -> desconto * 0 }

    val exAluno = Matricula1("Doug", 1000.0, desconto = descontoExAluno)
    val aluno = Matricula1("Carlos", 1000.0, desconto = descontoAluno)

    println("${exAluno.name} possui desconto de R$ %.2f por mês.".format(exAluno.calcularDesconto1()))
    println("${aluno.name} possui desconto de R$ %.2f por mês.".format(aluno.calcularDesconto1()))
}