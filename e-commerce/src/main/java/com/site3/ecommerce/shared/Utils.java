package com.site3.ecommerce.shared;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	private final Random RANDOM = new SecureRandom();
	
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
    
    public String generateStringId(int length) {
    	
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }
    
    
    public String generateOrderId() 
    {
        // Générer une chaîne de caractères aléatoire pour les caractères alphanumériques
        String randomId = generateStringId(5).toUpperCase();

        // Formater la date actuelle
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String formattedDate = dateFormat.format(new Date());

        // Concaténer la date formatée et l'identifiant aléatoire
        return formattedDate + "-" + randomId;
    }

}
