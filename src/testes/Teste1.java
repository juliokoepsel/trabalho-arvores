package testes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import arvores.Arvore;
import arvores.ArvoreAVL;
import arvores.ArvoreB;
import arvores.ArvoreBinaria;

public class Teste1 {
    public static void main(String[] args) throws Exception {
        // Just in case:
        // https://stackoverflow.com/questions/3700459/how-to-increase-the-java-stack-size
        
        // Teste de desempenho para inserção e remoção

        // Inicialização das árvores
        ArvoreBinaria arvoreBinaria = new ArvoreBinaria(); // Objeto árvore binária de busca
        ArvoreAVL arvoreAVL = new ArvoreAVL(); // Objeto árvore AVL
        ArvoreB arvoreB4 = new ArvoreB(2); // Objeto árvore B de ordem 4 (t * 2)
        ArvoreB arvoreB6 = new ArvoreB(3); // Objeto árvore B de ordem 6 (t * 2)

        // Criação dos conjuntos de dados
        List<Integer> cemMil = new ArrayList<Integer>();
        List<Integer> umMilhao = new ArrayList<Integer>();
        for (int i = 1; i <= 100000; i++) {cemMil.add(i);}
        for (int i = 1; i <= 1000000; i++) {umMilhao.add(i);}
        String cemMilText = "ordenados (cemMil)";
        String umMilhaoText = "ordenados (umMilhao)";

        // Inserir dados ordenados arvoreBinaria cemMil
        inserir(cemMil, cemMilText, arvoreBinaria, "arvoreBinaria");
        // Remover dados ordenados arvoreBinaria cemMil
        remover(cemMil, cemMilText, arvoreBinaria, "arvoreBinaria");
        
        // Inserir dados ordenados arvoreBinaria umMilhao
        inserir(umMilhao, umMilhaoText, arvoreBinaria, "arvoreBinaria");
        // Remover dados ordenados arvoreBinaria umMilhao
        remover(umMilhao, umMilhaoText, arvoreBinaria, "arvoreBinaria");
        
        // Inserir dados ordenados arvoreAVL cemMil
        inserir(cemMil, cemMilText, arvoreAVL, "arvoreAVL");
        // Remover dados ordenados arvoreAVL cemMil
        remover(cemMil, cemMilText, arvoreAVL, "arvoreAVL");
        
        // Inserir dados ordenados arvoreAVL umMilhao
        inserir(umMilhao, umMilhaoText, arvoreAVL, "arvoreAVL");
        // Remover dados ordenados arvoreAVL umMilhao
        remover(umMilhao, umMilhaoText, arvoreAVL, "arvoreAVL");
        
        // Inserir dados ordenados arvoreB4 cemMil
        inserir(cemMil, cemMilText, arvoreB4, "arvoreB4");
        // Remover dados ordenados arvoreB4 cemMil
        remover(cemMil, cemMilText, arvoreB4, "arvoreB4");
        
        // Inserir dados ordenados arvoreB4 umMilhao
        inserir(umMilhao, umMilhaoText, arvoreB4, "arvoreB4");
        // Remover dados ordenados arvoreB4 umMilhao
        remover(umMilhao, umMilhaoText, arvoreB4, "arvoreB4");

        // Inserir dados ordenados arvoreB6 cemMil
        inserir(cemMil, cemMilText, arvoreB6, "arvoreB6");
        // Remover dados ordenados arvoreB6 cemMil
        remover(cemMil, cemMilText, arvoreB6, "arvoreB6");

        // Inserir dados ordenados arvoreB6 umMilhao
        inserir(umMilhao, umMilhaoText, arvoreB6, "arvoreB6");
        // Remover dados ordenados arvoreB6 umMilhao
        remover(umMilhao, umMilhaoText, arvoreB6, "arvoreB6");

        // Mistura da ordem dos dados
        Collections.shuffle(cemMil);
        Collections.shuffle(umMilhao);
        cemMilText = "não " + cemMilText;
        umMilhaoText = "não " + umMilhaoText;
        
        // Inserir dados não ordenados arvoreBinaria cemMil
        inserir(cemMil, cemMilText, arvoreBinaria, "arvoreBinaria");
        // Remover dados não ordenados arvoreBinaria cemMil
        remover(cemMil, cemMilText, arvoreBinaria, "arvoreBinaria");
        
        // Inserir dados não ordenados arvoreBinaria umMilhao
        inserir(umMilhao, umMilhaoText, arvoreBinaria, "arvoreBinaria");
        // Remover dados não ordenados arvoreBinaria umMilhao
        remover(umMilhao, umMilhaoText, arvoreBinaria, "arvoreBinaria");
        
        // Inserir dados não ordenados arvoreAVL cemMil
        inserir(cemMil, cemMilText, arvoreAVL, "arvoreAVL");
        // Remover dados não ordenados arvoreAVL cemMil
        remover(cemMil, cemMilText, arvoreAVL, "arvoreAVL");
        
        // Inserir dados não ordenados arvoreAVL umMilhao
        inserir(umMilhao, umMilhaoText, arvoreAVL, "arvoreAVL");
        // Remover dados não ordenados arvoreAVL umMilhao
        remover(umMilhao, umMilhaoText, arvoreAVL, "arvoreAVL");
        
        // Inserir dados não ordenados arvoreB4 cemMil
        inserir(cemMil, cemMilText, arvoreB4, "arvoreB4");
        // Remover dados não ordenados arvoreB4 cemMil
        remover(cemMil, cemMilText, arvoreB4, "arvoreB4");
        
        // Inserir dados não ordenados arvoreB4 umMilhao
        inserir(umMilhao, umMilhaoText, arvoreB4, "arvoreB4");
        // Remover dados não ordenados arvoreB4 umMilhao
        remover(umMilhao, umMilhaoText, arvoreB4, "arvoreB4");

        // Inserir dados não ordenados arvoreB6 cemMil
        inserir(cemMil, cemMilText, arvoreB6, "arvoreB6");
        // Remover dados não ordenados arvoreB6 cemMil
        remover(cemMil, cemMilText, arvoreB6, "arvoreB6");

        // Inserir dados não ordenados arvoreB6 umMilhao
        inserir(umMilhao, umMilhaoText, arvoreB6, "arvoreB6");
        // Remover dados não ordenados arvoreB6 umMilhao
        remover(umMilhao, umMilhaoText, arvoreB6, "arvoreB6");

    }
    private static void inserir(List<Integer> dados, String nomeDados, Arvore arvore, String nomeArvore) {
        long startTime = System.nanoTime();
        for (int i : dados) {arvore.inserir(i);}
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Inserir dados " + nomeDados + " na " + nomeArvore + ": " + elapsedTime/1000000 + " milissegundos");
    }
    private static void remover(List<Integer> dados, String nomeDados, Arvore arvore, String nomeArvore) {
        long startTime = System.nanoTime();
        for (int i : dados) {arvore.remover(i);}
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Remover dados " + nomeDados + " na " + nomeArvore + ": " + elapsedTime/1000000 + " milissegundos");
    }
}
