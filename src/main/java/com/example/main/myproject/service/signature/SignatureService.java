package com.example.main.myproject.service.signature;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.security.Signature;

public class SignatureService {
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private Signature signature;

    public SignatureService() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    //getter & setters
    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    // Sign the message using SHA-256 with RSA
    public String sign(String message) throws Exception {
        signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());
        byte[] digitalSignature = signature.sign();
        return Base64.getEncoder().encodeToString(digitalSignature);
    }

    // Verify the RSA signature
    public boolean verify(String message, String signatureStr) throws Exception {
        signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(message.getBytes());
        byte[] digitalSignature = Base64.getDecoder().decode(signatureStr);
        return signature.verify(digitalSignature);
    }

    // Convert PrivateKey to Base64 String
    public String getPrivateKeyAsBase64() {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    // Convert PublicKey to Base64 String
    public String getPublicKeyAsBase64() {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    // Convert Base64 String to PublicKey
    public static PublicKey getPublicKeyFromBase64(String base64PublicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(base64PublicKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(spec);
    }

    // Convert Base64 String to PrivateKey
    public static PrivateKey getPrivateKeyFromBase64(String base64PrivateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(base64PrivateKey);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(spec);
    }

    //test
    public static void main(String[] args) throws Exception {
//        SignatureService service = new SignatureService();
//
//        // Chuyển khóa sang Base64
//        String publicKeyBase64 = service.getPublicKeyAsBase64();
//        String privateKeyBase64 = service.getPrivateKeyAsBase64();
//
//        System.out.println("Public Key (Base64): " + publicKeyBase64);
//        System.out.println("Private Key (Base64): " + privateKeyBase64);
//
//        // Khôi phục khóa từ Base64
//        PublicKey publicKey = SignatureService.getPublicKeyFromBase64(publicKeyBase64);
//        PrivateKey privateKey = SignatureService.getPrivateKeyFromBase64(privateKeyBase64);
//
//        System.out.println("Restored Public Key: " + publicKey);
//        System.out.println("Restored Private Key: " + privateKey);
    }

}
