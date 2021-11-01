package com.example.crm.SalesManagement;

public class Callsinfo
{
    int totalcalls;
    String hours;
    String date;

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }



    public Callsinfo(int totalcalls, String hours, String date)
    {
        this.totalcalls = totalcalls;
        this.hours = hours;
        this.date = date;
    }

    public Callsinfo(int totalcalls, String hours)
    {
        this.totalcalls = totalcalls;
        this.hours = hours;
    }

    public int getTotalcalls()
    {
        return totalcalls;
    }

    public void setTotalcalls(int totalcalls)
    {
        this.totalcalls = totalcalls;
    }

    public String getHours()
    {
        return hours;
    }

    public void setHours(String hours)
    {
        this.hours = hours;
    }
}
