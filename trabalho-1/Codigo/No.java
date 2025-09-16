    package Codigo;

    public class No {
        int valor;
        No filhoEsq, filhoDir, pai;
        int altura;
        int fb;

        No(int valor) {
            this.valor = valor;
            this.altura = 1;
            this.fb = 0;
        }
}
