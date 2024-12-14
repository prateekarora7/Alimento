package com.alimento.prototype.utils;

import com.alimento.prototype.repositories.blog.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SlugUtil {

    @Autowired
    private final BlogPostRepository blogPostRepository;

    private SlugUtil(BlogPostRepository blogPostRepository){    // Private Constructor to prevent Instantiation
        this.blogPostRepository = blogPostRepository;
    }

    public String toSlug(String inputSlug){ // This method is not used Anywhere right now. Will be used to generate auto incrementing slug, whenever there are requests with same slug

        if(inputSlug == null || inputSlug.isBlank()){
            throw new IllegalArgumentException("Slug cannot be Blank!!!");  // Checking if the input slug is null or empty
        }

        String slug = inputSlug.trim().toLowerCase().replace(" ", "-"); // Building Final slug by converting all letters to lower case and by replacing space with hyphen("-")

        if(blogPostRepository.existsBySlug(slug) == 1){
            throw new RuntimeException("This Slug already exists in the database");
        }

        return slug;
    }

    private String generateUniqueSlug(String slug){     // Unique Slug method will generate unique slug by adding numbering to the end of slug if slug is already associated with a previously uploaded blog

        if(!Character.isDigit(slug.charAt(slug.length()-1))) return slug+"1";

        int breakIndex = 0; // this will tell us the index from where the numbers are starting

        for (int i = slug.length()-1; i >=0 ; i--) {   //Loop through string to get the index from where the number is starting
            if (!Character.isDigit(slug.charAt(i))) {
                breakIndex = i+1;
                break;
            }
        }

        String prefix = slug.substring(0, breakIndex); //String containing only characters
        String numberString = slug.substring(breakIndex); //String contaaining only numbers

        Integer number = Integer.valueOf(numberString);

        String incrementedNumber = String.valueOf(number+1); // Incrementing Number by 1

        return prefix+incrementedNumber;

    }

}

