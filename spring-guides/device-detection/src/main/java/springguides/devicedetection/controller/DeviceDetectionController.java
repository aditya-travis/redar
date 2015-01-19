package springguides.devicedetection.controller;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mengf on 1/19/2015.
 */
@Controller
public class DeviceDetectionController {

    @RequestMapping("/detect-device")
    @ResponseBody
    public String detectDevice(Device device){
        String deviceType= "unkown";

        if(device.isNormal()){
            deviceType = "normal";
        }else if(device.isMobile()){
            deviceType = "mobile";

        }else if(device.isTablet()){
            deviceType = "tablet";
        }
        return "Hello " + deviceType + " browser!";
    }
}
