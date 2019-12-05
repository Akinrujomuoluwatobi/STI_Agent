package com.example.sti_agent.retrofit_interface;

import com.example.sti_agent.Model.AllRisk.AllRiskPost.AllRiskPostHead;
import com.example.sti_agent.Model.AllRisk.FormSuccessDetail.BuyQuoteFormGetHead_AllRisk;
import com.example.sti_agent.Model.AllRisk.QouteHeadAllrisk;
import com.example.sti_agent.Model.Auth.AgentDataHead;
import com.example.sti_agent.Model.Auth.ChangePassPost;
import com.example.sti_agent.Model.Auth.LoginModel.UserGetObj;
import com.example.sti_agent.Model.Auth.LoginModel.UserPostObj;
import com.example.sti_agent.Model.Auth.RegisterObj;
import com.example.sti_agent.Model.CustomerModel.CustomerModel;
import com.example.sti_agent.Model.Etic.EticPost.EticPostHead;
import com.example.sti_agent.Model.Etic.FormSuccessDetail.BuyQuoteFormGetHead_Etic;
import com.example.sti_agent.Model.Etic.QouteHeadEtic;
import com.example.sti_agent.Model.ForgetPass.UserHName;
import com.example.sti_agent.Model.ForgetPass.UserHNew;
import com.example.sti_agent.Model.ForgetPass.UserHead;
import com.example.sti_agent.Model.Marine.FormSuccessDetail.BuyQuoteFormGetHead_Marine;
import com.example.sti_agent.Model.Marine.MarinePost.MarinePostHead;
import com.example.sti_agent.Model.Marine.MarineQuote;
import com.example.sti_agent.Model.Marine.QouteHeadMarine;
import com.example.sti_agent.Model.Swiss.FormSuccessDetail.BuyQuoteFormGetHead_Swiss;
import com.example.sti_agent.Model.Swiss.QouteHeadSwiss;
import com.example.sti_agent.Model.Swiss.SwissPost.SwissPostHead;
import com.example.sti_agent.Model.TransactionHistroy.TransactionHead;
import com.example.sti_agent.Model.Vehicle.BrandType.VehicleBrandType;
import com.example.sti_agent.Model.Vehicle.FormSuccessDetail.BuyQuoteFormGetHead;
import com.example.sti_agent.Model.Vehicle.Quote.PostVehicleData;
import com.example.sti_agent.Model.Vehicle.Quote.QouteHead;
import com.example.sti_agent.Model.Vehicle.VehicleBrand.Vehicles_Brand;
import com.example.sti_agent.Model.Vehicle.VehiclePost.VehiclePostHead;
import com.example.sti_agent.Model.WalletModel.FundWallet;
import com.example.sti_agent.Model.WalletModel.GetWalletFunded;
import com.example.sti_agent.Model.WalletModel.WalletObj;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiInterface {
    @POST("users/agent")
    Call<AgentDataHead> register(@Body RegisterObj regPostData, @HeaderMap HashMap<String, String> headerMap);

    @POST("users/login")
    Call<UserGetObj> login(@Body UserPostObj userPostObj, @HeaderMap HashMap<String, String> headerMap);

    @POST("fund-wallet")
    Call<GetWalletFunded> fund_wallet(@Header("Authorization") String token, @Body FundWallet fundWallet);

    @GET("wallet-history")
    Call<WalletObj> wallet_history(@Header("Authorization") String token);

    @GET("vehicle-brands")
    Call<Vehicles_Brand> vehicle_brand();

    @GET("vehicle-brand-types/{brand_id}")
    Call<VehicleBrandType> brand_type(@Path("brand_id") int brand_id);

    @POST("change-password")
    Call<ResponseBody> change_password(@Header("Authorization") String token, @Body ChangePassPost changePassPost);


    //get transaction history
    @GET("transaction-history")
    Call<TransactionHead> transaction_hist(@Header("Authorization") String token);

    //Buy Vehicle Policy
    @POST("agents/buy-vehicle-policy")
    Call<BuyQuoteFormGetHead> vehicle_policy(@Header("Authorization") String token, @Body VehiclePostHead vehiclePostHead);

    @FormUrlEncoded
    @POST("update-transaction")
    Call<ResponseBody> update_payment(@Header("Authorization") String token, @Field("reference") String reference,
                                      @Field("provider_reference") String provider_reference,
                                      @Field("status") String status,
                                      @Field("policy_type") String policy_type);

    //Buy Swiss Policy
    @POST("agents/buy-swiss-policy")
    Call<BuyQuoteFormGetHead_Swiss> swiss_policy(@Header("Authorization") String token, @Body SwissPostHead swissPostHead);

    //Buy Marine Policy
    @POST("agents/buy-marine-policy")
    Call<BuyQuoteFormGetHead_Marine> marine_policy(@Header("Authorization") String token, @Body MarinePostHead marinePostHead);

    //Buy Travel Policy
    @POST("agents/buy-travel-policy")
    Call<BuyQuoteFormGetHead_Etic> etic_policy(@Header("Authorization") String token, @Body EticPostHead eticPostHead);

    @GET("agents/customers")
    Call<CustomerModel> fetchCustomers(@Header("Authorization") String token);

    @POST("get-vehicle-quote")
    Call<QouteHead> getVehicleQuote(@Header("Authorization") String token, @Body PostVehicleData getVehicleQuote);

    //Swiss Quote
    @FormUrlEncoded
    @POST("get-swiss-quote")
    Call<QouteHeadSwiss> swiss_quote(@Header("Authorization") String token, @Field("date_of_birth") String date_of_birth);

    //Marine Quote
    @POST("get-marine-quote")
    Call<QouteHeadMarine> marine_quote(@Header("Authorization") String token, @Body MarineQuote getMarineQuote);

    //Travel Quote
    @FormUrlEncoded
    @POST("get-travel-quote")
    Call<QouteHeadEtic> etic_quote(@Header("Authorization") String token, @Field("sum_insured") long sum_insured);

    //AllRisk Quote
    @FormUrlEncoded
    @POST("get-all-risk-quote")
    Call<QouteHeadAllrisk> allrisk_quote(@Header("Authorization") String token, @Field("sum_insured") String sum_insrured);

    //Buy AllRisk Policy
    @POST("agents/buy-all-risk-policy")
    Call<BuyQuoteFormGetHead_AllRisk> allrisk_policy(@Header("Authorization") String token, @Body AllRiskPostHead allRiskPostHead);

    @POST("users/initiate-password")
    Call<UserHName> initiate_forget_pass(@Body UserHead userHead);

    @POST("users/update-password")
    Call<ResponseBody> reset_pass(@Body UserHNew userHNew);
}
