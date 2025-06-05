package maria.Delazari.UniALFA.MariaEduardaPD;

public class Prontuario {
    private String nomePaciente;
    private String cpfPaciente;
    private String nomeMedico;
    private String especialidade;
    private String dataConsulta;
    private String diagnostico;
    private String prescricao;

    public Prontuario(String nomePaciente, String cpfPaciente, String nomeMedico,
                      String especialidade, String dataConsulta,
                      String diagnostico, String prescricao) {
        this.nomePaciente = nomePaciente;
        this.cpfPaciente = cpfPaciente;
        this.nomeMedico = nomeMedico;
        this.especialidade = especialidade;
        this.dataConsulta = dataConsulta;
        this.diagnostico = diagnostico;
        this.prescricao = prescricao;
    }

    @Override
    public String toString() {
        return "============================\n" +
                "Nome do paciente: " + nomePaciente + "\n" +
                "CPF: " + cpfPaciente + "\n" +
                "Médico: " + nomeMedico + "\n" +
                "Especialidade: " + especialidade + "\n" +
                "Data: " + dataConsulta + "\n" +
                "Diagnóstico: " + diagnostico + "\n" +
                "Prescrição: " + prescricao + "\n";
    }
}

