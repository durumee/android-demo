package com.nrzm.retrofitlocaldemo;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class FoodNutrition {

    @SerializedName("I2790")
    private FoodNutrtionInfo foodNutrtionInfo;
    public FoodNutrtionInfo getFoodNutrtionInfo() {
        return foodNutrtionInfo;
    }


    public static class FoodNutrtionInfo {
        @SerializedName("total_count")
        private String totalCount;
        @SerializedName("row")
        private List<FoodDetail> foodDetails;
        @SerializedName("RESULT")
        private Map<String, String> resultMsg;

        public String getTotalCount() {
            return totalCount;
        }

        public List<FoodDetail> getFoodDetails() {
            return foodDetails;
        }

        public Map<String, String> getResultMsg() {
            return resultMsg;
        }

        public static class FoodDetail {
            /**
             * 번호
             */
            @SerializedName("NUM")
            private String num;

            /**
             * 식품코드
             */
            @SerializedName("FOOD_CD")
            private String foodCode;

            /**
             * 지역명
             */
            @SerializedName("SAMPLING_REGION_NAME")
            private String samplingRegionName;

            /**
             * 채취월
             */
            @SerializedName("SAMPLING_MONTH_NAME")
            private String samplingMonthName;

            /**
             * 지역코드
             */
            @SerializedName("SAMPLING_REGION_CD")
            private String samplingRegionCode;

            /**
             * 채취월코드
             */
            @SerializedName("SAMPLING_MONTH_CD")
            private String samplingMonthCode;

            /**
             * 식품군
             */
            @SerializedName("GROUP_NAME")
            private String groupName;

            /**
             * 식품이름
             */
            @SerializedName("DESC_KOR")
            private String descKor;

            /**
             * 조사년도
             */
            @SerializedName("RESEARCH_YEAR")
            private String researchYear;

            /**
             * 제조사명
             */
            @SerializedName("MAKER_NAME")
            private String makerName;

            /**
             * 자료출처
             */
            @SerializedName("SUB_REF_NAME")
            private String subRefName;

            /**
             * 총내용량
             */
            @SerializedName("SERVING_SIZE")
            private String servingSize;

            /**
             * 총내용량단위
             */
            @SerializedName("SERVING_UNIT")
            private String servingUnit;

            /**
             * 열량(kcal)(1회제공량당)
             */
            @SerializedName("NUTR_CONT1")
            private String calories;

            /**
             * 탄수화물(g)(1회제공량당)
             */
            @SerializedName("NUTR_CONT2")
            private String carbohydrate;

            /**
             * 단백질(g)(1회제공량당)
             */
            @SerializedName("NUTR_CONT3")
            private String protein;

            /**
             * 지방(g)(1회제공량당)
             */
            @SerializedName("NUTR_CONT4")
            private String fat;

            /**
             * 당류(g)(1회제공량당)
             */
            @SerializedName("NUTR_CONT5")
            private String sugar;

            /**
             * 나트륨(mg)(1회제공량당)
             */
            @SerializedName("NUTR_CONT6")
            private String sodium;

            /**
             * 콜레스테롤(mg)(1회제공량당)
             */
            @SerializedName("NUTR_CONT7")
            private String cholesterol;

            /**
             * 포화지방산(g)(1회제공량당)
             */
            @SerializedName("NUTR_CONT8")
            private String saturatedFat;

            /**
             * 트랜스지방(g)(1회제공량당)
             */
            @SerializedName("NUTR_CONT9")
            private String transFat;


            public String getNum() {
                return num;
            }

            public String getFoodCode() {
                return foodCode;
            }

            public String getSamplingRegionName() {
                return samplingRegionName;
            }

            public String getSamplingMonthName() {
                return samplingMonthName;
            }

            public String getSamplingRegionCode() {
                return samplingRegionCode;
            }

            public String getSamplingMonthCode() {
                return samplingMonthCode;
            }

            public String getGroupName() {
                return groupName;
            }

            public String getDescKor() {
                return descKor;
            }

            public String getResearchYear() {
                return researchYear;
            }

            public String getMakerName() {
                return makerName;
            }

            public String getSubRefName() {
                return subRefName;
            }

            public String getServingSize() {
                return servingSize;
            }

            public String getServingUnit() {
                return servingUnit;
            }

            public String getCalories() {
                return calories;
            }

            public String getCarbohydrate() {
                return carbohydrate;
            }

            public String getProtein() {
                return protein;
            }

            public String getFat() {
                return fat;
            }

            public String getSugar() {
                return sugar;
            }

            public String getSodium() {
                return sodium;
            }

            public String getCholesterol() {
                return cholesterol;
            }

            public String getSaturatedFat() {
                return saturatedFat;
            }

            public String getTransFat() {
                return transFat;
            }
        }
    }
}
