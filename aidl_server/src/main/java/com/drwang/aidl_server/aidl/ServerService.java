package com.drwang.aidl_server.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.drwang.aidl_server.aidl.BinderObj;
import com.drwang.module_me.com.example.aidl_server.model.Person;

import java.util.ArrayList;
import java.util.List;

public class ServerService extends Service {

    private static final String TAG = "ServerSevice";
    private List<Person> mPeople = new ArrayList<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mPeople.add(new Person("haha",3));
    }

    private BinderObj mStub = new BinderObj() {
        @Override
        public void addPerson(Person mPerson) {
            if (mPerson == null) {
                mPerson = new Person();
            }
            mPeople.add(mPerson);
        }

        @Override
        public List<Person> getPersonList() {
            return mPeople;
        }
    };
}
