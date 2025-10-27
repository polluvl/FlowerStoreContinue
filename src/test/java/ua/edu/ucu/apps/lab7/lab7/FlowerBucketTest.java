package ua.edu.ucu.apps.lab7.lab7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.edu.ucu.apps.lab7.flower.Flower;
import ua.edu.ucu.apps.lab7.flower.FlowerBucket;
import ua.edu.ucu.apps.lab7.flower.FlowerColor;
import ua.edu.ucu.apps.lab7.flower.FlowerType;

class FlowerBucketTest {

    private FlowerBucket bucket;
    private Flower rose;
    private Flower tulip;

    @BeforeEach
    void setUp() {
        bucket = new FlowerBucket();
        rose = new Flower(FlowerColor.RED, FlowerType.ROSE, 50, 100);
        tulip = new Flower(FlowerColor.BLUE, FlowerType.TULIP, 40, 80);
    }

    @Test
    void testAddFlowers() {
        bucket.addFlowers(rose);
        assertEquals(100, bucket.getPrice());
    }

    @Test
    void testGetPriceMultipleFlowers() {
        bucket.addFlowers(rose);
        bucket.addFlowers(tulip);
        assertEquals(180, bucket.getPrice());
    }

    @Test
    void testEmptyBucketPrice() {
        assertEquals(0, bucket.getPrice());
    }

    @Test
    void testSearchFlowerFound() {
        bucket.addFlowers(rose);
        bucket.addFlowers(tulip);
        
        Flower found = bucket.searchFlower(FlowerType.ROSE);
        assertNotNull(found);
        assertEquals(FlowerType.ROSE, found.getFlowerType());
    }

    @Test
    void testSearchFlowerNotFound() {
        bucket.addFlowers(rose);
        
        Flower found = bucket.searchFlower(FlowerType.CHAMOMILE);
        assertNull(found);
    }

    @Test
    void testSearchFlowerInEmptyBucket() {
        Flower found = bucket.searchFlower(FlowerType.ROSE);
        assertNull(found);
    }

    @Test
    void testAddMultipleFlowersOfSameType() {
        Flower rose2 = new Flower(FlowerColor.BLUE, FlowerType.ROSE, 45, 90);
        bucket.addFlowers(rose);
        bucket.addFlowers(rose2);
        
        assertEquals(190, bucket.getPrice());
    }

    @Test
    void testSearchReturnsFirstMatch() {
        Flower rose2 = new Flower(FlowerColor.BLUE, FlowerType.ROSE, 45, 90);
        bucket.addFlowers(rose);
        bucket.addFlowers(rose2);
        
        Flower found = bucket.searchFlower(FlowerType.ROSE);
        assertEquals(FlowerColor.RED, found.getColor());
    }
}