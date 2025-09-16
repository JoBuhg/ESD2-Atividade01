package Codigo;

public class Arvore2 {
    public static void main(String[] args) {

        ArvoreBinaria arvore2 = new ArvoreBinaria();

        // Monta a árvore original
        arvore2.inserir(60);
        arvore2.inserir(40);
        arvore2.inserir(80);
        arvore2.inserir(35);
        arvore2.inserir(50);
        arvore2.inserir(90);
        arvore2.inserir(20);
        arvore2.inserir(38);
        arvore2.inserir(37);

        // Exibe a árvore em pré-ordem
        System.out.println("Árvore em pré-ordem:");
        arvore2.preOrdem(arvore2.raiz);
        System.out.println();
    }
}
