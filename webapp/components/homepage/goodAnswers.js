export const goodAnswers = {
    template:'<div class="good-answers">' +
        '<div class="good-item mb-6" v-for="(ans,index) in answers">' +
        '  <div class="ques-title good-ans-title" @click="toQues(ans.quesId)">' +
        '    {{ans.title}}' +
        '  </div> ' +
        '  <div class="good-answer-container">' +
        '    <div class="good-answer-user flex mt-2 mb-2">' +
        '      <div class="good-answer-avatar"><img id="ans-avatar" :src="ans.avatar"/></div>' +
        '      <div class="good-answer-us flex flex-col items-start justify-center ml-2">' +
        '        <div class="username flex">{{ans.username}}</div> ' +
        '        <div class="school">{{ans.school}}</div> ' +
        '      </div> ' +
        '    </div>' +
        '    <div class="ques-brief mb-2 ans-content">{{ans.content}}</div> ' +
        '    <div class="good" @click="goodLike(ans.answerId,index)">赞同 {{ans.good}}</div>' +
        '    <div class="divide-border mt-4"></div>' +
        '  </div> ' +
        '</div>' +
        '</div>',
    data:function (){
        return {
            answers:[]
        }
    },
    methods: {
        getGoodAnswers(){
            axios.post('goodAnswers',{
                userId:this.$store.state.user.userId
            }).then((res)=>{
                if(res.data.data==0){
                    this.answers = []
                }
                else{
                    this.answers = res.data.data;
                }
            })
        },
        toQues(id){
            this.$router.push('/question/'+id)
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
                this.getGoodAnswers()
            })
        },
    },
    mounted(){
        this.getGoodAnswers()
    }
}