package br.edu.ifsp.dmos5.utils;

import android.util.Log;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    public static String criptografar(String texto) {
        // Cria uma instância do algoritmo de hash MD5
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Converte a string em bytes e calcula o hash
            byte[] hash = md.digest(texto.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            String md5Hash = hexString.toString();
            return md5Hash;
        } catch (NoSuchAlgorithmException e) {
            Log.d("Error", "Erro na criptografia");
        }
        return null;
    }
}
