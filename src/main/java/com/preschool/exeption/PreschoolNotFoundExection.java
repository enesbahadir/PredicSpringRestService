package com.preschool.exeption;

public class PreschoolNotFoundExection extends RuntimeException{
    public PreschoolNotFoundExection(Integer id)
    {
        super("Could not find preschool " + id);
    }
}
