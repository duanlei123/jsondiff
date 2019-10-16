package com.tool.jsondiff.controller;

import com.fasthttp.HttpException;
import com.fasthttp.common.Constants;
import com.fasthttp.common.ParaType;
import com.fasthttp.internal.ApiHttpRequest;
import com.fasthttp.internal.ApiHttpResponse;
import com.fasthttp.internal.ApiHttpRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @ ClassName GetRequestController
 * @ Description
 * @ Auther wb-dl321273
 * @ Version 1.0
 **/
@RestController
public class GetRequestController {

    @GetMapping("/getRequest")
    public String getRequest(@RequestParam String url,@RequestParam String method,
                             @RequestParam String heards, @RequestParam String bodys) throws HttpException {
        String url1 = url.trim();
        String method1 = method.trim();
        System.out.println("请求url:"+ url1);

        ApiHttpRequest request = new ApiHttpRequest();
        request.setRespFormat(Constants.FORMAT_JSON);

        if(method1.equals("GET")){
            //添加请求方式
            request.setHttpMethod(method1);

            ApiHttpRunner api = new ApiHttpRunner(url1);
            api.setConnectTimeout(300);
            ApiHttpResponse resp = new ApiHttpResponse();
            api.execute(request, resp);
            System.out.println("get返回结果: "+resp.getResponseContent());
            return resp.getResponseContent();
        }else if (method1.equals("POST")){
            //为post请求
            request.setHttpMethod(method1);
            //处理heards 和 bodys
            Map<String, String> heardesMap = stringUtils(heards);
            for (Map.Entry<String, String> entry : heardesMap.entrySet()) {
                System.out.println("heard:Key = " + entry.getKey() + ", Value = " + entry.getValue());
                request.addHttpHeader(entry.getKey(),entry.getValue());
            }
            Map<String, String> bodysMap = stringUtils2(bodys);
            for (Map.Entry<String, String> entry : bodysMap.entrySet()) {
                System.out.println("bodys:Key = " + entry.getKey() + ", Value = " + entry.getValue());
                request.addCustomPara(entry.getKey(),entry.getValue(),ParaType.STRING,Constants.URL_PARA);
            }
            ApiHttpRunner api = new ApiHttpRunner(url1);
            api.setConnectTimeout(300);
            ApiHttpResponse resp = new ApiHttpResponse();
            api.execute(request, resp);
            System.out.println("post返回结果: "+resp.getResponseContent());
            return resp.getResponseContent();
        }else {
            return "不支持";
        }
    }


    private Map<String,String> stringUtils(String heards){

        Map<String,String> stringMap = new HashMap<>();

        if(heards != "" && heards != null){
            String[] strings = heards.split(">");
            for (int i=0;i<strings.length;i++){
                String[] split = strings[i].split(":");
                stringMap.put(split[0],split[1]);
            }
        }
        return stringMap;
    }


    private Map<String,String> stringUtils2(String bodys){

        Map<String,String> stringMap = new HashMap<>();

        if(bodys != "" && bodys != null){
            String[] strings = bodys.split("&");
            for (int i=0;i<strings.length;i++){
                String[] split = strings[i].split("=");
                stringMap.put(split[0],split[1]);
            }
        }
        return stringMap;
    }
}
