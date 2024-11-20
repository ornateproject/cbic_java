package com.ornates.cbic.controller;

import com.ornates.cbic.dao.pool.JDBCConnection;
import com.ornates.cbic.model.response.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/zoneName")
@Controller
public class ZoneGstCustom {
    private Logger logger = LoggerFactory.getLogger(ZoneGstCustom.class);

    @ResponseBody
    @RequestMapping(value = "/")
    public String home() {
        return "its working api";
    }

    /*
     * Date: nov 16, 2024
     * Purpose: This method checks the database connection.
     */
    @ResponseBody
    @RequestMapping(value = "/db_check")
    public String bdTest() {
        // Connection done
        Connection con = JDBCConnection.getTNConnection();
        System.out.println("Connection :" + con);
        return "its working api";
    }


//    @ResponseBody
//    @RequestMapping(value = "/gstZone")
//   // http://localhost:8080/cbicApi/zoneName/gstZone
//    public List<Zone> zoneReturnGst() {
//        return Arrays.asList(
//                new Zone("CHANDIGARH CE & GST", 50),
//                new Zone("DELHI CE & GST", 51),
//                new Zone("PANCHKULA CE & GST", 52),
//                new Zone("LUCKNOW CE & GST", 53),
//                new Zone("MEERUT CE & GST", 54),
//                new Zone("VISHAKAPATNAM CE & GST", 55),
//                new Zone("HYDERABAD CE & GST", 56),
//                new Zone("BENGALURU CE & GST", 57),
//                new Zone("THIRUVANANTHAPURAM CE & GST", 58),
//                new Zone("CHENNAI CE & GST", 59),
//                new Zone("RANCHI CE & GST", 60),
//                new Zone("KOLKATA CE & GST", 61),
//                new Zone("BHUBANESHWAR CE & GST", 62),
//                new Zone("JAIPUR CE & GST", 63),
//                new Zone("AHMEDABAD CE & GST", 64),
//                new Zone("VADODARA CE & GST", 65),
//                new Zone("NAGPUR CE & GST", 66),
//                new Zone("MUMBAI CE & GST", 67),
//                new Zone("PUNE CE & GST", 68),
//                new Zone("BHOPAL CE & GST", 69),
//                new Zone("GUWAHATI CE & GST", 70));
//    }

    @ResponseBody
    @RequestMapping(value = "/customZone")
    // http://localhost:8080/cbicApi/zoneName/customZone
    public List<Zone> zoneReturnCustom() {
        return Arrays.asList(
                new Zone("MEERUT CE & GST", "54"),
                new Zone("VISHAKAPATNAM CE & GST", "55"),
                new Zone("HYDERABAD CE & GST", "56"),
                new Zone("BENGALURU CE & GST", "57"),
                new Zone("THIRUVANANTHAPURAM CE & GST", "58"),
//                new Zone("CHENNAI CE & GST", "59""),
                new Zone("RANCHI CE & GST", "60"),
//                new Zone("KOLKATA CE & GST", "61"),
                new Zone("BHUBANESHWAR CE & GST", "62"),
                new Zone("JAIPUR CE & GST", "63"),
//                new Zone("AHMEDABAD CE & GST", "64"),
//                new Zone("VADODARA CE & GST", "65"),
                new Zone("NAGPUR CE & GST", "66"),
                new Zone("MUMBAI CE & GST", "67"),
                new Zone("PUNE CE & GST", "68"),
                new Zone("BHOPAL CE & GST", "69"),
                new Zone("GUWAHATI CE & GST", "70"),
                new Zone("Ahmedabad CUS", "71"),
                new Zone("Bangalore CUS", "72"),
                new Zone("Chennai CUS", "73"),
                new Zone("DELHI CUS", "74"),
                new Zone("DELHI PREV", "75"),
                new Zone("KOLKATA CUS", "76"),
                new Zone("MUMBAI - I  CUS", "77"),
                new Zone("MUMBAI - II CUS", "78"),
                new Zone("MUMBAI - III CUS", "79"),
                new Zone("PATNA PREV", "80"),
                new Zone("TIRUCHIRAPALLI PREV", "81"),
                new Zone("DRI DG", "DD")
        );
    }


}



