package Clases

class Equipo(val id: Int, val nombre: String) {
  def getId(): Int = id

  def getNombre(): String = nombre
}

object Equipo {
  def crearEquipo(id: Int, nombre: String): Equipo = {
    new Equipo(id, nombre)
  }
}
