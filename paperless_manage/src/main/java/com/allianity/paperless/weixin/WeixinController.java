package com.allianity.paperless.weixin;

import com.allianity.common.utils.PropertiesUtil;
import com.allianity.framework.aspectj.lang.annotation.Log;
import com.allianity.framework.aspectj.lang.enums.BusinessType;
import com.allianity.framework.web.controller.BaseController;
import com.allianity.framework.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: tangh
 * @create: 2020-06-24
 **/
@RestController
@RequestMapping("/weixin")
public class WeixinController extends BaseController {

    @GetMapping("/getAccessToken")
    @Log(title = "获取AccessToken", businessType = BusinessType.OTHER)
    public AjaxResult getWeixintoken(){
        String appid = PropertiesUtil.getProperty("weixin.appid");
        String appsecret = PropertiesUtil.getProperty("weixin.appsecret");

//        String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=%s&appid=%s&secret=%s", "client_credential",appid,appsecret);
//        String result = HttpUtils.sendGet(url, "");
        return AjaxResult.success(appid);
    }

}
