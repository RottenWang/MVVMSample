package com.drwang.aidl_server.aidl;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import java.util.List;

import static com.drwang.aidl_server.aidl.Stub.DESCRIPTOR;

//实现了Bp端与binder通信的相关代码
public class Proxy implements PersonManager {
    private IBinder mIBinder;

    public Proxy(IBinder mIBinder) {
        this.mIBinder = mIBinder;
    }

    public java.lang.String getInterfaceDescriptor() {
        return DESCRIPTOR;
    }

    @Override
    public void addPerson(Person mPerson) {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();

        try {
            data.writeInterfaceToken(DESCRIPTOR);
            if (mPerson != null) {
                data.writeInt(1);
                mPerson.writeToParcel(data, 0);
            } else {
                data.writeInt(0);
            }
            boolean status = mIBinder.transact(Stub.TRANSAVTION_addPerson, data, reply, 0);
//            if (!status && getDefaultImpl() != null) {
//                getDefaultImpl().addPerson(mPerson);
//                return;
//            }
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
            data.writeInterfaceToken(DESCRIPTOR);
            mIBinder.transact(Stub.TRANSAVTION_getPerson, data, reply, 0);
            reply.readException();
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
    public int getInt() {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        int result = 0;
        data.writeInterfaceToken(DESCRIPTOR);
        try {
            mIBinder.transact(Stub.TRANSAVTION_getInt, data, reply, 0);
            result = reply.readInt();
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
