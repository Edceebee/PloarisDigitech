package com.example.PolarisAssessment.controllers;

import com.example.PolarisAssessment.entities.Box;
import com.example.PolarisAssessment.entities.Item;
import com.example.PolarisAssessment.entities.dtos.BoxDto;
import com.example.PolarisAssessment.entities.dtos.ItemDto;
import com.example.PolarisAssessment.responses.ApiResponse;
import com.example.PolarisAssessment.service.BoxService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BoxController {

    @Autowired
    BoxService boxService;

    @Autowired
    ModelMapper modelMapper;

    /**
     * Endpoint to register a new Box.
     * @param boxDto contains details needed to register a new box
     * @return registered box
     */
    @PostMapping("/box")
    public ResponseEntity<?> registerBox(@RequestBody BoxDto boxDto) {
        try {
            Box boxRequest = modelMapper.map(boxDto, Box.class);
            Box box = boxService.registerBox(boxRequest);

            return new ResponseEntity<>(box, HttpStatus.CREATED);
        }

        catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(new ApiResponse(exception.getMessage()),
                    HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Endpoint to add an item to a box
     * @param boxId for identifying which box items want to be added to
     * @param itemDto contains details needed to add an item to a box
     * @return saved item
     */
    @PostMapping("/item/{boxId}")
    public ResponseEntity<?> addItemToBox(@PathVariable Long boxId, @RequestBody ItemDto itemDto) {
        try {
            Item itemRequest = modelMapper.map(itemDto, Item.class);
            Item item = boxService.addItemToBox(boxId, itemRequest);

            return new ResponseEntity<>(item, HttpStatus.CREATED);
        }
        catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(new ApiResponse(exception.getMessage()),
                    HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /**
     * Endpoint to check all the items in a box.
     * @param boxId for identifying which box the items are stored
     * @return a list of items attached to the box with given boxId
     */
    @GetMapping("/item/{boxId}")
    public ResponseEntity<?> checkItemsInABox(@PathVariable Long boxId) {
        try {
            List<Item> items = boxService.checkItemsInABox(boxId);
            return new ResponseEntity<>(items, HttpStatus.CREATED);
        }
        catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(new ApiResponse(exception.getMessage()),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }

    }

    /**
     * Endpoint to get all boxes available for loading in the database.
     * @return a list of boxes
     */
    @GetMapping("/boxes")
    public ResponseEntity<?> getAllBoxes() {
        try {
            List<Box> allBoxes = boxService.checkAvailableBoxForLoading();
            return new ResponseEntity<>(allBoxes, HttpStatus.FOUND);
        }
        catch (RuntimeException exception) {
            return new ResponseEntity<>(new ApiResponse(exception.getMessage()),
                    HttpStatus.NO_CONTENT);
        }

    }

    /**
     * Endpoint to get the battery capacity of a box with the boxId
     * @param boxId for identifying which box you want to check the battery capacity
     * @return battery capacity
     */
    @GetMapping("/batteryCapacity/{boxId}")
    public ResponseEntity<?> checkBatteryLevelForABox(@PathVariable Long boxId) {
        try {
            Integer batteryLevel = boxService.checkBatteryLevelForABox(boxId);
            return new ResponseEntity<>(batteryLevel, HttpStatus.CREATED);
        }
        catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(new ApiResponse(exception.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }

    }

}
