package com.allianity.paperless.weixin;

import com.allianity.framework.aspectj.lang.annotation.Log;
import com.allianity.framework.aspectj.lang.enums.BusinessType;
import com.allianity.framework.web.controller.BaseController;
import com.allianity.framework.web.domain.AjaxResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: tangh
 * @create: 2020-06-24
 **/
@RestController
@RequestMapping("/demo")
public class DemoController extends BaseController {

    @GetMapping("/test")
//    @PreAuthorize("@ss.hasPermi('monitor:server:list')")
    @Log(title = "日志测试+++++++++++", businessType = BusinessType.OTHER)
    public AjaxResult getWeixintoken(){


        return AjaxResult.success();
    }

}
