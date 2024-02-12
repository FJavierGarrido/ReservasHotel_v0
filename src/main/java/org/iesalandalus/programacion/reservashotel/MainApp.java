package org.iesalandalus.programacion.reservashotel;


import org.iesalandalus.programacion.reservashotel.dominio.*;
import org.iesalandalus.programacion.reservashotel.negocio.*;
import org.iesalandalus.programacion.reservashotel.vista.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;

public class MainApp {

    private static Huespedes huespedes;
    private static Habitaciones habitaciones;
    private static Reservas reservas;
    private Consola consola;
    public static int CAPACIDAD=15;
    public static void main(String[] args) {
        huespedes = new Huespedes(CAPACIDAD);
        habitaciones=new Habitaciones(CAPACIDAD);
        reservas=new Reservas(CAPACIDAD);

        MainApp mainApp = new MainApp();
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            mainApp.ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);

    }

    private void ejecutarOpcion(Opcion opcion) {
        Opcion[] opciones = Opcion.values();
        int ordinal = opcion.ordinal();

        if (ordinal >= 0 && ordinal < opciones.length) {
            switch (opciones[ordinal]) {
                case INSERTAR_HUESPED:
                    insertarHuesped();
                    break;
                case BUSCAR_HUESPED:
                    buscarHuesped();
                    break;
                case BORRAR_HUESPED:
                    borrarHuesped();
                    break;
                case MOSTRAR_HUESPED:
                    mostrarHuespedes();
                    break;
                case INSERTAR_HABITACION:
                    insertarHabitacion();
                    break;
                case BUSCAR_HABITACION:
                    buscarHabitacion();
                    break;
                case BORRAR_HABITACION:
                    borrarHabitacion();
                    break;
                case MOSTRAR_HABITACIONES:
                    mostrarHabitaciones();
                    break;
                case INSERTAR_RESERVA:
                    insertarReserva();
                    break;
                case ANULAR_RESERVA:
                    anularReserva();
                    break;
                case MOSTRAR_RESERVAS:
                    mostrarReservas();
                    break;
                case CONSULTAR_DISPONIBILIDAD:
                    TipoHabitacion tipoHabitacion = Consola.leerTipoHabitacion();
                    LocalDate fechaInicio = Consola.leerFecha("Introduce la fecha de inicio (dd/mm/aaaa): ");
                    LocalDate fechaFinal = Consola.leerFecha("Introduce la fecha de fin (dd/mm/aaaa): ");

                    consultarDisponibilidad(tipoHabitacion,fechaInicio,fechaFinal);
                    break;
                case SALIR:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opci�n no v�lida.");
            }
        } else {
            System.out.println("Opci�n no v�lida.");
        }
    }
    private void insertarHuesped() {
        try {
            Huesped huesped = Consola.leerHuesped();
            huespedes.insertar(huesped);
            System.out.println("Huesped insertado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarHuesped() {
        try {
            Huesped huesped = consola.getHuespedPorDni();
            Huesped huespedEncontrado = huespedes.buscar(huesped);
            if (huespedEncontrado != null ) {
                System.out.println(huespedEncontrado);
            } else {
                System.out.println("No se encontr� el hu�sped.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarHuesped() {
        try {
            Huesped huesped = consola.getHuespedPorDni();

            Huesped huespedEncontrado = huespedes.buscar(huesped);
            if (huespedEncontrado != null ) {
                huespedes.borrar(huespedEncontrado);
            } else {
                System.out.println("No se encontr� el hu�sped para borrarlo.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarHuespedes() {
        Huesped[] listaHuespedes = huespedes.get();
        if (listaHuespedes.length > 0) {
            for (Huesped huesped : listaHuespedes) {
                System.out.println(huesped);
            }
        } else {
            System.out.println("No hay hu�spedes registrados.");
        }
    }

    private void insertarHabitacion() {
        try {
            Habitacion habitacion = consola.leerHabitacion();
            Habitacion habitacionEncontrada = habitaciones.buscar(habitacion);
            habitaciones.insertar(habitacionEncontrada);
            System.out.println("Habitaci�n insertada correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarHabitacion() {
        try {
            Habitacion habitacion = consola.leerHabitacionPorIdentificador();
            Habitacion habitacionEncontrada = habitaciones.buscar(habitacion);
            if (habitacionEncontrada != null) {
                System.out.println(habitacionEncontrada);
            } else {
                System.out.println("No se encontr� la habitaci�n.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarHabitacion() {
        try {
            Habitacion habitacion = consola.leerHabitacionPorIdentificador();
            if (habitacion != null) {
                habitaciones.borrar(habitacion);
                System.out.println("Habitaci�n borrada correctamente.");
            }else {
                System.out.println("No se encontr� la habitaci�n para borrar.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarHabitaciones() {
        Habitacion[] listaHabitaciones = habitaciones.get();
        if (listaHabitaciones.length > 0) {
            for (Habitacion habitacion : listaHabitaciones) {
                System.out.println(habitacion);
            }
        } else {
            System.out.println("No hay habitaciones registradas.");
        }
    }

    private void insertarReserva() {
        try {
            Reserva reserva = consola.leerReserva();
            reservas.insertar(reserva);
            System.out.println("Reserva insertada correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void listarReservas(Huesped huesped) {
        try {
            Reserva[] reservasHuesped = reservas.getReservas(huesped);
            if (reservasHuesped.length > 0) {
                for (Reserva reserva : reservasHuesped) {
                    System.out.println(reserva);
                }
            } else {
                System.out.println("No hay reservas para este hu�sped.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void listarReservas(TipoHabitacion tipoHabitacion) {
        try {
            Reserva[] reservasTipoHabitacion = reservas.getReservas(tipoHabitacion);
            if (reservasTipoHabitacion.length > 0) {
                for (Reserva reserva : reservasTipoHabitacion) {
                    System.out.println(reserva);
                }
            } else {
                System.out.println("No hay reservas para este tipo de habitaci�n.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Reserva[] getReservasAnulables(Reserva[] reservasAAnular) {
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Contar cu�ntas reservas son anulables
        int contadorReservasAnulables = 0;
        for (Reserva reserva : reservasAAnular) {
            if (reserva.getFechaInicioReserva().isAfter(fechaActual)) {
                contadorReservasAnulables++;
            }
        }

        // Crear un array para almacenar las reservas anulables
        Reserva[] reservasAnulables = new Reserva[contadorReservasAnulables];

        // Llenar el array con las reservas anulables
        int indice = 0;
        for (Reserva reserva : reservasAAnular) {
            if (reserva.getFechaInicioReserva().isAfter(fechaActual)) {
                reservasAnulables[indice] = reserva;
                indice++;
            }
        }

        return reservasAnulables;
    }

    private void anularReserva() {
        try {

            Huesped huesped = Consola.getHuespedPorDni();

            if (huesped != null) {
                // Obtener todas las reservas asociadas al hu�sped
                Reserva[] reservaAnulable = reservas.getReservas(huesped);

                if (reservaAnulable.length > 0) {
                    // Mostrar las reservas anulables al usuario
                    System.out.println("Reservas anulables del hu�sped:");
                    for (int i = 0; i < reservaAnulable.length; i++) {
                        System.out.println((i + 1) + ". " + reservaAnulable[i].toString());
                    }

                    // Pedir al usuario que elija la reserva a anular
                    System.out.print("Ingrese el n�mero de reserva que desea anular: ");
                    int numeroReserva = Entrada.entero();

                    // Verificar si el n�mero de reserva es v�lido
                    if (numeroReserva >= 1 && numeroReserva <= reservaAnulable.length) {
                        // Mostrar informaci�n de la reserva seleccionada
                        Reserva reservaAnular = reservaAnulable[numeroReserva - 1];
                        System.out.println("Reserva seleccionada para anular:");
                        System.out.println(reservaAnular.toString());

                        // Pedir confirmaci�n al usuario
                        System.out.print("�Est� seguro de que desea anular esta reserva? (S/N): ");
                        String confirmacion = Entrada.cadena();

                        if (confirmacion.equalsIgnoreCase("S")) {
                            // Anular la reserva seleccionada
                            reservas.borrar(reservaAnular);
                            System.out.println("Reserva anulada con �xito.");
                        } else {
                            System.out.println("Operaci�n de anulaci�n cancelada.");
                        }
                    } else {
                        System.out.println("N�mero de reserva no v�lido.");
                    }
                } else {
                    System.out.println("El hu�sped no tiene reservas anulables.");
                }
            }

        } catch (Exception e) {
            System.out.println("Error al anular la reserva: " + e.getMessage());
        }

    }




    private void mostrarReservas() {
        Reserva[] listaReservas = reservas.get();
        if (listaReservas.length > 0) {
            for (Reserva reserva : listaReservas) {
                System.out.println(reserva);
            }
        } else {
            System.out.println("No hay reservas registradas.");
        }
    }

    private Habitacion consultarDisponibilidad(TipoHabitacion tipoHabitacion, LocalDate fechaInicioReserva, LocalDate fechaFinReserva) {
        if (tipoHabitacion == null) {
            throw new NullPointerException("ERROR: El tipo de habitaci�n no puede ser nulo.");
        }
        if (fechaInicioReserva == null || fechaFinReserva == null) {
            throw new NullPointerException("ERROR: Las fechas de inicio y fin no pueden ser nulas.");
        }
        if (fechaInicioReserva.isAfter(fechaFinReserva)) {
            throw new IllegalArgumentException("ERROR: La fecha de inicio no puede ser posterior a la fecha de fin.");
        }

        for (int i = 0; i < reservas.getTamano(); i++) {
            Reserva reserva = reservas.get()[i];
            if (reserva.getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                if (reserva.getFechaFinReserva().isBefore(fechaInicioReserva) || reserva.getFechaInicioReserva().isAfter(fechaFinReserva)) {
                    return reserva.getHabitacion();  // La habitaci�n est� disponible
                }
            }
        }

        return null;  // No hay habitaciones disponibles para el periodo seleccionado

    }




}