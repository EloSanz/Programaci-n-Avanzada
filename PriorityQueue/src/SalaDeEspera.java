public class SalaDeEspera {
    private PriorityQ<Paciente> pq;

    public SalaDeEspera() {
        this.pq = new PriorityQ<Paciente>();
    }
    
    public void ingresarPaciente(Paciente paciente) {
        this.pq.add(paciente);
    }

    public Paciente atenderPaciente() {
        return this.pq.poll();
    }
}
