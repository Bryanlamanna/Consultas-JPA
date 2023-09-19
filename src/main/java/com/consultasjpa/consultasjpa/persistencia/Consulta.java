package com.consultasjpa.consultasjpa.persistencia;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class Consulta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConsulta;
    private String nome;
    private String telefone;
    private String cpf;
    private String data;
    private boolean retorno;
    private String resumo;
    
    public void transpose(Consulta consulta){
        this.nome = consulta.getNome();
        this.telefone = consulta.getTelefone();
        this.cpf = consulta.getCpf();
        this.data = consulta.getData();
        this.retorno = consulta.getRetorno();
        this.resumo = consulta.getResumo();
    }

    public Consulta() {
    }

    public boolean checkVazio(){
        if (this.nome.isBlank() ||
            this.telefone.isBlank() ||
            this.cpf.isBlank() ||
            this.data.toString().isBlank() ||
            this.resumo.isBlank()){
                return false;                
        } else {
                return true;
        }
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean getRetorno() {
        return retorno;
    }

    public void setRetorno(boolean retorno) {
        this.retorno = retorno;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }
    
    
}