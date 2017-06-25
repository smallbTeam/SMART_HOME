package com.atat.common.bootitem;

import java.util.ArrayList;
import java.util.List;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaServerHandler extends IoHandlerAdapter {
	public static Logger logger = LoggerFactory.getLogger(MinaServerHandler.class);
	public static List<HardWare>  list = new ArrayList<HardWare>();
	private int state = 0;

	public static void sendMessage(int i,String msg){
		logger.info("sendMessage-listCount::"+list.size());
			try {
				int j = 0;
				for (HardWare handware : list) {
					if (handware.getNumber() == i) {
						System.out.println("jjjjj:" + j);
						IoSession session = list.get(j).getSession();
						System.out.println("msg:" + msg);
						if (msg.equals("off")) {
							list.remove(j);
							//SystemWebSocketHandler.sendMessage(-2, list.size() + "_" + j);
						} else {
							String mess[] = msg.split(" ");
							byte[] bar = new byte[mess.length];
							for (int z = 0; z < mess.length; z++) {
								bar[z] = (byte) Integer.parseInt(mess[z], 16);
							}
							IoBuffer buffers = IoBuffer.allocate(bar.length);
							buffers.put(bar, 0, bar.length);
							buffers.flip();
							session.write(buffers);
						}
					}
					j += 1;
				}
			}catch (Exception e){
				System.out.println(e);
			}
	}


	@Override
	public void sessionCreated(IoSession session) throws Exception {
		logger.info("服务端与客户端创建连接...");

	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		logger.info("服务端与客户端连接打开...");
		list.add(new HardWare(session,state));
		logger.info("listCount::"+list.size());
		state = state+1;
		//SystemWebSocketHandler.sendMessage(-1,list.size()+"");
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		int style = 0;
		for(int i=0;i<list.size();i++){
			System.out.println("send"+session.getRemoteAddress());
			System.out.println("list"+list.get(i).getSession().getRemoteAddress());
			if(session.getRemoteAddress().equals(list.get(i).getSession().getRemoteAddress())){
				style=list.get(i).getNumber();
			}
		}

		IoBuffer ioBuffer = (IoBuffer)message;
		byte[] b = new byte [ioBuffer.limit()];
		ioBuffer.get(b);

		StringBuilder stringBuilder = new StringBuilder("");
		if (b == null || b.length <= 0) {
			System.out.printf("为空");
		}
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv+" ");
		}
		String content = "设备"+style+"发来的消息："+String.valueOf(stringBuilder);
		System.out.println(content);
		//SystemWebSocketHandler.sendMessage(style,content);

	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		logger.info("服务端发送信息成功...");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		int j=0;

		for(int i=0;i<list.size();i++){
			System.out.println("send"+session.getRemoteAddress());
			System.out.println("list"+list.get(i).getSession().getRemoteAddress());
			if(session.getRemoteAddress().equals(list.get(i).getSession().getRemoteAddress())){
				j=list.get(i).getNumber();
				list.remove(i);
			}
		}
		//SystemWebSocketHandler.sendMessage(-2,list.size()+"_"+j);
		System.out.println("关闭session");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		logger.info("服务端进入空闲状态...");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		logger.error("服务端发送异常...", cause);

	}

}

