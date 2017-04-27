
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;



@ContextConfiguration(locations = {"classpath:/spring/spring-rabbitmq.xml"})
@Slf4j
public class CreditInMessageNormal extends AbstractTestNGSpringContextTests {

    @Autowired
    private RabbitTemplate loanRabbitTemplate;

    @Test
    public void send() throws Exception {

        String key="dai.merchant.notify.queue";
        // String type="wyxd.notify.collect.in";
         String type = "";
        //Map<String,String> map=new HashMap<>();
       // map.put("msg","hello test");
        //先不写文件
        //可以从文件里读取
        //String info="{\"apply_serial\":\"CREDIT_AUDIT-201612061931-000096\",\"audit_result\":true,\"available_amount\":2344.00,\"m_order_id\":\"merchant-20161103\",\"m_user_id\":\"84447\",\"user_level_code\":\"AA\",\"user_status\":0,\"valid_end\":1482370932000,\"valid_start\":1481074932000}";
        //String info="DNYgCJgd4WhQ6UkjaE/6H2JhLl6PB+A9Ada9Q8fvsL/Gd91JIzRwe8fJanq4q+0alOjtPS/udcBNtbKmpIRmp5V6ZamL+j6OjxkBqe4ajyyDmecAumqDnJo41yOUm0flMxh/SkTclgMfqQdz344bya36ob3geBRtobOIKkHsmPzEhpQ7hXQJPm2+RjoA84VRSE6YZNg4tL9jmg8LAUOkTU4dvlzzrXMTMKbQ63BKVlXhQi2YNZ57LDogUVe/zEKwHXe9XHydGTa0QDlDp9g1VcnPX1rycxSgJCjsITatR0XyqVmMZdtpMfy0zXhHq6+QObYmLGA7meXm22aGK5lpGtuii6nE6FjBFz1Sub6C7pkcRuJWpGAz1+hAb/gGBqChEu9zKz5ihqjka1Oh2yAAn068msSIRkP7ma0kcWC9hWQJfMXH/Ue9e6d6CFg+izoNBwdcFeAejKZuYig9347RlWLW9OleXzmWR0uTnrDQZfW5tUIUIJOSeMxBlLvVi0kNgZXiuOVse14/xvcgeSb93l7dDu28JzaAPOPB1G7ohGfb0u7CrE3792jiFBIHKWkt3MAV8uWyKDiKss8dSBv1Csx38zcIEQM65VGIiDZAFAFcOzdakujlkT5kA9Bz0i8p4Se2AHg5Ok9CpGV8NG+VjAZQOzhCU5Tylw24+Fgq/4o+idX9E60oFwyWogekoxVuMJGFKdjDKnFQgOYTwV88iV+uLQZ380rLRN9HIFyulA8INPwpFkpIo1eI/b1RZrIYfq4LwyUBmTXPjnhvQQHFshF4r3HT+ed06LGFNilmVQlPWKyH";
       String info = "asd";
       try {
            JSONObject object= JSON.parseObject(info);
             log.info(object.toString());

            MessageConverter messageConverter = loanRabbitTemplate.getMessageConverter();
            MessageProperties messageProperties=new MessageProperties();
            messageProperties.setType(type);
            Message message= messageConverter.toMessage(info, messageProperties);
            loanRabbitTemplate.send(key,message);

//            creditRabbitTemplate.convertAndSend(key,obj);
        }catch (Exception e){
           //Sysytem.out.println(e.getMessage());
           log.error(e.getMessage());
        }
    }


}

