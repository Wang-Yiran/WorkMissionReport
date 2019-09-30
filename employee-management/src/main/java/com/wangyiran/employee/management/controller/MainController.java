package com.wangyiran.employee.management.controller;

import com.wangyiran.employee.management.entity.Report;
import com.wangyiran.employee.management.entity.User;
import com.wangyiran.employee.management.mapper.UserMapper;
import com.wangyiran.employee.management.po.ReportReq;
import com.wangyiran.employee.management.service.MainService;
import com.wangyiran.employee.management.service.UserService;
import com.wangyiran.employee.management.util.DateAdd8Hour;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @program: hello-springboot-thymeleaf
 * @description: 主控制器，实现首页的访问
 * @author: Mr.Wang
 * @create: 2019-03-19 23:55
 **/
@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("login",true);
        return "index";
    }

    //登陆处理
    @RequestMapping(value = {"index"}, method = RequestMethod.POST)
    public String login(Model model, String user, String password){

        /**
         * 使用shiro编写认证操作
         */
        //1. 获取subject
        Subject subject = SecurityUtils.getSubject();
        //2. 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(user, password);
        //3. 执行登陆方法
        try {

            subject.login(token);
            //登陆成功
        } catch (UnknownAccountException e){
            e.printStackTrace();
            //用户名不存在
            model.addAttribute("msg", "用户名不存在");
            model.addAttribute("login",true);
            return "index";
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            model.addAttribute("msg","用户名密码错");
            model.addAttribute("login",true);
            return "index";
        }

        model.addAttribute("msg","登陆成功");
        model.addAttribute("login",false);
        return "index";
    }

    /**
     * 登出
     * @return
     */
    @RequestMapping("/logout")
    public String logout(){
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser!=null){
            currentUser.logout();
        }
        return "redirect:/index";
    }

    /**
     * 403没有授权
     * @return
     */
    @RequestMapping("/noAuth")
    public String noAuth(){
        return "/error/noAuth";
    }

    /**
     * 密码修改页面
     * TODO 实现密码修改功能
     * @return
     */
    @RequestMapping("/user")
    public String user(){
        //TODO 实现密码修改的页面
        return "redirect:/report";
    }

    @RequestMapping(value = {"/report"}, method = RequestMethod.GET)
    public String report(Model model){
//        List<Report>  itemsList = mainService.SelectAll();

        List<Report>  itemsList = mainService.queryAll();
        model.addAttribute("itemsList", itemsList);

        model.addAttribute("page","1");
        model.addAttribute("offset","10");
        //查找目前用户名
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("username",user.getUsername());
        //返回当前的可选指定完成人
        List<User> users = userService.queryAll();
        model.addAttribute("users", users);
        model.addAttribute("total", mainService.countAll());
        return "report";
    }

    @RequestMapping(value = {"/report", "/report/find"}, method = RequestMethod.POST)
    public String reportPost(Model model, @Param("report") ReportReq reportReq) {
        if(reportReq.getPage().isEmpty()){
            reportReq.setPage("1");
        }
        model.addAttribute("page", reportReq.getPage());
        model.addAttribute("offset", reportReq.getOffset());
        //时间处理工具 获取的日期加了8小时，要加8小时，存入的世界时间才是当天
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        if(reportReq.getReport().getEnddate()!=null) {
            date = reportReq.getReport().getEnddate();
            cal.setTime(date);//设置起时间
            cal.add(Calendar.HOUR, 8);
            reportReq.getReport().setEnddate(cal.getTime());
        }

        List<Report> reports = mainService.queryAll(reportReq);
        model.addAttribute("itemsList", reports);

        //查找目前用户名
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("username",user.getUsername());
        //返回当前的可选指定完成人
        List<User> users = userService.queryAll();
        model.addAttribute("users", users);
        model.addAttribute("users", users);
        model.addAttribute("total", mainService.countAll(reportReq));
        return "report";
    }

    @RequestMapping(value = {"/report/edit"}, method = RequestMethod.GET)
    public String reportEdit(Model model, @RequestParam String id) {
        if(id==null)
            return "";
        Report report = mainService.SelectById(Integer.valueOf(id));
        model.addAttribute("item", report);
        return "edit";
    }

    @RequestMapping(value = {"/report/edit"}, method = RequestMethod.POST)
    public String reportSubmit(Model model,@Param("item") Report report) {
        if(report==null){
            return "";
        }
        if(report.getId() == null) {
            return "";
        }
        //时间处理工具 获取的日期加了8小时，要加8小时，存入的世界时间才是当天
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        if(report.getStartdate()!=null) {
            date = report.getStartdate();
            cal.setTime(date);//设置起时间
            cal.add(Calendar.HOUR, 8);
            report.setStartdate(cal.getTime());
        }
        if(report.getEnddate()!=null) {
            date = report.getEnddate();
            cal.setTime(date);//设置起时间
            cal.add(Calendar.HOUR, 8);
            report.setEnddate(cal.getTime());
        }
        mainService.EditById(report);

        List<Report>  itemsList = mainService.queryAll();
        model.addAttribute("page","1");
        model.addAttribute("offset","10");
        model.addAttribute("itemsList", itemsList);
        return "redirect:/report";
    }

    @RequestMapping(value = {"/report/add"}, method = RequestMethod.POST)
    public String reportAddPost(Model model,@Param("item") Report report) {
        if(report==null){
            return "";
        }
        if(report.getId() != null) {
            return "";
        }

        //时间处理工具 获取的日期加了8小时，要加8小时，存入的世界时间才是当天
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        DateAdd8Hour dateAdd8Hour = new DateAdd8Hour();
        if(report.getStartdate()!=null) {
            report.setStartdate(dateAdd8Hour.dateAdd8hour(report.getStartdate()));
        }
        if(report.getEnddate()!=null) {
            report.setEnddate(dateAdd8Hour.dateAdd8hour(report.getEnddate()));
        }

        mainService.add(report);
//        Report report = mainService.SelectById(id);
//        Report report = new Report();
//        report.setUsername("Hello World!");
//
//        model.addAttribute("user", report);
//        model.addAttribute("item", report);

//        List<Report>  itemsList = mainService.SelectAll();
//        model.addAttribute("itemsList", itemsList);
        return "redirect:/report";
    }

    @RequestMapping(value = {"/report/add"}, method = RequestMethod.GET)
    public String reportAdd(Model model,@Param("item") Report report) {
        return "add";
    }

    @RequestMapping(value = {"/report/delete"}, method = RequestMethod.GET)
    public String reportAdd(Model model,@RequestParam String id) {
        if(id==null)
            return "";
        mainService.deleteById(Integer.valueOf(id));
        List<Report>  itemsList = mainService.queryAll();
        model.addAttribute("itemsList", itemsList);
        return "redirect:/report";
    }
}
