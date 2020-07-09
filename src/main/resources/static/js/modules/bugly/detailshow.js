$().ready(function(){

     var serviceException = $("#serviceException").val();

     var newstr = serviceException.replace(/(Connection reset by peer)/g, "<font color=red>$1</font>")
         .replace(/(Caused by)/g, "<font color=red>$1</font>")
         .replace(/(com.bullyun)/g, "<font color=red>$1</font>")
         .replace(/(manniu)/g, "<font color=red>$1</font>")
         .replace(/(Exception)/g, "<font color=red>$1</font>");
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