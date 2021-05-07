<%@ page pageEncoding="utf-8"%>
<link rel="stylesheet" href="css/ask.css">
<div v-if="ques" class="q-body">
<div class="form-wrapper shadow">
  <div id="ask-form" class="flex flex-col justify-center items-center relative ">
    <div class="title flex justify-center">
      <input v-model="quesTitle" id="title-input" placeholder="写下你的问题，准确描述更容易得到解答"/>
    </div>
    <textarea v-model="quesDiscrip" id="discription" placeholder=" 输入对问题的描述等详细信息（选填）">
    </textarea>
    <button class="flex items-center justify-center" id="post" @click="postQues">
      发布问题
    </button>
    <button id="close" @click="closeQues">
      <svg fill="currentColor" viewBox="0 0 24 24" width="24" height="24">
        <path d="M13.486 12l5.208-5.207a1.048 1.048 0 0 0-.006-1.483 1.046 1.046 0 0 0-1.482-.005L12 10.514 6.793 5.305a1.048 1.048 0 0 0-1.483.005 1.046 1.046 0 0 0-.005 1.483L10.514 12l-5.208 5.207a1.048 1.048 0 0 0 .006 1.483 1.046 1.046 0 0 0 1.482.005L12 13.486l5.207 5.208a1.048 1.048 0 0 0 1.483-.006 1.046 1.046 0 0 0 .005-1.482L13.486 12z" fill-rule="evenodd"></path>
      </svg>
    </button>
  </div>
</div>
</div>

