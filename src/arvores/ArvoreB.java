package arvores;

// ArvoreB desenvolvida utilizando como base:
// https://github.com/phishman3579/java-algorithms-implementation/blob/master/src/com/jwetherell/algorithms/data_structures/BTree.java

import java.util.Arrays;
import java.util.Comparator;

@SuppressWarnings("unchecked")
public class ArvoreB<T extends Comparable<T>> {

    private int minKeySize = 1;
    private int minChildrenSize = minKeySize + 1; // 2
    private int maxKeySize = 2 * minKeySize; // 2
    private int maxChildrenSize = maxKeySize + 1; // 3
    private Nodo<T> root = null;
    private int size = 0;

    // Construtor ArvoreB vazio (cria uma árvore 3)
    public ArvoreB() {}
    // Construtor ArvoreB
    public ArvoreB(int order) {
        this.minKeySize = order;
        this.minChildrenSize = minKeySize + 1;
        this.maxKeySize = 2 * minKeySize;
        this.maxChildrenSize = maxKeySize + 1;
    }
    // Função de inserção
    public boolean inserir(T value) {
        if (root == null) {
            root = new Nodo<T>(null, maxKeySize, maxChildrenSize);
            root.inserirKey(value);
        } else {
            Nodo<T> node = root;
            while (node != null) {
                if (node.numberOfChildren() == 0) {
                    node.inserirKey(value);
                    if (node.numberOfKeys() <= maxKeySize) {
                        break;
                    }
                    separar(node);
                    break;
                }
                T lesser = node.getKey(0);
                if (value.compareTo(lesser) <= 0) {
                    node = node.getChild(0);
                    continue;
                }
                int numberOfKeys = node.numberOfKeys();
                int last = numberOfKeys - 1;
                T greater = node.getKey(last);
                if (value.compareTo(greater) > 0) {
                    node = node.getChild(numberOfKeys);
                    continue;
                }
                for (int i = 1; i < node.numberOfKeys(); i++) {
                    T prev = node.getKey(i - 1);
                    T next = node.getKey(i);
                    if (value.compareTo(prev) > 0 && value.compareTo(next) <= 0) {
                        node = node.getChild(i);
                        break;
                    }
                }
            }
        }
        size++;
        return true;
    }
    // Função de separação de páginas
    private void separar(Nodo<T> nodeToSplit) {
        Nodo<T> node = nodeToSplit;
        int numberOfKeys = node.numberOfKeys();
        int medianIndice = numberOfKeys / 2;
        T medianValor = node.getKey(medianIndice);

        Nodo<T> left = new Nodo<T>(null, maxKeySize, maxChildrenSize);
        for (int i = 0; i < medianIndice; i++) {
            left.inserirKey(node.getKey(i));
        }
        if (node.numberOfChildren() > 0) {
            for (int j = 0; j <= medianIndice; j++) {
                Nodo<T> c = node.getChild(j);
                left.inserirChild(c);
            }
        }

        Nodo<T> right = new Nodo<T>(null, maxKeySize, maxChildrenSize);
        for (int i = medianIndice + 1; i < numberOfKeys; i++) {
            right.inserirKey(node.getKey(i));
        }
        if (node.numberOfChildren() > 0) {
            for (int j = medianIndice + 1; j < node.numberOfChildren(); j++) {
                Nodo<T> c = node.getChild(j);
                right.inserirChild(c);
            }
        }

        if (node.parent == null) {
            Nodo<T> newRoot = new Nodo<T>(null, maxKeySize, maxChildrenSize);
            newRoot.inserirKey(medianValor);
            node.parent = newRoot;
            root = newRoot;
            node = root;
            node.inserirChild(left);
            node.inserirChild(right);
        } else {
            Nodo<T> parent = node.parent;
            parent.inserirKey(medianValor);
            parent.removerChild(node);
            parent.inserirChild(left);
            parent.inserirChild(right);

            if (parent.numberOfKeys() > maxKeySize) separar(parent);
        }
    }
    // Função para remoção
    public T remover(T value) {
        T removido = null;
        Nodo<T> node = this.getNodo(value);
        removido = remover(value,node);
        return removido;
    }
    // Função para remoção de Nodo
    private T remover(T value, Nodo<T> node) {
        if (node == null) return null;

        T removido = null;
        int index = node.indexOf(value);
        removido = node.removerKey(value);
        if (node.numberOfChildren() == 0) {
            if (node.parent != null && node.numberOfKeys() < minKeySize) {
                this.combinado(node);
            } else if (node.parent == null && node.numberOfKeys() == 0) {
                root = null;
            }
        } else {
            Nodo<T> lesser = node.getChild(index);
            Nodo<T> greatest = this.getMaiorNodo(lesser);
            T replaceValor = this.removerMaiorValor(greatest);
            node.inserirKey(replaceValor);
            if (greatest.parent != null && greatest.numberOfKeys() < minKeySize) {
                this.combinado(greatest);
            }
            if (greatest.numberOfChildren() > maxChildrenSize) {
                this.separar(greatest);
            }
        }
        size--;
        return removido;
    }
    // Função para remoção do maior valor
    private T removerMaiorValor(Nodo<T> node) {
        T value = null;
        if (node.numberOfKeys() > 0) {
            value = node.removerKey(node.numberOfKeys() - 1);
        }
        return value;
    }
    // Função para buscar
    public boolean buscar(T value) {
        Nodo<T> node = getNodo(value);
        return (node != null);
    }
    // Função para retornar Nodo com certo valor
    private Nodo<T> getNodo(T value) {
        Nodo<T> node = root;
        while (node != null) {
            T lesser = node.getKey(0);
            if (value.compareTo(lesser) < 0) {
                if (node.numberOfChildren() > 0)
                    node = node.getChild(0);
                else
                    node = null;
                continue;
            }
            int numberOfKeys = node.numberOfKeys();
            int last = numberOfKeys - 1;
            T greater = node.getKey(last);
            if (value.compareTo(greater) > 0) {
                if (node.numberOfChildren() > numberOfKeys)
                    node = node.getChild(numberOfKeys);
                else
                    node = null;
                continue;
            }
            for (int i = 0; i < numberOfKeys; i++) {
                T currentValor = node.getKey(i);
                if (currentValor.compareTo(value) == 0) {
                    return node;
                }
                int next = i + 1;
                if (next <= last) {
                    T nextValor = node.getKey(next);
                    if (currentValor.compareTo(value) < 0 && nextValor.compareTo(value) > 0) {
                        if (next < node.numberOfChildren()) {
                            node = node.getChild(next);
                            break;
                        }
                        return null;
                    }
                }
            }
        }
        return null;
    }
    // Função para retornar maior Nodo
    private Nodo<T> getMaiorNodo(Nodo<T> nodeToGet) {
        Nodo<T> node = nodeToGet;
        while (node.numberOfChildren() > 0) {
            node = node.getChild(node.numberOfChildren() - 1);
        }
        return node;
    }
    // Função de combinação de páginas
    private boolean combinado(Nodo<T> node) {
        Nodo<T> parent = node.parent;
        int index = parent.indexOf(node);
        int indexOfLeftNeighbor = index - 1;
        int indexOfRightNeighbor = index + 1;

        Nodo<T> rightNeighbor = null;
        int rightNeighborSize = -minChildrenSize;
        if (indexOfRightNeighbor < parent.numberOfChildren()) {
            rightNeighbor = parent.getChild(indexOfRightNeighbor);
            rightNeighborSize = rightNeighbor.numberOfKeys();
        }

        if (rightNeighbor != null && rightNeighborSize > minKeySize) {
            T removerValor = rightNeighbor.getKey(0);
            int prev = getIndiceDoAnteriorValor(parent, removerValor);
            T parentValor = parent.removerKey(prev);
            T neighborValor = rightNeighbor.removerKey(0);
            node.inserirKey(parentValor);
            parent.inserirKey(neighborValor);
            if (rightNeighbor.numberOfChildren() > 0) {
                node.inserirChild(rightNeighbor.removerChild(0));
            }
        } else {
            Nodo<T> leftNeighbor = null;
            int leftNeighborSize = -minChildrenSize;
            if (indexOfLeftNeighbor >= 0) {
                leftNeighbor = parent.getChild(indexOfLeftNeighbor);
                leftNeighborSize = leftNeighbor.numberOfKeys();
            }

            if (leftNeighbor != null && leftNeighborSize > minKeySize) {
                T removerValor = leftNeighbor.getKey(leftNeighbor.numberOfKeys() - 1);
                int prev = getIndiceDoProximoValor(parent, removerValor);
                T parentValor = parent.removerKey(prev);
                T neighborValor = leftNeighbor.removerKey(leftNeighbor.numberOfKeys() - 1);
                node.inserirKey(parentValor);
                parent.inserirKey(neighborValor);
                if (leftNeighbor.numberOfChildren() > 0) {
                    node.inserirChild(leftNeighbor.removerChild(leftNeighbor.numberOfChildren() - 1));
                }
            } else if (rightNeighbor != null && parent.numberOfKeys() > 0) {
                T removerValor = rightNeighbor.getKey(0);
                int prev = getIndiceDoAnteriorValor(parent, removerValor);
                T parentValor = parent.removerKey(prev);
                parent.removerChild(rightNeighbor);
                node.inserirKey(parentValor);
                for (int i = 0; i < rightNeighbor.keysSize; i++) {
                    T v = rightNeighbor.getKey(i);
                    node.inserirKey(v);
                }
                for (int i = 0; i < rightNeighbor.childrenSize; i++) {
                    Nodo<T> c = rightNeighbor.getChild(i);
                    node.inserirChild(c);
                }

                if (parent.parent != null && parent.numberOfKeys() < minKeySize) {
                    this.combinado(parent);
                } else if (parent.numberOfKeys() == 0) {
                    node.parent = null;
                    root = node;
                }
            } else if (leftNeighbor != null && parent.numberOfKeys() > 0) {
                T removerValor = leftNeighbor.getKey(leftNeighbor.numberOfKeys() - 1);
                int prev = getIndiceDoProximoValor(parent, removerValor);
                T parentValor = parent.removerKey(prev);
                parent.removerChild(leftNeighbor);
                node.inserirKey(parentValor);
                for (int i = 0; i < leftNeighbor.keysSize; i++) {
                    T v = leftNeighbor.getKey(i);
                    node.inserirKey(v);
                }
                for (int i = 0; i < leftNeighbor.childrenSize; i++) {
                    Nodo<T> c = leftNeighbor.getChild(i);
                    node.inserirChild(c);
                }

                if (parent.parent != null && parent.numberOfKeys() < minKeySize) {
                    this.combinado(parent);
                } else if (parent.numberOfKeys() == 0) {
                    node.parent = null;
                    root = node;
                }
            }
        }

        return true;
    }
    // Função para retornar o índice do valor anterior
    private int getIndiceDoAnteriorValor(Nodo<T> node, T value) {
        for (int i = 1; i < node.numberOfKeys(); i++) {
            T t = node.getKey(i);
            if (t.compareTo(value) >= 0)
                return i - 1;
        }
        return node.numberOfKeys() - 1;
    }
    // Função para retornar o índice do próximo valor
    private int getIndiceDoProximoValor(Nodo<T> node, T value) {
        for (int i = 0; i < node.numberOfKeys(); i++) {
            T t = node.getKey(i);
            if (t.compareTo(value) >= 0)
                return i;
        }
        return node.numberOfKeys() - 1;
    }
    // Função para mostrar a árvore
    public void mostrar() {
        System.out.println(toString());
    }
    //Função toString()
    @Override
    public String toString() {
        return TreePrinter.getString(this);
    }
    // Classe Nodo
    private static class Nodo<T extends Comparable<T>> {

