package testes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import arvores.ArvoreAVL;
import arvores.ArvoreB;
import arvores.ArvoreBinaria;

public class Teste1 {
    public static void main(String[] args) throws Exception {
        // Just in case:
        // https://stackoverflow.com/questions/3700459/how-to-increase-the-java-stack-size
        
        // Teste de desempenho para inserção e remoção

        //long startTime = System.nanoTime();
        //long elapsedTime = System.nanoTime() - startTime;
        //System.out.println("Total execution time to create 1000K objects in Java in millis: " + elapsedTime/1000000);

        // Inicialização das árvores
        ArvoreBinaria arvoreBinaria = new ArvoreBinaria(); // Objeto árvore binária de busca
        ArvoreAVL arvoreAVL = new ArvoreAVL(); // Objeto árvore AVL
        ArvoreB arvoreB4 = new ArvoreB(2); // Objeto árvore B de ordem 4 (t * 2)
        ArvoreB arvoreB6 = new ArvoreB(3); // Objeto árvore B de ordem 6 (t * 2)

        // Criação dos conjuntos de dados
        List<Integer> cemMil = new ArrayList<Integer>();
        List<Integer> umMilhao = new ArrayList<Integer>();
        for (int i = 1; i <= 100000; i++) {
            cemMil.add(i);
        }
        for (int i = 1; i <= 1000000; i++) {
            umMilhao.add(i);
        }

        // Inicialização das variáveis para medição de tempo
        long startTime, elapsedTime;

        // TODO: Inserir dados ordenados arvoreBinaria
        // TODO: Remover dados ordenados arvoreBinaria
        // TODO: Inserir dados ordenados arvoreAVL
        // TODO: Remover dados ordenados arvoreAVL
        // TODO: Inserir dados ordenados arvoreB4
        // TODO: Remover dados ordenados arvoreB4
        // TODO: Inserir dados ordenados arvoreB6
        // TODO: Remover dados ordenados arvoreB6

        // Mistura da ordem dos dados
        Collections.shuffle(cemMil);
        Collections.shuffle(umMilhao);

        // TODO: Inserir dados não ordenados arvoreBinaria
        // TODO: Remover dados não ordenados arvoreBinaria
        // TODO: Inserir dados não ordenados arvoreAVL
        // TODO: Remover dados não ordenados arvoreAVL
        // TODO: Inserir dados não ordenados arvoreB4
        // TODO: Remover dados não ordenados arvoreB4
        // TODO: Inserir dados não ordenados arvoreB6
        // TODO: Remover dados não ordenados arvoreB6

    }
}
