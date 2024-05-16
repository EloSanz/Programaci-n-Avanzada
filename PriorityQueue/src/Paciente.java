public class Paciente implements Comparable<Paciente> {
    private String nombre;
    private String apellido; 
    private int dni;
    private Enfermedad enfermedadActual;
    
    public Paciente(String nombre, String apellido, int dni, Enfermedad enfermedadActual) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.enfermedadActual = enfermedadActual;
    }

    @Override
    public String toString() {
        return String.format("DNI: %d\nNombre: %s %s\nEnfermedad: %s", this.dni, this.nombre, this.apellido, this.enfermedadActual);
    }

    @Override
    public int compareTo(Paciente paciente) {
        return this.enfermedadActual.compareTo(paciente.enfermedadActual);
    }
}