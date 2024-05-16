public class Enfermedad implements Comparable<Enfermedad> {
    enum Triage {
        RESUSITAR, 
        EMERGENCIA,
        URGENCIA,
        POCA_URGENCIA,
        SIN_URGENCIA
    }
    
    private String nombre;
    private Triage prioridad;

    public Enfermedad(String nombre, Triage prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }
    
    public int compareTo(Enfermedad enfermedad) {
        return this.prioridad.compareTo(enfermedad.prioridad);
    }

    public String getNombre() {
        return nombre;
    }
    @Override
    public String toString()
    {
        return this.nombre + "- Prioridad: " + this.prioridad + " ";
    }
}