package com.mycompany.cli.individual.controller;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.util.Conversor;
import com.mycompany.cli.individual.dao.ConexaoLocal;
import com.mycompany.cli.individual.dao.ConexaoNuvem;
import com.mycompany.cli.individual.model.ComponenteDisco;
import com.mycompany.cli.individual.model.Totem;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.Scanner;

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

        Long tempoDeAtividade = looca.getSistema().getTempoDeAtividade();

        jdbcLocal.update("INSERT INTO registroComponente(" +
                        "fkTotem, fkComponente, valorUso, clock, dataHoraCaptura, " +
                        "tempoAtividade, dataInicializacao)" +
                        " VALUES (?,?,?,?,?,?,?)",
                totem.getIdTotem(),
                fkComponente,
                looca.getProcessador().getUso().intValue(),
                clockOnDouble,
                new Date(),
                Conversor.formatarSegundosDecorridos(tempoDeAtividade),
                Date.from(looca.getSistema().getInicializado())
        );
    }

    public void exibirDadosRam(Totem totem) {
        Integer fkComponente = 1;
        Long usoRam = looca.getMemoria().getEmUso();

        jdbcLocal.update("INSERT INTO registroComponente(" +
                        "fkTotem, fkComponente, valorUso, dataHoraCaptura)" +
                        "VALUES (?,?,?,?)",
                totem.getIdTotem(), fkComponente, usoRam, new Date()
        );
    }

    public void exibirDadosDisco(Totem totem) {
        Integer fkComponente = 2;
        ComponenteDisco disco = new ComponenteDisco();

        Double usoDisco = disco.getUsoDisco();
        Long tempoTransferencia = disco.getTempoTransferencia();

        jdbcLocal.update("INSERT INTO registroComponente(" +
                        "fkTotem, fkComponente, valorUso, tempoTransferencia, dataHoraCaptura) " +
                        "VALUES (?,?,?,?,?)",
                totem.getIdTotem(), fkComponente, usoDisco, tempoTransferencia, new Date()
        );
    }
}
