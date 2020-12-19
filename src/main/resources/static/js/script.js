var fun1 = function(name) {
    print('자바로부터 전달받았습니다. 이곳은 자바스크립트입니다., ' + name);
    
    var a = {
    	"no" : "1",
    	"title" : "제목입니다.",
    	"content" :"내용입니다.",
    	"jsToJava" :"자바스크립트에서 자바로 보내기"
    }
    return a;
};



var fun2 = function (object) {
    print("JS Class Definition: " + Object.prototype.toString.call(object));
};