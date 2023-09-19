package com.consultasjpa.consultasjpa.persistencia;

import jakarta.persistence.EntityManager;
import javax.swing.JOptionPane;

public class ConsultaDAO {
    
 
    
    public static void cadastrar(Consulta consulta){
        EntityManager manager = JPAUtil.getEntityManager();
        try{
            manager.getTransaction().begin();
            manager.persist(consulta);
            manager.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Falha ao inserir registro!\n"+e.getMessage()+"\n");
            System.out.println("Falha ao inserir registro!\n"+e.getMessage()+"\n"+consulta);
        } finally {
            manager.close();
        }
        
    }
    
    public static void excluir(int id){
        EntityManager manager = JPAUtil.getEntityManager();
        try{
        manager.getTransaction().begin();
        Consulta consulta = manager.find(Consulta.class, id);
        manager.remove(consulta);
        manager.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Falha ao excluir registro!\n"+e.getMessage());
        } finally {
            manager.close();
        }
    }
    
    public static void editar(Consulta consultaNova, int id){
        EntityManager manager = JPAUtil.getEntityManager();
        try {
            Consulta podcastVelho = manager.find(Consulta.class, id);
            manager.getTransaction().begin();
            podcastVelho.transpose(consultaNova);
            manager.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao editar registro!\n"+e.getMessage());
        } finally {
            manager.close();
        }
        
    }
}
