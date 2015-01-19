package springguides.fileupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by meng on 1/19/15.
 */
@Controller
public class FileUploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    @ResponseBody
    public String provideUploadInfo(){
        return "You can upload a file by posting to this same controller";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(@RequestParam("name") String name,
                                   @RequestParam("file") MultipartFile file){
        if(!file.isEmpty()){
            try{
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(name)));
                bufferedOutputStream.write(file.getBytes());
                bufferedOutputStream.close();
                return "You have uploaded " + name;
            }catch(Exception e){
                return "You failed to upload " + name + " ==>" + e.getMessage();
            }

        }else{
            return "You failed to upload " + name + " as file is empty";
        }
    }

}
