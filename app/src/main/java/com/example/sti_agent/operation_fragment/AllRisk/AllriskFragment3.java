package com.example.sti_agent.operation_fragment.AllRisk;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.sti_agent.MainActivity;
import com.example.sti_agent.Model.AllRisk.AllriskPolicy;
import com.example.sti_agent.Model.AllRisk.ItemDetail;
import com.example.sti_agent.Model.AllRisk.Personal_Detail_allrisk;
import com.example.sti_agent.R;
import com.example.sti_agent.UserPreferences;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.shuhart.stepview.StepView;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;

public class AllriskFragment3 extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String SELECTED_ITEM = "seleted_item";
    private static final String PREMIUM_AMOUNT = "amount";

    // TODO: Rename and change types of parameters
    private String selectedItem;
    private String p_amount;


    @BindView(R.id.qb_form_layout3)
    FrameLayout mQbFormLayout3;
    @BindView(R.id.step_view)
    StepView mStepView;
    @BindView(R.id.selected_item_txt_a3)
    TextView mSelectedItemTxtA3;
    @BindView(R.id.amount_a3)
    TextView mAmountA3;
    @BindView(R.id.btn_layout3_a3)
    LinearLayout mBtnLayout3A3;
    @BindView(R.id.v_back_btn3_a3)
    Button mVBackBtn3A3;
    @BindView(R.id.v_next_btn3_a3)
    Button mVNextBtn3A3;
    @BindView(R.id.progressbar)
    AVLoadingIndicatorView mProgressbar;
    @BindView(R.id.fabAddItem_a3)
    FloatingActionButton fabAddItem_a3;






    private  int currentStep=2;
    Realm realm;

    AllriskPolicy id=new AllriskPolicy();
    String primaryKey=id.getId();
    private UserPreferences userPreferences;


    public AllriskFragment3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Dashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static AllriskFragment3 newInstance(String param1, String param2) {
        AllriskFragment3 fragment = new AllriskFragment3();
        Bundle args = new Bundle();
        args.putString(SELECTED_ITEM, param1);
        args.putString(PREMIUM_AMOUNT, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        UserPreferences userPreferences=new UserPreferences(getContext());
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedItem = getArguments().getString(SELECTED_ITEM);
            double oldquote= Double.parseDouble(userPreferences.getTempAllRiskQuotePrice());
            p_amount=getArguments().getString(PREMIUM_AMOUNT);

            double newquote=Double.parseDouble(getArguments().getString(PREMIUM_AMOUNT));
            double total_quote=oldquote+newquote;
            userPreferences.setTempAllRiskQuotePrice(String.valueOf(total_quote));
            Log.i("PrimaryVariable",primaryKey);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_allrisk3, container, false);
        ButterKnife.bind(this,view);
        //        mStepView next registration step
        mStepView.go(currentStep, true);

        realm=Realm.getDefaultInstance();
        userPreferences=new UserPreferences(getContext());

        mSelectedItemTxtA3.setText(selectedItem);
        if(p_amount==null){
            p_amount="000";
            String format = p_amount + ".00";
            mAmountA3.setText(format);
        }else {
            //String format = p_amount ;

            NumberFormat nf = NumberFormat.getNumberInstance(new Locale("en", "US"));
            nf.setMaximumFractionDigits(2);
            DecimalFormat df = (DecimalFormat) nf;
            String v_price = "₦" + df.format(Double.valueOf(p_amount));
            mAmountA3.setText(v_price);
        }

        //Toast.makeText(getActivity(), "Click the Add Button, to add more Item", Toast.LENGTH_LONG).show();

        setViewActions();

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i("backPress_KeyCode", "keyCode: " + keyCode);
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    Log.i("backPress", "onKey Back listener is working!!!");
                    userPreferences.setTempAllRiskQuotePrice("0.0");
                    startActivity(new Intent(getActivity(), MainActivity.class));
                    return true;
                }
                return false;
            }
        });

        return  view;
    }



    //seting onclicks listeners
    private void setViewActions() {

        mVNextBtn3A3.setOnClickListener(this);
        mVBackBtn3A3.setOnClickListener(this);
        fabAddItem_a3.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_next_btn3_a3:
//                send quote to client and sti
                mSummary();
                break;

            case R.id.v_back_btn3_a3:
                if (currentStep > 0) {
                    currentStep--;
                }
                mStepView.done(false);
                mStepView.go(currentStep, true);

                Fragment quoteBuyFragment3 = new AllriskFragment3();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_allrisk_form_container, quoteBuyFragment3);
                ft.commit();

                break;

            case R.id.fabAddItem_a3:

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        Personal_Detail_allrisk personal_detail_allrisk=new Personal_Detail_allrisk();

                        if(realm.isEmpty()){
                            personal_detail_allrisk.setPrefix(userPreferences.getAllRiskIPrefix());
                            personal_detail_allrisk.setFirst_name(userPreferences.getAllRiskIFirstName());
                            personal_detail_allrisk.setLast_name(userPreferences.getAllRiskILastName());
                            personal_detail_allrisk.setEmail(userPreferences.getAllRiskIEmail());
                            personal_detail_allrisk.setGender(userPreferences.getAllRiskIGender());
                            personal_detail_allrisk.setPhone(userPreferences.getAllRiskIPhoneNum());
                            personal_detail_allrisk.setResident_address(userPreferences.getAllRiskIResAdrr());
                            personal_detail_allrisk.setNext_of_kin(userPreferences.getAllRiskINextKin());
                            personal_detail_allrisk.setPolicy_type(userPreferences.getAllRiskPtype());
                            personal_detail_allrisk.setCompany_name(userPreferences.getAllRiskICompanyName());
                            personal_detail_allrisk.setMailing_addr(userPreferences.getAllRiskIMailingAddr());
                            personal_detail_allrisk.setTin_num(userPreferences.getAllRiskITinNumber());
                            personal_detail_allrisk.setOffice_address(userPreferences.getAllRiskIOff_addr());
                            personal_detail_allrisk.setContact_person(userPreferences.getAllRiskIContPerson());
                            //Item List
                            ItemDetail itemDetail=new ItemDetail();
                            itemDetail.setStartDate(userPreferences.getAllRiskStartDate());
                            itemDetail.setItem(userPreferences.getAllRiskItemType());
                            itemDetail.setSerial(userPreferences.getAllRiskSerialNo());
                            itemDetail.setValue(userPreferences.getAllRiskItemValue());

                            RealmList<ItemDetail>itemDetailList=new RealmList<>();
                            itemDetailList.add(itemDetail);

                            personal_detail_allrisk.setItemDetails(itemDetailList);

                                    final Personal_Detail_allrisk personal_detail_allrisk1=realm.copyToRealm(personal_detail_allrisk);

                                    AllriskPolicy allriskPolicy=realm.createObject(AllriskPolicy.class,primaryKey);
                                    allriskPolicy.setAgent_id("1");
                                    allriskPolicy.setQuote_price(userPreferences.getTempAllRiskQuotePrice());
                                    allriskPolicy.setPayment_source("paystack");
                                    allriskPolicy.setPin("11234");

                                    Log.i("Primary1",primaryKey);

                                    allriskPolicy.getPersonal_detail_allrisks().add(personal_detail_allrisk1);



                        }else if(!realm.isEmpty()){



                            //Item List
                            ItemDetail itemDetail=new ItemDetail();
                            itemDetail.setStartDate(userPreferences.getAllRiskStartDate());
                            itemDetail.setItem(userPreferences.getAllRiskItemType());
                            itemDetail.setSerial(userPreferences.getAllRiskSerialNo());
                            itemDetail.setValue(userPreferences.getAllRiskItemValue());

                            RealmList<ItemDetail>itemDetailList=new RealmList<>();
                            itemDetailList.add(itemDetail);

                            personal_detail_allrisk.setItemDetails(itemDetailList);

                            final Personal_Detail_allrisk personal_detail_allrisk1=realm.copyToRealm(personal_detail_allrisk);

                            AllriskPolicy allriskPolicy=realm.createObject(AllriskPolicy.class,primaryKey);
                            allriskPolicy.setAgent_id("1");
                            allriskPolicy.setQuote_price(userPreferences.getTempAllRiskQuotePrice());
                            allriskPolicy.setPayment_source("paystack");
                            allriskPolicy.setPin("11234");

                            Log.i("Primary1",primaryKey);

                            allriskPolicy.getPersonal_detail_allrisks().add(personal_detail_allrisk1);


                            showMessage(primaryKey);



                        }else {
                            showMessage("Invalid transaction");
                        }

                    }
                });


                mStepView.done(false);
                mStepView.go(1, true);

                Fragment allriskFragment2 = new AllriskFragment2();
                FragmentTransaction ftrans = getFragmentManager().beginTransaction();
                ftrans.replace(R.id.fragment_allrisk_form_container, allriskFragment2);
                ftrans.commit();
                break;
        }
    }

    private void mSummary() {

        mBtnLayout3A3.setVisibility(View.GONE);
        mProgressbar.setVisibility(View.VISIBLE);


        Personal_Detail_allrisk personal_detail_allrisk=new Personal_Detail_allrisk();

        personal_detail_allrisk.setPrefix(userPreferences.getAllRiskIPrefix());
        personal_detail_allrisk.setFirst_name(userPreferences.getAllRiskIFirstName());
        personal_detail_allrisk.setLast_name(userPreferences.getAllRiskILastName());
        personal_detail_allrisk.setEmail(userPreferences.getAllRiskIEmail());
        personal_detail_allrisk.setGender(userPreferences.getAllRiskIGender());
        personal_detail_allrisk.setPhone(userPreferences.getAllRiskIPhoneNum());
        personal_detail_allrisk.setState(userPreferences.getAllRiskIState());
        personal_detail_allrisk.setResident_address(userPreferences.getAllRiskIResAdrr());
        personal_detail_allrisk.setNext_of_kin(userPreferences.getAllRiskINextKin());
        personal_detail_allrisk.setPolicy_type(userPreferences.getAllRiskPtype());
        personal_detail_allrisk.setCompany_name(userPreferences.getAllRiskICompanyName());
        personal_detail_allrisk.setMailing_addr(userPreferences.getAllRiskIMailingAddr());
        personal_detail_allrisk.setTin_num(userPreferences.getAllRiskITinNumber());
        personal_detail_allrisk.setOffice_address(userPreferences.getAllRiskIOff_addr());
        personal_detail_allrisk.setContact_person(userPreferences.getAllRiskIContPerson());
        personal_detail_allrisk.setPicture(userPreferences.getAllRiskPersonalImage());
        //Item List
        ItemDetail itemDetail=new ItemDetail();
        itemDetail.setStartDate(userPreferences.getAllRiskStartDate());
        itemDetail.setItem(userPreferences.getAllRiskItemType());
        itemDetail.setSerial(userPreferences.getAllRiskSerialNo());
        itemDetail.setValue(userPreferences.getAllRiskItemValue());
        itemDetail.setReceipt(userPreferences.getAllRiskItemReceipt());
        itemDetail.setDesc_item(userPreferences.getAllRiskItemDesc());
        itemDetail.setImei(userPreferences.getAllRiskItemImei());

        RealmList<ItemDetail>itemDetailList=new RealmList<>();
        itemDetailList.add(itemDetail);

        personal_detail_allrisk.setItemDetails(itemDetailList);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {



                final Personal_Detail_allrisk personal_detail_allrisk1=realm.copyToRealm(personal_detail_allrisk);

                AllriskPolicy allriskPolicy=realm.createObject(AllriskPolicy.class,primaryKey);
                allriskPolicy.setAgent_id("1");
                allriskPolicy.setQuote_price(userPreferences.getTempAllRiskQuotePrice());
                allriskPolicy.setPayment_source("paystack");
                allriskPolicy.setPin("11234");

                allriskPolicy.getPersonal_detail_allrisks().add(personal_detail_allrisk1);

            }
        });


        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_allrisk_form_container, AllriskFragment4.newInstance(primaryKey));
        ft.commit();




    }




    private void showMessage(String s) {
        Snackbar.make(mQbFormLayout3, s, Snackbar.LENGTH_SHORT).show();
    }




}
