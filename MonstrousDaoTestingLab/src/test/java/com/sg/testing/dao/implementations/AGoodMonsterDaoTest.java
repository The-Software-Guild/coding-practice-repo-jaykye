package com.sg.testing.dao.implementations;

import com.sg.testing.dao.MonsterDao;
import com.sg.testing.dao.implementations.buggy.*;
import com.sg.testing.model.Monster;
import com.sg.testing.model.MonsterType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AGoodMonsterDaoTest {
    MonsterDao testDao;

    public AGoodMonsterDaoTest(){}

    @BeforeEach
    void setUp() {
//        testDao = new AGoodMonsterDao();
        testDao = new BadMonsterDaoF();
    }

    @Test
    public void testAddAndGetMonsterEmptyDao(){
        // Arrange
        int monsterId = 1;
        String name = "testName";
        MonsterType type = MonsterType.VAMPIRE;
        int peopleEaten = 10;
        String favoriteFood = "Avocado";
        Monster testMonster = new Monster(name, type, peopleEaten, favoriteFood);

        // Act
        Monster dupMonsterExisted = testDao.addMonster(monsterId, testMonster);
        Monster retrievedMonster = testDao.getMonster(monsterId);

        // Assert
        assertTrue(testMonster.equals(retrievedMonster));

        // Assert
        assertNull(dupMonsterExisted);
    }

    @Test
    public void testAddAndGetMonsterDupDao(){
        // Arrange
        int monsterId = 1;
        String name = "testName";
        MonsterType type = MonsterType.VAMPIRE;
        int peopleEaten = 10;
        String favoriteFood = "Avocado";
        Monster testMonster = new Monster(name, type, peopleEaten, favoriteFood);
        testDao.addMonster(monsterId, testMonster);

        // Act
        Monster dupMonsterExisted = testDao.addMonster(monsterId, testMonster);
        Monster retrievedMonster = testDao.getMonster(monsterId);

        // Assert
        assertTrue(testMonster.equals(retrievedMonster));

        // Assert
        assertNotNull(dupMonsterExisted);
    }

    @Test
    public void testGetAllMonster(){
        // Arrange
        // First Monster
        int monsterId1 = 1;
        String name = "Ada";
        MonsterType type = MonsterType.VAMPIRE;
        int peopleEaten = 10;
        String favoriteFood = "lemon";
        Monster testMonster1 = new Monster(name, type, peopleEaten, favoriteFood);

        // Second Monster
        int monsterId2 = 2;
        String name2 = "Charles";
        MonsterType type2 = MonsterType.YETI;
        int peopleEaten2 = 11;
        String favoriteFood2 = "Avocado";
        Monster testMonster2 = new Monster(name2, type2, peopleEaten2, favoriteFood2);

        // Add both
        testDao.addMonster(monsterId1, testMonster1);
        testDao.addMonster(monsterId2, testMonster2);

        // Act
        List allMonsters = testDao.getAllMonsters();

        // Assert
        assertEquals(2, allMonsters.size(), "Should be 2 monsters in total.");
        assertNotNull(allMonsters, "List should not be null.");

        assertTrue(allMonsters.contains(testMonster1));
        assertTrue(allMonsters.contains(testMonster2));
    }

    @Test
    public void testUpdateMonster(){
        // Arrange
        int monsterId1 = 1;
        String name = "Ada";
        MonsterType type = MonsterType.VAMPIRE;
        int peopleEaten = 10;
        String favoriteFood = "lemon";
        Monster testMonster1 = new Monster(name, type, peopleEaten, favoriteFood);

        // Second Monster
        int monsterId2 = 2;
        String name2 = "Charles";
        MonsterType type2 = MonsterType.YETI;
        int peopleEaten2 = 11;
        String favoriteFood2 = "Avocado";
        Monster testMonster2 = new Monster(name2, type2, peopleEaten2, favoriteFood2);

        // Add the first one.
        testDao.addMonster(monsterId1, testMonster1);

        // Act
        testDao.updateMonster(monsterId1, testMonster2);

        // Assert
        Monster currentMonster = testDao.getMonster(monsterId1);
        assertEquals(currentMonster, testMonster2, "The monster should be 2nd monster");
    }

    @Test
    public void testRemoveMonster(){
        // Arrange
        int monsterId1 = 1;
        String name = "Ada";
        MonsterType type = MonsterType.VAMPIRE;
        int peopleEaten = 10;
        String favoriteFood = "lemon";
        Monster testMonster1 = new Monster(name, type, peopleEaten, favoriteFood);

        // Second Monster
        int monsterId2 = 2;
        String name2 = "Charles";
        MonsterType type2 = MonsterType.YETI;
        int peopleEaten2 = 11;
        String favoriteFood2 = "Avocado";
        Monster testMonster2 = new Monster(name2, type2, peopleEaten2, favoriteFood2);

        // Add both
        testDao.addMonster(monsterId1, testMonster1);
        testDao.addMonster(monsterId2, testMonster2);

        // Act
        // Only remove the first one and keep the second one.
        Monster removedMonster = testDao.removeMonster(monsterId1);

        // Assert
        // Size of the dao should be 1.
        List allMonsters = testDao.getAllMonsters();
        assertEquals(1, allMonsters.size());
        // First one should be gone.
        assertFalse(allMonsters.contains(testMonster1));
        // Second one should be unchanged.
        assertTrue(allMonsters.contains(testMonster2));
            // Monster number should stay the same.
        assertEquals(testMonster2, testDao.getMonster(monsterId2));
    }
}