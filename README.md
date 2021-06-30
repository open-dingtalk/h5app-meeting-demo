# h5app-meeting-demo

> 钉钉创建会议，需要登陆[开发者后台](https://open-dev.dingtalk.com/)，在创建的应用中配置应用首页地址，添加“成员信息读取权限“、“通讯录部门成员读权限”、”视频会议应用会议写权限“。

## 配置应用首页地址

![image-20210628163247545](https://img.alicdn.com/imgextra/i3/O1CN01YlfHQA22ZscKkxu0g_!!6000000007135-2-tps-2774-1176.png)

## 配置权限

### 添加成员信息读取权限

![image-20210629172246501](https://img.alicdn.com/imgextra/i3/O1CN01VOEiV72A20FUbk2yC_!!6000000008144-2-tps-2856-1212.png

### 添加通讯录部门成员读权限

![image-20210629172900028](https://img.alicdn.com/imgextra/i3/O1CN017gM55e1FrMZA3vCn1_!!6000000000540-2-tps-2852-1170.png)

### 添加视频会议应用会议写权限

![image-20210629173000413](https://img.alicdn.com/imgextra/i3/O1CN01TBv3Ts1fzlqWZfv4R_!!6000000004078-2-tps-2844-1160.png)

## Getting Started

### 克隆代码仓库到本地

git clone

```
https://github.com/open-dingtalk/h5app-meeting-demo.git
```

### 修改邀请人unionId

![image-20210629174119971](https://img.alicdn.com/imgextra/i2/O1CN01eAWKjK1FsHMAFxcSo_!!6000000000542-2-tps-2552-1312.png)

### 使用命令行安装依赖&打包

```txt
cd fronted/
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

![image-20210629174519462](https://img.alicdn.com/imgextra/i1/O1CN01NY5LOc1N4N7scIvHC_!!6000000001516-2-tps-2040-856.png)



### 参考文档

1. 会议权限申请，文档链接：https://developers.dingtalk.com/document/app/apply-for-permissions-5
2. 通讯录权限申请，文档链接：https://developers.dingtalk.com/document/app/address-book-permissions
3. 获取部门用户详情，文档链接：https://developers.dingtalk.com/document/app/queries-the-complete-information-of-a-department-user
4. 创建会议，文档链接：https://developers.dingtalk.com/document/app/create-a-video-conference
5. 关闭会议，文档链接：https://developers.dingtalk.com/document/app/close-audio-video-conference

