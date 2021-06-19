<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <script src="js/vue.js"></script>
  <script src="https://cdn.bootcss.com/vuex/3.0.1/vuex.min.js"></script>
  <script src="https://unpkg.com/vue-router/dist/vue-router.js"></script>
  <script src="js/axios.min.js"></script>
  <link href="css/tailwind.min.css" rel="stylesheet">
  <link href="css/index.css" rel="stylesheet">
  <link href="css/header.css" rel="stylesheet">
  <link href="css/main.css" rel="stylesheet">
  <link href="css/ask.css" rel="stylesheet">
  <link href="css/question.css" rel="stylesheet">
  <link href="css/people.css" rel="stylesheet">
  <link href="css/goodAnswers.css" rel="stylesheet">
  <title>逼乎</title>
</head>
<body>
<div id="app" class="flex flex-col items-center">
  <my-header></my-header>
  <router-view></router-view>
</div>
</body>
<script type="module">
  import {myHeader} from './components/homepage/myHeader.js'
  import {routes} from "./router/router.js";
  const router = new VueRouter({
      routes
  })
  const store = new Vuex.Store({
      state:{
          questions: [],
          token: '',
          user: {},
          askQues: false,
          noRes: false,
          backupQues: []
      },
      mutations: {
          updateQues(state,ques){
              state.questions = ques
          },
          updateToken(state,token){
              state.token = token
          },
          updateUser(state,user){
              state.user = user
          },
          updateAskQues(state,val){
              state.askQues = val
          },
          setResState(state,val){
              state.noRes = val
          },
          setBackQues(state,ques){
              state.backupQues = ques
          },
          updateUserAvatar(state,avatar){
              state.user.avatar = avatar;
          }
      },
      actions: {
          saveQues(context){
              axios.get('questions').then(res=>{
                context.commit('updateQues',res.data)
              })
          }
      }
  })
  var vue = new Vue({
      el:'#app',
      router,
      store: store,
      components: {
          myHeader,
      },
      methods:{
          getQues(){
              this.$store.dispatch('saveQues')
          },
          getToken(){
              let token = window.localStorage.getItem("token")
              if(token === undefined){
                  window.localStorage.clear();
                  location.href="${pageContext.request.contextPath}/login.jsp"
              }
              else
                  this.$store.commit('updateToken',token)
          },
          getUser(){
              axios.get('user',{
                  headers:{
                      'Authorization':this.$store.state.token
                  }}).then((res)=>{
                      if(res.data.valid=="fail"){
                          location.href="${pageContext.request.contextPath}/login.jsp"
                      }
                      else{
                          this.$store.commit('updateUser',res.data)
                      }
              })
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