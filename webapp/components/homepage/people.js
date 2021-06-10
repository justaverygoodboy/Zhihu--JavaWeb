let inputElement = null
export const people = {
    template:'<div class="people-container flex justify-center m-4 flex-col items-center">' +
        '  <div class="info-container rounded-sm shadow w-full flex flex-row items-center">  ' +
        '    <div class="user-avatar-editor ml-4">\n' +
        '      <div class="user-avatar cursor-pointer" @click="toGetImg">\n' +
        '        <img class="rounded big-avatar" :src="this.$store.state.user.avatar"/>\n' +
        '      </div>\n' +
        '    </div>' +
        '    <div class="name-school>"' +
        '    <div class="flex flex-col ml-6 mt-20">' +
        '      <div class="people-name">' +
        '        {{this.$store.state.user.username}}' +
        '      </div>' +
        '      <div class="people-school mt-2">' +
        '        {{this.$store.state.user.school}}' +
        '      </div>' +
        '    </div>' +
        '    </div> ' +
        '  </div>' +
        '  <div class="people-ques shadow w-full">' +
        '     哈哈，这里就写了个上传头像 -),其余的以后见' +
        '  </div> ' +
        '</div>',
    data:function (){
        return {
            upUrl:'',
        }
    },
    methods:{
        toGetImg() {
            if (inputElement === null) {
                inputElement = document.createElement('input')
                inputElement.setAttribute('type', 'file')
                inputElement.setAttribute('id','upImg')
                inputElement.style.display = 'none'
                if (window.addEventListener) {
                    inputElement.addEventListener('change', this.uploadFile, false)
                } else {
                    inputElement.attachEvent('onchange', this.uploadFile)
                }
                document.body.appendChild(inputElement)
            }
            inputElement.click()
        },
        uploadFile(el) {
            if (el && el.target && el.target.files && el.target.files.length > 0) {
                const files = el.target.files[0]
                if (files.type.indexOf('image') === -1) {
                    window.alert('请选择图片文件')
                } else {
                    const that = this;
                    const reader = new FileReader();
                    reader.readAsDataURL(el.target.files[0])
                    if(el.target.id === 'upImg'){
                        reader.onload = function() {
                            that.upUrl = this.result
                            console.log(that.upUrl)
                            axios.post('upload',{
                                userId: that.$store.state.user.userId,
                                avatar:that.upUrl
                            }).then((res)=>{
                                if(res.data.success===1){
                                    that.$store.commit('updateUserAvatar',that.upUrl)
                                }else{
                                    window.alert("上传失败！上传图片请勿过大！")
                                }
                            }).catch(err=>window.alert("上传失败！上传图片请勿过大！"))
                        }
                    }
                }
            }
        },
    },
}




