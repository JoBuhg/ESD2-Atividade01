package Codigo;

public class Arvore3 {
    public static void main(String[] args) {
        
                ArvoreBinaria arvore3 = new ArvoreBinaria();

        // Monta a árvore original
        arvore3.inserir(30);
        arvore3.inserir(20);
        arvore3.inserir(10);
        arvore3.inserir(25);
        arvore3.inserir(40);
        arvore3.inserir(50);
        arvore3.inserir(5);
        arvore3.inserir(35);
        arvore3.inserir(45);

        // Exibe a árvore em pré-ordem
        System.out.println("Árvore em pré-ordem:");
        arvore3.preOrdem(arvore3.raiz);
        System.out.println();
    }
}
