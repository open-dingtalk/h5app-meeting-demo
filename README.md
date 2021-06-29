# h5app-meeting-demo

> 钉钉创建会议，需要登陆[开发者后台](https://open-dev.dingtalk.com/)，在创建的应用中配置应用首页地址，添加“成员信息读取权限“、“通讯录部门成员读权限”、”视频会议应用会议写权限“。

## 配置应用首页地址

![image-20210628163247545](https://img.alicdn.com/imgextra/i3/O1CN01YlfHQA22ZscKkxu0g_!!6000000007135-2-tps-2774-1176.png)

## 配置权限

### 添加成员信息读取权限

![image-20210629172246501](/Users/wan/Library/Application Support/typora-user-images/image-20210629172246501.png)

### 添加通讯录部门成员读权限

![image-20210629172900028](/Users/wan/Library/Application Support/typora-user-images/image-20210629172900028.png)

### 添加视频会议应用会议写权限

![image-20210629173000413](/Users/wan/Library/Application Support/typora-user-images/image-20210629173000413.png)

## Getting Started

### 克隆代码仓库到本地

git clone

```
https://github.com/open-dingtalk/h5app-meeting-demo.git
```

### 修改邀请人unionId

![image-20210629174119971](/Users/wan/Library/Application Support/typora-user-images/image-20210629174119971.png)

### 使用命令行安装依赖&打包

```txt
cd fronted/
```

![image-20210629173609070](/Users/wan/Library/Application Support/typora-user-images/image-20210629173609070.png)

```txt
npm install
```

![image-20210629173856382](/Users/wan/Library/Application Support/typora-user-images/image-20210629173856382.png)

```txt
npm run build
```

![image-20210629174001090](/Users/wan/Library/Application Support/typora-user-images/image-20210629174001090.png)

### 将打包好的静态资源文件放入后端服务

![image-20210629174440058](/Users/wan/Library/Application Support/typora-user-images/image-20210629174440058.png)

### 替换后端应用配置

![image-20210629174519462](/Users/wan/Library/Application Support/typora-user-images/image-20210629174519462.png)



### 参考文档

1. 会议权限申请，文档链接：https://developers.dingtalk.com/document/app/apply-for-permissions-5
2. 通讯录权限申请，文档链接：https://developers.dingtalk.com/document/app/address-book-permissions
3. 获取部门用户详情，文档链接：https://developers.dingtalk.com/document/app/queries-the-complete-information-of-a-department-user
4. 创建会议，文档链接：https://developers.dingtalk.com/document/app/create-a-video-conference
5. 关闭会议，文档链接：https://developers.dingtalk.com/document/app/close-audio-video-conference

