package com.drwang.aidl_server.aidl;

import android.os.IInterface;

import java.util.List;

public interface PersonManager extends IInterface {
    void addPerson(Person mPerson);
    List<Person> getPersonList();
    int getInt();
}
