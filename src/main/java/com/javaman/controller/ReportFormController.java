package com.javaman.controller;

import com.javaman.domain.Student;
import com.javaman.enums.Sex;
import com.javaman.service.ReportManager;
import com.javaman.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.PageData;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/report")
public class ReportFormController {


    /**
     * 导出报表
     *
     * @return
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Student> list = new ArrayList<Student>();

        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setName("张三" + i + "号");
            student.setSex(Sex.MAN.getSex());
            student.setAge(18 + i);
            list.add(student);
        }

        //excel标题
        String[] title = {"姓名", "性别", "年龄"};

        //excel文件名
        String fileName = "学生信息表" + System.currentTimeMillis() + ".xls";

        //sheet名
        String sheetName = "学生信息表";

        String[][] content = new String[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            content[i] = new String[title.length];
            Student obj = list.get(i);
            content[i][0] = obj.getName();
            content[i][1] = obj.getSex();
            content[i][2] = obj.getAge() + "";


        }

         //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

         //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}