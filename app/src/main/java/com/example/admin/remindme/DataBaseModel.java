package com.example.admin.remindme;

import android.app.TimePickerDialog;
import java.util.Date;

/**
 * Created by admin on 19-01-2017.
 */

public class DataBaseModel {

    String Email_Id;
    String Name;
    String Mobile_No;
    String Address;
    String Gender;
    String Type;
    String Desc;
    String Install_Month,Snooze_time_hour,Remind_day;
    String Birth_Date,Start_Date,End_Date;
    int Id;
    String Remind_time;

    public DataBaseModel(int id, String name, String mobile_No, String email_Id, String address, String gender, String birth_Date, String start_Date, String end_Date, String install_month,String remindme,String snooze_time,String Remindday,String desc,String type) {
        this.setAddress(address);
        this.setBirth_Date(birth_Date);
        this.setEmail_Id(email_Id);
        this.setEnd_Date(end_Date);
        this.setGender(gender);
        this.setId(id);
        this.setMobile_No(mobile_No);
        this.setName(name);
        this.setDesc(desc);
        this.setStart_Date(start_Date);
        this.setSnooze_time_hour(snooze_time);
        this.setRemind_day(Remindday);
        this.setRemind_time(remindme);
        this.setInstall_Month(install_month);
        this.setType(type);
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getRemind_time() {
        return Remind_time;
    }

    public void setRemind_time(String remind_time) {
        Remind_time = remind_time;
    }

    public String getSnooze_time_hour() {
        return Snooze_time_hour;
    }

    public void setSnooze_time_hour(String snooze_time_hour) {
        Snooze_time_hour = snooze_time_hour;
    }

    public String getRemind_day() {
        return Remind_day;
    }

    public void setRemind_day(String remind_day) {
        Remind_day = remind_day;
    }

    public String getInstall_Month() {
        return Install_Month;
    }

    public void setInstall_Month(String install_Month) {
        Install_Month = install_Month;
    }

    public String getStart_Date() {
        return Start_Date;
    }

    public void setStart_Date(String start_Date) {
        Start_Date = start_Date;
    }

    public String getEnd_Date() {
        return End_Date;
    }

    public void setEnd_Date(String end_Date) {
        End_Date = end_Date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getBirth_Date() {
        return Birth_Date;
    }

    public void setBirth_Date(String birth_Date) {
        Birth_Date = birth_Date;
    }

    public String getEmail_Id() {
        return Email_Id;
    }

    public void setEmail_Id(String email_Id) {
        Email_Id = email_Id;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getMobile_No() {
        return Mobile_No;
    }

    public void setMobile_No(String mobile_No) {
        Mobile_No = mobile_No;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

}
