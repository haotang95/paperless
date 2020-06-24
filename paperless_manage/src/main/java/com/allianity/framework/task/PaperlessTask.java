package com.allianity.framework.task;

import org.springframework.stereotype.Component;
import com.allianity.common.utils.StringUtils;

/**
 * 定时任务调度测试
 * 
 * @author allianity
 */
@Component("paperlessTask")
public class PaperlessTask
{
    public void paperlessMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void paperlessParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void paperlessNoParams()
    {
        System.out.println("执行无参方法");
    }
}
