package objetos;

public class Paciente {
    private long documento;

    public Paciente(long documento){
        this.documento = documento;
    }

    public long getDocumento() {
        return documento;
    }

    public void setDocumento(long documento) {
        this.documento = documento;
    }
}
