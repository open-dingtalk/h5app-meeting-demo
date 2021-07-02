import './App.css';
import axios from 'axios';
import * as dd from 'dingtalk-jsapi';

//内网穿透工具介绍:
// https://developers.dingtalk.com/document/resourcedownload/http-intranet-penetration?pnamespace=app
// 替换成后端服务域名
export const domain = "";

function App() {

    let agentId, corpId, timeStamp, nonceStr, signature;

    // jsapi鉴权时传入的url，和需要鉴权的页面url保持一致，如果是index页面需要鉴权，访问页面时也需要加上index.html
    let url = "http://wanzq.vaiwan.com/index.html";

    axios.get(domain + "/config?url=" + encodeURIComponent(url))
        .then(response => {
            agentId = response.data.data.agentId;
            corpId = response.data.data.corpId;
            timeStamp = response.data.data.timeStamp;
            nonceStr = response.data.data.nonceStr;
            signature = response.data.data.signature;
            dd.config({
                agentId: agentId, // 必填，微应用ID
                corpId: corpId,//必填，企业ID
                timeStamp: timeStamp, // 必填，生成签名的时间戳
                nonceStr: nonceStr, // 必填，自定义固定字符串。
                signature: signature, // 必填，签名
                type: 0,  //选填。0表示微应用的jsapi,1表示服务窗的jsapi；不填默认为0。该参数从dingtalk.js的0.8.3版本开始支持
                jsApiList: [
                    'runtime.info',
                    'biz.contact.choose',
                    'biz.contact.complexPicker',
                    'device.notification.confirm',
                    'device.notification.alert',
                    'device.notification.prompt',
                    'biz.ding.post',
                    'biz.util.openLink'
                ] // 必填，需要使用的jsapi列表，注意：不要带dd。
            });

            dd.ready(function () {
                // dd.ready参数为回调函数，在环境准备就绪时触发，jsapi的调用需要保证在该回调函数触发后调用，否则无效。
                dd.runtime.permission.requestAuthCode({
                    corpId: corpId, //三方企业ID
                    onSuccess: function (result) {
                        // alert(JSON.stringify(result));
                        axios.get(domain + "/login?authCode=" + result.code)
                            .then(response => {
                                // alert(JSON.stringify(response));
                                // alert(JSON.stringify(response.data));
                                // alert(JSON.stringify(response.data.data.userid));
                                // alert(JSON.stringify(response.data.data.deptIdList[0]));
                                // 登录成功后储存用户部门和ID
                                sessionStorage.setItem("userId", response.data.data.userid);
                                sessionStorage.setItem("unionId", response.data.data.unionid);
                                sessionStorage.setItem("deptId", response.data.data.deptIdList[0]);
                            })
                            .catch(error => {
                                alert(JSON.stringify(error));
                                // console.log(error.message)
                            })

                    },
                    onFail: function (err) {
                        alert(JSON.stringify(err))
                    }
                });
            });

            dd.error(function (err) {
                alert('dd error: ' + JSON.stringify(err));
            })//该方法必须带上，用来捕获鉴权出现的异常信息，否则不方便排查出现的问题

        })
        .catch(error => {
            alert(JSON.stringify(error));
            // console.log(error.message)
        });


    const createMeeting = () => {

        dd.biz.contact.complexPicker({
            title: "测试标题",            //标题
            // corpId: sessionStorage.getItem("corpId"),              //企业的corpId
            corpId: corpId,              //企业的corpId
            multiple: true,            //是否多选
            limitTips: "超出了",          //超过限定人数返回提示
            maxUsers: 1000,            //最大可选人数
            pickedUsers: [],            //已选用户
            pickedDepartments: [],          //已选部门
            disabledUsers: [],            //不可选用户
            disabledDepartments: [],        //不可选部门
            requiredUsers: [],            //必选用户（不可取消选中状态）
            requiredDepartments: [],        //必选部门（不可取消选中状态）
            // appId: sessionStorage.getItem("agentId"),              //微应用Id，企业内部应用查看AgentId
            appId: agentId,              //微应用Id，企业内部应用查看AgentId
            // permissionType: "xxx",          //可添加权限校验，选人权限，目前只有GLOBAL这个参数
            responseUserOnly: false,        //返回人，或者返回人和部门
            startWithDepartmentId: 0,   //仅支持0和-1
            onSuccess: function (result) {
                alert(JSON.stringify(result))
                let deptIds = result.departments;
                let userIds = result.users.map(user => user.emplId);
                // 获取存储的用户部门和ID
                const userId = sessionStorage.getItem('userId');
                const unionId = sessionStorage.getItem('unionId');
                const deptId = sessionStorage.getItem('deptId');

                // demo直接构建了要请求的数据，实际开发需要从页面获取
                const data = {
                    "userId": unionId,
                    "confTitle": "会议Demo",
                    "inviteDeptIds": deptIds,
                    "inviteUserIds": userIds
                };
                // 发起会议
                axios({
                    url: domain + '/meeting',
                    method: 'post',
                    data: data,
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                    .then(function (response) {
                        // alert(JSON.stringify(response));
                        console.log(response);
                        sessionStorage.setItem("conferenceId", response.data.data.conferenceId);

                    })
                    .catch(function (error) {
                        alert(JSON.stringify(error))
                        console.log(error);
                    });

            },
            onFail: function (err) {
                alert(JSON.stringify(err));
                console.log(JSON.stringify(err));
            }
        });

    };

    const closeMeeting = () => {
        // 获取存储的用户部门和ID
        const unionId = sessionStorage.getItem('unionId');
        const conferenceId = sessionStorage.getItem('conferenceId');

        // 关闭会议
        axios({
            url: domain + '/meeting?conferenceId=' + conferenceId + '&unionId=' + unionId,
            method: 'put',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(function (response) {

            })
            .catch(function (error) {
                console.log(error);
            });
    };

    return (
        <div className="App">
            {/*<header className="App-header">*/}
            {/*<button onClick={createMeeting}>领用并提交审批</button>*/}
            {/*</header>*/}
            <header className="App-header">
                <button onClick={createMeeting}>发起会议</button>
                <button onClick={closeMeeting}>关闭会议</button>
            </header>
            {/*<div className="container">*/}
            {/*    <List/>*/}
            {/*</div>*/}
        </div>
    );
};


export default App;
