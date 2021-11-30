package com.demo.test;

import com.demo.model.Color;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: 罗帅
 * @Date: 2020/11/20
 */
@Slf4j
public class EnumTest {
    @Test
    public void testEnumPrint() {
        //输出某一枚举的值
        System.out.println(Color.RED.getName());
        System.out.println(Color.YELLOW.getName());
    }

@Test
    public void test2(){
    String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><LoadSearchCondition><FunCode>0</FunCode><ReaderData>30</ReaderData><ViewName></ViewName><KeyField></KeyField><SearchFieldItem>*</SearchFieldItem><AppID>86edca32-f636-413b-8b42-df80783e90f0</AppID><Language>Chinese</Language><LoadStyle></LoadStyle><GridMode>Dev</GridMode><SessionID>86edca32-f636-413b-8b42-df80783e90f0</SessionID></LoadSearchCondition><Popedom><CurrentUser>YG-000001</CurrentUser></Popedom></root>";

    Pattern p=Pattern.compile("<CurrentUser>([\\w\\W]*)</CurrentUser>");
    Matcher m=p.matcher(str);
    if(m.find()){
        System.out.println(m.group(1));
    }
}

@Test
    public void test3(){
//
//    log.info(FieldTypeConverter.SysDefinitionToDhccType(DataBaseTypes.Oracle.getType(),"NVARCHAR2"));
}
//    /**
//     * 判断数值是否属于枚举类的值
//     * @param key
//     * @return
//     */
//    public static boolean isInclude(String key){
//        boolean include = false;
//        for (MySqlFieldTypes e: MySqlFieldTypes.values()){
//            if(e.getSysDefinitionType().equals(key)){
//                include = true;
//                break;
//            }
//        }
//        return include;
//    }
//@Test
//    public void test4(){
//    if (isInclude("DATE3")) {
//        log.info("DATE3不为空");
//
//    }else {
//        log.info("DATE3不存在");
//    }
//}
}
