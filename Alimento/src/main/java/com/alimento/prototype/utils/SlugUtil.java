package com.alimento.prototype.utils;

import com.alimento.prototype.repositories.blog.BlogPostRepository;
import com.alimento.prototype.services.blog.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SlugUtil {

    public String toSlug(String title){ // This method is not used Anywhere right now. Will be used to generate auto incrementing slug, whenever there are requests with same slug

        if(title == null || title.isBlank()){
            throw new IllegalArgumentException("Title cannot be Blank!!!");  // Checking if the input slug is null or empty
        }

        String baseSlug = title.trim().toLowerCase().replace(" ", "-"); // Building Final slug by converting all letters to lower case and by replacing space with hyphen("-")

        return baseSlug;
    }

    public static String generateUniqueSlug(List<String> matchingSlugs, String baseSlug){     // Unique Slug method will generate unique slug by adding numbering to the end of slug if slug is already associated with a previously uploaded blog

        String regex = ".*-\\d+$"; // Regex for matching the numbers

        if(matchingSlugs == null || matchingSlugs.size() == 0) return baseSlug;

        String lastMatchingString = matchingSlugs.stream()
                .filter(slug -> slug.startsWith(baseSlug))
                .filter(slug -> slug.matches(regex))
                .reduce((first, second) -> second)
                .orElse(null);

        if(lastMatchingString == null ||
                (lastMatchingString.length() == baseSlug.length() && !Character.isDigit(lastMatchingString.charAt(lastMatchingString.length()-1)))) {
            return baseSlug+"-1";
        }

        int slugSuffix = Integer.valueOf(lastMatchingString.substring(baseSlug.length()+1));

        int newSlugSuffix = slugSuffix+1;

        return baseSlug+"-"+newSlugSuffix;
    }

}

