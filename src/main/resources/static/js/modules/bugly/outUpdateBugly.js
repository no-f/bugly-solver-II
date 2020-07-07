
var app = new Vue({
    el: '#app',
    data:{
    exceptionTypeId:"",
    state:"",
    remark:""


    },
    methods:{

    outDealBug :function(){

                     var outDealBug = {
                         'id':$('#to_exceptionTypeId').val(),
                         'state':Number($('#state option:selected') .val()),
                         'nickName':"admin",
                         'tag':update_deal_bug.tag.value,
                         'remark':update_deal_bug.remark.value
                     };

                     $.ajax({
                         cache : true,
                         type : "POST",
                         url : context + 'exception/deal_with',
                         data :JSON.stringify(outDealBug),
                         dataType : 'json',
                         contentType:'application/json',
                         error : function(request) {
                               console.log(request);
                         },
                         success : function(data) {
                         var str = "";
                             if (data.code === 200) {
                                 if (data.data.code === 200){
                                     str = "<font color=green>操作成功</font>"
                                     $('#result').html(str);
                                 } else if(data.data.code === 200){
                                      str = "<font color=red>操作失败</font>"
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
