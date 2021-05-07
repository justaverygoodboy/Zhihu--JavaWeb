<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="js/vue.js"></script>
    <script src="js/axios.min.js"></script>
    <link href="./css/tailwind.min.css" rel="stylesheet">
    <link href="./css/question.css" rel="stylesheet">
    <title>问题</title>
</head>
<body>
<jsp:include page="components/homepage/header2.jsp"/>
<div id="ques-container">
  <div class="question flex items-center justify-center flex-col">
    <div class="wrp">
      <div class="title">
        {{quesTitle}}
      </div>
      <div class="brief">
        {{quesBrief}}
      </div>
      <div @click="writeAns" class="write_ans">
        写回答
      </div>
    </div>
  </div>
  <div class="answers flex flex-col items-center">
    <div v-if="write" class="write-area answer">
      <div class="ans-wrp flex flex-col justify-center">
        <textarea v-model="content" id="ans-content" placeholder="写回答..."></textarea>
        <div @click="postAns" class="sendAns">
          发布回答
        </div>
        <button id="close" @click="closeWrite">
          <svg fill="currentColor" viewBox="0 0 24 24" width="24" height="24">
            <path d="M13.486 12l5.208-5.207a1.048 1.048 0 0 0-.006-1.483 1.046 1.046 0 0 0-1.482-.005L12 10.514 6.793 5.305a1.048 1.048 0 0 0-1.483.005 1.046 1.046 0 0 0-.005 1.483L10.514 12l-5.208 5.207a1.048 1.048 0 0 0 .006 1.483 1.046 1.046 0 0 0 1.482.005L12 13.486l5.207 5.208a1.048 1.048 0 0 0 1.483-.006 1.046 1.046 0 0 0 .005-1.482L13.486 12z" fill-rule="evenodd"></path>
          </svg>
        </button>
      </div>
    </div>
    <div class="answer" v-for="(ans,index) in answers" :key="index">
      <div class="a-wrp">
        <div class="user flex">
          <img class="avatar" :src="ans.avatar">
          <div class="name-school flex flex-col justify-center items-start">
            <div class="username">{{ans.username}}</div>
            <div class="school">{{ans.school}}</div>
          </div>
        </div>
        <div class="content">
          {{ans.content}}
        </div>
        <div class="time">发布于 {{ans.timestamp}}</div>
        <div class="good">赞同 {{ans.good}}</div>
      </div>
    </div>
    <div id="end">没有更多回答了</div>
  </div>
</div>
</body>
</html>
<script>
var ques = new Vue({
    el:'#ques-container',
    data: {
        quesTitle:'',
        quesBrief: '',
        menuShow: false,
        ques: false,
        answers:[],
        content:'',
        avatar:'',
        write: false
    },
    methods:{
        getDetail: function () {
            let quesId = window.location.search.substr(8)
            this.avatar = window.localStorage.getItem("avatar")
            window.document.getElementById("avatar").src=this.avatar
            axios.post('detail', {
                quesId: quesId
            }).then((res) => {
                this.quesTitle = res.data.title
                this.quesBrief = res.data.brief
                this.answers = res.data.answers
                console.log(res.data)
            })
        },
        writeAns: function () {
            this.write = true
        },
        closeWrite: function () {
            console.log("close")
            this.write = false
        },
        postAns:function (){
            let quesId = window.location.search.substr(8)
            let token = window.localStorage.getItem("token")
            if(this.content!==''){
                axios.post('write',{
                    quesId:quesId,
                    content: this.content
                },{
                    headers:{
                        'Authorization':token
                    }}
                ).then((res)=>{
                    if(res.data.success) {
                        this.write = false
                        this.getDetail()
                    }
                })
            }
        }

    },
    mounted:function () {
        this.getDetail()
    }
}
)
</script>
