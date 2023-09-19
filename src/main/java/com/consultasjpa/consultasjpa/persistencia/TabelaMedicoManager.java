package com.consultasjpa.consultasjpa.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabelaMedicoManager {
        
        
        public static int getRowCount() {
            EntityManager manager = JPAUtil.getEntityManager();  
            
            Query query = manager.createQuery("SELECT COUNT(m) FROM Medico m");
            
            Long count = (Long) query.getSingleResult();
            
            return count != null ? count.intValue() : 0;
        
    }
        
        public static void popularTabela(JTable tabela) {
        EntityManager manager = JPAUtil.getEntityManager();

        try {
            // Consulta todos os registros da tabela "queryConsulta"
            TypedQuery<Medico> query = manager.createQuery("SELECT m FROM Medico m", Medico.class);
            List<Medico> medicos = query.getResultList();

            // Limpa a tabela
            DefaultTableModel model = (DefaultTableModel) tabela.getModel();
            model.setRowCount(0);

            // Preenche a tabela com os resultados da queryConsulta
            for (Medico medico : medicos) {
                Object[] rowData = {
                    medico.getIdMedico(),
                    medico.getNome(),
                    medico.getCrm(),
                    medico.getEspecialidade(),
                };
                model.addRow(rowData);
            }
        } finally {
            manager.close();
        }
    }
        
        public static void buscar(JTable tabela, String termoPesquisa) {
            EntityManager manager = JPAUtil.getEntityManager();

            try {
                // Construir a queryConsulta JPA com base no termo de pesquisa
                String queryConsulta = "SELECT m FROM Medico m WHERE m.nome LIKE :termo";
                TypedQuery<Medico> query = manager.createQuery(queryConsulta, Medico.class);
                query.setParameter("termo", "%" + termoPesquisa + "%"); // Use LIKE com % para pesquisa parcial
                List<Medico> medicos = query.getResultList();

            // Limpa a tabela
            DefaultTableModel model = (DefaultTableModel) tabela.getModel();
            model.setRowCount(0);

            // Preenche a tabela com os resultados da queryConsulta
            for (Medico medico : medicos) {
                Object[] rowData = {
                    medico.getIdMedico(),
                    medico.getNome(),
                    medico.getCrm(),
                    medico.getEspecialidade(),
                };
                model.addRow(rowData);
            }
        } finally {
            manager.close();
        }
}
        
}
