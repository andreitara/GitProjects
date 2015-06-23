package util;

import hibernate.ManageTables;
import hibernate.TradeRelationship;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Andrei on 6/23/2015.
 */
public class ManualInvocation {

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
            FileUtils.writeStringToFile(new File("D:\\Edifecs\\RRM\\RIM\\working\\OutboundTrigger\\ManualInvocation\\" + trName + ".dat.Properties") ,"RRM_TradeRelationship=" + trName);
            FileUtils.writeStringToFile(new File("D:\\Edifecs\\RRM\\RIM\\working\\OutboundTrigger\\ManualInvocation\\" + trName + ".dat") ,trName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
