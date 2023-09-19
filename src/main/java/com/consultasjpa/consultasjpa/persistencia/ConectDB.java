package com.consultasjpa.consultasjpa.persistencia;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectDB {
    
    static Connection conn;
    private static final String url = "jdbc:mysql://localhost:3306/consultasDB";
    private static final String user = "root";
    private static final String password = "";
    
    public static void desconectar(){
         try {
            conn.close();
        } catch (SQLException ex) {
            
        }
    }

    public static Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("Conexão realizada com sucesso");
            return conn;
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não disponível na biblioteca" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Sintese de comando inválida!" + ex.getMessage());
        }
            return null;
    }
    
    
}
