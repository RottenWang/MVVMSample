package com.drwang.aidl_server.aidl;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import com.drwang.aidl_server.aidl.BinderObj;
import com.drwang.aidl_server.aidl.PersonManager;
import com.drwang.module_me.com.example.aidl_server.model.Person;

import java.util.List;

public class Proxy implements PersonManager {
    private IBinder mIBinder;

    public Proxy(IBinder mIBinder) {
        this.mIBinder = mIBinder;
    }

    @Override
    public void addPerson(Person mPerson) {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();

        data.writeInterfaceToken(BinderObj.DESCRIPTOR);
        if (mPerson != null) {
            data.writeInt(1);
            mPerson.writeToParcel(data, 0);
        } else {
            data.writeInt(0);
        }
        try {
            mIBinder.transact(BinderObj.TRANSAVTION_addPerson, data, reply, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            reply.recycle();
            data.recycle();
        }
        reply.readException();
    }

    @Override
    public List<Person> getPersonList() {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        List<Person> result = null;
        try {
            data.writeInterfaceToken(BinderObj.DESCRIPTOR);
            mIBinder.transact(BinderObj.TRANSAVTION_getPerson, data, reply, 0);
            result = reply.createTypedArrayList(Person.CREATOR);

        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            reply.recycle();
            data.recycle();
        }
        return result;
    }

    @Override
    public IBinder asBinder() {
        return mIBinder;
    }
}
