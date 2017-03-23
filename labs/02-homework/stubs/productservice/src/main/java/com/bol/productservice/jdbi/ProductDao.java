package com.bol.productservice.jdbi;

import com.bol.productservice.api.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ProductDao {
    private final ScheduledExecutorService scheduler;
    private final List<Product> products = new ArrayList<>();
    private final Random random = new Random();
    private final Logger logger = LoggerFactory.getLogger(ProductDao.class);

    public ProductDao() {
        initializeStubProducts();
        scheduler = Executors.newScheduledThreadPool(1);
        scheduleRandomProductUpdater();
    }

    public Optional<Product> getProduct(String ean) {
        return products.stream()
                .filter(product -> product.getEan().equals(ean))
                .findFirst();
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    private void scheduleRandomProductUpdater() {
        scheduler.scheduleAtFixedRate(
                this::updatePricesAndAvailabilityForRandomProduct,
                0,
                500,
                TimeUnit.MILLISECONDS
        );
    }

    private void initializeStubProducts() {
        products.add(new Product("9200000067742249", "Nintendo Switch 32GB Console - Grijs", new BigDecimal(375)));
        products.add(new Product("9200000064857631", "Sony PlayStation 4 Slim Console 500GB - Zwart PS4", new BigDecimal(289)));
        products.add(new Product("9200000064873664", "Sony PlayStation 4 Pro Console - 1TB - PS4", new BigDecimal(399)));

        products.add(new Product("9200000073662955", "The Legend Of Zelda: Breath of the Wild - Switch", new BigDecimal(59.99)));
        products.add(new Product("9200000028880319", "The Legend Of Zelda: Breath of the Wild - Wii U", new BigDecimal(59.99)));
        products.add(new Product("9200000045947778", "Horizon: Zero Dawn - PS4", new BigDecimal(59.99)));
        products.add(new Product("9200000028809047", "Grand Theft Auto V (GTA 5) - PS4", new BigDecimal(44.99)));
        products.add(new Product("9200000045907431", "Ghost Recon: Wildlands - PS4", new BigDecimal(59.99)));

        products.add(new Product("9200000073684225", "Mario Kart 8 DeLuxe - Switch", new BigDecimal(59.99)));
        products.add(new Product("9200000073698260", "1-2 Switch - Switch", new BigDecimal(45.99)));
        products.add(new Product("9200000073666630", "Super Mario Odyssey - Switch", new BigDecimal(59.99)));

        products.add(new Product("9200000010559157", "Pampers Baby Dry maandbox - Luiers", new BigDecimal(45.19)));
        products.add(new Product("9200000058374506", "Pampers Premium Protection maandbox - Luiers", new BigDecimal(45.19)));
        products.add(new Product("9200000024753993", "Pampers Sensitive - 840 Stuks (15x56) - Babydoekjes", new BigDecimal(25.11)));
        products.add(new Product("9200000035603838", "Zwitsal Billendoekjes Lotion - 24 x 72 stuks - Baby - Voordeelverpakking", new BigDecimal(58.99)));
    }

    private void updatePricesAndAvailabilityForRandomProduct() {
        updatePriceAndAvailabilityForProductRandomly(products.get(random.nextInt(products.size())));
    }

    private void updatePriceAndAvailabilityForProductRandomly(Product product) {
        product.setConsumerPrice(getNewPrice(product.getSuggestedRetailPrice()));
        product.setAvailable(random.nextDouble() > 0.2);
        logger.info("New price for product [{}] is set to [{}], availability is set to [{}]",
                product.getName(), product.getConsumerPrice(), product.isAvailable());
    }

    private BigDecimal getNewPrice(BigDecimal basePrice) {
        double percentageChange = -25 + random.nextInt(40);
        logger.debug("For price [{}] the price will change with [{}%]",
                basePrice, percentageChange);
        return basePrice.multiply(new BigDecimal(1 + (percentageChange / 100)));
    }
}
