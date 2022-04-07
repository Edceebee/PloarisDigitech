package com.example.PolarisAssessment.service;

import com.example.PolarisAssessment.entities.Box;
import com.example.PolarisAssessment.entities.Item;
import com.example.PolarisAssessment.entities.State;
import com.example.PolarisAssessment.repositories.BoxRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class BoxServiceImpl implements BoxService{

    @Autowired
    BoxRepo boxRepo;


    /**
     * Method to register a new Box.
     * @param box contains details needed to register a new box
     * @return registered box
     */
    @Override
    public Box registerBox(Box box) {
        log.info("registering a new box");

        if (box.getTired().length() > 20) {
            throw new RuntimeException("Name cannot exceed 20 characters");
        }

        if (box.getWeightLimit() > 500 ) {
            throw new RuntimeException("Weight of box cannot exceed 500gr");
        }
        if (box.getBatteryCapacity() > 100) {
            throw new RuntimeException("Battery percentage of box cannot be greater 100%");
        }
        if ( box.getBatteryCapacity() < 0) {
            throw new RuntimeException("Battery percentage of box cannot be lower than 0");
        }

        box.setRemainingWeightLimit(box.getWeightLimit());
        box.setStateOfBox(State.IDLE);
        Box savedBox = boxRepo.save(box);
        log.info("box saved successfully");
        return savedBox;
    }



    /**
     * Method to add an item to a box
     * @param boxId for identifying which box items want to be added to
     * @param item contains details needed to add an item to a box
     * @return saved item
     */
    @Override
    @Transactional
    public Item addItemToBox(Long boxId, Item item) {

        log.info("Adding item {} to box", item);
        Box findById = boxRepo.findById(boxId).
                orElseThrow(() -> new RuntimeException("Box with id " + boxId + " does not exist"));

        String itemNameRegex = "^[A-Za-z0-9_-]*$";
        Pattern itemNamePattern = Pattern.compile(itemNameRegex);
        log.info("pattern --> {}", itemNamePattern);
        Matcher itemNameMatcher = itemNamePattern.matcher(item.getItemName());
        log.info("matcher --> {}", itemNameMatcher);

        if (!itemNameMatcher.matches()) {
            throw new RuntimeException("Item name can only contain letters, numbers, -, and _");
        }

        int capacity = findById.getRemainingWeightLimit() - item.getItemWeight();
        findById.setRemainingWeightLimit(capacity);
        if (item.getItemWeight() > capacity) {
            throw new RuntimeException("Sorry you have exceeded the remaining weight capacity");
        }

        String itemCodeRegex = "^[A-Z0-9_-]+$";
        Pattern itemCodePattern = Pattern.compile(itemCodeRegex);
        log.info("pattern --> {}", itemCodePattern);
        Matcher itemCodeMatcher = itemCodePattern.matcher(item.getItemCode());
        log.info("matcher --> {}", itemCodeMatcher);

        if (!itemCodeMatcher.matches()) {
            throw new RuntimeException("Item code can only contain uppercase letters, numbers, -, and _");
        }

        if (findById.getBatteryCapacity() < 25) {
            throw new RuntimeException("Sorry you can't load an item because your battery capacity is below 25%");
        }

        findById.setStateOfBox(State.LOADING);
        findById.getItems().add(item);
        log.info("successfully added item to box");

        return item;

    }


    /**
     * Method to check all the items in a box.
     * @param boxId for identifying which box the items are stored
     * @return a list of items attached to the box with given boxId
     */
    @Override
    public List<Item> checkItemsInABox(Long boxId) {
        log.info("Checking loaded item in box");
        Box findById = boxRepo.findById(boxId).
                orElseThrow(() -> new RuntimeException("Box with id "+ boxId +" does not exist"));

        List<Item> item =  findById.getItems();
        log.info("loaded item in box retrieved successfully");
        return item;

    }

    /**
     * Method to get all boxes available for loading in the database.
     * @return a list of boxes
     */
    @Override
    public List<Box> checkAvailableBoxForLoading() {
        List<Box> availableBoxesForLoading = boxRepo.findAll();
        if (availableBoxesForLoading.isEmpty()) {
            throw new RuntimeException("Boxes not available");
        }
        return availableBoxesForLoading;
    }


    /**
     * Method to get the battery capacity of a box with the boxId
     * @param boxId for identifying which box you want to check the battery capacity
     * @return battery capacity
     */
    @Override
    public int checkBatteryLevelForABox(Long boxId) {
        log.info("Checking battery percent for box");
        Box findById = boxRepo.findById(boxId).
                orElseThrow(() -> new RuntimeException("Box with id "+ boxId +" does not exist"));

        return findById.getBatteryCapacity();
    }


}
