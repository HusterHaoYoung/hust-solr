$(".nav li").click(function(){
	var index = $(".nav li").index();
	$(this).eq(index).addClass("ons").siblings().removeClass("ons");
	console.log(this);
})
console.log($(".lis"));
function wordlimit(cname, wordlength) {
    var cname = document.getElementsByClassName(cname);
    for (var i = 0; i < cname.length; i++) {　　　　　　
        var nowLength = cname[i].innerHTML.length;
        if (nowLength > wordlength) {
            cname[i].innerHTML = cname[i].innerHTML.substr(0, wordlength) + '...';
        }　　　　　　
    }　
};
wordlimit('description', 268);
wordlimit('con', 300);