/**
 * ICT NASC
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.ict.nasc.weike.webcontrol.tools;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: ImgUrl2UserId.java, v 0.1 2016-3-17 下午3:56:05  Exp $
 */
public class ImgUrl2UserId {

    /**
     * 
     * @param url
     * @return userId
     */
    public static String imgUrl2UserId(String url) {
        StringBuilder userId = new StringBuilder();
        String[] splitStrs = url.split("/|_|.jpg");
        userId.append(splitStrs[3]);
        userId.append(splitStrs[4]);
        userId.append(splitStrs[5]);
        userId.append(splitStrs[8]);
        return String.valueOf(Integer.parseInt(userId.toString()));
    }
}
