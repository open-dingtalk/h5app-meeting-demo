(this.webpackJsonpfronted=this.webpackJsonpfronted||[]).push([[0],{271:function(e,t,n){"use strict";n.r(t);var s=n(7),o=n.n(s),i=n(27),r=n.n(i),c=(n(32),n(33),n(8)),a=n.n(c),d=n(9),u=n(3);d.ready((function(){var e;fetch("/config").then((function(e){return e.json()})).then((function(t){e=t.result.corpId,d.runtime.permission.requestAuthCode({corpId:e,onSuccess:function(e){a.a.get("/login?authCode="+e.code).then((function(e){sessionStorage.setItem("userId",e.data.result.userid),sessionStorage.setItem("unionId",e.data.result.unionid),sessionStorage.setItem("deptId",e.data.result.deptIdList[0])})).catch((function(e){alert(JSON.stringify(e))}))},onFail:function(e){alert(JSON.stringify(e))}})}))}));var l=function(){return Object(u.jsx)("div",{className:"App",children:Object(u.jsxs)("header",{className:"App-header",children:[Object(u.jsx)("button",{onClick:function(){d.biz.contact.complexPicker({title:"\u6d4b\u8bd5\u6807\u9898",corpId:"xxx",multiple:!0,limitTips:"\u8d85\u51fa\u4e86",maxUsers:1e3,pickedUsers:[],pickedDepartments:[],disabledUsers:[],disabledDepartments:[],requiredUsers:[],requiredDepartments:[],appId:158,permissionType:"xxx",responseUserOnly:!1,startWithDepartmentId:0,onSuccess:function(e){alert(JSON.stringify(e));var t=e.result.departments,n=e.result.users.map((function(e){return e.emplId})),s=(sessionStorage.getItem("userId"),sessionStorage.getItem("unionId")),o=(sessionStorage.getItem("deptId"),{userId:s,confTitle:"\u4f1a\u8baeDemo",inviteDeptIds:t,inviteUserIds:n});a()({url:"/meeting",method:"post",data:o,headers:{"Content-Type":"application/json"}}).then((function(e){console.log(e),sessionStorage.setItem("conferenceId",e.data.result.conferenceId)})).catch((function(e){console.log(e)}))},onFail:function(e){}})},children:"\u53d1\u8d77\u4f1a\u8bae"}),Object(u.jsx)("button",{onClick:function(){var e=sessionStorage.getItem("unionId"),t=sessionStorage.getItem("conferenceId");a()({url:"/meeting?conferenceId="+t+"&unionId="+e,method:"put",headers:{"Content-Type":"application/json"}}).then((function(e){})).catch((function(e){console.log(e)}))},children:"\u5173\u95ed\u4f1a\u8bae"})]})})},p=function(e){e&&e instanceof Function&&n.e(3).then(n.bind(null,272)).then((function(t){var n=t.getCLS,s=t.getFID,o=t.getFCP,i=t.getLCP,r=t.getTTFB;n(e),s(e),o(e),i(e),r(e)}))};r.a.render(Object(u.jsx)(o.a.StrictMode,{children:Object(u.jsx)(l,{})}),document.getElementById("root")),p()},32:function(e,t,n){},33:function(e,t,n){}},[[271,1,2]]]);
//# sourceMappingURL=main.6e906e91.chunk.js.map