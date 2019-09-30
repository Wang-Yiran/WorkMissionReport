package com.wangyiran.employee.management.controller;

import com.wangyiran.employee.management.entity.Report;
import com.wangyiran.employee.management.entity.TestReport;
import com.wangyiran.employee.management.entity.User;
import com.wangyiran.employee.management.mapper.UserMapper;
import com.wangyiran.employee.management.po.ReportReq;
import com.wangyiran.employee.management.po.TestReportReq;
import com.wangyiran.employee.management.service.MainService;
import com.wangyiran.employee.management.service.TestReportService;
import com.wangyiran.employee.management.service.UserService;
import com.wangyiran.employee.management.service.UserServiceImpl;
import com.wangyiran.employee.management.util.DateAdd8Hour;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @program: employee-management
 * @description: 测试报告控制器
 * @author: Mr.Wang
 * @create: 2019-03-28 14:09
 **/
@Controller
@RequestMapping("/test")
public class TestReportController {

    protected static Logger logger = LoggerFactory.getLogger(TestReportController.class);

    @Autowired
    private TestReportService TestReportService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/report"}, method = RequestMethod.GET)
    public String report(Model model){
        List<TestReport> itemsList = TestReportService.queryAll();
        //查找目前用户名
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("username",user.getUsername());
        model.addAttribute("itemsList", itemsList);

        model.addAttribute("page","1");
        model.addAttribute("offset","10");
        //返回当前日期
        Date today = new Date();
        model.addAttribute("today", today);

        //返回当前的可选指定完成人
        List<User> users = userService.queryAll();
        model.addAttribute("users", users);
        return "testreport/report";
    }

    @RequestMapping(value = {"/report/edit"}, method = RequestMethod.GET)
    public String reportEdit(Model model, @RequestParam String id) {
        if(id==null)
            return "";
        TestReport testReport = TestReportService.SelectById(Integer.valueOf(id));
        model.addAttribute("item", testReport);
        return "testreport/edit";
    }

    @RequestMapping(value = {"/report/edit"}, method = RequestMethod.POST)
    public String reportSubmit(Model model,@Param("item") TestReport testReport) {
        if(testReport==null){
            return "";
        }
        if(testReport.getId() == null) {
            return "";
        }
        //日志记录
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        logger.info("当前请求的url:/test/report/edit");
        logger.info("当前请求人:" + user.getUsername());
        logger.info("请求参数:" + testReport.toString());
        //时间处理工具 获取的日期加了8小时，要加8小时，存入的世界时间才是当天
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        if(testReport.getStartdate()!=null) {
            date = testReport.getStartdate();
            cal.setTime(date);//设置起时间
            cal.add(Calendar.HOUR, 8);
            testReport.setStartdate(cal.getTime());
        }
        if(testReport.getEnddate()!=null) {
            date = testReport.getEnddate();
            cal.setTime(date);//设置起时间
            cal.add(Calendar.HOUR, 8);
            testReport.setEnddate(cal.getTime());
        }
        TestReportService.EditById(testReport);

        List<TestReport>  itemsList = TestReportService.queryAll();
        model.addAttribute("page","1");
        model.addAttribute("offset","10");
        model.addAttribute("itemsList", itemsList);
        return "redirect:/test/report";
    }

    @RequestMapping(value = {"/report/add"}, method = RequestMethod.POST)
    public String reportAddPost(Model model,@Param("item") TestReport testReport) {
        if(testReport==null){
            return "";
        }
        if(testReport.getId() != null) {
            return "";
        }

        //时间处理工具 获取的日期加了8小时，要加8小时，存入的世界时间才是当天
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        DateAdd8Hour dateAdd8Hour = new DateAdd8Hour();
        if(testReport.getStartdate()!=null) {
            testReport.setStartdate(dateAdd8Hour.dateAdd8hour(testReport.getStartdate()));
        }
        if(testReport.getEnddate()!=null) {
            testReport.setEnddate(dateAdd8Hour.dateAdd8hour(testReport.getEnddate()));
        }

        TestReportService.add(testReport);
//        Report report = mainService.SelectById(id);
//        Report report = new Report();
//        report.setUsername("Hello World!");
//
//        model.addAttribute("user", report);
//        model.addAttribute("item", report);

        List<TestReport> itemsList = TestReportService.queryAll();
        model.addAttribute("itemsList", itemsList);
        return "redirect:/test/report";
    }

    @RequestMapping(value = {"/report/delete"}, method = RequestMethod.GET)
    public String reportAdd(Model model,@RequestParam String id) {
        if(id==null)
            return "";
        //日志记录
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        logger.info("当前请求的url:/test/report/edit");
        logger.info("当前请求人:" + user.getUsername());
        logger.info("请求删除的id :" + id);
        TestReportService.deleteById(Integer.valueOf(id));
        List<TestReport>  itemsList = TestReportService.queryAll();
        model.addAttribute("itemsList", itemsList);
        return "redirect:/test/report";
    }

    @RequestMapping(value = {"/report", "/report/find"}, method = RequestMethod.POST)
    public String reportPost(Model model, @Param("report") TestReportReq testReportReq) {
        if(testReportReq.getPage().isEmpty()){
            testReportReq.setPage("1");
        }
        model.addAttribute("page", testReportReq.getPage());
        model.addAttribute("offset", testReportReq.getOffset());
        //时间处理工具 获取的日期加了8小时，要加8小时，存入的世界时间才是当天
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        if(testReportReq.getTestReport().getEnddate()!=null) {
            date = testReportReq.getTestReport().getEnddate();
            cal.setTime(date);//设置起时间
            cal.add(Calendar.HOUR, 8);
            testReportReq.getTestReport().setEnddate(cal.getTime());
        }

        List<TestReport> testReports = TestReportService.queryAll(testReportReq);
        model.addAttribute("itemsList", testReports);

        //查找目前用户名
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("username",user.getUsername());
        //返回当前日期
        Date today = new Date();
        model.addAttribute("today", today);
        //返回当前的可选指定完成人
        List<User> users = userService.queryAll();
        model.addAttribute("users", users);
        return "testreport/report";
    }
}
