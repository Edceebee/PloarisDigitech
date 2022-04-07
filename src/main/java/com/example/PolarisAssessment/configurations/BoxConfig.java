package com.example.PolarisAssessment.configurations;

import com.example.PolarisAssessment.entities.Box;
import com.example.PolarisAssessment.entities.Item;
import com.example.PolarisAssessment.entities.State;
import com.example.PolarisAssessment.repositories.BoxRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class BoxConfig {


    /**
     * This is a method that seeds the database.
     * @param boxRepo represents the mechanism for encapsulating storage.
     * @return saved boxes.
     */
    @Bean
    @Transactional
    CommandLineRunner commandLineRunner(BoxRepo boxRepo) {
        return (args) -> {
            Box box1 = new Box(null, "Charles box", 500,
                    465, 99, State.IDLE, new ArrayList<>());

            Box box2 = new Box(null, "James box", 400,
                    365, 99, State.IDLE, new ArrayList<>());

            Box box3 = new Box(null, "John box", 460,
                    425, 99, State.IDLE, new ArrayList<>());

            Box box4 = new Box(null, "Peter box", 390,
                    355, 99, State.IDLE, new ArrayList<>());

            Box box5 = new Box(null, "Esther box", 500,
                    465, 99, State.IDLE, new ArrayList<>());

            Box box6 = new Box(null, "Cynthia box", 420,
                    385, 99, State.IDLE, new ArrayList<>());

            Item item01 = new Item(null, "fan12-_", 12, "AS123_", box1);
            Item item02 = new Item(null, "fdoe12-_", 23, "AG123_", box1);

            Item item03 = new Item(null, "shoe23-_", 12, "AW123_", box2);
            Item item04 = new Item(null, "hat2-_", 23, "AH123_", box2);

            Item item05 = new Item(null, "phone120-_", 12, "AM123_", box3);
            Item item06 = new Item(null, "pillow124-_", 23, "AC123_", box3);

            Item item07 = new Item(null, "belt26-_", 12, "ASXZ23_", box4);
            Item item08 = new Item(null, "stocks04-_", 23, "AOP123_", box4);

            List<Item> items1 = new ArrayList<>();
            items1.add(item01);
            items1.add(item02);

            List<Item> items2 = new ArrayList<>();
            items2.add(item03);
            items2.add(item04);

            List<Item> items3 = new ArrayList<>();
            items3.add(item05);
            items3.add(item06);

            List<Item> items4 = new ArrayList<>();
            items4.add(item07);
            items4.add(item08);

            box1.setItems(items1);
            box2.setItems(items2);
            box3.setItems(items3);
            box4.setItems(items4);

            boxRepo.save(box1);
            boxRepo.save(box2);
            boxRepo.save(box3);
            boxRepo.save(box4);
            boxRepo.save(box5);
            boxRepo.save(box6);

        };

    }
}
