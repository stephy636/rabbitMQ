import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * Created by hzbaorenai on 2017/4/20.
 */
@Slf4j
public class MessageConfirmCallbackImpl implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        if (!ack) {
            log.error("failed to publish message: {}, id = {}",
                    s, correlationData == null ? "" : correlationData.getId());
        } else {
            log.info("Auto-test message is sent to mq: id = {}", correlationData == null ? "" : correlationData.getId());
        }
    }
}