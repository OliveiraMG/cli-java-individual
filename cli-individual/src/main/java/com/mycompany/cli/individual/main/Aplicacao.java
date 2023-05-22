/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cli.individual.main;

import com.mycompany.cli.individual.controller.TotemController;
import java.util.Scanner;

/**
 *
 * @author oliveira
 */
public class Aplicacao {
        public static void main(String[] args) {
        Scanner leitor01 = new Scanner(System.in);
        TotemController totemController = new TotemController();
     
        System.out.println("Olá seja bem vindo ao sistema AIRPLANE SOLUTIONS");
        
        Integer resposta = 0;
        do{
            System.out.println("1 - Cadastrar um totem"
                            + "\n2 - Ver totens cadastrados"
                            + "\n3 - Ver componentes da sua máquina atual"
                            + "\n0 - Sair ");
            
            resposta = leitor01.nextInt();
            
            switch (resposta) {
                case 1:
                    totemController.cadastrarTotem();
                   break;
                case 2: 
                    totemController.exibirTotensCadastrados();
                    break;
                case 3: 
                    totemController.exibirComponentesMáquinaAtual();
                    break;
                case 0:
                    System.out.println("Até mais1!");
            }
        }
        while(resposta != 0);
    }
}

