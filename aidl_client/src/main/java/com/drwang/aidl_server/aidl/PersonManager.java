package com.drwang.aidl_server.aidl;

import android.os.IInterface;

import com.drwang.module_me.com.example.aidl_server.model.Person;

import java.util.List;

public interface PersonManager extends IInterface {
    void addPerson(Person mPerson);
    List<Person> getPersonList();
}
