package maria.Delazari.UniALFA.MariaEduardaPD;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ProntuarioDAO {
    private static final String ARQUIVO = "prontuarios.txt";

    public static void salvarProntuario(String nomePaciente, String cpfPaciente, String nomeMedico,
                                        String especialidade, String dataConsulta, String diagnostico,
                                        String prescricao) {
        File arquivo = new File(ARQUIVO);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) { // true = append
            bw.write("Nome do paciente: " + nomePaciente);
            bw.newLine();
            bw.write("CPF do paciente: " + cpfPaciente);
            bw.newLine();
            bw.write("Nome do médico: " + nomeMedico);
            bw.newLine();
            bw.write("Especialidade: " + especialidade);
            bw.newLine();
            bw.write("Data da consulta: " + dataConsulta);
            bw.newLine();
            bw.write("Descrição do diagnóstico: " + diagnostico);
            bw.newLine();
            bw.write("Prescrição de medicamentos: " + prescricao);
            bw.newLine();
            bw.write("--------------------------------------------------");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
