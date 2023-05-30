package Clases

class Pago(val valor: Double) {
  var estado: Boolean = false

  def estadoPago(valor: Double): Unit = {
    if (valor == this.valor) {
      this.estado = true
    }
  }

  def calcularGanancia(): Double = {
    this.valor * (1 + 0.55)
  }

  def getEstado(): Boolean = estado
}

object Pago {
  def crearPago(valor: Double): Pago = {
    new Pago(valor)
  }
}
