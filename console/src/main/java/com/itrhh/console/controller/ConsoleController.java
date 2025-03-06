package com.itrhh.console.controller;

import com.itrhh.module.service.impl.ConsoleServiceImpl;
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
public class ConsoleController {
    @Autowired
    private ConsoleServiceImpl consoleService;

    @RequestMapping("/noodle/create")
    public String noodleCreate(@RequestParam(name = "id")BigInteger id,
                               @RequestParam(name = "name") String name,
                               @RequestParam(name = "price") int price,
                               @RequestParam(name ="weight" ) int weight,
                               @RequestParam(name = "coverImage") List coverImages,
                               @RequestParam(name = "content") String content,
                               @RequestParam(name = "noodleImages")String noodleImage){
        int result = consoleService.createNoodle(id,name,price,noodleImage,content,weight,coverImages);

        return 1==result ?"成功":"失败";
    }
    @RequestMapping("/noodle/update")
    public String noodleUpdate(@RequestParam(name = "id") BigInteger id,
                               @RequestParam(name = "name") String name,
                               @RequestParam(name = "price") int price,
                               @RequestParam(name ="weight" ) int weight,
                               @RequestParam(name = "coverImage") List coverImages,
                               @RequestParam(name = "content") String content,
                               @RequestParam(name = "noodleImages")String noodleImage){
        int result = consoleService.updateNoodle(id,name,price,noodleImage,content,weight,coverImages);
        return 1==result ?"成功":"失败";
    }
    @RequestMapping("/noodle/delete")
    public String noodleDelete(@RequestParam(name = "noodleId") BigInteger noodleId){
        int result = consoleService.deleteNoodle(noodleId);
        return 1==result ?"成功":"失败";
    }

}
