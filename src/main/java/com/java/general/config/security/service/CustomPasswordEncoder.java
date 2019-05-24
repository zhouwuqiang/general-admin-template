package com.java.general.config.security.service;

import com.java.general.utils.MD5Util;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/3/6 15:37
 */
public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return  MD5Util.MD5(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(MD5Util.MD5(charSequence.toString()));
    }
}
