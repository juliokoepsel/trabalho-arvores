package testes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import arvores.Arvore;
import arvores.ArvoreAVL;
import arvores.ArvoreB;
import arvores.ArvoreBinaria;

public class Teste2 {
    public static void main(String[] args) throws Exception {
        
        // Teste de desempenho para busca

        // Inicialização das árvores
        ArvoreBinaria arvoreBinaria = new ArvoreBinaria(); // Objeto árvore binária de busca
        ArvoreAVL arvoreAVL = new ArvoreAVL(); // Objeto árvore AVL
        ArvoreB<Integer> arvoreB3 = new ArvoreB<Integer>(); // Objeto árvore B de ordem 3 (padrão)
        ArvoreB<Integer> arvoreB5 = new ArvoreB<Integer>(2); // Objeto árvore B de ordem 5 = (t * 2) + 1

        // Criação do conjunto de dados
        List<Integer> umMilhao = new ArrayList<Integer>();
        for (int i = 1; i <= 1000000; i++) {umMilhao.add(i);}
        String umMilhaoText = "ordenados (umMilhao)";
        int chaveNaoExistente = 1000001; // Chave não existente no conjunto

        // Inserir dados ordenados arvoreBinaria
        inserir(umMilhao, umMilhaoText, arvoreBinaria, "arvoreBinaria");
        // Buscar dado não existente arvoreBinaria
        buscar(chaveNaoExistente, arvoreBinaria, "arvoreBinaria");
        // Inserir dados ordenados arvoreAVL
        inserir(umMilhao, umMilhaoText, arvoreAVL, "arvoreAVL");
        // Buscar dado não existente arvoreAVL
        buscar(chaveNaoExistente, arvoreAVL, "arvoreAVL");
        // Inserir dados ordenados arvoreB3
        inserir(umMilhao, umMilhaoText, arvoreB3, "arvoreB3");
        // Buscar dado não existente arvoreB3
        buscar(chaveNaoExistente, arvoreB3, "arvoreB3");
        // Inserir dados ordenados arvoreB5
        inserir(umMilhao, umMilhaoText, arvoreB5, "arvoreB5");
        // Buscar dado não existente arvoreB5
        buscar(chaveNaoExistente, arvoreB5, "arvoreB5");

        // Reinicialização das árvores
        arvoreBinaria = new ArvoreBinaria();
        arvoreAVL = new ArvoreAVL();
        arvoreB3 = new ArvoreB<Integer>();
        arvoreB5 = new ArvoreB<Integer>(2);

        // Mistura da ordem dos dados
        Collections.shuffle(umMilhao);
        umMilhaoText = "não " + umMilhaoText;

        // Inserir dados não ordenados arvoreBinaria
        inserir(umMilhao, umMilhaoText, arvoreBinaria, "arvoreBinaria");
        // Buscar dado não existente arvoreBinaria
        buscar(chaveNaoExistente, arvoreBinaria, "arvoreBinaria");
        // Inserir dados não ordenados arvoreAVL
        inserir(umMilhao, umMilhaoText, arvoreAVL, "arvoreAVL");
        // Buscar dado não existente arvoreAVL
        buscar(chaveNaoExistente, arvoreAVL, "arvoreAVL");
        // Inserir dados não ordenados arvoreB3
        inserir(umMilhao, umMilhaoText, arvoreB3, "arvoreB3");
        // Buscar dado não existente arvoreB3
        buscar(chaveNaoExistente, arvoreB3, "arvoreB3");
        // Inserir dados não ordenados arvoreB5
        inserir(umMilhao, umMilhaoText, arvoreB5, "arvoreB5");
        // Buscar dado não existente arvoreB5
        buscar(chaveNaoExistente, arvoreB5, "arvoreB5");

    }
    private static void inserir(List<Integer> dados, String nomeDados, Arvore arvore, String nomeArvore) {
        for (int i : dados) {arvore.inserir(i);}
        System.out.println("Inserir dados " + nomeDados + " na " + nomeArvore);
    }
    private static void buscar(int dado, Arvore arvore, String nomeArvore) {
        long startTime = System.nanoTime();
        arvore.buscar(dado);
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Buscar dado não existente na " + nomeArvore + ": " + elapsedTime + " nanosegundos");
    }
    private static void inserir(List<Integer> dados, String nomeDados, ArvoreB<Integer> arvore, String nomeArvore) {
        for (int i : dados) {arvore.inserir(i);}
        System.out.println("Inserir dados " + nomeDados + " na " + nomeArvore);
    }
    private static void buscar(int dado, ArvoreB<Integer> arvore, String nomeArvore) {
        long startTime = System.nanoTime();
        arvore.buscar(dado);
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Buscar dado não existente na " + nomeArvore + ": " + elapsedTime + " nanosegundos");
    }
}
