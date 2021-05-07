<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <script src="js/vue.js"></script>
  <script src="js/axios.min.js"></script>
  <link rel="stylesheet" href="css/login.css">
  <title>逼乎--牛逼你就来</title>
</head>
<body>
<div id="app">
  <div class="logo">
    <img id="logo" src="https://pic2.zhimg.com/80/v2-f6b1f64a098b891b4ea1e3104b5b71f6_720w.png"/>
  </div>
  <div class="login">
    <div class="login-content">
      <div class="approach">
        <p>密码登录</p>
        <div class="rectangle"></div>
      </div>
      <div class="input-area">
        <div class="InputFlow">
          <input id="user" placeholder="手机号或邮箱">
          <span id="un-alert"></span>
        </div>
        <div class="InputFlow">
          <input ref="password" type="password" id="password" placeholder="密码">
          <span id="pd-alert"></span>
        </div>
        <div class="login-option"></div>
        <button id="login-btn" onclick="login()">
          登录
        </button>
      </div>
    </div>
    <div class="info">
      未注册手机验证后自动登录，注册即代表同意《逼乎协议》《隐私保护指引》
    </div>
  </div>
  <footer>
    物联网工程181 - 倪梓淇 - 学号：6112118010
  </footer>
</div>
</body>
<script>
  window.onload = ()=>{
      let token = window.localStorage.getItem("token")
      axios.get('login',{
          headers:{Authorization:token}
      }).then((res)=>{
          let state = res.data.success
          if (state == 1){
              location.href="${pageContext.request.contextPath}/"
          }
      })
  }header
  function alertInfo(alertEle,info) {
      alertEle.innerText = info
      alertEle.style.opacity = '1'
      setTimeout(()=>{alertEle.style.opacity='0'},"1000")
  }
  function login() {
      let un = document.getElementById("user")
      let pd = document.getElementById("password")
      let unalert = document.getElementById("un-alert")
      let pdalert = document.getElementById("pd-alert")
      if (un.value === '') {
        alertInfo(unalert,'用户名为空')
      }else if (pd.value === '') {
        alertInfo(pdalert,'密码为空')
      }else {
        axios.post('login',{
            user:un.value,
            password:pd.value
          }).then((res)=>{
            let token = res.data.token
            window.localStorage.setItem("token",token)
            axios.get('login',{
                headers:{Authorization:token}
            }).then((res)=>{
                let state = res.data.success
                if (state===1) {
                    location.href="${pageContext.request.contextPath}/";
                }else{
                    alertInfo(pdalert,'密码错误')
                }
            })
          }
        )
      }
  }
</script>
</html>