package com.devpro.btvn.model;

import com.devpro.btvn.R; // Nhớ import đúng gói R của dự án để gọi ảnh drawable
import java.util.ArrayList;
import java.util.List;

public class FakeData {

    public static List<Product> getData() {
        List<Product> list = new ArrayList<>();

        list.add(new Product(
                1,
                R.drawable.caramel_frappucino,
                "Caramel Frappuccino",
                "Món cà phê đá xay kết hợp sốt caramel ngọt ngào, lớp kem tươi béo ngậy phủ bên trên mang lại trải nghiệm mát lạnh sảng khoái.",
                "10 mins",
                "Easy",
                "1 person",
                true
        ));

        list.add(new Product(
                2,
                R.drawable.espresso,
                "Espresso Shot",
                "Cà phê nguyên chất đậm đặc được chiết xuất dưới áp suất cao, giữ trọn vẹn hương vị đắng đậm và lớp bọt crema quyến rũ.",
                "5 mins",
                "Medium",
                "1 person",
                false
        ));

        list.add(new Product(
                3,
                R.drawable.hot_chocolate,
                "Premium Hot Chocolate",
                "Sô-cô-la nóng đậm đà được pha chế từ bột cacao nguyên chất cùng sữa tươi ấm, mang lại cảm giác thư giãn ấm áp.",
                "8 mins",
                "Easy",
                "2 people",
                true
        ));

        list.add(new Product(
                4,
                R.drawable.ice_coffee,
                "Traditional Ice Coffee",
                "Cà phê đá truyền thống pha phin đậm vị, kết hợp hoàn hảo với đá mát lạnh giúp bạn tỉnh táo tức thì cho ngày dài năng động.",
                "7 mins",
                "Easy",
                "1 person",
                false
        ));

        list.add(new Product(
                5,
                R.drawable.mixed_black_coffee,
                "Mixed Black Coffee",
                "Sự pha trộn độc đáo giữa các hạt cà phê Arabica và Robusta hảo hạng, mang lại hậu vị sâu lắng và hương thơm nồng nàn.",
                "12 mins",
                "Medium",
                "2 people",
                false
        ));

        return list;
    }

    public static Product getProduct(int id) {
        var data = getData();
        for (Product product : data) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}