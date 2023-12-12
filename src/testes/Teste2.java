package testes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        for (int i = 1; i <= 1000000; i++) {
            umMilhao.add(i);
        }
        int chaveNaoExistente = 1000001; // Chave não existente no conjunto

        // Inicialização das variáveis para medição de tempo
        long startTime, elapsedTime;

        // TODO: Inserir dados ordenados arvoreBinaria
        // TODO: Buscar dado não existente arvoreBinaria
        // TODO: Inserir dados ordenados arvoreAVL
        // TODO: Buscar dado não existente arvoreAVL
        // TODO: Inserir dados ordenados arvoreB4
        // TODO: Buscar dado não existente arvoreB4
        // TODO: Inserir dados ordenados arvoreB6
        // TODO: Buscar dado não existente arvoreB6

        // Mistura da ordem dos dados
        Collections.shuffle(umMilhao);

        // TODO: Inserir dados não ordenados arvoreBinaria
        // TODO: Buscar dado não existente arvoreBinaria
        // TODO: Inserir dados não ordenados arvoreAVL
        // TODO: Buscar dado não existente arvoreAVL
        // TODO: Inserir dados não ordenados arvoreB4
        // TODO: Buscar dado não existente arvoreB4
        // TODO: Inserir dados não ordenados arvoreB6
        // TODO: Buscar dado não existente arvoreB6

    }
}
