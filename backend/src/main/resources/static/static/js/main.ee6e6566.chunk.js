(this.webpackJsonpfronted=this.webpackJsonpfronted||[]).push([[0],{272:function(t,e,n){"use strict";n.r(e);var s=n(4),c=n.n(s),i=n(31),o=n.n(i),a=(n(36),n(37),n(7)),r=n.n(a),d=n(13),l=n(14),u=n(15),j=n(17),h=n(16),b=n(2),p={height:"60px",margin:"10px",padding:"10px",fontsize:"30px"},m=function(t){Object(j.a)(n,t);var e=Object(h.a)(n);function n(t){return Object(l.a)(this,n),e.call(this,t)}return Object(u.a)(n,[{key:"render",value:function(){return this.props.items.map((function(t){var e=t.formComponentValues[0].name,n=t.result,s=t.approverUserids[0];return Object(b.jsxs)("tr",{className:"text-center",children:[Object(b.jsx)("td",{children:e}),Object(b.jsx)("td",{children:n}),Object(b.jsx)("td",{children:s})]})}))}}]),n}(c.a.Component),O=function(t){Object(j.a)(n,t);var e=Object(h.a)(n);function n(t){var s;return Object(l.a)(this,n),(s=e.call(this,t)).goodsCollectionAndApprove=function(){var t={detailForms:[{textForms:[{name:"\u7269\u54c1\u540d\u79f0",value:"\u6d4b\u8bd5\u7269\u54c1\u9886\u7528-\u7535\u8111"},{name:"\u6570\u91cf",value:"3"}],name:"\u7269\u54c1\u660e\u7ec6"}],originatorUserId:sessionStorage.getItem("userId"),textForms:[{name:"\u7269\u54c1\u7528\u9014",value:"\u6d4b\u8bd5\u7269\u54c1\u9886\u7528"},{name:"\u9886\u7528\u8be6\u60c5",value:"\u9886\u7528\u8be6\u60c51"}],deptId:sessionStorage.getItem("deptId")};r()({url:f+"/process/instance",method:"post",data:t,headers:{"Content-Type":"application/json"}}).then((function(t){alert("success")})).catch((function(t){console.log(t)}))},s.getTableRowData=function(){r.a.get(f+"/process/instance").then((function(t){s.setState({items:t.data.result,isLoaded:!0})})).catch((function(t){alert(JSON.stringify(t))}))},s.state={items:[],isLoaded:!1},s}return Object(u.a)(n,[{key:"render",value:function(){return this.state.isLoaded?Object(b.jsxs)("div",{children:[Object(b.jsx)("button",{style:p,onClick:this.goodsCollectionAndApprove,children:"\u9886\u7528\u5e76\u63d0\u4ea4\u5ba1\u6279"}),Object(b.jsx)("button",{style:p,onClick:this.getTableRowData,children:"\u83b7\u53d6\u63d0\u4ea4\u7684\u5ba1\u6279\u4fe1\u606f"}),Object(b.jsxs)("table",{className:"table table-bordered",children:[Object(b.jsx)("thead",{children:Object(b.jsxs)("tr",{children:[Object(b.jsx)("th",{children:"\u7269\u54c1\u7528\u9014"}),Object(b.jsx)("th",{children:"\u5ba1\u6279\u72b6\u6001"}),Object(b.jsx)("th",{children:"\u5ba1\u6279\u4eba"})]})}),Object(b.jsx)("tbody",{children:Object(b.jsx)(m,{items:this.state.items})})]})]}):Object(b.jsxs)("div",{children:[Object(b.jsx)("button",{style:p,onClick:this.goodsCollectionAndApprove,children:"\u9886\u7528\u5e76\u63d0\u4ea4\u5ba1\u6279"}),Object(b.jsx)("button",{style:p,onClick:this.getTableRowData,children:"\u83b7\u53d6\u63d0\u4ea4\u7684\u5ba1\u6279\u4fe1\u606f"})]})}}]),n}(c.a.Component),f="http://wanzq.vaiwan.com";d.ready((function(){d.runtime.permission.requestAuthCode({corpId:"ding9f50b15bccd16741",onSuccess:function(t){r.a.get(f+"/login?authCode="+t.code).then((function(t){sessionStorage.setItem("userId",t.data.result.userid),sessionStorage.setItem("deptId",t.data.result.deptIdList[0])})).catch((function(t){alert(JSON.stringify(t))}))},onFail:function(t){alert(JSON.stringify(t))}})}));var g=function(){return Object(b.jsx)("div",{className:"App",children:Object(b.jsx)("div",{className:"container",children:Object(b.jsx)(O,{})})})},x=function(t){t&&t instanceof Function&&n.e(3).then(n.bind(null,273)).then((function(e){var n=e.getCLS,s=e.getFID,c=e.getFCP,i=e.getLCP,o=e.getTTFB;n(t),s(t),c(t),i(t),o(t)}))};o.a.render(Object(b.jsx)(c.a.StrictMode,{children:Object(b.jsx)(g,{})}),document.getElementById("root")),x()},36:function(t,e,n){},37:function(t,e,n){}},[[272,1,2]]]);
//# sourceMappingURL=main.ee6e6566.chunk.js.map