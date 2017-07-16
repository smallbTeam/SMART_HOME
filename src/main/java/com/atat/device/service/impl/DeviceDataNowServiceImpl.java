package com.atat.device.service.impl;

import com.atat.common.util.CollectionUtil;
import com.atat.device.dao.CategoryParameterDao;
import com.atat.device.dao.DeviceDao;
import com.atat.device.dao.DeviceDataHourDao;
import com.atat.device.dao.DeviceDataNowDao;
import com.atat.device.service.DeviceDataNowService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeviceDataNowServiceImpl implements DeviceDataNowService {

    @Autowired
    private DeviceDataNowDao deviceDataNowDao;

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private CategoryParameterDao categoryParameterDao;

    @Autowired
    private DeviceDataHourDao deviceDataHourDao;

    @Override
    public void addDeviceDataNow(Map<String, Object> param) {
        deviceDataNowDao.addDeviceDataNow(param);
    }

    @Override
    public List<Map<String, Object>> selectDeviceDataNowList(Map<String, Object> param) {
        return deviceDataNowDao.selectDeviceDataNowList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getDeviceDataNowPageTurn(Map<String, Object> param, Integer pageNo,
            Integer pageSize) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String, Object>> list = deviceDataNowDao.selectDeviceDataNowList(param);
        // 用PageInfo对结果进行包装
        PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getDeviceDataNowById(Long deviceDataNowId) {
        Map<String, Object> deviceDataNowinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("deviceDataNowId", deviceDataNowId);
        List<Map<String, Object>> deviceDataNowList = deviceDataNowDao.selectDeviceDataNowList(rs);
        if ((null != deviceDataNowList) && (deviceDataNowList.size() > 0)) {
            deviceDataNowinfo = deviceDataNowList.get(0);
        }
        return deviceDataNowinfo;
    }

    @Override
    public void delDeviceDataNowById(Long deviceDataNowId) {
        deviceDataNowDao.delDeviceDataNowById(deviceDataNowId);
    }

    @Override
    public Integer addDeviceData(Map<String, Object> param) {
        // 网关设备序号
        String seriaNumber = (String) param.get("seriaNumber");
        // 网关设备序号
        String gatewaySerialNumber = (String) param.get("gatewaySerialNumber");
        // 属性code
        String code = (String) param.get("code");
        // 值
        try {
            Double value = Double.parseDouble((String) param.get("value"));
            // 依据设备序号查找设备类型和设备ID
            Map<String, Object> param_sede = new HashMap<String, Object>();
            param.put("seriaNumber", seriaNumber);
            param.put("gatewaySerialNumber", gatewaySerialNumber);
            if (CollectionUtil.isNotEmpty((List<Map<String, Object>>) deviceDao.selectDeviceList(param_sede))) {
                Map<String, Object> device = deviceDao.selectDeviceList(param_sede).get(0);
                Long deviceId = Long.parseLong(device.get("deviceId") + "");
                Long deviceCategoryId = Long.parseLong(device.get("deviceCategoryId") + "");
                // 依据设备类型和属性code查找设备参数ID
                Map<String, Object> param_secp = new HashMap<String, Object>();
                param_secp.put("deviceCategoryId", deviceCategoryId);
                param_secp.put("code", code);
                if (CollectionUtil.isNotEmpty((List<Map<String, Object>>) categoryParameterDao.selectCategoryParameterList(param_secp))) {
                    Map<String, Object> categoryParameter = categoryParameterDao.selectCategoryParameterList(param_secp).get(0);
                    Long categoryParameterId = (Long) categoryParameter.get("categoryParameterId");
                    // 添加设备参数
                    Map<String, Object> param_addn = new HashMap<String, Object>();
                    param_addn.put("categoryParameterId", categoryParameterId);
                    param_addn.put("value", value);
                    param_addn.put("deviceId", deviceId);
                    param_addn.put("recordTime", new Date().getTime());
                    deviceDataNowDao.addDeviceDataNow(param_addn);
                    return 1;
                }
                else {
                    //该设备不存在该属性
                    return 0;
                }
            }
            else {
                //数据库未存在该设备
                return -1;
            }
        } catch (Exception e){
            return -2;
        }
    }

    @Override public Map<String, Object> getThreeHourDeviceData(Long deviceId, String code) {
        //查找设备参数Id
        Map<String, Object> categoryParameter = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("deviceId", deviceId);
        List<Map<String, Object>> deviceList = deviceDao.selectDeviceList(param);
        if ((null != deviceList) && (deviceList.size() > 0)) {
            Map<String, Object> device = deviceList.get(0);
            Long deviceCategoryId = Long.parseLong(device.get("deviceCategoryId")+"");
            // 依据设备类型和属性code查找设备参数ID
            Map<String, Object> param_secp = new HashMap<String, Object>();
            param_secp.put("deviceCategoryId", deviceCategoryId);
            param_secp.put("code", code);
            if (CollectionUtil.isNotEmpty((List<Map<String, Object>>) categoryParameterDao.selectCategoryParameterList(param_secp))) {
                categoryParameter = categoryParameterDao.selectCategoryParameterList(param_secp).get(0);
                Long categoryParameterId = Long.parseLong(categoryParameter.get("categoryParameterId")+"");
                Map<String, Object> param_sdd = new HashMap<String, Object>();
                param_sdd.put("categoryParameterId", categoryParameterId);
                param_sdd.put("deviceId", deviceId);
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.HOUR, -3);
                param_sdd.put("recordTimeStart", cal.getTime().getTime());
                List<Map<String, Object>> deviceThreeHourData = deviceDataNowDao.selectDeviceDataNowList(param_sdd);
                categoryParameter.put("deviceEchartsData", deviceThreeHourData);
            }
        }
        return categoryParameter;
    }

    @Override public void timingFormateForThreeHour() {
        //分别计算前六小时到前三小时内的平均值
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, -3);
        List<Map<String, Object>> deviceDataList = new ArrayList<Map<String, Object>>();
        for (int i=0;i<3;i++){
            Long recordTimeEnd = cal.getTime().getTime();
            cal.add(Calendar.HOUR, -1);
            Long recordTimeStart = cal.getTime().getTime();
            Map<String, Object> param_oneHour = new HashMap<String, Object>();
            param_oneHour.put("recordTimeStart",recordTimeStart);
            param_oneHour.put("recordTimeEnd",recordTimeEnd);
            deviceDataList.addAll(deviceDataNowDao.timingNowAverageData(param_oneHour));
        }
        //存入小时表
        if (CollectionUtil.isNotEmpty(deviceDataList)){
            deviceDataHourDao.addDeviceDataHourList(deviceDataList);
        }
        //移除now表之前的数据
        cal.add(Calendar.HOUR, 3);
        deviceDataNowDao.delDeviceDataNowByEndTime(cal.getTime().getTime());
    }
}
