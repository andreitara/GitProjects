package util;

import hibernate.ManageTables;
import hibernate.TradeRelationship;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by Andrei on 6/23/2015.
 */
public class ManualInvocation {

    public static Properties properties = new Properties();
    static {
        try {
            properties.load(new FileInputStream("config\\config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void triggerAllTRsFromDatabase(){
        ManageTables manager = new ManageTables();
        List<TradeRelationship> trs = manager.getTradeRelationships();
        for(TradeRelationship tr : trs){
            triggerOneTR(tr.getName());
        }
    }

    public static void triggerAllTRsFromConfigFile(){

    }

    public static void triggerOneTR(String trName) {
        try {
            FileUtils.writeStringToFile(new File(properties.getProperty("ManualInvocationPath") + trName + ".dat.properties") ,"RRM_TradeRelationshipName=" + trName);
            FileUtils.writeStringToFile(new File(properties.getProperty("ManualInvocationPath") + trName + ".dat") ,trName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
