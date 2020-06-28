package com.allianity.paperless.weixin;

import com.alibaba.fastjson.JSONObject;
import com.allianity.common.constant.HttpStatus;
import com.allianity.common.constant.RedisConstants;
import com.allianity.common.utils.PropertiesUtil;
import com.allianity.common.utils.StringUtils;
import com.allianity.common.utils.http.HttpUtils;
import com.allianity.framework.aspectj.lang.annotation.Log;
import com.allianity.framework.aspectj.lang.enums.BusinessType;
import com.allianity.framework.redis.RedisCache;
import com.allianity.framework.web.controller.BaseController;
import com.allianity.framework.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

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
        String accessToken = RedisCache.getValue(RedisConstants.WEIXIN_ACCESSTOKEN);
        String jsTicket = RedisCache.getValue(RedisConstants.WEIXIN_JSTICKET);
        if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(jsTicket)){
            String appid = PropertiesUtil.getProperty("weixin.appid");
            String appsecret = PropertiesUtil.getProperty("weixin.appsecret");

            String tokenurl = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=%s&appid=%s&secret=%s", "client_credential",appid,appsecret);
            String tokenResult = HttpUtils.sendGet(tokenurl, "");
            accessToken = JSONObject.parseObject(tokenResult).getString("access_token");
            String jsticketurl = String.format("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi");
            String ticketReuslt = HttpUtils.sendGet(jsticketurl, "");
            jsTicket = JSONObject.parseObject(ticketReuslt).getString("ticket");

            RedisCache.setValue(RedisConstants.WEIXIN_ACCESSTOKEN, accessToken, 7200, TimeUnit.SECONDS);
            RedisCache.setValue(RedisConstants.WEIXIN_JSTICKET, jsTicket, 7200, TimeUnit.SECONDS);
        }
        /*封装结果*/
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accessToken", accessToken);
        jsonObject.put("jsTicket", jsTicket);
        return new AjaxResult(HttpStatus.SUCCESS, "查询成功", jsonObject);
    }

}
