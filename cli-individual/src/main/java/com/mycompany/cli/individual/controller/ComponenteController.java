package com.mycompany.cli.individual.controller;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.util.Conversor;
import com.mycompany.cli.individual.dao.ConexaoLocal;
import com.mycompany.cli.individual.dao.ConexaoNuvem;
import com.mycompany.cli.individual.model.Componente;
import com.mycompany.cli.individual.model.Totem;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class ComponenteController {

    ConexaoLocal conexaoBancoLocal = new ConexaoLocal();
    ConexaoNuvem conexaoBancoNuvem = new ConexaoNuvem();
    JdbcTemplate jdbcLocal =  conexaoBancoLocal.getConexaoLocal();
    JdbcTemplate jdbcNuvem = conexaoBancoNuvem.getConexaoNuvem();

    Looca looca = new Looca();

    Scanner leitor02 = new Scanner(System.in);

    public void captureDataAllComponents(Totem totem) {
        exibirDadosCpu(totem);
        exibirDadosRam(totem);
        exibirDadosDisco(totem);
    }

    public void exibirDadosCpu(Totem totem) {
        Integer fkComponente = 3;
        Integer clock = looca.getProcessador().getNumeroCpusFisicas() + looca.getProcessador().getNumeroCpusLogicas();
        Double clockOnDouble = clock.doubleValue();
        
        List<Totem> ultimoTotem = jdbcLocal.query("SELECT TOP 1 idTotem + 1 FROM totem ORDER BY idTotem DESC", new BeanPropertyRowMapper(Componente.class));

        Long tempoDeAtividade = looca.getSistema().getTempoDeAtividade();

        jdbcLocal.update("INSERT INTO registroComponente(" +
                        "fkTotem, fkComponente, valorUso, clock, dataHoraCaptura, " +
                        "tempoAtividade, dataInicializacao)" +
                        " VALUES (?,?,?,?,?,?,?)",
                ultimoTotem.get(0).getIdTotem(),
                fkComponente,
                looca.getProcessador().getUso().intValue(),
                clockOnDouble,
                new Date(),
                Conversor.formatarSegundosDecorridos(tempoDeAtividade),
                Date.from(looca.getSistema().getInicializado())
        );
        
        System.out.println("---".repeat(20) + "Dados da CPU" + "---".repeat(20) );
        System.out.println(String.format("\nValor de uso: %d "
                + "\nClock: %.2f"
                + "\nTempo de atividade: %s"
                + "\nData de inicialização: %s", looca.getProcessador().getUso().intValue(),
                clockOnDouble,
                Conversor.formatarSegundosDecorridos(tempoDeAtividade),
                Date.from(looca.getSistema().getInicializado())));
    }

    public void exibirDadosRam(Totem totem) {
        Integer fkComponente = 1;
        Long usoRam = looca.getMemoria().getEmUso();
        
        List<Totem> ultimoTotem = jdbcLocal.query("SELECT TOP 1 idTotem + 1 FROM totem ORDER BY idTotem DESC", new BeanPropertyRowMapper(Componente.class));

        jdbcLocal.update("INSERT INTO registroComponente(" +
                        "fkTotem, fkComponente, valorUso, dataHoraCaptura)" +
                        "VALUES (?,?,?,?)",
                ultimoTotem.get(0).getIdTotem(), fkComponente, usoRam, new Date()
        );
    }

    public void exibirDadosDisco(Totem totem) {
        Integer fkComponente = 2;
        Componente disco = new Componente();

        Double usoDisco = disco.getUsoDisco();
        Long tempoTransferencia = disco.getTempoTransferencia();

        jdbcLocal.update("INSERT INTO registroComponente(" +
                        "fkTotem, fkComponente, valorUso, tempoTransferencia, dataHoraCaptura) " +
                        "VALUES (?,?,?,?,?)",
                totem.getIdTotem(), fkComponente, usoDisco, tempoTransferencia, new Date()
        );
    }
}
