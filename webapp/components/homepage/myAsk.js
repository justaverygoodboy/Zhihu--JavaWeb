export const myAsk = {
    template: '<div v-if="this.$store.state.askQues" class="q-body">\n' +
        '<div class="form-wrapper shadow">\n' +
        '  <div id="ask-form" class="flex flex-col justify-center items-center relative ">\n' +
        '    <div class="ask-title flex justify-center">\n' +
        '      <input v-model="quesTitle" id="title-input" placeholder="写下你的问题，准确描述更容易得到解答"/>\n' +
        '    </div>\n' +
        '    <textarea v-model="quesDiscrip" id="discription" placeholder=" 输入对问题的描述等详细信息（选填）">\n' +
        '    </textarea>\n' +
        '    <button class="flex items-center justify-center" id="post" @click="postQues">\n' +
        '      发布问题\n' +
        '    </button>\n' +
        '    <button id="close" @click="closeQues">\n' +
        '      <svg fill="currentColor" viewBox="0 0 24 24" width="24" height="24">\n' +
        '        <path d="M13.486 12l5.208-5.207a1.048 1.048 0 0 0-.006-1.483 1.046 1.046 0 0 0-1.482-.005L12 10.514 6.793 5.305a1.048 1.048 0 0 0-1.483.005 1.046 1.046 0 0 0-.005 1.483L10.514 12l-5.208 5.207a1.048 1.048 0 0 0 .006 1.483 1.046 1.046 0 0 0 1.482.005L12 13.486l5.207 5.208a1.048 1.048 0 0 0 1.483-.006 1.046 1.046 0 0 0 .005-1.482L13.486 12z" fill-rule="evenodd"></path>\n' +
        '      </svg>\n' +
        '    </button>\n' +
        '  </div>\n' +
        '</div>\n' +
        '</div>\n',
    data: function (){
        return {
            quesTitle: '',
            quesDiscrip: '',
        }
    },
    methods:{
        closeQues(){
            this.$store.commit('updateAskQues',false)
        },
        postQues(){
            let title = this.quesTitle
            let discrip = this.quesDiscrip
            if (title !== ''){
                axios.post('postAsk',{
                    title:title,
                    brief:discrip
                },{
                    headers: {
                        'Authorization':this.$store.state.token
                    }}).then((res)=>{
                    if(res.data.success){
                        this.$store.dispatch('saveQues')
                        if(this.$router.currentRoute.fullPath !== '/') {
                            this.$router.push('/')
                        }
                    }else{
                        console.log("发布失败！")
                    }
                })
            }else{
                alert("标题不能为空")
            }
            this.closeQues()
        },
    }
}