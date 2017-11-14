$('#queryBtn').click(function () {
    var choose = document.forms[0];
    choose.action = "/student/"+model.studentId+"/grade/choose";
    choose.method = "POST";
    choose.submit();
});

$('#print').click(function () {
    $('.toHide').hide();
    $('.main-footer').hide();
    $('.content-header').hide();
    window.print();
    $('.toHide').show();
    $('.main-footer').show();
    $('.content-header').show();
});