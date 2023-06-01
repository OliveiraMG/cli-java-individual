/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cli.individual.controller;


import com.github.britooo.looca.api.core.Looca;
import com.mycompany.cli.individual.dao.ConexaoLocal;
import com.mycompany.cli.individual.dao.ConexaoNuvem;
import com.mycompany.cli.individual.model.Totem;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author oliveira
 */
public class TotemController {
    ConexaoLocal conexaoBancoLocal = new ConexaoLocal();
    ConexaoNuvem conexaoBancoNuvem = new ConexaoNuvem();
    JdbcTemplate jdbcLocal =  conexaoBancoLocal.getConexaoLocal();
    JdbcTemplate jdbcNuvem = conexaoBancoNuvem.getConexaoNuvem();
    
    Looca looca = new Looca();
    
    Scanner leitor02 = new Scanner(System.in);
    
    public void cadastrarTotem() {
        System.out.println("Digite o Token:");
        String token = leitor02.nextLine();
        
        System.out.println("Inserindo um novo totem no banco");
        
        jdbcLocal.update("INSERT INTO Totem(arquitetura, sistemaOperacional, processador, token) VALUES (?, ?, ?, ?)",
                            looca.getSistema().getArquitetura(), 
                            looca.getSistema().getSistemaOperacional(), 
                            looca.getProcessador().getNome(),
                            token);
        
//        jdbcNuvem.update("INSERT INTO Totem(arquitetura, sistemaOperacional, processador, token) VALUES (?, ?, ?, ?)",
//                            looca.getSistema().getArquitetura(), 
//                            looca.getSistema().getSistemaOperacional(), 
//                            looca.getProcessador().getNome(),
//                            token);
        
        System.out.println("\nNovo totem cadastrado com sucesso!");
    };
    public void exibirTotensCadastrados() {
        List<Map<String, Object>> resultados = jdbcNuvem.queryForList("SELECT * FROM Totem;");
    for (Map<String, Object> resultado : resultados) {
        System.out.println("\nidTotem: " + resultado.get("idTotem"));
        System.out.println("\ntoken: " + resultado.get("token"));
        System.out.println("\narquitetura: " + resultado.get("arquitetura"));
        System.out.println("\nsistemaOperacional: " + resultado.get("sistemaOperacional"));
        System.out.println("\nprocessador: " + resultado.get("processador"));
        System.out.println();
        
        System.out.println("\nExibindo totens cadastrados");
    }
    };
    public void exibirComponentesMaquinaAtual() {
        System.out.println(String.format(
                            "Processador: %s, Arquitetura: %s, Sistema Operacional: %s\n", 
                            looca.getProcessador().getNome(), looca.getSistema().getArquitetura(), looca.getSistema().getSistemaOperacional()
                    ));
        
        System.out.println("\nExibindo componentes da máquina atual");
    };
}
