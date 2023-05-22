/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cli.individual.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author oliveira
 */
public class ConexaoNuvem {
    private JdbcTemplate conexaoNuvem;
    
    public ConexaoNuvem() {
    
        BasicDataSource dataSource = new BasicDataSource();
        
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");;
        
        dataSource.setUrl("jdbc:sqlserver://airplane-solutions.database.windows.net:1433;" 
             + "database=bd-airplane-solutions;encrypt=true;trustServerCertificate=false;"
             + "hostNameInCertificate=*.database.windows.net;"); // trocar o localhost:3306 pelo endereço do banco e o tecflix pelo nome do banco
        
        dataSource.setUsername("totemInfo@airplane-solutions"); //Usuario do banco
        
        dataSource.setPassword("Totem_!nf0"); //Senha do banco

        this.conexaoNuvem = new JdbcTemplate(dataSource);
    }
    
    public JdbcTemplate getConexaoNuvem() {
        System.out.println("Acessando o banco de dados!");
        return conexaoNuvem;
    }
}
