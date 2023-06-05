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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author oliveira
 */
public class TotemController {
    ConexaoNuvem conexaoBancoNuvem = new ConexaoNuvem();
    JdbcTemplate jdbcNuvem = conexaoBancoNuvem.getConexaoNuvem();
    
    ConexaoLocal conexaoBancoLocal = new ConexaoLocal();
    JdbcTemplate jdbcLocal =  conexaoBancoLocal.getConexaoLocal();
    
    Looca looca = new Looca();
    
    Scanner leitor01 = new Scanner(System.in);
    Scanner leitor02 = new Scanner(System.in);
    
    public void cadastrarTotem() {
        System.out.println("Digite o Token:");
        String token = leitor02.nextLine();
        
        System.out.println("Inserindo um novo totem no banco");
        
        jdbcLocal.update("INSERT INTO totem(arquitetura, sistemaOperacional, processador, token) VALUES (?, ?, ?, ?)",
                            looca.getSistema().getArquitetura(), 
                            looca.getSistema().getSistemaOperacional(), 
                            looca.getProcessador().getNome(),
                            token);
        
//        jdbcNuvem.update("INSERT INTO Totem(arquitetura, sistemaOperacional, processador, token) VALUES (?, ?, ?, ?)",
//                            looca.getSistema().getArquitetura(), 
//                            looca.getSistema().getSistemaOperacional(), 
//                            looca.getProcessador().getNome(),
//                            token);
        
    };
    
    public void cadastrarTotemNuvem(){
        
        //PEGANDO TODOS AS COMPANHIAS QUE ESTÃO CADASTRADAS NO BANCO
        List<Map<String, Object>> listaCompanhia = jdbcNuvem.queryForList("SELECT * FROM companhiaAerea");
        System.out.println("Id    Nome");
        for(Map<String, Object> companhia : listaCompanhia){
            System.out.println(companhia.get("idCompanhia") + " -   "+ companhia.get("nome"));
        }
        
        // PEDINDO PARA O USUÁRIO DIGITAR O ID DA COMPANHIA
        System.out.println("Digite o identificador da companhia: ");
        String idCompanhia = leitor01.nextLine();
        
        //VALIDANDO O TOKEN DO TOTEM DIGITADO PELO O USUÁRIOS
        String token = "";
        Boolean hasToken = false;
        // O LAÇO É PARA VALIDAR O TOKEN
        System.out.println("Digite o token do totem: ");
        token = leitor02.nextLine();
        
        /*do{
            
            // VALIDANDO SE ESSE TOKEN JÁ EXISTE, CASO ELE NÃO EXISTA CADASTRA COM ESSE VALOR
            List<Map<String, Object>> listToken = jdbcNuvem.queryForList("SELECT token FROM totem WHERE token = ? AND fkCompanhia = ?", token, idCompanhia);
            if(listToken.isEmpty()) hasToken = true;
        }
        while(!hasToken);
        */
        
        // PEGANDO AS CAPTURAS DO LOOCA
        String arquitetura = looca.getSistema().getArquitetura() + "bits";
        String sistemaOperacional = looca.getSistema().getSistemaOperacional();
        String processador = looca.getProcessador().getNome();
        String fabricante = looca.getProcessador().getFabricante();
        
        Map<String, Object> mapIdTotem = jdbcNuvem.queryForMap("SELECT TOP 1 idTotem FROM totem WHERE fkCompanhia = ? ORDER BY idTotem DESC", idCompanhia);
        
        Integer idTotem = Integer.parseInt(mapIdTotem.get("idTotem").toString()) + 1;
        // INSERINDO UM NOVO TOTEM COM AS INFORMAÇÕES ANTERIORES
        /*
        jdbcNuvem.update("INSERT INTO totem(idTotem, fkCompanhia, token, fabricante, arquitetura, sistemaOperacional, processador, localizacaoTotem) VALUES (?,?,?,?,?,?,?, '')",
                idTotem,
                idCompanhia,
                token,
                fabricante, 
                arquitetura, 
                sistemaOperacional, 
                processador);
        */
        
        List<Totem> listTotem = jdbcNuvem.query("SELECT * FROM totem WHERE token = ? AND fkCompanhia = ?",
                new BeanPropertyRowMapper(Totem.class), token, idCompanhia);
        
        cadastrarComponente(listTotem.get(0));
    }
    
    public void cadastrarComponente(Totem totem){
        Integer idTotem = totem.getIdTotem();
        Integer idCompanhia = totem.getFkCompanhia();
        
        //INSERINDO O COMPONENTE RAM (fkComponente = 3)
        long total = looca.getMemoria().getTotal();
        jdbcNuvem.update("INSERT INTO ComponenteTotem(fkTotem, fkComponente, total, fkCompanhia) VALUES (?,?,?,?)",
                    idTotem, 1, total, idCompanhia);
        
        //INSERINDO COMPONENTE DISCO (fkComponente = 2)
        total = looca.getGrupoDeDiscos().getTamanhoTotal();
        jdbcNuvem.update("INSERT INTO ComponenteTotem(fkTotem, fkComponente, total, fkCompanhia) VALUES (?,?,?,?)",
                    idTotem, 2, total, idCompanhia);
        
        //INSERINDO O COMPONENTE CPU (fkComponente = 3)
        long frequencia = looca.getProcessador().getFrequencia();
        String descricaoCpu = looca.getProcessador().getIdentificador();
        jdbcNuvem.update("INSERT INTO ComponenteTotem(fkTotem, fkComponente, frequencia, descricao, fkCompanhia) VALUES (?,?,?,?,?)",
                idTotem, 3, frequencia, descricaoCpu, idCompanhia);
    }
    
    
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
