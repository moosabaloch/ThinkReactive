package com.moosa.thinkreactive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.moosa.thinkreactive.model.users.User;
import com.moosa.thinkreactive.network.UserService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private Button button;
    private AutoCompleteTextView completeTextView;
    private View view;
    private ArrayList<User> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.click_button);
        completeTextView = (AutoCompleteTextView) findViewById(R.id.autoComplete);
        view = findViewById(R.id.gesture);
        userArrayList = new ArrayList<>();
        UserService userService = new UserService();
        userService.getUsersSortedByName().subscribe(new Consumer<List<User>>() {
            @Override
            public void accept(List<User> users) throws Exception {
                  for (User user : users) {
                AppLogs.logd("User name is : " + user.getName());
                AppLogs.logd("User email is : " + user.getEmail());
                AppLogs.logd("User id is : " + user.getId());
                AppLogs.logd("User website is : " + user.getWebsite());
                AppLogs.logd("User Address is : " + user.getAddress().toString());
                AppLogs.logd("User Company is : " + user.getCompany().toString());
                AppLogs.logd("----------------------------------------------------");
                }

            }
        });


        /*
        userService.getUsersSortedByName().subscribe(new Observer<User>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(User user) {
                //userArrayList.add(users);
               // AppLogs.logd("Users Size is : " + userArrayList.size());
              //  for (User user : userArrayList) {
                    AppLogs.logd("User name is : " + user.getName());
                    AppLogs.logd("User email is : " + user.getEmail());
                    AppLogs.logd("User id is : " + user.getId());
                    AppLogs.logd("User website is : " + user.getWebsite());
                    AppLogs.logd("User Address is : " + user.getAddress().toString());
                    AppLogs.logd("User Company is : " + user.getCompany().toString());
                    AppLogs.logd("----------------------------------------------------");
                //}
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                AppLogs.logd("Observable Completed");
            }
        });
*/
    }

/*


 try {
            GetUser userRequest = GetUser.retrofit.create(GetUser.class);
            // To call is as a synchronous request,
            // just call execute or call enqueue to make an asynchronous request.
            userRequest.getAllUsers().enqueue(this);


            userRequest.getUserById(4).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    AppLogs.logd("Posting User:" + response.body().toString());

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        if (response.isSuccessful()) {
            userArrayList.addAll(response.body());
            AppLogs.logd("Users Size is : " + userArrayList.size());
            for (User user : userArrayList) {
                AppLogs.logd("User name is : " + user.getName());
                AppLogs.logd("User email is : " + user.getEmail());
                AppLogs.logd("User id is : " + user.getId());
                AppLogs.logd("User website is : " + user.getWebsite());
                AppLogs.logd("User Address is : " + user.getAddress().toString());
                AppLogs.logd("User Company is : " + user.getCompany().toString());
                AppLogs.logd("----------------------------------------------------");
            }
        }
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {

    }

* */





    /*
    TEMP CODE
      User u = response.body();
                  try {
                      JSONObject jsonObject = new JSONObject();
                      jsonObject.put("name", u.getName());
                      jsonObject.put("email",u.getEmail());
                      jsonObject.put("id",u.getId());
                      jsonObject.put("phone",u.getPhone());
                      JSONObject newJson = new JSONObject();
                      newJson.put("test1",jsonObject);
                      PostJSON json = PostJSON.retrofit.create(PostJSON.class);
                      json.postJson(jsonObject).enqueue(new Callback<JSONObject>() {
                          @Override
                          public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                              AppLogs.logd("onResponse(JSON)");
                              AppLogs.logd("code:"+response.code());
                              AppLogs.logd("Raw:"+response.raw().toString());
                              AppLogs.logd("isTrue();"+response.isSuccessful());
                              AppLogs.logd("Body();"+response.body().toString());
                          }

                          @Override
                          public void onFailure(Call<JSONObject> call, Throwable t) {
                              AppLogs.logd("onError(JSON)");
                              t.printStackTrace();
                          }
                      });
                  }catch (Exception ex){
                      AppLogs.logd("Error ");
                  }
    * */
}
