package ua.edu.ucu.apps.lab7.lab7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.edu.ucu.apps.lab7.flower.BasketDecorator;
import ua.edu.ucu.apps.lab7.flower.Flower;
import ua.edu.ucu.apps.lab7.flower.FlowerBucket;
import ua.edu.ucu.apps.lab7.flower.FlowerColor;
import ua.edu.ucu.apps.lab7.flower.FlowerType;
import ua.edu.ucu.apps.lab7.flower.PaperDecorator;
import ua.edu.ucu.apps.lab7.flower.RibbonDecorator;

class DecoratorTest {

    private FlowerBucket bucket;
    private Flower flower;

    @BeforeEach
    void setUp() {
        bucket = new FlowerBucket();
        flower = new Flower(FlowerColor.RED, FlowerType.ROSE, 50, 100);
        bucket.addFlowers(flower);
    }

    @Test
    void testBasketDecoratorPrice() {
        BasketDecorator decorated = new BasketDecorator(bucket);
        assertEquals(104, decorated.getPrice());
    }

    @Test
    void testPaperDecoratorPrice() {
        PaperDecorator decorated = new PaperDecorator(bucket);
        assertEquals(113, decorated.getPrice());
    }

    @Test
    void testRibbonDecoratorPrice() {
        RibbonDecorator decorated = new RibbonDecorator(bucket);
        assertEquals(140, decorated.getPrice());
    }

    @Test
    void testBasketDecoratorDescription() {
        bucket.setDescription("Beautiful roses");
        BasketDecorator decorated = new BasketDecorator(bucket);
        assertTrue(decorated.getDescription().contains("in basket"));
    }

    @Test
    void testPaperDecoratorDescription() {
        bucket.setDescription("Beautiful roses");
        PaperDecorator decorated = new PaperDecorator(bucket);
        assertTrue(decorated.getDescription().contains("wrapped in paper"));
    }

    @Test
    void testRibbonDecoratorDescription() {
        bucket.setDescription("Beautiful roses");
        RibbonDecorator decorated = new RibbonDecorator(bucket);
        assertTrue(decorated.getDescription().contains("with ribbon"));
    }

    @Test
    void testMultipleDecorators() {
        BasketDecorator basket = new BasketDecorator(bucket);
        PaperDecorator paper = new PaperDecorator(basket);
        RibbonDecorator ribbon = new RibbonDecorator(paper);
        
        assertEquals(157, ribbon.getPrice());
    }

    @Test
    void testDecoratorChainDescription() {
        bucket.setDescription("Roses");
        BasketDecorator basket = new BasketDecorator(bucket);
        PaperDecorator paper = new PaperDecorator(basket);
        
        String description = paper.getDescription();
        assertTrue(description.contains("in basket"));
        assertTrue(description.contains("wrapped in paper"));
    }
}