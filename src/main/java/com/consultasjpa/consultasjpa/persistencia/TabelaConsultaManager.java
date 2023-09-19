package com.consultasjpa.consultasjpa.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabelaConsultaManager {
    
        public static String dataConvertida(String data) {
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoSaida = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date date = formatoEntrada.parse(data);
            String dataFormatada = formatoSaida.format(date);
            return dataFormatada;
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Retorna null em caso de erro de análise
        }
    }
        
        public static int getRowCount() {
            EntityManager manager = JPAUtil.getEntityManager();  
            
            Query query = manager.createQuery("SELECT COUNT(c) FROM Consulta c");
            
            Long count = (Long) query.getSingleResult();
            
            return count != null ? count.intValue() : 0;
        
    }
        
        public static String retornoString(Consulta consulta){
                        if (consulta.getRetorno()){
                        return "SIM";
                    } else {
                        return "NÃO";
                    }
        }
        
        public static void popularTabela(JTable tabela) {
        EntityManager manager = JPAUtil.getEntityManager();

        try {
            // Consulta todos os registros da tabela "queryConsulta"
            TypedQuery<Consulta> query = manager.createQuery("SELECT c FROM Consulta c", Consulta.class);
            List<Consulta> consultas = query.getResultList();

            // Limpa a tabela
            DefaultTableModel model = (DefaultTableModel) tabela.getModel();
            model.setRowCount(0);

            // Preenche a tabela com os resultados da queryConsulta
            for (Consulta consulta : consultas) {
                Object[] rowData = {
                    consulta.getIdConsulta(),
                    consulta.getNome(),
                    consulta.getTelefone(),
                    consulta.getCpf(),
                    dataConvertida(consulta.getData()),
                    retornoString(consulta),
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
                String queryConsulta = "SELECT c FROM Consulta c WHERE c.nome LIKE :termo";
                TypedQuery<Consulta> query = manager.createQuery(queryConsulta, Consulta.class);
                query.setParameter("termo", "%" + termoPesquisa + "%"); // Use LIKE com % para pesquisa parcial
                List<Consulta> consultas = query.getResultList();

            // Limpa a tabela
            DefaultTableModel model = (DefaultTableModel) tabela.getModel();
            model.setRowCount(0);

            // Preenche a tabela com os resultados da queryConsulta
            for (Consulta consulta : consultas) {
                Object[] rowData = {
                    consulta.getIdConsulta(),
                    consulta.getNome(),
                    consulta.getTelefone(),
                    consulta.getCpf(),
                    consulta.getData(),
                    consulta.getRetorno(),
                };
                model.addRow(rowData);
                }
            } finally {
                manager.close();
            }
}
        
}
