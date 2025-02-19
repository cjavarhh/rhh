package com.itrhh.app.domain.vo;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;
import java.math.BigInteger;

/**
 * @Classname NooodleAppListVo
 * @Description TODO
 * @Created by 14195
 * @Date 2024/11/9 15:08
 * @Version 1.0.0
 */
@Data
public class NoodleAppListVo {
    BigInteger noodleId;
    String feedImage;
    String noodleName;
    int price;

}
