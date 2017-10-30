var myapp;

(function() {
    myapp = {
        init: function () {
            $.notify("init");
        },
    };
    var loader = function(){
        jQuery.myapp = myapp;
        myapp.init();
    };
    var ali = setInterval(function(){
        if (typeof jQuery !== 'function') return;
        clearInterval(ali);
        setTimeout(loader, 0);
    }, 50);
})();
