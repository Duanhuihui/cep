package xyz.eduplatform.cep.controller;

import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.eduplatform.cep.common.ResultVO;
import xyz.eduplatform.cep.serviceImpl.FileServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Slf4j
@RestController
public class FileController {


    @Autowired
    public FileServiceImpl fileService;

    @PostMapping(value = "/file/upload")
    public ResultVO upload(@RequestParam(value = "file") MultipartFile file){
        log.info("file",file);
        fileService.upload(file);
        return ResultVO.success();
    };
    @PostMapping(value = "/file/uploadBatch")
    public ResultVO uploadBatch(@RequestParam(value = "files") MultipartFile[] files){
        System.out.println(files);
//        for(MultipartFile file:files){
//            fileService.upload(file);
//        };
        return ResultVO.success();
    };
    @GetMapping("/file/preview/{fileName}")
    public ResultVO preview(@PathVariable @NotNull String fileName, HttpServletResponse response){
        log.info(String.format("预览的文件是%s",fileName));
        if(!fileName.contains(".") && fileName == null){
            log.info(String.format("预览的文件%s缺失后缀名",fileName));
            return ResultVO.error();
        }
        fileService.preview(fileName,response);
        return ResultVO.success();
    }
    @GetMapping("/file/delete/{fileName}")
    public ResultVO deleteFile(@PathVariable @NotNull String fileName){
        log.info("fileName"+fileName);
        fileService.delete(fileName);
        return null;
    }
    @PostMapping(value = "/file/delete")
    public ResultVO deleteFiles(@RequestParam("fileNames") List<String> fileNames){
        log.info("fileNames"+fileNames);
//        fileService.delete(fileName);
        return null;
    }
}
