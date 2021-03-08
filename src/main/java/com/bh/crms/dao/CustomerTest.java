package com.bh.crms.dao;
import com.bh.crms.pojo.Customer;
import com.bh.crms.util.CustomerUtils;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * 添加数据
 * @Author WWT
 * @Date 2021/1/29
 */
public class CustomerTest {
    @Test
    public void addData() {
        CustomerDao customerDao = new CustomerDao();

        for (int i = 0 ; i < 100 ; i++) {
            Customer c= new Customer();
            c.setCid(CustomerUtils.uuid());
            c.setCname("李青青"+i);
            c.setGender(i%2==0?"男":"女");
            c.setBirthday("2020-01-01");
            c.setCellphone("1555213619" + i);
            c.setEmail("12" + i + "@163.com");
            c.setDescription("我是李青青");
            c.setEbable("0");
            customerDao.addCustomer(c);
        }
    }

    /*public static void main(String[] argus){
        Set set=new TreeSet<String>();
        //插入的时候，已经去重和排序，所以很方便
        set.add("13855555555");
        set.add("13455555555");
        set.add("13845555555");
        set.add("13455555555");
        set.add("");
        Iterator it=set.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }*/
}
