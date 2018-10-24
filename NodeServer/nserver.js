var io = require('socket.io')() //소켓생성

io.listen(4600)  //
console.log("wait")
io.on("connect", accept_sock ) //클라이언트 접속
function accept_sock( socket ) 
{
	console.log("client connect")
	socket.on("join", function(data) {
		//data(방이름)
		console.log("방이름:",data)
		socket.join(data) //특정방에 join...
	})
	socket.on("recv", function(data) {
		room = data.room
		chat = data.chat
		console.log("recv", room, chat)
		io.to( room ).emit("ssend", chat )
		//console.log("recv", data.name, data.age)
		//socket.emit("ssend", data)//전송..
		//io.sockets.emit("ssend", data)
	} )
	socket.on("disconnect", function() {
		console.log("client disconnect")
		socket.disconnect()
	})
}




//function fn()
//{
//	a = 10
//	console.log("fncall", a)
//}
//
//console.log('hello')
//console.log('hello')
//fn();
