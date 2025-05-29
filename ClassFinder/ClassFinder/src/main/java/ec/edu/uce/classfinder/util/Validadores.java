package ec.edu.uce.classfinder.util;

import ec.edu.uce.classfinder.dominio.EstadoReserva;
import ec.edu.uce.classfinder.dominio.Rol;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Clase utilitaria que proporciona métodos de validación para diferentes tipos de datos utilizados en el sistema ClassFinder.
 * Incluye validaciones para números, texto, correos, IDs, fechas, cédulas, capacidades, tamaños, estados, roles y contraseñas.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Validadores {

    /**
     * Valida si una cadena representa un número entero positivo.
     * @param entradaTeclado La cadena a validar.
     * @return true si la cadena contiene solo dígitos, false de lo contrario.
     */
    public static boolean esNumeroValido(String entradaTeclado) {
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher matcher = pattern.matcher(entradaTeclado);
        return matcher.matches();
    }

    /**
     * Valida si una cadena contiene solo letras y espacios con un máximo de 75 caracteres.
     * @param entradateclado La cadena a validar.
     * @return true si la cadena es válida, false de lo contrario.
     */
    public static boolean esTextoValido(String entradateclado) {
        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]{1,75}$");
        Matcher matcher = pattern.matcher(entradateclado);
        return matcher.matches();
    }

    /**
     * Valida si una cadena representa un correo electrónico válido con dominios gmail o hotmail.
     * @param entradaTeclado La cadena a validar.
     * @return true si el correo es válido, false de lo contrario.
     */
    public static boolean esCorreoValido(String entradaTeclado) {
        Pattern pattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9._-]{1,25}\\@(gmail|hotmail)\\.[c-o]{3}$");
        Matcher matcher = pattern.matcher(entradaTeclado);
        return matcher.matches();
    }

    /**
     * Valida si una cadena sigue el formato de ID (ejemplo: USR-001, RES-001).
     * @param entradaTeclado La cadena a validar.
     * @return true si el ID es válido, false de lo contrario.
     */
    public static boolean esIdValido(String entradaTeclado) {
        Pattern pattern = Pattern.compile("^[A-Z]{3}-\\d{3}$");
        Matcher matcher = pattern.matcher(entradaTeclado);
        return matcher.matches();
    }

    /**
     * Valida si una cadena representa una fecha con hora en el formato YYYY/MM/DD HH:MM y es futura.
     * @param entradaTeclado La cadena a validar.
     * @return true si la fecha es válida y futura, false de lo contrario.
     */
    public static boolean esFechaConHoraValida(String entradaTeclado) {
        Pattern pattern = Pattern.compile("^\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}$");
        Matcher matcher = pattern.matcher(entradaTeclado);
        if (!matcher.matches())
            return false;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            sdf.setLenient(false);
            Date fecha = sdf.parse(entradaTeclado);
            Date ahora = new Date();
            return !fecha.before(ahora);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Valida si la fecha de fin es posterior a la fecha de inicio.
     * @param fechaInicioStr La cadena de la fecha de inicio.
     * @param fechaFinStr La cadena de la fecha de fin.
     * @return true si la fecha de fin es posterior, false de lo contrario.
     */
    public static boolean esFechaFinValida(String fechaInicioStr, String fechaFinStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            sdf.setLenient(false);
            Date fechaInicio = sdf.parse(fechaInicioStr);
            Date fechaFin = sdf.parse(fechaFinStr);
            return fechaFin.after(fechaInicio);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Valida si una cadena representa una cédula de 10 dígitos.
     * @param entradaTeclado La cadena a validar.
     * @return true si la cédula es válida, false de lo contrario.
     */
    public static boolean esCedulaValida(String entradaTeclado) {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(entradaTeclado);
        return matcher.matches();
    }

    /**
     * Valida si una cadena representa una capacidad válida (entre 1 y 999).
     * @param entradaTeclado La cadena a validar.
     * @return true si la capacidad es válida, false de lo contrario.
     */
    public static boolean esCapacidadValido(String entradaTeclado) {
        Pattern pattern = Pattern.compile("^\\d{1,3}$");
        Matcher matcher = pattern.matcher(entradaTeclado);
        if (!matcher.matches()) return false;
        int capacidad = Integer.parseInt(entradaTeclado);
        return capacidad > 0 && capacidad <= 999;
    }

    /**
     * Valida si una cadena representa un tamaño válido (pequeño, mediano, grande).
     * @param entradaTeclado La cadena a validar.
     * @return true si el tamaño es válido, false de lo contrario.
     */
    public static boolean esTamanoValido(String entradaTeclado) {
        Pattern pattern = Pattern.compile("^(pequeño|mediano|grande)$");
        Matcher matcher = pattern.matcher(entradaTeclado.toLowerCase());
        return matcher.matches();
    }

    /**
     * Valida si un EstadoReserva es válido (no nulo y pertenece al enum EstadoReserva).
     * @param estado el estado a validar
     * @return true si el estado es válido, false de lo contrario
     */
    public static boolean esEstadoValido(EstadoReserva estado) {
        if (estado == null) {
            return false;
        }
        for (EstadoReserva valor : EstadoReserva.values()) {
            if (valor == estado) {
                return true;
            }
        }
        return false;
    }

    /**
     * Valida si un Rol es válido (no nulo y pertenece al enum Rol).
     * @param rol el rol a validar
     * @return true si el rol es válido, false de lo contrario
     */
    public static boolean esRolValido(Rol rol) {
        if (rol == null) {
            return false;
        }
        // Verifica si el rol está dentro de los valores definidos en el enum
        for (Rol valor : Rol.values()) {
            if (valor == rol) {
                return true;
            }
        }
        return false;
    }

    /**
     * Valida si una cadena representa una contraseña válida (6-20 caracteres alfanuméricos y algunos símbolos).
     * @param entradaTeclado La cadena a validar.
     * @return true si la contraseña es válida, false de lo contrario.
     */
    public static boolean esContrasenaValida(String entradaTeclado) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9 .@-_!%+*]{6,20}$");
        Matcher matcher = pattern.matcher(entradaTeclado);
        return matcher.matches();
    }
}