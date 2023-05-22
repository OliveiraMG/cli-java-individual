/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cli.individual.model;

import com.github.britooo.looca.api.core.Looca;

/**
 *
 * @author oliveira
 */
public class Totem {
    private Integer idTotem;
    private Integer fkCompanhia;
    private String token;
    private String fabricante;
    private Integer arquitetura;
    private String sistemaOperacional;
    private String processador;
    private String localizacaoTotem;
    Looca looca = new Looca();

    public Totem() {
    }

    public Totem(Integer idTotem, Integer fkCompanhia, String token, String fabricante, Integer arquitetura, String sistemaOperacional, String processador, String localizacaoTotem) {
        this.idTotem = idTotem;
        this.fkCompanhia = fkCompanhia;
        this.token = token;
        this.fabricante = fabricante;
        this.arquitetura = arquitetura;
        this.sistemaOperacional = sistemaOperacional;
        this.processador = processador;
        this.localizacaoTotem = localizacaoTotem;
    }

    public Integer getIdTotem() {
        return idTotem;
    }

    public void setIdTotem(Integer idTotem) {
        this.idTotem = idTotem;
    }

    public Integer getFkCompanhia() {
        return fkCompanhia;
    }

    public void setFkCompanhia(Integer fkCompanhia) {
        this.fkCompanhia = fkCompanhia;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Integer getArquitetura() {
        return arquitetura;
    }

    public void setArquitetura(Integer arquitetura) {
        this.arquitetura = arquitetura;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public String getLocalizacaoTotem() {
        return localizacaoTotem;
    }

    public void setLocalizacaoTotem(String localizacaoTotem) {
        this.localizacaoTotem = localizacaoTotem;
    }

    public Looca getLooca() {
        return looca;
    }

    public void setLooca(Looca looca) {
        this.looca = looca;
    }

    

    @Override
    public String toString() {
        return "DadosTotem{" + "idTotem=" + idTotem + ", fkCompanhia=" + fkCompanhia + ", fabricante=" + fabricante + ", arquitetura=" + arquitetura + ", sistemaOperacional=" + sistemaOperacional + ", processador=" + processador + ", localizacaoTotem=" + localizacaoTotem + ", token=" + token + '}';
    }
    
    
}
