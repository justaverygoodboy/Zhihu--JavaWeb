<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <script src="js/vue.js"></script>
  <script src="js/axios.min.js"></script>
  <link href="css/tailwind.min.css" rel="stylesheet">
  <title>逼乎</title>
</head>
<body>
<div id="app">
  <jsp:include page="components/homepage/header.jsp" flush="true" />
  <jsp:include page="components/homepage/main.jsp" flush="true"/>
</div>
</body>
<script>
  var vue = new Vue({
      el:'#app',
      data: {
          questions: [],
          token: '',
          user: {},
          menuShow: false,
          quesTitle: '',
          quesDiscrip: '',
          ques: false,
      },
      methods:{
          getQues(){
              axios.get('questions').then((res)=>{
                  this.questions = res.data
                  console.log(res)
              })
          },
          getToken(){
              let token = window.localStorage.getItem("token")
              this.token = token
              if(token == null){
                  location.href="${pageContext.request.contextPath}/login.jsp";
              }
          },
          getUser(){
              axios.get('user',{
                  headers:{
                      'Authorization':this.token
                  }}).then((res)=>{
                  this.user=res.data
                  window.localStorage.setItem("avatar",res.data.avatar)
                  console.log(window.localStorage.getItem("avatar"))
              })
          },
          showMenu(){
            this.menuShow = true
          },
          closeMenu(){
            this.menuShow = false
          },
          ask(){
            this.ques = true
          },
          postQues(){
              let title = this.quesTitle
              let discrip = this.quesDiscrip
              if (title !== ''){
                  let token = this.token
                  axios.post('postAsk',{
                      title:title,
                      brief:discrip
                      },{
                      headers: {
                        'Authorization':token
                      }}).then((res)=>{
                      if(res.data.success){
                          this.getQues()
                      }else{
                          console.log("发布失败！")
                      }
                  })
              }else{
                  alert("标题不能为空")
              }
              this.closeQues()
          },
          closeQues(){
            this.ques = false
          },
          toQues(id){
              location.href="${pageContext.request.contextPath}/question.jsp?quesId="+id.toString();
          },
          logout(){
              window.localStorage.clear();
              location.href="${pageContext.request.contextPath}/login.jsp";
          }
      },
      mounted:function () {
        this.getToken()
        this.getUser()
        this.getQues()
      }
  })
</script>
</html>