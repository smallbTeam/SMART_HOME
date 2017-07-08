package com.atat.common.bootitem;

import java.util.*;

import com.atat.common.util.JsonUtil;
import com.atat.device.config.SystemWebSocketHandler;
import com.atat.device.service.DeviceService;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static com.atat.common.bootitem.MinaUtil.InPutMessageToString;

public class MinaServerHandler extends IoHandlerAdapter {
	public static Logger logger = LoggerFactory.getLogger(MinaServerHandler.class);
	public static List<HardWare>  list = new ArrayList<HardWare>();
	//	private int state = 0;

	///////////////根据返回值直接发送ok
	public static void sendMessage(String msg,IoSession number) {
		logger.info("sendMessage-listCount::" + list.size());
		System.out.println("发送数据:====>"+msg);
		try {
			for (HardWare handware : list) {
				if (handware.getSession().equals(number)) {
					System.out.println("aaaaaaaaaaaaaa");
					byte[] bar = msg.getBytes();
					IoBuffer buffers = IoBuffer.allocate(bar.length);
					buffers.put(bar, 0, bar.length);
					buffers.flip();
					number.write(buffers);
				}
			}
		} catch (Exception e) {
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

		list.add(new HardWare(session));
		logger.info("listCount::"+list.size());
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");// 此文件放在SRC目录下
		DeviceService deviceService = (DeviceService) context.getBean("deviceService");



		for(int i=0;i<list.size();i++){
			if(session.getRemoteAddress().equals(list.get(i).getSession().getRemoteAddress())){
				IoBuffer ioBuffer = (IoBuffer)message;
				byte[] b = new byte [ioBuffer.limit()];
				ioBuffer.get(b);
				String stringmsg = new String(b);
				String [] str = stringmsg.split("\\|");
				list.get(i).setNumber(str[0]);
				Map<String,Object> map =  InPutMessageToString(str);
				sendMessage("OK\\\r\\\n", session);

				String gatewayDeviceID = (String) map.get("devicenumber");
				Iterator it = map.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next().toString();//设备类型名称
					Object value = map.get(key);//设备值
					String valuestr = JsonUtil.toJson(value);
					if (!"devicenumber".equals(key)){
						logger.info("网关"+gatewayDeviceID+"下设备,[设备类型："+key+"][设备信号值："+valuestr+"]");
						//将设备信息存储到数据库
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("gatewayDeviceID",gatewayDeviceID);
						param.put("Name",key);
						param.put("DeviceData",valuestr);
						deviceService.addOrUpdateDeviceDataBydeviceTypeAndgateway(param);
					}
				}
			}
		}


		//		StringBuilder stringBuilder = new StringBuilder("");
		//		if (b == null || b.length <= 0) {
		//			System.out.printf("为空");
		//		}
		//		for (int i = 0; i < b.length; i++) {
		//			int v = b[i] & 0xFF;
		//			String hv = Integer.toHexString(v);
		//			if (hv.length() < 2) {
		//				stringBuilder.append(0);
		//			}
		//			stringBuilder.append(hv+" ");
		//		}
		//		String content = "设备"+style+"发来的消息："+String.valueOf(stringBuilder);
		//		System.out.println(content);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		logger.info("服务端发送信息成功...");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {

		for(int i=0;i<list.size();i++){
			System.out.println("send"+session.getRemoteAddress());
			System.out.println("list"+list.get(i).getSession().getRemoteAddress());
			if(session.getRemoteAddress().equals(list.get(i).getSession().getRemoteAddress())){
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

