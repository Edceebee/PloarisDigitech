package com.example.PolarisAssessment.service;

import com.example.PolarisAssessment.entities.Box;
import com.example.PolarisAssessment.entities.Item;

import java.util.List;

public interface BoxService {
    Box registerBox(Box box);
    Item addItemToBox(Long boxId, Item item);
    List<Item> checkItemsInABox(Long boxId);
    List<Box> checkAvailableBoxForLoading();
    int checkBatteryLevelForABox(Long id);

}
