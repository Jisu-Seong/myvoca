package com.example.vocaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.vocaapi.dto.CartItemListDTO;
import com.example.vocaapi.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
        @Query(value = "select " + " new com.example.mallapi.dto.CartItemListDTO" +
                        "(ci.cino, ci.qty, p.pno, p.pname, p.price, pi.fileName) " +
                        " from " +
                        " CartItem ci inner join Cart mc on ci.cart = mc " +
                        " left join Product p on ci.product = p " +
                        " left join p.imageList pi" +
                        " where " +
                        " mc.owner.email = :email and pi.ord = 0 " +
                        " order by ci desc ", nativeQuery = true)
        public List<CartItemListDTO> getItemsOfCartDTOByEmail(@Param("email") String email);

        @Query(value = "select " +
                        " ci " +
                        " from " +
                        " CartItem ci inner join Cart c on ci.cart = c " +
                        " where " +
                        " c.owner.email = :email and ci.product.pno = :pno", nativeQuery = true)
        public CartItem getItemOfPno(@Param("email") String email, @Param("pno") Long pno);

        @Query(value = "select " +
                        " c.cno " +
                        " from " +
                        " Cart c inner join CartItem ci on ci.cart = c " +
                        " where " +
                        " ci.cino = :cino", nativeQuery = true)
        public Long getCartFromItem(@Param("cino") Long cino);

        @Query(value = "select new com.example.mallapi.dto.CartItemListDTO(ci.cino, ci.qty, p.pno, p.pname, p.price, pi.fileName) "
                        +
                        " from " +
                        " CartItem ci inner join Cart mc on ci.cart = mc " +
                        " left join Product p on ci.product = p" +
                        " left join p.imageList pi" +
                        " where " +
                        " mc.cno = :cno and pi.ord = 0 " +
                        " order by ci desc ", nativeQuery = true)
        public List<CartItemListDTO> getItemsOfCartDTOByCart(@Param("cno") Long cno);
}
