package arvores;

public class ArvoreB implements Arvore {   
    private int T;

    // Nodo da árvore
    public class Nodo {          
        int n; // Número de chaves
        int chave[] = new int[2 * T - 1]; // Chaves do Nodo
        Nodo filho[] = new Nodo[2 * T]; // Nodos filhos
        boolean folha = true; // Nodo é ou não folha
 
        public int encontrar(int k) {                        
            for (int i = 0; i < this.n; i++) {	             
                if (this.chave[i] == k) {            
                    return i;
                }	        
            }                        
            return -1;
        };       
    }
 
    // Construtor ArvoreB
    public ArvoreB(int t) {
        T = t;
        raiz = new Nodo();
        raiz.n = 0;
        raiz.folha = true;
    }

    private Nodo raiz; // Raiz da árvore
    
    // Função para verificar se Nodo contêm chave
    private Nodo contem(Nodo x, int chave) {
        int i = 0;
        if (x == null)
            return x;
        for (i = 0; i < x.n; i++) {
            if (chave < x.chave[i]) {
                break;
            }
            if (chave == x.chave[i]) {
                return x;
            }
        }
        if (x.folha) {
            return null;
        } else {
            return contem(x.filho[i], chave);    
        }
    }

    // Função de divisão de página
    private void dividir(Nodo x, int pos, Nodo y) {             
        Nodo z = new Nodo();
        z.folha = y.folha;
        z.n = T - 1;

        for (int j = 0; j < T - 1; j++) {	        
            z.chave[j] = y.chave[j + T];
        } 
        if (!y.folha) {	        
            for (int j = 0; j < T; j++) {	            
                z.filho[j] = y.filho[j + T];
            } 
        }          
        y.n = T - 1;
    
        for (int j = x.n; j >= pos + 1; j--) {	        
            x.filho[j + 1] = x.filho[j];
        } 
        x.filho[pos + 1] = z;   

        for (int j = x.n - 1; j >= pos; j--) {	        
            x.chave[j + 1] = x.chave[j];
        }
        x.chave[pos] = y.chave[T - 1];
        x.n = x.n + 1;
    } 
 
     // Função de inserção de chave
    public void inserir(final int chave) {             
        Nodo r = raiz;

        if (r.n == 2 * T - 1) {	    
            Nodo s = new Nodo();
            raiz = s;
            s.folha = false;
            s.n = 0;
            s.filho[0] = r;
            dividir (s, 0, r);
            inserirNodo(s, chave);
        } else {	        
            inserirNodo(r, chave);
        }       
    } 
    // Função de inserção de Nodo
    final private void inserirNodo(Nodo x, int k) {    
 
        if (x.folha) {	        
            int i = 0;
	
	        for (i = x.n - 1; i >= 0 && k < x.chave[i]; i--) {	            
                x.chave[i + 1] = x.chave[i];
            }	    
            x.chave[i + 1] = k;
            x.n = x.n + 1;
        } else {	    
            int i = 0;

            for (i = x.n - 1; i >= 0 && k < x.chave[i]; i--) {	 

            };
            i++;
            
            Nodo tmp = x.filho[i];
            if (tmp.n == 2 * T - 1) {
                dividir(x, i, tmp);
                if (k > x.chave[i]) {	        	
                    i++;
                }	      
            }	    
            inserirNodo(x.filho[i], k);
        }   
    }

