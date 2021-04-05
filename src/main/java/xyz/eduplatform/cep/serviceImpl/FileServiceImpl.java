package xyz.eduplatform.cep.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.eduplatform.cep.bean.FileLocation;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Service
public class FileServiceImpl {

    @Autowired
    private FileLocation fileLocation;
    public void upload(MultipartFile file){
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName  = fileName.substring(fileName.lastIndexOf("."));
        System.out.println(suffixName);
        System.out.println(fileLocation.getImg());
        switch (suffixName){
            case ".png": case ".jpg": case ".gif":
                uploadFile(fileLocation.getImg(),file);break;
            case ".pdf":
                uploadFile(fileLocation.getPdf(),file);break;
            case ".mp4":
                uploadFile(fileLocation.getVideo(),file);break;
            case ".txt":
                uploadFile(fileLocation.getTxt(),file);break;
            default:
                uploadFile(fileLocation.getOther(),file);break;
        }
    }

    private void uploadFile(String url, MultipartFile file){
        File dest = new File(url+file.getOriginalFilename());
        log.info(String.format("文件上传地址是  %s",url));
        log.info(String.format("文件名是  %s",file.getOriginalFilename()));
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void preview(String fileName,HttpServletResponse response){
        String suffixName  = fileName.substring(fileName.lastIndexOf("."));

        switch (suffixName){
            case ".png": case ".jpg": case ".gif":
                 previewFile(fileLocation.getImg()+fileName,response);break;
            case ".pdf":
                previewFile(fileLocation.getPdf()+fileName,response);break;
            case ".mp4":
                previewFile(fileLocation.getVideo()+fileName,response);break;
            case ".txt":
                previewFile(fileLocation.getTxt()+fileName,response);break;
            default:
                previewFile(fileLocation.getTxt()+fileName,response);break;
        }
    }

    private void previewFile(String url, HttpServletResponse response){
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(url));
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] bArray = new byte[1024];
            while(fileInputStream.read(bArray) != -1){
                outputStream.write(bArray);
            }
            outputStream.flush();
            fileInputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean delete(String fileName){
        if(fileName.contains(".")){
            String suffixName  = fileName.substring(fileName.lastIndexOf("."));
            switch (suffixName){
                case ".png": case ".jpg": case ".gif":
                    deleteFile(fileLocation.getImg()+fileName);break;
                case ".pdf":
                    deleteFile(fileLocation.getPdf()+fileName);break;
                case ".mp4":
                    deleteFile(fileLocation.getVideo()+fileName);break;
                case ".txt":
                    deleteFile(fileLocation.getTxt()+fileName);break;
                default:
                    deleteFile(fileLocation.getOther()+fileName);break;
            }
            return true;
        }
        return false;
    }
    /**
     *
     * @param fileName 文件的地址+ 文件名 + 文件后缀
     * @return 删除成功返回true
     */
    private boolean deleteFile(String fileName){
        File file = new File(fileName);
        if(file.exists() && file.isFile()){
            file.delete();
            log.info("删除文件"+fileName+"成功！");
            return true;
        }else{
            log.info("删除文件"+fileName+"失败！");
            return false;
        }
    }
}
