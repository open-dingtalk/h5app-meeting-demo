# h5app-meeting-demo

> 钉钉创建会议，需要登陆[开发者后台](https://open-dev.dingtalk.com/)，在创建的应用中配置应用首页地址，添加“成员信息读取权限“、“通讯录部门成员读权限”、”视频会议应用会议写权限“。
>
> 架构形态是一个Java单体应用，钉钉用户可以在页面上点击**发起会议**按钮调用钉钉通讯录选择邀请人员或者部门创建会议，会议完毕后用户可以在页面点击**关闭会议**按钮关闭会议。包含功能：

- 发起会议：用于创建会议，被邀请人可以在钉钉客户端会议，也可以通过创建会议接口返回的入会地址通过浏览器进入会议；
- 关闭会议：根据会议ID关闭会议；

### 开发环境准备

#### 钉钉开放平台环境准备

1. 需要有一个钉钉注册企业，如果没有可以创建：https://oa.dingtalk.com/register_new.htm?source=1008_OA&lwfrom=2018122711522903000&succJump=oa#/

2. 成为钉钉开发者，参考文档：https://developers.dingtalk.com/document/app/become-a-dingtalk-developer

3. 登录钉钉开放平台后台创建一个H5应用： https://open-dev.dingtalk.com/#/index

4. 配置应用

   配置开发管理，参考文档：https://developers.dingtalk.com/document/app/configure-orgapp

   ![image-20210702161541006](https://img.alicdn.com/imgextra/i4/O1CN01OHKBbK1fcPe3l30PV_!!6000000004027-2-tps-2878-1064.png)

   配置免登相关权限：https://developers.dingtalk.com/document/app/address-book-permissions

   ![image-20210628161245415](https://img.alicdn.com/imgextra/i4/O1CN01fvqz0z1J8iQ1XSiRi_!!6000000000984-2-tps-2828-1200.png)

   添加成员信息读取权限

   ![image-20210702161723826](https://img.alicdn.com/imgextra/i2/O1CN01Cw9zQW1GBVzZdn2ej_!!6000000000584-2-tps-2862-1154.png)

添加通讯录部门成员读权限

![image-20210629172900028](https://img.alicdn.com/imgextra/i3/O1CN017gM55e1FrMZA3vCn1_!!6000000000540-2-tps-2852-1170.png)

添加视频会议应用会议写权限

![image-20210629173000413](https://img.alicdn.com/imgextra/i3/O1CN01TBv3Ts1fzlqWZfv4R_!!6000000004078-2-tps-2844-1160.png)

##### 获取H5钉钉应用的参数

```properties
#钉钉组织ID
corpId=xxxxx
#H5应用Key
appKey=xxxx
#H5应用秘钥
appSecret=xxxxxx
#H5应用ID
agentID=xxxxx
```

##### 钉钉应用参数需要登陆开发者后台

1. 首页获取corpId https://open-dev.dingtalk.com/#/index
2. 进入应用-基础信息获取agentId、appKey、appSecret

## Getting Started

### 克隆代码仓库到本地

git clone

```
https://github.com/open-dingtalk/h5app-meeting-demo.git
```

### 修改需要鉴权的url

![image-20210702162223818](https://img.alicdn.com/imgextra/i2/O1CN01igAD421P8c1dSUSFp_!!6000000001796-2-tps-2622-998.png)

### 使用命令行安装依赖&打包

```txt
cd frontend/
```

![image-20210629173609070](https://img.alicdn.com/imgextra/i3/O1CN0129OwPi1fkem44fFKT_!!6000000004045-2-tps-2492-728.png)

```txt
npm install
```

![image-20210629173856382](https://img.alicdn.com/imgextra/i1/O1CN01aJ5SC31MRRSS8Boi6_!!6000000001431-2-tps-2196-938.png)

```txt
npm run build
```

![image-20210629174001090](https://img.alicdn.com/imgextra/i1/O1CN01Vxi9rM1nCmPIYJrxB_!!6000000005054-2-tps-2004-1076.png)

### 将打包好的静态资源文件放入后端服务

![image-20210629174440058](https://img.alicdn.com/imgextra/i3/O1CN01NJ199X1fP89uCNv3a_!!6000000003998-2-tps-2174-1602.png)

### 替换后端应用配置

![image-20210702161342938](https://img.alicdn.com/imgextra/i3/O1CN011C2NFX1coN8qE1ttP_!!6000000003647-2-tps-2648-1104.png)

### 启动服务-使用钉钉移动端访问

![image-20210702161342938](https://img.alicdn.com/imgextra/i2/O1CN01aOKVj51VRHrrGAUDe_!!6000000002649-0-tps-324-720.jpg)

![image-20210702161342938](https://img.alicdn.com/imgextra/i3/O1CN01DazjWD1XXokQWOIXE_!!6000000002934-2-tps-1080-1420.png)

### 参考文档

1. 会议权限申请，文档链接：https://developers.dingtalk.com/document/app/apply-for-permissions-5
2. 接入JSAPI，文档链接：https://developers.dingtalk.com/document/app/read-before-development
3. JSAPPI鉴权，文档链接：https://developers.dingtalk.com/document/app/jsapi-authentication
4. 免登获取用户unionId，文档链接：https://developers.dingtalk.com/document/app/obtain-the-userid-of-a-user-by-using-the-log-free?spm=ding_open_doc.document.0.0.33d877a2zhsNBN#topic-2010561
5. 根据用户ID获取用户详情，文档链接：https://developers.dingtalk.com/document/app/query-user-details
6. 获取部门用户详情，文档链接：https://developers.dingtalk.com/document/app/queries-the-complete-information-of-a-department-user
7. 获取企业内部应用accessToken，文档链接：https://developers.dingtalk.com/document/app/obtain-orgapp-token?spm=ding_open_doc.document.0.0.73aed394yusS9l#topic-1936350
8. 创建会议，文档链接：https://developers.dingtalk.com/document/app/create-a-video-conference
9. 关闭会议，文档链接：https://developers.dingtalk.com/document/app/close-audio-video-conference

