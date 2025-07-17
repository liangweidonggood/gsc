package com.lwd.gsc.auth.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

/**
 * JWT工具类，使用RSA算法进行签名和验证
 * @author lwd
 */
@Slf4j
public class JwtRsaUtil {

    /**
     * 从Base64字符串还原私钥
     */
    public static PrivateKey getPrivateKeyFromBase64(String privateKeyBase64)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 从Base64字符串还原公钥
     */
    public static PublicKey getPublicKeyFromBase64(String publicKeyBase64)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 使用RSA私钥生成JWT
     * @param privateKey RSA私钥
     * @param subject JWT主题（通常是用户ID）
     * @param claims 自定义声明
     * @param expirationSeconds 过期时间（秒）
     * @return JWT字符串
     */
    public static String generateJwt(PrivateKey privateKey, String subject,
                                     Map<String, Object> claims, long expirationSeconds) {

        Instant now = Instant.now();
        Instant expiration = now.plusSeconds(expirationSeconds);
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiration))
                .signWith(privateKey, Jwts.SIG.RS256)
                .compact();
    }
    /**
     * 解析token
     * 如果抛异常就说明token不正常，不需要解析出来再去验证了
     * @param token token
     * @param publicKey publickey
     * @return Jws<Claims>
     */
    public static Jws<Claims> parseToken(String token,PublicKey publicKey){
        return Jwts.parser().verifyWith(publicKey).build().parseSignedClaims(token);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        System.out.println("默认字符集：" + java.nio.charset.Charset.defaultCharset());
        System.out.println("控制台编码：" + System.getProperty("console.encoding"));
        //生成一个密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 获取公钥和私钥
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        // Base64编码（转换为字符串，方便存储）
        String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String privateKeyBase64 = Base64.getEncoder().encodeToString(privateKey.getEncoded());

        // 输出结果（后续可复制到Nacos配置）
        System.out.println("公钥（Base64）：" + publicKeyBase64);
        String pubBase64="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAz4IZ+6NrrwLiO8gpXgedieypDX+YvyP8BXSTzAdw5h0OTyNB62cuLncWcfDgGqp3LAAmTOKp4cZceK+VKray5ntSVPtrm49ijY3de1sAEwMWbqOf+p4Y90ESbL2EdKlgQW6MpEU0Th4rqL0vyJRs63hfjku6RnyZgBcPglcn6xSzYrIqqxc3rVWl1nBFMixSZhygOaZACWJFqTUvPJOTh82cUNMwn5cygUGuM9/iIZsmo9LR4y8JsPq9wW4eqt2GCPc4bo8VSMcPzPk1SLgKQMgDum1oO0p1FoXBg+hzGfdsTELtk6UG58AWy1OCdNTeGBl2rGsArgFE8shSsAYVmQIDAQAB";
        System.out.println("私钥（Base64）：" + privateKeyBase64);
        String priBase64="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDPghn7o2uvAuI7yCleB52J7KkNf5i/I/wFdJPMB3DmHQ5PI0HrZy4udxZx8OAaqncsACZM4qnhxlx4r5UqtrLme1JU+2ubj2KNjd17WwATAxZuo5/6nhj3QRJsvYR0qWBBboykRTROHiuovS/IlGzreF+OS7pGfJmAFw+CVyfrFLNisiqrFzetVaXWcEUyLFJmHKA5pkAJYkWpNS88k5OHzZxQ0zCflzKBQa4z3+Ihmyaj0tHjLwmw+r3Bbh6q3YYI9zhujxVIxw/M+TVIuApAyAO6bWg7SnUWhcGD6HMZ92xMQu2TpQbnwBbLU4J01N4YGXasawCuAUTyyFKwBhWZAgMBAAECggEAAaEJWC7jwQd6RDLhlEHmDDel6hu21MVRDK/qGx9x1PSkcJ2bvl/cfnPj4rgb4km1TsyfgFoFMGcQwcfLGX8+r02Hi3A/24RIJ0lWVEoWGZAcJHk3X83O71386YqhWBMeVNUr2HxMmVJmUFhP4Di+FCkpQuI4T6ZZxCYqMoFBqHhJA85epiWEAlp/qnHzgG+pxE3Bz4zT//eHGbWEghnsPEy9qKFjCyF7LjWDnqv7Gh21cjJWYsuycZjXLlIXo/+Jw35ZzH7axaec44pCL5LVdk5C6HQ4zOhNBsGDWDeEhhCuy5MvqCbLBcTi0j0bix4QgtZ6/ZdzEl6Zb47XioPl+wKBgQDmwBasIbs1r8JZ7EMLdPB8GR95BWNBdY2DPPtBh+yUUvF6B5Fzg1H19s0U9ymjIPgRyhIOCw3L2jlidLgviZ6Von04ysbWErgqd2EO0fgslKQ7YLhbCFx/j5DwKxrkh3EzuMAiwiFV9YOOtZAH5psVfwit2r4LMn9lwQcscbAlQwKBgQDmNvDZlXfLUqfvpLUWt239oBI+MDZIEjNDrFqo+1d3gf68WSgG/ahYzsZJCSnLhJZcy+3BCpeLUEUTElvkosYl35P5vtLPwDD1LjaDl9P/73MJ7C1WRwfdnZN+I2H2RksUX8XZVCbOxQDHNuMu0BmgV+NXlf85vVmqX6Q9s0t98wKBgQCHZ2amgXCysz1PuSUqfudzWuauBGxXCB0PSuSBcrpv2zAaFIDUkNWyiK2VROgo8nq/LZ1kojXpTlfoH2fJdW8ajWv/vHXrPrbVLJJGuQ1xU5P/x+TGfmSQ8fTyCclRGu1hlWMhwlr/bv9Q6dfn2vlcJtw38KkeNvatBhUojln3swKBgDLZt+GXzlou68FXVFisM7NuBBmQdKz+uCweifgBbJSNNkNMPjctGF5lbg774yt9IellciLNwCOZm2hXCc061yHOfNURfWc+QJInnwr6Im3J6lMqw+jmz6n4AHhAQIcxh6v8a6tRlzDc+wsF6LCSbtaoEyNpknKijIfBkHnAfNBZAoGAfyFijI0Zv6mG5zcwOnaSV/sw4HLgXjwz27zg+5MpUox5C9clS5dgs5rRl5/xe/EnVPOMos6nd/RIyaMtTaCvjvgUCJkA66DfS1Jy8xzlm/XRq0OqbmBfQ5l72Umen3AA4omIOmkkEpFO1nvQj6jXMQhd6SNVVcm0jSo4EN3mjv8=";

        PrivateKey privateKeyFromBase64 = JwtRsaUtil.getPrivateKeyFromBase64(priBase64);
        PublicKey publicKeyFromBase64 = JwtRsaUtil.getPublicKeyFromBase64(pubBase64);

        //生成一个jwt-token
        String jwtToken = JwtRsaUtil.generateJwt(privateKeyFromBase64, "admin", null, 3000);
        System.out.println(jwtToken);
        String token1="eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc1MjcyNDU3MCwiZXhwIjoxNzUyNzI3NTcwfQ.rbwQorbJITeaHFF90tkdCDoD8i1j0RQg-IVv5tXsubfFHx0yP73jBcrqHsSTXZ2zagQwYsh0K2Jhre-QpsVScP7TpVkiXPYxSi7pjKq2jpSrhtwVE8Qu6K5oQdmgfW0r7zENj7klw3mqlgE7QNI5iYf5PL9g4V2dkx3SZ2eoBc3flgSZpGYiLL4ZL4q3uz-As8ImwDc_9z5aCi0KcSnByiZvQwlZo48bCpwZ_ttuthI9TUeKhn6alf9MISnrjnN-D816IJrs_qt9Quf_sROFhZuzLCk81N6YjiNGazEb1YTlvtYe46G9GpSwnuwn7HlzOacQVmKLm2OIWNh1_4vKuQ";
        //解密token
        Jws<Claims> claimsJws = JwtRsaUtil.parseToken(jwtToken, publicKeyFromBase64);
        System.out.println(claimsJws);
        //header={alg=RS256},payload={sub=admin, iat=1752731809, exp=1752734809},signature=YK7jqYjN-lggU9BCkTN0qt_1BSqZn6zw5oORwX5nuuL80no61ttoJl2RS48uKfLHqUoGSNZieBUXL5GHoypmxgrtEWtzZELpm-vHK3qp3IAkwK2N-CAh2v18AzwYUtcr1uqS54u746WrcJhyvPeFKoAGfolzuMOdyeqAf9wNOjrXY8h8z9te9atd2lFtONfeqX4MkfHKkuRJrJT3MUJk17Cx8ZGbZqHYB1Sc1e3iI0vo5QWicWzHIPUDnGMaXi_NhpEcb14pbtkFDsbVQX_SNy4aUFJ5smGUIot6sZJnogh58UY1JEixJxRYWh3M-HycJF_di5YtKq0Czb6ELqfsvg
    }
}
