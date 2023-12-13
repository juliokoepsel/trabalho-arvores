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
        ArvoreB arvoreB4 = new ArvoreB(2); // Objeto árvore B de ordem 4 (t * 2)
        ArvoreB arvoreB6 = new ArvoreB(3); // Objeto árvore B de ordem 6 (t * 2)

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
        // Inserir dados ordenados arvoreB4
        inserir(umMilhao, umMilhaoText, arvoreB4, "arvoreB4");
        // Buscar dado não existente arvoreB4
        buscar(chaveNaoExistente, arvoreB4, "arvoreB4");
        // Inserir dados ordenados arvoreB6
        inserir(umMilhao, umMilhaoText, arvoreB6, "arvoreB6");
        // Buscar dado não existente arvoreB6
        buscar(chaveNaoExistente, arvoreB6, "arvoreB6");

        // Reinicialização das árvores
        arvoreBinaria = new ArvoreBinaria();
        arvoreAVL = new ArvoreAVL();
        arvoreB4 = new ArvoreB(2);
        arvoreB6 = new ArvoreB(3);

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
        // Inserir dados não ordenados arvoreB4
        inserir(umMilhao, umMilhaoText, arvoreB4, "arvoreB4");
        // Buscar dado não existente arvoreB4
        buscar(chaveNaoExistente, arvoreB4, "arvoreB4");
        // Inserir dados não ordenados arvoreB6
        inserir(umMilhao, umMilhaoText, arvoreB6, "arvoreB6");
        // Buscar dado não existente arvoreB6
        buscar(chaveNaoExistente, arvoreB6, "arvoreB6");

    }
    private static void inserir(List<Integer> dados, String nomeDados, Arvore arvore, String nomeArvore) {
        long startTime = System.nanoTime();
        for (int i : dados) {arvore.inserir(i);}
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Inserir dados " + nomeDados + " na " + nomeArvore + ": " + elapsedTime/1000000 + " milissegundos");
    }
    private static void buscar(int dado, Arvore arvore, String nomeArvore) {
        long startTime = System.nanoTime();
        arvore.buscar(dado);
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Buscar dado não existente na " + nomeArvore + ": " + elapsedTime/1000000 + " milissegundos");
    }
}
