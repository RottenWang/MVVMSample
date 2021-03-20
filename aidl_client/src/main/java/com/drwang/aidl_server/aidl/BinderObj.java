package com.drwang.aidl_server.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


public abstract class BinderObj extends Binder implements PersonManager {
    public static final String DESCRIPTOR = "com.drwang.aidl_server.aidl.PersonManager";
    public static final int TRANSAVTION_getPerson = IBinder.FIRST_CALL_TRANSACTION;
    public static final int TRANSAVTION_addPerson = IBinder.FIRST_CALL_TRANSACTION + 1;
    public static final int TRANSAVTION_getInt = IBinder.FIRST_CALL_TRANSACTION + 2;

    public static PersonManager asInterface(IBinder mIBinder) {
        IInterface iInterface = mIBinder.queryLocalInterface(DESCRIPTOR);
        if (null != iInterface && iInterface instanceof PersonManager) {
            return (PersonManager) iInterface;
        }
        return new Proxy(mIBinder);

    }

    public BinderObj() {
        attachInterface(this, DESCRIPTOR);
    }

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION:
                reply.writeString(DESCRIPTOR);
                return true;
            case TRANSAVTION_getPerson:
                data.enforceInterface(DESCRIPTOR);
                List<Person> result = getPersonList();
                reply.writeNoException();
                reply.writeTypedList(result);
                return true;
            case TRANSAVTION_getInt:
                data.enforceInterface(DESCRIPTOR);
                int ints = getInt();
                reply.writeInt(ints);
                reply.writeNoException();
                return true;
            case TRANSAVTION_addPerson:
                data.enforceInterface(DESCRIPTOR);
                Person arg0 = null;
                if (data.readInt() != 0) {
                    arg0 = Person.CREATOR.createFromParcel(data);
                }else {
                    arg0 = null;
                }
                this.addPerson(arg0);
               reply.writeNoException();
                return true;
        }
        return super.onTransact(code, data, reply, flags);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }
}
