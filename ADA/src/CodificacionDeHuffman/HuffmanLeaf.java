package CodificacionDeHuffman;

/*
 Clase de nodo de hoja de árbol
 */
class HuffmanLeaf extends HuffmanTree {
    public final char value; // La letra se asigna a un nodo hoja
 
    public HuffmanLeaf(int freq, char val) {
        super(freq);
        value = val;
    }
}