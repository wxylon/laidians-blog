package com.laidians.core.ws;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

public class AuthHeader {
    private final static String QNAME = "http://www.laidians.com/";
    private String KEY = "AuthenticationHeader";
    private String TOKEN = "Token";
    private String qName;
    private String key;
    private String token;
    private String content;
    private String seed;

    public AuthHeader() {
    }

    public String getTokenValue() {
        if (StringUtils.isNotEmpty(content)) {
            if (StringUtils.isNotEmpty(seed)) {
                byte[] bb = DigestUtils.md5(content + "-" + seed);
                return new String(Base64.encodeBase64(bb));
            } else {
                return content;
            }
        }
        return "";
    }

    public String getqName() {
        if (StringUtils.isEmpty(qName)) {
            qName = QNAME;
        }
        return qName;
    }

    public void setqName(String qName) {
        this.qName = qName;
    }

    public String getKey() {
        if (StringUtils.isEmpty(key)) {
            key = KEY;
        }
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getToken() {
        if (StringUtils.isEmpty(token)) {
            token = TOKEN;
        }
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }
}
