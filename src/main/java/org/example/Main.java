package org.example;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException {

        // This shouldn't trigger a finding
        long t = System.currentTimeMillis();

        // This shouldn't trigger a a finding either
        Object a = args[0].getClass();

        // This should trigger, that's what the Unsafe JNI rule is for
        leavingTheJvm();

        // This should trigger
        Cipher c1 = Cipher.getInstance("AES/ECB/PKCS5Padding");

        // This should also trigger
        Cipher c2 = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // This should not trigger
        Cipher c3 = Cipher.getInstance("AES/CTR/PKCS5Padding");

    }


    public static native void leavingTheJvm();

}
