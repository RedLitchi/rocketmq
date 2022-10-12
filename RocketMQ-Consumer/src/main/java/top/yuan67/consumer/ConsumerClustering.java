/**
 * @Author: NieQiang
 * @User: CAPTAIN
 * @CreateTime: 2022-10-11
 * @Desc: 描述信息
 **/
package top.yuan67.consumer;

import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(
    topic = "topic-clustering", consumerGroup = "CLUSTERING",
    messageModel = MessageModel.CLUSTERING
)
public class ConsumerClustering implements RocketMQListener<String> {
  @Value("${server.port}")
  private String port;
  @Override
  public void onMessage(String message) {
    System.out.println(port + "这是mq消费掉的消息:"+message);
  }
}
