package com.example.user.troyecomputersystems;

public class Projects {

    String company, customerID, employeeID, skills, task;

    public Projects(String company, String customerID, String employeeID, String  skills,String task)
    {
        this.company = company;
        this.customerID =customerID;
        this.employeeID = employeeID;
        this.skills = skills;
        this.task = task;
    }

    public String getCompany()
    {
        return company;
    }
    public void setCompany()
    {
        this.company = company;
    }

    public String getCustomerID()
    {
        return customerID;
    }
    public void setCustomerID()
    {
        this.customerID = customerID;
    }

    public String getEmployeeID()
    {
        return employeeID;
    }
    public void setEmployeeID()
    {
        this.employeeID = employeeID;
    }

    public String getSkills()
    {
        return skills;
    }
    public void setSkills()
    {
        this.skills = skills;
    }

    public String getTask()
    {
        return task;
    }
    public void setTask()
    {
        this.task = task;
    }
}
