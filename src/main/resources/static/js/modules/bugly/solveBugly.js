
var app = new Vue({
    el: '#app',
    data:{
     exceptionTypeId:"",
     state:"",
     remark:""

    },
    methods:{
    solveBug :function(){

                      var solveBug = {
                           'id':$('#to_exceptionTypeId').val(),
                           'state':1,
                           'nickName':"admin",
                           'tag':" ",
                           'remark':" "
                      };

                      $.ajax({
                          cache : true,
                          type : "POST",
                          url : context + 'exception/deal_with',
                          data :JSON.stringify(solveBug),
                          dataType : 'json',
                          contentType:'application/json',
                          error : function(request) {
                                console.log(request);
                          },
                          success : function(data) {
                          var str = "";
                              if (data.code === 200) {
                                  if (data.data.code === 200){
                                      str = "<font color=green>解决成功</font>"
                                      $('#result').html(str);
                                  } else if(data.data.code === 500){
                                       str = "<font color=red>解决失败</font>"
                                       $('#result').html(str);
                                  }

                              }
                          }
                      });

                  }

    },
    mounted:function () {

    }
});
