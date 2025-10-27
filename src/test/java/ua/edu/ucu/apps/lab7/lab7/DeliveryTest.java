package ua.edu.ucu.apps.lab7.lab7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import ua.edu.ucu.apps.lab7.flower.Store;
import ua.edu.ucu.apps.lab7.flower.Flower;
import ua.edu.ucu.apps.lab7.flower.FlowerColor;
import ua.edu.ucu.apps.lab7.flower.FlowerType;

class StoreTest {

    private Store store;
    private Flower redRose;
    private Flower blueRose;
    private Flower redTulip;

    @BeforeEach
    void setUp() {
        store = new Store();
        redRose = new Flower(FlowerColor.RED, FlowerType.ROSE, 50, 100);
        blueRose = new Flower(FlowerColor.BLUE, FlowerType.ROSE, 45, 90);
        redTulip = new Flower(FlowerColor.RED, FlowerType.TULIP, 40, 80);
    }

    @Test
    void testAddFlower() {
        store.addFlower(redRose);
        List<Flower> roses = store.searchByType(FlowerType.ROSE);
        assertEquals(1, roses.size(), "Store should have one rose");
    }

    @Test
    void testSearchByColor() {
        store.addFlower(redRose);
        store.addFlower(blueRose);
        store.addFlower(redTulip);
        
        List<Flower> redFlowers = store.searchByColor(FlowerColor.RED);
        assertEquals(2, redFlowers.size(), "Should find 2 red flowers");
    }

    @Test
    void testSearchByType() {
        store.addFlower(redRose);
        store.addFlower(blueRose);
        store.addFlower(redTulip);
        
        List<Flower> roses = store.searchByType(FlowerType.ROSE);
        assertEquals(2, roses.size(), "Should find 2 roses");
    }

    @Test
    void testSearchByColorNoResults() {
        store.addFlower(redRose);
        
        List<Flower> blueFlowers = store.searchByColor(FlowerColor.BLUE);
        assertTrue(blueFlowers.isEmpty(), "Should find no blue flowers");
    }

    @Test
    void testSearchByTypeNoResults() {
        store.addFlower(redRose);
        
        List<Flower> chamomiles = store.searchByType(FlowerType.CHAMOMILE);
        assertTrue(chamomiles.isEmpty(), "Should find no chamomiles");
    }

    @Test
    void testAddMultipleFlowers() {
        store.addFlower(redRose);
        store.addFlower(blueRose);
        store.addFlower(redTulip);
        
        List<Flower> allRoses = store.searchByType(FlowerType.ROSE);
        List<Flower> allTulips = store.searchByType(FlowerType.TULIP);
        
        assertEquals(2, allRoses.size());
        assertEquals(1, allTulips.size());
    }

    @Test
    void testSearchReturnsCorrectFlowers() {
        store.addFlower(redRose);
        store.addFlower(blueRose);
        
        List<Flower> redFlowers = store.searchByColor(FlowerColor.RED);
        assertEquals(FlowerColor.RED, redFlowers.get(0).getColor());
    }
}