# A-SOUL评论区小作文 枝网查重系统 后端

源项目：[https://github.com/ASoulCnki/ASoulCnki](https://github.com/ASoulCnki/ASoulCnki)

网站地址：[https://asoulcnki.cbu.net](https://asoulcnki.cbu.net)

服务监控地址: [http://43.134.33.161:3001/status/asoul](http://43.134.33.161:3001/status/asoul)

## 部署

### 1 系统要求

1. Java 8
2. Java堆内存1500MB及其以上（取决于数据量）

### 2 运行

#### 2.1 依赖json文件运行

##### 2.1.1 下载数据文件bilibili_cnki_reply.json

- 旧数据（截止至2021年8月中旬），包含约139万条评论数据，需要Java堆内存1500MB：[bilibili_cnki_reply.json](https://drive.google.com/file/d/151oz560vj2T2uwxYrRbxq1NPYwvx_dNf/view?usp=sharing)
- 新数据（更新至2025年1月21日），包含约468万条评论数据，已过滤掉字数少于30字的评论，需解压，需要Java堆内存7GB：[JTLjrYCdIJrpaLuVntv.zip](https://fileditchfiles.me/file.php?f=/s21/JTLjrYCdIJrpaLuVntv.zip)

##### 2.1.2 （方法一）快速开始
1. 将bilibili_cnki_reply.json放入项目根目录下的 data 文件夹
2. 运行 src/test/asia/asoulcnki.api/StartTrainTest.testStartTrain 开始训练
3. 训练完毕后，运行 ApiApplication 启动服务，即可正常使用 (note: 见 2.1.3 运行构建，构建时会自动运行这段代码。)

##### 2.1.3 （方法二）使用其他语言调接口训练
1. 修改application-demo.yml中的secure.key
2. 将bilibili_cnki_reply.json放入data文件夹
3. 运行springboot后端
4. 调用后端train接口训练数据 训练需要较长时间(约一分钟)  
   [示例请求](./dev/start_train.py)(python):

```python
import requests
CONTROL_SECURE_KEY = "123456" #注意修改为application-demo.yml中的secure.key
base_url = "http://localhost:8000/v1/api/data/train"
r = requests.post(base_url, json={'secure_key': CONTROL_SECURE_KEY, 'start_time': 0})
print(r.json())
```

#### 2.1.3 完成数据下载后，使用 maven 构建项目

运行：

```shell
mvn clean package
```

此时 maven 会自动下载依赖并对项目进行编译打包，同时会自动运行上方的 src/test/asia/asoulcnki.api/StartTrainTest.testStartTrain 预处理对应的数据（原作者真是个天才，放到测试单元里运行这段代码），如果数据预处理成功，那么会在项目根目录下的 ./target 目录下生成一个 `api-latest.jar` 文件。可通过该命令：

```shell
java -jar api-latest.jar
```

启动项目。

#### 2.2 依赖数据库运行

需要预先安装docker

```shell
sh bin/start.sh # 启动docker
sh bin/do_setup.sh # 初始化数据库, 需要等待mysql初始化完成，约10s
```

如果想清理数据文件, 执行下面命令即可

```shell
sh bin/cleanup.sh 
```

在初始化数据库完成后，启动项目，可以按照2.1的方式训练数据，也可以参照api文档从数据库拉取数据来训练

#### 2.3 使用docker运行 (deprecated)

> 本仓库目前已不再负责维护 docker 镜像。因此不保证原项目的 docker 镜像能够正常工作。

```shell
# 构建镜像
mvn clean package docker:build
# 或者
# docker pull registry.cn-hangzhou.aliyuncs.com/asoulcnki/api:latest

docker run -e PROFILES=demo -p 8000:8000 -d -v host-path-to-data-dir:/opt/data registry.cn-hangzhou.aliyuncs.com/asoulcnki/api:latest
 # 暴露本机 8000 端口，启动docker
```

## API文档

参见[API文档](./api.md)
