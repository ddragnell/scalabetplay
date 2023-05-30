package Clases

class Apostador(val id: Int, val nombre: String) {
}

object Apostador {
  def crearApostador(id: Int, nombre: String): Apostador = {
    new Apostador(id, nombre)
  }
}
