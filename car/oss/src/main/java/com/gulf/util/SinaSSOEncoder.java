package com.gulf.util;

import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.codec.binary.Base64;

public class SinaSSOEncoder {
    private final boolean i = false;
    private final int g = 8;

    public SinaSSOEncoder() {

    }

    public static String decode(String content) {
        String userName = "";
        try {
            userName = URLDecoder.decode(content, "GBK");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return userName;
    }

    public static String encode(String content) {
        String userName = "";
        try {
            userName = URLEncoder.encode(content);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return userName;
    }

    public static String encodeAccount(String account) {
        String userName = "";
        try {
            userName = Base64.encodeBase64String(URLEncoder.encode(account, "UTF-8").getBytes());
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return userName;
    }

    private static String makeNonce(int len) {
        String x = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String str = "";
        for (int i = 0; i < len; i++) {
            str += x.charAt((int) (Math.ceil(Math.random() * 1000000) % x.length()));
        }
        return str;
    }

    private static String getServerTime() {
        long servertime = new Date().getTime() / 1000;
        return String.valueOf(servertime);
    }

    public String encode(String psw, String servertime, String nonce) {
        String password;
        password = hex_sha1("" + hex_sha1(hex_sha1(psw)) + servertime + nonce);
        return password;
    }

    private String hex_sha1(String j) {
        return h(b(f(j, j.length() * g), j.length() * g));
    }

    private String h(int[] l) {
        String k = i ? "0123456789ABCDEF" : "0123456789abcdef";
        String m = "";
        for (int j = 0; j < l.length * 4; j++) {
            m +=
                    k.charAt((l[j >> 2] >> ((3 - j % 4) * 8 + 4)) & 15) + ""
                            + k.charAt((l[j >> 2] >> ((3 - j % 4) * 8)) & 15);
        }
        return m;
    }

    private int[] b(int[] A, int r) {
        A[r >> 5] |= 128 << (24 - r % 32);
        A[((r + 64 >> 9) << 4) + 15] = r;
        int[] B = new int[80];
        int z = 1732584193;
        int y = -271733879;
        int v = -1732584194;
        int u = 271733878;
        int s = -1009589776;
        for (int o = 0; o < A.length; o += 16) {
            int q = z;
            int p = y;
            int n = v;
            int m = u;
            int k = s;
            for (int l = 0; l < 80; l++) {
                if (l < 16) {
                    B[l] = A[o + l];
                }
                else {
                    B[l] = d(B[l - 3] ^ B[l - 8] ^ B[l - 14] ^ B[l - 16], 1);
                }
                int C = e(e(d(z, 5), a(l, y, v, u)), e(e(s, B[l]), c(l)));
                s = u;
                u = v;
                v = d(y, 30);
                y = z;
                z = C;
            }
            z = e(z, q);
            y = e(y, p);
            v = e(v, n);
            u = e(u, m);
            s = e(s, k);
        }
        return new int[]{z, y, v, u, s};
    }

    private int a(int k, int j, int m, int l) {
        if (k < 20) {
            return (j & m) | ((~j) & l);
        };
        if (k < 40) {
            return j ^ m ^ l;
        };
        if (k < 60) {
            return (j & m) | (j & l) | (m & l);
        };
        return j ^ m ^ l;
    }

    private int c(int j) {
        return (j < 20) ? 1518500249 : (j < 40) ? 1859775393 : (j < 60) ? -1894007588 : -899497514;
    }

    private int e(int j, int m) {
        int l = (j & 65535) + (m & 65535);
        int k = (j >> 16) + (m >> 16) + (l >> 16);
        return (k << 16) | (l & 65535);
    }

    private int d(int j, int k) {
        return (j << k) | (j >>> (32 - k));
    }

    private int[] f(String m, int r) {
        int[] l;
        int j = (1 << this.g) - 1;
        int len = ((r + 64 >> 9) << 4) + 15;
        int k;
        for (k = 0; k < m.length() * g; k += g) {
            len = k >> 5 > len ? k >> 5 : len;
        }
        l = new int[len + 1];
        for (k = 0; k < l.length; k++) {
            l[k] = 0;
        }
        for (k = 0; k < m.length() * g; k += g) {
            l[k >> 5] |= (m.charAt(k / g) & j) << (24 - k % 32);
        }
        return l;
    }

    public static void main(String[] args) {
        // RSAencode(null, null, null, null);
        String s = "%CE%AA%C1%CB%C4%FA%B5%C4%D5%CA%BA%C5%B0%B2%C8%AB%A3%AC%C7%EB%CA%E4%C8%EB%D1%E9%D6%A4%C2%EB";
        String n = "绫致";
        System.out.println(decode(s));
        System.out.println(encode(n));
    }

    public static String RSAencode(String publickey, String servertime, String nonce, String pw) {
        ScriptEngineManager manager = new ScriptEngineManager();

        ScriptEngine engine = manager.getEngineByName("javascript");

        String root = System.getProperty("user.dir");
        System.out.println(root);
        /*
         * 指定加密RSA加密文件
         */
        String jsFileName = root + "/src/main/java/com/gulf/util/sinaRsa.js";
        /*
         * 读取js文件
         */
        FileReader reader;
        String pass = "";
        try {
            reader = new FileReader(jsFileName);

            engine.eval(reader);

            if (engine instanceof Invocable) {
                Invocable invoke = (Invocable) engine;
                // 调用encrypt方法，并传入密码加密
                pass =
                        invoke.invokeFunction("sinaRsa", publickey.trim(), servertime.trim(), nonce.trim(), pw.trim())
                                .toString();
                System.out.println("after encode password = : " + pass);
                return pass;

            }
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return pass;
    }

}
