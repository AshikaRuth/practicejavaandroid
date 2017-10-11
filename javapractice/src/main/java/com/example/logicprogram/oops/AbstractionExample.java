package com.example.logicprogram.oops;

import java.util.Scanner;

/**
 * Created by parasmani.sharma on 25/07/2017.
 */

/*
* Best example to analyze and think when you talk about Client-Server based interaction using Abstract.
* This type of interaction we have done in Aidl example - (Aidl_Test_Cover)
* Try to assume this program as client-server interaction. Here you can consider that Bank Abstract class is client side
* and its implementation is on server side i.e SBI server.
* Here we have shown abstract class only at Client Side. But in real world senerio - one Abstract class (with the
* same name as Client Abstract class) is also present at Server Side (as like client side Abstract class) as we have done in
* our AIDl_Test_Cover project. By this we can analyze that a pipeline will create for communication between both
* Client and Server(acts like mirror - which uses shared memory concept on same device for communication between two
* Apps using IBinder Interface of Android OS).
* */

class SBI extends Bank
{
    private int cash = 1000000;
    @Override
    public int withdrawelAmount(int i) {
        if(i < 1200)
        {
            System.out.println("Cash withdrawel successfully !!");
            cash = cash-i;
            System.out.println("\nRemaining Account Balance : " + cash);
            return i;

        }else {
            System.out.println("\nYou are not allowed");
        }
        return 0;
    }

    @Override
    public void getLogin() {
        System.out.println("\nUser Logged In Successfully !!");
    }

    @Override
    public void failed() {
        return;
    }

}

class Failed extends Bank
{

    @Override
    public int withdrawelAmount(int i) {
        return 0;
    }

    @Override
    public void getLogin() {

    }

    @Override
    public void failed() {
        System.out.println("User connection Faiiled");
        System.out.println("***** Wrong Password *********");
    }
}


/************Top Server Side********************/



/************Down Client Side********************/

abstract class Bank
{
    public abstract int withdrawelAmount(int i);
    public abstract void getLogin();
    public abstract void failed();
}




public class AbstractionExample {

    public static void main(String arg[])
    {

        /*
        * when user authentication is successfull
        * then pass SBI object
        * else pass failed object
        *
        * Suppose password is : 12345
        * */



        System.out.println("Enter Password to login in bank : " );
        Scanner scan = new Scanner(System.in);
        scan.nextInt();

        if(scan.nextInt() == 12345)
        {
            SBI success = new SBI();
            connectBank(success);
        }
        else
        {
            Failed failed = new Failed();
            connectBank(failed);
        }

    }

    private static void connectBank(Object obj)
    {
        if(obj instanceof SBI)
        {

            Bank user1 = (SBI)obj;
            user1.getLogin();
            user1.withdrawelAmount(1000);
        }else
        {
            Bank user1 = (Failed)obj;
            user1.failed();
        }



    }


}
