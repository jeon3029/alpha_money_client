package com.hongik.alpha_money.DataStructure;

/**
 * Created by Patabear on 2016-11-08.
 */

/* category :   00 미분류
                    10 식비
                    11 밥
                    12 음료
                    13 디저트
                    20 생활비
                    21 생활용품
                    22 교육
                    23 교통
                    24 통신
                    30 여가
                    31 문화
                    32 건강
                    33 의류
                    34 미용
*/

public class Category {
    public Category() {}

    public String GetCategoryName(String category) {
        int big = Integer.parseInt(category.substring(0,1));
        int small = Integer.parseInt(category.substring(1,2));

        switch (big) {
            case 0:
                return "미분류";
            case 1:
                switch (small) {
                    case 0:
                        return "식비";
                    case 1:
                        return "밥";
                    case 2:
                        return "음료";
                    case 3:
                        return "디저트";
                }
                break;
            case 2:
                switch (small) {
                    case 0:
                        return "생활비";
                    case 1:
                        return "생활용품";
                    case 2:
                        return "교육";
                    case 3:
                        return "교통";
                    case 4:
                        return "통신";
                }
                break;
            case 3:
                switch (small) {
                    case 0:
                        return "여가";
                    case 1:
                        return "문화";
                    case 2:
                        return "건강";
                    case 3:
                        return "의류";
                    case 4:
                        return "미용";
                }
                break;
        }
        return " ";
    }
}
