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
public class ConexaoLocal {
    
    private JdbcTemplate conexaoLocal;
    
    public ConexaoLocal() {
    
        BasicDataSource dataSource = new BasicDataSource();;
        
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/projetoIndividual?autoReconnect=true&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");

        this.conexaoLocal = new JdbcTemplate(dataSource);
    }
    
    public JdbcTemplate getConexaoLocal() {
        return conexaoLocal;
    }
  
}
