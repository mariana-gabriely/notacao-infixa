package estrutura;

import java.util.EmptyStackException;

public class Pilha<T> {

    private static class No<T> {
        private final T dado;
        private final No<T> proximo;

        public No(T dado, No<T> proximo) {
            this.dado = dado;
            this.proximo = proximo;
        }
    }

    private No<T> topo;
    private int tamanho;

    public Pilha() {
        this.topo = null;
        this.tamanho = 0;
    }

    public void push(T elemento) {
        this.topo = new No<>(elemento, this.topo);
        this.tamanho++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T dado = this.topo.dado;
        this.topo = this.topo.proximo;
        this.tamanho--;
        return dado;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return this.topo.dado;
    }

    public boolean isEmpty() {
        return this.topo == null;
    }

    public int size() {
        return this.tamanho;
    }
}
