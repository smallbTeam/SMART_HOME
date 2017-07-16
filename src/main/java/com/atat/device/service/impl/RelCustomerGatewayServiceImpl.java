package com.atat.device.service.impl;

import com.atat.account.dao.CustomerDao;
import com.atat.common.util.CollectionUtil;
import com.atat.common.util.StringUtil;
import com.atat.device.dao.DeviceDao;
import com.atat.device.dao.GatewayDao;
import com.atat.device.dao.RelCustomerGatewayDao;
import com.atat.device.service.RelCustomerGatewayService;
import com.atat.message.service.ShortMessageService;
import com.atat.message.service.WeixinMessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RelCustomerGatewayServiceImpl implements RelCustomerGatewayService {

    @Autowired
    private RelCustomerGatewayDao relCustomerGatewayDao;

    @Autowired
    private GatewayDao gatewayDao;

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private WeixinMessageService weixinMessageService;

    @Autowired
    private ShortMessageService shortMessageService;

    @Override
    public void  addRelCustomerGateway(Map<String, Object> param) {
        relCustomerGatewayDao.addRelCustomerGateway(param);
    }

    @Override
    public void  updateRelCustomerGatewayById(Map<String, Object> param) {
        relCustomerGatewayDao.updateRelCustomerGatewayById(param);
    }

    @Override
    public List<Map<String, Object>> selectRelCustomerGatewayList(Map<String, Object> param) {
        return relCustomerGatewayDao.selectRelCustomerGatewayList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getRelCustomerGatewayPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;
        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list = relCustomerGatewayDao.selectRelCustomerGatewayList(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getRelCustomerGatewayById(Long relCustomerGatewayId) {
        Map<String, Object> relCustomerGatewayinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("relCustomerGatewayId", relCustomerGatewayId);
        List<Map<String, Object>> relCustomerGatewayList = relCustomerGatewayDao.selectRelCustomerGatewayList(rs);
        if ((null != relCustomerGatewayList) && (relCustomerGatewayList.size() > 0)) {
            relCustomerGatewayinfo = relCustomerGatewayList.get(0);
        }
        return relCustomerGatewayinfo;
    }

    @Override
    public void delRelCustomerGatewayById(Long relCustomerGatewayId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("isDeleted", 1);
        param.put("customerGatewayId", relCustomerGatewayId);
        param.put("modifiedDate", new Date());
        relCustomerGatewayDao.updateRelCustomerGatewayById(param);
    }

    @Override public void delGatewayForCustomer(Map<String, Object> param) {
        List<Map<String, Object>> relCustomerGatewayList = relCustomerGatewayDao.selectRelCustomerGatewayList(param);
        if (CollectionUtil.isNotEmpty(relCustomerGatewayList)){
            Map<String, Object> relCustomerGateway =  relCustomerGatewayList.get(0);
            //如果客户是网关拥有着则直接删除网关
            if (((Integer)1).equals((Integer) relCustomerGateway.get("isOnwer"))){
                Map<String, Object> del_param = new HashMap<String, Object>();
                String gatewaySerialNumber = (String) param.get("gatewaySerialNumber");
                del_param.put("isDeleted", 1);
                del_param.put("serialNumber", gatewaySerialNumber);
                gatewayDao.updateGatewayBySerialNumber(del_param);
                //删除当前网关关联的所有用户
                relCustomerGatewayDao.deleteRelCustomerGatewayByGatewayNum(gatewaySerialNumber);
                //删除当前网关关联的所有设备
                deviceDao.deleteDeviceByGatewayNum(gatewaySerialNumber);
            }
            //解除关联
            Long relCustomerGatewayId = (Long)relCustomerGateway.get("relCustomerGatewayId");
            Map<String, Object> paramdel = new HashMap<String, Object>();
            paramdel.put("isDeleted", 1);
            paramdel.put("relCustomerGatewayId", relCustomerGatewayId);
            relCustomerGatewayDao.updateRelCustomerGatewayById(param);
        }
    }

    @Override public Integer addGatewayForCustomer(Map<String, Object> param) {
        Integer isOnwer = 0;
        //判断是否是初次添加
        Map<String, Object> param_se = new HashMap<String, Object>();
        String gatewaySerialNumber = (String) param.get("gatewaySerialNumber");
        param_se.put("serialNumber", gatewaySerialNumber);
        if (CollectionUtil.isEmpty((List<Map<String, Object>>)gatewayDao.selectGatewayList(param_se))){
            Map<String, Object> param_cu = new HashMap<String, Object>();
            param_cu.put("serialNumber", gatewaySerialNumber);
            gatewayDao.addGateway(param_cu);
            isOnwer = 1;
            param.put("isOnwer",isOnwer);
            //默认订阅当前网关
            param.put("isSendMsg",1);
            relCustomerGatewayDao.addRelCustomerGateway(param);
            //初次添加网关 添加网管下设备 设备序号与网管保持一致
            Map<String, Object> paramDevice = new HashMap<String, Object>();
            paramDevice.put("seriaNumber",gatewaySerialNumber);
            //设备类型ID为1
            paramDevice.put("deviceCategoryId",1);
            paramDevice.put("gatewaySerialNumber",gatewaySerialNumber);
            deviceDao.addDevice(paramDevice);
            return 1;
        } else {
            return 0;
        }
    }

    @Override public Integer addGateWayByInvite(Map<String, Object> param) {
        //网关Id
        String gatewaySerialNumber = (String) param.get("gatewaySerialNumber");
        //邀请人Id'
        Long customerId = (Long) param.get("customerId");
        //被邀请人Id
        Long invitederId = (Long) param.get("invitederId");
        Map<String, Object> paramCheckOnwer = new HashMap<String, Object>();
        //判断原用户是否拥有权限
        paramCheckOnwer.put("gatewaySerialNumber",gatewaySerialNumber);
        paramCheckOnwer.put("customerId",customerId);
        List<Map<String, Object>> relCustomerGatewayList = relCustomerGatewayDao.selectRelCustomerGatewayList(paramCheckOnwer);
        if (CollectionUtil.isEmpty(relCustomerGatewayList)) {
            return 0;
        }
        Map<String, Object> onwerCustomerInfo = relCustomerGatewayList.get(0);
        if (!(Boolean) onwerCustomerInfo.get("isOnwer")){
            return 0;
        }
        String onwerName = (String) onwerCustomerInfo.get("nickName");
        String onwerPhone = (String) onwerCustomerInfo.get("mobelPhone");
        String gatewayName = (String) onwerCustomerInfo.get("gatewayName");
        //网关拥有着短信昵称
        String onwerTishi = StringUtil.isEmpty(onwerName) ? onwerPhone : onwerName;
        //新用户下是否已经拥有该网关
        Map<String, Object> paramCheckInviteder = new HashMap<String, Object>();
        paramCheckInviteder.put("gatewaySerialNumber",gatewaySerialNumber);
        paramCheckInviteder.put("customerId",invitederId);
        List<Map<String, Object>> customerGatewayList = relCustomerGatewayDao.selectRelCustomerGatewayList(paramCheckInviteder);
        if (CollectionUtil.isEmpty(customerGatewayList)) {
            paramCheckInviteder.put("isOnwer",0);
            paramCheckInviteder.put("gatewayName",gatewayName);
            //默认订阅当前网关
            paramCheckInviteder.put("isSendMsg",1);
           relCustomerGatewayDao.addRelCustomerGateway(paramCheckInviteder);
           //推送微信消息
            Map<String, Object> rs = new HashMap<String, Object>();
            rs.put("customerId", invitederId);
            List<Map<String, Object>> invitedCustomerList = customerDao.selectCustomerList(rs);
            if (CollectionUtil.isNotEmpty(invitedCustomerList)) {
                Map<String, Object> invitedCustomerinfo = invitedCustomerList.get(0);
                String invitedPhone = (String) invitedCustomerinfo.get("mobelPhone");
                String invitedName = (String) invitedCustomerinfo.get("nickName");
                String invitedTishi = StringUtil.isEmpty(invitedName) ? invitedPhone : invitedName;
                //String wxId = (String) customerinfo.get("wxId");
                //List<String> touser = new ArrayList<String>();
                //touser.add(wxId);
                //weixinMessageService.sendWeixinMessage(touser,null,);
                String msgContent = "尊敬的"+invitedTishi+"！你好！用户"+onwerTishi+"给您分享了"+gatewayName+"网关,请前往\"ATAT智能家\"公众号查看";
                //发送短信
                shortMessageService.sendShortMessage(invitedPhone,msgContent);
            }
        }
        return 1;
    }


    @Override public Boolean switchAllIsSendMsg(String wxId) {
        //获取原开关状态
        Map<String, Object> relCustomerGatewayinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("wxId", wxId);
        rs.put("limit", 1);
        List<Map<String, Object>> relCustomerGatewayList = relCustomerGatewayDao.selectRelCustomerGatewayList(rs);
        if (CollectionUtil.isEmpty(relCustomerGatewayList)) {
            return false;
        }
        relCustomerGatewayinfo = relCustomerGatewayList.get(0);
        Boolean status = (Boolean) relCustomerGatewayinfo.get("isSendMsg");
        status = status ? false : true;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("isSendMsg", status ? 1: 0);
        param.put("customerId", (Long)relCustomerGatewayinfo.get("customerId"));
        relCustomerGatewayDao.updateAllIsSendMsg(param);
        return status;
    }

    @Override public Boolean switchGatewayIsSendMag(Map<String, Object> param) {
        List<Map<String, Object>> relCustomerGatewayList = relCustomerGatewayDao.selectRelCustomerGatewayList(param);
        if (CollectionUtil.isEmpty(relCustomerGatewayList)) {
            return false;
        }
        Map<String, Object> relCustomerGatewayinfo = relCustomerGatewayList.get(0);
        Boolean status = (Boolean) relCustomerGatewayinfo.get("isSendMsg");
        status = status ? false : true;
        param.put("isSendMsg", status ? 1: 0);
        relCustomerGatewayDao.updateAllIsSendMsg(param);
        return status;
    }
}
