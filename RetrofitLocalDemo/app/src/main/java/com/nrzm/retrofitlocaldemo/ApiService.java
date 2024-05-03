package com.nrzm.retrofitlocaldemo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    /* 식품영양성분DB(NEW)
	 * sample 문자열을 발급받은 API Key 로 변경할 수 있습니다
	 */
    @GET("api/sample/I2790/json/1/1000")
    Call<FoodNutrition> foodList();

}