package CodificacionDeHuffman;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import CodificacionDeHuffman.Huffman.*;

public class File {
	public String leerTxt(String direccion){ //direccion del archivo
        
        String texto = "";
        
        try{
        	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(direccion), "UTF-8"));
            String temp = "";
            String bfRead;
            while((bfRead = br.readLine()) != null){ 
                //haz el ciclo, mientras bfRead tiene datos
                temp = temp + bfRead; //guardado el texto del archivo
            }
            
            texto = temp;
       
            
        }catch(Exception e){ 
            System.err.println("No se encontro archivo");
        }
        
        return texto;
        
    }
}
