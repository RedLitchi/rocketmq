# RocketMQ-4.9.0

### Update or create subscription group
```bat
sh mqadmin updateSubGroup -b 127.0.0.1:10911 -g SubGroupA
```

```bat
.\tools.cmd org.apache.rocketmq.example.quickstart.Producer
```

```bat
.\tools.cmd org.apache.rocketmq.example.quickstart.Consumer
```

## 启动NameServer

```bat
mqnamesrv.cmd
```



## 启动Borker

```bat
mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true
```