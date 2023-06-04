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
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class ComponenteController {

    ConexaoLocal conexaoBancoLocal = new ConexaoLocal();
    ConexaoNuvem conexaoBancoNuvem = new ConexaoNuvem();
    JdbcTemplate jdbcLocal =  conexaoBancoLocal.getConexaoLocal();
    JdbcTemplate jdbcNuvem = conexaoBancoNuvem.getConexaoNuvem();

    Looca looca = new Looca();

    public void captureDataAllComponents() {
        exibirDadosCpu();
        exibirDadosRam();
        exibirDadosDisco();
    }

    public void exibirDadosCpu() {
        Integer clock = looca.getProcessador().getNumeroCpusFisicas() + looca.getProcessador().getNumeroCpusLogicas();
        Double clockOnDouble = clock.doubleValue();
        Long tempoDeAtividade = looca.getSistema().getTempoDeAtividade();
        
        System.out.println("---".repeat(20) + "Dados da CPU" + "---".repeat(20) );
        System.out.println(String.format("\nValor de uso: %d "
                + "\nClock: %f"
                + "\nTempo de atividade: %s"
                + "\nData de inicializacao: %s", looca.getProcessador().getUso().intValue(),
                clockOnDouble,
                Conversor.formatarSegundosDecorridos(tempoDeAtividade),
                Date.from(looca.getSistema().getInicializado())));
    }

    public void exibirDadosRam() {
        Double usoRam = looca.getMemoria().getEmUso().doubleValue();

        System.out.println("---".repeat(20) + "Dados da RAM" + "---".repeat(20) );
        System.out.println(String.format("\nValor de uso: %f "
                + "\nData de captura: %s", usoRam, new Date().toString()));
    }

    public void exibirDadosDisco() {
        Componente disco = new Componente();

        Double usoDisco = disco.getUsoDisco();
        Double tempoTransferencia = disco.getTempoTransferencia().doubleValue();
        
        System.out.println("---".repeat(20) + "Dados da CPU" + "---".repeat(20) );
        System.out.println(String.format("\nValor de uso: %d "
                + "\nTempo de transferência: %f"
                + "\nData de captura: %s", usoDisco, tempoTransferencia, new Date().toString()));
    }
}
