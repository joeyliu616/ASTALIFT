package com.aoe.astalift.product.constants;

/**
 * Created by joey on 16-3-17.
 */
public interface ProductError {
    interface ProductNotFound {
        Integer code = 30001;
        String msg = "产品不存在";
    }
}
