package com.sky.mapper;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Alan
 * @version 1.0
 * @create 2023/11/5 11:01
 */
@Mapper
public interface ShoppingCartMapper {

    List<ShoppingCart> list(ShoppingCart shoppingCart);

    @Update("update shopping_cart set number = #{number} where id = #{id}")
    void updateById(ShoppingCart cart);

    @Insert("insert into shopping_cart(user_id, name,dish_id, setmeal_id, dish_flavor,image, amount, number, create_time) " +
            "values(#{userId}, #{name}, #{dishId}, #{setmealId}, #{dishFlavor },#{image}, #{amount}, #{number}, #{createTime})")
    void insert(ShoppingCart shoppingCart);

    @Delete("delete from shopping_cart where user_id = #{userId}")
    void deleteByUserId(Long userId);
}
