(this.webpackJsonpfronted=this.webpackJsonpfronted||[]).push([[0],{271:function(t,e,n){"use strict";n.r(e);var i=n(9),o=n.n(i),a=n(27),s=n.n(a),c=(n(32),n(33),n(7)),r=n.n(c),d=n(6),u=n(3);var p=function(){var t,e,n,i,o;return r.a.get("/config?url="+encodeURIComponent("http://wanzq.vaiwan.com/index.html")).then((function(a){t=a.data.data.agentId,e=a.data.data.corpId,n=a.data.data.timeStamp,i=a.data.data.nonceStr,o=a.data.data.signature,d.config({agentId:t,corpId:e,timeStamp:n,nonceStr:i,signature:o,type:0,jsApiList:["runtime.info","biz.contact.choose","dd.biz.contact.complexPicker","device.notification.confirm","device.notification.alert","device.notification.prompt","biz.ding.post","biz.util.openLink"]}),d.error((function(t){alert("dd error: "+JSON.stringify(t))}))})).catch((function(t){alert(JSON.stringify(t))})),d.ready((function(){d.runtime.permission.requestAuthCode({corpId:e,onSuccess:function(t){r.a.get("/login?authCode="+t.code).then((function(t){sessionStorage.setItem("userId",t.data.data.userid),sessionStorage.setItem("unionId",t.data.data.unionid),sessionStorage.setItem("deptId",t.data.data.deptIdList[0])})).catch((function(t){alert(JSON.stringify(t))}))},onFail:function(t){alert(JSON.stringify(t))}})})),Object(u.jsx)("div",{className:"App",children:Object(u.jsxs)("header",{className:"App-header",children:[Object(u.jsx)("button",{onClick:function(){d.biz.contact.complexPicker({title:"\u6d4b\u8bd5\u6807\u9898",corpId:e,multiple:!0,limitTips:"\u8d85\u51fa\u4e86",maxUsers:1e3,pickedUsers:[],pickedDepartments:[],disabledUsers:[],disabledDepartments:[],requiredUsers:[],requiredDepartments:[],appId:t,responseUserOnly:!1,startWithDepartmentId:0,onSuccess:function(t){alert(JSON.stringify(t));var e=t.data.departments,n=t.data.users.map((function(t){return t.emplId})),i=(sessionStorage.getItem("userId"),sessionStorage.getItem("unionId")),o=(sessionStorage.getItem("deptId"),{userId:i,confTitle:"\u4f1a\u8baeDemo",inviteDeptIds:e,inviteUserIds:n});r()({url:"/meeting",method:"post",data:o,headers:{"Content-Type":"application/json"}}).then((function(t){console.log(t),sessionStorage.setItem("conferenceId",t.data.data.conferenceId)})).catch((function(t){console.log(t)}))},onFail:function(t){alert(JSON.stringify(t)),console.log(JSON.stringify(t))}})},children:"\u53d1\u8d77\u4f1a\u8bae"}),Object(u.jsx)("button",{onClick:function(){var t=sessionStorage.getItem("unionId"),e=sessionStorage.getItem("conferenceId");r()({url:"/meeting?conferenceId="+e+"&unionId="+t,method:"put",headers:{"Content-Type":"application/json"}}).then((function(t){})).catch((function(t){console.log(t)}))},children:"\u5173\u95ed\u4f1a\u8bae"})]})})},l=function(t){t&&t instanceof Function&&n.e(3).then(n.bind(null,272)).then((function(e){var n=e.getCLS,i=e.getFID,o=e.getFCP,a=e.getLCP,s=e.getTTFB;n(t),i(t),o(t),a(t),s(t)}))};s.a.render(Object(u.jsx)(o.a.StrictMode,{children:Object(u.jsx)(p,{})}),document.getElementById("root")),l()},32:function(t,e,n){},33:function(t,e,n){}},[[271,1,2]]]);
//# sourceMappingURL=main.35207d4e.chunk.js.map