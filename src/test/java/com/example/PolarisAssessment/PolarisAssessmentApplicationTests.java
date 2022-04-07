package com.example.PolarisAssessment;

import com.example.PolarisAssessment.entities.Box;
import com.example.PolarisAssessment.entities.Item;
import com.example.PolarisAssessment.entities.State;
import com.example.PolarisAssessment.repositories.BoxRepo;
import com.example.PolarisAssessment.service.BoxService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class PolarisAssessmentApplicationTests {

	@Autowired
	BoxService boxService;

	@Autowired
	BoxRepo boxRepo;

	@Test
	void testToCheckAvailableBoxForLoading() {
		int sizeofBoxes = boxRepo.findAll().size();
		log.info("size of boxes --> {}", sizeofBoxes);
		assertThat(boxService.checkAvailableBoxForLoading().stream().count()).isEqualTo(sizeofBoxes);
	}


	@Test
	void testThatABoxCanBeRegistered() {
		Box box001 = new Box();

		box001.setTired("box01");
		box001.setWeightLimit(300);
		box001.setRemainingWeightLimit(300);
		box001.setBatteryCapacity(90);
		box001.setStateOfBox(State.IDLE);

		boxService.registerBox(box001);

		assertThat(box001.getTired()).isEqualTo("box01");
		assertThat(box001.getWeightLimit()).isEqualTo(300);
		assertThat(box001.getRemainingWeightLimit()).isEqualTo(300);
		assertThat(box001.getBatteryCapacity()).isEqualTo(90);
		assertThat(box001.getStateOfBox()).isEqualTo(State.IDLE);

	}

	@Test
	void testThatItemCanBeAddedToABox() {
		Box box002 = new Box();

		box002.setTired("box02");
		box002.setWeightLimit(300);
		box002.setRemainingWeightLimit(300);
		box002.setBatteryCapacity(90);
		box002.setStateOfBox(State.IDLE);

		boxService.registerBox(box002);

		Item item002 = new Item();

		item002.setItemName("string");
		item002.setItemWeight(20);
		item002.setItemCode("KJF89");

		List<Item> itemList = new ArrayList<>();
		itemList.add(item002);
		box002.setItems(itemList);
		item002.setBox(box002);

		boxService.addItemToBox(box002.getId(), item002);

		assertThat(box002.getItems()).contains(item002);
	}

	@Test
	void testToCheckItemsInABox() {
		Box box003 = new Box();

		box003.setTired("box02");
		box003.setWeightLimit(300);
		box003.setRemainingWeightLimit(300);
		box003.setBatteryCapacity(90);
		box003.setStateOfBox(State.IDLE);

		Item item003 = new Item();

		item003.setItemName("string");
		item003.setItemWeight(20);
		item003.setItemCode("KJF89");

		boxService.registerBox(box003);

		List<Item> itemList = new ArrayList<>();
		itemList.add(item003);
		box003.setItems(itemList);
		item003.setBox(box003);

		boxService.addItemToBox(box003.getId(), item003);
		boxService.checkItemsInABox(box003.getId());

		assertThat(box003.getItems().size()).isEqualTo(1);
		assertThat(box003.getItems().stream().findFirst().get().getItemName()).isEqualTo("string");
		assertThat(box003.getItems().stream().findFirst().get().getItemWeight()).isEqualTo(20);
		assertThat(box003.getItems().stream().findFirst().get().getItemCode()).isEqualTo("KJF89");

	}


	@Test
	void testToCheckBatteryLevelForABox() {
		Box box004 = new Box();

		box004.setTired("box02");
		box004.setWeightLimit(300);
		box004.setRemainingWeightLimit(300);
		box004.setBatteryCapacity(90);
		box004.setStateOfBox(State.IDLE);

		boxService.registerBox(box004);

		boxService.checkBatteryLevelForABox(box004.getId());

		assertThat(box004.getBatteryCapacity()).isEqualTo(90);
	}
}
