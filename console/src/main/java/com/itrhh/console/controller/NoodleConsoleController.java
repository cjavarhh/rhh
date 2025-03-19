package com.itrhh.console.controller;

import com.itrhh.module.service.ConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

/**
 * 7@Classname ConsoleController
 * @Description TODO
 * @Created by 14195
 * @Date 2024/10/18 9:43
 * @Version 1.0.0
 */
@RestController
public class NoodleConsoleController {
    @Autowired
    private ConsoleService consoleService;

    @RequestMapping("/noodle/create")
    public String noodleCreate(@RequestParam(name = "noodleName") String noodleName,
                               @RequestParam(name = "price") Integer price,
                               @RequestParam(name ="weight" ) Integer weight,
                               @RequestParam(name = "coverImages") String coverImages,
                               @RequestParam(name = "content") String content,
                               @RequestParam(name = "noodleImage")String noodleImage){
        int result = consoleService.createNoodle(noodleName,price,noodleImage,content,weight,coverImages);

        return 1==result ?"成功":"失败";
    }
    @RequestMapping("/noodle/update")
    public String noodleUpdate(@RequestParam(name = "noodleId") BigInteger noodleId,
                               @RequestParam(name = "noodleName") String noodleName,
                               @RequestParam(name = "price") Integer price,
                               @RequestParam(name ="weight" ) Integer weight,
                               @RequestParam(name = "coverImages") String coverImages,
                               @RequestParam(name = "content") String content,
                               @RequestParam(name = "noodleImage")String noodleImage){
        int result = consoleService.updateNoodle(noodleId,noodleName,price,noodleImage,content,weight,coverImages);
        return 1==result ?"成功":"失败";
    }
    @RequestMapping("/noodle/delete")
    public String noodleDelete(@RequestParam(name = "noodleId") BigInteger noodleId,
                               @RequestParam(name = "updateTime")Integer updateTime){
        int result = consoleService.deleteNoodle(noodleId,updateTime);
        return 1==result ?"成功":"失败";
    }

}