    // Função para remoção de chave
    public void remover(int chave) {
        Nodo x = contem(raiz, chave);
        if (x == null) {
            return;
        }
        remover(raiz, chave);
    }
    // Função para remoção de Nodo
    private void remover(Nodo x, int chave) {
        int pos = x.encontrar(chave);
        if (pos != -1) {
            if (x.folha) {
                int i = 0;
                for (i = 0; i < x.n && x.chave[i] != chave; i++) {

                };
                for (; i < x.n; i++) {
                    if (i != 2 * T - 2) {
                        x.chave[i] = x.chave[i + 1];
                    }
                }
                x.n--;
                return;
            }
            if (!x.folha) {
                Nodo pred = x.filho[pos];
                int predChave = 0;
                if (pred.n >= T) {
                    for (;;) {
                        if (pred.folha) {
                            System.out.println (pred.n);
                            predChave = pred.chave[pred.n - 1];
                            break;
                        } else {
                            pred = pred.filho[pred.n];
                        }
                    }
                    remover(pred, predChave);
                    x.chave[pos] = predChave;
                    return;
                }
                
                Nodo nextNodo = x.filho[pos + 1];
                if (nextNodo.n >= T) {  
                    int nextChave = nextNodo.chave[0];
                    if (!nextNodo.folha) {
                        nextNodo = nextNodo.filho[0];
                        for (;;) {
                            if (nextNodo.folha) {
                                nextChave = nextNodo.chave[nextNodo.n - 1];
                                break;
                            } else {
                                nextNodo = nextNodo.filho[nextNodo.n];
                            }
                        }
                    }
                    remover(nextNodo, nextChave);
                    x.chave[pos] = nextChave;
                    return;
                }
                int temp = pred.n + 1;
                pred.chave[pred.n++] = x.chave[pos];
                for (int i = 0, j = pred.n; i < nextNodo.n; i++) {
                    pred.chave[j++] = nextNodo.chave[i];
                    pred.n++;
                } 
                for (int i = 0; i < nextNodo.n + 1; i++) {
                    pred.filho[temp++] = nextNodo.filho[i];
                } 
                x.filho[pos] = pred;
                for (int i = pos; i < x.n; i++) {
                    if (i != 2 * T - 2) {
                        x.chave[i] = x.chave[i + 1];
                    }
                }
                for (int i = pos + 1; i < x.n + 1; i++) {
                    if (i != 2 * T - 1) {
                        x.filho[i] = x.filho[i + 1];
                    }
                }
                x.n--;
                if (x.n == 0) {
                    if (x == raiz) {
                        raiz = x.filho[0];
                    }
                    x = x.filho[0];
                }
                remover(pred, chave);
                return;
            }
        } else {
            for (pos = 0; pos < x.n; pos++) {
                if (x.chave[pos] > chave) {
                    break;
                }
            }
            Nodo tmp = x.filho[pos];
    
            if (tmp.n >= T) {
                remover(tmp, chave);
                return;
            }
            if (true) {
                Nodo nb = null;
                int devider = -1;
                if (pos != x.n && x.filho[pos + 1].n >= T) {
                    devider = x.chave[pos];
                    nb = x.filho[pos + 1];
                    x.chave[pos] = nb.chave[0];
                    tmp.chave[tmp.n++] = devider;
                    tmp.filho[tmp.n] = nb.filho[0];
                
                    for (int i = 1; i < nb.n; i++) {
                        nb.chave[i - 1] = nb.chave[i];
                    }
                    for (int i = 1; i <= nb.n; i++) {
                        nb.filho[i - 1] = nb.filho[i];
                    } 
                    nb.n--;
                    remover(tmp, chave);
                    return;
                } else if (pos != 0 && x.filho[pos - 1].n >= T) {
                    devider = x.chave[pos - 1];
                    nb = x.filho[pos - 1];
                    x.chave[pos - 1] = nb.chave[nb.n - 1];
                    Nodo filho = nb.filho[nb.n];
                    nb.n--;
                    
                    for (int i = tmp.n; i > 0; i--) {
                        tmp.chave[i] = tmp.chave[i - 1];
                    } 
                    tmp.chave[0] = devider;
		
                    for (int i = tmp.n + 1; i > 0; i--) {
                        tmp.filho[i] = tmp.filho[i - 1];
                    } 
                    tmp.filho[0] = filho;
                    tmp.n++;
                    remover(tmp, chave);
                    return;
                } else {
                    Nodo lt = null;
                    Nodo rt = null;
                    //boolean last = false;
                    if (pos != x.n) {
                        devider = x.chave[pos];
                        lt = x.filho[pos];
                        rt = x.filho[pos + 1];
                    } else {
                        devider = x.chave[pos - 1];
                        rt = x.filho[pos];
                        lt = x.filho[pos - 1];
                        //last = true;
                        pos--;      
                    }
                    for (int i = pos; i < x.n - 1; i++) {
                        x.chave[i] = x.chave[i + 1];
                    }
                    for (int i = pos + 1; i < x.n; i++) {
                        x.filho[i] = x.filho[i + 1];
                    } 
                    x.n--;
                    lt.chave[lt.n++] = devider;

                    for (int i = 0, j = lt.n; i < rt.n + 1; i++, j++) {
                        if (i < rt.n) {
                            lt.chave[j] = rt.chave[i];
                        }
                        lt.filho[j] = rt.filho[i];
                    }
                    lt.n += rt.n;
                    if (x.n == 0) {
                        if (x == raiz) {
                            raiz = x.filho[0];
                        }
                        x = x.filho[0];
                    }
                    remover(lt, chave);
                    return;
                }
            }
        }
    }

    // Função para buscar chave
    public boolean buscar(int k) {
        if (this.contem(raiz, k) != null) {
            return true;
        } else {
            return false;
        }
    }
    
    // Função para mostrar a árvore
    public void mostrar() {
        mostrar(raiz, 0);
    }  
    // Função para mostrar a árvore pelo Nodo
    private void mostrar(Nodo x, int nivel) {      
        assert (x == null);
        System.out.print("[");
        for (int i = 0; i < x.n; i++) {	    
            System.out.print(x.chave[i]);
            if ((i + 1) < x.n) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
        if (!x.folha) {
            System.out.print(":");
            System.out.println();
            for (int i = 0; i < nivel; i++) {
                System.out.print("  ");
            }
            nivel++;
            System.out.println("{");
            for (int i = 0; i < x.n + 1; i++) {
                for (int j = 0; j < nivel; j++) {
                    System.out.print("  ");
                }
                mostrar(x.filho[i], nivel);
            }
            for (int i = 0; i < (nivel - 1); i++) {
                System.out.print("  ");
            }
            System.out.print("}");
        }
        System.out.println();
    } 
}