        private T[] keys = null;
        private int keysSize = 0;
        private Nodo<T>[] children = null;
        private int childrenSize = 0;
        private Comparator<Nodo<T>> comparator = new Comparator<Nodo<T>>() {
            @Override
            public int compare(Nodo<T> arg0, Nodo<T> arg1) {
                return arg0.getKey(0).compareTo(arg1.getKey(0));
            }
        };

        protected Nodo<T> parent = null;

        private Nodo(Nodo<T> parent, int maxKeySize, int maxChildrenSize) {
            this.parent = parent;
            this.keys = (T[]) new Comparable[maxKeySize + 1];
            this.keysSize = 0;
            this.children = new Nodo[maxChildrenSize + 1];
            this.childrenSize = 0;
        }

        private T getKey(int index) {
            return keys[index];
        }

        private int indexOf(T value) {
            for (int i = 0; i < keysSize; i++) {
                if (keys[i].equals(value)) return i;
            }
            return -1;
        }

        private void inserirKey(T value) {
            keys[keysSize++] = value;
            Arrays.sort(keys, 0, keysSize);
        }

        private T removerKey(T value) {
            T removido = null;
            boolean found = false;
            if (keysSize == 0) return null;
            for (int i = 0; i < keysSize; i++) {
                if (keys[i].equals(value)) {
                    found = true;
                    removido = keys[i];
                } else if (found) {
                    keys[i - 1] = keys[i];
                }
            }
            if (found) {
                keysSize--;
                keys[keysSize] = null;
            }
            return removido;
        }

