
public class Main {
    public static void main(String[] args)
    {
        SalaDeEspera salaDeEspera = new SalaDeEspera();
    
        Enfermedad e1 = new Enfermedad("Psiriasis", Enfermedad.Triage.URGENCIA);
    
        Paciente p1 = new Paciente("Pablo", "Perez", 1, e1);

        
        salaDeEspera.ingresarPaciente(p1);
        System.out.println(salaDeEspera.atenderPaciente());
    }
    
}
