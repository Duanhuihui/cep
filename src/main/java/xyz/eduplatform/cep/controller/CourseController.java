package xyz.eduplatform.cep.controller;

import javafx.util.converter.IntegerStringConverter;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.eduplatform.cep.common.ResultVO;
import xyz.eduplatform.cep.pojo.Info;
import xyz.eduplatform.cep.service.CourseService;

import java.io.File;
import java.io.IOException;

@RestController
public class CourseController {

    @Autowired
    public CourseService courseService;

    @Value("${file-location.img}")
    public String imgSrc;

    @GetMapping(value = "/getAllInfo")
    public ResultVO getInfo(){
        return ResultVO.success(courseService.getAllInfo());
    }
    @PostMapping(value = "/getInfoById")
    public ResultVO getInfo(@RequestParam("id") String id,@RequestParam("name") String name){
        return ResultVO.success(courseService.getInfoById(Integer.parseInt(id)));
    }
    @PostMapping(value="/submitCourse")
    public ResultVO submitCourse(@RequestBody Info info){
        System.out.println(info.getContent());
        return ResultVO.success(courseService.setCourse(info));
    }
}