        private T removerKey(int index) {
            if (index >= keysSize)
                return null;
            T value = keys[index];
            for (int i = index + 1; i < keysSize; i++) {
                keys[i - 1] = keys[i];
            }
            keysSize--;
            keys[keysSize] = null;
            return value;
        }

        private int numberOfKeys() {
            return keysSize;
        }

        private Nodo<T> getChild(int index) {
            if (index >= childrenSize)
                return null;
            return children[index];
        }

        private int indexOf(Nodo<T> child) {
            for (int i = 0; i < childrenSize; i++) {
                if (children[i].equals(child))
                    return i;
            }
            return -1;
        }

        private boolean inserirChild(Nodo<T> child) {
            child.parent = this;
            children[childrenSize++] = child;
            Arrays.sort(children, 0, childrenSize, comparator);
            return true;
        }

        private boolean removerChild(Nodo<T> child) {
            boolean found = false;
            if (childrenSize == 0)
                return found;
            for (int i = 0; i < childrenSize; i++) {
                if (children[i].equals(child)) {
                    found = true;
                } else if (found) {
                    children[i - 1] = children[i];
                }
            }
            if (found) {
                childrenSize--;
                children[childrenSize] = null;
            }
            return found;
        }

        private Nodo<T> removerChild(int index) {
            if (index >= childrenSize)
                return null;
            Nodo<T> value = children[index];
            children[index] = null;
            for (int i = index + 1; i < childrenSize; i++) {
                children[i - 1] = children[i];
            }
            childrenSize--;
            children[childrenSize] = null;
            return value;
        }

