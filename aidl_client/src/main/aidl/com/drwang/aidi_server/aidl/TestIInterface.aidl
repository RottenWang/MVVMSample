// TestIInterface.aidl
package com.drwang.aidl_server.aidl;
import com.drwang.aidl_server.aidl.Person;
parcelable Person;
// Declare any non-default types here with import statements

interface TestIInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     void addPerson(in Person mPerson);
        List<Person> getPersonList();
        int getInt();
        List<Person> abc(out Person mPerson);
        List<Person> abcd(inout  Person mPerson);
        oneway void abcde(in  Person mPerson);

}