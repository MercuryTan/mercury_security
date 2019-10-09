package com.tx.web.controller;

import com.tx.domain.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 *
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private String localPath = "E:\\Java\\mercury_security\\security_demo\\src\\main\\java\\com\\tx\\web\\controller";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException { //字段名和@test中的一致

        //把模拟的上传数据保存到 controller下
        File localFile = new File(localPath ,new Date().getTime()+".txt");
        //上传文件到localFile
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }


    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        File localFile = new File(localPath ,id+".txt");
        try(
                FileInputStream in = new FileInputStream(localFile);
                //获得输出流，相应到浏览器中
                OutputStream out = response.getOutputStream();
                ){
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition","attachment;filename=test.txt");
            //把文件输入流写到相应中
            IOUtils.copy(in,out);
            out.flush();
        }
    }
}