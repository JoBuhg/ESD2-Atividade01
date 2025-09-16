# Pseudocódigo Árvore AVL

### Estrutura do nó (nomenclatura do primeiro)

```
NóDes:
    inteiro data
    inteiro height
    NóDes filhoEsq
    NóDes filhoDir
    NóDes pai
```
### Funções utilitárias
```
função calcularAltura(NoDes p):
    se p == NULL então
        retorna 0
    senão
        esquerdaH = 0
        direitaH  = 0
        se p.filhoEsq != NULL então esquerdaH = p.filhoEsq.height
        se p.filhoDir != NULL então direitaH = p.filhoDir.height
        retorna max(esquerdaH, direitaH) + 1

função fatorBalanceamento(NoDes n):
    se n == NULL então retorna 0
    esquerdaH = 0
    direitaH  = 0
    se n.filhoEsq != NULL então esquerdaH = n.filhoEsq.height
    se n.filhoDir != NULL então direitaH  = n.filhoDir.height
    retorna esquerdaH - direitaH
```
### Rotações usando nomenclatura do primeiro

**Rotação simples à direita (LL)**
```
função rotacaoLL(NoDes NoDes): 
    p = NoDes
    tp = p.filhoEsq

    // executar rotação
    p.filhoEsq = tp.filhoDir
    se tp.filhoDir != NULL então tp.filhoDir.pai = p

    tp.filhoDir = p

    // atualizar pais
    tp.pai = p.pai
    p.pai = tp

    // se existir novo filhoEsq da antiga raiz, ajustar pai
    se tp.pai != NULL então
        se tp.pai.filhoEsq == p então tp.pai.filhoEsq = tp
        senão tp.pai.filhoDir = tp

    // atualizar alturas
    p.height = calcularAltura(p)
    tp.height = calcularAltura(tp)

    retorna tp    // nova raiz da subárvore
```
**Rotação simples à esquerda (RR)**
```
função rotacaoRR(NoDes NoDes):
    p = NoDes
    tp = p.filhoDir

    // executar rotação
    p.filhoDir = tp.filhoEsq
    se tp.filhoEsq != NULL então tp.filhoEsq.pai = p

    tp.filhoEsq = p

    // atualizar pais
    tp.pai = p.pai
    p.pai = tp

    se tp.pai != NULL então
        se tp.pai.filhoEsq == p então tp.pai.filhoEsq = tp
        senão tp.pai.filhoDir = tp

    // atualizar alturas
    p.height = calcularAltura(p)
    tp.height = calcularAltura(tp)

    retorna tp
```
**Dupla: esquerda no filhoEsq, depois direita em NoDes**
```
função rotacaoLR(NoDes NoDes):
    p = NoDes
    filho = p.filhoEsq
    neto = filho.filhoDir

    // etapa 1: rotação à esquerda no filho (usando rotacaoRR sobre 'filho')
    filho.filhoDir = neto.filhoEsq
    se neto.filhoEsq != NULL então neto.filhoEsq.pai = filho
    neto.filhoEsq = filho
    neto.pai = filho.pai
    filho.pai = neto

    // reajusta conexão com p
    p.filhoEsq = neto

    // etapa 2: rotação à direita em p (usar rotacaoLL sobre 'p')
    retorna rotacaoLL(p)
```
**Dupla: direita no filhoDir, depois esquerda em NoDes**
```
função rotacaoRL(NoDes NoDes):
    p = NoDes
    filho = p.filhoDir
    neto = filho.filhoEsq

    // etapa 1: rotação à direita no filho (usar rotacaoLL sobre 'filho')
    filho.filhoEsq = neto.filhoDir
    se neto.filhoDir != NULL então neto.filhoDir.pai = filho
    neto.filhoDir = filho
    neto.pai = filho.pai
    filho.pai = neto

    // reajusta conexão com p
    p.filhoDir = neto

    // etapa 2: rotação à esquerda em p (usar rotacaoRR sobre 'p')
    retorna rotacaoRR(p)
```
### Inserção recursiva (mantendo nomes do segundo, mas campos do primeiro)
```
função inserir(NoDes raiz, inteiro valor):
    se raiz == NULL então
        cria novo NoDes n
        n.data = valor
        n.height = 1
        n.filhoEsq = NULL
        n.filhoDir = NULL
        n.pai = NULL
        retorna n

    se valor < raiz.data então
        filho = inserir(raiz.filhoEsq, valor)
        raiz.filhoEsq = filho
        filho.pai = raiz
    senão
        filho = inserir(raiz.filhoDir, valor)
        raiz.filhoDir = filho
        filho.pai = raiz

    // atualizar altura e fator
    raiz.height = calcularAltura(raiz)
    fb = fatorBalanceamento(raiz)

    // casos de rebalanceamento (usando nomenclatura do primeiro)
    se fb == 2 e fatorBalanceamento(raiz.filhoEsq) >= 0 então
        // LL
        raiz = rotacaoLL(raiz)
    senão se fb == 2 e fatorBalanceamento(raiz.filhoEsq) < 0 então
        // LR
        raiz = rotacaoLR(raiz)
    senão se fb == -2 e fatorBalanceamento(raiz.filhoDir) <= 0 então
        // RR
        raiz = rotacaoRR(raiz)
    senão se fb == -2 e fatorBalanceamento(raiz.filhoDir) > 0 então
        // RL
        raiz = rotacaoRL(raiz)

    // após rotação, garantir pai nulo na raiz retornada (se necessário)
    raiz.pai = raiz.pai    // mantém o pai corretamente por quem chamou

    retorna raiz
```
