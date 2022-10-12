/**
 * @Author: NieQiang
 * @User: CAPTAIN
 * @CreateTime: 2022-10-11
 * @Desc: 描述信息
 **/
package top.yuan67.producer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yuan67.producer.service.ProducerBroadcastingService;
import top.yuan67.producer.service.ProducerClusteringService;

import javax.annotation.Resource;

@RestController
public class ProducerController {
  
  @Resource
  private ProducerClusteringService producerService;
  @Resource
  private ProducerBroadcastingService producerBroadcastingService;
  
  /**
   * 负载均衡模式
   * @param msg
   * @param num
   * @param type
   * @return
   */
  @GetMapping("/c")
  public String sendMessage(String msg, int num, int type){
    switch (type){
      case 1:
        return producerService.convertAndSend(msg, num);
      case 2:
        return producerService.syncSend(msg, num);
      case 3:
        return producerService.asyncSend(msg, num);
      case 4:
        return producerService.sendOneWay(msg, num);
    }
    return msg;
  }
  
  /**
   * 广播模式
   * @param msg
   * @param num
   * @param type
   * @return
   */
  @GetMapping("/b")
  public String sendMessages(String msg, int num, int type){
    switch (type){
      case 1:
        return producerBroadcastingService.convertAndSend(msg, num);
      case 2:
        return producerBroadcastingService.syncSend(msg, num);
      case 3:
        return producerBroadcastingService.asyncSend(msg, num);
      case 4:
        return producerBroadcastingService.sendOneWay(msg, num);
    }
    return msg;
  }
}