var io = require('socket.io')() //소켓생성

io.listen(4600)  //
console.log("wait----")
io.on("connect", accept_sock ) //클라이언트 접속
function accept_sock( socket ) 
{
	console.log("client connect")
	socket.on("recv", function(data) {
		console.log("recv...")
		socket.emit("ssend", data.toString('base64') )//전송..
	} )
	socket.on("disconnect", function() {
		console.log("client disconnect")
		socket.disconnect()
	})
}

