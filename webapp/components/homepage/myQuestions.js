export const myQuestions = {
    template: '<main class="flex justify-center">\n' +
        '  <div class="main-content">\n' +
        '    <div class="question-item" v-for="(ques,index) in this.$store.state.questions" :key="index">\n' +
        '      <div class="item">\n' +
        '        <div @click="toQues(ques.quesId)" class="ques-title">{{ques.title}}</div>\n' +
        '        <div class="ques-brief">{{ques.brief}}</div>\n' +
        '      </div>\n' +
        '    </div>\n' +
        '    <div class="flex flex-col" id="no-ques" v-if="this.$store.state.noRes">\n' +
        '      <p class="search-warning mt-4">未搜索到相关内容，提问快速获得回答</p>\n' +
        '      <div class="flex justify-center items-center mb-2">' +
        '      <button class="back-search flex items-center justify-center" @click="backSearch">\n' +
        '        <div>返回主页</div>\n' +
        '      </button>\n' +
        '      </div>' +
        '    </div>\n' +
        '  </div>\n' +
        '</main>',
    methods: {
        backSearch(){
            this.$store.commit('updateQues',this.$store.state.backupQues)
            this.$store.commit('setResState',false)
        },
        toQues(id){
            this.$router.push('/question/'+id)
        },
    }
}