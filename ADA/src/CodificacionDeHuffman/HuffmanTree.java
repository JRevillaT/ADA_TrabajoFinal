package CodificacionDeHuffman;

/*Clase de árbol abstracto
 */
abstract class HuffmanTree implements Comparable<HuffmanTree> {
    public final int frequency; // Frequência da árvore
    //
    public HuffmanTree(int freq) { 
    	frequency = freq; 
    }
    
    // Compara as frequências - Implementação da Interface Comparable para a ordenação na fila
    public int compareTo(HuffmanTree tree) {
        return frequency - tree.frequency;
    }
}