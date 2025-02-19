package com.example.sti_agent.operation_fragment.AllRisk;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.sti_agent.BuildConfig;
import com.example.sti_agent.NetworkConnection;
import com.example.sti_agent.R;
import com.example.sti_agent.UserPreferences;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.shuhart.stepview.StepView;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AllriskFragment1 extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAA1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /** ButterKnife Code **/
    @BindView(R.id.qb_form_layout1)
    FrameLayout mQbFormLayout1;
    @BindView(R.id.step_view)
    StepView mStepView;
    @BindView(R.id.type_spinner_a1)
    Spinner mTypeSpinnerA1;
    @BindView(R.id.state_spinner)
    Spinner state_spinner;
    @BindView(R.id.prefix_spinner_a1)
    Spinner mPrefixSpinnerA1;
    @BindView(R.id.inputLayoutCompanyName_a1)
    TextInputLayout mInputLayoutCompanyNameA1;
    @BindView(R.id.companyname_editxt_a1)
    EditText mCompanynameEditxtA1;
    @BindView(R.id.inputLayoutTinNum_a1)
    TextInputLayout mInputLayoutTinNumA1;
    @BindView(R.id.tin_num_editxt_a1)
    EditText mTinNumEditxtA1;
    @BindView(R.id.inputLayoutOfficeAddr_a1)
    TextInputLayout mInputLayoutOfficeAddrA1;
    @BindView(R.id.office_addr_editxt_a1)
    EditText mOfficeAddrEditxtA1;
    @BindView(R.id.inputLayoutContactPerson_a1)
    TextInputLayout mInputLayoutContactPersonA1;
    @BindView(R.id.contact_person_editxt_a1)
    EditText mContactPersonEditxtA1;
    @BindView(R.id.inputLayoutFirstName_a1)
    TextInputLayout mInputLayoutFirstNameA1;
    @BindView(R.id.firstname_editxt_a1)
    EditText mFirstnameEditxtA1;
    @BindView(R.id.inputLayoutLastName_a1)
    TextInputLayout mInputLayoutLastNameA1;
    @BindView(R.id.lastname_editxt_a1)
    EditText mLastnameEditxtA1;
    @BindView(R.id.gender_spinner_a1)
    Spinner mGenderSpinnerA1;
    @BindView(R.id.inputLayoutResAddr_a1)
    TextInputLayout mInputLayoutResAddrA1;
    @BindView(R.id.residents_addr_editxt_a1)
    EditText mResidentsAddrEditxtA1;
    @BindView(R.id.inputLayoutNextKin_a1)
    TextInputLayout mInputLayoutNextKinA1;
    @BindView(R.id.next_kin_editxt_a1)
    EditText mNextKinEditxtA1;
    @BindView(R.id.inputLayoutPhone_a1)
    TextInputLayout mInputLayoutPhoneA1;
    @BindView(R.id.phone_no_editxt_a1)
    EditText mPhoneNoEditxtA1;
    @BindView(R.id.inputLayoutEmail_a1)
    TextInputLayout mInputLayoutEmailA1;
    @BindView(R.id.email_editxt_a1)
    EditText mEmailEditxtA1;
    @BindView(R.id.inputLayoutMailingAddr_a1)
    TextInputLayout mInputLayoutMailingAddrA1;
    @BindView(R.id.mail_addr_editxt_a1)
    EditText mMailAddrEditxtA1;
    @BindView(R.id.upload_img_btn1_a1)
    Button mUploadImgBtn1A1;
    @BindView(R.id.next_btn1_a1)
    Button mNextBtn1A1;
    @BindView(R.id.progressbar1_a1)
    AVLoadingIndicatorView mProgressbar1A1;


    String typeString,genderString,prifixString,stateString;
    private int currentStep = 0;
    int PICK_IMAGE_PASSPORT = 1;
    int CAM_IMAGE_PASSPORT = 2;
    private String cameraFilePath;
    NetworkConnection networkConnection=new NetworkConnection();

    Uri personal_info_img_uri;
    String personal_img_url = "";
    private UserPreferences userPreferences;

    public AllriskFragment1() {
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
    public static AllriskFragment1 newInstance(String param1, String param2) {
        AllriskFragment1 fragment = new AllriskFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAA1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAA1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_allrisk1, container, false);
        ButterKnife.bind(this,view);

        mStepView.go(currentStep, true);



        init();

        mTypeSpinnerA1();
        mPrefixSpinnerA1();
        stateSpinner();
        mGenderSpinnerA1();
        setViewActions();

        return  view;
    }

    private  void init(){
        userPreferences = new UserPreferences(getContext());

        //Temporal save and go to next Operation


        mCompanynameEditxtA1.setText(userPreferences.getAllRiskICompanyName());

        mTinNumEditxtA1.setText(userPreferences.getAllRiskITinNumber());

        mOfficeAddrEditxtA1.setText(userPreferences.getAllRiskIOff_addr());


        mContactPersonEditxtA1.setText(userPreferences.getAllRiskIContPerson());

        mNextKinEditxtA1.setText(userPreferences.getAllRiskINextKin());

        mFirstnameEditxtA1.setText(userPreferences.getAllRiskIFirstName());

        mLastnameEditxtA1.setText(userPreferences.getAllRiskILastName());

        mResidentsAddrEditxtA1.setText(userPreferences.getAllRiskIResAdrr());
        mPhoneNoEditxtA1.setText(userPreferences.getAllRiskIPhoneNum());
        mEmailEditxtA1.setText(userPreferences.getAllRiskIEmail());
        mMailAddrEditxtA1.setText(userPreferences.getAllRiskIMailingAddr());

    }


    private void mTypeSpinnerA1() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.type_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mTypeSpinnerA1.setAdapter(staticAdapter);

        mTypeSpinnerA1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String stringText = (String) parent.getItemAtPosition(position);
                if(stringText.equals("Individual")){

                    mTypeSpinnerA1.setVisibility(View.VISIBLE);
                    mTypeSpinnerA1.setClickable(true);


                    //De-Visualizing the corporate form
                    mInputLayoutCompanyNameA1.setVisibility(View.GONE);
                    mInputLayoutCompanyNameA1.setClickable(false);
                    mInputLayoutTinNumA1.setVisibility(View.GONE);
                    mInputLayoutTinNumA1.setClickable(false);
                    mInputLayoutOfficeAddrA1.setVisibility(View.GONE);
                    mInputLayoutOfficeAddrA1.setClickable(false);
                    mInputLayoutContactPersonA1.setVisibility(View.GONE);
                    mInputLayoutContactPersonA1.setClickable(false);

                    //Visualizing the individual form

                    mPrefixSpinnerA1.setVisibility(View.VISIBLE);
                    mPrefixSpinnerA1.setClickable(true);
                    mInputLayoutFirstNameA1.setVisibility(View.VISIBLE);
                    mInputLayoutFirstNameA1.setClickable(true);
                    mInputLayoutLastNameA1.setVisibility(View.VISIBLE);
                    mInputLayoutLastNameA1.setClickable(true);
                    mGenderSpinnerA1.setVisibility(View.VISIBLE);
                    mGenderSpinnerA1.setClickable(true);
                    mInputLayoutResAddrA1.setVisibility(View.VISIBLE);
                    mInputLayoutResAddrA1.setClickable(true);
                    mInputLayoutNextKinA1.setVisibility(View.VISIBLE);
                    mInputLayoutNextKinA1.setClickable(true);


                }else if(stringText.equals("Corporate")){

                    //De-Visualizing the individual form
                    mPrefixSpinnerA1.setVisibility(View.GONE);
                    mPrefixSpinnerA1.setClickable(false);
                    mInputLayoutFirstNameA1.setVisibility(View.GONE);
                    mInputLayoutFirstNameA1.setClickable(false);
                    mInputLayoutLastNameA1.setVisibility(View.GONE);
                    mInputLayoutLastNameA1.setClickable(false);
                    mInputLayoutNextKinA1.setVisibility(View.GONE);
                    mInputLayoutNextKinA1.setClickable(false);
                    mGenderSpinnerA1.setVisibility(View.GONE);
                    mGenderSpinnerA1.setClickable(false);
                    mInputLayoutResAddrA1.setVisibility(View.GONE);
                    mInputLayoutResAddrA1.setClickable(false);



                    //Visualizing the individual form
                    mTypeSpinnerA1.setVisibility(View.VISIBLE);
                    mTypeSpinnerA1.setClickable(true);
                    mInputLayoutCompanyNameA1.setVisibility(View.VISIBLE);
                    mInputLayoutCompanyNameA1.setClickable(true);
                    mInputLayoutTinNumA1.setVisibility(View.VISIBLE);
                    mInputLayoutTinNumA1.setClickable(true);
                    mInputLayoutOfficeAddrA1.setVisibility(View.VISIBLE);
                    mInputLayoutOfficeAddrA1.setClickable(true);
                    mInputLayoutContactPersonA1.setVisibility(View.VISIBLE);
                    mInputLayoutContactPersonA1.setClickable(true);

                }else {

                    //De-Visualizing the individual form
                    mPrefixSpinnerA1.setVisibility(View.GONE);
                    mInputLayoutFirstNameA1.setVisibility(View.GONE);
                    mInputLayoutLastNameA1.setVisibility(View.GONE);
                    mGenderSpinnerA1.setVisibility(View.GONE);
                    mInputLayoutResAddrA1.setVisibility(View.GONE);
                    mInputLayoutCompanyNameA1.setVisibility(View.GONE);
                    mInputLayoutNextKinA1.setVisibility(View.GONE);
                    mInputLayoutTinNumA1.setVisibility(View.GONE);
                    mInputLayoutOfficeAddrA1.setVisibility(View.GONE);
                    mInputLayoutContactPersonA1.setVisibility(View.GONE);




                    //Visualizing the individual form
                    mTypeSpinnerA1.setVisibility(View.VISIBLE);
                    mInputLayoutPhoneA1.setVisibility(View.VISIBLE);
                    mInputLayoutEmailA1.setVisibility(View.VISIBLE);
                    mInputLayoutMailingAddrA1.setVisibility(View.VISIBLE);


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //De-Visualizing the individual form
                mTypeSpinnerA1.getItemAtPosition(0);
                mPrefixSpinnerA1.setVisibility(View.GONE);
                mInputLayoutFirstNameA1.setVisibility(View.GONE);
                mInputLayoutLastNameA1.setVisibility(View.GONE);
                mGenderSpinnerA1.setVisibility(View.GONE);
                mInputLayoutResAddrA1.setVisibility(View.GONE);
                mInputLayoutNextKinA1.setVisibility(View.GONE);
                mInputLayoutCompanyNameA1.setVisibility(View.GONE);
                mInputLayoutTinNumA1.setVisibility(View.GONE);
                mInputLayoutOfficeAddrA1.setVisibility(View.GONE);
                mInputLayoutContactPersonA1.setVisibility(View.GONE);




                //Visualizing the individual form
                mTypeSpinnerA1.setVisibility(View.VISIBLE);
                mInputLayoutPhoneA1.setVisibility(View.VISIBLE);
                mInputLayoutEmailA1.setVisibility(View.VISIBLE);
                mInputLayoutMailingAddrA1.setVisibility(View.VISIBLE);


            }
        });

    }

    private void stateSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.state_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        state_spinner.setAdapter(staticAdapter);

        state_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String stateText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                state_spinner.getItemAtPosition(0);

            }
        });

    }

    private void mPrefixSpinnerA1() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.prefix_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mPrefixSpinnerA1.setAdapter(staticAdapter);

        mPrefixSpinnerA1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String prefixText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mPrefixSpinnerA1.getItemAtPosition(0);


            }
        });

    }
    private void mGenderSpinnerA1() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.gender_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mGenderSpinnerA1.setAdapter(staticAdapter);

        mGenderSpinnerA1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String genderText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                mGenderSpinnerA1.getItemAtPosition(0);

            }
        });

    }

    //setting onclicks listeners
    private void setViewActions() {

        mNextBtn1A1.setOnClickListener(this);
        mUploadImgBtn1A1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.next_btn1_a1:
//                validate user input
                validateUserInputs();

                break;

            case R.id.upload_img_btn1_a1:
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Choose Mode of Entry");
// add a list
                String[] entry = {"Camera", "Gallery"};
                builder.setItems(entry, (dialog, option) -> {
                    switch (option) {
                        case 0:
                            // direct entry
                            chooseIdImage_camera();
                            dialog.dismiss();
                            break;

                        case 1: // export

                            chooseImageFile();
                            dialog.dismiss();

                            break;

                    }
                });
// create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
                mUploadImgBtn1A1.setBackgroundColor(getResources().getColor(R.color.colorAccentEnds));

                break;

        }
    }

    private void chooseImageFile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_PASSPORT);
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        //This is the directory in which the file will be created. This is the default location of Camera photos
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for using again
        cameraFilePath = "file://" + image.getAbsolutePath();
        return image;
    }



    private void chooseIdImage_camera() {

        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID + ".fileprovider", createImageFile()));
            startActivityForResult(intent, CAM_IMAGE_PASSPORT);
        } catch (IOException ex) {
            ex.printStackTrace();
            showMessage("Invalid Entry");
            Log.i("Invalid_Cam_Entry",ex.getMessage());
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            showMessage("No image is selected, try again");
            return;
        }


        showMessage("Uploading...");
        if (networkConnection.isNetworkConnected(getContext())) {
            Random random=new Random();
            String rand= String.valueOf(random.nextInt());
            if (requestCode == 1) {
                personal_info_img_uri = data.getData();

                try {
                    if (personal_info_img_uri != null) {
                        String name = mEmailEditxtA1.getText().toString()+rand;
                        if (name.equals("")) {
                            showMessage("Enter your email address first");

                        } else {

                            String imageId = MediaManager.get().upload(Uri.parse(personal_info_img_uri.toString()))
                                    .option("public_id", "user_registration/profile_photos/user_passport" + name)
                                    .unsigned("xbiscrhh").callback(new UploadCallback() {
                                        @Override
                                        public void onStart(String requestId) {
                                            // your code here
                                            mNextBtn1A1.setVisibility(View.GONE);
                                            mProgressbar1A1.setVisibility(View.VISIBLE);

                                        }

                                        @Override
                                        public void onProgress(String requestId, long bytes, long totalBytes) {
                                            // example code starts here
                                            Double progress = (double) bytes / totalBytes;
                                            // post progress to app UI (e.g. progress bar, notification)
                                            // example code ends here
                                            mProgressbar1A1.setVisibility(View.VISIBLE);
                                            if(!networkConnection.isNetworkConnected(getContext())){
                                                mProgressbar1A1.setVisibility(View.GONE);
                                                mNextBtn1A1.setVisibility(View.VISIBLE);
                                                showMessage("Internet Connection Failed");
                                            }

                                        }

                                        @Override
                                        public void onSuccess(String requestId, Map resultData) {
                                            // your code here

                                            showMessage("Image Uploaded Successfully");
                                            Log.i("ImageRequestId ", requestId);
                                            Log.i("ImageUrl ", String.valueOf(resultData.get("url")));
                                            mProgressbar1A1.setVisibility(View.GONE);
                                            mNextBtn1A1.setVisibility(View.VISIBLE);
                                            personal_img_url = String.valueOf(resultData.get("url"));


                                        }

                                        @Override
                                        public void onError(String requestId, ErrorInfo error) {
                                            // your code here
                                            showMessage("Error: " + error.toString());
                                            Log.i("Error: ", error.toString());

                                            mNextBtn1A1.setVisibility(View.VISIBLE);
                                            mProgressbar1A1.setVisibility(View.GONE);
                                        }

                                        @Override
                                        public void onReschedule(String requestId, ErrorInfo error) {
                                            // your code here
                                        }
                                    })
                                    .dispatch();

                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    showMessage("Please Check your Image");

                }

            }else if(requestCode==2){
                personal_info_img_uri = Uri.parse(cameraFilePath);

                try {
                    if (personal_info_img_uri != null) {
                        String name = mEmailEditxtA1.getText().toString()+rand;
                        if (name.equals("")) {
                            showMessage("Enter your email address first");

                        } else {

                            String imageId = MediaManager.get().upload(personal_info_img_uri)
                                    .option("public_id", "user_registration/profile_photos/user_passport" + name)
                                    .unsigned("xbiscrhh").callback(new UploadCallback() {
                                        @Override
                                        public void onStart(String requestId) {
                                            // your code here
                                            mNextBtn1A1.setVisibility(View.GONE);
                                            mProgressbar1A1.setVisibility(View.VISIBLE);

                                        }

                                        @Override
                                        public void onProgress(String requestId, long bytes, long totalBytes) {
                                            // example code starts here
                                            Double progress = (double) bytes / totalBytes;
                                            // post progress to app UI (e.g. progress bar, notification)
                                            // example code ends here
                                            mProgressbar1A1.setVisibility(View.VISIBLE);
                                            if(!networkConnection.isNetworkConnected(getContext())){
                                                mProgressbar1A1.setVisibility(View.GONE);
                                                mNextBtn1A1.setVisibility(View.VISIBLE);
                                                showMessage("Internet Connection Failed");
                                            }

                                        }

                                        @Override
                                        public void onSuccess(String requestId, Map resultData) {
                                            // your code here

                                            showMessage("Image Uploaded Successfully");
                                            Log.i("ImageRequestId ", requestId);
                                            Log.i("ImageUrl ", String.valueOf(resultData.get("url")));
                                            mProgressbar1A1.setVisibility(View.GONE);
                                            mNextBtn1A1.setVisibility(View.VISIBLE);
                                            personal_img_url = String.valueOf(resultData.get("url"));


                                        }

                                        @Override
                                        public void onError(String requestId, ErrorInfo error) {
                                            // your code here
                                            showMessage("Error: " + error.toString());
                                            Log.i("Error: ", error.toString());

                                            mNextBtn1A1.setVisibility(View.VISIBLE);
                                            mProgressbar1A1.setVisibility(View.GONE);
                                        }

                                        @Override
                                        public void onReschedule(String requestId, ErrorInfo error) {
                                            // your code here
                                        }
                                    })
                                    .dispatch();

                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    showMessage("Please Check your Image");

                }
            }
            return;
        }
        showMessage("No Internet connection discovered!");
    }



    private void validateUserInputs() {


        boolean isValid = true;

        if (mFirstnameEditxtA1.getText().toString().isEmpty()&&mInputLayoutFirstNameA1.isClickable()) {
            mInputLayoutFirstNameA1.setError("Your FirstName is required!");
            isValid = false;
        } else if (mLastnameEditxtA1.getText().toString().isEmpty()&&mInputLayoutLastNameA1.isClickable()) {
            mInputLayoutLastNameA1.setError("Your LastName is required!");
            isValid = false;
        } else if (mCompanynameEditxtA1.getText().toString().isEmpty()&&mInputLayoutCompanyNameA1.isClickable()) {
            mInputLayoutCompanyNameA1.setError("Your Company Name is required!");
            isValid = false;
        } else if (mTinNumEditxtA1.getText().toString().isEmpty()&&mInputLayoutTinNumA1.isClickable()) {
            mInputLayoutTinNumA1.setError("Your TIN Number is required!");
            isValid = false;
        } else if (mOfficeAddrEditxtA1.getText().toString().isEmpty()&&mInputLayoutOfficeAddrA1.isClickable()) {
            mInputLayoutOfficeAddrA1.setError("Office Address is required!");
            isValid = false;
        }else if (mNextKinEditxtA1.getText().toString().isEmpty()&&mInputLayoutNextKinA1.isClickable()) {
            mInputLayoutNextKinA1.setError("Next of Kin is required!");
            isValid = false;
        }else {
            mInputLayoutFirstNameA1.setErrorEnabled(false);
            mInputLayoutLastNameA1.setErrorEnabled(false);
            mInputLayoutCompanyNameA1.setErrorEnabled(false);
            mInputLayoutTinNumA1.setErrorEnabled(false);
            mInputLayoutNextKinA1.setErrorEnabled(false);
            mInputLayoutOfficeAddrA1.setErrorEnabled(false);
        }

        if (mEmailEditxtA1.getText().toString().isEmpty()&&mInputLayoutEmailA1.isClickable()) {
            mInputLayoutEmailA1.setError("Email is required!");
            isValid = false;
        } else if (!isValidEmailAddress(mEmailEditxtA1.getText().toString())) {
            mInputLayoutEmailA1.setError("Valid Email is required!");
            isValid = false;
        } else {
            mInputLayoutEmailA1.setErrorEnabled(false);
        }

        if (mMailAddrEditxtA1.getText().toString().isEmpty()&& mInputLayoutMailingAddrA1.isClickable()) {
            mInputLayoutMailingAddrA1.setError("Mailing Address is required!");
            isValid = false;
        } else if (!isValidEmailAddress(mMailAddrEditxtA1.getText().toString())&&mInputLayoutMailingAddrA1.isClickable()) {
            mInputLayoutMailingAddrA1.setError("Valid Mailing Address is required!");
            isValid = false;
        } else {
            mInputLayoutMailingAddrA1.setErrorEnabled(false);
        }


        if (mPhoneNoEditxtA1.getText().toString().isEmpty()&&mInputLayoutPhoneA1.isClickable()) {
            mInputLayoutPhoneA1.setError("Phone number is required");
            isValid = false;
        } else if (mPhoneNoEditxtA1.getText().toString().trim().length() < 11 && mInputLayoutPhoneA1.isClickable()) {
            mInputLayoutPhoneA1.setError("Your Phone number must be 11 in length");
            isValid = false;
        } else {
            mInputLayoutPhoneA1.setErrorEnabled(false);
        }

        if (mContactPersonEditxtA1.getText().toString().isEmpty()&&mInputLayoutContactPersonA1.isClickable()) {
            mInputLayoutContactPersonA1.setError("Contact Person is required");
            isValid = false;
        } else {
            mInputLayoutContactPersonA1.setErrorEnabled(false);
        }
        if (mResidentsAddrEditxtA1.getText().toString().isEmpty()&&mInputLayoutResAddrA1.isClickable()) {
            mInputLayoutResAddrA1.setError("Resident Address is required");
            isValid = false;
        } else {
            mInputLayoutResAddrA1.setErrorEnabled(false);
        }

        /*if (personal_img_url==null) {
            showMessage("Please upload an image: passport,company license..etc");
            isValid = false;
        }*/


        //Tyepe Spinner
        typeString = mTypeSpinnerA1.getSelectedItem().toString();
        if (typeString.equals("Select Type")&&mTypeSpinnerA1.isClickable()) {

            showMessage("Select Product Type");
            isValid = false;
        }
        //Prefix Spinner
        prifixString = mPrefixSpinnerA1.getSelectedItem().toString();
        if (prifixString.equals("Select Prefix")&&mPrefixSpinnerA1.isClickable()) {
            showMessage("Select your Prefix e.g Mr.");
            isValid = false;
        }

        genderString = mGenderSpinnerA1.getSelectedItem().toString();
        if (genderString.equals("Gender")&&mGenderSpinnerA1.isClickable()) {
            showMessage("Don't forget to Select Gender");
            isValid = false;
        }
       

        if (isValid) {
//            send inputs to next next page
//            Goto to the next Registration step
            initFragment();
        }




    }

    private void initFragment() {
        mNextBtn1A1.setVisibility(View.GONE);
        mProgressbar1A1.setVisibility(View.VISIBLE);

        try {

            //Temporal save and go to next Operation

            userPreferences.setAllRiskPtype(typeString);
            userPreferences.setAllRiskIPrefix(prifixString);
            userPreferences.setAllRiskICompanyName(mCompanynameEditxtA1.getText().toString());
            userPreferences.setAllRiskITinNumber(mTinNumEditxtA1.getText().toString());
            userPreferences.setAllRiskIOff_addr(mOfficeAddrEditxtA1.getText().toString());
            userPreferences.setAllRiskINextKin(mNextKinEditxtA1.getText().toString());
            userPreferences.setAllRiskIContPerson(mContactPersonEditxtA1.getText().toString());
            userPreferences.setAllRiskIFirstName(mFirstnameEditxtA1.getText().toString());
            userPreferences.setAllRiskILastName(mLastnameEditxtA1.getText().toString());
            userPreferences.setAllRiskIGender(genderString);
            userPreferences.setAllRiskIState(stateString);
            userPreferences.setAllRiskIResAdrr(mResidentsAddrEditxtA1.getText().toString());
            userPreferences.setAllRiskIPhoneNum(mPhoneNoEditxtA1.getText().toString());
            userPreferences.setAllRiskIEmail(mEmailEditxtA1.getText().toString());
            userPreferences.setAllRiskIMailingAddr(mMailAddrEditxtA1.getText().toString());
            userPreferences.setAllRiskPersonalImage(personal_img_url);

            if (currentStep < mStepView.getStepCount() - 1) {
                currentStep++;
                Fragment allriskFragment2 = new AllriskFragment2();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_allrisk_form_container, allriskFragment2);
                ft.commit();
                mStepView.go(currentStep, true);

            } else {
                mStepView.done(true);
            }



        }catch (Exception e){
            Log.i("Form Error",e.getMessage());
            mProgressbar1A1.setVisibility(View.GONE);
            mNextBtn1A1.setVisibility(View.VISIBLE);
            showMessage("Error: " + e.getMessage());
        }

    }


    private void showMessage(String s) {
        Snackbar.make(mQbFormLayout1, s, Snackbar.LENGTH_LONG).show();
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        String Email = email.trim();
        if (null != Email) {
            String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(Email);
            if (!matcher.matches()) {
                result = false;
            }
        }

        return result;
    }


}
