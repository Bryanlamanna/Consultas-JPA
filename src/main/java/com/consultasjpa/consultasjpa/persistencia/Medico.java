package com.consultasjpa.consultasjpa.persistencia;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class Medico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMedico;
    private String nome;
    private String crm;
    private String especialidade;
    
    public void transpose(Medico medico){
        this.nome = medico.getNome();
        this.crm = medico.getCrm();
        this.especialidade = medico.getEspecialidade();
    }

    public Medico() {
    }

    public boolean checkVazio(){
        if (this.nome.isBlank() ||
            this.crm.isBlank() ||
            this.especialidade.isBlank()){
                return false;                
        } else {
                return true;
        }
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }


    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return "Medico{\n" + "idMedico=" + idMedico + ", \nnome=" + nome + ", \ncrm=" + crm + ", \nespecialidade=" + especialidade + '}';
    }

   
    
    
}