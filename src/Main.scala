import Clases.{Apostador, Apuesta, Pago}

import java.security.NoSuchAlgorithmException
import java.text.{NumberFormat, ParsePosition}
import java.util.Scanner
import scala.collection.convert.ImplicitConversions.`buffer AsJavaList`

object Main {
  def isNumeric(s: String): Boolean = {
    val pos: ParsePosition = new ParsePosition(0)
    NumberFormat.getInstance.parse(s, pos)
    s.length == pos.getIndex
  }

  def main(args: Array[String]): Unit = {
    val scanner: Scanner = new Scanner(System.in)

    println("Bienvenido a apuestas Betplay\n")
    println("Por favor ingrese su cédula")
    val id: String = scanner.nextLine
    if (!isNumeric(id)) {
      println("Ingrese una Id Válida")
      System.exit(0)
    }
    println("Por favor ingrese su nombre")
    val nombre: String = scanner.nextLine

    val ap: Apostador = Apostador.crearApostador(id.toInt, nombre)
    val apuesta: Apuesta = Apuesta.crearApuesta(id.toInt)
    apuesta.crearEquipos()
    apuesta.crearApuestas()

    println(s"A continuación se le mostrarán las apuestas disponibles, por favor escoja una, ${ap.nombre}")
    for (i <- 1 until apuesta.getApuestas.size) {
      println(i + ".")
      apuesta.getApuestas.get(i).imprimirPartido()
    }
    println()
    println("Ingrese el número de partido al que desea apostar")
    val apostar: String = scanner.nextLine
    if (!isNumeric(apostar)) {
      println("Ingrese el número del equipo por el que desea apostar")
      System.exit(0)
    }
    if (apostar.toInt > apuesta.getApuestas.size || apostar.toInt <= 0) {
      println("Por favor, ingrese una apuesta válida")
      System.exit(0)
    }
    var gana: Boolean = false
    for (i <- 1 until apuesta.getApuestas.size) {
      if (apostar.toInt == i) {
        println("El partido elegido es:")
        println(i + ".")
        apuesta.getApuestas.get(i).imprimirPartido()
        println(s"Ingrese el marcador esperado para el equipo ${apuesta.getApuestas.get(i).getEquipo1.getNombre}: ")
        val n1: String = scanner.nextLine
        if (!isNumeric(apostar)) {
          println("Ingrese un marcador válido para el equipo 1")
          System.exit(0)
        }
        apuesta.setReseq1(n1.toInt)
        println(s"Ingrese el marcador esperado para el equipo ${apuesta.getApuestas.get(i).getEquipo2.getNombre}: ")
        val n2: String = scanner.nextLine
        if (!isNumeric(apostar)) {
          println("Ingrese un marcador válido para el equipo 2")
          System.exit(0)
        }
        apuesta.setReseq2(n2.toInt)
        if (apuesta.getReseq1 == apuesta.getApuestas.get(i).getReq1 && apuesta.getReseq2 == apuesta.getApuestas.get(i).getReq2) {
          gana = true
        }
      }
    }
    println("Ingresa el valor que deseas apostar: ")
    val `val`: String = scanner.nextLine
    if (!isNumeric(`val`)) {
      println("Ingrese un marcador válido para el equipo 2")
      System.exit(0)
    }
    val pag: Pago = Pago.crearPago(`val`.toFloat)

    println(s"El pago a obtener si se gana la apuesta es: ${pag.calcularGanancia}")
    println("¿Desea continuar con el pago? (S/N): ")
    val r: String = scanner.nextLine
    if (r.equals("s"))
      if(r.equals("n")) {
        println("Seleccione 's' para Sí continuar o 'n' para No continuar")
        System.exit(0)
    }

    if (r.equalsIgnoreCase("S")) {
      println("Por favor ingrese el monto a pagar: ")
      val pagar: Float = scanner.nextLine.toFloat
      pag.estadoPago(pagar)

      if (pag.getEstado) {
        println("El pago de la apuesta se ha completado exitosamente. A continuación podrá ver el resultado de la apuesta.")

        if (gana) {
          println("¡Felicitaciones, ha ganado!")
          println(s"Un total de ${pag.calcularGanancia} fue depositado en su cuenta.")
        }
        else {
          for (i <- 1 until apuesta.getApuestas.size) {
            if (i == apostar.toInt) {
              println(s"El resultado fue ${apuesta.getApuestas.get(i).getReq1} vs ${apuesta.getApuestas.get(i).getReq2}")
            }
          }
          println("Sigue Intentándolo")
        }
      }
      else {
        println("El monto es Inválido")
      }
    }
  }
}
