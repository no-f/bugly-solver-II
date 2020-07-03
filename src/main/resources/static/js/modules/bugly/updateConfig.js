var app = new Vue({
    el: '#app',
    data:{

    },
    methods:{
        getAllUserName:function(nickname) {
            $.ajax({
                cache : true,
                type : "GET",
                url : context + 'user/getAllUserName',
                error : function(request) {
                    parent.layer.alert("Connection error");
                },
                success : function(data) {
                    if (data.code === 200) {
                        $("#users").html("");
                        var level = "";
                        level += "<div class='layui-input-inline'>";
                        level += "<select id='users' name='modules' lay-verify='required' lay-search=''style='width: 235px;height: 33.9px;border: 1px solid #ccc;border-radius: 4px;'>";
                        for (var i = 0; i < data.data.allUserNickname.length; i++){
                            level += "<option value='"+data.data.allUserNickname[i]+"'>"+data.data.allUserNickname[i]+"</option>";
                        }
                        level += "</select></div>";
                        $("#users").append(level);
                        $("#users option[value='"+nickname+"']").attr("selected","selected");

                    }
                }
            });
        },
        updateConfig :function(){
                             var updateConfigParam = {
                                 'id':$('#id').val(),
                                 'nickname':$('#users option:selected').val(),
                                 'webhookUrl':$('#webhookUrl').val()
                             };

                             console.log("test"+JSON.stringify(updateConfigParam))

                             $.ajax({
                                 cache : true,
                                 type : "POST",
                                 url : context + 'exception/update_alarm_config',
                                 data :JSON.stringify(updateConfigParam),
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
        this.getAllUserName($("#nickname").val());
    }
});
