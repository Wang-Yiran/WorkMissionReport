package com.wangyiran.employee.management.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @program: employee-management
 * @description: 由于Date不带小时分钟秒，所以传入的时间被处理时会自动转换为世界时，即减去8小时，因此需要转换
 * @author: Mr.Wang
 * @create: 2019-03-22 10:34
 **/
public class DateAdd8Hour {
    private Calendar cal = Calendar.getInstance();

    public Date dateAdd8hour(Date date) {
        cal.setTime(date);
        cal.add(Calendar.HOUR, 8);
        return cal.getTime();
    }
}
