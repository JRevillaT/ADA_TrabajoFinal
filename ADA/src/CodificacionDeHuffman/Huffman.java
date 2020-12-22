package CodificacionDeHuffman;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;

public class Huffman extends JFrame {
	
	private JButton btnDesDoc,btnDesTxt;
	private JLabel lblEstado;
	private String test;
	private JPanel contentPane;
	private JTextField txtNomArchivo;
	private JTextArea txtTexto;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Huffman frame = new Huffman();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Huffman() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("HuffCod");
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(0, 128, 0));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Bookman Old Style", Font.BOLD, 30));
		lblTitulo.setBounds(0, 0, 461, 58);
		contentPane.add(lblTitulo);
		
		JLabel lblCarga = new JLabel("Coloca aqui la direccion del archivo");
		lblCarga.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblCarga.setBounds(10, 69, 290, 24);
		contentPane.add(lblCarga);
		
		//txtAreaTeoria.setWrapStyleWord(true);
		//txtAreaTeoria.setLineWrap(true);
		
		JButton btnGenerar = new JButton("CARGAR");
		btnGenerar.setFont(new Font("Bookman Old Style", Font.PLAIN, 11));
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File archivo=new File();
				test=archivo.leerTxt(txtTexto.getText());
				System.out.println(test);
				if(test.isEmpty()) {
					btnDesDoc.setEnabled(false);
					btnDesTxt.setEnabled(false);
					lblEstado.setForeground(Color.RED);
					lblEstado.setText("No se cargo el archivo correctamente");
				}else {
					btnDesDoc.setEnabled(true);
					btnDesTxt.setEnabled(true);
					lblEstado.setForeground(Color.GREEN);
					lblEstado.setText("El archivo se cargo de manera exitosa");
				}
				// En este ejemplo se asumirá que tendremos un máximo de 256 caracteres diferentes
		        // Paso 1: desplazarnos por el texto contando los símbolos y armando un vector de frecuencia
			}
		});
		btnGenerar.setBounds(359, 100, 88, 23);
		contentPane.add(btnGenerar);
		
		JLabel lblGenArchivo = new JLabel("Descargar Archivo");
		lblGenArchivo.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblGenArchivo.setBounds(156, 170, 164, 24);
		contentPane.add(lblGenArchivo);
		
		btnDesTxt = new JButton("DESCARGAR");
		btnDesTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] charFreqs = new int[256];
		        for (char c : test.toCharArray())
		            charFreqs[c]++;
		        
		        // Criar a Árvore dos códigos para a Compactação
		        HuffmanTree tree = buildTree(charFreqs);
		        
		        // Resultados das quantidade e o código da Compactação
		        System.out.println("TABELA DE CÓDIGOS");
		        System.out.println("SÍMBOLO\tQUANTIDADE\tHUFFMAN CÓDIGO");
		        printCodes(tree, new StringBuffer());
		        
		        // Compactar o texto
		        String encode = encode(tree,test);
		        // Mostrar o texto Compactado
		        System.out.println("\nTEXTO COMPACTADO");
		        System.out.println(encode); // Tamanho de 40 bits - Economia de 72 bits
		        
		        // Decodificar o texto
		        System.out.println("\n\nTEXTO DECODIFICADO");
		        System.out.println(decode(tree,encode));
				
				JFileChooser fc=new JFileChooser();
				int sel=fc.showSaveDialog(contentPane);

				if(sel==JFileChooser.APPROVE_OPTION) {
					java.io.File fichero=fc.getSelectedFile();
					try (FileWriter fw =new FileWriter(fichero)){
						fw.write("Texto Original\n");
						fw.write(encode+"\n");
						fw.write("Diccionario");
						
						fw.write("Mensaje Codificado\n");
						fw.write(decode(tree,encode));
						
						
			
					} catch (IOException e1) {
						System.out.println("gg ps");
						e1.printStackTrace();
					}
				}
			}
		});
		
		btnDesTxt.setEnabled(false);
		btnDesTxt.setFont(new Font("Bookman Old Style", Font.PLAIN, 11));
		btnDesTxt.setBounds(32, 464, 114, 23);
		contentPane.add(btnDesTxt);
		
		btnDesDoc = new JButton("DESCARGAR");
		btnDesDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] charFreqs = new int[256];
		        for (char c : test.toCharArray())
		            charFreqs[c]++;
		        
		        // Criar a Árvore dos códigos para a Compactação
		        HuffmanTree tree = buildTree(charFreqs);
		        
		        // Resultados das quantidade e o código da Compactação
		        System.out.println("TABELA DE CÓDIGOS");
		        System.out.println("SÍMBOLO\tQUANTIDADE\tHUFFMAN CÓDIGO");
		        printCodes(tree, new StringBuffer());
		        
		        // Compactar o texto
		        String encode = encode(tree,test);
		        // Mostrar o texto Compactado
		        System.out.println("\nTEXTO COMPACTADO");
		        System.out.println(encode); // Tamanho de 40 bits - Economia de 72 bits
		        
		        // Decodificar o texto
		        System.out.println("\n\nTEXTO DECODIFICADO");
		        System.out.println(decode(tree,encode));
				
			}
		});
		btnDesDoc.setEnabled(false);
		btnDesDoc.setFont(new Font("Bookman Old Style", Font.PLAIN, 11));
		btnDesDoc.setBounds(288, 464, 114, 23);
		contentPane.add(btnDesDoc);
		
		JLabel lbltxt = new JLabel(".txt");
		lbltxt.setHorizontalAlignment(SwingConstants.CENTER);
		lbltxt.setBounds(32, 439, 114, 14);
		contentPane.add(lbltxt);
		
		JLabel lbldoc = new JLabel(".doc");
		lbldoc.setHorizontalAlignment(SwingConstants.CENTER);
		lbldoc.setBounds(288, 439, 114, 14);
		contentPane.add(lbldoc);
		
		JLabel lblNombArchivo = new JLabel("Nombre del archivo:");
		lblNombArchivo.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		lblNombArchivo.setBounds(10, 212, 136, 24);
		contentPane.add(lblNombArchivo);
		
		txtNomArchivo = new JTextField();
		txtNomArchivo.setBounds(156, 213, 291, 24);
		contentPane.add(txtNomArchivo);
		txtNomArchivo.setColumns(10);
		
		txtTexto = new JTextArea();
		txtTexto.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		txtTexto.setWrapStyleWord(true);
		txtTexto.setLineWrap(true);
		txtTexto.setBounds(10, 100, 339, 20);
		contentPane.add(txtTexto);
		
		lblEstado = new JLabel("");
		lblEstado.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblEstado.setBounds(10, 135, 339, 24);
		contentPane.add(lblEstado);
		
		JLabel lblEstDescarga = new JLabel("");
		lblEstDescarga.setFont(new Font("Bookman Old Style", Font.PLAIN, 11));
		lblEstDescarga.setBounds(86, 405, 268, 23);
		contentPane.add(lblEstDescarga);
		
		JLabel lblNewLabel = new JLabel("Direccion: ");
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 247, 136, 24);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(156, 248, 291, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblimgWord = new JLabel("");
		
		lblimgWord.setBounds(288, 307, 114, 121);
		contentPane.add(lblimgWord);
		ImageIcon fig=new ImageIcon("img/imgWord.png");
		ImageIcon grap=new ImageIcon(fig.getImage().getScaledInstance(lblimgWord.getWidth(), lblimgWord.getHeight(), Image.SCALE_DEFAULT));
		lblimgWord.setIcon(grap);
		
		JLabel lblimgTxt = new JLabel("");
		lblimgTxt.setBounds(32, 307, 114, 121);
		contentPane.add(lblimgTxt);
		ImageIcon fig2=new ImageIcon("img/imgTxt.png");
		ImageIcon grap2=new ImageIcon(fig2.getImage().getScaledInstance(lblimgTxt.getWidth(), lblimgTxt.getHeight(), Image.SCALE_DEFAULT));
		lblimgTxt.setIcon(grap2);
	}
	
	/* Cree el árbol de codificación: a partir del número de frecuencias de cada letra
	 * Se crea un árbol binario para la compresión de texto
	 * Entrada: charFreqs: matriz con el número de frecuencias para cada letra
	 * Salida: árboles: árbol binario para compresión y decodificación
	 */
    public static HuffmanTree buildTree(int[] charFreqs) {
    	// Crea una cola de prioridad
    	// A Fila será criado pela ordem de frequência da letra no texto
        PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
        //La cola se creará en el orden de frecuencia de la letra en el texto
        for (int i = 0; i < charFreqs.length; i++){
            if (charFreqs[i] > 0)
                trees.offer(new HuffmanLeaf(charFreqs[i], (char)i)); // Insertar los elementos, nodo hoja, en la cola de prioridad
        }
        // Percorre todos os elementos da fila
        // Criando a árvore binária de baixo para cima
        while (trees.size() > 1) {
            // Pega os dois nós com menor frequência
            HuffmanTree a = trees.poll(); // poll - Retorna o próximo nó da Fila ou NULL se não tem mais
            HuffmanTree b = trees.poll(); // poll - Retorna o próximo nó da Fila ou NULL se não tem mais
 
            // Criar os nós da árvore binária
            trees.offer(new HuffmanNode(a, b)); 
        }
        // Retorna o primeiro nó da árvore
        return trees.poll();
    }
 
    /* COMPACTAR a string 
     *     Parâmetros de Entrada: tree - Raiz da Árvore de compactação
     *     						  encode - Texto original 
     *     Parâmetros de Saída: encodeText- Texto Compactado
    */ 
    public static String encode(HuffmanTree tree, String encode){
    	assert tree != null;
    	
    	String encodeText = "";
        for (char c : encode.toCharArray()){
        	encodeText+=(getCodes(tree, new StringBuffer(),c));
        }
    	return encodeText; // Retorna o texto Compactado
    }
    
    
    /* DECODIFICAR a string
     *     Parâmetros de Entrada: tree - Raiz da Árvore de compactação
     *     						  encode - Texto Compactado
     *     Parâmetros de Saída: decodeText- Texto decodificado
    */
    public static String decode(HuffmanTree tree, String encode) {
    	assert tree != null;
    	
    	String decodeText="";
    	HuffmanNode node = (HuffmanNode)tree;
    	for (char code : encode.toCharArray()){
    		if (code == '0'){ // Quando for igual a 0 é o Lado Esquerdo
    		    if (node.left instanceof HuffmanLeaf) { 
    		    	decodeText += ((HuffmanLeaf)node.left).value; // Retorna o valor do nó folha, pelo lado Esquerdo  
	                node = (HuffmanNode)tree; // Retorna para a Raíz da árvore
	    		}else{
	    			node = (HuffmanNode) node.left; // Continua percorrendo a árvore pelo lado Esquerdo 
	    		}
    		}else if (code == '1'){ // Quando for igual a 1 é o Lado Direito
    		    if (node.right instanceof HuffmanLeaf) {
    		    	decodeText += ((HuffmanLeaf)node.right).value; //Retorna o valor do nó folha, pelo lado Direito
	                node = (HuffmanNode)tree; // Retorna para a Raíz da árvore
	    		}else{
	    			node = (HuffmanNode) node.right; // Continua percorrendo a árvore pelo lado Direito
	    		}
    		}
    	} // End for
    	return decodeText; // Retorna o texto Decodificado
    }    
    
    /* 
     * Método para percorrer a Árvore e mostra a tabela de compactação
     *     Parâmetros de Entrada: tree - Raiz da Árvore de compactação
     *     						  prefix - texto codificado com 0 e/ou 1
     */
    public static void printCodes(HuffmanTree tree, StringBuffer prefix) {
        assert tree != null;
        
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
            
            // Imprime na tela a Lista
            System.out.println(leaf.value + "\t" + leaf.frequency + "\t\t" + prefix);
 
        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode)tree;
 
            // traverse left
            prefix.append('0');
            printCodes(node.left, prefix);
            prefix.deleteCharAt(prefix.length()-1);
 
            // traverse right
            prefix.append('1');
            printCodes(node.right, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }
    
    /* 
     * Método para retornar o código compactado de uma letra (w)
     *     Parâmetros de Entrada: tree - Raiz da Árvore de compactação
     *     						  prefix - texto codificado com 0 e/ou 1
     *     						  w - Letra
     *     Parâmetros de Saída: prefix- Letra codificada
     */
    public static String getCodes(HuffmanTree tree, StringBuffer prefix, char w) {
        assert tree != null;
        
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
            
            // Retorna o texto compactado da letra
            if (leaf.value == w ){
            	return prefix.toString();
            }
            
        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode)tree;
 
            // Se Desplaza a la izquierda
            prefix.append('0');
            String left = getCodes(node.left, prefix, w);
            prefix.deleteCharAt(prefix.length()-1);
 
            // Se Desplaza a la derexha
            prefix.append('1');
            String right = getCodes(node.right, prefix,w);
            prefix.deleteCharAt(prefix.length()-1);
            
            if (left==null) return right; else return left;
        }
		return null;
    }
}
