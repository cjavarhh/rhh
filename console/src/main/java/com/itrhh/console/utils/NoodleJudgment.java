package com.itrhh.console.utils;

/**
 * @Classname NoodleJudgmen
 * @Description TODO
 * @Created by 14195
 * @Date 2025/7/2 15:08
 * @Version 1.0.0
 */
public class NoodleJudgment {

    public static void validateEntity(String noodleName, String coverImages, Integer price) {
/*        Optional<Noodle> optional = Optional.ofNullable(noodle);
        if (optional.isPresent()) {
            throw new NullPointerException("空指针异常");
        }*/
        if (noodleName == null || noodleName.isEmpty()) {
            throw new RuntimeException("字段不能为空");
        }
        if (price == null || price == 0) {
            throw new RuntimeException("数字不合规");
        }
        if (coverImages == null || coverImages.isEmpty()) {
            throw new RuntimeException("不为空");
        }
    }
}
