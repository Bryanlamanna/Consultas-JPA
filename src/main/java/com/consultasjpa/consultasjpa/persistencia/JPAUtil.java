package com.consultasjpa.consultasjpa.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    
    private static final String PERSISTENCE_UNIT = "Consultas-PU";
    private static EntityManagerFactory fabricaEntidade;
    private static EntityManager manager;
    
    public static EntityManager getEntityManager(){
        if(fabricaEntidade == null || !fabricaEntidade.isOpen())
            fabricaEntidade = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        
        if(manager == null || !manager.isOpen()) 
            manager = fabricaEntidade.createEntityManager();

        return manager;
    }
    
     public static void closeEtityManager(){
        if(manager.isOpen() && manager != null){
            manager.close();
            fabricaEntidade.close();
        }
     }
    
}
