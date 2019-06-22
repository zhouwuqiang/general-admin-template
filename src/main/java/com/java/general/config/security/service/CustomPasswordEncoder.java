package com.java.general.config.security.service;


import com.java.general.utils.Md5Utils;
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
        return  Md5Utils.hash(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(Md5Utils.hash(charSequence.toString()));
    }
}
