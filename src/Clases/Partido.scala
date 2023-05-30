package Clases
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.util.List
import java.util.Random
import scala.collection.mutable.ListBuffer

class Partido {
  var id: Int = 0
  var req1: Int = 0
  var req2: Int = 0
  var equipo1: Equipo = null
  var equipo2: Equipo = null
  private var rand: Random = SecureRandom.getInstanceStrong()


  def this(apuestas: ListBuffer[Partido]) {
    this()
  }

  def getReq1: Int = req1

  def getReq2: Int = req2

  def setEquipo1(equipo1: Equipo): Unit = {
    this.equipo1 = equipo1
  }
  def setEquipo2(equipo2: Equipo): Unit = {
    this.equipo2 = equipo2
  }

  def generarResultados(): Int = {
    val l = Array(0, 1, 2, 3, 4)
    l(rand.nextInt(l.length))
  }

  def imprimirPartido(): Unit = {
    println(s"${equipo1.getNombre()} vs ${equipo2.getNombre()}")
  }

  def generarPartido(equipos: ListBuffer[Equipo]): Partido = {
    req1 = generarResultados()
    req2 = generarResultados()
    setEquipo1(equipos(rand.nextInt(equipos.size)))
    setEquipo2(equipos(rand.nextInt(equipos.size)))

    while (equipo1 == equipo2) {
      setEquipo2(equipos(rand.nextInt(equipos.size)))
    }
    this
  }
  def getEquipo1: Equipo = {
    this.equipo1
  }
  def getEquipo2: Equipo = {
    this.equipo2
  }
}
