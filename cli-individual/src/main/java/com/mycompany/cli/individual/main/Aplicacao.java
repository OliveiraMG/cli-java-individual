/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cli.individual.main;

import com.mycompany.cli.individual.controller.ComponenteController;
import com.mycompany.cli.individual.controller.TotemController;
import com.mycompany.cli.individual.model.Totem;

import java.util.Scanner;

/**
 *
 * @author oliveira
 */
public class Aplicacao {
        public static void main(String[] args) {
        Scanner leitor01 = new Scanner(System.in);
        TotemController totemController = new TotemController();
        ComponenteController componenteController = new ComponenteController();
     
        System.out.println("Olá seja bem vindo ao sistema AIRPLANE SOLUTIONS");
        
        Totem totem = new Totem();
        
        Integer resposta = 0;
        do{
            System.out.println("1 - Cadastrar um totem"
                            + "\n2 - Ver totens cadastrados"
                            + "\n3 - Ver componentes da sua máquina atual"
                            + "\n4 - Ver dados somente da CPU"
                            + "\n5 - Ver dados somente da Memória RAM"
                            + "\n6 - Ver dados somente do Disco"
                            + "\n0 - Sair ");
            
            resposta = leitor01.nextInt();
            
            switch (resposta) {
                case 1:
                    System.out.println("Cadastrando um totem");
                    totemController.cadastrarTotem();
                   break;
                case 2:
                    System.out.println("Exibindo totens cadastrados");
                    totemController.exibirTotensCadastrados();
                    break;
                case 3:
                    System.out.println("Exibindo componentes da sua máquina atual");
                    totemController.exibirComponentesMaquinaAtual();
                    break;
                case 4:
                    System.out.println("Exibindo dados da CPU");
                    componenteController.exibirDadosCpu(totem);
                    break;

                case 5:
                    System.out.println("Exibindo dados da Memória RAM");
                    componenteController.exibirDadosRam(totem);
                    break;

                case 6:
                    System.out.println("Exibindo dados do Disco");
                    componenteController.exibirDadosDisco(totem);
                    break;

                case 0:
                    System.out.println("Até mais!");
            }
        }
        while(resposta != 0);
    }
}