        private int numberOfChildren() {
            return childrenSize;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();

            builder.append("keys=[");
            for (int i = 0; i < numberOfKeys(); i++) {
                T value = getKey(i);
                builder.append(value);
                if (i < numberOfKeys() - 1)
                    builder.append(", ");
            }
            builder.append("]\n");

            if (parent != null) {
                builder.append("parent=[");
                for (int i = 0; i < parent.numberOfKeys(); i++) {
                    T value = parent.getKey(i);
                    builder.append(value);
                    if (i < parent.numberOfKeys() - 1)
                        builder.append(", ");
                }
                builder.append("]\n");
            }

            if (children != null) {
                builder.append("keySize=").append(numberOfKeys()).append(" children=").append(numberOfChildren()).append("\n");
            }

            return builder.toString();
        }
    }
    // Classe de construção de desenho da árvore
    private static class TreePrinter {

        public static <T extends Comparable<T>> String getString(ArvoreB<T> tree) {
            if (tree.root == null) return "Árvore não tem nodos.";
            return getString(tree.root, "", true);
        }

        private static <T extends Comparable<T>> String getString(Nodo<T> node, String prefix, boolean isTail) {
            StringBuilder builder = new StringBuilder();

            builder.append(prefix).append((isTail ? "└── " : "├── "));
            for (int i = 0; i < node.numberOfKeys(); i++) {
                T value = node.getKey(i);
                builder.append(value);
                if (i < node.numberOfKeys() - 1)
                    builder.append(", ");
            }
            builder.append("\n");

            if (node.children != null) {
                for (int i = 0; i < node.numberOfChildren() - 1; i++) {
                    Nodo<T> obj = node.getChild(i);
                    builder.append(getString(obj, prefix + (isTail ? "    " : "│   "), false));
                }
                if (node.numberOfChildren() >= 1) {
                    Nodo<T> obj = node.getChild(node.numberOfChildren() - 1);
                    builder.append(getString(obj, prefix + (isTail ? "    " : "│   "), true));
                }
            }

            return builder.toString();
        }
    }
}