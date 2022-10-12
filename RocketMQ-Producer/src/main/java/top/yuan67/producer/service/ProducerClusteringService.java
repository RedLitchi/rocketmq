/**
 * @Author: NieQiang
 * @User: CAPTAIN
 * @CreateTime: 2022-10-12
 * @Desc: 描述信息
 **/
package top.yuan67.producer.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProducerClusteringService {
  @Autowired
  private RocketMQTemplate rocketMQTemplate;
  
  /**
   * 负载均衡模式主题
   **/
  private String topicClustering = "topic-clustering";
  
  public String convertAndSend(String msg, int num) {
    for (int i = 1; i <= num; i++){
      System.out.println("convertAndSend发送异步消息:"+msg+i);
      //异步消息
      rocketMQTemplate.convertAndSend(topicClustering,msg+i);
    }
    return "异步消息:" + msg;
  }
  
  public String syncSend(String msg, int num) {
    List<String> sendResultList = new LinkedList<>();
    for (int i = 1; i <= num; i++){
      System.out.println("syncSend发送同步消息:"+msg+i);
      //同步消息
      SendResult sendResult = rocketMQTemplate.syncSend(topicClustering,msg+i);
      sendResultList.add(JSONObject.toJSON(sendResult).toString());
    }
    return "同步消息:" + msg + "<br/>" + sendResultList;
  }
  
  public String asyncSend(String msg, int num) {
    List<String> sendResultList = new LinkedList<>();
    for (int i = 1; i <= num; i++){
      System.out.println("asyncSend发送异步消息:"+msg+i);
      //同步消息
      rocketMQTemplate.asyncSend(topicClustering, msg + i, new SendCallback() {
        @Override
        public void onSuccess(SendResult sendResult) {
          sendResultList.add(JSONObject.toJSON(sendResult).toString());
        }
  
        @Override
        public void onException(Throwable e) {
          sendResultList.add(e.getMessage());
        }
      });
    }
    return "同步消息:" + msg + "<br/>" + sendResultList;
  }
  
  public String sendOneWay(String msg, int num) {
    for (int i = 1; i <= num; i++){
      System.out.println("sendOneWay发送单向消息:"+msg+i);
      //单向消息
      rocketMQTemplate.sendOneWay(topicClustering,msg+i);
    }
    return "单向消息:" + msg;
  }
}
