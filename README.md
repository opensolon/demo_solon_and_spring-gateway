
### 案例1-测试（gateway-solon）

1. 编译 项目
2. 运行 demo-gateway-solon 的 DemoGatewayApp（端口 8000）
3. 运行 demo-service-solon 的 DemoServerApp（端口 8080）
4. 调试 运行 demo-service-solon/test 下面的单测


### 案例2-测试（gateway-string）

1. 编译 项目
2. 运行 demo-gateway-spring 的 DemoGatewayApplication（端口 8000）
3. 运行 demo-service-solon 的 DemoServerApp（端口 8080）
4. 调试 运行 demo-service-solon/test 下面的单测

### 简单压测参考：

1. 重启动电脑，并尽量不要打开其它软件
2. 运行 wrk 命令（或别的测试工具），运行4次

```
wrk -t10 -c100 -d10s --latency "http://localhost:8000/demo/model/add?modelId=4"
```


