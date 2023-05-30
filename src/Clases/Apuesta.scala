package Clases

import scala.util.control.Breaks.break
import java.security.NoSuchAlgorithmException
import scala.collection.mutable.ListBuffer

class Apuesta(val idApostador: Int) {
  val equipos: ListBuffer[Equipo] = ListBuffer()
  val apuestas: ListBuffer[Partido] = ListBuffer()
  private var resEq1: Int = 0
  private var resEq2: Int = 0

  def setReseq1(resEq1: Int): Unit = {
    this.resEq1 = resEq1
  }

  def setReseq2(resEq2: Int): Unit = {
    this.resEq2 = resEq2
  }

  def getReseq1: Int = resEq1

  def getReseq2: Int = resEq2

  def crearEquipos(): Unit = {
    val eqname = Array("Medellin", "Nacional", "Envigado", "Cali", "America", "Junior")
    for (i <- eqname.indices) {
      equipos += Equipo.crearEquipo(i, eqname(i))
    }
  }

  def crearApuestas(): Unit = {
    while (apuestas.size != 6) {
      val partido = new Partido(apuestas) {
      }
      if (apuestas.isEmpty) {
        apuestas += partido.generarPartido(equipos)
      } else {
        val apu = partido.generarPartido(equipos)
        var bandera = true

        for (i <- apuestas.indices) {
          if (apu.getEquipo1.getId == apuestas(i).getEquipo1.getId && apu.getEquipo2.getId == apuestas(i).getEquipo2.getId) {
            bandera = false
            break
          }
        }
        if (bandera) {
          apuestas += apu
        }
      }
    }
  }

  def getApuestas: ListBuffer[Partido] = apuestas
}

object Apuesta {
  def crearApuesta(idApostador: Int): Apuesta = {
    new Apuesta(idApostador)
  }
}
