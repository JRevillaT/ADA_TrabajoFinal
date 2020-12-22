package CodificacionDeHuffman;

/*Clase de �rbol abstracto
 */
abstract class HuffmanTree implements Comparable<HuffmanTree> {
    public final int frequency; // Frequ�ncia da �rvore
    //
    public HuffmanTree(int freq) { 
    	frequency = freq; 
    }
    
    // Compara as frequ�ncias - Implementa��o da Interface Comparable para a ordena��o na fila
    public int compareTo(HuffmanTree tree) {
        return frequency - tree.frequency;
    }
}