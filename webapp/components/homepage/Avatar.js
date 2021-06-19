let inputElement = null
export const Avatar = {
    template: '<div class="user-avatar-editor ml-4">' +
        '<div class="user-avatar cursor-pointer" @click="toGetImg">' +
        '<img class="rounded big-avatar" :src="this.$store.state.user.avatar"/>' +
        '</div>' +
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