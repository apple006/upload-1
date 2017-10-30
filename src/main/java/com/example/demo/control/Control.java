package com.example.demo.control;

import com.example.demo.dao.UserRepository;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by 18274 on 2017/8/9.
 */
@Controller
public class Control {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/zhuce")
    public String zhuce(){
        return "zhuce";
    }
    @PostMapping("/zhuce")
    public String tijiao(@RequestParam("name") String name,
                         @RequestParam("password") String password,
                         @RequestParam("file")MultipartFile file,
                         Model model) {
        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File("f:\\IDEAworkplave\\demo5\\src\\main\\resources\\static\\picture\\"+name+".jpg")));//保存图片到目录下
                out.write(file.getBytes());
                out.flush();
                out.close();
                String picutreaddr="picture\\" + name +".jpg";
                user.setTupian(picutreaddr);
                userRepository.save(user);//增加用户
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
            model.addAttribute(user);
            return "permanager";
        } else {
            return "上传失败，因为文件是空的.";
        }
 }
}
