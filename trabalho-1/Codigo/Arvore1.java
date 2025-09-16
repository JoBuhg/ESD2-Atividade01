package Codigo;

public class Arvore1 {
    public static void main(String[] args) {
        ArvoreBinaria arvore1 = new ArvoreBinaria();

        // Monta a árvore original
        arvore1.inserir(40);
        arvore1.inserir(20);
        arvore1.inserir(60);
        arvore1.inserir(10);
        arvore1.inserir(30);
        arvore1.inserir(25);

        // Exibe a árvore em pré-ordem
        System.out.println("Árvore em pré-ordem:");
        arvore1.preOrdem(arvore1.raiz);
        System.out.println();

        } 
    
}
