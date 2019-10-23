package com.jeecg.controller.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("file")
@Controller
public class FileCommonController {

    @RequestMapping(params = "upload")
    @ResponseBody
    public String upload(MultipartFile file){

        return null;
    }
}
