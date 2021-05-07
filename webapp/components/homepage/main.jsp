<%@ page pageEncoding="utf-8"%>
<link rel="stylesheet" href="css/main.css">
<main class="flex justify-center shadow">
  <div class="main-content">
    <div class="question-item" v-for="(ques,index) in questions" :key="index">
      <div class="item">
        <div @click="toQues(ques.quesId)" class="ques-title">{{ques.title}}</div>
        <div class="ques-brief">{{ques.brief}}</div>
      </div>
    </div>
  </div>
</main>

