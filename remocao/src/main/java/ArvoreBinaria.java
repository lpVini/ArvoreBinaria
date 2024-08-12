public class ArvoreBinaria {
    private No raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public void inserir(int valor) {
        No novoNo = new No(valor); // Cria um novo nó com o valor dado
        if(this.raiz == null) { // Se a raiz é nula, o novo nó se torna a raiz
            this.raiz = novoNo;
        } else { // Caso contrário, percorre a árvore para encontrar a posição correta
            No atual = this.raiz;
            No pai = null;
            boolean esquerda = false;
            while(atual != null) { // Enquanto não encontrar uma posição vazia
                pai = atual; // Mantém o nó pai atual para possível ligação
                if(novoNo.getValor() < atual.getValor()) { // Se o valor do novo nó é menor que o atual
                    atual = atual.getEsq(); // Vai para o filho esquerdo
                    esquerda = true;
                } else { // Caso contrário, vai para o filho direito
                    atual = atual.getDir();
                    esquerda = false;
                }
            }
            // Após encontrar a posição correta (atual é nulo)
            if(esquerda) { // Se o último movimento foi para a esquerda
                pai.setEsq(novoNo); // Liga o novo nó como filho esquerdo
            } else { // Se o último movimento foi para a direita
                pai.setDir(novoNo); // Liga o novo nó como filho direito
            }
        }
    }

    public void remover(int valor){
        No atual = this.raiz;// o atual começa na raiz e percorre a árvore em busca do nó que contém o valor a ser removido
        No pai = null;
        boolean FilhoEsquerda = false;

            while (atual != null && atual.getValor() != valor){
                pai = atual;
                if (valor < atual.getValor()) {
                    FilhoEsquerda = true;
                    atual = atual.getEsq();
                } else {
                    FilhoEsquerda = false;
                    atual = atual.getDir();
                }
            }
        if (atual == null){// o no nao foi encontrado
            return;
        }

        //remocao no sem folha
        if (atual.getEsq() == null && atual.getDir() == null){
            if(atual == this.raiz){
                this.raiz = null;
            }
            else if (FilhoEsquerda){
                pai.setEsq(null);
            } else {
                pai.setDir(null);
            }
        }
        //no com 1 filho na direita
        else if(atual.getEsq()==null){
            if(atual == this.raiz){
                this.raiz = atual.getDir();
            } else if(FilhoEsquerda){
                pai.setEsq(atual.getDir());
            }else{
                pai.setDir(atual.getDir());
            }
        }
        //no com 1 filho na esquerda
        else if (atual.getDir() == null) {
            if (atual == this.raiz) {
                this.raiz = atual.getEsq();
            } else if (FilhoEsquerda) {
                pai.setEsq(atual.getEsq());
            } else {
                pai.setDir(atual.getEsq());
            }
        }

        //no com  dois filhos
        else {
            // Encontre o sucessor (que e o menor nó na subárvore direita)
            No sucessor = Sucessor(atual);

            if (atual == this.raiz) {
                this.raiz = sucessor;
            } else if (FilhoEsquerda) {
                pai.setEsq(sucessor);
            } else {
                pai.setDir(sucessor);
            }

            sucessor.setEsq(atual.getEsq());
        }
    }

    private No Sucessor(No no) {
        No paiDoSucessor = no;
        No sucessor = no;
        No atual = no.getDir(); // Começa no filho direito

        while (atual != null) { // Vai para o nó mais à esquerda
            paiDoSucessor = sucessor;
            sucessor = atual;
            atual = atual.getEsq();
        }

        if (sucessor != no.getDir()) {
            paiDoSucessor.setEsq(sucessor.getDir());
            sucessor.setDir(no.getDir());
        }
        return sucessor;
    }


    public No getRaiz() {
        return this.raiz;
    }

    public void preOrdem(No no) {
        if(no == null) { // Se o nó atual é nulo, retorna
            return;
        }
        System.out.println(no.getValor()); // Visita o nó imprime o valor
        preOrdem(no.getEsq()); // Percorre a subárvore esquerda
        preOrdem(no.getDir()); // Percorre a subárvore direita
    }


    public void emOrdem(No no) {
        if(no == null) {
            return;
        }
        emOrdem(no.getEsq()); // Percorre a subárvore esquerda
        System.out.println(no.getValor()); // Visita o nó
        emOrdem(no.getDir()); // Percorre a subárvore direita
    }


    public void posOrdem(No no) {
        if(no == null) {
            return;
        }
        posOrdem(no.getEsq()); // Percorre a subárvore esquerda
        posOrdem(no.getDir()); // Percorre a subárvore direita
        System.out.println(no.getValor()); // Visita o nó
    }

}
