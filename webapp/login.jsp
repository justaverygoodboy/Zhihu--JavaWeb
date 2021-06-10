<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <script src="js/axios.min.js"></script>
  <script src="js/md5.js"></script>
  <link rel="stylesheet" href="css/login.css">
  <link ref="stylesheet" href="css/tailwind.min.css">
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
        <div class="way-choose p-active" id="login-way" onclick="LogRegSwitch(this)">
          <p>密码登录</p>
          <div class="rectangle active"></div>
        </div>
        <div class="way-choose" id="register-way" onclick="LogRegSwitch(this)">
          <p>注册</p>
          <div class="rectangle"></div>
        </div>
      </div>
      <div class="input-area">
        <div class="InputFlow">
          <input id="user" placeholder="手机号或邮箱">
          <span class="alert" id="un-alert"></span>
        </div>
        <div class="InputFlow">
          <input ref="password" type="password" id="password" placeholder="密码">
          <span class="alert" id="pd-alert"></span>
        </div>
        <div class="login-option"></div>
        <button id="login-btn" onclick="login()">
          登录
        </button>
      </div>
      <div class="register-area">
        <div class="InputFlow">
          <input id="r-username" placeholder="用户名">
          <span class="alert" id="ru-alert"></span>
        </div>
        <div class="InputFlow">
          <input id="r-phone" placeholder="手机号">
          <span class="alert" id="rp-alert"></span>
        </div>
        <div class="InputFlow">
          <input id="r-email" placeholder="邮箱">
          <span class="alert" id="re-alert"></span>
        </div>
        <div class="InputFlow">
          <input ref="password" type="password" id="r-password" placeholder="密码">
          <span class="alert" id="rpd-alert"></span>
        </div>
        <div class="InputFlow">
          <input id="r-school" placeholder="学校">
          <span class="alert" id="rs-alert"></span>
        </div>
        <div class="login-option"></div>
        <button id="register-btn" onclick="register()">
          注册
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
      console.log(token)
      if(token!==undefined||token!==null){
        axios.get('login',{
            headers:{Authorization:token}
        }).then((res)=>{
            let state = res.data.success
            if (state == 1){
                location.href="${pageContext.request.contextPath}/"
            }
        })
      }
  }
  function alertInfo(alertEle,info) {
      alertEle.innerText = info
      alertEle.style.opacity = '1'
      setTimeout(()=>{alertEle.style.opacity='0'},"1000")
  }
  function register() {
      let un = document.getElementById("r-username")
      let pd = document.getElementById("r-password")
      let school = document.getElementById("r-school")
      let phone = document.getElementById("r-phone")
      let email = document.getElementById("r-email")
      let ruAlert = document.getElementById("ru-alert")
      let reAlert = document.getElementById("re-alert")
      let rpAlert = document.getElementById("rp-alert")
      let rpdAlert = document.getElementById("rpd-alert")
      let rsAlert = document.getElementById("rs-alert")
      if (un.value === '')
          alertInfo(ruAlert,'用户名为空')
      else if (email.value === '')
          alertInfo(reAlert,'邮箱为空')
      else if (phone.value === '')
          alertInfo(rpAlert,'手机号为空')
      else if (pd.value === '')
          alertInfo(rpdAlert,'密码为空')
      else if (school.value === '')
          alertInfo(rsAlert,'学校为空')
      else {
          let password = md5(pd.value)
          axios.post('register',{
              username:un.value,
              password:password,
              email:email.value,
              phone:phone.value,
              school:school.value,
          }).then((res)=>{
              if(res.data.state==='success'){
                  window.alert("注册成功！")
                  LogRegSwitch(document.getElementById("login-way"))
              }else if(res.data.state==="p")
                  alertInfo(rpAlert,'手机号已被注册')
              else if(res.data.state==="e")
                  alertInfo(reAlert,'邮箱已被注册')
              else{
                  window.alert('出现错误！')
              }
          }).catch(err=>{
              window.alert('注册失败！请检查网络')
          })
      }
  }
  function LogRegSwitch(that){
      let lo = document.getElementsByClassName("input-area")[0]
      let re = document.getElementsByClassName("register-area")[0]
      if(that.id==='login-way'){
          let chooseBar = document.getElementById("register-way")
          let thatRec = document.querySelector("#login-way>.rectangle")
          let unRec = document.querySelector("#register-way>.rectangle")
          let classLists1 = thatRec.classList
          let classLists2 = unRec.classList
          lo.style.display = "block"
          re.style.display = "none"
          chooseBar.classList.remove("p-active")
          that.classList.add("p-active")
          if(classLists1.length!==2)
              thatRec.classList.add("active")
          if(classLists2.length==2)
              unRec.classList.remove("active")
      }
      if(that.id==='register-way'){
          let chooseBar = document.getElementById("login-way")
          let thatRec = document.querySelector("#register-way>.rectangle")
          let unRec = document.querySelector("#login-way>.rectangle")
          let classLists1 = thatRec.classList
          let classLists2 = unRec.classList
          re.style.display = "block"
          lo.style.display = "none"
          chooseBar.classList.remove("p-active")
          that.classList.add("p-active")
          if(classLists1.length!==2)
              thatRec.classList.add("active")
          if(classLists2.length==2)
              unRec.classList.remove("active")
      }
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
        let password = md5(pd.value)
        axios.post('login',{
            user:un.value,
            password:password
          }).then((res)=>{
            let token = res.data.token
            window.localStorage.setItem("token",token)
            axios.get('login',{
                headers:{Authorization:token}
            }).then((res)=>{
                let state = res.data.success
                if (state===1) {
                    location.href="${pageContext.request.contextPath}/"
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