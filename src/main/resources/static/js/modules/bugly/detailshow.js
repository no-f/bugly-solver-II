$().ready(function(){

     var serviceException = $("#serviceException").val();

     var newstr = serviceException.replace(/(Connection reset by peer)/g, "<font color=red>$1</font>")
         .replace(/(Caused by)/g, "<font color=red>$1</font>")
         .replace(/(com.bullyun)/g, "<font color=red>$1</font>")
         .replace(/(manniu)/g, "<font color=red>$1</font>")
         .replace(/(Exception)/g, "<font color=red>$1</font>")
         .replace(/(信息:)/g, "<strong>信息: </strong>")
         .replace(/(异常：)/g, "<br><br> <strong>异常: </strong>");
     $('#exception').html(newstr);

    var vm = new Vue({
        el: '#app',
        data: {
            tableData: [],
            total: 50,
            page_size: 5,
            current_page: 1
        }
    });
});