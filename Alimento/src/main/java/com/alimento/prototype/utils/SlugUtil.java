package com.alimento.prototype.utils;

import java.util.Locale;

public class SlugUtil {

    private SlugUtil(){
        // Private Constructor to prevent Instantiation
    }

    public static String toSlug(String inputSlug){

        if(inputSlug == null || inputSlug.isBlank()){
            throw new IllegalArgumentException("inputSlug cannot be Blank!!!");  // Checking if the input slug is null or empty
        }

        String finalSlug = inputSlug.trim().toLowerCase().replace(" ", "-"); // Building Final slug by converting all letters to lower case and by replacing space with hyphen("-")

        return finalSlug;

    }

}

