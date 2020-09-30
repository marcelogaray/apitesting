package org.newchallenge.managedata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class InfoShare {
    private static InfoShare InfoShare = null;
    private static final Logger logger = LoggerFactory.getLogger(InfoShare.class);
    private HashMap<String, Object> dataBank = new HashMap<>();

    public static InfoShare getInstance(){
        if(InfoShare == null){
            InfoShare = new InfoShare();
        }
        return InfoShare;
    }
    private InfoShare(){ }

    public <T> T getInfoFromShare(String keyName, Class<T> objectClass) {
        logger.info("Get data {} from the Information Share.", keyName);
        T response = (T) dataBank.get(keyName);
        if (response == null)
            return (T) keyName;
        else
            return response;
    }

    public void putInfoToShare(String key, Object value) {
        logger.info("Put info {} to info share using key {}.", value, key);
        this.dataBank.put(key, value);
    }
}
