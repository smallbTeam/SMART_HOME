// document.write("<script language=javascript src='http://127.0.0.1:8080/js/jquery-1.10.2.min.js'></script>");
// document.write("<script language=javascript src='http://127.0.0.1:8080/layer/layer.js'></script>");
document.write("<script language=javascript src='http://cdn.sockjs.org/sockjs-0.3.min.js'></script>");
	var ws = null;
	var basePath = "ws://127.0.0.1:8080/";
	if ('WebSocket' in window) {
    	 ws = new WebSocket(basePath+'webSocketServer'); 
	} 
	else if ('MozWebSocket' in window) {
		ws = new MozWebSocket(basePath+"webSocketServer");
	} 
	else {
		ws = new SockJS(basePath+"sockjs/webSocketServer");
	}
    ws.onopen = function () {
        
    };
    ws.onmessage = function (event) {
    	pop(event.data);
    };
    ws.onclose = function (event) {
    	 ws.close();
    };
//提示信息
function pop(message){
	layer.alert(message);
}
