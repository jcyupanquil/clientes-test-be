package com.css.springboot.backend.apirest.auth;

public class JwtConfig {

    public static final String SECRET_KEY = "this.is.the.most.secure.password";

    public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIBPAIBAAJBAL6fHIR9Ji5gsovlHfqVYHe1AptJ8NyIq6Xwqo0Plcf2APegX4MD\n" +
            "X9jkrMbSBP95gAwT82KADjx05hNY+lo5H9sCAwEAAQJBAKltLqGrwqXyGIAzEuRI\n" +
            "pMeBQWHY5JZ/e2EwecaLFav1uCdx5BshjznEPGvVL7MPbN7dDmT5GryEUon6XjZ6\n" +
            "TZECIQDqH49U6v4JV4+NO0zrrxVItetqq803o3yLSy3rUjbqNwIhANBu80pMDY4B\n" +
            "ntvzsDkj9GBW2CEOfgfxz8/XgJKnP9V9AiEAsMIJDbyo9Nalq6Q0y8+QyyeFyDBT\n" +
            "LImCswucbIjSqvsCIHQG70PSplEAse/67YRBcY4XieSM7rd5cpdefWIl3DD5AiEA\n" +
            "ubZALwzdx4FkU0e42cgpW/8oIoKdbIb0tMYtOxZSRSc=\n" +
            "-----END RSA PRIVATE KEY-----";

    public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\n" +
            "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAL6fHIR9Ji5gsovlHfqVYHe1AptJ8NyI\n" +
            "q6Xwqo0Plcf2APegX4MDX9jkrMbSBP95gAwT82KADjx05hNY+lo5H9sCAwEAAQ==\n" +
            "-----END PUBLIC KEY-----";

}
