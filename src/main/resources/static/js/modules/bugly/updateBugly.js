
var app = new Vue({
    el: '#app',
    data:{
    exceptionTypeId:"",
    state:"",
    nickName:"",
    remark:""


    },
    methods:{


    updateBugly :function(){
                     var updateBugly = {
                         'id':update_bugly.exceptionTypeId.value,
                         'state':update_bugly.state.value,
                         'nickName':update_bugly.nickName.value,
                         'tag':update_bugly.tag.value,
                         'remark':update_bugly.remark.value
                     };

                     $.ajax({
                         cache : true,
                         type : "POST",
                         url : context + 'exception/deal_with',
                         data :JSON.stringify(updateBugly),
                         dataType : 'json',
                         contentType:'application/json',
                         error : function(request) {
                             parent.layer.alert("Connection error");
                         },
                         success : function(data) {
                         var index = parent.layer.getFrameIndex(window.name);
                             if (data.code === 200) {
                                 if (data.data.code === 200){
                                     parent.layer.msg("操作成功");
                                 } else if(data.data.code === 200){
                                     parent.layer.msg("操作失败");
                                 }
                                 var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                                 parent.layer.close(index);
                             }
                         }
                     });

                 }

    },
    mounted:function () {

    }
});
