package com.drwang.aidl_server.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

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
        Person person = new Person();
        person.name = "zhangsan";
        person.age = 3;
        mPeople.add(person);
    }

    private Stub mStub = new Stub() {
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

        @Override
        public int getInt() {
            return 3;
        }
    };
}
