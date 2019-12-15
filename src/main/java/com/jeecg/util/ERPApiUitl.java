package com.jeecg.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ERPApiUitl {
    public static String POST_K3CloudURL = "http://62.234.96.240/K3Cloud/";
    private static String dbId = "5de23d43b5072c";
    private static String uid = "Administrator";
    private static String pwd = "jmeichina-2018";
    private static int lang = 2052;
    // Cookie ֵ
    private static String CookieVal = null;

    private static Map map = new HashMap();
    static {
        map.put("Save",
                "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Save.common.kdsvc");
        map.put("View",
                "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.View.common.kdsvc");
        map.put("Submit",
                "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Submit.common.kdsvc");
        map.put("Audit",
                "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Audit.common.kdsvc");
        map.put("UnAudit",
                "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.UnAudit.common.kdsvc");
        map.put("StatusConvert",
                "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.StatusConvert.common.kdsvc");
        map.put("list",
                "Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.ExecuteBillQuery.common.kdsvc");
    }

    // HttpURLConnection
    private static HttpURLConnection initUrlConn(String url, JSONArray paras)
            throws Exception {
        URL postUrl = new URL(POST_K3CloudURL.concat(url));
        HttpURLConnection connection = (HttpURLConnection) postUrl
                .openConnection();
        if (CookieVal != null) {
            connection.setRequestProperty("Cookie", CookieVal);
        }
        if (!connection.getDoOutput()) {
            connection.setDoOutput(true);
        }
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type", "application/json");
        DataOutputStream out = new DataOutputStream(
                connection.getOutputStream());

        UUID uuid = UUID.randomUUID();
        int hashCode = uuid.toString().hashCode();

        JSONObject jObj = new JSONObject();

        jObj.put("format", 1);
        jObj.put("useragent", "ApiClient");
        jObj.put("rid", hashCode);
        jObj.put("parameters", chinaToUnicode(paras.toString()));
        jObj.put("timestamp", new Date().toString());
        jObj.put("v", "1.0");

        out.writeBytes(jObj.toString());
        out.flush();
        out.close();

        return connection;
    }

    // Login
    public static boolean login()
            throws Exception {

        boolean bResult = false;

        String sUrl = "Kingdee.BOS.WebApi.ServicesStub.AuthService.ValidateUser.common.kdsvc";

        JSONArray jParas = new JSONArray();
        jParas.add(dbId);
        jParas.add(uid);
        jParas.add(pwd);
        jParas.add(lang);

        HttpURLConnection connection = initUrlConn(sUrl, jParas);
        String key = null;
        for (int i = 1; (key = connection.getHeaderFieldKey(i)) != null; i++) {
            if (key.equalsIgnoreCase("Set-Cookie")) {
                String tempCookieVal = connection.getHeaderField(i);
                if (tempCookieVal.startsWith("kdservice-sessionid")) {
                    CookieVal = tempCookieVal;
                    break;
                }
            }
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String line;
        System.out.println(" ============================= ");
        System.out.println(" Contents of post request ");
        System.out.println(" ============================= ");
        while ((line = reader.readLine()) != null) {
            String sResult = new String(line.getBytes(), "utf-8");
            System.out.println(sResult);
            bResult = line.contains("\"LoginResultType\":1");
        }
        System.out.println(" ============================= ");
        System.out.println(" Contents of post request ends ");
        System.out.println(" ============================= ");
        reader.close();

        connection.disconnect();

        return bResult;
    }

    // Save
    public static void Save(String formId, String content) throws Exception {
        invoke("Save", formId, content);
    }

    // View
    public static String View(String formId, String content) throws Exception {
        if(login()){
          return  invoke("View", formId, content);
        }
        return null;
    }

    // Submit
    public static void Submit(String formId, String content) throws Exception {
        invoke("Submit", formId, content);
    }

    // Audit
    public static void Audit(String formId, String content) throws Exception {
        invoke("Audit", formId, content);
    }

    // UnAudit
    public static void UnAudit(String formId, String content) throws Exception {
        invoke("UnAudit", formId, content);
    }

    // StatusConvert
    public static void StatusConvert(String formId, String content)
            throws Exception {
        invoke("StatusConvert", formId, content);
    }

    public static String list(String content) throws Exception {
        if(login()){
           return invoke("list",null,content);
        }
        return null;
    }

    private static String invoke(String deal, String formId, String content)
            throws Exception {
        StringBuilder result = new StringBuilder();
        String sUrl = map.get(deal).toString();
        JSONArray jParas = new JSONArray();
        if(StringUtils.isNotBlank(formId)){
            jParas.add(formId);
        }
        jParas.add(content);

        HttpURLConnection connectionInvoke = initUrlConn(sUrl, jParas);

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connectionInvoke.getInputStream()));

        String line;
        System.out.println(" ============================= ");
        System.out.println(" Contents of post request ");
        System.out.println(" ============================= ");
        while ((line = reader.readLine()) != null) {
            result.append(new String(line.getBytes(), "utf-8"));
        }
        System.out.println(" ============================= ");
        System.out.println(" Contents of post request ends ");
        System.out.println(" ============================= ");
        reader.close();

        connectionInvoke.disconnect();

        return result.toString();
    }

    public static String chinaToUnicode(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            int chr1 = (char) str.charAt(i);
            if (chr1 >= 19968 && chr1 <= 171941) {
                result += "\\u" + Integer.toHexString(chr1);
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }
}
