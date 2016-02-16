/**
 * ICT NASC
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.ict.nasc.weike.app.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import junit.framework.TestCase;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: StringSplitTest.java, v 0.1 2016-1-22 上午9:19:59  Exp $
 */
public class StringSplitTest extends TestCase {

    public void testSplit() {
        List<String> strList = strList();
        dataMsg(strList);

    }

    /**
     * 
     * @return
     */
    private List<String> strList() {
        List<String> strList = new ArrayList<String>();
        strList.add("Citations\t6");
        strList.add("Author\t4");
        strList.add("Name\tDASH, UN\tDAS, BB\tBISWAL, UK\tPANDA, T");
        strList.add("4\t1987\t0.509551\t0.163483\t0.163483\t0.163483");
        strList.add("6\t1996\t0.482749\t0.172417\t0.172417\t0.172417");
        strList.add("6\t2002\t0.507319\t0.164227\t0.164227\t0.164227");
        strList.add("total  0.507319    0.164227    0.164227    0.164227");
        return strList;
    }

    /**
     * 
     * @param context
     * @param strList
     * @throws NumberFormatException
     */
    private void dataMsg(List<String> strList) throws NumberFormatException {
        int authorCnt = 0;
        List<String> nameList = new ArrayList<String>();
        List<JSONArray> yearDataList = new ArrayList<JSONArray>();
        for (String str : strList) {
            if (str.startsWith("Citations")) {
                continue;
            } else if (str.startsWith("Author")) {
                String[] countStr = str.split("\t");
                //authorCnt = Integer.parseInt(countStr);
                continue;
            } else if (str.startsWith("Name")||str.startsWith("total")) {
                continue;
            }  else {
                //yearDataList.add(str);
                String[] yearData = str.split("\t");

                List<JSONObject> linedata = new ArrayList<JSONObject>();
                for (String data : yearData) {
                    JSONObject ob = new JSONObject();
                    ob.put("a", data);
                    linedata.add(ob);
                }
                JSONArray dataList = new JSONArray(linedata);
                yearDataList.add(dataList);
            }
        }
        JSONArray dataList = new JSONArray(yearDataList);
        System.out.println(dataList.toString());
    }
}
