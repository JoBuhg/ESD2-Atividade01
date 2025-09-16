package Codigo;

public class ArvoreBinaria {
    No raiz;

    // Inserção 
    No inserir(No no, int valor) {
        if (no == null) {
            System.out.println(" Inserindo nó " + valor);
            return new No(valor);
        }

        if (valor < no.valor) {
            System.out.println(" Indo para a esquerda de " + no.valor);
            no.filhoEsq = inserir(no.filhoEsq, valor);
        } else if (valor > no.valor) {
            System.out.println(" Indo para a direita de " + no.valor);
            no.filhoDir = inserir(no.filhoDir, valor);
        } else {
            System.out.println(" Valor duplicado ignorado: " + valor);
            return no;
        }

        // Atualizar altura e FB
        no.altura = 1 + Math.max(altura(no.filhoEsq), altura(no.filhoDir));
        no.fb = fatorBalanceamento(no);

        System.out.println("Nó " + no.valor + " atualizado | Altura: " + no.altura + " | FB: " + no.fb);

        // Balancear
        return balancear(no, valor);
    }

    int altura(No no) {
        return (no == null) ? 0 : no.altura;
    }

    int fatorBalanceamento(No no) {
        if (no == null) return 0;
        return altura(no.filhoEsq) - altura(no.filhoDir);
    }

   No balancear(No no, int valor) {
    // Caso Esquerda-Esquerda
    if (no.fb > 1 && valor < no.filhoEsq.valor) {
        System.out.println(" Desbalanceio em " + no.valor + " (Esquerda-Esquerda) → Rotação à direita");
        return rotacaoDireita(no); // retorna já balanceado
    }

    // Caso Direita-Direita
    if (no.fb < -1 && valor > no.filhoDir.valor) {
        System.out.println(" Desbalanceio em " + no.valor + " (Direita-Direita) → Rotação à esquerda");
        return rotacaoEsquerda(no);
    }

    // Caso Esquerda-Direita
    if (no.fb > 1 && valor > no.filhoEsq.valor) {
        System.out.println(" Desbalanceio em " + no.valor + " (Esquerda-Direita) → Rotação dupla (Esquerda + Direita)");
        no.filhoEsq = rotacaoEsquerda(no.filhoEsq);
        return rotacaoDireita(no);
    }

    // Caso Direita-Esquerda
    if (no.fb < -1 && valor < no.filhoDir.valor) {
        System.out.println(" Desbalanceio em " + no.valor + " (Direita-Esquerda) → Rotação dupla (Direita + Esquerda)");
        no.filhoDir = rotacaoDireita(no.filhoDir);
        return rotacaoEsquerda(no);
    }

    // Se não está desbalanceado, apenas retorna
    return no;
}

    No rotacaoDireita(No y) {
        System.out.println("Rotação à direita em " + y.valor);
        No x = y.filhoEsq;
        No T2 = x.filhoDir;

        x.filhoDir = y;
        y.filhoEsq = T2;

        y.altura = 1 + Math.max(altura(y.filhoEsq), altura(y.filhoDir));
        x.altura = 1 + Math.max(altura(x.filhoEsq), altura(x.filhoDir));

        y.fb = fatorBalanceamento(y);
        x.fb = fatorBalanceamento(x);

        return x;
    }

    No rotacaoEsquerda(No x) {
        System.out.println(" Rotação à esquerda em " + x.valor);
        No y = x.filhoDir;
        No T2 = y.filhoEsq;

        y.filhoEsq = x;
        x.filhoDir = T2;

        x.altura = 1 + Math.max(altura(x.filhoEsq), altura(x.filhoDir));
        y.altura = 1 + Math.max(altura(y.filhoEsq), altura(y.filhoDir));

        x.fb = fatorBalanceamento(x);
        y.fb = fatorBalanceamento(y);

        return y;
    }

    public void inserir(int valor) {
        raiz = inserir(raiz, valor);
        System.out.println(" Raiz atual: " + raiz.valor + "\n");
    }

    public void preOrdem(No no) {
        if (no != null) {
            System.out.println("Valor: " + no.valor + " | FB: " + no.fb);
            preOrdem(no.filhoEsq);
            preOrdem(no.filhoDir);
        }
    }
}
