package com.java.general.utils.text;

import java.nio.charset.Charset;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/22 10:54
 */
public interface CharsetConstant {

    /**
     * ISO-8859-1
     */
    String ISO_8859_1 = "ISO-8859-1";

    /**
     * UTF-8
     */
    String UTF_8 = "UTF-8";

    /**
     * GBK
     */
    String GBK = "GBK";


    /**
     * ISO-8859-1
     */
    Charset CHARSET_ISO_8859_1 = Charset.forName(ISO_8859_1);

    /**
     * UTF-8
     */
    Charset CHARSET_UTF_8 = Charset.forName(UTF_8);

    /**
     * GBK
     */
    Charset CHARSET_GBK = Charset.forName(GBK);

}
