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
    
        BasicDataSource dataSource = new BasicDataSource();
        
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");;
        
        dataSource.setUrl("jdbc:mysql://35.174.111.134:3309/BancoLocalCli?autoReconnect=true&useSSL=false"); // trocar o localhost:3306 pelo endereço do banco e o tecflix pelo nome do banco
        
        dataSource.setUsername("root"); //Usuario do banco
        
        dataSource.setPassword("urubu100"); //Senha do banco

        this.conexaoLocal = new JdbcTemplate(dataSource);
    }
    
    public JdbcTemplate getConexaoLocal() {
        System.out.println("Acessando o banco de dados!");
        return conexaoLocal;
    }
  
}
