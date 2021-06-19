export const questionDetail = {
    template: '<div id="ques-container">\n' +
        '  <div class="question flex items-center justify-center flex-col">\n' +
        '    <div class="wrp">\n' +
        '      <div class="title">\n' +
        '        {{quesTitle}}\n' +
        '      </div>\n' +
        '      <div class="brief">\n' +
        '        {{quesBrief}}\n' +
        '      </div>\n' +
        '      <div @click="writeAns" class="write_ans">\n' +
        '        写回答\n' +
        '      </div>\n' +
        '    </div>\n' +
        '  </div>\n' +
        '  <div class="answers flex flex-col items-center">\n' +
        '    <div v-if="write" class="write-area answer">\n' +
        '      <div class="ans-wrp flex flex-col justify-center">\n' +
        '        <textarea v-model="content" id="ans-content" placeholder="写回答..."></textarea>\n' +
        '        <div @click="postAns" class="sendAns">\n' +
        '          发布回答\n' +
        '        </div>\n' +
        '        <button id="close" @click="closeWrite">\n' +
        '          <svg fill="currentColor" viewBox="0 0 24 24" width="24" height="24">\n' +
        '            <path d="M13.486 12l5.208-5.207a1.048 1.048 0 0 0-.006-1.483 1.046 1.046 0 0 0-1.482-.005L12 10.514 6.793 5.305a1.048 1.048 0 0 0-1.483.005 1.046 1.046 0 0 0-.005 1.483L10.514 12l-5.208 5.207a1.048 1.048 0 0 0 .006 1.483 1.046 1.046 0 0 0 1.482.005L12 13.486l5.207 5.208a1.048 1.048 0 0 0 1.483-.006 1.046 1.046 0 0 0 .005-1.482L13.486 12z" fill-rule="evenodd"></path>\n' +
        '          </svg>\n' +
        '        </button>\n' +
        '      </div>\n' +
        '    </div>\n' +
        '    <div class="answer" v-for="(ans,index) in answers" :key="index">\n' +
        '      <div class="a-wrp">\n' +
        '        <div class="user flex">\n' +
        '          <img class="avatar" :src="ans.avatar">\n' +
        '          <div class="name-school flex flex-col justify-center items-start">\n' +
        '            <div class="username">{{ans.username}}</div>\n' +
        '            <div class="school">{{ans.school}}</div>\n' +
        '          </div>\n' +
        '        </div>\n' +
        '        <div class="content">\n' +
        '          {{ans.content}}\n' +
        '        </div>\n' +
        '        <div class="time">发布于 {{ans.timestamp}}</div>\n' +
        '        <div class="good" @click="goodLike(ans.answerId,index)">赞同 {{ans.good}}</div>\n' +
        '      </div>\n' +
        '    </div>\n' +
        '    <div id="end">没有更多回答了</div>\n' +
        '  </div>\n' +
        '</div>',
    data: function (){
        return {
            quesTitle:'',
            quesBrief: '',
            menuShow: false,
            ques: false,
            answers:[],
            content:'',
            write: false
        }
    },
    methods:{
        getDetail() {
            let quesId = this.$route.params.quesId
            axios.post('detail', {
                quesId: quesId
            }).then((res) => {
                this.quesTitle = res.data.title
                this.quesBrief = res.data.brief
                this.answers = res.data.answers
            })
        },
        writeAns() {
            this.write = true
        },
        closeWrite() {
            this.write = false
        },
        postAns(){
            let quesId = this.$route.params.quesId
            if(this.content!==''){
                axios.post('write',{
                    quesId:quesId,
                    content: this.content
                },{
                    headers:{
                        'Authorization':this.$store.state.token
                    }}
                ).then((res)=>{
                    if(res.data.success==1) {
                        this.write = false
                        this.getDetail()
                    }else if(res.data.success==-1){
                        window.alert('回答失败')
                    }else{
                        location.href="/bihu_war_exploded/login.jsp";
                    }
                })
            }
        },
        goodLike:function(answerId,index){
            axios.post('like',{
                answerId:answerId,
                userId:this.$store.state.user.userId
            }).then((res)=>{
                if(res.data.success === 1){
                    let value = this.answers[index].good
                    if(typeof(value)==="string"){
                        value = parseInt(value)
                    }
                    value += 1
                    this.answers[index].good=value
                }else if(res.data.success === -1) {
                    let value = this.answers[index].good
                    if(typeof(value)==="string"){
                        value = parseInt(value)
                    }
                    value -= 1
                    this.answers[index].good=value
                }else
                    window.alert('服务器发生错误')
            })
        },
    },
    mounted:function () {
        this.getDetail()
    }
}