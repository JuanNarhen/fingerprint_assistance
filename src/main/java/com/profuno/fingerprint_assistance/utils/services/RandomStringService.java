package com.profuno.fingerprint_assistance.utils.services;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RandomStringService {
    public static String generateRandom(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String generateRandom(int size){
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, size);
    }
}
