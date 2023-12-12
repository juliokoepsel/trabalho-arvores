package arvores;

import java.util.LinkedList;
import java.util.Queue;

public class ArvoreBinaria {
    private class Nodo {
        private int chave;
        private Nodo dir, esq;

        public Nodo(int item) {
            this.chave = item;
            dir = esq = null;
        }
    }

    private Nodo raiz = null;

    public void inserir(int chave) {
        raiz = inserirDado(raiz, chave);
    }

    private Nodo inserirDado(Nodo raiz, int chave) {
        if (raiz == null) {
            raiz = new Nodo(chave);
        } else if (chave < raiz.chave) {
            raiz.esq = inserirDado(raiz.esq, chave);
        } else if (chave > raiz.chave) {
            raiz.dir = inserirDado(raiz.dir, chave);
        }
        return raiz;
    }

    public void mostrarEmOrdem() {
        mostrarOrdenado(raiz);
    }

    private void mostrarOrdenado(Nodo raiz) {
        if (raiz != null) {
            mostrarOrdenado(raiz.esq);
            System.out.println(raiz.chave);
            mostrarOrdenado(raiz.dir);
        }
    }

    public void mostrarEmOrdemDecrescente() {
        mostrarOrdenadoDecrescente(raiz);
    }

    private void mostrarOrdenadoDecrescente(Nodo raiz) {
        if (raiz != null) {
            mostrarOrdenadoDecrescente(raiz.dir);
            System.out.println(raiz.chave);
            mostrarOrdenadoDecrescente(raiz.esq);
        }
    }

    public void mostrarPorNivel() {
        if (raiz == null) {
            System.out.println("Árvore vazia!");
            return;
        }
        Queue<Nodo> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            int tamanhoNivel = fila.size();
            for (int i = 0; i < tamanhoNivel; i++) {
                Nodo nodoAtual = fila.poll();
                if (nodoAtual != null) {
                    System.out.print(nodoAtual.chave + " ");
                    fila.add(nodoAtual.esq);
                    fila.add(nodoAtual.dir);
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println(); // Nova linha para separar os níveis
        }
    }

    public void remover(int chave) {
        raiz = removerItem(raiz, chave);
    }

    private Nodo removerItem(Nodo raiz, int chave) {
        if (raiz == null) {
            // Nó não encontrado, não faz nada
            return null;
        } else if (chave < raiz.chave) {
            // Chave a ser removida está à esquerda
            raiz.esq = removerItem(raiz.esq, chave);
        } else if (chave > raiz.chave) {
            // Chave a ser removida está à direita
            raiz.dir = removerItem(raiz.dir, chave);
        } else {
            // Encontramos o nó a ser removido
            if (raiz.esq == null) {
                // Caso em que o nó não possui filho à esquerda
                return raiz.dir;
            } else if (raiz.dir == null) {
                // Caso em que o nó não possui filho à direita
                return raiz.esq;
            } else {
                // Caso em que o nó possui ambos os filhos
                // Nó sucessor será o menor da subárvore da direita
                Nodo sucessor = encontrarSucessor(raiz.dir);
                // Substituímos o valor do nó a ser removido pelo valor do sucessor
                raiz.chave = sucessor.chave;
                raiz.dir = removerItem(raiz.dir, sucessor.chave);
            }
        }
        return raiz;
    }

    private Nodo encontrarSucessor(Nodo nodo) {
        while (nodo.esq != null) {
            nodo = nodo.esq;
        }
        return nodo;
    }

    public void inserirSemRecursao(int chave) {
        Nodo aux = raiz;
        boolean proximo = true;
        while (proximo) {
            if (chave < aux.chave) {
                if (aux.esq == null) {
                    proximo = false;
                    aux.esq = new Nodo(chave);
                    System.out.println("Nó inserido!");
                } else {
                    aux = aux.esq;
                }
            } else if (chave > aux.chave) {
                if (aux.dir == null) {
                    proximo = false;
                    aux.dir = new Nodo(chave);
                    System.out.println("Nó inserido!");
                } else {
                    aux = aux.dir;
                }
            } else {
                proximo = false;
                System.out.println("Nó já existe na árvore!");
            }
        }
    }

    public boolean buscar(int chave) {
        Nodo aux = raiz;
        boolean resultado = false;
        while (null != aux) {
            if (chave < aux.chave) {
                aux = aux.esq;
            } else if (chave > aux.chave) {
                aux = aux.dir;
            } else {
                resultado = true;
                break;
            }
        }
        return resultado;
    }
}