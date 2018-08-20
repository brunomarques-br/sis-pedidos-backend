package br.com.sis.pedidos.backend.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {
    public static String urlDecoder(String s){
        try {
            return URLDecoder.decode(s, "UTF-8");
        }catch (UnsupportedEncodingException ex){
            return "";
        }
    }
    public static List<Integer> decodeIntToList(String s){
        //Utilizando Lambda (Java 8)
        return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
    }
}
