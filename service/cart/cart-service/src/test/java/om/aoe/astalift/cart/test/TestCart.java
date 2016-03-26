package om.aoe.astalift.cart.test;

import com.aoe.astalift.cart.CartServiceConfigHook;
import com.aoe.astalift.cart.entity.Cart;
import com.aoe.astalift.cart.entity.CartItem;
import com.aoe.astalift.product.dto.util.ProductDtoUtil;
import com.aoe.astalift.product.entity.Product;
import com.aoe.astalift.product.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by joey on 16-3-18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(CartServiceConfigHook.class)
public class TestCart {

    @Resource
    RedisTemplate<Integer, Cart> cartCache;

    @Resource
    ProductRepository productRepository;

    @Test
    public void foo() throws JsonProcessingException {
        Assert.notNull(cartCache);

        Cart cart = new Cart();
        cart.setUserId(1);

        List<Product> all = productRepository.findAll();

        System.out.println(all.size());
        for (Product product : all) {
            cart.addProduct(ProductDtoUtil.createProductInfo(product));
        }

        cartCache.opsForValue().set(1, cart);

        Cart cart1 = cartCache.opsForValue().get(1);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("===>" + objectMapper.writeValueAsString(cart1));

    }
}
