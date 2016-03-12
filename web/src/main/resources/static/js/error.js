function processErrorCode( data) {
    if (typeof(data) != "object"){
        data = eval("(" + data + ")");
    }
    if(data.result_code == 11120202){
        toastr.error(data.result_msg);
        window.history.back();
    }
    if(data.result_code == 11120103){
        toastr.error(data.result_msg);
        location.href ="/login.html";
    }
}