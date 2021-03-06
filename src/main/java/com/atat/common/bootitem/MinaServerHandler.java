package com.atat.common.bootitem;

import java.util.*;

import com.atat.common.util.JsonUtil;
import com.atat.device.config.SystemWebSocketHandler;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import static com.atat.common.bootitem.MinaUtil.*;

public class MinaServerHandler extends IoHandlerAdapter {
//	public static List<HardWare>  sessionMap = new ArrayList<HardWare>();
	private static Map<IoSession,HardWare> sessionMap = new HashMap<IoSession,HardWare>();
	//	private int state = 0;

	///////////////根据返回值直接发送ok
	public static void sendMessage(String msg,IoSession number) {
		//System.out.println("发送数据:====>"+msg);

		byte[] bar = msg.getBytes();
		IoBuffer buffers = IoBuffer.allocate(bar.length);
		buffers.put(bar, 0, bar.length);
		buffers.flip();
		number.write(buffers);

	}
	
	public void checkSessionMsg(){
		Iterator it = sessionMap.keySet().iterator();
		while (it.hasNext()) {
			IoSession session = (IoSession) it.next();//Session
			//String value = JsonUtil.toJson((Object) sessionMap.get(key));//
			sendMessage("OK\\\r\\\n", session);
		}
	}


	@Override
	public void sessionCreated(IoSession session) throws Exception {

	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {

		sessionMap.put(session,new HardWare(0));
	}


	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {

		/////收到数据就会返回ok\r\n
		sendMessage("OK\\\r\\\n", session);

		HardWare hd = new HardWare();
		IoBuffer ioBuffer = (IoBuffer)message;
		byte[] b = new byte [ioBuffer.limit()];
		ioBuffer.get(b);
		String stringmsg = new String(b);
		String [] str = stringmsg.split("\\|");

		if(str[1].equals("KQSJ")){
			//////将数据转换为map
			List<Map<String,Object>> map =  InPutMessageToMap(str);
//			hd = sessionMap.get(session);
			SystemWebSocketHandler.sendMessage(map.get(0));
//			sendEnviTosql(map.get(0));

			/////如果当前session中网关和之前网关名称一样则继续执行
			/////否则上传数据，并重新计算数据
//			if (hd.getNumber()==null){
//				hd.setNumber(str[0]);
//			}
//			if(hd.getNumber().equals(str[0])){
//				////////数据存储次数+1
//				int cony = hd.getCount()+1;
//				System.out.println("传值次数------> "+cony);
//				////////如果是第一次传值则发送到数据库
//				if(cony==1){
//					System.out.println("第一次传值发送数据");
//					sendEnviTosql(map);
//				}
//				//////如果数据存储次数到达60次则重置
//				if(cony>60){
//					System.out.println("重置");
//					hd.setCount(0);
//				}else{
//					hd.setCount(cony);
//				}
//				sessionMap.put(session,hd);
//			}else{
//				hd.setCount(1);
//				hd.setNumber(str[0]);
//				sendEnviTosql(map);
//				System.out.println("网关信息不通发送数据");
//			}
		}else if(str[1].equals("KQTS")){
			/////向后台发送数据
			Map map = new HashMap<String, Object>();
			map = InPutMessageToString(str);
			sendEnviTosql(map);
			AlarmPush(str);
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {


		sessionMap.remove(session);

		System.out.println("关闭session");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		System.out.println("服务端发送异常..."+cause);

	}

}

