package com.consultasjpa.consultasjpa.persistencia;

import jakarta.persistence.EntityManager;
import javax.swing.JOptionPane;

public class MedicoDAO {
    
    public static void cadastrar(Medico medico){
        EntityManager manager = JPAUtil.getEntityManager();
        try{
            manager.getTransaction().begin();
            manager.persist(medico);
            manager.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Falha ao inserir registro!\n"+e.getMessage()+"\n");
            System.out.println("Falha ao inserir registro!\n"+e.getMessage()+"\n"+medico);
        } finally {
            manager.close();
        }
        
    }
    
    public static void excluir(int id){
        EntityManager manager = JPAUtil.getEntityManager();
        try{
        manager.getTransaction().begin();
        Medico medico = manager.find(Medico.class, id);
        manager.remove(medico);
        manager.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Falha ao excluir registro!\n"+e.getMessage());
        } finally {
            manager.close();
        }
    }
    
    public static void editar(Medico medicoNovo, int id){
        EntityManager manager = JPAUtil.getEntityManager();
        try {
            Medico medicoVelho = manager.find(Medico.class, id);
            manager.getTransaction().begin();
            medicoVelho.transpose(medicoNovo);
            manager.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao editar registro!\n"+e.getMessage());
        } finally {
            manager.close();
        }
        
    }
}
