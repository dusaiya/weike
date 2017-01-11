/**
 * ICT NASC
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.ict.nasc.weike.app.data;

import junit.framework.TestCase;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: ImgUrl2UserIdTest.java, v 0.1 2016-3-17 下午3:45:31  Exp $
 */
public class ImgUrl2UserIdTest extends TestCase {

    /**
     * 
     */
    public void testImgUrl2UserId() {
        String url = "http://avatar.zbjimg.com/007/63/71/200x200_avatar_19.jpg!middle";
        String userId = imgUrl2UserId(url);
        System.out.println(userId);
        url = "http://avatar.zbjimg.com/013/14/77/200x200_avatar_12.jpg!middle";
        userId = imgUrl2UserId(url);
        System.out.println(userId);
    }

    /**
     * 
     * @param url
     * @return
     */
    private String imgUrl2UserId(String url) {
        StringBuilder userId = new StringBuilder();
        String[] splitStrs = url.split("/|_|.jpg");
        userId.append(splitStrs[3]);
        userId.append(splitStrs[4]);
        userId.append(splitStrs[5]);
        userId.append(splitStrs[8]);
        return String.valueOf(Integer.parseInt(userId.toString()));
    }

}
