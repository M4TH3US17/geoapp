package br.com.mail.sender.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailUtils {

    public static String returnsBodyEmail(Object data) {
        log.info("EmailUtils :: montando corpo do email...");
        return data.toString();
    }

}
